@Example

Feature: Guarantee balance

  Scenario: Balance request pending then completed
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

    When I complete the balance request for EORI number GB1234 and GRN 1234
    And I click the Continue waiting button
    Then I should see a confirmation of my balance
    And I sign out
