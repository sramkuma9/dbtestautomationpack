Feature: offline renewal for  a member

      Scenario: verify member renewal by offline
      Given A member is added newly to the region following V2 pricing and member status is active now.
      | userName | password  | country    | region             | chapter   |

      # |admin     |Pass1word  |CC Test Country|Automation |CC CoreGroup|
     # | Bni89    | Pass1word | Antarctica | Z_RG Test Region 6 | Chapter A |
      | admin    | Pass1word | Antarctica | Ant - Two | Chapter C |
     When I navigate to Operations ->Chapter ->Manage Memberships -> Manage Members. Enter firstname and last name and enter search members button following V2 pricing.

      | firstName |  | lastName |  | periodYear |  | periodMonth |  | day |  | membershipPeriod    |  | paymentMode |  | profession              |  | speciality         |
    #  | Selenium  |  | Bni+v250 |  | 2020       |  | Jul         |  | 11  |  | One Year Membership |  | Cash        |  | Advertising & Marketing |  | Advertising Agency |
      | Selenium  |  | Bni22 |  | 2020       |  | Jul         |  | 17  |  | 12 Month S |  | Cash - GB       |  | Advertising & Marketing |  | Advertising Agency |



