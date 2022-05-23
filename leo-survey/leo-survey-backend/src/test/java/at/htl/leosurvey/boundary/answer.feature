Feature: Answer management endpoint.
  An user of the endpoint is able to create, delete, update and read Answer

  Background:
    * url baseUrl

  Scenario: Get answer with id 15 - cannot find
    Given path 'answer/id/15'
    When method GET
    Then status 204
    * print response


  Scenario: Create an answer
    Given path 'answer'
    And request
    """
    {
      "question": {
        "text": "Er kann auch schwierige Fragen beantworten, weil er sich in seimen Fachgebiet gut auskennt?",
        "sequenceNumber": 2,
        "questiontype": "SINGLECHOICE",
        "questionnaire": {
          "name": "Feedback Bogen",
          "description": "4ahitm"
        }
      },
      "answerText": "völlig",
      "transaction": {
        "transactionCode": "1001-2012-abcd",
        "survey": {
          "title": "Umfrage 4ahitm",
          "descritpion": "Lehrer Feedbackbogen 4ahitm",
          "interviewer":{
            "name": "Hans Moser"
          },
          "questionnaire": {
            "name": "Feedback Bogen",
            "description": "4ahitm"
          },
          "date": "2021-08-22",
          "surveyObducted": true
        },
        "isUsed": "true"
      }
    }
    """
    When method POST
    Then status 201

  Scenario: Get answer with id 15
    Given path 'answer/id/15'
    When method GET
    Then status 200
    Then match response contains {"answerText":"völlig","question":{"id":102},"id":15,"transaction":{"id":5}}
    * print response

  Scenario: Get all answer
    Given path 'answer'
    When method GET
    Then status 200
    * print response

  Scenario: Get answer by id
    Given path 'answer/id'
    #ID 15
    And path 15
    When method GET
    Then status 200
    * print response

  Scenario: Update answer
    Given path 'answer'
    #Id 15
    And path 15
    And request
    """
    {
      "question": {
        "text": "Er kann auch leichte Fragen beantworten, weil er sich in seimen Fachgebiet gut auskennt?",
        "sequenceNumber": 2,
        "questiontype": "SINGLECHOICE",
        "questionnaire": {
          "name": "Feedback Bogen",
          "description": "4ahitm"
        }
      },
      "answerText": "völlig richtig",
      "transaction": {
        "transactionCode": "1001-2012-abcd",
        "survey": {
          "title": "Umfrage 4ahitm",
          "descritpion": "Lehrer Feedbackbogen 4ahitm",
          "interviewer":{
            "name": "Hans Moser"
          },
          "questionnaire": {
            "name": "Feedback Bogen",
            "description": "4ahitm"
          },
          "date": "2021-08-22",
          "surveyObducted": true
        },
        "isUsed": "true"
      }
    }
    """
    When method PUT
    Then status 200
    * print response

  Scenario: Get answer with id 15
    Given path 'answer/id/15'
    When method GET
    Then status 200
    Then match response contains {"answerText":"völlig richtig","question":{"id":103},"id":15,"transaction":{"id":6}}
    * print response

  Scenario: Delete answer
    Given path 'answer'
    #ID 15
    And path 15
    When method DELETE
    Then status 200

  Scenario: Get answer with id 15
    Given path 'answer/id/15'
    When method GET
    Then status 204
    * print response


