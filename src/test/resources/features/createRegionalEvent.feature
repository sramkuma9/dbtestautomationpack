#Feature: Create Regional Event
#
#  Scenario: Create Regional Event on the system
#    Given user navigates to homepage using below details
#      | userName                | password     | country         | region        | chapter        |
#    #  | shanthibni+04@gmail.com | aadhira@2014 | Australia - BNI | BNI- Adelaide | Adelaide North |
#        | admin | Pass1word | Australia - BNI | BNI- Adelaide | Adelaide North |
#    When user enter the website with Regional level admin login details and navigate to Tools , click Events, Manage National Events,  click Create New Regional Event and enter below details and click Submit button and click Publish
#      | country         |  | region        |  | eventType                 |  | eventName      |  | shortDescription |  | contactPerson |  | location |  | timeZone            |  | timeZoneCountry |  | eventStartDay |  | eventStartMonth |  | eventStartYear |  | eventStartHour |  | eventStartMin |  | startTime |  | eventEndDay |  | eventEndMonth |  | eventEndYear |  | eventEndHour |  | eventEndMin |  | endTime |  | firstRemainderSent |  | secondRemainderSent |
#      | Australia - BNI |  | BNI- Adelaide |  | *Leadership Team Training |  | TestAutomation |  | Leaders Meet     |  | enter enter   |  | Test     |  | Australia/Melbourne |  | Australia       |  | 10            |  | Oct             |  | 2020           |  | 10             |  | 15            |  | AM        |  | 30          |  | Oct           |  | 2020         |  | 11           |  | 15          |  | AM      |  | 3                  |  | 2                   |
#    Then Regional Event will be created and Published successfully. Navigate to Tools menu, Events, Manage Regional Events . Click View Regional Events button and verify Event added successfully