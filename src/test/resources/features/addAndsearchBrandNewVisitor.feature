@regression
Feature: Add and search brand new visitor

  Scenario: Add a brand new Visitor
    Given User navigates to BNI homepage using the below login credentials
      | userName | password  | country    | region    | chapter   |
      | malshq   | Pass1word | Antarctica | Ant - Two | Chapter C |
#      | malshq   | Pass1word | Antarctica | Ant - Two | Chapter C |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#      | malshq   | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#      | malshq   | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#      | malshq   | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
    When I enter a valid new email id and click search and create new button and I enter the below details and click the save button and search the added visitor
      | chapter   |  | region    |  | firstName |  | lastName   |  | profession |  | speciality       |  | person |  | visitDay |  | visitMonth |  | visitYear |  | title |  | companyName |  | country    |  | phone     |
      | Chapter C |  | Ant - Two |  | db        |  | selenium+v |  | BNI        |  | BNI Professional |  | BNI    |  | 22       |  | Jan        |  | 2020      |  | Mr.   |  | DBQA        |  | Antarctica |  | 123456789 |
#      | Chapter C |  | Ant - Two |  | db        |  | selenium+v |  | BNI        |  | BNI Professional |  | BNI    |  | 29       |  | Jan        |  | 2020      |  | Mr.   |  | DBQA        |  | Antarctica |  | 123456789 |
#      | Ant - One - Chapter A |  | Ant - One |  | Selenium  |  | Test+v   |  | BNI        |  | BNI Professional |  | BNI    |  | 12       |  | Aug        |  | 2019      |  | Mr.   |  | Airtel      |  | Antarctica |  | 123456789 |
#      | Ant - One - Chapter A |  | Ant - One |  | Selenium  |  | Test+v   |  | BNI        |  | BNI Professional |  | BNI    |  | 12       |  | Aug        |  | 2019      |  | Mr    |  | Airtel      |  | Antarctica |  | 123456789 |
#      | Ant - One - Chapter A |  | Ant - One |  | Selenium  |  | Test+v   |  | BNI        |  | BNI Professional |  | BNI    |  | 12       |  | Aug        |  | 2019      |  | Mr    |  | Airtel      |  | Antarctica |  | 123456789 |
#      | Ant - One - Chapter A |  | Ant - One |  | Selenium  |  | Test+v   |  | BNI        |  | BNI Professional |  | BNI    |  | 12       |  | Aug        |  | 2019      |  | Mr    |  | Airtel      |  | Antarctica |  | 123456789 |
    Then brand new visitor details saved successfully
    And sign out from BNI