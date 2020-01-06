#Feature: Emails should be received by gmail clients
#
#  Scenario: To verify mass Emails should be received to gmail clients
#    Given User logged in as admin login and select Operations, Region. Navigate to Create Email, Email Members
#      | userName | password  | country    | region    | chapter               |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#    When Enter the below details and click Find button. Select Region Email Members and click Next button. Enter Subject, select attachment and click Send button
#      | listOfRegions |  | membersInOneOrMoreChapters |  | membersUnderThisAssistantDirector |  | membersWhoHoldThisPosition |  | membersWhoAreUnderThisAreaDirector |  | membersWhoAreInThisGeoArea |  | membersWhoHaveThisStatus                             |  | subject                   |  | emailIds                                                                       |
#      | Ant - One     |  | All                        |  | Any                               |  | Any                        |  | Any                                |  | Any                        |  | Active,Suspended,Late,Pending Member,Pending Renewal |  | Business Enhancement Meet |  | pavibni+temp029@gmail.com,vijaisiraman15+new@gmail.com,shanthibni+22@gmail.com |
#    Then Verify selected members only receive the Email