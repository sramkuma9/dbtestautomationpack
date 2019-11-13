Feature: Add a prospective visitor

  Scenario: Add a Prospective Visitor
    Given user logs into BNI and navigates to home page using the below data
      | userName | password  | country    | region    | chapter               |
      | malshq   | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
      #| malshq   | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
    When I enter a valid existing email id and click search and Add button and I enter the below details and click the save button
      | emailId                              |  | applicationDay |  | applicationMonth |  | applicationYear |  | country    |  | region    |  | chapter               |  | profession |  | speciality       |  | firstName |  | lastName |  | companyName |  | addressLine1      |  | phone      |  | paymentOption |  | membershipOption    |
      | SeleniumTest20190901074115@gmail.com |  | 9              |  | Sep              |  | 2019            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Card          |  | One Year Membership |
     # | SeleniumTest20190830142840@gmail.com |  | 9              |  | Sep              |  | 2019            |  | Antarctica |  | Ant - One |  | Ant - One - Chapter A |  | BNI        |  | BNI Professional |  | Selenium  |  | Test+v   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Card          |  | One Year Membership |
    Then visitor details saved successfully
    And I successfully sign out from BNI