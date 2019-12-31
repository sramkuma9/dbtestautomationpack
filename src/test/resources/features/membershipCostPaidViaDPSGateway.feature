#Feature: Membership cost paid via DPS Gateway
#
#  Scenario: Membership cost paid via DPS Gateway
#    Given User logged in as newly added member login
#      | User Name | Password  | country    | region    | chapter               |
#      | admin     | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#    Then click Renew Now button in home page and enter the below details. Click submit button
#      | userName |  | password  |  | firstName |  | lastName |  | tosVersion  |  | allowOnlineRenewals                             |  | membershipPeriod |  | additionalQuestions |  | payerName |  | methodOfPayment   |  | cardNumber          |  | nameOnCard    |  | expiryMonth |  | expiryYear |  | cvc |
#      | test345  |  | Pass1word |  | Stella    |  | mary     |  | May 28,2018 |  | LT Decision Only After Online Renewal Submitted |  | 2 Year           |  | yes                 |  | Shanthi   |  | Bank Card Payment |  | 4111 1111 1111 1111 |  | Selenium Test |  | 12          |  | 19         |  | 123 |
#    And  Member renewed successfully for the appropriate period