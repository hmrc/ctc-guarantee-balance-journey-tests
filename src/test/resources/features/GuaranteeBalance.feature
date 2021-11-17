## Ticket Id: CTCTRADERS-2721 - Create Happy Path pages
## Ticket Id: CTCTRADERS-2726 - Exception scenario: can't try again within 1 min in the same session

## GRN: Guarantee balance
## GG: Government Gateway


@guarantee_balance
Feature: Guarantee balance rate limitation check

  Background: Verify that confirmation of my balance is displayed when valid data is used to submit my guarantee balance
    Given I clear my cookies
    And I login with identifier value 123457890
    Then I should be on the Manage your transit movements page
    When I click the Check your guarantee balance link
    Then I should be on the What is your EORI number? page
    When I submit GB1234 as eori number value
    Then I should be on the What is the guarantee reference number? page
    When I submit 1234 as guarantee reference number value
    Then I should be on the What is the access code? page
    When I submit 1111 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your balance page
    And I should see a confirmation of my balance


  Scenario: 1. Verify rate limitation occurs when the same GRN is used in the second attempt within 60 seconds of the first GRN submission
    When I click the check another guarantee balance link
    And I submit GB1234 as eori number value
    And I submit 1234 as guarantee reference number value
    And I submit 1111 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the You can try again page


  Scenario: 2. Verify that user is able to uses a different GRN on the second attempt submission
    When I click the check another guarantee balance link
    And I submit GB1234 as eori number value
    And I submit 2233 as guarantee reference number value
    And I submit 1111 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your balance page
    And I should see a confirmation of my balance


  Scenario: 3. Verify that user is able to use the same GRN but different GG login
    Given I login with identifier value 123457890
    When I click the Check your guarantee balance link
    And I submit GB04321 as eori number value
    And I submit 1234 as guarantee reference number value
    And I submit 1111 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your balance page
    And I should see a confirmation of my balance