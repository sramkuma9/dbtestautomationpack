$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("powerSearchFindAPerson.feature");
formatter.feature({
  "line": 1,
  "name": "Power Search Find a person",
  "description": "",
  "id": "power-search-find-a-person",
  "keyword": "Feature"
});
formatter.before({
  "duration": 29457287,
  "status": "passed"
});
formatter.before({
  "duration": 177888,
  "status": "passed"
});
formatter.before({
  "duration": 292217,
  "status": "passed"
});
formatter.before({
  "duration": 156539,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Power Search Find a person",
  "description": "",
  "id": "power-search-find-a-person;power-search-find-a-person",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I’m in the BNI home page, and click Tools-\u003ePower search",
  "rows": [
    {
      "cells": [
        "userName",
        "password",
        "country",
        "region",
        "chapter"
      ],
      "line": 5
    },
    {
      "cells": [
        "malshq",
        "Pass1word",
        "Antarctica",
        "Ant - One",
        "Ant - One - Chapter A"
      ],
      "line": 6
    },
    {
      "cells": [
        "malshq",
        "Pass1word",
        "Antarctica",
        "Ant - One",
        "Ant - One - Chapter A"
      ],
      "line": 7
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I click “Find a Person” and enter the below details and click search button",
  "rows": [
    {
      "cells": [
        "firstName",
        "",
        "lastName",
        "",
        "company",
        "",
        "bniOrganisation",
        "",
        "dateCriteria",
        "",
        "PeriodStartDay",
        "",
        "periodStartMonth",
        "periodStartYear",
        "",
        "periodEndDay",
        "",
        "periodEndMonth",
        "",
        "periodEndYear",
        "",
        "queryDay",
        "",
        "queryMonth",
        "",
        "queryYear",
        "",
        "role",
        "",
        "feesSuspended",
        "",
        "activeRecordsOnly",
        "",
        "showRecordsWithRemarksOnly",
        "",
        "deletedRecords",
        "",
        "resultsPerPage"
      ],
      "line": 9
    },
    {
      "cells": [
        "Selenium",
        "",
        "Test",
        "",
        "Airtel",
        "",
        "BNI",
        "",
        "No Date Criteria",
        "",
        "1",
        "",
        "Jan",
        "2017",
        "",
        "31",
        "",
        "Dec",
        "",
        "2019",
        "",
        "21",
        "",
        "Oct",
        "",
        "2019",
        "",
        "HQ Admin",
        "",
        "false",
        "",
        "false",
        "",
        "false",
        "",
        "true",
        "",
        "20"
      ],
      "line": 10
    },
    {
      "cells": [
        "Selenium",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
      ],
      "line": 11
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "Reports for the specified person displayed successfully- verify with DB",
  "keyword": "Then "
});
formatter.match({
  "location": "PowerSearchFindAPerson.I_m_in_the_BNI_home_page_and_click_Tools_Power_search(DataTable)"
});
formatter.result({
  "duration": 402237106,
  "status": "passed"
});
formatter.match({
  "location": "PowerSearchFindAPerson.I_click_Find_a_Person_and_enter_the_below_details_and_click_search_button(DataTable)"
});
formatter.result({
  "duration": 250879795649,
  "status": "passed"
});
formatter.match({
  "location": "PowerSearchFindAPerson.Reports_for_the_specified_person_displayed_successfully_verify_with_DB()"
});
formatter.result({
  "duration": 219812,
  "status": "passed"
});
formatter.after({
  "duration": 63359,
  "status": "passed"
});
formatter.after({
  "duration": 36897,
  "status": "passed"
});
formatter.after({
  "duration": 32805,
  "status": "passed"
});
formatter.after({
  "duration": 68842,
  "status": "passed"
});
});