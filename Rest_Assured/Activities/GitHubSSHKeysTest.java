package com.github.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GitHubSSHKeyTest {

    // Request specification
    private RequestSpecification requestSpec;

    // Use ONLY public SSH key
    private String sshKey = "ssh-rsa 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";

    private int keyId;

    @BeforeClass
    public void setup() {

        String githubToken = System.getenv("GITHUB_TOKEN");

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.github.com")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "token " + githubToken)
                .build();
    }

    @Test(priority = 1)
    public void addSSHKey() {

        String requestBody = "{"
                + "\"title\":\"TestAPIKey\","
                + "\"key\":\"" + sshKey + "\""
                + "}";

        Response response =
                given()
                        .spec(requestSpec)
                        .body(requestBody)
                .when()
                        .post("/user/keys")
                .then()
                        .extract()
                        .response();

        keyId = response.jsonPath().getInt("id");

        Reporter.log("POST Response: " + response.asString(), true);

        assertThat(response.getStatusCode(), equalTo(201));
        assertThat(keyId, notNullValue());
    }

    @Test(priority = 2)
    public void getSSHKey() {

        Response response =
                given()
                        .spec(requestSpec)
                        .pathParam("keyId", keyId)
                .when()
                        .get("/user/keys/{keyId}")
                .then()
                        .extract()
                        .response();

        Reporter.log("GET Response: " + response.asString(), true);

        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("title"), equalTo("TestAPIKey"));
    }

    @Test(priority = 3)
    public void deleteSSHKey() {

        Response response =
                given()
                        .spec(requestSpec)
                        .pathParam("keyId", keyId)
                .when()
                        .delete("/user/keys/{keyId}")
                .then()
                        .extract()
                        .response();

        Reporter.log("DELETE Response Status: " + response.getStatusCode(), true);

        assertThat(response.getStatusCode(), equalTo(204));
    }
}
