@sm12

Feature: New Member Application following V2 pricing

  Scenario: To ensure New Member Application flow is applied to the member following V2 pricing

    Given A new Pricing version

      | userName    | password  | country    | region             | chapter   |
      |  Bni89   | Pass1word | Antarctica | Z_RG Test Region 1|1 - Global Test Chapter |


    When I login  BNI and add a  Member using NMA process  and enter the below details click Proceed to payment button following new pricing

      | firstName |  | lastName |  | country    |  | region             |  | chapter   |  | NewPassword |  | ConfirmPassword |  | language    |  | option  |  | option2 |  | templateType        |  | membershipTerm |  | payerName |  | member   |  | appType    |  | paymentMethod |  | status |  | changePaymentType |
      | Selenium  |  | Bni+v200346 |  | Antarctica |  | Z_RG Test Region 1 |  | Chapter A |  | Pass1word   |  | Pass1word       |  | German (CH) |  | VISITOR |  | ACTIVE  |  | Chapter Status Type |  | 12 Month       |  | Selenium  |  | Selenium |  | Online New |  | Cash         |  | Active |  | Cash            |

   # | Selenium  |  | bni+v0301  |  | Antarctica |  | Z_RG Test Réĝíon 4 ||Chapter A|  | Pass1word   |  | Pass1word       |  | German (CH) |  | VISITOR |  | ACTIVE  |  | Chapter Status Type |  | 12 Months      |  | Selenium  |  | Selenium |  | Online New |  | Cash ン#サ&         |  | Active |  | Cash ン#サ&            |

       #This flow is based on settings in DB - verify DB (Activate wireguard before executing)
      #Check Category version in BR is set to 2 ( UPDATE bni.org_business_rule SET rule_value = '2' where id_org = '653' and id_business_rule = '36';)

























     # |Selenium|         |Bni+v  ||Antarctica ||Z_RG Test Region 6  ||Chapter A||Individual Type||VISITOR||ACTIVE|| Pass1word          ||Pass1word|      |German (CH)||Chennai||Monday| |One Year Membership|  | Selenium  |   | Selenium  | |03/07/2020||2 Years||Three | |Linguistic|   |Online New|   |Cash||Active||Cash             ||Yes||Yes||No            |



 # |Selenium|         |bni+v0340 ||Antarctica ||ZZ_Region A_ren ||3cnewchapter|| Pass1word          ||Pass1word| |German (CH)|  |DEFAULT ||DEFAULT |  |Organization| |24 Month|  | Selenium  |   | Selenium  | |03/07/2020||2 Years||Three | |Linguistic|   |Online New|   |Cash ン#サ&||Active||Cash ン#サ&            ||Yes||Yes||No            |
  #   |Selenium|         |bni+v6582   ||Bulgaria ||Bankso  ||Chapter One| |Pass1word          ||Pass1word|      |German (CH)||VISITOR||ACTIVE ||Individual Type| |12 Month  |  | Selenium  |   | Selenium  | |03/07/2020||2 Years||Three | |Linguistic|   |Online New|   |Cash - GB||Active||Cash             ||Yes||Yes||No            |

    #  |Selenium|         |bni+v345345353|| Antarctica   ||Z_RG Test Réĝíón 3  ||CG3Scheme|| Pass1word          ||Pass1word| |German (CH)|  |VISITOR||ACTIVE |  |Individual Type| |12 Months|  | Selenium  |   | Selenium  | |03/07/2020||2 Years||Three | |Linguistic|   |Online New|   |Cash||Active||Cash            ||Yes||Yes||No            |

  #  |admin     |Pass1word  |Bulgaria     |Bankso  |Chapter One |
     # |bekigoodwin|Pass1word| Antarctica |Z_RG Test Region 1|2 - Core Group|
    #  |bekigoodwin|Pass1word| Antarctica |ZZ_Region A_ren|3cnewchapter|
     # |globalna    |Pass1word  | Antarctica |Z_RG Test Réĝíón 3|CG3Scheme|


