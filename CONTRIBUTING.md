# Contributing to Automation Project

Thank you for considering contributing to this project! ❤️

## How to Contribute

### 1. Reporting Bugs / Requesting Features

- Please first search through existing [Issues](https://github.com/TAMILKUMAR0027/Automation-Project/issues) to avoid duplicates.
- Use the **Bug Report** template when creating a new issue.

### 2. Development Setup

```bash
# Clone the repository
git clone https://github.com/TAMILKUMAR0027/Automation-Project.git
cd Automation-Project

# Install dependencies
mvn clean install -DskipTests


3. Making Changes

Create a new branch from develop (preferred) or main:Bashgit checkout -b feature/your-feature-name
Make your changes and ensure:
Code follows existing style
All tests pass: mvn clean test
No new warnings

Commit with clear messages:Bashgit commit -m "feat: add new E2E scenario for checkout"
Push and open a Pull Request.

4. Pull Request Guidelines

PR title should be clear and follow conventional commits (e.g., feat:, fix:, docs:)
Reference the related issue
All checks (Maven build + SonarQube) must pass
Must be reviewed by @TAMILKUMAR0027

5. Code of Conduct
Please read and follow our Code of Conduct.
