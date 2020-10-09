#change emailId suffix for every run

      Feature: bounce Status

      Scenario: bounce Status (Hard and soft bounce)

      Given login with credentials

      | userName | password  | country    | region             | chapter   |
      | Bni89    | Pass1word | Antarctica | Z_RG Test Region 6 | Chapter A |

       When I add a visitor with an EmailId  bounce Status

      | chapter   |  | region             |  | firstName |  | lastName  |  | email                              |  | profession |  | speciality   |  | language     |  | person |  | visitDay |  | visitMonth |  | visitYear |  | title |  | address   |  | companyName |  | country    |  | phone     |  | startDate |  | startMonth |  | startYear |  | endDate |  | endMonth |  | endYear |  | date |  | month |  | year |
      | Chapter A |  | Z_RG Test Region 6 |  | Selenium  |  | Bni+v02365 |  | bounce+573@simulator.amazonses.com |  | Animals    |  | Veterinarian |  | English (GB) |  | BNI    |  | 16       |  | Sep        |  | 2020      |  | Ms.   |  | PSB Nagar |  | Airtel      |  | Antarctica |  | 123456789 |  | 10        |  | Jan        |  | 2020      |  | 30      |  | Sep      |  | 2020    |  | 30   |  | Aug   |  | 2020 |


