

    Feature: Create Scheme for pricing portal

    Scenario: Create Scheme for pricing portal

    Given user navigates to Pricing portal and create a new scheme

      | userName | password  | country    | region    | chapter      |
      |admin   | Pass1word | Germany|Berlin 2| Berlin - Beki! |
    #  |admin   | Pass1word | Australia - BNI | ACT - BNI Canberra | ACT Core now Active test |

    When user login as admin and enters Pricing portal
#
      | country    || region    || chapter      ||productFrom||template||sku||day||month||year||minimumFees||maximumFees||memberPrice||alumniPrice||visitorPrice||defaultPrice||orgPrice||taxable|
   | Germany    ||Berlin 2|   | | |Renewal Membership||Individual Type||24 Month ||26||September||2020||100||200    ||110          ||  120      ||  130      ||145          | |22  |  |Yes|