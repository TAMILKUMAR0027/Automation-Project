package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

import com.actions.AddReviewPageAction;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddReview {

	AddReviewPageAction arpa = new AddReviewPageAction();

	SoftAssert sa = new SoftAssert();

	private static final Logger log = LogManager.getLogger(AddReview.class);

	@Given("the user is on product details page")
	public void the_user_is_on_product_details_page() {

		arpa.launchProductPage();
	}

	@When("the user chooses star rating and writes feedback on the product with his\\/her name")
	public void the_user_chooses_star_rating_and_writes_feedback_on_the_product_with_his_her_name() {

		arpa.selectRating(1);

		arpa.enterName(1);

		arpa.enterReview(1);
	}

	@When("clicks on Write Review")
	public void clicks_on_write_review() {

		arpa.clickWriteReview();
	}

	@Then("the user is able to submit the review successfully and receives confirmation message")
	public void the_user_is_able_to_submit_the_review_successfully_and_receives_confirmation_message() {

		String actual = arpa.getSuccessMessage();

		String expected = arpa.expectedMessage(1);

		try {

			sa.assertEquals(actual, expected);

			log.info("Added review successfully");

		} catch (AssertionError e) {

			log.error("Valid review failed");
		}
	}

	@When("the user selects star rating")
	public void the_user_selects_star_rating() {

		arpa.selectRating(2);
	}

	@Then("the user should receive a warning message to wite review with characters more than {int} and less than {int}.")
	public void the_user_should_receive_a_warning_message_to_wite_review_with_characters_more_than_and_less_than(
			Integer int1, Integer int2) {

		String actual = arpa.getWarningMessage();

		String expected = arpa.expectedMessage(2);

		try {

			sa.assertEquals(actual, expected);

			log.info("Invalid review with rating verified");

		} catch (AssertionError e) {

			log.error("Invalid review with rating failed " + e.getMessage());
		}
	}

	@When("the user adds review without the name")
	public void the_user_adds_review_without_the_name() {

		arpa.selectRatingFromCSV(1);

		arpa.enterReviewFromCSV(1);
	}

	@When("clicks on the Write Review")
	public void clicks_on_the_write_review() {

		arpa.clickWriteReview();
	}

	@Then("the user should receive a warning message stating name must contain characters between {int} and {int}")
	public void the_user_should_receive_a_warning_message_stating_name_must_contain_characters_between_and(Integer int1,
			Integer int2) {

		String actual = arpa.getWarningMessage();

		String expected = arpa.expectedCSVMessage(1);

		try {

			sa.assertEquals(actual, expected);

			log.info("Invalid review without name verified");

		} catch (AssertionError e) {

			log.error("Invalid review without name failed " + e.getMessage());
		}
	}

	@When("the user writes review with star rating and name")
	public void the_user_writes_review_with_star_rating_and_name() {

		arpa.selectRatingFromCSV(2);

		arpa.enterNameFromCSV(2);

		arpa.enterReviewFromCSV(2);

		arpa.clickWriteReview();
	}

	@Then("the user should receive a warning message Stating that characters must be between {int} and {int}")
	public void the_user_should_receive_a_warning_message_stating_that_characters_must_be_between_and(Integer int1,
			Integer int2) {

		String actual = arpa.getWarningMessage();

		String expected = arpa.expectedCSVMessage(2);

		try {

			sa.assertEquals(actual, expected);

			log.info("Invalid review without feedback verified");

		} catch (AssertionError e) {

			log.error("Invalid review without feedback failed " + e.getMessage());
		}
	}
}