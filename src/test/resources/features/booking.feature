Feature: Booking API

  Scenario: Get all bookings
    Given the booking API is available
    When I request all bookings
    Then the response status code should be 200

  Scenario: Get an existing booking by ID
    Given the booking API is available
    When I request an existing booking by ID
    Then the response status code should be 200
    And the booking response should contain booking details