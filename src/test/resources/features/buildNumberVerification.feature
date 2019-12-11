Feature: Build Number verification

  Scenario: To verify Build Release Number
    Given I log in as admin in bniTest2 environment
      | User Name | Password  | country         | region        | chapter        |
      | admin     | Pass1word | Australia - BNI | BNI- Adelaide | Adelaide North |
    When  I verify Build Number is displayed as mentioned below
      | buildNumber               |
      | BNIConnect: Release 1.11.0 |
    Then Build Release Number is correct