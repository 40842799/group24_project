# Group 24 DevOps Project

SET09803 DevOps coursework project developed by Group 24 at Edinburgh Napier University.

## Project Overview

This project uses the MySQL World database to produce population and demographic reports for countries, cities, regions and languages.

The project is being developed using DevOps practices including GitHub version control, feature branches, pull requests, Docker containers and continuous integration.

## Technologies

- Java 17
- Maven
- MySQL
- Docker
- Docker Compose
- Git and GitHub
- GitHub Actions

## Project Structure

- `src/` - Java application source code
- `db/` - MySQL database Docker configuration
- `.github/workflows/` - GitHub Actions workflow files
- `Dockerfile` - Docker configuration for the Java application
- `docker-compose.yml` - Runs the application and database services together
- `pom.xml` - Maven project configuration

## Database Configuration

The application uses the MySQL World database.

When running with Docker Compose, the application connects to the database using:

- Database: `world`
- Host: `db`
- Port: `3306`
- User: `world_app`

Database connection settings are configured in `docker-compose.yml`.

## Running the Project

### Prerequisites

The following software is required:

- Git
- Docker Desktop
- Java 17
- Maven

### Clone the Repository

```bash
git clone https://github.com/40842799/group24_project.git
cd group24_project
```

### Run Using Docker Compose

```bash
docker compose up --build
```

Docker Compose builds and starts both the Java application and MySQL database services.

To stop the containers:

```bash
docker compose down
```

## Development Workflow

Development is carried out using Git and GitHub.

The team uses:

- `master` - main project branch
- `develop` - integration and development branch
- Feature branches - used for individual pieces of work

Changes are committed to feature branches and reviewed using pull requests before being merged into the shared development branch.

## Product Backlog

The project backlog contains user stories and acceptance criteria covering:

- Country population reports
- City population reports
- Capital city reports
- Urban and non-urban population analysis
- General population queries
- Language speaker analysis

## Continuous Integration

GitHub Actions is used as part of the project's continuous integration workflow to check changes made to the project.

## Team

Group 24  
SET09803 DevOps  
Edinburgh Napier University
