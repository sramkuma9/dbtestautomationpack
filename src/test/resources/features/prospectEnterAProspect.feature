@NewDataDependent
Feature: Enter a prospect

  Scenario: Enter a prospect
    Given User Login into BNI navigates to home page using the below data
      | userName   | password  | country    | region    | chapter   |
      |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
      #|testautomation|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
    When I click Manage Prospects - Enter a Prospect, enter email ID and click search button. Click Add button and then enter below details and click the submit button
      | droppedMemberEmail        | droppedMemberFirstName | droppedMemberLastName | prospectIndustry         | classification | title | firstName | lastName | suffix   | companyName | prospectAddressLine1 | prospectAddressLine2 | city    | prospectState | prospectCountry | prospectZipCode | prospectPhoneNumber | fax       | contactBy     |
    #  |SeleniumBni013@gmail.com|test             |test   | Computer & Programming|App Developer|Mr.|Joseph            |Charlie   | Motorist|BMW|SRC Nagar|South Lane|Chennai|TN|India|603103| 900452961  |9045324567|SVK Vivek|
      |SeleniumBni2021@gmail.com|test             |test   | Finance & Insurance|Collections|Ms|Abiel           |William   | Motorist|BMW|SRC Nagar|South Lane|Chennai|TN|India|603103| 900452961  |9045324567|SVK Vivek|

    Then Prospect details saved successfully
    And Sign out from BNI
