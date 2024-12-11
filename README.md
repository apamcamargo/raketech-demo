# Raketech Automation Testing Project

## Overview 📚

This project is a comprehensive framework for UI and API automation testing. It supports Selenium WebDriver for browser-based tests and Rest-Assured for API validations, integrated with Allure for detailed reporting.

### Table of Contents

1. [Technologies Used 🛠️](#technologies-used-️)
   - [Programming Language](#programming-language)
   - [Testing Frameworks](#testing-frameworks)
   - [Build Tool](#build-tool)
   - [Reporting](#reporting)
   - [Other Tools](#other-tools)
2. [Pre-requisites 📌](#pre-requisites-)
3. [Project Structure 📜](#project-structure-)
   - [High-Level Overview](#high-level-overview)
4. [Dependencies 🖇️](#dependencies-️)
5. [Clone Project 💾](#clone-project-)
6. [Execute Tests 🚀](#execute-tests-)
   - [Running Tests via IDE](#running-tests-via-ide)
   - [Running Tests via Maven](#running-tests-via-maven)
7. [Generating Allure Reports 📈](#generating-allure-reports-)
8. [Tests ✅](#tests-)
   - [API Tests](#api-tests)
   - [UI Tests](#ui-tests)
9. [Evidence of Test Execution 📊](#evidence-of-test-execution-)

---

## Technologies Used 🛠️

### **Programming Language**
- **Java**

### **Testing Frameworks**
- **JUnit 5** for test execution.
- **Rest-Assured** for API testing.
- **Selenium** for UI testing.

### **Build Tool**
- **Maven** for dependency management and build automation.

### **Reporting**
- **Allure Report** for detailed and interactive reporting of test results.

### **Other Tools**
- **WebDriverManager** for managing browser drivers.
- **SnakeYAML** for configuration management.

---

## Pre-requisites 📌

1. **Java 17+** installed and properly set up in your system.
2. **Maven** installed and configured in your PATH.
3. Browser drivers are managed automatically using WebDriverManager, but make sure your browser versions are compatible:
    - Chrome
    - Firefox
    - Edge
4. Install Allure for report generation:
*[Allure Report Installation](https://github.com/afonsopacifer/my-personal-website/blob/master/dev/assets/styles/molecules/box-default.styl)*


## Project Structure 📜

### High-Level Overview
- **`src/test/java`**: Contains all test classes.
    - **`api`**: The classes for API tests.
        - `requests`: Contains request classes for API calls.
        - `tests`: Contains test classes for API validations.
    - **`core`**: Contains the classes with encapsulated methods for UI and API tests, such as driver management, selenium methods, etc.
    - **`ui`**: UI test structure.
        - `locators`: Locator with the elements mapped.
        - `pages`: Page Object Model classes.
        - `tests`: Contains test classes for UI validationss.
    - **`utils`**: Utility classes, including Allure helper, YAML reader and Log4j.
- **`src/test/resources`**: Configuration files.
    - `config.yml`: Centralized configuration of main URL's and the browser selection on the config.yml file.

---

## Dependencies 🖇️

Key dependencies used:
- **JUnit 5**: Test framework.
- **Selenium WebDriver**: For UI automation.
- **Rest-Assured**: For API testing.
- **Allure**: For test reporting.

Dependencies are listed in the [pom.xml](pom.xml) file.

---

## Clone Project 💾

1. Clone the repository:
   ```bash
   git clone https://github.com/apamcamargo/raketech-demo.git

2. Go to project folder
3. Done, you already can start to use the project

---

## Execute Tests 🚀
### Running Tests via IDE
1. Open the project in your IDE (e.g., IntelliJ IDEA, Eclipse).
2. Navigate to the desired test class
   * _src/test/java/com/raketech/demo/runner/_
3. Right-click on the AllTestsSuite class and click on run with junit

### Running Tests via Maven
1. Open a terminal window on the project folder
2. Run all tests using Maven
   ```bash
   mvn test
   
---

## Generating Allure Reports 📈
Make sure you already have Allure Report installed on your machine. To generate the report, follow the steps below.

1. After run the tests, open a terminal window on project root folder and run the command
   ```bash
   allure serve allure-results


---


## Tests ✅
### API
* 3 reusable tests to validate different characteristics of the characters
  * Check if R2-D2 have the skin_color as white, blue
  * Check if Luke Skywalker have the hair_color as blond
  * Check if Darth Vader have the eye_color as yellow
* 1 Test to validate the pagination results size and if each page return distinct results
* 3 tests to retrieve homeworld enpoint dynamically, send a GET to planet API and check the planet of character
  * Check if R2-D2 is from Naboo planet
  * Check if Leia Organa is from Alderaan planet
  * Check if Beru Whitesun lars is from Tatooine planet

---

# Evidence of Test Execution 📊
## API
![Api_results_1.png](/src/test/resources/evidences/Api_results_1.png)
![Api_results_2.png](/src/test/resources/evidences/Api_results_2.png)

## UI
![UI_evidence_1.png](/src/test/resources/evidences/UI_evidence_1.png)
![UI_evidence_2.png](/src/test/resources/evidences/UI_evidence_2.png)
