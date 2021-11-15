## Ticket Id: CTCTRADERS-2721
## Ticket Id: CTCTRADERS-2745

## Enter x000 as Access code to see Wait page
## Enter x906 as Access code to see Try Again Page

@guarantee_balance
Feature: Pending guarantee balance

  ## Note: Enter x000 as Access code to see Wait page
  Background: Verify that Wait page is displayed when guarantee balance response take more than 15 seconds
    Given I clear my cookies
    And I login with identifier value 123457890
    When I click the Check your guarantee balance link
    Then I should be on the What is your EORI number? page
    When I submit GB1234 on the EORI number page
    And I submit 1234 on the guarantee reference number page
    And I submit 0000 on the access code page
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the We are trying to get your guarantee balance page


  Scenario: 1. Verify that Wait page is displayed when guarantee balance request is still pending on selecting Continue waiting button
    When I click the Continue waiting button
    Then I should be on the We are trying to get your guarantee balance page


  Scenario: 2. Verify that Your balance page is displayed when guarantee balance response is successful (IE037)
    Given The balance request completes for EORI number GB1234 and GRN 1234
    When I click the Continue waiting button
    Then I should see a confirmation of my balance


  Scenario: 3. Verify that Details do not match page is displayed when guarantee balance details do not match EORI number and GRN (IE906)
    Given The details do not match for EORI number GB1234 and GRN 1234
    When I click the Continue waiting button
    Then I should be on the Your details do not match our records page


  Scenario: 4. Verify that Try again page is displayed when guarantee balance request is deleted or does not exist
    Given The balance request is removed
    When I click the Continue waiting button
    Then I should be on the We have not been able to get your guarantee balance page


@wip
  Scenario: 5. Verify that user is able to successfully submit guarantee balance after try again and changing their access code
    When I click the Continue waiting button
    Then I should be on the We are trying to get your guarantee balance page
    When I choose to wait for 30 seconds
    And I click the Continue waiting button
    Then I should be on the We have not been able to get your guarantee balance page
    When I click the Try again button
    Then I should be on the We are trying to get your guarantee balance page
    When I choose to wait for 30 seconds
    And I click the Continue waiting button
    Then I should be on the We have not been able to get your guarantee balance page
    When I click the check that your details are correct link
    Then I should be on the Check your answers page
    When I click the Change link for Access code
    Then I should be on the What is the access code? page
    When I submit 1212 on the access code page
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your balance page
    And I should see a confirmation of my balance