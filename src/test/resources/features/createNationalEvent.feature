@smoke1
Feature: Create National Event

  Scenario: Create National Event on the system
    Given I navigate to homepage
      | userName | password  | country    | region    | chapter               |
      |automationUser| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

     When I enter the website with National level admin login details and navigate to Tools , click Events, Manage National Events,  click Create New National Event and enter below details and click Submit button and click Publish
      | country         |  | eventType                 |  | eventName      |  | shortDescription |  | contactPerson |  | location           |  | timeZone            |  | timeZoneCountry |  | eventStartDay |  | eventStartMonth |  | eventStartYear |  | eventStartHour |  | eventStartMin |  | startTime |  | eventEndDay |  | eventEndMonth |  | eventEndYear |  | eventEndHour |  | eventEndMin |  | endTime |  | hostCountry     | |costForMember||costForNonMember| | firstRemainderSent |  | secondRemainderSent |

      | Antarctica |  | *Leadership Team Training |  | Test Automation |  | Leaders Meet     |  | New Member |  | Antartica Test Location   |  | Antarctica/Macquarie |  | Antarctica      |  | 24           |  | Feb            |  | 2020           |  | 10             |  | 15            |  | AM        |  | 29         |  | Feb          |  | 2020         |  | 11           |  | 15          |  | AM      |  | Antarctica |      |5            ||10              | | 3                  |  | 2                   |

    Then National Event will be created and Published successfully. Navigate to Tools menu, Events, Manage National Events . Click View National Events button and verify Event added successfully