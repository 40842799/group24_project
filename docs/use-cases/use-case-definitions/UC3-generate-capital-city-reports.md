# USE CASE UC3: Generate Capital City Reports

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Reporting User* I want to *view a report of all capital cities meeting a specified criteria, sorted by population (largest to smallest)* so that *I can assess global demographic distribution.*


### Epic Reference

Epic 3: Capital City Reports

### Scope

Global Population Reporting System

### Level

Primary Level.

### Primary Actor

Reporting User.

### Preconditions

The Reporting User has access to the Global Demographic Database. Database is up-to-date.

### Success End Condition

A report with results sorted in descending order of population is generated.

### Failed End Condition

No report is produced.


### Trigger

The Reporting User selects the 'Capital City Population' option from the system's navigation menu

## MAIN SUCCESS SCENARIO

1. The Reporting User navigates to capital city population section of system.
2. System displays filter options (Global, Continent, Region) and an input option for row limit (N)
3. The Reporting User selects one of the filters, leaves row limit blank, and requests report.
4. System filters database to retrieve only capital city population records matching the criteria specified.
5. System sorts retrieved population records in descending order.
6. System displays report in a table with column titles: Name, Country, and Population.


## ALTERNATIVE FLOWS

3. The Reporting User selects one of the following filter options:

   a. World  
   **This maps to Requirement R17: List of Capital Cities in the World** 
    1. The Reporting User selects Global filter and requests report.  
    2. System retrieves all capital city population records from database.  
    This flow resumes at Step 5 of the Main Flow

   b. Continent    
   **This maps to Requirement R18: List of Capital Cities in a Continent** 
    1. The Reporting User selects Continent and requests report.  
    2. System prompts the Reporting User to enter a continent name.  
    3. The Reporting User enters a specific continent name.
    4. System filters database to retrieve only capital city population records matching the specified continent.  
    This flow resumes at Step 5 of the Main Flow

   c. Region  
   **This maps to Requirement R19: List of Capital Cities in a Region**   
    1. The Reporting User selects Region and requests report.  
    2. System prompts the Reporting User to enter a region name.  
    3. The Reporting User enters a specific region name  
    4. System filters database to retrieve only capital city population records matching the specified region.  
    This flow resumes at Step 5 of the Main Flow


3.  The Reporting User, in addition to selecting a filter, enters a positive integer for row limit (N)  
    This maps to Requirements R20, R21, and R22
     1. System filters database to retrieve only capital city population records matching the criteria specified.
     2. The System sorts all records in descending order    
     3. The System retrieves only the first *N* capital cities   
        This flow resumes at Step 6 of the Main Flow

## EXCEPTION FLOWS

3. **No Data is found for specified filter**:
   1. System displays error message "No data found for the specified location. Please check your selection".
   2. System allows the Reporting User to re-enter filter.


3. **Invalid Input for Row Limit (N). The Reporting User inputs a negative integer, noninteger, or zero:**
   1. System Displays error message: "Row Limit  is invalid. Please try again"
   2. System allows the Reporting User to re-enter row limit (N)


## SCHEDULE

**DUE DATE**: Release 1.0



