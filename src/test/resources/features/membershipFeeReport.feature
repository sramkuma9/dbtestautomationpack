#Feature: Membership Fee Report
#
#  Scenario: Membership Fee Report on BNI can be run and exported
#    Given User logged in as admin login, select Reports, Country, Membership Fee Report
#      | userName | password  | country    | region    | chapter               |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#    When User enters the below effective date and click Go Button
#      | effectiveDay |  | effectiveMonth |  | effectiveYear |
#      | 1            |  | Oct            |  | 2019          |
#    Then Membership Fee Report exported and verified with Database successfully