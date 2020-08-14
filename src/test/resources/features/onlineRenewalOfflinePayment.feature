Feature: Online Renewal features

  # This scenario is dependant on the username and password of the member login.

  Scenario: To ensure Online Renewal features for the already added members
    Given A member is added newly to the region and member status is active now. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules. Keep “Allow Online Renewals” as Post-Approval only

      | userName | password  | country    | region             | chapter   |
      | Bni89 | Pass1word | Antarctica | Z_RG Test Réĝíon 4 | Chapter A |

    When I login BNI app with Member login details and accept TOS, check latest TOS version is displayed,then click Renew Now button in the home page and enter the below details click Proceed to payment button. Enter card details and Proceed to payment

      | userName                         |  | password  |  | country    |  | region             |  | chapter   |  | firstName |  | lastName  |  | emailID                          |  | tosVersion  |  | allowOnlineRenewals   |  | question       |  | membershipPeriod |  | additionalQuestions |  | payerName |  | methodOfPayment |  | cardNumber     |  | nameOnCard |  | expriyDate |  | cvv |  | profession          |  | speciality |
      | sugabni123+bindhu@gmail.com |  | Pass1word |  | Antarctica |  | Z_RG Test Réĝíon 4 |  | Chapter A |  | bindhu  |  | sundar |  | sugabni123+bindhu@gmail.com |  | May 25,2018 |  | Pre and Post Approval |  | How to improve |  | 12 Months        |  | yes                 |  | Selenium  |  | Check           |  | 4111 1111 1111 |  | Selenium   |  | 08/20      |  | 123 |  | Art & Entertainment |  | Musicians  |

    Then A confirmation message is displayed and I sign out from BNI