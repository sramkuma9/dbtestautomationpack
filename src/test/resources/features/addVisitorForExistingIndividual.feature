@smoke1
Feature: Add a prospective visitor

 # This scenario is dependent on the email id of the existing individual / visitor

  Scenario: Add a Prospective Visitor
   Given user logs into BNI and navigates to home page using the below data
     | userName | password  | country    | region    | chapter    |
     |testautomation| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
#     |SeleniumBni+v20200127133418@gmail.com| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

  When I enter a valid existing email id and click search and Add button and I enter the below details and click the save button
  | emailId                        |  | applicationDay |  | applicationMonth |  | applicationYear |  | country    |  | region    |  | chapter               |  | profession |  | speciality       | |requestedSpeciality| | firstName |  | lastName |  | companyName |  | addressLine1      |  | phone      |  | paymentOption |  | membershipOption    |

#      | SeleniumBni+v20200127175908@gmail.com |  | 19              |  | Jan              |  | 2020            |   | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash - GB     |  | One Year Membership |
#      |SeleniumBni+v20200127172100@gmail.com |  | 9              |  | Jan              |  | 2020            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash - GB     |  | One Year Membership |
#      |SeleniumTest+v20200129165123@gmail.com|  | 21             |  | Feb              |  | 2020            |   | Antarctica |  | Z_RG Test Region 6 |  |Chapter A |  | Agricultural|  | Water Pumping and Filtration |  | Selenium  |  | Bni+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash |  | One Year Membership |


  |SeleniumBni+20200214113341@gmail.com|  | 10            |  | Feb              |  | 2020            |   | Antarctica |  | Z_RG Test Region 6 |  |Chapter A |  | Advertising & Marketing|  |Advertising Agency| |test     | | Selenium  |  | Bni+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash |  | One Year Membership |


 Then visitor details saved successfully
   And I successfully sign out from BNI