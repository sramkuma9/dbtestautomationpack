Feature: Online Renewal features

  # This scenario is dependant on the username and password of the member login.

    Scenario: To ensure Online Renewal features for the already added members
    Given  “Allow Online Renewals” as Post-Approval only following Vpricing

      | userName | password  | country    | region             | chapter   |
      #|admin     |Pass1word  |Antarctica|Ant - Two |Chapter C|
     # | Bni89    | Pass1word | Antarctica | Z_RG Test Region 6 | Chapter A |
      | admin    | Pass1word | Antarctica | Ant - Two | Chapter C |
  #  |natdir|Pass1word| Antarctica |Z_RG Test Region 6|Chapter B|
    When Click Renew Now button in the home page and enter the below details click Proceed to payment button following Vpricing. Enter card details and Proceed to payment

      | userName     |  | password  |  | firstName |  | lastName |  | emailID                               |  | country    |  | region    |  | chapter   |  | templateType |  | option |  | option2   |  | tosVersion  |  | allowOnlineRenewals   |  | question       |  | membershipPeriod | |templateType| | additionalQuestions |  | payerName |  | methodOfPayment |  | cardNumber     |  | nameOnCard |  | expriyDate |  | cvv |  | profession          |  | speciality |
      | SeleniumBni6 |  | Pass1word |  | Selenium  |  | Bni6     |  | SeleniumBni+v20200124165930@gmail.com |  | Antarctica |  | Ant - Two |  | Chapter C |  | Organization |  | MEMBER |  | COREGROUP |  | May 25,2018 |  | Pre and Post Approval |  | How to improve |  | 12 Month S       | | Organisation  |  | yes                 |  | Selenium  |  | Offline Card Payment     |  | 4111 1111 1111 |  | Selenium   |  | 06/20      |  | 123 |  | Art & Entertainment |  | Musicians  |

      #| BrandingUser |  | Pass1word |  | Selenium  |  | branding     |  | seleniumbni+v453@gmail.com |  | Antarctica |  | Z_RG Test Region 6  |  | Chapter A |  | Organization |  | MEMBER |  | COREGROUP |  | May 25,2018 |  | Pre and Post Approval |  | How to improve |  | 24 Month         |  | yes                 |  | Selenium  |  | Cash      |  | 4111 1111 1111 |  | Selenium   |  | 06/20      |  | 123 |  | Art & Entertainment |  | Musicians  |

