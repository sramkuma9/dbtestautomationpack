@NewDataDependent
Feature: Add Slips via the Member Module

  Scenario: Add Slips via the Member Module and check it appears in PALMS
    Given member login using below details
      | userName    | password     | country   | region | chapter |

   |Bni11|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
  |newsr15|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
    |Bni8|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|


  # |SeleniumBni8|Pass1word| Antarctica |Ant - Two|Chapter C|

    When the member select Enter One to Ones from Member Module, Enter the below details and click save
      | metWith    |  | invitedBy |  | location |  | topicsOfConversation |  | year |  | month |  | day |
      #|Rizwana Begum| |Selenium Bni+v20200124170605| |testbase|  |to meet1-1|  | 2020 |  |Feb  |  |21 |

      |Selenium Bni+v20200127171147 |  |SeleniumBni+v20200127175506|  | RS Puram |  | General Assembly || 2020 |  | Jan   |  | 8   |
|   Selenium Bni+v20200212130157||SeleniumBni+v20200212125052  || RS Puram |  | General Assembly || 2020 |  | Jan   |  | 22   |
|  SeleniumBni+v20200127171147@gmail.com | |Selenium Test+v20200217143925| |location|  |toc               | |5    |  |Feb    |  |2020  |


    Then a database entry is made
