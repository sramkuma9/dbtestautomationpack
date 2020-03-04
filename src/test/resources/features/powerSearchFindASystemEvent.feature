@smoke1
Feature: Power Search To search for a Event

  Scenario: Power Search Find a Event scenario
    Given to search System Events

      | userName | password  | country    | region    | chapter               |
      |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When I search for a event by entering the details and click search button
      | IdHistoryType              |  | IdCountry |  | MSIdRegionsScreen |  | MSIdChaptersScreen |  | periodStartYear |  | periodStartMonth |  | periodStartDay |  | periodEndYear |  | periodEndMonth |  | periodEndDay |  | resultsPerPage |
      |  || Antarctica | |          |    |                      |  |           |  |                     | |               | |               ||              ||                  ||  10      |

    Then Reports for the System Event for the selected country displayed successfully

