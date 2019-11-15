#Feature: Online Renewal features
#
#  # This scenario is dependant on the username and password of the member login.
#
#  Scenario: To ensure Online Renewal features for the already added members
#    Given A member is added newly to the region and member status is active now. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules. Keep “Allow Online Renewals” as Post-Approval only
#      | userName | password  | country    | region    | chapter               |
#      | admin    | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#
#    When I login BNI app with Member login details and accept TOS, check latest TOS version is displayed,then click Renew Now button in the home page and enter the below details click Proceed to payment button. Enter card details and Proceed to payment
#      | userName                |  | password     |  | firstName |  | lastName |  | tosVersion  |  | allowOnlineRenewals |  | membershipPeriod |  | additionalQuestions |  | payerName |  | methodOfPayment   |  | cardNumber     |  | nameOnCard |  | expriyDate |  | cvv |
#      | shanthibni+29@gmail.com |  | aadhira@2014 |  | Purnima   |  | Thilagam |  | May 28,2018 |  | Post-Approval Only  |  | 2 Year           |  | yes                 |  | Shanthi   |  | Bank Card Payment |  | 4111 1111 1111 |  | Shanthi    |  | 12/19      |  | 123 |
#    Then A confirmation message is displayed and I sign out from BNI