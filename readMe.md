# DB Regression Test Suite:

### Set Up:
Do the following steps to set up the code to run locally:

### Input Data:
1) Enter the input data for each feature in the feature file(resources/inputFiles) 
2) Enter the config data in the config.properties(resources/properties)
3) Enter the sql in the sql.properties(resources/properties)
4) Enter the translation data in translation excel file(resources/inputFiles)

### Execution:
1) Install Java 11 locally
2) Install maven
3) Clone the below gitlab repository:
https://gitlab.com/sramkuma/dbtestautomationpack.git
4) Run a mvn clean install for the above cloned repo in turn. This will install them into your local maven repository and execute the tests.




![Screenshot](dBTestAutomationArchitecture.png)