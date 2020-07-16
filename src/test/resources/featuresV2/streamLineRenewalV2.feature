

Feature: Streamline Renewal features

      # This scenario is dependant on the username and password of the member login.

    Scenario: To ensure Streamline Renewal features for the already added members
    Given A member is added newly to the region and member status is active now. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules. Keep “Allow Online Renewals” as Streamline - do NOT choose, but contact Support for implementation plan following V2 pricing

      | userName | password  | country    | region    | chapter   |
     # | admin    | Pass1word | India   | Chènnai CBD (A) | CG 2    |

      | admin    | Pass1word | Antarctica | Ant - Two | Chapter C |
    When I login BNI app with Member login details then click Renew Now button in the home page and enter the below details click Proceed to payment button following V2 pricing.

      | userName     |  | password  |  | firstName |  | lastName |  | memberEmailID                         | |templateType | | country    |  | region    |  | chapter   |  | option |  | option2 |  | allowOnlineRenewals                                                     |  | allowOverride |  | allowRegionOnlineRenewals |  | membershipTerm |  | payerName |  | member   |  | professionalExp |  | lengthProfExp |  | background |  | paymentMethod |
      | bni8356 |  | Pass1word |  | Selenium  |  | Bni+hb8356     |  | SeleniumBni+v814@gmail.com |           |Organisation| | Antarctica |  | Ant - Two |  | Chapter C |  | MEMBER |  | ACTIVE  |  | Streamline - do NOT choose, but contact Support for implementation plan |  | No            |  | Streamline Renewals       |  | 24 Month S      |  | Selenium  |  | Selenium |  | 2 Years         |  | Three         |  | Linguistic |  | Cash - GB          |
     # | hemaO |  | Pass1word |  | Selenium  |  | Bni7     |  | SeleniumBni+v56002@gmail.com |  | Antarctica |  | Z_RG Test Region 1 |  | 1 - Global Test Chapter |  | MEMBER |  | ACTIVE  |  | Streamline - do NOT choose, but contact Support for implementation plan |  | No            |  | Streamline Renewals       |  | One Year Membership       |  | Selenium  |  | Selenium |  | 2 Years         |  | Three         |  | Linguistic |  | Cash          |





