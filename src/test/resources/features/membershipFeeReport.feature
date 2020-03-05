@smoke1
Feature: Membership Fee Report

  Scenario: Membership Fee Report on BNI can be run and exported
    Given User logged in as admin login, select Reports, Country, Membership Fee Report
     | userName | password  | country    | region    | chapter               |
     |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When User enters the below effective date and click Go Button
      | effectiveDay |  | effectiveMonth |  | effectiveYear |
      | 10            |  |Feb          |  | 2020          |

    Then Membership Fee Report exported and verified with Database successfully