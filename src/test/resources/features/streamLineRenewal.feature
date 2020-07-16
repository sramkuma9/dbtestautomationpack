@NewDataDependent

Feature: Streamline Renewal features

      # This scenario is dependant on the username and password of the member login.

     Scenario: To ensure Streamline Renewal features for the already added members
     Given A member is added newly to the region and member status is active now. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules. Keep “Allow Online Renewals” as Streamline - do NOT choose, but contact Support for implementation plan

      | userName | password  | country    | region    | chapter   |
      |globalna| Pass1word| Antarctica |Z_RG Test Region 1|1 - Global Test Chapter|

     When I login BNI app with Member login details then click Renew Now button in the home page and enter the below details click Proceed to payment button.

      | userName     |  | password  |  | firstName |  | lastName |  | memberEmailID                         |  | country    |  | region    |  | chapter   |  | option |  | option2 |  | allowOnlineRenewals                                                     |  | allowOverride |  | allowRegionOnlineRenewals |  | membershipTerm |  | payerName |  | member   |  | professionalExp |  | lengthProfExp |  | background |  | paymentMethod |
      | bni67006 |  | Pass1word |  | Selenium  |  | Bni+v67006    |  | SeleniumBni+v67006@gmail.com |  | Antarctica |  | Z_RG Test Region 1 |  | 1 - Global Test Chapter |  | MEMBER |  | ACTIVE  |  | Streamline - do NOT choose, but contact Support for implementation plan |  | No            |  | Streamline Renewals       |  | 1 Year      |  | Selenium  |  | Selenium |  | 2 Years         |  | Three         |  | Linguistic |  | Cash          |

     Then Logged out from BNI





