Feature: Question management endpoint.
  An user of the endpoint is able to create, delete, update and read Question

  Background:
    * url baseUrl

  Scenario: Get question with id 110 - cannot find
    Given path 'question/id/110'
    When method GET
    Then status 204
    * print response


  Scenario: Create an question
    Given path 'question'
    And request
    """
    {
      "text": "Sie kennt sich in seinem Fachgebiet gut aus?",
      "sequenceNumber": 2,
      "questiontype": "SINGLECHOICE",
      "questionnaire": {
        "name": "Feedback Bogen",
        "description": "4ahitm"
      }
    }
    """
    When method POST
    Then status 201

  Scenario: Get question with id 110
    Given path 'question/id/110'
    When method GET
    Then status 200
    Then match response contains {"sequenceNumber":2,"questionnaire":{"name":"Feedback Bogen","description":"4ahitm","id":14},"id":110,"text":"Sie kennt sich in seinem Fachgebiet gut aus?","questiontype":"SINGLECHOICE"}
    * print response

  Scenario: Get all question
    Given path 'question'
    When method GET
    Then status 200
    * print response

  Scenario: Get question by id
    Given path 'question/id'
    #ID 110
    And path 110
    When method GET
    Then status 200
    * print response

  Scenario: Update question
    Given path 'question'
    #Id 110
    And path 110
    And request
    """
    {
      "text": "Er kennt sich in seinem Fachgebiet gut aus und kann schwere Fragen beantworten?",
      "sequenceNumber": 2,
      "questiontype": "SINGLECHOICE",
      "questionnaire": {
        "name": "Feedback Bogen",
        "description": "4ahitm"
      }
    }
    """
    When method PUT
    Then status 200
    * print response

  Scenario: Get question with id 110
    Given path 'question/id/110'
    When method GET
    Then status 200
    Then match response contains {"sequenceNumber":2,"questionnaire":{"name":"Feedback Bogen","description":"4ahitm","id":15},"id":110,"text":"Er kennt sich in seinem Fachgebiet gut aus und kann schwere Fragen beantworten?","questiontype":"SINGLECHOICE"}
    * print response

  Scenario: Delete question
    Given path 'question'
    #ID 110
    And path 110
    When method DELETE
    Then status 200

  Scenario: Get question with id 110
    Given path 'question/id/110'
    When method GET
    Then status 204
    * print response

