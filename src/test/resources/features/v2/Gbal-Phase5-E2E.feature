@guarantee_balance_v2
Feature: Guarantee balance referrals full journey testing getting balance and incorrect details

  Scenario: 1. User accesses journey via NCTS
    Given I clear my cookies
    And I login with identifier value 123457890
    Then I should be on the Manage your transit movements page
    When I click the Check your guarantee balance link
    Then I should be on the What is the Guarantee Reference Number (GRN)? page
    And I submit 01GB1234567890120A123456 as guarantee reference number value

    When I submit x906 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the The Guarantee Reference Number (GRN) and access code do match page
    When I click the Check your details and try again link
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the We could not check your guarantee balance page
    Then I choose to wait for 60 seconds
    When I click the Try again button
    Then I should be on the The Guarantee Reference Number (GRN) and access code do match page
    When I click the Check your details and try again link

    And I click the change link for guarantee reference number
    When I submit 22GB1111111111112 as guarantee reference number value
    Then I should be on the Check your answers page
    And I click the change link for access code
    When I submit AB12 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your balance is page
    And I should see a confirmation of my balance

  Scenario: 2. User accesses journey via NCTS with new enrolment
    Given I clear my cookies
    And I authenticate with new enrolment and identifier value 123457890
    Then I should be on the Manage your transit movements page
    When I click the Check your guarantee balance link
    Then I should be on the What is the Guarantee Reference Number (GRN)? page
    And I submit 01GB1234567890120A123456 as guarantee reference number value

    When I submit x906 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the The Guarantee Reference Number (GRN) and access code do match page
    When I click the Check your details and try again link
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the We could not check your guarantee balance page
    Then I choose to wait for 60 seconds
    When I click the Try again button
    Then I should be on the The Guarantee Reference Number (GRN) and access code do match page
    When I click the Check your details and try again link

    And I click the change link for guarantee reference number
    When I submit 22GB1111111111112 as guarantee reference number value
    Then I should be on the Check your answers page
    And I click the change link for access code
    When I submit AB12 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your balance is page
    And I should see a confirmation of my balance

  Scenario: 3. User accesses journey via GOVUK
    Given I clear my cookies
    And I login with no identifier value
    Then I should be on the What is the Guarantee Reference Number (GRN)? page
    And I submit 01GB1234567890120A123456 as guarantee reference number value

    When I submit x906 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the The Guarantee Reference Number (GRN) and access code do match page
    When I click the Check your details and try again link
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the We could not check your guarantee balance page
    Then I choose to wait for 60 seconds
    When I click the Try again button
    Then I should be on the The Guarantee Reference Number (GRN) and access code do match page
    When I click the Check your details and try again link

    And I click the change link for guarantee reference number
    When I submit 22GB1111111111112 as guarantee reference number value
    Then I should be on the Check your answers page
    And I click the change link for access code
    When I submit AB12 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your balance is page
    And I should see a confirmation of my balance