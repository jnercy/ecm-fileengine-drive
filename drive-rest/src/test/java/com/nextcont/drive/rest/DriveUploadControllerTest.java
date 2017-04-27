package com.nextcont.drive.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;

import org.apache.http.HttpStatus;
import org.junit.Test;

public class DriveUploadControllerTest extends AbstractControllerTest {
	@Test
	public void uploadWithNonexistedFileReturnOK() {
		given().multiPart(new File("c:\\a9a7ec7f4c903f16a043cf390703d1f0.jpg")).param("fileId", nonexistedFileId)
				.param("uploadType", "multipart").param("path", "testPath").when()
				.post(uploadUrl).then().statusCode(HttpStatus.SC_OK)
				.body(containsString("http://139.196.138.51/"));
	}
	
	@Test
	public void uploadWithExistedFileReturnOK() {
		given().multiPart(new File("c:\\a9a7ec7f4c903f16a043cf390703d1f0.jpg")).param("fileId", existedFileId)
				.param("uploadType", "multipart").param("path", "testPath").when()
				.post(uploadUrl).then().statusCode(HttpStatus.SC_OK)
				.body(containsString("http://139.196.138.51/"));
	}

	@Test
	public void uploadWithFileUploadTypeError() {
		given().multiPart(new File("c:\\a9a7ec7f4c903f16a043cf390703d1f0.jpg")).param("fileId", nonexistedFileId)
				.param("uploadType", "testUploadType").param("path", "testPath").when()
				.post(uploadUrl).then().statusCode(HttpStatus.SC_OK)
				.body(containsString("uploadType method not support"));
	}

	@Test
	public void uploadWithoutFileReturnError() {
		given().when().post(uploadUrl).then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("message",
				equalTo("Current request is not a multipart request"));
	}
}
