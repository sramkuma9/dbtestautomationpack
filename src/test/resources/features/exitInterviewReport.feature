#Feature: Exit Interview Report
#
#  Scenario: Exit Interview Report on BNI can be run and exported
#    Given User logged in as admin login, select Reports, HQ, Exit Interview Report
#      | userName | password  | country    | region    | chapter               |
#      | admin    | Pass1word | Antarctica | Ant - One | Core Group |
#    When User enters the below start and end dates and click Go Button
#      | exportWithoutHeaders |  | startDay |  | startMonth |  | startYear |  | endDay |  | endMonth |  | endYear |
#      | No                   |  | 1        |  | Oct        |  | 2018      |  | 1      |  | Oct      |  | 2019    |
#    Then Exit Interview Report exported and verified with Database successfully