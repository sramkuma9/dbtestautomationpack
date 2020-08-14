@NewDataDependent1
Feature: Add CEU Slips via the Member Module

  Scenario: Add CEU Slips via the Member Module and check it's updated in the Database

    Given  verify CEU details with member login details

      | userName    | password     | country   | region | chapter |
      | Bni89    | Pass1word | Antarctica | Z_RG Test Region 6| Chapter A|

    When The Member select Enter CEU slip from Member Module, to check Chapter Education Units

      |BNICD  |podcast  |msp  |LTT   |AdvancedBNITraining  |other |book|
      |2         |7        |1   |4    |3     |1          |2|

    Then verify to get actual count on Chapter Education Units earned
