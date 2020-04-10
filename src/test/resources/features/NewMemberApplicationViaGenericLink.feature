# new feature
# Tags: optional

Feature: New Member Application via Generic link

  Scenario: New Member Application via Generic link
    Given New Member Application via Generic link
      | userName    | password     | country   | region | chapter |
      |sindhu1234|Pass1word|Australia - BNI |ACT - BNI Canberra|BNI Ambassador|
    When I Copy the Form link and Register the member
    |NewPassword||ConfirmPassword||email||chapter||city||meetingDay| |applicationLanguage||firstName||lastName||language||phoneNumber||addressLine1||country||industry||membershipTerm||payerName|
    |Pass1word  ||Pass1word      ||seleniumbni+081@gmail.com||BNI Directors||Chennai||Tuesday| |English (AU)||Daisy    ||Peter   ||English (AU)||98345345654||Clifton Lane||Australia AU||Agriculture||1 Year||Test|