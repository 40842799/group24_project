## USE CASE 2 (UC2) : Generate City Reports

### CHARACTERISTIC INFORMATION

#### Goal in Context
  
As a _Reporting User_ I want to _view a report of all cities meeting a specified criteria, sorted by population (largest to smallest)_ so that _I can assess global demographic distribution._

#### Epic Reference
  
Epic 2: City Reports

#### Scope
  
Global Population Reporting System

#### Level
  
Primary Level.

#### Primary Actor
  
Reporting User

#### Preconditions
  
The World database is running and the application has been given its connection details. The data in the database is up to date.

#### Success End Condition
  
The city reports are displayed in the command-line terminal, sorted in descending order of population.

#### Failed End Condition
  
No report is produced.

#### Trigger
  
The Reporting User starts the application from the command line.

### MAIN SUCCESS SCENARIO

**This maps to Requirements R07 to R11: Cities in the World, a Continent, a Region, a Country and a District**
1. The Reporting User starts the application from the command line. The user may optionally supply their own values for the report inputs (see Alternative Flows). Any input not supplied uses its default value.
2. System checks the supplied values.
3. System connects to the World database.
4. System retrieves the city records for the current report and sorts them in descending order of population.
5. System displays the report in a table with column headers: Name, Country, District, and Population.
6. System moves on to the next city report and repeats steps 4 and 5 until all city reports have been displayed. When run in an interactive terminal, the system waits for the Reporting User to press Enter between reports.

### ALTERNATIVE FLOWS

- **The Reporting User supplies a continent name** (default: Europe)
  - System uses this continent for the "cities in a continent" reports (R08 and R13).
  - This flow resumes at Step 2 of the Main Flow.
- **The Reporting User supplies a region name** (default: Western Europe)
  - System uses this region for the "cities in a region" reports (R09 and R14).
  - This flow resumes at Step 2 of the Main Flow.
- **The Reporting User supplies a country name** (default: United Kingdom)
  - System uses this country for the "cities in a country" reports (R10 and R15).
  - This flow resumes at Step 2 of the Main Flow.
- **The Reporting User supplies a district name** (default: England)
  - System uses this district for the "cities in a district" reports (R11 and R16).
  - This flow resumes at Step 2 of the Main Flow.
- **The Reporting User supplies a row limit (N)** (default: 5)
  - **This maps to Requirements R12 to R16: Top N Cities in the World, a Continent, a Region, a Country and a District**
  - N is a positive whole number chosen by the user. It is not hard-coded.
  - System sorts the matching records in descending order of population and displays only the first _N_ cities.
  - This flow resumes at Step 5 of the Main Flow.

### EXCEPTION FLOWS

- **Invalid input. The Reporting User supplies N as zero, a negative number or a non-whole number, or supplies an option the system does not recognise:**
  - System displays an error message and a short guide on how to use the application.
  - No report is produced. The Reporting User can start the application again with corrected values.
- **The database cannot be reached:**
  - System displays an error message.
  - No report is produced.
- **No cities match the continent, region, country or district supplied (for example, a misspelt name):**
  - System displays the report with its column headers and no rows.

### SCHEDULE
  
**DUE DATE**: Release 1.0
