@NewDataDependent
  Feature: Add TYFCB Slips via the Member Module

  Scenario: Add TYFCB Slips via the Member Module and check it appears in PALMS
    Given Login as a member with below details in AddTYFCBSlipsViaMemberModule
      | userName    | password     | country   | region | chapter |
      |SeleniumBni7|Pass1word| Antarctica |Ant - Two|Chapter C|

   # |SeleniumBni8|Pass1word| Antarctica |Ant - Two|Chapter C|

    When the member select Enter TYFCB slip from Member Module, Submit the below details
      | thankYouTo |  | referralAmount |  | businessType |  | referralType |

      |Selenium Bni+v20200124234344|  |4 |  | New |  |Tier 1 |

    Then a database entry is made , verify to get actual number of slips
