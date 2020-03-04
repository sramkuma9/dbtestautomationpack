@smoke1
Feature: Register a prospective visitor

  Scenario: Register a prospective visitor scenario
    Given User login details to navigate into BNI Home page

      | userName   | password  | country    | region    | chapter   |
      |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When I enter the below details and click the submit button to register a Prospective visitor

   |profession |specialty|visitDate|visitMonth|visitYear|title|  firstName| lastName |suffix|companyName|  phone  |

   | Transport & Shipping|Courier|5|Mar |2020|Dr|Selenium|Bni| Motorist|BMW| 900452961  |
#   |Personal Services|Color & Style Consultant|8|Mar |2020|Prof.|Selenium|Bni| Motorist|BMW| 900452961  |
#   | Consulting|Business Broker|22|Mar|2020|Ms|Selenium|Bni| Motorist|BMW| 900452961  |
    Then Prospective visitor details saved successfully
     And sign out from BNI


