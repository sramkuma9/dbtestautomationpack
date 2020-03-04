@smoke1
Feature: New CMS with default and customized website contents.

  Scenario: New CMS displays default and customized website contents
    Given User logged in as Admin
      | User Name | Password  |


      | testautomation |Pass1word |

   When I select Tools option and navigate to CMS and select Manage your Websites. Select Country Website list ->On Argentina row, Click settings under options, click “preview ” in Find a chapter. Click Advanced Search, Enter region and click find button
      | country   |  | region |

      | Antarctica|  |Z_RG Test Region 6 |


    Then Chapter List contents displayed successfully