#Feature: ZEN Desk Integration
#
#  Scenario: ZEN Desk Integration
#    Given I log in as admin to check zen desk integration
#      | User Name | Password  | country    | region    | Chapter               |
#      | admin     | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#    When I select help icon  in the Home page and the page is redirected to Track and enter the track server using below login details
#      | userName    |  | password  |  | trackUrl                                          |
#      | bekigoodwin |  | Pass1word |  | https://bniconnect1491570759.zendesk.com/hc/en-us |
#    Then verify your are successfully logged into track and landed in the above link  and verify BNI Connect Support SANDBOX hyperlink is displayed at the bottom of the page