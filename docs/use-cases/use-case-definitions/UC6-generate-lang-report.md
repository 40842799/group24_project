# USE CASE UC6: Generate Major-Language Report

## CHARACTERISTIC INFORMATION

### Goal in Context
As an *Organization Analyst* I want to *view the number of people speaking Chinese, English, Hindi, Spanish, and Arabic (sorted greatest to smallest) along with global percentages* so that *I can analyze global language dominance.*

### Epic Reference

Epic 6: Language Speaker Analysis

### Scope

Global Population Reporting System

### Level

Primary Level.

### Primary Actor

Organization Data Analyst.

### Preconditions

The Organization Analyst has access to the Global Demographic Database. Database is up-to-date.

### Success End Condition

A report with results sorted in descending order by total speakers is generated.

### Failed End Condition

No report is produced.

### Trigger

The actor selects the 'Language Population' option from the system's navigation menu

## MAIN SUCCESS SCENARIO

**This maps with the Requirement R32: Report Major Language Speakers**
1. Data Analyst navigates to the Major Language Population section of system.
2. Data Analyst requests language report.
3. System retrieves the total global population for each major language.
4. System retrieves overall total global population
5. System calculates the percentage of the world population that speaks each language.
6. System sorts calculated results in descending order.
7. System displays report in a table with column titles: Language, Total Speakers, and Percentage of World Population.


## ALTERNATIVE FLOWS

*None*

## EXCEPTION FLOWS

2. **No Data is found for one or more of the languages**:
    1. System displays error message "Language data is currently Incomplete. Try again later"
    2. System returns to the navigation menu 


## SCHEDULE

**DUE DATE**: Release 1.0