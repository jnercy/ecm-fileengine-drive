package com.nextcont.drive.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.apache.http.HttpStatus;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

public class OauthTest {
	private static final String host = "api.inecm.cn";
	// private static final String host = "api.nextcont.com";
	private static final String testUserName = "yiguo.chen@nextcont.com";
	private static final String testPassword = "Passw0rD";

	@Test
	public void login() throws Exception {
		String passwordMd5Digest = MD5Security.code(testPassword, 32);

		String loginJson = "{\"account\": \"" + testUserName + "\",\"password\": \"" + passwordMd5Digest
				+ "\",\"lang\": \"EN_US\",\"platform\": \"win\", \"umd\":\"C02M25UGFD57\"}";
		String ncat = given().log().all().contentType(JSON).body(loginJson).when().post("https://" + host + "/o/auth")
				.then().log().all().statusCode(HttpStatus.SC_OK).contentType(JSON)
				.body("account", equalTo(testUserName), "access_token", notNullValue()).extract().path("access_token");

		given().header("authorization", "OAuth " + ncat).when().get("https://" + host + "/o/state").then().log().all()
				.statusCode(HttpStatus.SC_OK).contentType(JSON).body("gmail", equalTo(testUserName));
	}

	public static String getToken() {
		String passwordMd5Digest;
		try {
			passwordMd5Digest = MD5Security.code(testPassword, 32);

			String loginJson = "{\"account\": \"" + testUserName + "\",\"password\": \"" + passwordMd5Digest
					+ "\",\"lang\": \"EN_US\",\"platform\": \"win\", \"umd\":\"C02M25UGFD57\"}";
			return given().log().all().contentType(JSON).body(loginJson).when().post("https://" + host + "/o/auth")
					.then().log().all().statusCode(HttpStatus.SC_OK).contentType(JSON)
					.body("account", equalTo(testUserName), "access_token", notNullValue()).extract()
					.path("access_token");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
