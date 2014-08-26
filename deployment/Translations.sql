/*

PSI Translation

*/

use PolarisDealersCommon

DECLARE @ContentID int
DECLARE @ApplicationID int
SELECT @ApplicationID = ApplicationID FROM Enterprise.Application WHERE AppGuid='02e0e350-da3b-4c84-bb26-9b1e8f5a9862'


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Inventory Profile Summary'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Inventory Profile Summary', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END

-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Inventory Profile Summary', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'RÇsumÇ du profil des stocks', 1, 'admin', '8/15/2014 8:50:00 AM', 'admin', '8/15/2014 8:50:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Current Profiles'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Current Profiles', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Current Profiles', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Profils actuels', 1, 'admin', '8/15/2014 8:50:00 AM', 'admin', '8/15/2014 8:50:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Profiles History'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Profiles History', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Profiles History', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Historique des profils', 1, 'admin', '8/15/2014 8:50:00 AM', 'admin', '8/15/2014 8:50:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Target Completion Date'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Target Completion Date', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Target Completion Date', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Date de cible', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Inventory Profile'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Inventory Profile', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Inventory Profile', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Profil des stocks', 1, 'admin', '8/15/2014 8:50:00 AM', 'admin', '8/15/2014 8:50:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Status'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Status', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Status', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'êtat', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Dealer'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Dealer', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Dealer', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Concessionnaire', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Dealer Number'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Dealer Number', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Dealer Number', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'NumÇro du concessionnaire', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'City/State/Zip'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'City/State/Zip', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'City/State/Zip', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Ville, province, code postal', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Dealer Name'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Dealer Name', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Dealer Name', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Nom du concessionnaire', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Order Segment'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Order Segment', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Order Segment', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Segment de la commande', 1, 'admin', '8/15/2014 8:50:00 AM', 'admin', '8/15/2014 8:50:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Segment'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Segment', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Segment', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Segments', 1, 'admin', '8/15/2014 8:50:00 AM', 'admin', '8/15/2014 8:50:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Recommendation - Qty'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Recommendation - Qty', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Recommendation - Qty', 1, 'admin', '8/15/2014 8:44:00 AM', 'admin', '8/15/2014 8:44:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Recommandation', 1, 'admin', '8/15/2014 8:50:00 AM', 'admin', '8/15/2014 8:50:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Profile Name'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Profile Name', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Profile Name', 1, 'jsbenni', '8/15/2014 9:09:00 AM', 'jsbenni', '8/15/2014 9:09:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Nom du profile', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Last Updated'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Last Updated', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Last Updated', 1, 'jsbenni', '8/15/2014 9:09:00 AM', 'jsbenni', '8/15/2014 9:09:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'dernier rÇviser', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'DSM/DRM'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'DSM/DRM', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'DSM/DRM', 1, 'jsbenni', '8/15/2014 9:09:00 AM', 'jsbenni', '8/15/2014 9:09:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'DSM/DRM', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Recommendation - DSM Qty - RSM Qty'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Recommendation - DSM Qty - RSM Qty', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Recommendation - DSM Qty - RSM Qty', 1, 'jsbenni', '8/15/2014 9:09:00 AM', 'jsbenni', '8/15/2014 9:09:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Recommendation - DSM QuantitÇ - RSM QuantitÇ', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 12:00:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Dealer Comments'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Dealer Comments', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Dealer Comments', 1, 'jsbenni', '8/15/2014 9:09:00 AM', 'jsbenni', '8/15/2014 9:09:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Commentaires du concessionnaire', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'RSM/RRM Comments'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'RSM/RRM Comments', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'RSM/RRM Comments', 1, 'jsbenni', '8/15/2014 9:09:00 AM', 'jsbenni', '8/15/2014 9:09:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Commentaires du DSM/DRM', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'DSM/DRM Comments'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'DSM/DRM Comments', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'DSM/DRM Comments', 1, 'jsbenni', '8/15/2014 9:09:00 AM', 'jsbenni', '8/15/2014 9:09:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Commentaires du DSM/DRM', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Processing...'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Processing...', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Processing...', 1, 'tgcarls', '8/15/2014 9:15:00 AM', 'tgcarls', '8/15/2014 9:15:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Veuillez Patienter', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Return to Summary'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Return to Summary', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Return to Summary', 1, 'tgcarls', '8/15/2014 9:15:00 AM', 'tgcarls', '8/15/2014 9:15:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Retour au rÇsumÇ', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Submit for Exception'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Submit for Exception', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Submit for Exception', 1, 'tgcarls', '8/15/2014 9:15:00 AM', 'tgcarls', '8/15/2014 9:15:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'envoyer Ö exception', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Send Back to Dealer'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Send Back to Dealer', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Send Back to Dealer', 1, 'tgcarls', '8/15/2014 9:15:00 AM', 'tgcarls', '8/15/2014 9:15:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Retourner au concessionnaire', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Approve as Compliant'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Approve as Compliant', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Approve as Compliant', 1, 'jsbenni', '8/15/2014 9:23:00 AM', 'jsbenni', '8/15/2014 9:23:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Approuver comme conforme', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Approve as Non-Compliant'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Approve as Non-Compliant', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Approve as Non-Compliant', 1, 'jsbenni', '8/15/2014 9:23:00 AM', 'jsbenni', '8/15/2014 9:23:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Approuver comme non conforme', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Return to DRM'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Return to DRM', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Return to DRM', 1, 'jsbenni', '8/15/2014 9:23:00 AM', 'jsbenni', '8/15/2014 9:23:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Retourner au DRM', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Super Segment'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Super Segment', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Super Segment', 1, 'admin', '8/15/2014 11:37:00 AM', 'admin', '8/15/2014 11:37:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Super Segment', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Segments'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Segments', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Segments', 1, 'admin', '8/15/2014 11:37:00 AM', 'admin', '8/15/2014 11:37:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Segments', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Requested / Min'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Requested / Min', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Requested / Min', 1, 'admin', '8/15/2014 11:37:00 AM', 'admin', '8/15/2014 11:37:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'DemandÇ / Min.', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Recommendation'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Recommendation', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Recommendation', 1, 'admin', '8/15/2014 11:37:00 AM', 'admin', '8/15/2014 11:37:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Recommandation', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Profile Details'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Profile Details', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Profile Details', 1, 'admin', '8/15/2014 11:37:00 AM', 'admin', '8/15/2014 11:37:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'DÇtails du profil', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Requested Total'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Requested Total', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Requested Total', 1, 'admin', '8/15/2014 11:37:00 AM', 'admin', '8/15/2014 11:37:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Total de DemandÇ', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Min - Recommended - Max'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Min - Recommended - Max', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Min - Recommended - Max', 1, 'admin', '8/15/2014 11:37:00 AM', 'admin', '8/15/2014 11:37:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Min. - Recommendation - Max.', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Qty'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Qty', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Qty', 1, 'admin', '8/15/2014 11:37:00 AM', 'admin', '8/15/2014 11:37:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'QuantitÇ', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 8:08:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Profile Total'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Profile Total', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Profile Total', 1, 'tpsmoks', '8/18/2014 10:12:00 AM', 'tpsmoks', '8/18/2014 10:12:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Recommendation - Qty - Approve Qty'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Recommendation - Qty - Approve Qty', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Recommendation - Qty - Approve Qty', 1, 'tpsmoks', '8/18/2014 10:12:00 AM', 'tpsmoks', '8/18/2014 10:12:00 AM')
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 2, 'Recommendation - DSM QuantitÇ - RSM QuantitÇ', 1, 'admin', '8/20/2014 8:08:00 AM', 'admin', '8/20/2014 12:00:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'dealerReason'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'dealerReason', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'dealerReason', 1, 'admin', '8/19/2014 10:26:00 AM', 'admin', '8/19/2014 10:26:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Approve with Changes'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Approve with Changes', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Approve with Changes', 1, 'cmwilfa', '8/19/2014 10:50:00 AM', 'cmwilfa', '8/19/2014 10:50:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'sendBack'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'sendBack', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'sendBack', 1, 'cmwilfa', '8/19/2014 10:50:00 AM', 'cmwilfa', '8/19/2014 10:50:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Return to DSM'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Return to DSM', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Return to DSM', 1, 'jsbenni', '8/19/2014 1:37:00 PM', 'jsbenni', '8/19/2014 1:37:00 PM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Approve as Requested'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Approve as Requested', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Approve as Requested', 1, 'cmwilfa', '8/21/2014 9:25:00 AM', 'cmwilfa', '8/21/2014 9:25:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Submitted'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Submitted', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Submitted', 1, 'admin', '8/22/2014 10:33:00 AM', 'admin', '8/22/2014 10:33:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'has been returned'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'has been returned', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'has been returned', 1, 'tpsmoks', '8/22/2014 10:38:00 AM', 'tpsmoks', '8/22/2014 10:38:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Submitted for Exception'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Submitted for Exception', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Submitted for Exception', 1, 'tgcarls', '8/22/2014 10:41:00 AM', 'tgcarls', '8/22/2014 10:41:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'has been returned.'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'has been returned.', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'has been returned.', 1, 'bmmclen', '8/22/2014 10:45:00 AM', 'bmmclen', '8/22/2014 10:45:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Approved As Requested'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Approved As Requested', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Approved As Requested', 1, 'abtreas', '8/22/2014 2:29:00 PM', 'abtreas', '8/22/2014 2:29:00 PM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'has been approved as non-compliant.'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'has been approved as non-compliant.', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'has been approved as non-compliant.', 1, 'jamorri', '8/25/2014 7:44:00 AM', 'jamorri', '8/25/2014 7:44:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'has been approved'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'has been approved', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'has been approved', 1, 'mwcarr', '8/25/2014 7:50:00 AM', 'mwcarr', '8/25/2014 7:50:00 AM')


SET @ContentID=NULL

SELECT @ContentId=ContentID FROM Translate.Content WHERE ApplicationID=@ApplicationID AND Content = 'Approved With Changes'
IF @ContentId IS NULL
BEGIN
INSERT INTO Translate.Content
(ApplicationId, Content, DefaultLcid, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ApplicationID, 'Approved With Changes', 409, 1, 'SYSTEM', GETDATE(), 'SYSTEM', GETDATE())
SET @ContentId = @@IDENTITY
END
-- delete the existing translations
DELETE FROM Translate.[ContentLanguage] WHERE ContentId=@ContentId
INSERT INTO [Translate].[ContentLanguage]
(ContentId, LanguageId, Value, IsActive, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate)
VALUES (@ContentId, 1, 'Approved With Changes', 1, 'akkiefe', '8/25/2014 7:57:00 AM', 'akkiefe', '8/25/2014 7:57:00 AM')
