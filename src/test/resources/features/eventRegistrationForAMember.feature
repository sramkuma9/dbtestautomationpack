# This feature is dependant on create regional events
@NewDataDependent
Feature: Event Registration for a member

  Scenario: To register for a member
    Given BNI Home page entered with below login details
      | userName     | password  | country    | region    | Chapter  |

      |SeleniumBni+v20200127164511@gmail.com| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|



    When Click My Network link in the home page, under Events select an event, click Register Me. On Register event page enter payment option click submit button

      | eventName      |  | role   |  | paymentOption                      |
      |Test Automation20200206145753 |  | Member |  | Collected at Venue (if applicable) |
     # | Test Automation|  | President |  | Collected at Venue (if applicable) |

    Then Verify Email is received in registered Email account