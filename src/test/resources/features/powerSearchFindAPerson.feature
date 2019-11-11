Feature: Power Search Find a person

  Scenario: Power Search Find a person
    Given I’m in the BNI home page, and click Tools->Power search
      | userName | password  | country    | region    | chapter               |
      | malshq   | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
      | malshq   | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
    When I click “Find a Person” and enter the below details and click search button
      | firstName |  | lastName |  | company |  | bniOrganisation |  | dateCriteria     |  | PeriodStartDay |  | periodStartMonth | periodStartYear |  | periodEndDay |  | periodEndMonth |  | periodEndYear |  | queryDay |  | queryMonth |  | queryYear |  | role     |  | feesSuspended |  | activeRecordsOnly |  | showRecordsWithRemarksOnly |  | deletedRecords |  | resultsPerPage |
      #| Selenium  |  | Test     |  | Airtel  |  | BNI             |  | No Date Criteria |  | 1              |  | Jan              | 2017            |  | 31           |  | Dec            |  | 2019          |  | 21       |  | Oct        |  | 2019      |  | HQ Admin |  | false         |  | false             |  | false                      |  | true           |  | 20             |
      | Selenium  |  |          |  |         |  |                 |  |                  |  |                |  |                  |                 |  |              |  |                |  |               |  |          |  |            |  |           |  |          |  |               |  |                   |  |                            |  |                |  | 20             |
    Then Reports for the specified person displayed successfully- verify with DB