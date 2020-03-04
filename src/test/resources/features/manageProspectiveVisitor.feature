@NewDataDependent

Feature: Manage prospective visitors

  Scenario: To Manage visitor registration and add as a visitor
    Given I am on the manage Visitor Registration page with details below and PV details already added
     | userName   | password  | Country    | region    | chapter   |
     |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When I click Manage Prospective Visitors and search already added PV user, click edit button , check visitor attended checkbox, enter Invited By and Visited Date details and click the submit button

      | visitorInvitedBy | visitDate | visitMonth | visitYear | industry         | classification     |
      |BNI |18|Feb|2020| Architecture & Engineering|Architect|

    Then PV visitor is added as a visitor successfully
    And sign out from BNI
