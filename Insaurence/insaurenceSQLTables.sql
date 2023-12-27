create database insaurence;
use insaurence ;

-- User Table
CREATE TABLE User (
    userId INT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);

-- Client Table
CREATE TABLE Client (
    clientId INT PRIMARY KEY,
    clientName VARCHAR(255),
    contactInfo VARCHAR(255),
    policyId INT REFERENCES Policy(policyId) -- Assuming Policy is another table
);

-- Claim Table
CREATE TABLE Claim (
    claimId INT PRIMARY KEY,
    claimNumber VARCHAR(255),
    dateFiled VARCHAR(255),
    claimAmount DECIMAL(10, 2),
    status VARCHAR(255),
    policyId INT REFERENCES Policy(policyId), -- Assuming Policy is another table
    clientId INT REFERENCES Client(clientId) -- Assuming Client is another table
);



-- Payment Table
CREATE TABLE Payment (
    paymentId INT PRIMARY KEY,
    paymentDate VARCHAR(255),
    paymentAmount DECIMAL(10, 2),
    clientId INT REFERENCES Client(clientId) -- Assuming Client is another table
);


-- Policy Table
CREATE TABLE Policy (
    policyId INT PRIMARY KEY,
    policyName VARCHAR(255),
    policyType VARCHAR(255),
    coverageAmount DECIMAL(10, 2)
);

INSERT INTO Policy (policyId, policyName, policyType, coverageAmount)
VALUES(3, 'Motor Insurance', 'Comprehensive', 300000.00),
    (4, 'Travel Insurance', 'Overseas Mediclaim', 200000.00),
    (5, 'Home Insurance', 'Comprehensive', 800000.00);


