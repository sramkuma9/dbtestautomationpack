
Feature: View Scheme from pricing portal

  Scenario: View Scheme from pricing portal

    Given user navigates to Pricing portal and view the available schemes

      | userName | password  | country    | region    | chapter      |
      |sel112  | Pass1word|Antarctica|Ant - Two|Chapter C|

    When user with any role for access logins the BNI Connect will be able to view the available Schemes

      | country    || region    || chapter      ||userName||product||template||status||sku||day||month||year||userRole|
     |Antarctica||Ant - Two||Chapter C| |sel112| |Renewal Membership||Organization||derived||24 Month ||15||September||2020||ChapterLevel|