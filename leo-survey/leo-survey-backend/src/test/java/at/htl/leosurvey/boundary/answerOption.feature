Feature: AnswerOption management endpoint.
  An user of the endpoint is able to create, delete, update and read AnswerOption

  Background:
    * url baseUrl

  Scenario: Get answerOption with id 357 - cannot find
    Given path 'answerOption/id/357'
    When method GET
    Then status 204
    * print response


  Scenario: Create an answerOption
    Given path 'answerOption'
    And request
    """
    {
      "text": "völlig richtig",
      "sequenceNumber": 1,
      "value": 4,
      "question": {
        "text": "Er kann auch schwierige Fragen beantworten, weil er sich in seimen Fachgebiet gut auskennt?",
        "sequenceNumber": 2,
        "questiontype": "SINGLECHOICE",
        "questionnaire": {
          "name": "Feedback Bogen",
          "description": "4ahitm"
        }
      }
    }
    """
    When method POST
    Then status 201

  Scenario: Get answerOption with id 357
    Given path 'answerOption/id/357'
    When method GET
    Then status 200
    Then match response contains {"sequenceNumber":1,"question":{"sequenceNumber":2,"questionnaire":{"name":"Feedback Bogen","description":"4ahitm","id":8},"id":100,"text":"Er kann auch schwierige Fragen beantworten, weil er sich in seimen Fachgebiet gut auskennt?","questiontype":"SINGLECHOICE"},"id":357,"text":"völlig richtig","value":4}
    * print response

  Scenario: Get all answerOptions
    Given path 'answerOption'
    When method GET
    Then status 200
    * print response

  Scenario: Get answerOption by id
    Given path 'answerOption/id'
    #ID 357
    And path 357
    When method GET
    Then status 200
    * print response

  Scenario: Update answerOption
    Given path 'answerOption'
    #Id 357
    And path 357
    And request
    """
    {
      "text": "völlig richtig",
      "sequenceNumber": 1,
      "value": 4,
      "question": {
        "text": "Er kann auch schwierige Fragen beantworten, weil er sich in seimen Fachgebiet gut auskennt?",
        "sequenceNumber": 2,
        "questiontype": "SINGLECHOICE",
        "questionnaire": {
          "name": "Feedback Bogen",
          "description": "4ahitm"
        }
      }
    }
    """
    When method PUT
    Then status 200
    * print response

  Scenario: Get answerOption with id 357
    Given path 'answerOption/id/357'
    When method GET
    Then status 200
    Then match response contains {"sequenceNumber":1,"question":{"sequenceNumber":2,"questionnaire":{"name":"Feedback Bogen","description":"4ahitm","id":9},"id":101,"text":"Er kann auch schwierige Fragen beantworten, weil er sich in seimen Fachgebiet gut auskennt?","questiontype":"SINGLECHOICE"},"id":357,"text":"völlig richtig","value":4}
    * print response

  Scenario: Delete answerOption
    Given path 'answerOption/357'
    When method DELETE
    Then status 200

  Scenario: Get answerOption with id 357
    Given path 'answerOption/id/357'
    When method GET
    Then status 204
    * print response



