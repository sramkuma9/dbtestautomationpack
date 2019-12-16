Feature: Amend the status of a member via 'Amend Due Date' function

  Scenario: Amend the status of an active member via 'Amend Due Date' function, Force to late the status and verify Cron update after 30 mins.
    Given I navigate to Enter New Application page using below login details
      | userName | password  | country         | region        | chapter        |
      | admin    | Pass1word | Australia - BNI | BNI- Adelaide | Adelaide North |
    When I Enter EmailId of the active member and click Search button, click hyperlink under type. On Edit profile page,Select Membership Details tab and click Amend Due Date
      | emailId                 |  | amendDueDay |  | amendDueMonth |  | amendDueYear |  | lateBr |
      | shanthibni+32@gmail.com |  | 15          |  | Dec           |  | 2019         |  | 1      |
    Then Check if the status is changed to 'Late'
  # Note: navigate to Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules) and change “After how many days does a member get marked as late?” as 1