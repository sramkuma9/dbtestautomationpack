#Feature: Convert Prospect to Member
#
#  Scenario: Convert prospect to Member
#    Given User Login into BNI application navigates to home page using the below data
#      |userName|password|country  |region|chapter|
#
#    #Test2
#  |automation | Pass1word | Antarctica |Ant - Two | Chapter C |
##    #|TestAutomation  |  Pass1word | Antarctica |Ant - Two | Chapter C |
##     #Track
##      | automationUser | Pass1word | Antarctica | Z_RG Test Region 6| Chapter A |
##      | automationUser | Pass1word | Antarctica | Z_RG Test Region 6| Chapter A |
#    When I click Operations -Chapter -Manage Memberships and click Enter New Application, enter EmailID, click search button and click Add button
#     |emailId|| applicationDay  | | applicationMonth   |   | applicationYear  | | country   |   | region     | | chapter        |       |   profession   || speciality      |   | firstName| |   lastName |  |  addressLine1      | | phone       | |fax|| paymentOption | |  membershipOption    |
#       #Test2
#      |seleniumtest013@gmail.com|  |17              |  | Jan             |  | 2020            |  | Antarctica |   |Ant - Two |  |Chapter C |  |Event & Business Service||Event Planner|  | Selenium  |  | bni+v   |   | 77, Edward Street |  | 1234567890 ||93457894556|  | Cash          |  | One Year Membership |
##|SeleniumTest012@gmail.com|  |17              |  | Jan             |  | 2020            |  | Antarctica |  | |Ant - Two |  |Chapter C |   |Construction | |Energy Services |  | Selenium  |  | Test+v   |   | 77, Edward Street |  | 1234567890 ||93457894556|  | Cash          |  | One Year Membership |
#     #Track
#    # |SeleniumTest11@gmail.com|  |16           ||Feb                  |  |2020      |  | Antarctica | | Z_RG Test Region 6||Chapter A |   | Car & Motorcycle||Auto/Car Detailing||yahoo||test     |   |new Street        | |1334353|    |  89345234534      | |cash| | One Year Membership|
#   #  |SeleniumTest121@gmail.com|  |16           ||Feb                  |  |2020      |  | Antarctica | | Z_RG Test Region 6||Chapter B |   | Car & Motorcycle||Auto/Car Detailing||yahoo||test     |   |new Street        | |1334353|    |  89345234534      | |cash| | One Year Membership|
#    Then Prospect is added as Member successfully
#   # And sign out from BNI
