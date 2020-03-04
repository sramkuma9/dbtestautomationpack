
Feature: Membership cost paid via DPS Gateway

  Scenario: Membership cost paid via DPS Gateway
    Given User logged in as newly added member login
      | User Name | Password  | country    | region    | chapter  |
     # | Seleniumuser1 | Pass1word | Antarctica |Z_RG Test Region 6|Chapter A|
|chitrahq   |  Pass1word   |           Australia |ACT - BNI Canberra|BNI Ambassador|

    Then click Renew Now button in home page and enter the below details. Click submit button

    | userName |  | password  |  | firstName |  | lastName |  | tosVersion  |  | allowOnlineRenewals                             |  | membershipPeriod |  | additionalQuestions |  | payerName |  | methodOfPayment   |  | cardNumber          |  | nameOnCard    |  | expiryMonth |  | expiryYear |  | cvc |
    |Bni05| | Pass1word | |Selenium|  |Bni05 |  | May 25,2018 |  | Pre and Post Approval|  | 1 Year           |  | yes                 |  | Selenium  |  |Online Credit Card Payment |  | 4111 1111 1111 1111 |  | Selenium  |  | 03          |  |20      |  | 123 |

   # |SeleniumBni+v20200127133418@gmail.com| | Pass1word | |Selenium|  |Bni+v20200127133418|  | May 25,2018 |  | LT Decision Only After Online Renewal Submitted |  | 1 Year           |  | yes                 |  | Selenium  |  | Bank Card Payment |  | 4111 1111 1111 1111 |  | Selenium  |  | 03          |  |20      |  | 123 |

    And  Member renewed successfully for the appropriate period