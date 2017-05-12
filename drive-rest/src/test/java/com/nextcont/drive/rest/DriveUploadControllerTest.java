package com.nextcont.drive.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;

import org.apache.http.HttpStatus;
import org.junit.Test;

public class DriveUploadControllerTest extends AbstractControllerTest {
	@Test
	public void uploadWithNonexistedFileReturnOK() {
		give().header(tokenHeader, validToken).multiPart(new File("c:\\ad.psd"))
				.param("fileId", nonexistedFileId).param("uploadType", "multipart").param("path", "testPath").when()
				.post(uploadUrl).then()
				.log().all()
				.statusCode(HttpStatus.SC_OK).body(containsString("http://139.196.138.51/"));
	}

	@Test
	public void uploadWithExistedFileReturnOK() {
		give().header(tokenHeader, validToken).multiPart(new File("c:\\ad.psd"))
				.param("fileId", existedFileId).param("uploadType", "multipart").param("path", "testPath").when()
				.post(uploadUrl).then()
				.log().all()
				.statusCode(HttpStatus.SC_OK).body(containsString("http://139.196.138.51/"));
	}

	@Test
	public void uploadWithFileUploadTypeError() {
		give().header(tokenHeader, validToken).multiPart(new File("c:\\ad.psd"))
				.param("fileId", nonexistedFileId).param("uploadType", "testUploadType").param("path", "testPath")
				.when().post(uploadUrl).then()
				.log().all()
				.statusCode(HttpStatus.SC_BAD_REQUEST)
				.body("message", containsString("uploadType method not support"));
	}

	@Test
	public void uploadWithoutFileReturnError() {
		give().header(tokenHeader, validToken).when().post(uploadUrl).then()
		.log().all()
		.statusCode(HttpStatus.SC_BAD_REQUEST)
				.body("error.message", equalTo("Current request is not a multipart request"));
	}
}
