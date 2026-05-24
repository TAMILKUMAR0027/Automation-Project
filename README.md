```markdown
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF6F00?style=for-the-badge&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white)
![Allure](https://img.shields.io/badge/Allure-5C2D91?style=for-the-badge&logoColor=white)
![ExtentReports](https://img.shields.io/badge/Extent%20Reports-0A66C2?style=for-the-badge&logo=googlechrome&logoColor=white)

---

# Automation Project — LambdaTest Playground

> A production-grade Selenium BDD automation framework built with Java, Cucumber, TestNG, and Maven.  
> Designed for scalability, maintainability, and CI/CD integration.

**Application Under Test:** [LambdaTest Ecommerce Playground](https://ecommerce-playground.lambdatest.io/)

---

## Table of Contents

- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Reports](#reports)
- [Logging](#logging)
- [Jenkins Integration](#jenkins-integration)
- [Contributors](#contributors)

---

## Project Overview

This framework automates end-to-end UI test scenarios for the LambdaTest Ecommerce Playground using a hybrid architecture combining BDD, Page Object Model, and data-driven testing.

| Key                  | Value                                   |
|----------------------|-----------------------------------------|
| Project Name         | Automation-Project                      |
| Framework Type       | Hybrid — BDD + POM + TestNG             |
| Application Under Test | LambdaTest Ecommerce Playground       |
| Team                 | Defect Defenders LambdaTesters          |
| Build Tool           | Maven                                   |
| Version Control      | GitHub                                  |

**Core capabilities:**

- Page Object Model (POM) design pattern
- Cucumber BDD feature files with Gherkin syntax
- TestNG test runner with parallel execution support
- Cross-browser execution — Chrome and Firefox
- Data-driven testing via CSV and Excel
- Centralized configuration via `config.properties`
- Structured logging with Log4j2
- Jenkins CI/CD pipeline integration
- Multiple report formats — HTML, JSON, Extent, Allure, Surefire

---

## Tech Stack

| Technology         | Purpose                        |
|--------------------|--------------------------------|
| Java 17 / 21       | Programming language           |
| Selenium WebDriver | Browser automation             |
| Cucumber           | BDD framework                  |
| TestNG             | Test execution and assertions  |
| Maven              | Build and dependency management|
| WebDriverManager   | Automatic driver management    |
| Log4j2             | Structured logging             |
| Extent Reports     | Advanced HTML reporting        |
| Allure Reports     | Dashboard reporting            |
| Jenkins            | CI/CD pipeline                 |

---

## Prerequisites

Ensure the following are installed before running the project:

| Requirement         | Recommended Version |
|---------------------|---------------------|
| Java JDK            | 17 or 21            |
| Apache Maven        | 3.x                 |
| Git                 | Latest              |
| Google Chrome       | Latest stable       |
| Mozilla Firefox     | Latest stable       |
| IDE (optional)      | IntelliJ IDEA       |

**Verify installed versions:**

```bash
# Java
java -version

# Maven
mvn -version

# Git
git --version

# Firefox (Windows)
wmic datafile where name="C:\\Program Files\\Mozilla Firefox\\firefox.exe" get Version

# Chrome (Windows)
wmic datafile where name="C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe" get Version
```

---

## Project Structure

```
Automation-Project/
│
├── src/
│   └── test/
│       ├── java/
│       │   ├── com.actions/          # Action layer — reusable browser interactions
│       │   ├── com.driver/           # WebDriver initialization and management
│       │   ├── com.hooks/            # Cucumber Before/After hooks
│       │   ├── com.pages/            # Page Object Model classes
│       │   ├── com.runner/           # TestNG Cucumber runner classes
│       │   ├── com.stepDefinitions/  # Cucumber step definitions
│       │   └── com.utils/            # Utilities — CSV reader, config reader, etc.
│       │
│       └── resources/
│           ├── features/             # Cucumber .feature files
│           ├── config.properties     # Browser, URL, and execution config
│           ├── extent.properties     # Extent report configuration
│           ├── extent-config.xml     # Extent report theme and metadata
│           ├── log4j2.xml            # Log4j2 logging configuration
│           └── wishlist_data.csv     # Test data for data-driven scenarios
│
├── logs/                             # Execution log files
│
├── target/
│   ├── allure-results/               # Raw Allure report data
│   ├── cucumber-report.html          # Cucumber HTML report
│   ├── cucumber-report.json          # Cucumber JSON report
│   └── failedrerun.txt               # Failed scenario URIs for rerun
│
├── pom.xml                           # Maven project descriptor
├── testng.xml                        # Main TestNG suite
├── testng-failed.xml                 # Failed scenarios rerun suite
├── Jenkinsfile                       # Jenkins pipeline definition
└── README.md
```

---

## Configuration

All execution settings are managed in a single properties file:

**`src/test/resources/config.properties`**

```properties
browser=chrome
headless=false
url=https://ecommerce-playground.lambdatest.io/index.php?route=common/home
```

| Property   | Accepted Values                                                  |
|------------|------------------------------------------------------------------|
| `browser`  | `chrome`, `firefox`                                              |
| `headless` | `true`, `false`                                                  |
| `url`      | Any valid application URL                                        |

---

## Running Tests

### Full test suite

```bash
mvn clean test
```

Executes all scenarios configured in `testng.xml`.

---

### Run with a specific browser

Update `browser` in `config.properties`, then run:

```bash
mvn clean test
```

---

### Run by Cucumber tag

Update the `tags` value in `TestNgRunner.java`:

```java
tags = "@Smoke"
```

Then run:

```bash
mvn clean test
```

**Available tags:**

| Tag                | Description                              |
|--------------------|------------------------------------------|
| `@WishlistFeature` | All wishlist scenarios                   |
| `@Smoke`           | Critical path scenarios                  |
| `@Regression`      | Full regression suite                    |
| `@ProductAdded`    | Add product to wishlist scenarios        |
| `@MultipleProduct` | Multiple product wishlist scenarios      |
| `@RemoveProduct`   | Remove product from wishlist scenarios   |

---

### Rerun failed scenarios

Failed scenarios are automatically captured in `target/failedrerun.txt` after each run.

**Step 1 — Run the full suite:**

```bash
mvn clean test
```

**Step 2 — Rerun only failed scenarios:**

```bash
mvn test -Dtest=Failed_TestNgRunner
```

**Step 3 — Run full suite and rerun failures in sequence:**

```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml,testng-failed.xml
```

**Step 4 - Check the Dependency-Update
```bash
mvn versions:display-dependency-updates
```

**Step 5 - Check Update only property versions
```bash
mvn versions:update-properties
```

`testng-failed.xml` configuration:

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Failed Test Suite">
    <test name="Failed Scenarios Rerun">
        <classes>
            <class name="com.runner.Failed_TestNgRunner"/>
        </classes>
    </test>
</suite>
```

