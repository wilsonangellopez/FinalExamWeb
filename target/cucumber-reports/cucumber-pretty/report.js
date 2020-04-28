$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/main/java/com/automation/training/features/FeaturesTestOne.feature");
formatter.feature({
  "line": 2,
  "name": "Begin the process of booking a flight till the complete credit card information page.",
  "description": "",
  "id": "begin-the-process-of-booking-a-flight-till-the-complete-credit-card-information-page.",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@TestOne"
    }
  ]
});
formatter.scenario({
  "line": 3,
  "name": "TestOne - Search Fly from LAS to LAS",
  "description": "",
  "id": "begin-the-process-of-booking-a-flight-till-the-complete-credit-card-information-page.;testone---search-fly-from-las-to-las",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "Open travelocity",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "hola",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "fin del escenario",
  "keyword": "Then "
});
formatter.match({
  "location": "HomePageStepDefinition.OpenTravelocity()"
});
