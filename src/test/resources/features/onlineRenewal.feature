Feature: Online Renewal features

  # This scenario is dependant on the username and password of the member login.

  Scenario: To ensure Online Renewal features for the already added members
    Given A member is added newly to the region and member status is active now. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules. Keep “Allow Online Renewals” as Post-Approval only
      | userName | password  | country    | region    | chapter               |
      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |

    When I login BNI app with Member login details and accept TOS, check latest TOS version is displayed,then click Renew Now button in the home page and enter the below details click Proceed to payment button. Enter card details and Proceed to payment
      | userName                |  | password     |  | tosVersion  |  | allowOnlineRenewals                             |  | membershipPeriod |  | additionalQuestions |  | payerName |  | methodOfPayment   |  | cardNumber     |  | nameOnCard |  | expriyDate |  | cvv |
      | shanthibni+26@gmail.com |  | aadhira@2014 |  | May 28,2018 |  | LT Decision Only After Online Renewal Submitted |  | 2 Year           |  | yes                 |  | Shanthi   |  | Bank Card Payment |  | 4111 1111 1111 |  | Shanthi    |  | 12/19      |  | 123 |
    Then Confirm you receive confirmation message “Thank you for applying to renew your BNI membership. Your Membership Committee have been notified and will contact you when a decision has been made.” Member renewed successfully for the appropriate period and verify Email is received to the registered email ID and click the hyperlink and check Online renewal form PDF document is updated with Japanese language.
    And sign out from BNI