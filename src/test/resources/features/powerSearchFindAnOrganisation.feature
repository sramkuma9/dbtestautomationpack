#Feature: Power Search Find an Organisation
#
#  Scenario: Power Search Find an Organisation
#    Given I enter BNI home page, and select Tools->Power search->Find An Organisation
#      | userName       | password  | country    | region    | chapter   |
#      #Test2
#      | TestAutomation | Pass1word | Antarctica | Ant - Two | Chapter C |
#  # |TestAutomation  |  Pass1word | Antarctica |Ant - Two | Chapter C |
#     #Track
##      | automationUser | Pass1word | Antarctica | Z_RG Test Region 6| Chapter A |
##      | automationUser | Pass1word | Antarctica | Z_RG Test Region 6| Chapter A |
##
#    When I enter the search criteria of Organisations and select search button
#      | name       | company        | orgType | orgStatus | category  | secCategory | sizeType                   | sizeValue | queryDateType  | periodYear | periodMonth | periodDate | numberPerPage |
# #Test2
#      | Antarctica | BNI Costa Rica | Country | Active    | Appraisal | Appraisals  | Chapters Members more than | 10        | Created Before | 2019       | Nov         | 12         | 20            |
#  #Track
# #  |Car |                 |                  | |                  |           |                               |            |                  |          |           |        |   10   |
#
#    # |3b Coregroup |                 |                  | |                  |           |                               |            |                  |          |           |        |   10   |
#
#
#
#    Then Reports for the specified Organisation displayed successfully-verify with DB
