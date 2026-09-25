package com.automation.api.restassured.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.automation.api.restassured.clients.BookingApi;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.notNullValue;

public class BookingSteps {

    private BookingApi bookingApi;
    private Response response;

    @Given("the booking API is available")
    public void theBookingApiIsAvailable() {

        bookingApi = new BookingApi();
    }

    @When("I request all bookings")
    public void iRequestAllBookings() {

        response = bookingApi.getAllBookings();

        response.prettyPrint();
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {

        response
            .then()
            .statusCode(expectedStatusCode);
    }
    
    @When("I request an existing booking by ID")
    public void iRequestAnExistingBookingById() {

        Response bookingsResponse = bookingApi.getAllBookings();

        int bookingId =
                bookingsResponse
                    .jsonPath()
                    .getInt("[0].bookingid");

        System.out.println("Using Booking ID: " + bookingId);

        response = bookingApi.getBookingById(bookingId);

        response.prettyPrint();
    }
    
    @Then("the booking response should contain booking details")
    public void verifyBookingDetails() {

        response
            .then()
            .body("firstname", notNullValue())
            .body("lastname", notNullValue())
            .body("totalprice", notNullValue())
            .body("depositpaid", notNullValue())
            .body("bookingdates", notNullValue());
    }
}