@sm34

Feature: Streamline Renewal features

      # This scenario is dependant on the username and password of the member login.

    Scenario: To ensure Streamline Renewal features for the already added members
    Given A member is added newly to the region and member status is active now. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules. Keep “Allow Online Renewals” as Streamline - do NOT choose, but contact Support for implementation plan following V2 pricing

      | userName | password  | country    | region    | chapter   |
      |  Bni89   | Pass1word | Antarctica | Z_RG Test Region 1|1 - Global Test Chapter |


      When I login BNI app with Member login details then click Renew Now  button in the home page and enter the below details click Proceed to payment button following V pricing.

      | userName                     |  | password  |  | firstName |  | lastName   |  | memberEmailID                |  | templateType |  | country    |  | region    |  | chapter   |  | option  |  | option2 |  | allowOnlineRenewals                                                     |  | allowOverride |  | allowRegionOnlineRenewals |  | membershipTerm |  | payerName |  | member   |   | paymentMethod |
      | bni67006 |  | Pass1word |  | Selenium  |  |  Bni+v67006 |  |seleniumbni+v67006@gmail.com|  | Organisation |  | Antarctica |  |Z_RG Test Region 1 |  | 1 - Global Test Chapter |  | DEFAULT |  | ACTIVE  |  | Streamline - do NOT choose, but contact Support for implementation plan |  | No            |  | Streamline Renewals       |  | 24 Month       |  | Selenium  |  | Selenium |    | Cash |




























































    #  | seleniumBni+v89114@gmail.com |  | Pass1word |  | Selenium  |  | Bni+v89114 |  | seleniumBni+v89114@gmail.com |  | Organisation |  | Antarctica |  | Ant - Two |  | Chapter C |  | DEFAULT |  | ACTIVE  |  | Streamline - do NOT choose, but contact Support for implementation plan |  | No            |  | Streamline Renewals       |  | 24 Month       |  | Selenium  |  | Selenium |  | 2 Years         |  | Three         |  | Linguistic |  | Cash - GB     |

