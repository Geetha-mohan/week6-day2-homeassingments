
Feature: Edit Account Functionality
 
  Scenario: Validate Edit Account
    Given Launch browser and load URL
    And Login into the application
    When Click viewAll from App Launcher and select Actions
    And Select the unique account name and click Edit 
    And Set all the dropdown options
    And Enter the Billing Address as 'Chennai, TN-600000'
    And Enter the Shipping Address as 'Kochi, Kerala-834792'
    And Enter the unique phoneNumber as '1234567890'
    Then Click Save
    And Verify the phoneNumber

