## Group 24 DevOps Project

[![Build master](https://img.shields.io/github/actions/workflow/status/40842799/group24_project/main.yml?branch=master&label=build%20master)](https://github.com/40842799/group24_project/actions/workflows/main.yml?query=branch%3Amaster)
[![Build develop](https://img.shields.io/github/actions/workflow/status/40842799/group24_project/main.yml?branch=develop&label=build%20develop)](https://github.com/40842799/group24_project/actions/workflows/main.yml?query=branch%3Adevelop)

[![Release](https://img.shields.io/github/v/release/40842799/group24_project?display_name=release)](https://github.com/40842799/group24_project/releases/latest)
[![License](https://img.shields.io/github/license/40842799/group24_project)](LICENSE)

SET09803 DevOps coursework project developed by Group 24 at Edinburgh Napier University.


### Project Overview
  
This project uses the MySQL World database to produce population and demographic reports for countries, cities, regions and languages.  
It is a command-line application: there is no graphical interface. Reports are printed as text tables in the terminal.  
The project is being developed using DevOps practices including GitHub version control, feature branches, pull requests, Docker containers and continuous integration.

### Technologies
- Java 17
- Maven
- MySQL
- Docker
- Docker Compose
- Git and GitHub
- GitHub Actions

### Project Structure
- src/ - Java application source code
- db/ - MySQL database Docker configuration
- .github/workflows/ - GitHub Actions workflow files
- Dockerfile - Docker configuration for the Java application
- docker-compose.yml - Runs the application and database services together
- pom.xml - Maven project configuration

### Database Configuration
  
The application uses the MySQL World database.  
When running with Docker Compose, the application connects to the database using:
- Database: world
- Host: db
- Port: 3306
- User: world\_app  
Database connection settings are configured in docker-compose.yml. The application reads them from the environment variables DB\_URL, DB\_USER and DB\_PASSWORD.

### Running the Project

#### Prerequisites
  
The following software is required:
- Git
- Docker Desktop
- Java 17
- Maven

#### Clone the Repository
git clone https://github.com/40842799/group24\_project.git
cd group24\_project
mvn clean package  
You must run mvn clean package first, because the Dockerfile copies target/devopsApp.jar, and that folder is not stored in Git.

#### Run Using Docker Compose
docker compose up --build  
Docker Compose builds and starts both the Java application and MySQL database services. The application runs all 32 reports using the default input values described below, then exits.  
To stop the containers: docker compose down

### Report Inputs (N and Other Parameters)

The application produces all 32 reports each time it runs. Some reports need an input value, such as how many rows to show (N) or which continent to report on. You do not have to enter anything: if you do not give a value, a default is used.

| Input | What it controls | Default |
|---|---|---|
| N | How many rows the "Top N" reports show | 5 |
| Continent | The continent used by the continent reports | Europe |
| Region | The region used by the region reports | Western Europe |
| Country | The country used by the country reports | United Kingdom |
| District | The district used by the district reports | England |
| City | The city used by the city population report | Edinburgh |

To change a value, add it after the application name in the form --name=value. You can change as many as you like, in any order. The names are: --n, --continent, --region, --country, --district and --city.

If a value contains spaces, put it in quotes, for example --region="Eastern Asia".

#### Changing the inputs with Docker Compose
Start the database first and wait until it is ready, then run the application with your values:

docker compose up -d db  
docker compose run --rm app --n=3  
docker compose run --rm app --n=10 --continent=Asia --region="Eastern Asia" --country=Japan --district=Tokyo-to --city=Tokyo

#### Changing the inputs when running the JAR directly
Set the database connection details, then run the JAR:

export DB\_URL='jdbc:mysql://127.0.0.1:3306/world?allowPublicKeyRetrieval=true&useSSL=false'  
export DB\_USER=world\_app  
export DB\_PASSWORD=world\_example  
java -jar target/devopsApp.jar --n=3 --continent=Asia

#### Other things to know
- To see the list of options, run the application with --help.
- N must be a positive whole number. If it is not (for example --n=0 or --n=abc), or if an option is not recognised, the application prints an error and the usage guide, and no reports are produced.
- If a name matches nothing (for example a misspelt country), that report is shown with its column headings and no rows.
- When run in an interactive terminal, the application pauses after each report. Press Enter to continue.

### Development Workflow
  
Development is carried out using Git and GitHub.  
The team uses:
- master - main project branch
- develop - integration and development branch
- Feature branches - used for individual pieces of work  
Changes are committed to feature branches and reviewed using pull requests before being merged into the shared development branch.

### Product Backlog
  
The project backlog contains user stories and acceptance criteria covering:
- Country population reports
- City population reports
- Capital city reports
- Urban and non-urban population analysis
- General population queries
- Language speaker analysis

### Continuous Integration
  
GitHub Actions is used as part of the project's continuous integration workflow to check changes made to the project.

### Team
  
Group 24
SET09803 DevOps
Edinburgh Napier University
