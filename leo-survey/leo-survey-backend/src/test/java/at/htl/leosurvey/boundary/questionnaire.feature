Feature: Questionnaire management endpoint.
  An user of the endpoint is able to create, delete, update and read Questionnaire

  Background:
    * url baseUrl

  Scenario: Get questionnaires with id 16 - cannot find
    Given path 'questionnaire/id/16'
    When method GET
    Then status 204
    * print response


  Scenario: Create an questionnaire
    Given path 'questionnaire'
    And request
    """
    {
      "name": "Frage Bogen",
      "description": "3ahitm",
      "interviewer":{
        "name": "Hans Moser"
      }
    }

    """
    When method POST
    Then status 201
    #Then match header Location contains 'http://localhost:8081/api/questionnaire/5'

  Scenario: Get questionnaires with id 16
    Given path 'questionnaire/id/16'
    When method GET
    Then status 200
    Then match response contains {"description":"3ahitm","id":16,"interviewer":"","name":"Frage Bogen"}
    * print response

  Scenario: Get all questionnaires
    Given path 'questionnaire'
    When method GET
    Then status 200
    * print response
  
  Scenario: Get questionnaire by id
    Given path 'questionnaire/id'
    #ID 16
    And path 16
    When method GET
    Then status 200
    * print response

  Scenario: Update questionnaire
    Given path 'questionnaire'
    #Id 16
    And path 16
    And request
     """
    {
      "name": "Frage Bogen",
      "description": "4ahitm",
      "interviewer":{
        "name": "Hans Moser"
      }
    }
    """
    When method PUT
    Then status 200
    * print response

  Scenario: Get questionnaires with id 16
    Given path 'questionnaire/id/16'
    When method GET
    Then status 200
    Then match response contains {"description":"4ahitm","id":16,"interviewer":"","name":"Frage Bogen"}
    * print response

  Scenario: Delete questionnaire
    Given path 'questionnaire/delete'
    #ID 16
    And path 16
    When method DELETE
    Then status 200

  Scenario: Get questionnaires with id 16
    Given path 'questionnaire/id/16'
    When method GET
    Then status 204
    * print response

