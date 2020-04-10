@NewDataDependent
Feature: Add Referral Slips via the Member Module

  Scenario: Add Referral Slips via the Member Module and ensure PALMS count is correct

    Given  With member login details login BNI
      | userName    | password     | country   | region | chapter |
      |SeleniumBni12|Pass1word| Antarctica |Ant - Two|Chapter C|


    When The Member submit Referral slip from Member Module entering Referral type, Status and other details
|memberName|firstName|lastName|companyName|referral|referralType|status|address|number|email|userName|password|
|Selenium Bni5|  |Bni17|Airtel|test    |Tier 1  |Given Your Card|addr|9456543463|seleniumbni+v56@gmail.com|SeleniumBni5|Pass1word|

    Then verify to get actual count on referral slips submitted