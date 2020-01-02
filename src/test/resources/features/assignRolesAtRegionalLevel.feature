Feature: Assign roles at region level.

  Scenario: Assign roles at region level.
    Given Iam in the BNI home page, and click Admin, Region
      | userName | password  | country    | region    | chapter               |
      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
    When I click Manage Roles and click view/allocate roles, and against Region wide access, click Assign role image under options. Click Add person to role and enter Email ID, select user and click Assign. Click save button
      | emailId                 |  | firstName |  | lastName    |  | role           |
      | shanthibni+28@gmail.com |  | Rithika   |  | vinoth12345 |  | Region Wide Access |
    Then Role is assigned at region level and confirmation email is received