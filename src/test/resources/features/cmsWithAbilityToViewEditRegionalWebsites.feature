Feature: Listing of existing regional Website with access to edit and ability to create new.

  Scenario: CMS displays regional website with ability to edit and create a new website content
    Given I logged in as Admin user
      | User Name | Password  |
      | admin     | Pass1word |
      | admin     | Pass1word |
    When I select Tools ->Manage Websites and select Manage your Websites and Select Regional website list. Select country as Argentina. Click settings under options. Scroll down and click publish
      | country   |  | region |  | searchWebSiteString |  | editWebSiteString  |
      | Argentina |  | BA     |  | Find A Member       |  | Find A Member Test |
      | Argentina |  | BA     |  | Find A Member Test  |  | Find A Member      |
    Then Enter the BNI app with edited or newly created regional website loaded as expected and active member is able to view button appears in the header of the published Regional Website