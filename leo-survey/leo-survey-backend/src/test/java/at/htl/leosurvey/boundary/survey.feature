Feature: Survey management endpoint.
  An user of the endpoint is able to create, delete, update and read Survey

  Background:
    * url baseUrl

  Scenario: Get survey with id 5 - cannot find
    Given path 'survey/id/5'
    When method GET
    Then status 204
    * print response


  Scenario: Create an survey
    Given path 'survey'
    And request
    """
    {
      "interviewer":{
        "name": "Hans Moser"
      },
      "questionnaire": {
        "name": "Feedback Bogen",
        "description": "4ahitm"
      },
      "date": "2021-08-22"
    }
    """
    When method POST
    Then status 201

  Scenario: Get survey with id 5
    Given path 'survey/id/5'
    When method GET
    Then status 200
    Then match response contains {"date":"2021-08-22","id":5,"interviewer":"","questionnaire":{"description":"4ahitm","id":17,"name":"Feedback Bogen"}}
    * print response

  Scenario: Get all survey
    Given path 'survey'
    When method GET
    Then status 200
    * print response

  Scenario: Get survey by id
    Given path 'survey/id'
    #ID 5
    And path 5
    When method GET
    Then status 200
    * print response

  Scenario: Update survey
    Given path 'survey'
    #Id 5
    And path 5
    And request
    """
    {
      "interviewer":{
        "name": "Hans Moser"
      },
      "questionnaire": {
        "name": "Feedback Bogen",
        "description": "4ahitm"
      },
      "date": "2021-08-25"
    }
    """
    When method PUT
    Then status 200
    * print response

  Scenario: Get survey with id 5
    Given path 'survey/id/5'
    When method GET
    Then status 200
    Then match response contains {"date":"2021-08-25","id":5,"interviewer":"","questionnaire":{"description":"4ahitm","id":18,"name":"Feedback Bogen"}}
    * print response

  Scenario: Delete survey
    Given path 'survey'
    #ID 5
    And path 5
    When method DELETE
    Then status 200

  Scenario: Get survey with id 5
    Given path 'survey/id/5'
    When method GET
    Then status 204
    * print response

