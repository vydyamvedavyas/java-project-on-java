CampusLock - Campus Locker Management System

Project Information

Student Name: Vydyam Srinivasa Vedavyas

Registration Number: 25BAI10846

Course: Programming in Java

Project: CampusLock - Campus Locker Management System

Date: 18 September 2026

1. Overview

CampusLock is a console-based Java application that simulates a secure campus locker management system. The system maintains lockers of different sizes, allows a user to reserve an available locker for a package, generates a four-digit access PIN, displays locker occupancy, and verifies the PIN when the package is collected.

The project demonstrates core Java and object-oriented programming concepts such as classes and objects, encapsulation, constructors, collections, exception handling, input validation, synchronized service methods, and utility classes.

2. Main Features

View locker status - Displays all lockers, their sizes, and whether they are available or occupied.

Reserve / drop package - Accepts a user ID and requested locker size, selects the first available matching locker, and generates a four-digit PIN.

Retrieve / pickup package - Accepts a locker ID and PIN and releases the locker only when the PIN is valid and correct.

Input validation - Handles empty user IDs, invalid locker sizes, invalid locker IDs, and incorrectly formatted PINs.

Security logging - Logs allocation events, successful retrievals, and incorrect PIN attempts.

Deterministic locker display order - Uses TreeMap so locker IDs are displayed in ascending order.

3. Technology Used

Java

Java Collections Framework

TreeMap

SecureRandom

Scanner

Java time API (LocalDateTime, DateTimeFormatter)

Exception handling

OOP principles

4. Project Structure

JAVA PROJECT/
└── src/
    └── com/
        └── vityarthi/
            └── campuslock/
                ├── Main.java
                ├── exception/
                │   └── LockerException.java
                ├── model/
                │   ├── Locker.java
                │   └── User.java
                ├── service/
                │   └── LockerService.java
                └── util/
                    └── LoggerUtil.java

The directory structure matches the package declarations. For example:

package com.vityarthi.campuslock.model;

must be stored under:

src/com/vityarthi/campuslock/model/

5. How the System Works

Startup

Main.java creates a LockerService with six lockers and opens a menu-driven console interface.

Locker creation

The service creates six lockers with these automatically assigned sizes:

Locker

Size

1

SMALL

2

MEDIUM

3

LARGE

4

MEDIUM

5

SMALL

6

LARGE

Reservation

The user enters:

Student/User ID

Locker size

The service finds the lowest-numbered available locker of the requested size and generates a random four-digit PIN.

Retrieval

The user enters:

Locker ID

Four-digit PIN

The service checks the locker, validates the PIN format, compares the PIN, and releases the locker after successful authentication.

6. How to Run in VS Code

Prerequisites

Install:

JDK 17 or a newer Java version that supports the switch syntax used by the project

Visual Studio Code

Extension Pack for Java (recommended)

Run using the VS Code interface

Open the extracted JAVA PROJECT folder in VS Code.

Open:
src/com/vityarthi/campuslock/Main.java

Click the Run button in the top-right of the editor.

Select Run Without Debugging if prompted.

Use the terminal menu to interact with the application.

Run from the terminal

From the project root:

javac -d out $(find src -name "*.java")
java -cp out com.vityarthi.campuslock.Main

On Windows PowerShell, an equivalent approach is:

Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName } | Set-Content sources.txt
javac -d out @sources.txt
java -cp out com.vityarthi.campuslock.Main

7. Expected Output

The application starts with:

===============================================
       WELCOME TO CAMPUSLOCK LOGISTICS
===============================================

1. View Lockers Status
2. Reserve / Drop Package
3. Retrieve / Pickup Package
4. Exit
Available: 6 | Occupied: 0
Choose an option:

Example reservation

Choose an option: 2
Enter Student/User ID: STU101
Enter Locker Size (SMALL/MEDIUM/LARGE): SMALL

[LOG 2026-09-18 17:03:04] Locker #1 allocated to user STU101
SUCCESS: Allocated Locker #1 | Access PIN: 4240

The PIN is generated randomly, so the actual PIN in a new run will normally be different.

Example retrieval

Choose an option: 3
Enter Locker ID: 1
Enter 4-digit Pickup PIN: 4240

[LOG 2026-09-18 17:03:10] Package retrieved successfully from Locker #1
SUCCESS: Locker unlocked. Item retrieved!

8. Error Handling Examples

Invalid locker size

ERROR: Invalid locker size. Use SMALL, MEDIUM, or LARGE.

Invalid locker ID

ERROR: Locker ID must be a number.

Wrong PIN

[LOG 2026-09-18 17:03:15] SECURITY ALERT: Invalid PIN attempt on Locker #1
ERROR: Access Denied: Incorrect PIN.

9. Java Concepts Demonstrated

Encapsulation

Locker keeps its fields private and exposes controlled getter and state-changing methods.

Abstraction

LockerService hides the details of locker selection, PIN generation, validation, and package retrieval from the user interface.

Collections

TreeMap<Integer, Locker> stores lockers and keeps them ordered by locker ID.

Exception Handling

LockerException provides application-specific error messages for invalid operations.

Synchronization

The main service operations are declared synchronized to protect locker state if the service is later accessed by multiple threads.

Utility Class

LoggerUtil centralizes timestamped logging.

10. Limitations and Future Scope

The current version is an in-memory console application. Data is lost when the program exits, and there is no database, graphical interface, authentication system for administrators, or multi-user network deployment.

Possible future improvements include:

Database integration using JDBC or JPA

JavaFX or web-based user interface

Admin login and locker management

Package metadata and delivery tracking

Persistent transaction history

Automated tests using JUnit

REST API integration

Better reporting and audit logs

11. Author

Vydyam Srinivasa Vedavyas
Reg. No.: 25BAI10846
