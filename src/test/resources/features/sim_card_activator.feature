Feature: SIM Card Activation

  Scenario: Successfully activate a SIM card
    Given a SIM card with ICCID "1234567890"
    And a customer email address "customer@example.com"
    When the activation request is sent
    Then the SIM card should be successfully activated

  Scenario: Failed activation of a SIM card
    Given a SIM card with ICCID "0987654321"
    And a customer email address "anothercustomer@example.com"
    When the activation request is sent
    Then the activation should fail

