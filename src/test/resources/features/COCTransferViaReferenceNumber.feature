@NewDataDependent
Feature: verify COC with Reference Number

  Scenario: verify COC with Reference Number
    Given I login to verify COC with Reference Number
      | userName | password  | country         | region        | chapter        |
      |TestAutomation|Pass1word|Antarctica |Ant - One|Ant - One - Chapter A|

    When I select Admin -> Region , Certificate of Credit and enter the COC Reference Number
      |firstName||lastName||cocCreditType|
     |selenium||Test+v20191128235504| | |

    Then COC transferred successfully