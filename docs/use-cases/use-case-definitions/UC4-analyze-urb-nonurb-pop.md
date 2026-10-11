## USE CASE UC4: Analyse Urban and Non-Urban Population

### CHARACTERISTIC INFORMATION

#### Goal in Context
  
As a _Reporting User_ I want to _view total population alongside urban and non-urban breakdown (with percentages) for every Continent, Region, and Country_ so that _I can understand urbanization levels across administrative divisions._

#### Epic Reference
  
Epic 4: Urban vs. Non-Urban Population Breakdown

#### Scope
  
Global Population Reporting System

#### Level
  
Primary Level.

#### Primary Actor
  
Reporting User.

#### Preconditions
  
The World database is running and the application has been given its connection details. The data in the database is up to date.

#### Success End Condition
  
Reports showing the total population, the population living in cities and not living in cities, and the percentage of each are displayed in the command-line terminal.

#### Failed End Condition
  
No report is produced.

#### Trigger
  
The Reporting User starts the application from the command line.

### MAIN SUCCESS SCENARIO

This use case needs no input values from the Reporting User. Each report covers every continent, region or country.

1. The Reporting User starts the application from the command line.
2. System connects to the World database.
3. System retrieves the total population and the city population for the current report.
4. System calculates the non-city population as the total population minus the city population.
5. System calculates the city population percentage as: city population ÷ total population × 100.
6. System calculates the non-city population percentage as: non-city population ÷ total population × 100.
7. System displays the report in a table with column headers: Name (Continent, Region or Country), Total Population, City Population, City Population Percentage, Non-City Population, and Non-City Population Percentage.
8. System repeats steps 3 to 7 for each level of the breakdown, in this order. When run in an interactive terminal, the system waits for the Reporting User to press Enter between reports.
   - **Continent: this maps to Requirement R23: Analyze Urban Split by Continent**
   - **Region: this maps to Requirement R24: Analyze Urban Split by Region**
   - **Country: this maps to Requirement R25: Analyze Urban Split by Country**

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
