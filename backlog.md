# Product Backlog: World Population Reporting System

## Epic 1: Country Reports
Target Deliverables: Reports on country population data sorted from largest to smallest.

### Story 1.1: All Countries in the World
* **As an** Organization Data Analyst
* **I want to** view a report of all countries in the world sorted by population (largest to smallest)
* **So that** I can assess global demographic distribution.
* **Acceptance Criteria**:
    - Displays Code, Name, Continent, Region, Population, and Capital.
    - Results are sorted in descending order of Population.

### Story 1.2: All Countries in a Continent
* **As an** Organization Data Analyst
* **I want to** view a report of all countries in a specified continent sorted by population
* **So that** I can analyze continental demographics.
* **Acceptance Criteria**:
    - User can specify the continent name.
    - Displays Code, Name, Continent, Region, Population, and Capital.
    - Results are filtered by continent and sorted in descending order of Population.

### Story 1.3: All Countries in a Region
* **As an** Organization Data Analyst
* **I want to** view a report of all countries in a specified region sorted by population
* **So that** I can analyze regional demographics.
* **Acceptance Criteria**:
    - User can specify the region name.
    - Displays Code, Name, Continent, Region, Population, and Capital.
    - Results are filtered by region and sorted in descending order of Population.

### Story 1.4: Top N Populated Countries in the World
* **As an** Organization Data Analyst
* **I want to** specify $N$ to retrieve the top $N$ populated countries globally
* **So that** I can highlight the world's most populous nations.
* **Acceptance Criteria**:
    - User inputs an integer parameter $N$.
    - Displays Code, Name, Continent, Region, Population, and Capital for the top $N$ countries.

### Story 1.5: Top N Populated Countries in a Continent
* **As an** Organization Data Analyst
* **I want to** specify $N$ and a continent to retrieve the top $N$ populated countries in that continent
* **So that** I can identify key nations per continent.
* **Acceptance Criteria**:
    - User inputs a continent name and integer parameter $N$.
    - Displays Code, Name, Continent, Region, Population, and Capital for the top $N$ countries in that continent.

### Story 1.6: Top N Populated Countries in a Region
* **As an** Organization Data Analyst
* **I want to** specify $N$ and a region to retrieve the top $N$ populated countries in that region
* **So that** I can identify key nations per region.
* **Acceptance Criteria**:
    - User inputs a region name and integer parameter $N$.
    - Displays Code, Name, Continent, Region, Population, and Capital for the top $N$ countries in that region.

---

## Epic 2: City Reports
Target Deliverables: Reports on city population data sorted from largest to smallest.

### Story 2.1: All Cities in the World
* **As an** Organization Data Analyst
* **I want to** view all cities in the world ordered by population (largest to smallest)
* **So that** I can analyze global urbanization patterns.
* **Acceptance Criteria**:
    - Displays Name, Country, District, and Population.
    - Results are sorted in descending order of Population.

### Story 2.2: All Cities in a Continent
* **As an** Organization Data Analyst
* **I want to** view all cities in a specified continent ordered by population
* **So that** I can evaluate urban density per continent.
* **Acceptance Criteria**:
    - User specifies the continent.
    - Displays Name, Country, District, and Population.

### Story 2.3: All Cities in a Region
* **As an** Organization Data Analyst
* **I want to** view all cities in a specified region ordered by population
* **So that** I can analyze regional urban hubs.
* **Acceptance Criteria**:
    - User specifies the region.
    - Displays Name, Country, District, and Population.

### Story 2.4: All Cities in a Country
* **As an** Organization Data Analyst
* **I want to** view all cities in a specified country ordered by population
* **So that** I can assess national urbanization.
* **Acceptance Criteria**:
    - User specifies the country.
    - Displays Name, Country, District, and Population.

### Story 2.5: All Cities in a District
* **As an** Organization Data Analyst
* **I want to** view all cities in a specified district ordered by population
* **So that** I can evaluate local administrative division populations.
* **Acceptance Criteria**:
    - User specifies the district.
    - Displays Name, Country, District, and Population.

### Story 2.6: Top N Populated Cities (World / Continent / Region / Country / District)
* **As an** Organization Data Analyst
* **I want to** retrieve the top $N$ populated cities filtered by scope (World, Continent, Region, Country, or District)
* **So that** I can restrict focus to major urban hubs.
* **Acceptance Criteria**:
    - Accepts boundary parameter and integer $N$.
    - Displays Name, Country, District, and Population for top $N$ entries.

---

## Epic 3: Capital City Reports
Target Deliverables: Reports specifically for capital city demographics.

### Story 3.1: All Capital Cities (World / Continent / Region)
* **As an** Organization Data Analyst
* **I want to** view capital cities sorted by population globally, by continent, or by region
* **So that** I can evaluate capital city demographics.
* **Acceptance Criteria**:
    - Displays Name, Country, and Population.
    - Sorted in descending order of Population.

### Story 3.2: Top N Capital Cities (World / Continent / Region)
* **As an** Organization Data Analyst
* **I want to** view the top $N$ capital cities globally, by continent, or by region
* **So that** I can isolate the largest capital cities.
* **Acceptance Criteria**:
    - Accepts area filter and integer parameter $N$.
    - Displays Name, Country, and Population for top $N$ capital cities.

---

## Epic 4: Urban vs. Non-Urban Population Breakdown
Target Deliverables: Detailed demographic summaries comparing city dwellers vs. non-city dwellers.

### Story 4.1: Population Breakdown by Continent / Region / Country
* **As an** Organization Executive
* **I want to** view total population alongside urban and non-urban breakdown (with percentages) for every Continent, Region, and Country
* **So that** I can understand urbanization levels across administrative divisions.
* **Acceptance Criteria**:
    - Output columns:
        - Target Area Name (Continent/Region/Country)
        - Total Population
        - City Population (+ %)
        - Non-City Population (+ %)
    - Percentage formulas: $\text{City \%} = \left(\frac{\text{City Pop}}{\text{Total Pop}}\right) \times 100$.

---

## Epic 5: General Population Access
Target Deliverables: Single aggregated population count queries.

### Story 5.1: Specific Entity Population Lookups
* **As an** Organization User
* **I want to** query the total exact population for the World, a Continent, a Region, a Country, a District, or a City
* **So that** I can obtain rapid statistical totals.
* **Acceptance Criteria**:
    - User can request total count for any selected category.
    - Output displays exact integer count.

---

## Epic 6: Language Speaker Analysis
Target Deliverables: Global language usage metrics.

### Story 6.1: Language Distribution Report
* **As an** Organization Analyst
* **I want to** view the number of people speaking Chinese, English, Hindi, Spanish, and Arabic (sorted greatest to smallest) along with global percentages
* **So that** I can analyze global language dominance.
* **Acceptance Criteria**:
    - Calculates total speakers for: Chinese, English, Hindi, Spanish, and Arabic.
    - Sorts results in descending order by total speakers.
    - Displays: Language, Total Speakers, and Percentage of World Population.