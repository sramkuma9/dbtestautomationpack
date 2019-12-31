#Feature: Assign roles at country level.
#
#  Scenario: Assign roles at country level.
#    Given Iam in the BNI home page, and click Admin, Country
#      | userName | password  | country    | region    | chapter               |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#    When I click Manage Roles and click view/allocate roles, and select Assign Role image under options against National Admin. Click Add person to role and enter EmailID, select user and click Assign Role button. Click save button
#      | emailId                 |  | firstName |  | lastName    |  | role           |
#      | shanthibni+28@gmail.com |  | Rithika   |  | vinoth12345 |  | National Admin |
#    Then Role is assigned and confirmation email is received