Project Statement

CampusLock - Campus Locker Management System

Student Details

Name: Vydyam Srinivasa Vedavyas

Registration No.: 25BAI10846

Course Title: Programming in Java

Date: 18 September 2026

Problem Statement

In a college campus, students may need a secure and convenient way to temporarily store packages or personal items. Manual locker allocation can lead to confusion about locker availability, incorrect assignment, and difficulty controlling access during item collection.

The objective of this project is to develop a Java-based Campus Locker Management System that can maintain locker availability, allocate an appropriate locker based on size, generate a pickup PIN, and verify the PIN before releasing the stored package.

Proposed Solution

CampusLock provides a menu-driven console application that performs four major tasks:

Display the current status of all lockers.

Allocate an available locker of the requested size to a student or user.

Generate and display a four-digit access PIN for the allocated locker.

Verify the locker ID and PIN during pickup and release the locker after successful authentication.

The application also validates user input and reports errors without terminating the program unnecessarily.

Objectives

To implement a practical Java application using object-oriented programming.

To demonstrate classes, objects, constructors, encapsulation, and methods.

To use Java collections for locker management.

To implement custom exception handling.

To demonstrate secure-style random PIN generation using SecureRandom.

To maintain basic operational and security logs.

To provide a simple, user-friendly command-line interface.

Functional Requirements

FR1 - Locker Status

The system shall display every locker with its ID, size, and current availability.

FR2 - Locker Allocation

The system shall accept a valid user ID and locker size and allocate the first available locker of that size.

FR3 - PIN Generation

The system shall generate a random four-digit PIN for every successful allocation.

FR4 - Package Retrieval

The system shall accept a locker ID and PIN and release the locker only if the PIN matches the stored PIN.

FR5 - Validation

The system shall reject invalid locker sizes, blank user IDs, non-numeric locker IDs, and incorrectly formatted PINs.

FR6 - Logging

The system shall record allocation events, successful retrievals, and incorrect PIN attempts in timestamped console logs.

Non-Functional Requirements

The application should be easy to operate from a command-line terminal.

Locker states should remain consistent during service operations.

Error messages should be clear and understandable.

The source code should be organized into packages according to responsibility.

Expected Result

After implementation, a user should be able to view available lockers, allocate a suitable locker, receive an access PIN, and later retrieve the package using the correct PIN. Incorrect input or authentication should produce an informative error message.

Scope

The project is intended as a Java programming project and demonstration of fundamental application design. It currently stores all data in memory and runs as a console application. Database storage, graphical UI, and network deployment are outside the current scope but can be added later.
