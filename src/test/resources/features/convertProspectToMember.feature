
@NewDataDependent
Feature: Convert Prospect to Member

  Scenario: Convert prospect to Member
    Given User Login into BNI application navigates to home page using the below data

      | userName | password  | country    | region    | chapter     |
      |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When I click Operations -Chapter -Manage Memberships and click Enter New Application, enter EmailID, click search button and click Add button
     |emailId|| applicationDay  | | applicationMonth   |   | applicationYear  | | country   |   | region     | | chapter        |       |   profession   || speciality      |  |requestedSpeciality| | firstName| |   lastName |  |  addressLine1      | | phone       | |fax|| paymentOption | |  membershipOption    |

    |SeleniumBni2021@gmail.com|  |22 |    |Feb|      |2020| |Antarctica |   |Z_RG Test Region 6|  |Chapter A | | Finance & Insurance   ||Collections|                         |test               ||Abiel              ||William   | | 77, Edward Street |  | 1234567890 ||93457894556|  | Credit Card Payment au          |  | One Year Membership |

     Then Prospect is added as Member successfully
    And sign out from BNI
