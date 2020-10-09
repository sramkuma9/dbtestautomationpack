
Feature: Schedule a change for a Scheme from pricing portal

  Scenario: Schedule a change for a Scheme from pricing portal

    Given user navigates to Pricing portal and Schedule a change for a Scheme from available schemes

      | userName | password  | country    | region    | chapter      |
     # |admin | Pass1word|Antarctica|Ant - Two|Chapter C|
      |admin | Pass1word|Antarctica|Ant - Two|Chapter C|

    When a user login as admin and schedule a change for the scheme in the available Schemes

      | country    || region    || chapter      ||product||template||status||sku||day||month||year||minimumFees||maximumFees||memberPrice||alumniPrice||visitorPrice||defaultPrice||orgPrice||taxable||templateToChange|
     # |Antarctica||Ant - Two||Chapter C|  |New Membership||Organization||derived||24 Month ||15||October||2020||100||300    ||110          ||  120      ||  130      ||145          | |220  |  |Yes||Chapter Status Type              |
      |Antarctica||Ant - Two|| |  |New Membership||Individual Type||current||24 Month ||23||October||2020||1000||3000    ||1100          ||  1200      ||  1300      ||1405          | |1200  |  |Yes||Chapter Status Type              |
