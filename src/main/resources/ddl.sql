-- DROP / CLEANUP
--DROP TABLE PolarisDealersExtension.DealerForm.DealerForm;
--DROP TABLE PolarisDealersExtension.DealerForm.Section;
--DROP TABLE PolarisDealersExtension.DealerForm.Form;
--DROP SCHEMA DealerForm;

-- CREATE OBJECTS
--CREATE SCHEMA DealerForm AUTHORIZATION 'dbo';
CREATE TABLE Form
    (
        id BIGINT NOT NULL IDENTITY,
        FormName VARCHAR(255) NOT NULL,
        FormType VARCHAR(255) NOT NULL,
        RegionCode VARCHAR(255),
        ApprovedDateTime DATETIME,
        ApprovedUser VARCHAR(255),        
        Version INT NOT NULL,
        CreateUser VARCHAR(255) NOT NULL,
        CreateDateTime DATETIME NOT NULL,
        UpdateUser VARCHAR(255) NOT NULL,
        UpdateDateTime DATETIME NOT NULL,
        PRIMARY KEY(Id)
    );
CREATE TABLE Section
    (
        id BIGINT NOT NULL IDENTITY,
        FormId BIGINT NOT NULL,
        Label VARCHAR(255) NOT NULL,
        Type VARCHAR(255) NOT NULL,
        IsRequiredForWarranty BIT NOT NULL,
        HelpText VARCHAR(255),
        FormOrder INT,
        Version INT NOT NULL,
        CreateUser VARCHAR(255) NOT NULL,
        CreateDateTime DATETIME NOT NULL,
        UpdateUser VARCHAR(255) NOT NULL,
        UpdateDateTime DATETIME NOT NULL,
        PRIMARY KEY(Id),
        FOREIGN KEY(FormId) REFERENCES Form(Id)
    );


CREATE TABLE DealerForm
    (
        id BIGINT NOT NULL IDENTITY,
        FormId BIGINT NOT NULL,
        FormType VARCHAR(255) NOT NULL,
        IsCompleted BIT NOT NULL,
        Version INT NOT NULL,
        CreateUser VARCHAR(255) NOT NULL,
        CreateDateTime DATETIME NOT NULL,
        UpdateUser VARCHAR(255) NOT NULL,
        UpdateDateTime DATETIME NOT NULL,
        PRIMARY KEY(Id),
        FOREIGN KEY(FormId) REFERENCES Form(Id)
    );
    
