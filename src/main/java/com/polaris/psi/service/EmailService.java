package com.polaris.psi.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

import javax.mail.MessagingException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.Constants;
import com.polaris.psi.resource.dto.DealerDto;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.util.AttributeHelper;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
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
	
	@Autowired
	DealerService dealerService;
	
	@Autowired
	DsmService dsmService;

	@Autowired
	ProfileService profileService;

	private static final SplunkLogger LOG = new SplunkLogger(EmailService.class);
	
    private static Properties EMAIL_PROPS = new Properties();

    static {
        try {
            // Initialize Velocity
            Properties p = new Properties();
            p.setProperty("resource.loader", "class");
            p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            
            
            Velocity.init(p);
        }
        catch(Exception e) {
            LOG.error(PolarisIdentity.get(),"static initializer", e);
        }
    }
    
    private void sendEmail(String subject, String messageBody, String to) {
    	LOG.methodStart(PolarisIdentity.get(), "sendEmail");

    	try {
    		
    		if(to==null || to.trim().isEmpty()) {
    			LOG.warn(PolarisIdentity.get(), "sendEmail", "'to' address is blank or null. Email could not be sent");
    			return;
			}
   		
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
    	}
    	
    	LOG.info(PolarisIdentity.get(), "sendEmail", "Email sent to: " + to);
    	
    	LOG.methodEnd(PolarisIdentity.get(), "sendEmail");
    	
    }



    /**
     * When an order is submitted, an e-mail is sent to the DSM letting them know there is a new order that needs approval.
     * @param dealerOrder
     * @throws MessagingException
     */
    public void sendProfileSubmissionEmail(ProfileDetailsDto profileDetailsDto)  {
    	LOG.methodStart(PolarisIdentity.get(), "sendProfileSubmissionEmail");
    	
    	// Ticket: PS-178: Email to dealer confirming submission.
    	String subject = "Sell-In Submitted";
    	
    	Template template = Velocity.getTemplate("/templates/email_sellIn_submitted.vm");
    	
    	DealerDto dealerInfo = getDealerInfo(profileDetailsDto);

    	VelocityContext context = new VelocityContext();
    	context.put("dealerId", dealerInfo.getDealerId());
    	context.put("dealerName", dealerInfo.getName());
    	
    	StringWriter writer = new StringWriter();
    	template.merge(context, writer);
    	String renderedTemplate = writer.toString();
    	
    	// Send email to Dealer
    	String toAddress = getDealerEmail(profileDetailsDto);
    	sendEmail(subject, renderedTemplate, toAddress);
    	
    	// Send email to DSM
    	toAddress = getDSMEmail(profileDetailsDto);
    	sendEmail(subject, renderedTemplate, toAddress);
    	
    	LOG.methodEnd(PolarisIdentity.get(), "sendProfileSubmissionEmail");
    	
    }
    

    /*
     * Sends an email to the dealer indicating that approved as requested.
     * Story: PS-180: Approve as requested email notification to dealer.
     * @author pceder
     */
	public void sendApproveAsRequestedEmail(ProfileDetailsDto profileDetailsDto) {
		
		LOG.methodStart(PolarisIdentity.get(), "sendApproveAsRequestedEmail");
		
    	String subject = "Sell-In Approved As Requested";
    	
    	Template template = Velocity.getTemplate("/templates/email_sellIn_approved_as_requested.vm");
    	DealerDto dealerInfo = getDealerInfo(profileDetailsDto);
    	
    	VelocityContext context = new VelocityContext();
    	context.put("dealerId", dealerInfo.getDealerId());
    	context.put("dealerName", dealerInfo.getName());

    	
    	StringWriter writer = new StringWriter();
    	template.merge(context, writer);
    	String renderedTemplate = writer.toString();
    	
    	// Send email to Dealer
    	String toAddress = getDealerEmail(profileDetailsDto);
    	sendEmail(subject, renderedTemplate, toAddress);
    	
    	LOG.methodEnd(PolarisIdentity.get(), "sendApproveAsRequestedEmail");
	}     
    /*
     * Sends an email to the dealer indicating that approved as requested.
     * Story: PS-180: Approve as requested email notification to dealer.
     * @author pceder
     */
	public void sendApproveWithChangesEmail(ProfileDetailsDto profileDetailsDto) {
		
		LOG.methodStart(PolarisIdentity.get(), "sendApproveWithChangesEmail");
		
    	String subject = "Sell-In Approved With Changes";
    	
    	Template template = Velocity.getTemplate("/templates/email_sellIn_approved_w_changes.vm");
    	DealerDto dealerInfo = getDealerInfo(profileDetailsDto);
    	
    	VelocityContext context = new VelocityContext();
    	context.put("dealerId", dealerInfo.getDealerId());
    	context.put("dealerName", dealerInfo.getName());

    	
    	StringWriter writer = new StringWriter();
    	template.merge(context, writer);
    	String renderedTemplate = writer.toString();
    	
    	// Send email to Dealer
    	String toAddress = getDealerEmail(profileDetailsDto);
    	sendEmail(subject, renderedTemplate, toAddress);
    	
    	LOG.methodEnd(PolarisIdentity.get(), "sendApproveWithChangesEmail");
	}  
	
    /*
     * Sends an email to the RSM indicating that submitted with exception.
     * @author pceder
     */
	public void sendSubmitForExceptionEmail(ProfileDetailsDto profileDetailsDto) {
		
		LOG.methodStart(PolarisIdentity.get(), "sendSubmitForExceptionEmail");
		
    	String subject = "Sell-In Submitted for Exception";
    	
    	Template template = Velocity.getTemplate("/templates/email_sellIn_submit_for_exception.vm");
    	DealerDto dealerInfo = getDealerInfo(profileDetailsDto);

    	VelocityContext context = new VelocityContext();
    	context.put("dealerId", dealerInfo.getDealerId());
    	context.put("dealerName", dealerInfo.getName());
    	
    	
    	StringWriter writer = new StringWriter();
    	template.merge(context, writer);
    	String renderedTemplate = writer.toString();
    	
    	// Send email to Dealer
    	String toAddress = getDealerEmail(profileDetailsDto);
    	sendEmail(subject, renderedTemplate, toAddress);
    	
    	LOG.methodEnd(PolarisIdentity.get(), "sendSubmitForExceptionEmail");
	} 	
	
    /*
     * Sends an email to the RSM indicating that submitted with exception.
     * @author pceder
     */
	public void sendReturnToDealerEmail(ProfileDetailsDto profileDetailsDto) {
		
		LOG.methodStart(PolarisIdentity.get(), "sendReturnToDealerEmail");
		
    	String subject = "Sell-In Profile has been returned.";
    	
    	Template template = Velocity.getTemplate("/templates/email_sellIn_return_to_dealer.vm");
    	DealerDto dealerInfo = getDealerInfo(profileDetailsDto);

    	VelocityContext context = new VelocityContext();
    	context.put("dealerId", dealerInfo.getDealerId());
    	context.put("dealerName", dealerInfo.getName());
    	
    	
    	StringWriter writer = new StringWriter();
    	template.merge(context, writer);
    	String renderedTemplate = writer.toString();
    	
    	// Send email to Dealer
    	String toAddress = getDealerEmail(profileDetailsDto);
    	sendEmail(subject, renderedTemplate, toAddress);
    	
    	LOG.methodEnd(PolarisIdentity.get(), "sendReturnToDealerEmail");
	} 	
    
    private DealerDto getDealerInfo(ProfileDetailsDto profileDetailsDto) {
    	if(profileDetailsDto==null) {throw new IllegalArgumentException("profileDetailsDto cannot be null");}
    	if(profileDetailsDto.getOrderSegments().size()==0) { throw new IllegalArgumentException("profileDetailsDto.getOrderSegments is empty"); }

    	OrderSegmentDto segment = profileDetailsDto.getOrderSegments().get(0);
    	
    	ProfileDto profile = profileService.getDealerProfile(segment.getProfileId(), segment.getDealerId());

    	return dealerService.getDealer(segment.getDealerId(), profile.getTypeCode());

    }
    
    private String getDealerEmail(ProfileDetailsDto profileDetailsDto) {
    	if(profileDetailsDto==null) {throw new IllegalArgumentException("profileDetailsDto cannot be null");}
    	if(profileDetailsDto.getOrderSegments().size()==0) { throw new IllegalArgumentException("profileDetailsDto.getOrderSegments is empty"); }
    	
    	OrderSegmentDto segment = profileDetailsDto.getOrderSegments().get(0);
    	
    	DealerDto dealerInfo = getDealerInfo(profileDetailsDto);

    	String toAddress = segment.getDealerEmail();
    	if(toAddress==null || toAddress.trim().isEmpty()) {
    		toAddress = dealerInfo.getEmailAddress();
    	}
    	
    	return toAddress;

    }   
    
    private String getDSMEmail(ProfileDetailsDto profileDetailsDto) {
    	if(profileDetailsDto==null) {throw new IllegalArgumentException("profileDetailsDto cannot be null");}
    	if(profileDetailsDto.getOrderSegments().size()==0) { throw new IllegalArgumentException("profileDetailsDto.getOrderSegments is empty"); }
    	
    	DealerDto dealerInfo = getDealerInfo(profileDetailsDto);
    	
    	return dealerInfo.getDsmEmailAddress();

    }
}
