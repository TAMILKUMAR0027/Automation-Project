Feature:Logout account in the application -Jothika 13/05/2026

Description:This feature is about the logout functionlaity of the application

Background:
Given the user is on the My Account page
When the user clicks on Logout link

@validlogout
Scenario Outline:
Then the user should receive an intimation "<message>" regarding logout

Examples:
|message       |
|Account Logout|

@invalidlogout
Scenario:
Then the user is unable to receive any messages and remains on the same page
