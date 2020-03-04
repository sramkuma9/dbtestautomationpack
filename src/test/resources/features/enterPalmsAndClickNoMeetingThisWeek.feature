@NewDataDependent
Feature: Enter PALMS and Click No Meeting This Week

  Scenario: To view chapter PALMS summary and Click “No Meeting This Week"
    Given I enter the application with below details. Operation, Chapter, Meeting Management, Enter PALMS, Click Continue without Approving visitors button. In Enter Chapter PALMS page, select Enter Meeting Date and click Enter PALMS button
      #Test2
      | userName | password  | country    | region    | chapter               |
      |chitrahq| Pass1word| Antarctica |Z_RG Test Region 6|Chapter B|


    When I Click No Meeting This Week button at the bottom of the page, click ok on  the message box. On View Chapter PALMS summary page, Enter above meeting date

    | meetingDay |  | meetingMonth |  | meetingYear |

    |10         |  |Feb        |  |2020         |

    Then Verify Holiday for the given date is saved successfully