#Feature: Convert visitor to member
#
#  Scenario: convert the added brand new visitor to member
#    Given I am on the Enter New Application page using the below data
#      | userName | password  | country    | region    | chapter               |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#    When I search emailid and click add and enter the below details and click save
#      | applicationDay |  | applicationMonth |  | applicationYear |  | country    |  | region    |  | chapter               |  | profession |  | speciality       |  | firstName |  | lastName |  | companyName |  | addressLine1      |  | phone      |  | paymentOption |  | membershipOption    |
#      | 9              |  | Sep              |  | 2019            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash - GB     |  | One Year Membership |
#      | 9              |  | Sep              |  | 2019            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash - GB     |  | One Year Membership |
#      | 9              |  | Sep              |  | 2019            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash - GB     |  | One Year Membership |
#      | 9              |  | Sep              |  | 2019            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash - GB     |  | One Year Membership |
#      | 9              |  | Sep              |  | 2019            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash - GB     |  | One Year Membership |
#    Then visitor is successfully converted to a member and I signout from BNI
