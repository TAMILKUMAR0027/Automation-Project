# 🚀 Automation Project - LambdaTest Playground (Selenium + Cucumber + TestNG)

This repository contains a complete **Selenium Automation Framework** built using **Java + Cucumber BDD + TestNG + Maven** for automating the **LambdaTest Ecommerce Playground Website**.

The framework supports:

✅ Cross Browser Execution (Chrome / Firefox)  
✅ Page Object Model (POM) Design  
✅ Cucumber Feature Files (BDD)  
✅ TestNG Runner Execution  
✅ Jenkins CI/CD Integration  
✅ Reporting:
- Cucumber HTML Report  
- Cucumber JSON Report  
- Extent Spark Report  
- Allure Report  
- TestNG Surefire Report  

---

# 📌 Project Details

- **Project Name:** Automation-Project  
- **Application Under Test:** LambdaTest Ecommerce Playground  
- **Framework Type:** Hybrid (BDD + POM + TestNG)  
- **Team Name:** Defect Defenders LambdaTesters  
- **Repository:** GitHub  

---

# 🛠 Tech Stack Used

| Technology | Purpose |
|----------|---------|
| Java | Programming Language |
| Selenium WebDriver | UI Automation |
| Cucumber | BDD Framework |
| TestNG | Test Execution |
| Maven | Build Tool |
| WebDriverManager | Browser Driver Handling |
| Log4j2 | Logging |
| Extent Reports | Advanced HTML Reporting |
| Allure Reports | Dashboard Reporting |
| Jenkins | CI/CD Pipeline |

---

# 📌 Prerequisites

Before running this project, ensure the following are installed:

✅ Java JDK (Recommended: Java 17 / Java 21)  
✅ Maven (Apache Maven 3.x)  
✅ Git  
✅ Google Chrome  
✅ Mozilla Firefox  
✅ IDE (IntelliJ IDEA / Eclipse) - Optional  

---

# ⚙️ How to Check Installed Versions (Windows CMD)

## Java Version
```cmd
java -version


Maven Version
mvn -version
Git Version
git --version
Firefox Version (WMIC)
wmic datafile where name="C:\\Program Files\\Mozilla Firefox\\firefox.exe" get Version
IntelliJ Version (WMIC)
wmic datafile where name="C:\\Program Files\\IntelliJ IDEA 2025.3.3\\bin\\idea64.exe" get Version

---

📂 Project Folder Structure
Automation-Project
Automation-Project
│
├── src
│   └── test
│       ├── java
│       │   ├── com.pages
│       │   │   ├── LaunchPages.java
│       │   │   └── LoginPage.java
│       │   │
│       │   ├── com.stepDefinitions
│       │   │   ├── Launch.java
│       │   │   └── LoginSteps.java
│       │   │
│       │   ├── com.hooks
│       │   │   └── Hooks.java
│       │   │
│       │   ├── com.runner
│       │   │   └── TestNgRunner.java
│       │   │
│       │   └── com.utils
│       │       ├── ConfigReader.java
│       │       ├── DriverFactory.java
│       │       └── HelperClass.java
│       │
│       └── resources
│           ├── features
│           │   ├── launchWebsite.feature
│           │   └── login.feature
│           │
│           ├── config.properties
│           ├── extent.properties
│           ├── extent-config.xml
│           └── log4j2.xml
│
├── logs
│   └── logs.log
│
├── pom.xml
├── testng.xml
├── Jenkinsfile
└── README.md

⚙️ Configuration Setup

All execution configurations are maintained in:

📌 src/test/resources/config.properties

Example:

browser=chrome
url=https://ecommerce-playground.lambdatest.io/
🏃 How to Run the Automation Project
✅ Run Project (Default Execution)
mvn clean test

This will run the suite configured inside:

📌 testng.xml

✅ Run with Chrome Browser

Update:

📌 config.properties

browser=chrome

Then execute:

mvn clean test



