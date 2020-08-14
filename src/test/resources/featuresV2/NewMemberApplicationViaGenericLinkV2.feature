
Feature: New Member Application via Generic link

  Scenario: New Member Application via Generic link

    Given New Member Application via Generic link via VPricing

      | userName | password  | country    | region    | chapter   |
      |  Bni89   | Pass1word | Antarctica | Z_RG Test Region 2|Chapter A_rename |

    When I Copy the Form link and Register the member via VPricing

      | NewPassword |  | ConfirmPassword |  | email                       |  | templateType |  | option  |  | option2 |  | country    |  | region    |  | chapter   |  | city    |  | meetingDay |  | applicationLanguage |  | firstName |  | lastName |  | language    |  | phoneNumber |  | addressLine1 |  | industry                                     |  | membershipTerm |  | payerName |  | member   |  | date       |  | professionalExp |  | lengthProfExp |  | background |  | appType    |  | paymentMethod |  | status |  | changePaymentType |
      | Pass1word   |  | Pass1word       |  | seleniumbni+v0307@gmail.com|  | Organization |  | DEFAULT |  | DEFAULT |  | Antarctica |  | Z_RG Test Region 2 |  | Chapter A_rename|  | Chapter A_rename |  | Wednesday  |  | ~                   |  | Gangesh   |  | Kumar   |  | English(IN) |  | 98345345654 |  | Clifton Lane |  | Advertising & Marketing - Advertising Agency |  | 12 Month       |  | Test      |  | Selenium |  | 03/06/2020 |  | 2 Years         |  | Three         |  | Linguistic |  | Online New |  |Cash      |  | Active |  |Cash        |







            # Since captcha is involved, copy paste url in GenericApplynow sheet
            #check for meeting day in PV and enter respective day




































    #  | Pass1word   |  | Pass1word       |  | seleniumbni+v85321@gmail.com|  |Organization ||DEFAULT || DEFAULT  | | Antarctica      ||Z_RG Test Region 1 | |2 - Core Group  |  | Chennai |  |Friday   |  | ~                   |  | Elizhil   |  | Kumar    |  | English(IN) |  | 98345345654 |  | Clifton Lane |    | Advertising & Marketing - Advertising Agency |  | 24 Month    |  | Test      |  | Selenium |  | 03/06/2020 |  | 2 Years         |  | Three         |  | Linguistic |  | Online New |  | Cash ン#サ&        |  | Active |  | Cash              |

    #  | Pass1word   |  | Pass1word       |  |seleniumbni+v7009@gmail.com |  |Organization ||DEFAULT || DEFAULT  | | Antarctica      ||ZZ_Region A_ren| |3cnewchapter |  | Chennai |  |Sunday   |  | ~                   |  | Elizh  |  | Kumar    |  | English(IN) |  | 98345345654 |  | Clifton Lane |    | Advertising & Marketing - Advertising Agency |  | 12 Month    |  | Test      |  | Selenium |  | 03/06/2020 |  | 2 Years         |  | Three         |  | Linguistic |  | Online New |  | Cash ン#サ&        |  | Active |  | Cash              |
     # |admin     |Pass1word  |Antarctica     |Ant - Two  |Chapter C |
    #  |bekigoodwin|Pass1word| Antarctica |Z_RG Test Region 1|2 - Core Group|
     # |admin     |Pass1word  |Antarctica     |ZZ_Region A_ren |3cnewchapter |
