@smoke1
Feature: Power Search Find an Organisation

  Scenario: Power Search Find an Organisation
    Given I enter BNI home page, and select Tools->Power search->Find An Organisation
      | userName   | password  | Country    | region    | chapter   |
      |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|


    When I enter the search criteria of Organisations and select search button
      | name       | company        | orgType | orgStatus | category  | secCategory | sizeType                   | sizeValue | queryDateType  | periodYear | periodMonth | periodDate | numberPerPage |
      | |       | Country|     Active|      ||    |            |    |   |       |       | 20|
    Then Reports for the specified Organisation displayed successfully-verify with DB