---

## Reports

Reports are generated automatically after each execution run.

| Report Type          | Location                              |
|----------------------|---------------------------------------|
| Cucumber HTML        | `target/cucumber-report.html`         |
| Cucumber JSON        | `target/cucumber-report.json`         |
| Extent Spark Report  | `test-output/ExtentReport.html`       |
| Allure Results       | `target/allure-results/`             |
| TestNG Surefire      | `target/surefire-reports/`           |

**Serve Allure report locally:**

```bash
allure serve target/allure-results
```

**Generate static Allure HTML report:**

```bash
allure generate target/allure-results --clean -o target/allure-report
```

---

## Logging

Log4j2 is configured to write structured logs to:

```
logs/logs.log
```

Log configuration file: `src/test/resources/log4j2.xml`

Logs capture step-level execution details, browser events, assertion results, and error stack traces.

---

## Jenkins Integration

This framework is fully compatible with Jenkins CI/CD pipelines.

**Required Jenkins plugins:**

| Plugin              | Purpose                        |
|---------------------|--------------------------------|
| Git Plugin          | Source code checkout           |
| Maven Integration   | Build execution                |
| Allure Plugin       | Allure report dashboard        |
| HTML Publisher      | Cucumber/Extent HTML reports   |

**Pipeline execution via Jenkinsfile:**

```groovy
pipeline {
    agent any
    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }
        stage('Execute Tests') {
            steps { sh 'mvn clean test' }
        }
        stage('Allure Report') {
            steps {
                allure includeProperties: false,
                       results: [[path: 'target/allure-results']]
            }
        }
    }
    post {
        always {
            publishHTML(target: [
                reportName : 'Cucumber Report',
                reportDir  : 'target',
                reportFiles: 'cucumber-report.html'
            ])
        }
    }
}
```

---

## Execution Command Reference

| Purpose                          | Command                                                                 |
|----------------------------------|-------------------------------------------------------------------------|
| Run full suite                   | `mvn clean test`                                                        |
| Run failed scenarios only        | `mvn test -Dtest=Failed_TestNgRunner`                                   |
| Run full suite + rerun failures  | `mvn clean test -Dsurefire.suiteXmlFiles=testng.xml,testng-failed.xml` |
| Serve Allure dashboard locally   | `allure serve target/allure-results`                                    |
| Generate static Allure report    | `allure generate target/allure-results --clean -o target/allure-report` |

---

## Troubleshooting

| Issue                          | Resolution                                                   |
|--------------------------------|--------------------------------------------------------------|
| Tests not running              | Verify `tags` in `TestNgRunner.java` match feature file tags |
| Browser not launching          | Check `browser` value in `config.properties`                 |
| Driver error                   | WebDriverManager auto-manages drivers — ensure internet access |
| CSV data not found             | Confirm `wishlist_data.csv` exists in `src/test/resources/`  |
| Allure report not generating   | Install Allure CLI and verify `target/allure-results/` exists |
| Failed rerun not working       | Confirm `target/failedrerun.txt` has content after a failed run |

---

## Contributors

| Name                 | Role                         |
|----------------------|------------------------------|
| Tamilkumar           | Automation Engineer          |
| Rishwanth            | Automation Engineer          |
| Prasanna Venkatesh K | Automation Engineer          |
| Samiha               | Automation Engineer          |
| Jothika              | Automation Engineer          |

**Team:** Defect Defenders LambdaTesters

---

## License

This project is developed for educational and automation testing purposes only.

---

*Built with Selenium WebDriver + Cucumber BDD + TestNG + Maven*
```---

# 📜 License

This project is licensed under the **MIT License**.

Copyright (c) 2026 Prasanna Venkatesh K

Permission is hereby granted, free of charge, to any person obtaining a copy  
of this software and associated documentation files (the "Software"), to deal  
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, and to permit persons to whom the Software is  
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE  
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER  
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,  
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  
SOFTWARE.
