
Feature: Convert visitor to member

  Scenario: convert the added brand new visitor to member
    Given I am on the Enter New Application page using the below data
      | userName | password  | country    | region    | chapter               |

#      |chitrahq| Pass1word| Antarctica |Z_RG Test Réĝíón 3|Chapter R3A|
#      |chitrahq| Pass1word| Antarctica |Z_RG Test Réĝíón 3|Chapter R3A|
#      |chitrahq| Pass1word| Antarctica |Z_RG Test Réĝíón 3|Chapter R3A|
#      |chitrahq| Pass1word| Antarctica |Z_RG Test Réĝíón 3|Chapter R3A|
#      |chitrahq| Pass1word| Antarctica |Z_RG Test Réĝíón 3|Chapter R3A|
#      |chitrahq| Pass1word| Antarctica |Z_RG Test Réĝíón 3|Chapter R3A|
#      |chitrahq| Pass1word| Antarctica |Z_RG Test Réĝíón 3|Chapter R3A|
#      |chitrahq| Pass1word| Antarctica |Z_RG Test Réĝíón 3|Chapter R3A|

    |chitrahq| Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
#      |SeleniumUser1| Pass1word| Antarctica |Z_RG Test Region 6|Chapter B|
#FiveBni+20200214115117@gmail.com
    When I search emailid and click add and enter th below details and click save
      | applicationDay |  | applicationMonth |  | applicationYear |  | country    |  | region    |  | chapter               |  | profession |  | speciality       | |requestedSpeciality| | firstName |  | lastName |  | companyName |  | addressLine1      |  | phone      |  | paymentOption |  | membershipOption    |
    |19             |  |Feb            |  | 2020          |  | Antarctica |     |Z_RG Test Region 6||Chapter A|  |Car & Motorcycle  ||z Car & Motorcycle Specialist||gearBox Mechanism| | Selenium|  | Bni+v  |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash    |  | One Year Membership |
    # |6            |  |Feb            |  | 2020           |  | Antarctica |    |Z_RG Test Réĝíón 3||Chapter R3A| |Construction   ||Builder/General Contractor|  | Selenium|  | Bni+mtwo   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash    |  | One Year Membership |
    #  |6             |  |Mar           |  | 2019           |  | Antarctica |     |Z_RG Test Réĝíón 3||Chapter R3A|   |Employment Activities||Administrative Services|| Selenium|  | Bni+three  |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash    |  | One Year Membership |
#      |10           |  |Apr            |  | 2020           |  | Antarctica |    |Z_RG Test Réĝíón 3||Chapter R3A| |Employment Activities||Administrative Services|  | Selenium|  | Bni+mfour   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash    |  | One Year Membership |
#      |13             |  |Feb           |  | 2019           |  | Antarctica |     |Z_RG Test Réĝíón 3||Chapter R3A|  |Food & Beverage ||z Food & Beverage Specialist|| Selenium|  | Bni+mfive  |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash    |  | One Year Membership |
#      |8             |  |May            |  | 2020           |  | Antarctica |    |Z_RG Test Réĝíón 3||Chapter R3A| |Construction   ||Builder/General Contractor|  | Selenium|  | Bni+msix  |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash    |  | One Year Membership |
#      |16             |  |Jan            |  | 2019           |  | Antarctica |     |Z_RG Test Réĝíón 3||Chapter R3A|   |Employment Activities||Administrative Services|| Selenium|  | Bni+seven |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash    |  | One Year Membership |
#      |13            |  |Mar            |  | 2020           |  | Antarctica |    |Z_RG Test Réĝíón 3||Chapter R3A| |Employment Activities||Administrative Services|  | Selenium|  | Bni+meight   |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash    |  | One Year Membership |
#      |17             |  |Apr           |  | 2019           |  | Antarctica |     |Z_RG Test Réĝíón 3||Chapter R3A| |Repair                   ||z Repair Specialist|| Selenium|  | Bni+mnine  |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash    |  | One Year Membership |
#      |15            |  |May          |  | 2020           |  | Antarctica |    |Z_RG Test Réĝíón 3||Chapter R3A|  |Training & Coaching      ||z Training & Coaching Specialist| | Selenium|  | Bni+mten |  | Airtel      |  | 77, Edward Street |  | 1234567890 |  | Cash    |  | One Year Membership |





    Then visitor is successfully converted to a member and I signout from BNI