#UPDATE bni.org_business_rule SET final_rule_value = 'Yes'  where id_org = '715' and id_business_rule = '37';
#UPDATE bni.org_business_rule SET rule_value = 'Yes'  where id_org = '715' and id_business_rule = '37';
#select * from bni.org_business_rule where id_org = '715' and id_business_rule = '37';
#  UPDATE bni.org_business_rule SET final_rule_value = '2'  where id_org = '44' and id_business_rule = '36';
#  select * from bni.org_business_rule where id_org = '44' and id_business_rule = '36';


   # |Selenium|         |Bni+v8008  ||Spain (CNM) ||Alicante (Cerrada) ||BNI Albatera||Organization||VISITOR||ACTIVE|| Pass1word          ||Pass1word|      |German (CH)||Chennai||Monday| |12 Month S |  | Selenium  |   | Selenium  | |03/07/2020||2 Years||Three | |Linguistic|   |Online New|   |Cash - GB||Active||Cash             ||Yes||Yes||No            |

  # |Selenium|         |  bni+v9023  ||Antarctica ||Z_RG Test Region 6  ||Chapter A| | Pass1word          ||Pass1word| |German (CH)| |VISITOR||ACTIVE||Organisation| | 年 - IN(Re-edit)|       | Selenium  |   | Selenium  | |03/07/2020||2 Years||Three | |Linguistic|   |Online New|   |Cash||Active||Cash             ||Yes||Yes||No            |

 #  |Bni03|Pass1word| Antarctica |Z_RG Test Region 6|Chapter A|
     # |admin     |Pass1word  |Spain (CNM)     |Alicante (Cerrada) |BNI Albatera |


# For region
#      UPDATE bni.org_business_rule SET rule_value = '2' where id_org = '896' and id_business_rule = '36';
#      select * from bni.org_business_rule where id_org = '896' and id_business_rule = '36';





 # | Selenium  |  | bni+v456456 |  | Antarctica |  | Ant - Two |  | Chapter C |  | Pass1word   |  | Pass1word       |  | German (CH) |  | DEFAULT |  | DEFAULT |  | Organization |  | 24 Month Sgb   |  | Selenium  |  | Selenium |  | 03/07/2020 |  | 2 Years         |  | Three         |  | Linguistic |  | Online New |  | Cash          |  | Active |  | Cash              |  | Yes           |  | Yes              |  | No               |


     #|Selenium|  |bni+v34534 ||Antarctica || Z_RG Test Réĝíón 3 ||BNI Paradise||Pass1word    |  |Pass1word        | | German (CH) |  | DEFAULT |  | DEFAULT |  | Organization |  | 24 Month   |  | Selenium  |  | Selenium |  | 03/07/2020 |  | 2 Years         |  | Three         |  | Linguistic |  | Online New |  | Cash          |  | Active |  | Cash              |  | Yes           |  | Yes              |  | No               |






  #      |globalna|Pass1word| Antarctica |Z_RG Test Region 6| Chapter A|

   # | userName | password  | country    | region    | chapter               |
     #|admin|Pass1word| Antarctica |Ant - Two| Chapter C|
   # |hqadmin| Pass1word | India |Chennai CBD (A)|Crown|
           #  |bekigoodwin|Pass1word| Antarctica |Z_RG Test Region 6|3b coregroup1|
     # |admin |Pass1word|India         |Chènnai CBD (A) |CG 2 |

  #    |firstName|lastName|  |NewPassword||ConfirmPassword||language| |chapter| |city||meetingDay| |membershipTerm|  | payerName ||member|  |date| |professionalExp| |lengthProfExp| |background||appType||paymentMethod||status||changePaymentType|
#     |Selenium||Bni+v101629| Pass1word          ||Pass1word|      |English (GB)||Chapter A||Chennai||Monday| | 2 Year|  | Selenium  |   | Selenium  | |03/04/2020||2 Years||Three | |Linguistic|   |Online New|   |Cash||Active||Cash             |
#
#  |Selenium|         |Bni+v3156|| Pass1word          ||Pass1word|      |English (IN)||CG 2||Chennai||Monday| |12 Month|  | Selenium  |   | Selenium  | |03/04/2020||2 Years||Three | |Linguistic|   |Online New|   |Cash||Active||Cash             |
     # |Selenium|         |Bni+v0810810  ||Brazil||BNi Jiar ||BNI Baniwa||Individual Type|| Pass1word          ||Pass1word|      |German (CH)||cdc||Chennai||Monday| |24 Month|  | Selenium  |   | Selenium  | |03/07/2020||2 Years||Three | |Linguistic|   |Online New|   |Cash||Active||Cash             ||Yes||Yes||No            |
