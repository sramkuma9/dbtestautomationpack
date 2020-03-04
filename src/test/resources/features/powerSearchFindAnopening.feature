@smoke1
Feature: Power Search Find An Opening

  Scenario: Power Search Find An Opening

    Given I’m in the BNI home page, and click Tools, Power search , Find an Opening
      | userName       | password  | country    | region    | chapter   |
      |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When I select the search criteria as profession and click search button
      | category         |  | secCategory             |  | city |  | dayOfWeek |
      |Car & Motorcycle  ||Auto/Car Sales| |All ||  |

        Then opening for the specified speciality displayed successfully