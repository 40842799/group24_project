## USE CASE UC6: Generate Major-Language Population Report

### CHARACTERISTIC INFORMATION

#### Goal in Context
  
As a _Reporting User_ I want to _view the number of people speaking Chinese, English, Hindi, Spanish, and Arabic (sorted greatest to smallest) along with global percentages_ so that _I can analyze global language dominance._

#### Epic Reference
  
Epic 6: Language Speaker Analysis

#### Scope
  
Global Population Reporting System

#### Level
  
Primary Level.

#### Primary Actor
  
Reporting User.

#### Preconditions
  
The World database is running and the application has been given its connection details. The data in the database is up to date.

#### Success End Condition
  
A report with results sorted in descending order by total speakers is displayed in the command-line terminal.

#### Failed End Condition
  
No report is produced.

#### Trigger
  
The Reporting User starts the application from the command line.

### MAIN SUCCESS SCENARIO
  
**This maps to Requirement R32: Report Major Language Speakers**

This use case needs no input values from the Reporting User. The five languages are fixed by the requirement.

1. The Reporting User starts the application from the command line.
2. System connects to the World database.
3. System calculates the number of people who speak each major language.
4. System retrieves the total world population.
5. System calculates the percentage of the world population that speaks each language.
6. System sorts the results in descending order of speakers.
7. System displays the report in a table with column headers: Language, Speakers, and World Percentage.

### ALTERNATIVE FLOWS
  
_None_

### EXCEPTION FLOWS

- **The Reporting User supplies an option the system does not recognise:**
  - System displays an error message and a short guide on how to use the application.
  - No report is produced.
- **The database cannot be reached:**
  - System displays an error message.
  - No report is produced.

### SCHEDULE
  
**DUE DATE**: Release 1.0
