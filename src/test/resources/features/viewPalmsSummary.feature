Feature: View PALMS summary

  Scenario: To view chapter PALMS summary
    Given User logged in with member login, select Enter One to Ones from Member Module, Enter the below details and click save
      | userName                | password     | country   | region | chapter |
      | shanthibni+91@gmail.com | aadhira@2014 | Argentina | BA     | Arg CG1 |
    When I Login as admin and enter the From Date and click view reports
      | userName |  | password  |  | country   |  | region |  | chapter |  | enterFromDay |  | enterFromMonth |  | enterFromYear |  | metWith    |  | invitedBy |  | location |  | topicsOfConversation |  | day |  | month |  | year |  | searchString |
      | admin    |  | Pass1word |  | Argentina |  | BA     |  | Arg CG1 |  | 6            |  | Oct            |  | 2019          |  | balika sri |  | Test Test |  | RS Puram |  | Enhancement          |  | 1   |  | Nov   |  | 2019 |  | draft        |
    Then palms Summary report is displayed