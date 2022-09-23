@DummyFeatureTest
Feature: George Create Adjustment

  Scenario: Create dummy Get api successfully
    Given create dummy get request "Mahabir"
    When call dummy get API
    Then validate dummy get API response
      | http response | response      |
      | 200           | Hello Mahabir |


  Scenario: Create dummy post api successfully
    Given create dummy post request
      | dummy post request    |
      | request-01932488.json |
    When call dummy post API

    Then validate dummy post API response
      | http response | age | name          |
      | 200           | 30  | Mahabir Singh |

