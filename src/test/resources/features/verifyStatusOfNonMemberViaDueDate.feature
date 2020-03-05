@NewDataDependent
Feature: Amend the status of a member via 'Amend Due Date' function

  Scenario: Amend the status of an active member via 'Amend Due Date' function, Force to late the status and verify Cron update after 30 mins.
    Given I navigate to Enter New Application page
      | userName | password  | country         | region        | chapter        |
      |Seleniumuser1| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
     # |Bni+v20200127154647|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
    When I Enter EmailId of the active member and click Search button and click hyperlink under type. On Edit profile page,Select Membership Details tab and check amend due date
      | status |
      | Late   |
    Then Signout BNI