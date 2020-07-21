Feature: HelloWorld

  Scenario: Call Hello World endpoint
    Given valid bearer token
    When I call HelloWorld endpoint
    Then the client receives response status code of 200

