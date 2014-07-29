package com.polaris.psi.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.Constants;
import com.polaris.psi.resource.ApplicationAttributeResource;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.util.AttributeHelper;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polarisind.proxy.emailservice.DefaultEmailService;
import com.polarisind.proxy.emailservice.EmailClient;
import com.polarisind.proxy.emailservice.EmailResponseType;
import com.polarisind.proxy.emailservice.EmailType;
import com.polarisind.proxy.emailservice.MessageType;
import com.polarisind.proxy.emailservice.SuccessType;
import com.polarisind.proxy.emailservice.TargetType;

@Service
public class EmailService {
	
	@Autowired
	AttributeHelper attributeHelper;

	private static final SplunkLogger LOG = new SplunkLogger(EmailService.class);
	
    private static Properties EMAIL_PROPS = new Properties();

    static {
        try {
            InputStream rs = EmailService.class.getClassLoader().getResourceAsStream("emailProperties.xml");
            EMAIL_PROPS.loadFromXML(rs);
            
            // Initialize Velocity
            Velocity.init();
        }
        catch(IOException ioe) {
            LOG.error(PolarisIdentity.get(),"static initializer", ioe);
        }
    }
    
    private void sendEmail(String subject, String messageBody, String to) throws Exception {
    	LOG.methodStart(PolarisIdentity.get(), "sendEmail");

    	try {
   		
        	EmailClient client = EmailClient.getInstance();
        	
        	EmailType message = new EmailType();
        	
        	LOG.info(PolarisIdentity.get(), "sendEmail", "Attempting to send email to: " + to);
        	
        	String fromName = attributeHelper.getAttribute(Constants.ATTR_EMAILFROMNAME);
        	String fromAddr = attributeHelper.getAttribute(Constants.ATTR_EMAILBOUNCEADDR);
        	
        	message.getToAddresses().add(to);
        	message.setSubject(subject);
        	message.setFromName(fromName);
        	message.setBounceAddress(fromAddr);
        	message.setSourceAddress(fromAddr);
        	message.setHtmlBody(messageBody);
        	message.setTextBody(messageBody);
        	message.setTargetType(TargetType.BUSINESS);
        	
        	// Get the URL for the Email service
        	String url = attributeHelper.getAttribute(Constants.ATTR_EMAILSERVICEURL);
        	
        	EmailResponseType response = 
        			client.sendEmail(message, url);
        	
        	if(response.getSuccess()==SuccessType.FAILED) {
        		
        		String errorMsg="";
        		for(MessageType msg: response.getMessages()) {
        			errorMsg+=msg.getMessage() + "\n";
        		}
        		
        		LOG.error(PolarisIdentity.get(), "sendEmail",errorMsg);
        	}
        	
    	} catch (Exception e) {
    		LOG.error(PolarisIdentity.get(), "sendEmail", e);
    		throw e;
    	}
    	
    	LOG.info(PolarisIdentity.get(), "sendEmail", "Email sent to: " + to);
    	
    	LOG.methodEnd(PolarisIdentity.get(), "sendEmail");
    	
    }



    /**
     * When an order is submitted, an e-mail is sent to the DSM letting them know there is a new order that needs approval.
     * @param dealerOrder
     * @throws MessagingException
     */
    public void sendProfileSubmissionEmail(ProfileDetailsDto profileDetailsDto) throws Exception  {
    	
    	
    	// Ticket: PS-178: Email to dealer confirming submission.
    	String template = EMAIL_PROPS.getProperty("email.profileSubmittedDealer.message");
    	String subject = EMAIL_PROPS.getProperty("email.profileSubmittedDealer.subject");
    	
    	VelocityContext context = new VelocityContext();
    	
    	context.put("dealerId", "123456");
    	context.put("dealerName", "I am a dealer");
    	
    	StringWriter swOut = new StringWriter();
    	
    	Velocity.evaluate( context, swOut, "what is this", template);

    	String renderedTemplate = swOut.toString();
    	
    	sendEmail(subject, renderedTemplate, "per.cedersund@polaris.com");
    	

    }
}
