@NewDataDependent
Feature: Assign roles at region level.

  Scenario: Assign roles at region level.
    Given Iam in the BNI home page, and click Admin, Region
      | userName | password  | country    | region    | chapter |
      |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When I click Manage Roles and click view/allocate roles, and against Region wide access, click Assign role image under options. Click Add person to role and enter Email ID, select user and click Assign. Click save button

      | emailId                 |  | firstName |  | lastName    |  | role           |
       |SeleniumBni+v20200127160225@gmail.com|  |Selenium|   |Bni+v20200127160225| |Ambassador  |
    Then Role is assigned at region level and confirmation email is received