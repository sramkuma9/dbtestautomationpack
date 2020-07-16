Feature: Online Renewal features

  # This scenario is dependant on the username and password of the member login.

    Scenario: To ensure Online Renewal features for the already added members
    Given A member is added newly to the region and member status is active now. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules. Keep “Allow Online Renewals” as Post-Approval only

      | userName | password  | country    | region             | chapter   |
      #|admin     |Pass1word  |Antarctica|Ant - Two |Chapter C|
      |globalna   | Pass1word | Antarctica | Z_RG Test Region 6 | Chapter A |

  #  |natdir|Pass1word| Antarctica |Z_RG Test Region 6|Chapter B|
    When I login BNI app with Member login details and accept TOS, check latest TOS version is displayed,then click Renew Now button in the home page and enter the below details click Proceed to payment button. Enter card details and Proceed to payment

      | userName     |  | password  | | country  |  | region     |        | chapter   |  | firstName |  | lastName |  | emailID                               |   | tosVersion  |  | allowOnlineRenewals   |  | question       |  | membershipPeriod |  | additionalQuestions |  | payerName |  | methodOfPayment |  | cardNumber     |  | nameOnCard |  | expriyDate |  | cvv |  | profession          |  | speciality |
      #| SeleniumBni6 |  | Pass1word |  | Selenium  |  | Bni6     |  | SeleniumBni+v20200124165930@gmail.com |  | Antarctica |  | Ant - Two |  | Chapter C |  | Organization |  | MEMBER |  | COREGROUP |  | May 25,2018 |  | Pre and Post Approval |  | How to improve |  | 24 Month         |  | yes                 |  | Selenium  |  | Cash - GB       |  | 4111 1111 1111 |  | Selenium   |  | 06/20      |  | 123 |  | Art & Entertainment |  | Musicians  |

      | Bni89 |  | Pass1word | | Antarctica || Z_RG Test Region 6| | Chapter A |  | Selenium  |  | Bni89    |  | Seleniumbni+v6008@gmail.com |    | May 25,2018 |  | Pre and Post Approval |  | How to improve |  | One Year Membership        |  | yes                 |  | Selenium  |  | Check     |  | 4111 1111 1111 |  | Selenium   |  | 06/20      |  | 123 |  | Art & Entertainment |  | Musicians  |

    Then A confirmation message is displayed and I sign out from BNI