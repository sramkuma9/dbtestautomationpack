@smoke1
Feature: Emails should be received by gmail clients

  Scenario: To verify mass Emails should be received to gmail clients
    Given User logged in as admin login and select Operations, Region. Navigate to Create Email, Email Members

      | userName | password  | country    | region    | chapter    |
      |chitrahq| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When Enter the below details and click Find button. Select Region Email Members and click Next button. Enter Subject, select attachment and click Send button

      | listOfRegions |  | membersInOneOrMoreChapters |  | membersUnderThisAssistantDirector |  | membersWhoHoldThisPosition |  | membersWhoAreUnderThisAreaDirector |  | membersWhoAreInThisGeoArea |  | membersWhoHaveThisStatus                             |  | subject                   |  | emailIds                                                                       |
      |Z_RG Test Region 6     |  | All                        |  | Any                               |  | Any                        |  | Any                                |  | Any                        |  | Active |  | Business Enhancement Meet |  |All|

    Then Verify selected members only receive the Email