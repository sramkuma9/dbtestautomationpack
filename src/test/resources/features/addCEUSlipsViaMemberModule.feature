@NewDataDependent
Feature: Add CEU Slips via the Member Module

  Scenario: Add CEU Slips via the Member Module and check it's updated in the Database

    Given  verify CEU details with member login details
      | userName    | password     | country   | region | chapter |
      |SeleniumBni18|Pass1word| Antarctica |Ant - Two|Chapter C|


    When The Member select Enter CEU slip from Member Module, to check Chapter Education Units

      |BNICD  |podcast  |msp  |LTT   |AdvancedBNITraining  |other |book|

      |2           |1          |3    |4    |1        |1           |1|

    Then verify to get actual count on Chapter Education Units earned
