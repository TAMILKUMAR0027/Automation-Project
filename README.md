```markdown
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF6F00?style=for-the-badge&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white)
![Allure](https://img.shields.io/badge/Allure-5C2D91?style=for-the-badge&logoColor=white)

# Automation Project — LambdaTest Ecommerce Playground

> A production-grade **BDD + POM** automation framework built with **Java, Selenium, Cucumber, TestNG & Maven**.

**Application Under Test:** [LambdaTest Ecommerce Playground](https://ecommerce-playground.lambdatest.io/)

---

## Table of Contents
- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Contributors](#contributors)
- [Default Reviewer](#default-reviewer)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Reports](#reports)
- [How to Contribute](#how-to-contribute)
- [Raising Issues & Pull Requests](#raising-issues--pull-requests)
- [CI/CD](#cicd)

---

## Project Overview

This is a **Hybrid Automation Framework** designed for the LambdaTest Ecommerce Playground. It follows **Behavior Driven Development (BDD)** using Cucumber, **Page Object Model (POM)** design pattern, and TestNG for execution.

**Key Features:**
- Cross-browser support (Chrome & Firefox)
- Data-driven testing using CSV and DataTables
- End-to-End Smoke & Regression suites
- Parallel execution support
- Structured logging with Log4j2
- Multiple reporting tools (Extent Reports, Allure, Surefire)
- Jenkins & GitHub Actions ready

---

## Tech Stack

| Technology          | Purpose                          |
|---------------------|----------------------------------|
| Java 17 / 21        | Programming Language             |
| Selenium WebDriver  | Browser Automation               |
| Cucumber            | BDD Framework                    |
| TestNG              | Test Execution & Assertions      |
| Maven               | Build & Dependency Management    |
| Log4j2              | Logging                          |
| Extent Reports + Allure | Advanced Reporting           |
| Jenkins / GitHub Actions | CI/CD                        |

---

## Contributors

Thanks to all our contributors who have helped build and improve this project:

| Rank | Contributor                        | Commits |
|------|------------------------------------|---------|
| 1    | **TAMILKUMAR0027**                 | 190     |
| 2    | **14-Prasanna** (Prasanna Venkatesh K) | 122 |
| 3    | **Rishwanth-Adhishwar**            | 102     |
| 4    | **samihamuhabathulla**             | 61      |
| 5    | **raviravi31799-crypto**           | 35      |

---

## Default Reviewer

**Tamilkumar (@TAMILKUMAR0027)** — All Pull Requests should be reviewed by Tamilkumar.

---

## Prerequisites

- **JDK 17 or 21**
- **Apache Maven 3.8+**
- **Git**
- Chrome / Firefox browsers

Verify installation:
```bash
java -version
mvn -version

Running Tests
Bash# Clean and run all tests
mvn clean test

# Run specific tag (E2E Wishlist)
mvn clean test -Dcucumber.filter.tags="@WishlistE2E"

# Run with specific browser
mvn clean test -Dbrowser=chrome -Dheadless=false

Reports

Extent Reports: target/ExtentReports/
Allure Reports: Run allure serve allure-results
Surefire Reports: target/surefire-reports/


How to Contribute
We welcome contributions from everyone! Whether you're fixing a bug, adding new test scenarios, improving documentation, or enhancing the framework — your help is appreciated.
Contribution Guidelines

Fork the repository
Create a new branch (git checkout -b feature/your-feature-name)
Make your changes
Test your changes thoroughly (mvn clean test)
Commit your changes with clear messages
Push to your branch and open a Pull Request


Raising Issues & Pull Requests

Found a bug? → Please raise an Issue
Want to add a feature? → Open an Issue first for discussion, then submit a Pull Request
Documentation improvement? → Feel free to raise a PR directly

Please follow the issue and PR templates available in .github folder.

CI/CD

GitHub Actions: Automatically runs on every push and PR
Jenkins: Configured for scheduled and manual builds

## Using as Maven Dependency

You can use this framework as a dependency in other projects via **GitHub Packages**.

### 1. Add Repository

Add the following in your project's `pom.xml`:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/TAMILKUMAR0027/Automation-Project</url>
    </repository>
</repositories>


<dependency>
    <groupId>com.ecom</groupId>
    <artifactId>automation-framework</artifactId>
    <version>1.0.0</version>
</dependency>

# Run all tests
mvn clean test

# Run specific E2E suite
mvn clean test -Dcucumber.filter.tags="@WishlistE2E"

# Run with Chrome
mvn clean test -Dbrowser=chrome

Made with ❤️ by Defect Defenders LambdaTesters Team
