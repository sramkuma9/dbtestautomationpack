


  Feature: Deactivate Scheme for pricing portal

  Scenario: Deactivate Scheme for pricing portal

  Given user navigates to Pricing portal and Deactivate a created scheme

      | userName | password  |country    | region    | chapter      |
      |admin   | Pass1word |Antarctica|Ant - Two|Chapter C|

    When user login as admin and enters Pricing portal to Deactivate a scheme

      | country  || region    || chapter      | |sku|     |SKU|  |effectiveTo||product||template||status||month||year||day||derived Scheme or discontinueSKU|
      |Antarctica| |Ant - Two | |Chapter C  |   |6 Month|  |7| |2020-10-21||New Membership||Organization||current|       |October||2020||21||Use Derived Scheme|


#  id	id_sku_type	description_key	code	months	created	last_modified
#  1	1	services.pricing.skus.registrationfee	REG	NULL	3/16/2020 12:00	3/16/2020 12:00
#  2	2	services.pricing.skus.membershipfee12months	TERM	12	3/16/2020 12:00	3/16/2020 12:00
#  3	2	services.pricing.skus.membershipfee24months	TERM	24	3/16/2020 12:00	3/16/2020 12:00
#  4	3	services.pricing.skus.latefee	LATE	NULL	3/16/2020 12:00	3/16/2020 12:00
#  5	2	services.pricing.skus.membershipfee12months	TERM	12	4/14/2020 14:08	4/14/2020 14:08
#  6	2	services.pricing.skus.membershipfee24months	TERM	24	4/14/2020 14:08	4/14/2020 14:08
#  7	2	services.pricing.skus.membershipfee6months	TERM	6	7/10/2020 8:42	7/10/2020 8:42
#  8	2	services.pricing.skus.membershipfee6months	TERM	6	7/10/2020 8:42	7/10/2020 8:42
#  9	2	services.pricing.skus.membershipfee18months	TERM	18	7/16/2020 12:24	7/16/2020 12:24
#  10	2	services.pricing.skus.membershipfee18months	TERM	18	8/3/2020 6:27	8/3/2020 6:27
