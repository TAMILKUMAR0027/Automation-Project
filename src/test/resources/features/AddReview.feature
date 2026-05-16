Feature:Add review to the product-Jothika 13/05/2026

Description:This feature facilitates the user to add the feedback on the product as review with star rating ,name with feedback

Background:
Given the user is on product details page

@Validreview
Scenario Outline:









Scenario :Successful review with ratings,name and feedback

When the user chooses star rating and writes feedback on the product with his/her name

And clicks on Write Review

Then the user is able to submit the review successfully and receives confirmation message



Scenario : Add review with Star ratings only

When the user selects star rating 

And clicks on Write Review

Then the user should receive a warning message to wite review with characters more than 25 and less than 1000.

Scenario : Add review without name

When the user adds review without the name 

And clicks on the Write Review

Then the user should receive a warning message stating name must contain characters between 3 and 25

Scenario :Add review without feedback

When the user writes review with star rating and name 

And clicks on Write Review 

Then the user should receive a warning message Stating that characters must be between 25 and 1000