#Feature: Add a prospective visitor
#
#  # This scenario is dependent on the email id of the existing individual / visitor
#
#  Scenario: Add a Prospective Visitor
#    Given user logs into BNI and navigates to home page using the below data
#      | userName | password  | country    | region    | chapter               |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#    When I enter a valid existing email id and click search and Add button and I enter the below details and click the save button
#      | emailId                        |  | applicationDay |  | applicationMonth |  | applicationYear |  | country    |  | region    |  | chapter               |  | profession |  | speciality       |  | firstName |  | lastName |  | companyName |  | addressLine1      |  | phone      |  | paymentOption |  | membershipOption    |
#      | menubnitester+toggle@gmail.com |  | 9              |  | Sep              |  | 2019            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash - GB     |  | One Year Membership |
#      | meenabni25+opsdrag@gmail.com   |  | 9              |  | Sep              |  | 2019            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash - GB     |  | One Year Membership |
#      | meenabni25+pvplus@gmail.com    |  | 9              |  | Sep              |  | 2019            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash - GB     |  | One Year Membership |
#      | pavibni+pennypending@gmail.com |  | 9              |  | Sep              |  | 2019            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash - GB     |  | One Year Membership |
#      | zz-04d09bce@bni.com            |  | 9              |  | Sep              |  | 2019            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash - GB     |  | One Year Membership |
#    Then visitor details saved successfully
#    And I successfully sign out from BNI