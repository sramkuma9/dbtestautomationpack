@smoke12345
Feature: Power Search To search for a Event

  Scenario: Power Search Find a Event scenario
    Given to search System Events

      | userName | password  | country    | region    | chapter               |
      |Bni89|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When I search for a event by entering the details and click search button
      | IdHistoryType              |  | IdCountry |  | MSIdRegionsScreen |  | MSIdChaptersScreen |  | periodStartYear |  | periodStartMonth |  | periodStartDay |  | periodEndYear |  | periodEndMonth |  | periodEndDay |  | resultsPerPage |
      |User - Convert From Visitor|| Antarctica | |          |    |                      |  |           |  |                     | |               | |               ||              ||                  ||  10      |

    Then Reports for the System Event for the selected country displayed successfully

