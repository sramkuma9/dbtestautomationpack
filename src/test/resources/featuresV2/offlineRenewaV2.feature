Feature: offline renewal for  a member

  Scenario: verify member renewal by offline

    Given A member is added newly to the region following V2 pricing and member status is active now.

      | userName | password  | country           | region           | chapter      |
      | Bni89  | Pass1word | Antarctica | Z_RG Test Region 1 | 1 - Global Test Chapter |


    When I navigate to Operations ->Chapter ->Manage Memberships -> Manage Members. Enter firstname and last name and enter search members button following V2 pricing.

      | firstName |  | lastName  |  | periodYear |  | periodMonth |  | day |  | country    |  | region    |  | chapter   |  | membershipPeriod |  | templateType |  | option  |  | option2 |  | paymentMode |  | profession              |  | speciality         |
      | Selenium  |  |   Bni+v67006|  | 2020       |  |Aug      |  | 15  |  | Antarctica |  |Z_RG Test Region 1 |  | 1 - Global Test Chapter |  | 24 Month         |  | Organisation |  | Default |  | Active  |  | Cash     |  | Advertising & Marketing |  | Advertising Agency |

