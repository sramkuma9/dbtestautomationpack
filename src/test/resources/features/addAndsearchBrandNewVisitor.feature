@smoke1
Feature: Add and search brand new visitor

  Scenario: Add a brand new Visitor
    Given User navigates to BNI homepage using the below login credentials
      | userName | password  | country    | region    | chapter               |

      |chitrahq| Pass1word|  Antarctica |Z_RG Test Region 6|Chapter A|
#      |chitrahq| Pass1word| Barbados |Barbados Region|Bridgetown|
#      |chitrahq| Pass1word| Barbados |Barbados Region|Bridgetown|
#      |chitrahq| Pass1word| Barbados |Barbados Region|Bridgetown|
#      |chitrahq| Pass1word| Barbados |Barbados Region|Bridgetown|
#      |chitrahq| Pass1word| Barbados |Barbados Region|Bridgetown|
#      |chitrahq| Pass1word| Barbados |Barbados Region|Bridgetown|
#      |chitrahq| Pass1word| Barbados |Barbados Region|Bridgetown|
#      |chitrahq| Pass1word| Barbados |Barbados Region|Bridgetown|
#      |chitrahq| Pass1word| Barbados |Barbados Region|Bridgetown|
#      |chitrahq| Pass1word| Antarctica |Z_RG Test Réĝíón 3|Chapter R3A|
#      |chitrahq| Pass1word| Antarctica |Z_RG Test Réĝíón 3|Chapter R3A|
     # |chitra| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When I enter a valid new email id and click search and create new button and I enter the below details and click the save button and search the added visitor
      | chapter               |  | region    |  | firstName |  | lastName |  | profession |  | speciality       |  | person |  | visitDay |  | visitMonth |  | visitYear |  | title |  | companyName |  | country    |  | phone     |

      |Chapter A|  |Z_RG Test Region 6|  | Selenium|  | Bni+v   |  |Car & Motorcycle  ||Auto/Car Detailing|| BNI    |  | 16     |  | Feb     |  | 2020     |  | Ms.   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Bridgetown|  |Barbados Region |  | Selenium |  | Bni+mtwo  |  |Construction   ||Builder/General Contractor| | BNI    |  | 17       |  | Apr        |  | 2019     |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Bridgetown|  |Barbados Region|  | Selenium|  | Bni+mthree   |    |Employment Activities||Administrative Services|| BNI    |  | 13     |  | Mar        |  | 2019      |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |
#    |Bridgetown|  |Barbados Region|  | Selenium|  | Bni+mfour |  |Food & Beverage ||z Food & Beverage Specialist|| BNI    |  | 15      |  | May       |  | 2019      |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Bridgetown|  |Barbados Region|  | Selenium |  | Bni+mfive   |  |Repair                   ||z Repair Specialist| | BNI    |  | 10      |  | Apr        |  | 2019     |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Bridgetown|  |Barbados Region|  | Selenium|  | Bni+msix |    |Training & Coaching      ||z Training & Coaching Specialist|| BNI    |  | 8 |  | May |  | 2019      |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Bridgetown|  |Barbados Region|  | Selenium|  | Bni+mnine   |    |Employment Activities||Administrative Services|| BNI    |  | 13     |  | Mar        |  | 2019      |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Bridgetown|  |Barbados Region|  | Selenium|  | Bni+mseven  |  |Food & Beverage ||z Food & Beverage Specialist|| BNI    |  | 15      |  | May       |  | 2019      |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Bridgetown|  |Barbados Region |  | Selenium |  | Bni+meight   |  |Repair                   ||z Repair Specialist| | BNI    |  | 10      |  | Apr        |  | 2019     |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Bridgetown|  |Barbados Region|  | Selenium|  | Bni+men |    |Training & Coaching      ||z Training & Coaching Specialist|| BNI    |  | 8 |  | May |  | 2019      |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |

#      |Chapter R3A|  |Z_RG Test Réĝíón 3 |  | Selenium |  | Bni+mfive   |  |Repair                   ||z Repair Specialist| | BNI    |  | 10      |  | Apr        |  | 2019     |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Chapter R3A|  |Z_RG Test Réĝíón 3|  | Selenium|  | Bni+msix |    |Training & Coaching      ||z Training & Coaching Specialist|| BNI    |  | 8 |  | May |  | 2019      |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Chapter R3A|  |Z_RG Test Réĝíón 3 |  | Selenium |  | Bni+mfive   |  |Repair                   ||z Repair Specialist| | BNI    |  | 10      |  | Apr        |  | 2019     |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Chapter R3A|  |Z_RG Test Réĝíón 3|  | Selenium|  | Bni+msix |    |Training & Coaching      ||z Training & Coaching Specialist|| BNI    |  | 8 |  | May |  | 2019      |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |

     # |Chapter A|  |Z_RG Test Region 6 |  | Selenium |  | Bni+mfour  |   |Computer & Programming ||Computer Retailer| | BNI    |  | 16       |  | Feb        |  | 2020     |  | Ms   |  | Airtel      |  | Antarctica |  | 123456789 |


    Then brand new visitor details saved successfully
    And sign out from BNI





























    #      |Chapter A|  |Z_RG Test Region 6  |  | Member1 |  | Bni+   |   |Training & Coaching      ||Communication Coach | | BNI    |  | 16       |  | Feb        |  | 2020     |  | Mr.   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Chapter A|  |Z_RG Test Region 6 |  | Member4 |  | Bni+   |  |Repair                   ||    Appliance Repair| | BNI    |  | 2       |  | Feb        |  | 2020     |  | Mr.   |  | Airtel      |  | Antarctica |  | 123456789 |
#      |Chapter A|  |Z_RG Test Region 6 |  | Member5 |  | Bni+   |   |Computer & Programming ||Computer Retailer| | BNI    |  | 9       |  | Feb        |  | 2020     |  | Mr.   |  | Airtel      |  | Antarctica |  | 123456789 |


    #  |Chapter A|  |Z_RG Test Region 6  |  | Nine|  | Bni+   |   |Computer & Programming ||Computer Retailer| | BNI    |  | 3       |  | Feb        |  | 2019      |  | Mr.   |  | Airtel      |  | Antarctica |  | 123456789 |
     # |Chapter A|  |Z_RG Test Region 6  |  | Ten|  | Bni+   |    |Training & Coaching      ||Communication Coach | | BNI    |  | 10      |  | Feb        |  | 2019      |  | Mr.   |  | Airtel      |  | Antarctica |  | 123456789 |
  # |Chapter A|  |Z_RG Test Region 6  |  | Four|  | Bni+   |   |Repair                   ||    Appliance Repair| | BNI    |  | 27      |  | Jan        |  | 2019      |  | Mr.   |  | Airtel      |  | Antarctica |  | 123456789 |
    #  |Chapter A|  |Z_RG Test Region 6  |  | Six |  | Bni+   |   |Computer & Programming ||Computer Retailer| | BNI    |  | 3       |  | Feb        |  | 2019      |  | Mr.   |  | Airtel      |  | Antarctica |  | 123456789 |
