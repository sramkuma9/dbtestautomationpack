#Feature: Membership Dues Report
#
#  Scenario: Membership Dues Report on BNI can be run and exported
#    Given User logged in as admin login, select Reports, Chapter, Membership Dues Report
#      | userName | password  | country    | region    | chapter               |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#    When User enters the below report date and click Go Button
#      | reportDay |  | reportMonth |  | reportYear |
#      | 1            |  | Oct            |  | 2019          |
#    Then Membership Dues Report exported and verified with Database successfully