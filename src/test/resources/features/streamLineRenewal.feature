@NewDataDependent

Feature: Streamline Renewal features

      # This scenario is dependant on the username and password of the member login.

     Scenario: To ensure Streamline Renewal features for the already added members
     Given A member is added newly to the region and member status is active now. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules. Keep “Allow Online Renewals” as Streamline - do NOT choose, but contact Support for implementation plan

      | userName | password  | country    | region    | chapter   |
      |Bni89|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

     When I login BNI app with Member login details then click Renew Now button in the home page and enter the below details click Proceed to payment button.

      | userName     |  | password  |  | firstName |  | lastName |  | memberEmailID                         |  | country    |  | region    |  | chapter   |   | allowOnlineRenewals                                                     |  | allowOverride |  | allowRegionOnlineRenewals |  | membershipTerm |   | paymentMethod ||language||payerName|
      |  vijibni+15101@gmail.com|  | Pass1word |  | viji    |  | bni1   |  | vijibni+15101@gmail.com |  | Antarctica |  |Z_RG Test Region 6||Chapter A|   | Streamline - do NOT choose, but contact Support for implementation plan |  | No   |  | Streamline Renewals       |  | 12 Months     |  | Cash          ||English (US)||Selenium|

     Then Logged out from BNI





