@smoke1
Feature: Power Search Find an invoice
Scenario: Power Search Find an invoice
Given I’m in the BNI home page logged in as admin, and click Tools->Power search -> Find An Invoice
   | userName | password  | country    | region    | chapter   |
   |chitrahq|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|

    When I  select “Find an Invoice” and enter the below details and click search button
     |invoiceReference|periodEndYear|periodEndMonth|periodEndDay|periodStartYear|periodStartMonth|periodStartDay|idCompany  |fromIdOrg|toIdOrg|onBehalfIdOrg|idInvoiceType|idInvoiceStatus|idPaymentType|idPaymentStatus|numberPerPage|
     |APR%|       |   |            |         |           |         |      |         |            |           |           |             |            |                |       10         |

   # |BNIW_16848%|       |   |            |         |           |         |      |         |            |           |           |             |            |   Payment Received                |       10         |
   Then Invoice Reports for the specified person displayed successfully- verify with DB


