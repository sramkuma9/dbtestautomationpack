@NewDataDependent
Feature: Manually drop a member to trigger an instant drop

  Scenario: Manually drop a member to trigger an instant drop of a member

    Given I login using below credentials and select country, chapter and region
      | userName | password  | country         | region        | chapter        |
      |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When I select Operations,Chapter,Manage Memberships,Enter New Application. Enter EmailIF of the member and click Search button, click hyperlink under type. On Edit profile page, Select Membership Details tab and click drop button

    | emailId                 |  | dropDay |  | dropMonth |  | dropYear |  | dropType |  | dropReason  |
    |SeleniumBni+v20200127175054@gmail.com|| 25       |  | Feb      |  | 2020     |  | Terminated |  | Changed Job |

     Then I Click Certificate of Credit COC button is appeared and check COC page is opened and Member status changed as dropped and Role is Unassigned from BNI Successfully
