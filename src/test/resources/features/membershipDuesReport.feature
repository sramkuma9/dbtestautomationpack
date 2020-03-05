@smoke1
Feature: Membership Dues Report

  Scenario: Membership Dues Report on BNI can be run and exported
    Given User logged in as admin login, select Reports, Chapter, Membership Dues Report
      | userName | password  | country    | region    | chapter     |
      |chitrahq| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When User enters the below report date and click Go Button

      | reportDay |  | reportMonth |  | reportYear |
      | 1        |  | Feb    |  | 2020 |

    Then Membership Dues Report exported and verified with Database successfully