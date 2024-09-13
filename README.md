### Technologies Used ###

Java: Programming language
Selenium WebDriver: For browser automation
Page Object Model (POM): Design pattern for better test maintainability
Page Factory: To initialize web elements in POM classes
TestNG: Test framework for running tests
JSON: For managing test data
ExtentReports: For generating test reports
Maven: For managing dependencies and running tests

### Prerequisites ###

Before setting up the project, ensure you have the following installed:

Java 8 or above: Required for executing Selenium tests.
Maven: Used to handle project dependencies and build automation.

### Setup Instructions ###

1. Clone the repository
git clone https://github.com/hasanka123/BGP.git

2. Install dependencies: Maven will automatically download the dependencies specified in the pom.xml file
mvn clean install

### Running Tests ###

Command Line Execution:
mvn test
mvn test -DsuiteXmlFile=src/test/testng.xml

Viewing the Report:
After test execution, Extent Reports will be generated in the test-output folder. Open the ExtentReport.html file to view detailed test results with screenshots for any failed tests.



