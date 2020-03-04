@NewDataDependent
Feature: Enter PALMS and Discard PALMS

  Scenario: To view chapter PALMS summary and Discard PALMS
    Given User enter the application with below details, Operations,Chapter,Meeting Management,Enter PALMS,Click Continue without Approving visitors button. In Enter Chapter PALMS page, select Enter Meeting Date and click Enter PALMS button


      | userName | password  | country    | region    | chapter               |
      |chitrahq| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

   When User Click Discard PALMS button at the bottom of the page, click ok on  the message box. On View Chapter PALMS summary page, Enter the above meeting date

      | meetingDay |  | meetingMonth |  | meetingYear |
     # | 1        |  | Mar         |  | 2020        |

      |02      |  |Feb        |  |2020         |

    Then Verify Draft for the given date is saved successfully