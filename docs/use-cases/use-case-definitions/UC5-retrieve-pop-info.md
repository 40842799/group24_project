## USE CASE UC5: Retrieve Population Information

### CHARACTERISTIC INFORMATION

#### Goal in Context
  
As a _Reporting User_ I want to _query the total exact population for the World, a Continent, a Region, a Country, a District, or a City_ so that _I can obtain rapid statistical totals._

#### Epic Reference
  
Epic 5: General Population Access

#### Scope
  
Global Population Reporting System

#### Level
  
Primary Level.

#### Primary Actor
  
Reporting User

#### Preconditions
  
The World database is running and the application has been given its connection details. The data in the database is up to date.

#### Success End Condition
  
The total population of each requested location is displayed in the command-line terminal.

#### Failed End Condition
  
No population value is displayed.

#### Trigger
  
The Reporting User starts the application from the command line.

### MAIN SUCCESS SCENARIO

1. The Reporting User starts the application from the command line. The user may optionally supply the name of the continent, region, country, district or city they are interested in (see Alternative Flows). Any name not supplied uses its default value.
2. System checks the supplied values.
3. System connects to the World database.
4. System retrieves the total population for each location type in turn, and displays each result as a table showing the location and its total population. When run in an interactive terminal, the system waits for the Reporting User to press Enter between reports.
   - **World: this maps to Requirement R26: Retrieve World Population**
   - **Continent: this maps to Requirement R27: Retrieve Continent Population**
   - **Region: this maps to Requirement R28: Retrieve Region Population**
   - **Country: this maps to Requirement R29: Retrieve Country Population**
   - **District: this maps to Requirement R30: Retrieve District Population**
   - **City: this maps to Requirement R31: Retrieve City Population**

### ALTERNATIVE FLOWS

- **The Reporting User supplies a continent name** (default: Europe)
  - System displays the total population of that continent (R27).
  - This flow resumes at Step 2 of the Main Flow.
- **The Reporting User supplies a region name** (default: Western Europe)
  - System displays the total population of that region (R28).
  - This flow resumes at Step 2 of the Main Flow.
- **The Reporting User supplies a country name** (default: United Kingdom)
  - System displays the total population of that country (R29).
  - This flow resumes at Step 2 of the Main Flow.
- **The Reporting User supplies a district name** (default: England)
  - System displays the total population of that district (R30).
  - This flow resumes at Step 2 of the Main Flow.
- **The Reporting User supplies a city name** (default: Edinburgh)
  - System displays the population of that city (R31). If several cities share the same name, each is shown with its country and district.
  - This flow resumes at Step 2 of the Main Flow.

The world population (R26) needs no input.

### EXCEPTION FLOWS

- **The Reporting User supplies an option the system does not recognise:**
  - System displays an error message and a short guide on how to use the application.
  - No report is produced. The Reporting User can start the application again with corrected values.
- **The database cannot be reached:**
  - System displays an error message.
  - No report is produced.
- **No data matches the name supplied (for example, a misspelt name):**
  - System displays the report with its column headers and no rows.

### SCHEDULE
  
**DUE DATE**: Release 1.0
