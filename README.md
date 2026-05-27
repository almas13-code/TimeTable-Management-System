# TimeTable Management System

> A Java-based desktop application developed for efficient timetable management with secure authentication, CSV handling, timetable editing, and PDF export functionality.

---

# Abstract

The TimeTable Management System is a Java-based desktop application created to simplify and automate timetable organization for educational institutions. The project provides a user-friendly graphical interface where users can securely manage timetable schedules, edit entries, import/export CSV files, and generate PDF reports.

The system is designed using Java Swing for the graphical user interface and MySQL for authentication management. The project focuses on improving efficiency, reducing manual errors, and providing an organized way to manage timetable-related data.

---

# Objectives

- To automate timetable management processes
- To provide secure user authentication
- To reduce manual scheduling errors
- To support CSV file handling for easy data management
- To generate printable PDF timetable reports
- To provide a simple and user-friendly GUI interface

---

# System Features

## Secure Authentication

- User login authentication system
- MySQL-based credential verification
- Separate authentication handling classes
- Secure login interface using Java Swing

---

## Timetable Management

- Create timetable entries
- Edit timetable schedules
- Organize timetable data efficiently
- Manage multiple timetable records

---

## CSV File Handling

- Import timetable information from CSV files
- Export timetable data into CSV format
- Easy file-based data management

---

## PDF Export Functionality

- Generate timetable reports in PDF format
- Printable timetable output
- Organized timetable representation

---

## Graphical User Interface

- Built using Java Swing
- User-friendly interface
- Separate panels for different operations
- Easy navigation and timetable editing

---

# Technical Implementation

## Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Core Programming Language |
| Java Swing | GUI Development |
| MySQL | Database Authentication |
| iTextPDF | PDF Generation |
| CSV Handling | File Management |

---

# OOP Concepts Utilized

## Encapsulation

Data members are protected and accessed through methods to ensure data security and controlled access.

## Inheritance

GUI classes inherit properties from Java Swing components like JFrame.

## Polymorphism

Method overriding is used across multiple classes for flexible implementation.

## Abstraction

Authentication handling, CSV operations, and timetable logic are separated into different classes to hide implementation complexity.

---

# Project Structure

```bash
TimeTable-Management-System
│
├── src/
│   ├── AdminGUI.java
│   ├── AuthenticationGUI.java
│   ├── Authenticator.java
│   ├── CSVFile.java
│   ├── Main.java
│   ├── MySQLAuthenticator.java
│   ├── TimeTable.java
│   └── TimeTableGUI.java
│
├── database/
│
├── lib/
│   ├── itextpdf-5.5.13.3.jar
│   └── mysql-connector-j-9.7.0.jar
│
└── .vscode/
```

---

# How to Run the Project

## Step 1: Clone the Repository

```bash
git clone https://github.com/almas13-code/TimeTable-Management-System.git
```

---

## Step 2: Open the Project

Open the project using:
- VS Code
- IntelliJ IDEA
- Eclipse

---

## Step 3: Configure MySQL

- Install MySQL Server
- Create the required database
- Configure database credentials in the project

---

## Step 4: Add Required Libraries

Add the following JAR files:

- mysql-connector-j
- itextpdf

---

## Step 5: Run the Application

Run the `Main.java` file to start the application.

---

# Advantages of the System

- Reduces manual timetable management effort
- Easy timetable editing and organization
- Secure authentication system
- Simple graphical user interface
- PDF report generation support
- CSV import/export functionality

---

# Future Enhancements

- Cloud database integration
- Multi-user timetable synchronization
- Advanced timetable conflict detection
- Improved UI/UX design
- Online timetable access

---

#  Academic Information

**Course:** Object Oriented Programming  
**Institution:** BITS Pilani Dubai Campus

---

# License

This project is developed for academic and educational purposes.

---
