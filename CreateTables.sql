CREATE TABLE [User] (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role VARCHAR(20) CHECK (Role IN ('Admin', 'Librarian'))
);

CREATE TABLE Member (
    MemberID INT IDENTITY(1,1) PRIMARY KEY,
    Name VARCHAR(100),
    Email VARCHAR(100),
    Phone VARCHAR(20),
    Address TEXT
);

CREATE TABLE Book (
    BookID INT IDENTITY(1,1) PRIMARY KEY,
    Title VARCHAR(100),
    Author VARCHAR(100),
    Publisher VARCHAR(100),
    ISBN VARCHAR(13),
    Quantity INT
);

CREATE TABLE Loan (
    LoanID INT IDENTITY(1,1) PRIMARY KEY,
    BookID INT,
    MemberID INT,
    IssueDate DATE,
    ReturnDate DATE,
    Status VARCHAR(20) CHECK (Status IN ('Issued', 'Returned')),
    FOREIGN KEY (BookID) REFERENCES Book(BookID),
    FOREIGN KEY (MemberID) REFERENCES Member(MemberID)
);