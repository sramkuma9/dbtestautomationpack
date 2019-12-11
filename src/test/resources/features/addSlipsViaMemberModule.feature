#Feature: Add Slips via the Member Module
#
#  Scenario: Add Slips via the Member Module and check it appears in PALMS
#    Given member login using below details
#      | userName                | password     | country   | region | chapter |
#      | shanthibni+91@gmail.com | aadhira@2014 | Argentina | BA     | Arg CG1 |
#    When the member select Enter One to Ones from Member Module, Enter the below details and click save
#      | metWith    |  | invitedBy |  | location |  | topicsOfConversation |  | year |  | month |  | day |
#      | balika sri |  | Test Test |  | RS Puram |  | Enhancement          |  | 2019 |  | Nov   |  | 1   |
#    Then a database entry is made