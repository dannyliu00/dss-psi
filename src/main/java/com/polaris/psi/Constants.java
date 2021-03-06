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
    public static String GLOBAL_APPLICATION_GUID = "16C36E64-F410-47D9-BE4E-65FA2CF29FC8";    
    
    public static final Integer DEALER_NOT_CANCELED_CODE = 0;
    
    public static final String DEFAULT_PRODUCT_LINE = "2";
    
    public static final String DEALER_REASON_ROLE_DESC = "DEALER";
    public static final String DSM_REASON_ROLE_DESC = "DSM";
    public static final String ADMIN_REASON_ROLE_DESC = "ADMIN";

    // Active profile status
    public static final String ACTIVE_PROFILE_STATUS = "ACTIVE PROFILE SESSION";
    public static final String HISTORICAL_PROFILE_STATUS = "OFF-LINE";

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
    public static final String APPROVED = "APPROVED";
    
    public static final String SAVE_SUCCESSFUL = "Successful";
    public static final String NOT_AUTHORIZED = "Not Authorized";
    public static final String NO_RECORDS = "No Records";
    public static final String COULD_NOT_UPDATE_RSM_VALUES = "Could not update the values submitted by the RSM";
    public static final String COULD_NOT_UPDATE_DSM_VALUES = "Could not update the values submitted by the DSM";
    public static final String COULD_NOT_UPDATE_DLR_VALUES = "Could not update the values submitted";
    public static final String PROFILE_STATUS_CHANGED = "The profile status has changed.  Please try again.";
    
    // Application Attributes
    public static final String ATTR_ASKPOLARISURL = "AskPolaris";
    public static final String ATTR_ECHATURL = "eChatUrl";
    public static final String ATTR_DSMHOME = "DsmHome";
    public static final String ATTR_DEALERHOME = "DealerHome";
    public static final String ATTR_INTRANETHOME = "IntranetHome";
    public static final String ATTR_DEALERCASES = "DealerCases";
    public static final String ATTR_CHANGEDEALER = "ChangeDealer";
    public static final String ATTR_ADMINHOMEURL = "adminHomeUrl";
    public static final String ATTR_DEXROOT = "DexRoot";
    public static final String ATTR_ADMINHOME = "AdminHome";
    public static final String ATTR_EMAILSERVICEURL = "PolarisEmailServiceUrl";
    public static final String ATTR_EMAILBOUNCEADDR = "EmailBounceAddress";
    public static final String ATTR_EMAILFROMNAME = "EmailFromName";
    
    // Session Constant
    public final static String LANGUAGE_PREFERENCE = "DealerLanguage";
    
    // Cache Constants
    public final static String PRODUCTLINE_CACHE = "app-ProductLines";

}

