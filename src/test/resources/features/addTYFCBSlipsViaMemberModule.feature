@NewDataDependent1
  Feature: Add TYFCB Slips via the Member Module

  Scenario: Add TYFCB Slips via the Member Module and check it appears in PALMS
    Given Login as a member with below details in AddTYFCBSlipsViaMemberModule
      | userName    | password     | country   | region | chapter |
      | Bni89    | Pass1word | Antarctica | Z_RG Test Region 6| Chapter A|


    When the member select Enter TYFCB slip from Member Module, Submit the below details
      | thankYouTo |  | referralAmount |  | businessType |  | referralType |
      |selenium Bni+v045034      |  |15 |  | New |  |Tier 3 |


