# DB Regression Test Suite:

### Set Up:
Do the following steps to set up the code to run locally:

### Input Data:
1) Enter the input data for each feature in the feature file(resources/inputFiles) 
2) Enter the config data in the config.properties(resources/properties)
3) Enter the sql in the sql.properties(resources/properties)
4) Enter the translation data in translation excel file(resources/inputFiles)

### Execution:
1) Install Ubuntu 18.04.3 LTS
2) Install Java (JRE & JDK) 11.0.6
3) Install Apache Maven 3.6.0
3) Clone the below gitlab repository:
https://gitlab.com/sramkuma/dbtestautomationpack.git
4) Run a mvn clean install for the above cloned repo in turn. This will install them into your local maven repository.
5) To run the daily job execute the below from command line
mvn surefire:test -Dtest=DailyJob
6) To run the Weekend job execute the below from command line
   mvn surefire:test -Dtest=WeekendJob




![Screenshot](dBTestAutomationArchitecture.png)