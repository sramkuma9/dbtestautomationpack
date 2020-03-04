@smoke1
Feature: To verify slips audit report
  Scenario: To verify slips audit report
    Given Add slips via member module script executed successfully
    When I query the db with correct sql
    Then the slips audit report should be correct