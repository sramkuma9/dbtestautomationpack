Feature: View / Edit Member Details

  Scenario: Manage the member details - Manage Member
    Given I’m in the home page, Operations -> Region, select Manage Memberships ->Manage Members
      | userName |  | password  |  | country   |  | region    |  | Chapter               |
      | admin     |  | pass1word |  | Antartica |  | Ant - One |  | Ant - One - Chapter A |
    When I enter first name and last name click the Search Members button. Click Edit member button, on user profile tab, select language as Japanese and In Main profile tab,  edit company name in Japanese language and click update button
      | firstName |  | lastName |  | language |  | companyName |
      | aadhira    |  | kumar     |  | Japan   |  | ありがとう        |
    Then Logout and login again to check edited details are updated