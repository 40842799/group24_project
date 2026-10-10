# USE CASE UC5: Retrieve Population Information

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Reporting User* I want to *query the total exact population for the World, a Continent, a Region, a Country, a District, or a City* so that *I can obtain rapid statistical totals.*


### Epic Reference

Epic 5: General Population Access

### Scope

Global Population Reporting System

### Level

Primary Level.

### Primary Actor

Reporting User

### Preconditions

The Reporting User has access to the Global Demographic Database. Database is up-to-date.

### Success End Condition

A numerical value representing the total population of the selected location is generated

### Failed End Condition

No numerical value is generated

### Trigger

The Reporting User selects the 'Total Population' option from the system's navigation menu

## MAIN SUCCESS SCENARIO

1. The Reporting User navigates to General Population section of system.
2. System displays filter options (World, Continent, Region, Country, District, and City).
3. The Reporting User selects one of the filters and requests report.
4. System filters database to retrieve only the total population of the specified location.
5. System displays report showing the specified location and the numerical value representing its total population.



## ALTERNATIVE FLOWS

3. The Reporting User selects one of the following filters:

    a. World  
  **This maps to Requirements R26: Retrieve World Population**
    1. The Reporting User selects World filter and requests report.   
    2. System retrieves the total population of the World.   
    3. System displays report showing 'World' as the selected criteria and the numerical value representing its total population

    b. Continent  
  **This meets Requirements R27: Retrieve Continent Population** 
    1. The Reporting User selects Continent filter and requests report.  
    2. System prompts the Reporting User to enter a continent name.  
    3. System retrieves the total population of the specified continent.     
    4. System displays report showing the Continent Name and the corresponding total population.

   c. Region  
  **This maps to Requirements R28: Retrieve Region Population**
   1. The Reporting User selects Region filter and requests report.    
   2. System prompts the Reporting User to enter the region name.  
   3. System retrieves the total population of the specified region.     
   4. System displays report showing the Region Name and the corresponding total population.  

   d. Country   
  **This maps to Requirements R29: Retrieve Country Population**  
   1. The Reporting User selects Country filter and requests report.    
   2. System prompts the Reporting User to enter the country name.  
   3. System retrieves the total population of the specified country.     
   4. System displays report showing the Country Name and the corresponding total population.  

   e. District  
  **This maps to Requirements R30: Retrieve District Population**
   1. The Reporting User selects District filter and requests report.    
   2. System prompts the Reporting User to enter the district name.  
   3. System retrieves the total population of the specified district.     
   4. System displays report showing the District Name and the corresponding total population.  

   f. City    
  **This meets Requirements R31: Retrieve City Population**
   1. The Reporting User selects City filter and requests report.    
   2. System prompts the Reporting User to enter the city name.  
   3. System retrieves the total population of the specified city.     
   4. System displays report showing the City Name and the corresponding total population.  


## EXCEPTION FLOWS

3. **No Data is found for specified filter**:
    1. System displays error message "No data found for the specified location. Please check your selection".
    2. System allows the Reporting User to re-enter filter.


## SCHEDULE

**DUE DATE**: Release 1.0




