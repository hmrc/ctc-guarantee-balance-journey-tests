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
    Then I should be on the Your details do not match our records page
    When I click the check your answers and try again link
    Then I should be on the Check your answers page
    When I click the change link for guarantee reference number
    And I submit 01GB1234567890120A123456 as guarantee reference number value
    Then I should be on the Check your answers page
    When I click the change link for access code

    And I submit x916 as access code value
    When I click the Continue button
    Then I should be on the We have not been able to get your guarantee balance page
    When I click the Try again button
    Then I should be on the We have not been able to get your guarantee balance page
    When I click the check that your details are correct link

    And I click the change link for guarantee reference number
    When I submit 22GB1111111111112 as guarantee reference number value
    Then I should be on the Check your answers page
    And I click the change link for access code
    When I submit AB12 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your balance page
    And I should see a confirmation of my balance

  Scenario: 2. User accesses journey via GOVUK
    Given I clear my cookies
    And I login with no identifier value
    Then I should be on the What is the Guarantee Reference Number (GRN)? page
    And I submit 01GB1234567890120A123456 as guarantee reference number value

    When I submit x906 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your details do not match our records page
    When I click the check your answers and try again link
    Then I should be on the Check your answers page
    When I click the change link for guarantee reference number
    And I submit 01GB1234567890120A123456 as guarantee reference number value
    Then I should be on the Check your answers page
    When I click the change link for access code

    And I submit x916 as access code value
    When I click the Continue button
    Then I should be on the We have not been able to get your guarantee balance page
    When I click the Try again button
    Then I should be on the We have not been able to get your guarantee balance page
    When I click the check that your details are correct link

    And I click the change link for guarantee reference number
    When I submit 22GB1111111111112 as guarantee reference number value
    Then I should be on the Check your answers page
    And I click the change link for access code
    When I submit AB12 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your balance page
    And I should see a confirmation of my balance