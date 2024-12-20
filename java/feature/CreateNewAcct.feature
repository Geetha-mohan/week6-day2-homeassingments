Feature: Salesforce New Account Creation Functionality
  Scenario Outline: Create New Account
    Given Launch the Browser and Load the URL
    And Login into application
    When Click viewAll and select Actions
    And Click New button
    And Enter the Account Name as <AcctName>
    And Select Ownership as Public
    Then Click on the Save button
    And check Account Name

 Examples:
 |AcctName|
 |'geetha'|
 |'Mohan'|
 |'Priya'|