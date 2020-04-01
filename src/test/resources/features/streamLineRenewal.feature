Feature: Streamline Renewal features

  # This scenario is dependant on the username and password of the member login.

  Scenario: To ensure Streamline Renewal features for the already added members
    Given A member is added newly to the region and member status is active now. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules. Keep “Allow Online Renewals” as Streamline - do NOT choose, but contact Support for implementation plan

      |userName | password  | country    | region    | chapter               |
      |admin|Pass1word|Antarctica|Ant - Two|Chapter C|

    When I login BNI app with Member login details then click Renew Now button in the home page and enter the below details click Proceed to payment button.

     | userName  |  | password     |   | lastName |  |memberEmailID  || allowOnlineRenewals    |  |allowOverride| | allowRegionOnlineRenewals    |  |membershipTerm|  | payerName ||member|   |professionalExp| |lengthProfExp| |background| |paymentMethod|

     |Bni010| | Pass1word |  |Bni+v010| |SeleniumBni+v010@gmail.com||Streamline - do NOT choose, but contact Support for implementation plan| |No| |Streamline Renewals| | 2 Year|  | Selenium  |   | Selenium  | |2 Years||Three | |Linguistic| |Cash|
#Bni+v012
    # applied by active member or late member

    Then Logged out from BNI


#Bni41


