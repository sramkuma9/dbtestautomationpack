

    Feature: Membership cost paid via DPS Gateway

    Scenario: Membership cost paid via DPS Gateway
    Given User logged in as newly added member login

    | userName | password  | country    | region    | chapter               |
    |Bni89|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    Then click Renew Now button in home page and enter the below details. Click submit button

     | userName |  | password  |  | firstName |  | lastName             | |emailID| | tosVersion  ||  country   | | region  |  | chapter               | | allowOnlineRenewals                             |  | membershipPeriod |  | additionalQuestions |  | payerName |  | methodOfPayment   |  | cardNumber          |  | nameOnCard |  | expiryMonth |  | expiryYear |  | cvc |
     | BrandingUser   |  | Pass1word |  | Selenium  |  |  branding | |seleniumbni+v453@gmail.com| | May 25,2018 | |Antarctica ||Z_RG Test Region 6 || Chapter A | | LT Decision Only After Online Renewal Submitted |  | One Year Membership        |  | yes                 |  | Selenium  |  | Bank Card Payment |  | 4111 1111 1111 1111 |  | Selenium   |  | 07          |  | 21         |  | 369 |

    And  Member renewed successfully for the appropriate period