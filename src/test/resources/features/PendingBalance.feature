## Ticket Id: CTCTRADERS-2721 - Create Happy Path pages
## Ticket Id: CTCTRADERS-2745 - Exception scenario: wait - IE037/906 not returned yet

## Enter Access Code of x000 and the "Wait page" page is displayed
## Enter Access Code of x906 and the "Details do not match" page is displayed
## Enter Access Code of x914 and the "We cannot get the balance for this type of guarantee" page is displayed

@guarantee_balance
Feature: Pending guarantee balance

  ## Note: Enter x000 as Access code to see Wait page
  Background: Verify that Wait page is displayed when guarantee balance response take more than 15 seconds
    Given I clear my cookies
    And I login with identifier value 123457890
    When I click the Check your guarantee balance link
    Then I should be on the What is your EORI number? page
    When I submit GB123456123456 as eori number value
    And I submit 22GB1111111111112 as guarantee reference number value
    And I submit 0000 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the We have not been able to get your guarantee balance page


  Scenario: 1. Verify that Wait page is displayed when guarantee balance request is still pending on selecting Continue waiting button
    When I click the Try again button
    #When I click the Continue waiting button
    Then I should be on the We have not been able to get your guarantee balance page


  Scenario: 2. Verify that Your balance page is displayed when guarantee balance response is successful
    Given The balance request completes for EORI number GB123456123456 and GRN 22GB1111111111112
    When I click the Try again button
    Then I should see a confirmation of my balance


  Scenario: 3. Verify that Details do not match page is displayed when guarantee balance details do not match
    Given The details do not match for EORI number GB123456123456 and GRN 22GB1111111111112
    When I click the Try again button
    Then I should be on the Your details do not match our records page


  Scenario: 4. Verify that Guarantee type not accepted page is displayed when guarantee balance details do not match
    Given The guarantee type not accepted for EORI number GB123456123456 and GRN 22GB1111111111112
    When I click the Try again button
    Then I should be on the We cannot get the balance for this type of guarantee page


  Scenario: 5. Verify that Try again page is displayed when guarantee balance request is deleted or does not exist
    Given The balance request is removed
    When I click the Try again button
    Then I should be on the We have not been able to get your guarantee balance page


  Scenario: 6. Verify that user is able to successfully submit guarantee balance after try again and changing their access code
    When I click the Try again button
    Then I should be on the We have not been able to get your guarantee balance page
    When I choose to wait for 30 seconds
    And I click the Try again button
    Then I should be on the We have not been able to get your guarantee balance page
    When I click the Try again button
    Then I should be on the We have not been able to get your guarantee balance page
    When I choose to wait for 30 seconds
    And I click the Try again button
    Then I should be on the We have not been able to get your guarantee balance page
    When I click the check that your details are correct link
    Then I should be on the Check your answers page
    When I click the Change link for Access code
    Then I should be on the Access code page
    When I submit 1212 as access code value
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your balance page
    And I should see a confirmation of my balance