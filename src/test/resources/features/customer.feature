@CucumberFeatureTest
Feature: BDD Cucumber Test

  Scenario: Create Cucumber Get api successfully
    Given create cucumber get request "Mahabir"
    When call cucumber get API
    Then validate cucumber get API response
      | http response | response      |
      | 200           | Hello Mahabir |


  Scenario: Create cucumber post api successfully
    Given create cucumber post request
      | customer post request    |
      | request-01932488.json |
    When call cucumber post API

    Then validate cucumber post API response
      | http response | age | name          |
      | 200           | 30  | Mahabir Singh |

