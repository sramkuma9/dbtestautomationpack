#Feature: Branding
#
#  Scenario: Check if correct text is dispalyed when cc/bni users logs in
#    Given Below user navigates to BNI homepage
#      | userName | password  | country    | region    | chapter               |
#      | saraha   | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#      | malshq   | Pass1word | Antarctica | Ant - One | Ant - One - Chapter A |
#    Then the header text should be displayed correctly
#      | concept |
#      | CC      |
#      | BNI     |
