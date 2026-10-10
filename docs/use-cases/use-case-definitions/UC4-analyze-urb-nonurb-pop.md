# USE CASE UC4: Analyse Urban and Non-Urban Population 

## CHARACTERISTIC INFORMATION

### Goal in Context
As a *Reporting User* I want to *view total population alongside urban and non-urban breakdown (with percentages) for every Continent, Region, and Country* so that *I can understand urbanization levels across administrative divisions.*

### Epic Reference

Epic 4: Urban vs. Non-Urban Population Breakdown

### Scope

Global Population Reporting System

### Level

Primary Level.

### Primary Actor

Reporting User.

### Preconditions

The Reporting User has access to the Global Demographic Database. Database is up-to-date.

### Success End Condition

A report showing the total population, the population living in cities and not living in cities, and the percentage of each is generated.

### Failed End Condition

No report is produced. 

### Trigger

The Reporting User selects the 'Urban vs. Non-Urban Population' option from the system's navigation menu

## MAIN SUCCESS SCENARIO

1. The Reporting User navigates to urban vs. non-urban population section of system.
2. System displays filter options (Continent, Region, Country).
3. The Reporting User selects one of the filters and requests report.
4. System filters database to retrieve only the total, urban, and non-urban population records matching the criteria specified.
5. System calculates the urban population percentage using the following formula: $\left(\frac{\text{City Pop}}{\text{Total Pop}}\right) \times 100$.
6. System calculates the non-urban population percentage using the following formula: $\left(\frac{\text{Non-City Pop}}{\text{Total Pop}}\right) \times 100$.
7. System displays report in a table with column headers: Name (Continent, Region or Country), Total Population, City Population, City Population Percentage, Non-City Population, and Non-City Population Percentage.


## ALTERNATIVE FLOWS

3. The Reporting User selects one of the following filter options:

   a. Continent    
   **This maps to Requirement R23: Analyze Urban Split by Continent** 
    1. The Reporting User selects Continent and requests report.  
    2. System filters database to retrieve only the total, urban, and non-urban population records of each continent.
    This flow resumes at Step 5 of Main Flow

   b. Region  
   **This maps to Requirement R24: Analyze Urban Split by Region**  
    1. The Reporting User selects Region and requests report.  
    2. System filters database to retrieve only the total, urban, and non-urban population records of each region.
    This flow resumes at Step 5 of Main Flow

   c. Country
   **This maps to Requirement R25: Analyze Urban Split by Country**
    1. The Reporting User selects Country and requests report.  
    2. System filters database to retrieve only the total, urban, and non-urban population records of each country.
    This flow resumes at Step 5 of Main Flow


## EXCEPTION FLOWS

3. **No Data is found for specified filter**:
   1. System displays error message "No data found for the specified location. Please check your selection".
   2. System allows the Reporting User to re-enter filter.

## SCHEDULE

**DUE DATE**: Release 1.0