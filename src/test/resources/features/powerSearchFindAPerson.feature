@smoke1
Feature: Power Search Find a person

  Scenario: Power Search Find a person
    Given I’m in the BNI home page, and click Tools->Power search
      | userName | password  | country    | region    | chapter               |
      |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
    When I click “Find a Person” and enter the below details and click search button
      | firstName |  | lastName |  | company |  | bniOrganisation |  | dateCriteria     |  | PeriodStartDay |  | periodStartMonth | periodStartYear |  | periodEndDay |  | periodEndMonth |  | periodEndYear |  | queryDay |  | queryMonth |  | queryYear |  | role     |  | feesSuspended |  | activeRecordsOnly |  | showRecordsWithRemarksOnly |  | deletedRecords |  | resultsPerPage |
      | Selenium  |  |          |  |         |  |                 |  |                  |  |                |  |                  |                 |  |              |  |                |  |               |  |          |  |            |  |           |  |  Country Webmaster           |  |               |  |                   |  |                            |  |                |  | 10             |
    Then Reports for the specified person displayed successfully- verify with DB