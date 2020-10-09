#@NewDataDependent
#Feature: COC Transfer Months to an active member
#
#  Scenario: COC Transfer Months to an active member
#    Given I login to verify COC for late member
#      | userName | password  | country         | region        | chapter        |
#      |Bni89|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
#
#    When Transfer COC as credits to another active member
#      |firstName||lastName|| memberStatus|  | dropDay |  | dropMonth |  | dropYear |  | dropType |  | dropReason  | |cocCreditType| |transferMemFirstName||transferMemLastName||day||month||year||chapter||addressLine||payerName||companyName||region||chapter||industry||classification|
#      |Selenium||Bni+v251| |Active|  |14|  |Aug|  | 2020     |  | Resigned |  |Member Relocated|    | ||Selenium| |Bni+v251||14 ||Aug||2020||Chapter A||Christoper lane||Selenium||Bni+v30||Z_RG Test Region 6 | |Chapter A| |Animals ||Veterinarian|
#
#    Then COC Transferred Months to an active member


  #check in coc table
  #user history table ..  //  select * from bni.user_history where id_user ='6377';

  #  select * from bni.certificate_of_credit where id_membership ='5822';    select * from bni.user_history where id_user =6221;