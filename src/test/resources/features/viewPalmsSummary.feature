#Feature: View PALMS summary
#
#  Scenario: To view chapter PALMS summary
#    Given User logged in with member login
#      | userName                | password     | country   | region | chapter |
#      | shanthibni+91@gmail.com | aadhira@2014 | Argentina | BA     | Arg CG1 |
#    When I select Enter One to Ones from Member Module, Enter the below details and click save
#      | metWith    |  | invitedBy |  | location |  | topicsOfConversation |  | year |  | month |  | day |
#      | balika sri |  | Test Test |  | RS Puram |  | Enhancement          |  | 2019 |  | Nov   |  | 1   |
#    Then palms Summary report is displayed, check via DB