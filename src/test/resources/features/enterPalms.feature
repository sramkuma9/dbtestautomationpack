Feature: Enter PALMS

  Scenario: To view chapter PALMS summary
    Given I enter the application with below details , Operations->Chapter->Meeting Management->Enter PALMS. I am on the Review Visitor Ant - One - Chapter A page
      | userName | password  | country    | region    | chapter               |
      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
    When I click continue without Approving visitors button and enter the meeting date and click Enter PALMS
      | meetingDay |  | meetingMonth |  | meetingYear |  | firstName |  | lastName |
      | 10         |  | Feb          |  | 2020        |  | Monica   |  | Daniel     |
    Then Manually enter 1 against 1-2-1  for Monica Daniel and  click Submit PALMS. In view chapter PALMS Summary page Verify 1 against Monica for 1-2-1 entered successfully