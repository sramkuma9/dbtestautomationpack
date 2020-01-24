#Feature: Enter a prospect
#
#  Scenario: Enter a prospect
#    Given User Login into BNI navigates to home page using the below data
#      | userName   | password  | country    | region    | chapter   |
# #          #Test2
#      | automation | Pass1word | Antarctica | Ant - Two | Chapter C |
#
##     #Track
##      | automationUser | Pass1word | Antarctica | Z_RG Test Region 6| Chapter A |
#
#    When I click Manage Prospects - Enter a Prospect, enter email ID and click search button. Click Add button and then enter below details and click the submit button
#      | droppedMemberEmail        | droppedMemberFirstName | droppedMemberLastName | prospectIndustry         | classification | title | firstName | lastName | suffix   | companyName | prospectAddressLine1 | prospectAddressLine2 | city    | prospectState | prospectCountry | prospectZipCode | prospectPhoneNumber | fax       | contactBy     |
# #Test2
#      | seleniumtest013@gmail.com | test                   | test                  | Event & Business Service | Event Planner  | Mr.   | Giri      | tharan   | Motorist | BMW         | SRC Nagar            | South Lane           | Chennai | TN            | India           | 603103          | 900452961           | 987664567 | Aadhira Vivek |
##|SeleniumTest012@gmail.com|test             |test   |Event & Business Service|Event Planner|Mr.|Stephen             |charles   | Motorist|BMW|SRC Nagar|South Lane|Chennai|TN|India|603103| 900452961  |BNM4567|Larry Late|
# #Track
##|SeleniumTest10@gmail.com|test             |test   | Legal & Accounting|Family Law|Mrs.|Sneka               |priya   | Motorist|BMW|SRC Nagar|North Lane|Chennai|TN|India|603103| 900452961  |BNM4567|stella mary|
#
#
#    Then Prospect details saved successfully
#    And Sign out from BNI
