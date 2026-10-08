# Population Reporting System – Use Cases

UML use-case model for the Group 24 Population Reporting System. The model has two levels:

| Page | Image | Purpose |
|---|---|---|
| 1 – System Overview | `use-case-overview.png` | One actor and six high-level use cases |
| 2 – Detailed Report Use Cases | `use-case-detailed.png` | The six use cases decomposed into all 32 coursework requirements (R01–R32) |

The editable source for both pages is `use-case-diagram.drawio` (open in [diagrams.net](https://app.diagrams.net/)).

## Actor

**Reporting User** – a person acting for the organisation who needs to generate or retrieve population and demographic information held in the MySQL World database.

## Use-case catalogue

| ID | Use case | Requirements | GitHub issues |
|---|---|---|---|
| UC1 | Generate Country Reports | R01–R06 | #10, #11 |
| UC2 | Generate City Reports | R07–R16 | #12, #13 |
| UC3 | Generate Capital City Reports | R17–R22 | #14, #15 |
| UC4 | Analyse Urban and Non-Urban Population | R23–R25 | #16 |
| UC5 | Retrieve Population Information | R26–R31 | #17 |
| UC6 | Generate Major-Language Population Report | R32 | #18 |

## Requirement traceability (R01–R32)

| Req | Detailed use case | Parent |
|---|---|---|
| R01 | List Countries in the World | UC1 |
| R02 | List Countries in a Continent | UC1 |
| R03 | List Countries in a Region | UC1 |
| R04 | List Top N Countries in the World | UC1 |
| R05 | List Top N Countries in a Continent | UC1 |
| R06 | List Top N Countries in a Region | UC1 |
| R07 | List Cities in the World | UC2 |
| R08 | List Cities in a Continent | UC2 |
| R09 | List Cities in a Region | UC2 |
| R10 | List Cities in a Country | UC2 |
| R11 | List Cities in a District | UC2 |
| R12 | List Top N Cities in the World | UC2 |
| R13 | List Top N Cities in a Continent | UC2 |
| R14 | List Top N Cities in a Region | UC2 |
| R15 | List Top N Cities in a Country | UC2 |
| R16 | List Top N Cities in a District | UC2 |
| R17 | List Capital Cities in the World | UC3 |
| R18 | List Capital Cities in a Continent | UC3 |
| R19 | List Capital Cities in a Region | UC3 |
| R20 | List Top N Capital Cities in the World | UC3 |
| R21 | List Top N Capital Cities in a Continent | UC3 |
| R22 | List Top N Capital Cities in a Region | UC3 |
| R23 | Analyse Urban Split by Continent | UC4 |
| R24 | Analyse Urban Split by Region | UC4 |
| R25 | Analyse Urban Split by Country | UC4 |
| R26 | Retrieve World Population | UC5 |
| R27 | Retrieve Continent Population | UC5 |
| R28 | Retrieve Region Population | UC5 |
| R29 | Retrieve Country Population | UC5 |
| R30 | Retrieve District Population | UC5 |
| R31 | Retrieve City Population | UC5 |
| R32 | Report Major Language Speakers (Chinese, English, Hindi, Spanish, Arabic) | UC6 |

## Notation

- **Solid line** – association between the actor and a use case.
- **Hollow-triangle arrow** – generalisation: each detailed report (Rxx) is a specialised form of its parent use case (UCx).
- **Top N reports** (R04–R06, R12–R16, R20–R22) use a fixed demonstration value of N so that the reports can run unattended in GitHub Actions, as confirmed by the module leader.
- **R23–R25** report total population, population living in cities, population not living in cities, and the matching percentages. Non-city population is calculated as total population minus city population.

## Relationship to the written use cases

These diagrams are an overview. The detailed use-case definitions (Issue #33) should reuse the UC1–UC6 names and document, for each one: primary actor, goal, trigger, preconditions, main success scenario, alternative or exception flows, postconditions, and the mapped requirement numbers.

## Repository layout

```text
docs/use-cases/
├── README.md
├── use-case-diagram.drawio
├── use-case-overview.png
└── use-case-detailed.png
```
