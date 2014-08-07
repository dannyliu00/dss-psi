-- PSI STAGE Attributes
use [PolarisDealersCommon]
 
DECLARE @APPGUID uniqueidentifier 
DECLARE @GLOBAL_APPGUID uniqueidentifier 
SET @APPGUID='02E0E350-DA3B-4C84-BB26-9B1E8F5A9862'
SET @GLOBAL_APPGUID='16C36E64-F410-47D9-BE4E-65FA2CF29FC8'

DECLARE @ApplicationID int
DECLARE @GlobalApplicationID int
SELECT @ApplicationID=ApplicationID FROM [Enterprise].[Application]
WHERE AppGuid=@APPGUID

SELECT @GlobalApplicationID=ApplicationID FROM [Enterprise].[Application]
WHERE AppGuid=@GLOBAL_APPGUID


DELETE FROM [PolarisDealersCommon].[Enterprise].[Attribute] WHERE ApplicationID=@GlobalApplicationID and Attribute='PolarisEmailServiceUrl'
INSERT INTO [PolarisDealersCommon].[Enterprise].[Attribute]
(ApplicationId, ApplicationModule, Description, Attribute, AttributeValue)
Values 
(@GlobalApplicationID,'Java Core Service','Polaris Email Service','PolarisEmailServiceUrl','https://svc.polarisstage.com:9443/EmailService/1.0/DefaultEmailService/DefaultEmailService.wsdl')

DELETE FROM [PolarisDealersCommon].[Enterprise].[Attribute] WHERE ApplicationID=@GlobalApplicationID and Attribute='EmailBounceAddress'
INSERT INTO [PolarisDealersCommon].[Enterprise].[Attribute]
(ApplicationId, ApplicationModule, Description, Attribute, AttributeValue)
Values 
(@GlobalApplicationID,'Java Core Service','Polaris Email Service FROM address','EmailBounceAddress','MSDS.DoNotReply@polaris.com')

DELETE FROM [PolarisDealersCommon].[Enterprise].[Attribute] WHERE ApplicationID=@ApplicationID and Attribute='EmailFromName'
INSERT INTO [PolarisDealersCommon].[Enterprise].[Attribute]
(ApplicationId, ApplicationModule, Description, Attribute, AttributeValue)
Values 
(@ApplicationID,'PSI','Email FROM name','EmailFromName','salesoperations@polaris.com')

DELETE FROM [PolarisDealersCommon].[Enterprise].[Attribute] WHERE ApplicationID=@ApplicationID and Attribute='sendToStockingProfile'
INSERT INTO [PolarisDealersCommon].[Enterprise].[Attribute]
(ApplicationId, ApplicationModule, Description, Attribute, AttributeValue)
Values 
(@ApplicationID,'PSI','Send PSI profile to stocking profile flag','sendToStockingProfile','false')

