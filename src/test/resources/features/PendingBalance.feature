# TODO - Change runner tag
@Example

Feature: Pending guarantee balance

  Background: User submits answers and is told their balance is pending
    Given I get a bearer token
    When I redirect to manage my transit movements
    And I click the Check your guarantee balance link
    Then I should be on the What is your EORI number? page

    When I submit GB1234 on the EORI number page
    And I submit 1234 on the guarantee reference number page
    And I submit 0000 on the access code page
    Then I should be on the Check your answers page

    When I click the Continue button
    Then I should be on the We are trying to get your guarantee balance page

  Scenario: Balance request still pending
    Given I click the Continue waiting button
    Then I should be on the We are trying to get your guarantee balance page
    And I sign out

  Scenario: Balance request completed
    Given The balance request completes for EORI number GB1234 and GRN 1234
    And I click the Continue waiting button
    Then I should see a confirmation of my balance
    And I sign out

  Scenario: Balance request details do not match
    Given The details do not match for EORI number GB1234 and GRN 1234
    And I click the Continue waiting button
    Then I should be on the Your details do not match our records page
    And I sign out

  Scenario: Balance request deleted
    Given The balance request is removed
    And I click the Continue waiting button
    Then I should be on the We have not been able to get your guarantee balance page
    And I sign out
