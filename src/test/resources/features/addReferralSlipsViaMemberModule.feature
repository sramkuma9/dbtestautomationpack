@NewDataDependent1
Feature: Add Referral Slips via the Member Module

  Scenario: Add Referral Slips via the Member Module and ensure PALMS count is correct

    Given  With member login details login BNI
      | userName | password  | country    | region             | chapter   |
      | Bni89    | Pass1word | Antarctica | Z_RG Test Region 6| Chapter A|



    When The Member submit Referral slip from Member Module entering Referral type, Status and other details
      | memberName         | firstName | lastName  | companyName | referral | referralType | status          | address | number     | email                            |
      | Selenium Bni+v30 | Selenium  | Bni+v30 |             | 12      | Tier 1       | Given Your Card | addr    | 9456543463 | SeleniumBni+v30@gmail.com |

    Then verify to get actual count on referral slips submitted












































    #|periodStartYear||periodStartMonth| |periodStartDay| |periodEndYear||periodEndMonth||periodEndDay|
  #|29||Jan       | |2020          | |20           ||Apr           | |2020|