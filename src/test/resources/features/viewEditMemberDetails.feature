@smoke1
Feature: View / Edit Member Details

  Scenario: Manage the member details - Manage Member
    Given I’m in the home page, Operations -> Region, select Manage Memberships ->Manage Members
      | userName | password  | country    | region    | Chapter               |
      |Seleniumuser1| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
    When I enter first name and last name click the Search Members button. Click Edit member button, on user profile tab, select language as Japanese and In Main profile tab,  edit company name in Japanese language and click update button
      | firstName |  | lastName |  | language |  | companyName |  | status         |
      |Selenium|   |Bni+v20200127144820 | |Japanese||おはよう       |  |Active          |

  Then Logout and login again to check edited details and status are updated