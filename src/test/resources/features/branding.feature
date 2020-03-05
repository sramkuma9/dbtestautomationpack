@smoke1
Feature: Branding

  Scenario: Check if correct text is dispalyed when cc/bni users logs in
    Given Below user navigates to BNI homepage
      | userName | password  | country    | region    | chapter               |
     | ccna | Pass1word | Antarctica |Z_RG Test Region 6|Chapter A|
      |automationUser| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
      |chitrahq  | Pass1word | Antarctica |Z_RG Test Region 6|Chapter A|
      |automationUser| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
    Then the header text should be displayed correctly
      | concept |
      | CC      |
      | BNI     |
