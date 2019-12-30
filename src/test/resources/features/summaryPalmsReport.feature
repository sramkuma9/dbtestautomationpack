#Feature: Summary PALMS Report
#
#  Scenario: Summary PALMS Report on BNI can be run and exported
#    Given User logged in as admin login, select Reports, Region, Summary PALMS Report
#      | userName | password  | country    | region    | chapter               |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#    When User enters the below parameters and click Go Button
#      | region   |  | startDay |  | startMonth |  | startYear |  | endDay |  | endMonth |  | endYear |  | showDroppedMembers |  | trackingPalms |
#      | Ant - One |  | 1        |  | Oct        |  | 2018      |  | 1      |  | Oct      |  | 2019    |  | Yes                 |  | No            |
#    Then Summary PALMS Report exported and verified with Database successfully