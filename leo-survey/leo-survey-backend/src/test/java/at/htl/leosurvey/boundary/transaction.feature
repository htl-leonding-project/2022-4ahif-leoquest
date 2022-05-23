Feature: Transaction management endpoint.
  An user of the endpoint is able to create, delete, update and read Transaction

  Background:
    * url baseUrl

  Scenario: Get transaction with id 9 - cannot find
    Given path 'transaction/id/9'
    When method GET
    Then status 204
    * print response

  Scenario: Create an transaction
    Given path 'transaction'
    And request
    """
     {
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
    """
    When method POST
    Then status 201

  Scenario: Get transaction with id 9
    Given path 'transaction/id/9'
    When method GET
    Then status 200
    Then match response contains {"id":9,"isUsed":true,"survey":{"date":"2021-08-22","id":6,"interviewer":"","questionnaire":{"description":"4ahitm","id":19,"name":"Feedback Bogen"},"title":"Umfrage 4ahitm"},"transactionCode":"1001-2012-abcd"}
    * print response

  Scenario: Get all transactions
    Given path 'transaction'
    When method GET
    Then status 200
    * print response

  Scenario: Get transaction by id
    Given path 'transaction/id'
    #ID 9
    And path 9
    When method GET
    Then status 200
    * print response

  Scenario: Update transaction
    Given path 'transaction'
    #Id 9
    And path 9
    And request
    """
    {
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
    """
    When method PUT
    Then status 200
    * print response

  Scenario: Get transaction with id 9
    Given path 'transaction/id/9'
    When method GET
    Then status 200
    Then match response contains {"id":9,"isUsed":true,"survey":{"date":"2021-08-22","id":7,"interviewer":"","questionnaire":{"description":"4ahitm","id":20,"name":"Feedback Bogen"},"title":"Umfrage 4ahitm"},"transactionCode":"1001-2012-abcd"}
    * print response

  Scenario: Delete transaction
    Given path 'transaction'
    #ID 9
    And path 9
    When method DELETE
    Then status 200

  Scenario: Get transaction with id 9
    Given path 'transaction/id/9'
    When method GET
    Then status 204
    * print response


