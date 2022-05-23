Feature: ChosenOption management endpoint.
  An user of the endpoint is able to create, delete, update and read ChosenOption

  Background:
    * url baseUrl

  Scenario: Get chosenOption with id 10 - cannot find
    Given path 'chosenOption/id/10'
    When method GET
    Then status 204
    * print response


  Scenario: Create an chosenOption
    Given path 'chosenOption'
    And request
    """
    {
      "answerOption": {
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
      },
      "answer": {
        "question": {
          "text": "Er kann auch schwierige Fragen beantworten, weil er sich in seimen Fachgebiet gut auskennt?",
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
      },
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

  Scenario: Get chosenOption with id 10
    Given path 'chosenOption/id/10'
    When method GET
    Then status 200
    Then match response contains {"answer":{"answerText":"völlig richtig","question":{"id":106},"id":16,"transaction":{"id":7}},"question":{"sequenceNumber":2,"questionnaire":{"name":"Feedback Bogen","description":"4ahitm","id":11},"id":105,"text":"Er kann auch schwierige Fragen beantworten, weil er sich in seimen Fachgebiet gut auskennt?","questiontype":"SINGLECHOICE"},"id":10,"answerOption":{"sequenceNumber":1,"question":{"sequenceNumber":2,"questionnaire":{"name":"Feedback Bogen","description":"4ahitm","id":10},"id":104,"text":"Er kann auch schwierige Fragen beantworten, weil er sich in seimen Fachgebiet gut auskennt?","questiontype":"SINGLECHOICE"},"id":358,"text":"völlig richtig","value":4}}
    * print response

  Scenario: Get all chosenOptions
    Given path 'chosenOption'
    When method GET
    Then status 200
    * print response

  Scenario: Get chosenOption by id
    Given path 'chosenOption/id'
    #ID 3
    And path 3
    When method GET
    Then status 200
    * print response

  Scenario: Update chosenOption
    Given path 'chosenOption'
    #Id 10
    And path 10
    And request
    """
    {
      "answerOption": {
        "text": "völlig falsch",
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
      },
      "answer": {
        "question": {
          "text": "Er kann auch schwierige Fragen beantworten, weil er sich in seimen Fachgebiet gut auskennt?",
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
      },
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

  Scenario: Get chosenOption with id 10
    Given path 'chosenOption/id/10'
    When method GET
    Then status 200
    Then match response contains  {"answer":{"answerText":"völlig richtig","id":17,"question":{"id":109},"transaction":{"id":8}},"answerOption":{"id":359,"question":{"id":107,"questionnaire":{"description":"4ahitm","id":12,"name":"Feedback Bogen"},"questiontype":"SINGLECHOICE","sequenceNumber":2,"text":"Er kann auch schwierige Fragen beantworten, weil er sich in seimen Fachgebiet gut auskennt?"},"sequenceNumber":1,"text":"völlig falsch","value":4},"id":10,"question":{"id":108,"questionnaire":{"description":"4ahitm","id":13,"name":"Feedback Bogen"},"questiontype":"SINGLECHOICE","sequenceNumber":2,"text":"Er kann auch schwierige Fragen beantworten, weil er sich in seimen Fachgebiet gut auskennt?"}}
    * print response

  Scenario: Delete chosenOption
    Given path 'chosenOption'
    #ID 10
    And path 10
    When method DELETE
    Then status 200

  Scenario: Get chosenOption with id 10
    Given path 'chosenOption/id/10'
    When method GET
    Then status 204
    * print response

