@guarantee_balance
Feature: Guarantee balance referral


  Scenario: 1. User accesses journey via NCTS
    Given I clear my cookies
    And I login with identifier value 123457890
    When I click the Check your guarantee balance link
    Then I should be on the What is your EORI number? page
    When I submit GB123456123456 as eori number value
    Then I should be on the Guarantee reference number page
    When I submit 22GB1111111111112 as guarantee reference number value
    Then I should be on the Access code page
    When I submit 1111 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your balance page
    And I should see a confirmation of my balance
    And I should see a manage your transit movements link


  Scenario: 2. User accesses journey via GOVUK
    Given I clear my cookies
    And I login with no identifier value
    Then I should be on the What is your EORI number? page
    When I submit GB123456123456 as eori number value
    Then I should be on the Guarantee reference number page
    When I submit 22GB1111111111112 as guarantee reference number value
    Then I should be on the Access code page
    When I submit 1111 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your balance page
    And I should see a confirmation of my balance
    And I shouldn't see a manage your transit movements link
