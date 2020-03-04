## This feature is dependant on create regional events
@NewDataDependent
Feature: Event Registration for a member via application

  Scenario: To register for a member via application
    Given user enters the BNI Home page with  Regional admin login details
      | userName | password  | country    | region    | chapter               |
      |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When Click My Network link in the home page, under Events select an event, click Add a Registration button, enter first name and last name and click search button

      | eventName      |  | name |  | country   |  | role           |  | specialNeeds |
      | Test Automation |  |Bni+v20200127140312 |  | Antarctica  |  | Vice President |  | notepad       |

    Then Click + symbol and enter special needs and click submit in Add a Registration page. Verify confirmation message