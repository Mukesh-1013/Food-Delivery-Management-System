Feature: Food Delivery Management API

  Scenario: Register a new user
    When I send a POST request to "/users/register" with the following data
      | username | password | email              |
      | Raj   | pass6    |Raj@example.com |
    Then the response should have status 201
    And the response should contain the new user with username "Raj"

  Scenario: Login a user
    When I send a POST request to "/users/login" with the following data
      | username | password |
      | Kowshik  | pass2    |
    Then the response should have status 200
    And the response should contain "Login successful"

  Scenario: Get restaurant details by ID
    When I send a GET request to "/restaurants/1"
    Then the response should contain the restaurant with id 2

  Scenario: Search for foods by name
    When I send a GET request to "/foods/search" with query "Briyani"
    Then the response should be a JSON array containing the food with name "Briyani"

  Scenario: Update user profile
    When I send a PUT request to "/users/1" with the following data
      | username | email             |
      | Kowshik   | Kowshik04@example.com |
    Then the response should have status 200
    And the response should contain the updated user with new email

  Scenario: Delete a user
    When I send a DELETE request to "/users/1"
    Then the response should have status 204

 