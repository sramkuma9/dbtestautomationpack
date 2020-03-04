@NewDataDependent
Feature: Assign roles at country level.

  Scenario: Assign roles at country level.
    Given Iam in the BNI home page, and click Admin, Country
      | userName | password  | country    | region    | chapter               |
      |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

   When I click Manage Roles and click view/allocate roles, and select Assign Role image under options against National Admin. Click Add person to role and enter EmailID, select user and click Assign Role button. Click save button
      | emailId                 |  | firstName |  | lastName    |  | role           |
      |SeleniumBni+v20200127164511@gmail.com|  |Selenium|  |Bni+v20200127164511| |Country Webmaster |

    Then Role is assigned and confirmation email is received