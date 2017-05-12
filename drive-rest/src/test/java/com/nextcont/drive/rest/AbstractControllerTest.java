package com.nextcont.drive.rest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:config/application.properties")
public abstract class AbstractControllerTest {
	protected static final Logger logger = LoggerFactory.getLogger(AbstractControllerTest.class);

	@LocalServerPort
	protected int port;
	protected String uploadUrl;

	protected static final String existedFileId = "testexistedFileId";
	protected static final String existedFileId2 = "testexistedFileId2";
	protected static final String nonexistedFileId = "NonexistedFileID";
	protected static final String deletedFileID = "testdeletedFileID";

	protected static final String existedPermissionId = "testexistedPermissionId";
	protected static final String nonexistedPermissionId = "NonexistedPermissionId";
	protected static final String deletedPermissionId = "testdeletedPermissionId";

	protected static final String existedRevisionId = "testexistedRevisionId";
	protected static final String nonexistedRevisionId = "NonexistedRevisionId";
	protected static final String deletedRevisionId = "testdeletedRevisionId";

	protected static final String existedUserId = "jnercywang@gmail.com";
	protected static final String nonexistedUserId = "tt@gmail.com";

	protected static final boolean supportTeamDrives = true;
	protected static final boolean notSupportTeamDrives = false;

	protected static String validToken = "";
	protected static String invalidToken = "122121";
	protected static final String tokenHeader = "authorization";

	@Before
	public void setUp() throws Exception {
		logger.debug("get everything ready");

		RestAssured.baseURI = "http://localhost";
		RestAssured.basePath = "/drive/v1";
		RestAssured.port = port;

		uploadUrl = "http://localhost:" + port + "/upload/drive/v1/files";

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.registerParser("text/plain", Parser.JSON);
		// RestAssured.registerParser("text/plain", Parser.TEXT);
		RestAssured.defaultParser = Parser.JSON;
	}

	@BeforeClass
	public static void getToken() {
		validToken = OauthTest.getToken();
	}

	protected RequestSpecification give() {
		return given();
	}

	protected RequestSpecification give(boolean isValid) {
		if (isValid) {
			return given().header(tokenHeader, validToken);
		} else {
			return given().header(tokenHeader, invalidToken);
		}
	}
}
