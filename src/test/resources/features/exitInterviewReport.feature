@smoke1
Feature: Exit Interview Report

  Scenario: Exit Interview Report on BNI can be run and exported
    Given User logged in as admin login, select Reports, HQ, Exit Interview Report
      | userName | password  | country    | region    | chapter    |
      |chitrahq| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
    When User enters the below start and end dates and click Go Button
      | exportWithoutHeaders |  | startDay |  | startMonth |  | startYear |  | endDay |  | endMonth |  | endYear |
   | No                   |  | 1      |  | Feb        |  | 2020      |  | 31      |  | Mar     |  | 2020    |

    Then Exit Interview Report exported and verified with Database successfully