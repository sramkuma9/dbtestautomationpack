Feature: Online Renewal features

  # This scenario is dependant on the username and password of the member login.

  Scenario: To ensure Online Renewal features for the already added members
    Given  “Allow Online Renewals” as Post-Approval only following Vpricing

      | userName | password  | country    | region    | chapter   |
      |  Bni89   | Pass1word | Antarctica | Z_RG Test Region 2|Chapter A_rename |

    When Click Renew Now button in the home page and enter the below details click Proceed to payment button following Vpricing. Enter card details and Proceed to payment

      | userName                     |  | password  |  | firstName |  | lastName   |  | emailID                      |  | country    |  | region    |  | chapter   |  | templateType |  | option  |  | option2 |  | tosVersion  |  | allowOnlineRenewals   |  | question       |  | membershipPeriod |  | templateType |  | additionalQuestions |  | payerName |  | methodOfPayment      |  | cardNumber     |  | nameOnCard |  | expriyDate |  | cvv |  | profession          |  | speciality |
      | |  | Pass1word |  | Sree   |  |vidhya |  | seleniumbni+v8009@gmail.com|  | Antarctica |  |Z_RG Test Region 2 |  | Chapter A_rename |  | Organization |  | DEFAULT |  | DEFAULT |  | May 25,2018 |  | Pre and Post Approval |  | How to improve |  | 12 Months         |  | Organisation |  | yes                 |  | Selenium  |  |Cash|  | 4111 1111 1111 |  | Selenium   |  | 06/20      |  | 123 |  | Art & Entertainment |  | Musicians  |











































      # | Bni89    | Pass1word | Antarctica | Z_RG Test Region 6 | Chapter A |
     # | admin    | Pass1word | Antarctica | Ant - Two | Chapter C |
   #   |bekigoodwin|Pass1word| Antarctica |Z_RG Test Region 1|2 - Core Group|
     # |bekigoodwin|Pass1word| Antarctica |ZZ_Region A_ren|3cnewchapter|
    #  |admin     |Pass1word  |Antarctica     |ZZ_Region A_ren |3cnewchapter |
  #  |natdir|Pass1word| Antarctica |Z_RG Test Region 6|Chapter B|

     # |globalna       |Pass1word| Antarctica |Z_RG Test Region 6|Chapter B|

   #| BrandingUser |  | Pass1word |  | Selenium  |  | branding     |  | seleniumbni+v453@gmail.com |  | Antarctica |  | Z_RG Test Region 6  |  | Chapter A |  | Organization |  | MEMBER |  | COREGROUP |  | May 25,2018 |  | Pre and Post Approval |  | How to improve |  | 24 Month         |  | yes                 |  | Selenium  |  | Cash      |  | 4111 1111 1111 |  | Selenium   |  | 06/20      |  | 123 |  | Art & Entertainment |  | Musicians  |

     # |SeleniumBni+v7010229@gmail.com  |  | Pass1word |  | Selenium  |  | Bni+v7010    |  |SeleniumBni+v7010229@gmail.com|  | Antarctica |  | Z_RG Test Region 1 |  | 2 - Core Group |  | Organization |  | DEFAULT |  | DEFAULT |  | May 25,2018 |  | Pre and Post Approval |  | How to improve |  | 12 Month       | | Organisation  |  | yes                 |  | Selenium  |  | Offline Card Payment     |  | 4111 1111 1111 |  | Selenium   |  | 06/20      |  | 123 |  | Art & Entertainment |  | Musicians  |
    #  |seleniumbni+v45003@gmail.com |  | Pass1word |  | Selenium  |  | bni+v45003  |  |seleniumbni+v45003@gmail.com |  | Antarctica |  | ZZ_Region A_ren |  | 3cnewchapter |  | Organization |  | DEFAULT |  | DEFAULT |  | May 25,2018 |  | Pre and Post Approval |  | How to improve |  | 24 Month       | | Organisation  |  | yes                 |  | Selenium  |  | Offline Card Payment     |  | 4111 1111 1111 |  | Selenium   |  | 06/20      |  | 123 |  | Art & Entertainment |  | Musicians  |
    #  |Bni011 |  | Pass1word |  | selenium  |  |Bni+v285011 |  |SeleniumBni+v285011744@gmail.com |             |  Antarctica| |Z_RG Test Region 6||Chapter B|  |   |  | DEFAULT |  | DEFAULT |  | May 25,2018 |  | Pre and Post Approval |  | How to improve |  | 24 Month       | |    |  | yes                 |  | Selenium  |  |Cash    |  | 4111 1111 1111 |  | Selenium   |  | 06/20      |  | 123 |  | Art & Entertainment |  | Musicians  |

