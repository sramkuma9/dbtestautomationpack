Feature: Create National Event

  Scenario: Create National Event on the system
    Given I navigate to homepage
      | userName                | password     | country         | region        | chapter        |
      | shanthibni+04@gmail.com | aadhira@2014 | Australia - BNI | BNI- Adelaide | Adelaide North |
    When I enter the website with National level admin login details and navigate to Tools , click Events, Manage National Events,  click Create New National Event and enter below details and click Submit button and click Publish
      | country         |  | eventType                 |  | eventName      |  | shortDescription |  | contactPerson |  | location           |  | timeZone            |  | timeZoneCountry |  | eventStartDay |  | eventStartMonth |  | eventStartYear |  | eventStartHour |  | eventStartMin |  | startTime |  | eventEndDay |  | eventEndMonth |  | eventEndYear |  | eventEndHour |  | eventEndMin |  | endTime |  | hostCountry     |  | firstRemainderSent |  | secondRemainderSent |
      | Australia - BNI |  | *Leadership Team Training |  | TestAutomation |  | Leaders Meet     |  | Neenika Dev   |  | Australia Location |  | Australia/Melbourne |  | Australia       |  | 10            |  | Oct             |  | 2020           |  | 10             |  | 15            |  | AM        |  | 30          |  | Oct           |  | 2020         |  | 11           |  | 15          |  | AM      |  | Australia - BNI |  | 3                  |  | 2                   |
    Then National Event will be created and Published successfully. Navigate to Tools menu, Events, Manage National Events . Click View National Events button and verify Event added successfully