@smoke1

Feature: Create Regional Event

  Scenario: Create Regional Event on the system
    Given user navigates to homepage using below details

      | userName | password  | country    | region    | chapter      |
      |chitrahq| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

      When user enter the website with Regional level admin login details and navigate to Tools , click Events, Manage National Events,  click Create New Regional Event and enter below details and click Submit button and click Publish
      | country         |  | region        |  | eventType                 |  | eventName      |  | shortDescription |  | contactPerson |  | location |  | timeZone            |  | timeZoneCountry |  | eventStartDay |  | eventStartMonth |  | eventStartYear |  | eventStartHour |  | eventStartMin |  | startTime |  | eventEndDay |  | eventEndMonth |  | eventEndYear |  | eventEndHour |  | eventEndMin |  | endTime ||costOfMember||costOfNonMember|  | firstRemainderSent |  | secondRemainderSent |
      | Antarctica |  | Z_RG Test Region 6 | |Director Orientation |  | Test Automation |  | Leaders Meet     |  |stella mary|  | new location  |        | Antarctica/Macquarie |  | Antarctica      |  | 25            |  | Feb             |  | 2020           |  | 10             |  | 15            |   | AM        |  | 28          |  | Feb          |  | 2020         |  | 11           |  | 15          |  | AM      | |5     ||10             |  | 3   |  | 2                   |

    Then Regional Event will be created and Published successfully. Navigate to Tools menu, Events, Manage Regional Events . Click View Regional Events button and verify Event added successfully