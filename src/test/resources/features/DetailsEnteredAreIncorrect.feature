## Ticket Id: CTCTRADERS-2772 - New page - "We don't support this type of guarantee"

## Enter Access Code of x906 and the "Details do not match" page is displayed
## Enter Access Code of x914 and the "We don't support this type of guarantee" page is displayed

@guarantee_balance
Feature: Guarantee balance incorrect details entered pages are pages

  Background: Guarantee balance background test set up
    Given I clear my cookies
    And I login with identifier value 123457890
    Then I should be on the Manage your transit movements page
    When I click the Check your guarantee balance link
    Then I should be on the What is your EORI number? page
    When I submit GB1234 on the EORI number page
    Then I should be on the What is the guarantee reference number? page
    When I submit 1234 on the guarantee reference number page
    Then I should be on the What is the access code? page

  Scenario: 1. Your details do not match our records page is displayed when access code x906 and user can navigate to Check your answers page
    When I submit x906 on the access code page
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the Your details do not match our records page
    When I click the check your answers and try again link
    Then I should be on the Check your answers page


  Scenario: 2. We don't support this type of guarantee page is displayed when access code x914 and user can navigate back to Check your answers page
    When I submit x914 on the access code page
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the We cannot get the balance for this type of guarantee page
    When I click the change the reference for the guarantee you check the balance link
    Then I should be on the Check your answers page


  Scenario: 3. We don't support this type of guarantee page is displayed when access code x914 and user can navigate back to Enter EORI page
    When I submit x914 on the access code page
    Then I should be on the Check your answers page
    When I click the Continue button
    Then I should be on the We cannot get the balance for this type of guarantee page
    When I click the Start again button
    Then I should be on the What is your EORI number? page
