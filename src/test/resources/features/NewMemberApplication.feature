#This flow is based on settings in DB - verify DB (Activate wireguard before executing)

Feature: New Member Application
  Scenario: To ensure New Member Application flow is applied to the visitor

    Given A visitor is added newly to the region

      | userName | password  | country    | region    | chapter               |
      |SeleniumBni+v68@gmail.com|Pass1word| CC Test Country |CC - Test Region|CC Directors|

    When I login BNI app with Member login details and accept TOS, check latest TOS version is displayed,then click Renew Now button in the home page and enter the below details click Proceed to payment button.

       |NewPassword||ConfirmPassword||language|   |membershipTerm|  | payerName ||member|  |date| |professionalExp| |lengthProfExp| |background||appType||paymentMethod||status||changePaymentType|
       | Pass1word          ||Pass1word|      |English (GB)| | 2 Year|  | Selenium  |   | Selenium  | |03/04/2020||2 Years||Three | |Linguistic|   |Online New|   |Cash||Active||Cash             |







#
#      |userName||password||firstName||lastName||membershipTerm|  | companyPaidMembership |  | payerName ||member||radioButton|
#
#      | SeleniumBni10| | Pass1word | |Selenium| |  Bni+v20200124163918|   | 1 Year           |  | yes                 |  | Selenium  |  | Bank Card Payment |  | 4111 1111 1111 |  | Selenium  |  | 03/20      |  | 123 |


