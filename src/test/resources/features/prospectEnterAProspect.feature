      @NewDataDependent
      Feature: Enter a prospect

      Scenario: Enter a prospect
      Given User Login into BNI navigates to home page using the below data

      | userName   | password  | country    | region    | chapter   |
      | Bni89    | Pass1word | Antarctica | Z_RG Test Region 6| Chapter A|

      When I click Manage Prospects - Enter a Prospect, enter email ID and click search button. Click Add button and then enter below details and click the submit button

      | droppedMemberEmail        | droppedMemberFirstName | droppedMemberLastName | prospectIndustry         | classification | title | firstName | lastName | suffix   | companyName | prospectAddressLine1 | prospectAddressLine2 | city    | prospectState | prospectCountry | prospectZipCode | prospectPhoneNumber | fax       | contactBy     |
      |SeleniumBni2025@gmail.com|test             |test   | Finance & Insurance|Collections|Ms.|Abiel |William | Motorist|BMW|SRC Nagar|South Lane|Chennai|TN|Antarctica|603103| 900452961  |9045324567|Selenium Bni+v250|

      Then Prospect details saved successfully
      And Sign out from BNI
