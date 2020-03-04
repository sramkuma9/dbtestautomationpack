@NewDataDependent
Feature: Enter PALMS and Submit PALMS

  Scenario: To view chapter PALMS summary and Submit PALMS
    Given Given I enter the application with below details, Operations, Chapter, Meeting Management, Enter PALMS and click Continue without Approving visitors button. In Enter Chapter PALMS page, select Enter Meeting Date and click Enter PALMS button

  | userName | password  | country    | region    | chapter               |
  |chitrahq| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When I Click “Submit PALMS” button at the bottom of the page, click ok on the message box. Navigate to Operations,Chapter,Meeting Management,View PALMS Summary, enter the meeting date given above and click view Reports

    | meetingDay |  | meetingMonth |  | meetingYear |

    |26         |  |Jan        |  |2020         |
     Then Verify PALMS is entered in Completed Status successfully