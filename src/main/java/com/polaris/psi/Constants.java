package com.polaris.psi;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Constants {
	
    // initialized in InitServlet
	public static final Calendar DEFAULT_DATE = new GregorianCalendar(1900, 0, 1, 0, 0, 0);
	
	//Enterprise attributes specific to this application
	public final static String ENTERPRISE_ATTRIBUTE_TRANSLATION_URL = "getTranslation";

    public static final String PROGRAM_NAME = "Inventory Profile Sell-In";
    public static final String PROGRAM_CODE = "PSIv1";
    public static String APPLICATION_GUID = "02E0E350-DA3B-4C84-BB26-9B1E8F5A9862";
    
    public static final Integer DEALER_NOT_CANCELED_CODE = 0;
    
    public static final String DEALER_REASON_ROLE_DESC = "DEALER";
    public static final String DSM_REASON_ROLE_DESC = "DSM";
    public static final String ADMIN_REASON_ROLE_DESC = "ADMIN";

    // Active profile status
    public static final String ACTIVE = "ACTIVE PROFILE SESSION";

    // Current Dealer Profile statuses
    public static final String DEFAULT_PROFILE_STATUS = "NOT STARTED";
    public static final String IN_PROGRESS_STATUS = "IN PROGRESS";
    public static final String RETURNED_TO_DEALER = "RETURNED TO DEALER";
    
    // Current DSM Dealer Profile statuses
    public static final String PENDING_STATUS = "PENDING";
    public static final String RETURNED_TO_DSM = "RETURNED TO DSM";

    // Current Admin Dealer Profile statuses
    public static final String EXCEPTION_REQUESTED = "EXCEPTION REQUESTED";

    // Approved Profile statuses
    public static final String APPROVED_AS_REQUESTED = "APPROVED AS REQUESTED";
    public static final String APPROVED_W_CHANGES = "APPROVED WITH CHANGES";
    public static final String APPROVED_COMPLIANT = "APPROVED AS COMPLIANT";
    public static final String APPROVED_NONCOMPLIANT = "APPROVED AS NON-COMPLIANT";
    
    public static final String SAVE_SUCCESSFUL = "Successful";
    public static final String NOT_AUTHORIZED = "Not Authorized";
    public static final String NO_RECORDS = "No Records";
    
    public static final String PRODUCT_LINE_REGEX = "2|5|6|9|D|F|Z";

}

