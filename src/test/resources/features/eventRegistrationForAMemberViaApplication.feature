### This feature is dependant on create regional events
#Feature: Event Registration for a member
#
#  Scenario: To register for an event via application
#    Given user enters the BNI Home page with  Regional admin login details
#      | UserName                | Password     | country         | region        | chapter        |
#      #| shanthibni+32@gmail.com | aadhira@2014 | Australia - BNI | BNI- Adelaide | Adelaide North |
#      | shanthibni+43@gmail.com | aadhira@2014 | Australia - BNI | BNI- Adelaide | Adelaide North |
#    When Click My Network link in the home page, under Events select an event, click Add a Registration button, enter first name and last name and click search button
#      | eventName      |  | name |  | country   |  | role           |  | specialNeeds |
#     # | TestAutomation |  | Hema |  | Australia |  | Vice President |  | notepad       |
#      | LTT |  | Hema |  | Argentina |  | Vice President |  | notepad       |
#    Then Click + symbol and enter special needs and click submit in Add a Registration page. Verify confirmation message