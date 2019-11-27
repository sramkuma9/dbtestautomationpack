Feature: View PALMS summary

  Scenario: To view chapter PALMS summary
    Given User logged in with member login, select Enter One to Ones from Member Module, Enter the below details and click save
      | userName             | password     | country | region | chapter   |
      | shanthibni@gmail.com | aadhira@2014 | India   | Baroda | BNI Vesta |
    When I Login as admin and enter the From Date and click view reports
      | userName |  | password  |  | country |  | region |  | chapter   |  | enterFromDate |  | metWith    |  | invitedBy      |  | location |  | topicsOfConversation |  | day |  | month |  | year |
      | admin    |  | Pass1word |  | India   |  | Baroda |  | BNI Vesta |  | 06-oct-2019   |  | chennai me |  | Akshitha Vivek |  | RS Puram |  | Enhancement          |  | 18  |  | Nov   |  | 2019 |
    Then palms Summary report is displayed