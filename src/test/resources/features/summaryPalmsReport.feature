@smoke1
Feature: Summary PALMS Report

  Scenario: Summary PALMS Report on BNI can be run and exported
    Given User logged in as admin login, select Reports, Region, Summary PALMS Report

      | userName | password  | country    | region    | Chapter               |
      |testautomation|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
    When User enters the below parameters and click Go Button

      | region   |  | startDay |  | startMonth |  | startYear |  | endDay |  | endMonth |  | endYear |  | showDroppedMembers |  | trackingPalms |
      | Z_RG Test Region 6|  | 1        |  | Feb       |  | 2020      |  | 30   |  | Mar  |  | 2020    |  | Yes         |  | No            |
      Then Summary PALMS Report exported and verified with Database successfully