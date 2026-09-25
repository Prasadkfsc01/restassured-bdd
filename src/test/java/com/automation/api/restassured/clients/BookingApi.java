package com.automation.api.restassured.clients;

import com.automation.api.restassured.config.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookingApi {

    private final RequestSpecification requestSpec;

    public BookingApi() {

        requestSpec =
                new RequestSpecBuilder()
                    .setBaseUri(ConfigManager.getBaseUrl())
                    .setContentType(ContentType.JSON)
                    .build();
    }

    public Response getAllBookings() {

        return RestAssured
                .given()
                .spec(requestSpec)
                .when()
                .get("/booking")
                .then()
                .extract()
                .response();
    }

    public Response getBookingById(int bookingId) {

        return RestAssured
                .given()
                .spec(requestSpec)
                .pathParam("bookingId", bookingId)
                .when()
                .get("/booking/{bookingId}")
                .then()
                .extract()
                .response();
    }
}