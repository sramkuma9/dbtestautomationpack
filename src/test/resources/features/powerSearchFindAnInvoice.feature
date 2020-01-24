
Feature: Power Search Find an invoice

 Scenario: Power Search Find an invoice


     Given I’m in the BNI home page logged in as admin, and click Tools->Power search -> Find An Invoice
      | userName | password  | country    | region    | chapter               |
     #          #Test2
     |TestAutomation | Pass1word | Antarctica |Ant - Two | Chapter C |
#    #|TestAutomation  |  Pass1word | Antarctica |Ant - Two | Chapter C |
#     #Track
#      | automationUser | Pass1word | Antarctica | Z_RG Test Region 6| Chapter A |
#      | automationUser | Pass1word | Antarctica | Z_RG Test Region 6| Chapter A |


    When I  select “Find an Invoice” and enter the below details and click search button
     |invoiceReference|periodEndYear|periodEndMonth|periodEndDay|periodStartYear|periodStartMonth|periodStartDay|idCompany  |fromIdOrg|toIdOrg|onBehalfIdOrg|idInvoiceType|idInvoiceStatus|idPaymentType|idPaymentStatus|numberPerPage|
   #Test2
     |          |           |         |                |         |           |         |                   |         |            |           |           |             |            |   Payment Received                |       10         |
   #  |          |           |         |                |         |           |         |                   |         |            |           |             |             |            |                   |       10         |
    # |          |           |         |                |         |           |         |                   |         |            |           |             |             |            |                   |       10         |

   #track

     #  |          |           |         |                |         |           |         |                   |         |            |           |             |             |            |                   |       30         |
   #  |          |  2019         | Oct        |  21              |  2019       |  Oct       |  30       |                   |         |            |           |             |             |            |                   |       10         |
   #  |          |           |         |                |         |           |         |                   |         |            |           |             |  Credited           |            |                   |       10         |

   Then Invoice Reports for the specified person displayed successfully- verify with DB


