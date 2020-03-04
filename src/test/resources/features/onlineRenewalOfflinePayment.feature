
Feature: Online Renewal features

  # This scenario is dependant on the username and password of the member login.

  Scenario: To ensure Online Renewal features for the already added members
    Given A member is added newly to the region and member status is active now. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules. Keep “Allow Online Renewals” as Post-Approval only

      | userName | password  | country    | region    | chapter               |
      |bekigoodwin| Pass1word | Australia |ACT - BNI Canberra|BNI Ambassador|

    When I login BNI app with Member login details and accept TOS, check latest TOS version is displayed,then click Renew Now button in the home page and enter the below details click Proceed to payment button. Enter card details and Proceed to payment

#      | userName                |  | password     |  | firstName |  | lastName |  | tosVersion  |  | allowOnlineRenewals                             |  | membershipTerm |  | companyPaidMembership |  | payerName |  | methodOfPayment   |  | cardNumber     |  | nameOnCard |  | expriyDate |  | cvv |
#      |Selenium2| | Pass1word | |Selenium2|   |Bni+630|  | May 25,2018 |  | Streamline Renewals|  | 1 Year           |  | yes                 |  | Selenium  |  | Card ン#サ& |  | 4111 1111 1111 |  | Selenium  |  | 03/20      |  | 123 |
      | userName                |  | password     |  | firstName |  | lastName |  | tosVersion  |  | allowOnlineRenewals    |       | membershipPeriod |  | additionalQuestions |  | payerName |  | methodOfPayment   | | cardNumber     |  | nameOnCard |  | expriyDate |  | cvv |
      #| SeleniumBni+v20200127175054@gmail.com| | Pass1word | |Selenium| | Bni+v20200127175054 |  | May 25,2018 |  | LT Decision Only After Online Renewal Submitted |  | 1 Year           |  | yes                 |  | Selenium  |  | Cash - GB |  | 4111 1111 1111 |  | Selenium  |  | 03/20      |  | 123 |
      |Bni01| | Pass1word | |Selenium|  | Bni | | May 25,2018 |  | Pre and Post Approval |  | 1 Year           |  | yes                 |  | Selenium  |  |Offline Card Payment |  | 4111 1111 1111 |  | Selenium  |  | 03/20      |  | 123 |

  #    Then A confirmation message is displayed and I sign out from BNI


    # | SeleniumBni+v20200127144820@gmail.com|| Pass1word | |Selenium| | Bni+v20200127144820|  | May 25,2018 |  | LT Decision Only After Online Renewal Submitted |  | 1 Year           |  | yes                 |  | Selenium  |  | Bank Card Payment |  | 4111 1111 1111 |  | Selenium  |  | 03/20      |  | 123 |
    ##
    Then A confirmation message is displayed and I sign out from BNI