@NewDataDependent
Feature: Amend the status of a member via 'Amend Due Date' function

  Scenario: Amend the status of an active member via 'Amend Due Date' function, Force to late the status and verify Cron update after 30 mins.
    Given I login to verify amend status of the member
    | userName | password  | country         | region        | chapter        |
    |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When I Enter EmailId of the active member and click Search button, click hyperlink under type. On Edit profile page,Select Membership Details tab and click Amend Due Date
      | emailId                 |  | amendDueDay |  | amendDueMonth |  | amendDueYear |  | lateBr |
      |SeleniumTest+v20200129161412@gmail.com|  | 10          |  | Jan          |  | 2019         |  | 1      |
    Then signout
  # Note: navigate to Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules) and change “After how many days does a member get marked as late?” as 1