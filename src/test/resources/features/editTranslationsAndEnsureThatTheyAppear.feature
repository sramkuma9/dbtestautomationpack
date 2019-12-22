Feature: Edit Translations and ensure that they appear

  Scenario: Check Amended screens with Translations appear
    Given User logged in with below login details, select T icon in the Home page
      | userName                | password     | country    | region    | Chapter               |
      | shanthibni+22@gmail.com | aadhira@2014 | Antarctica | Ant - One | Ant - One - Chapter A |
    Then Amend the Translate English to Japanese page, click Footer and update the below details and click Submit button
      | browserPolicy |  | privacyPolicy |
      | ブラウザポリシー      |  | 個人情報保護方針      |
    Then the browser policy and privacy policy reflect the above changes