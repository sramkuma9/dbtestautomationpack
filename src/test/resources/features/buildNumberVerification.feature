@smoke1
Feature: Build Number verification

  Scenario: To verify Build Release Number
    Given I log in as admin in bniTest2 environment
      | userName | password  | country    | region    | chapter  |
      |chitrahq| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
    When  I verify Build Number is displayed as mentioned below
      | buildNumber               |
      | BNIConnect: Release 1.13.0 |
    Then Build Release Number is correct