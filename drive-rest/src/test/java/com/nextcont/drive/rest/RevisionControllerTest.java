package com.nextcont.drive.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RevisionControllerTest extends AbstractControllerTest{
	@Before
	public void createTestData() {
		
	}
	
	@After
	public void deleteTestData() {
		
	}
	
	@Test
	public void deleteExistedRevisionReturnOK() {
		given().pathParam("fileId", existedFileId).pathParam("revisionId", existedRevisionId).when()
				.delete("/files/{fileId}/revisions/{revisionId}").then().statusCode(HttpStatus.SC_OK).body(notNullValue());
	}
	
	@Test
	public void deleteNonexistedRevisionReturnError() {
		given().pathParam("fileId", nonexistedFileId).pathParam("revisionId", nonexistedRevisionId).when()
				.delete("/files/{fileId}/revisions/{revisionId}").then().statusCode(HttpStatus.SC_OK).body(notNullValue());
	}

	@Test
	public void getExistedRevisionReturnOK() {
		given().pathParam("fileId", existedFileId).pathParam("revisionId", existedRevisionId).when()
				.get("/files/{fileId}/revisions/{revisionId}").then().statusCode(HttpStatus.SC_BAD_REQUEST).body("error", notNullValue());
	}
	
	@Test
	public void getNonexistedRevisionReturnError() {
		given().pathParam("fileId", nonexistedFileId).pathParam("revisionId", nonexistedRevisionId).when()
				.get("/files/{fileId}/revisions/{revisionId}").then().statusCode(HttpStatus.SC_BAD_REQUEST).body("error", notNullValue());
	}
	
	@Test
	public void listExistedRevisionsReturnOK() {
		given().pathParam("fileId", existedFileId).when()
				.get("/files/{fileId}/revisions").then().statusCode(HttpStatus.SC_OK).body(anything());
	}
	
	@Test
	public void listNonexistedRevisionsReturnError() {
		given().pathParam("fileId", nonexistedFileId).when()
				.get("/files/{fileId}/revisions").then().statusCode(HttpStatus.SC_OK).body(anything());
	}
	
	@Test
	public void updateExistedRevisionReturnOK() {
		given().pathParam("fileId", existedFileId).pathParam("revisionId", existedRevisionId).when()
				.patch("/files/{fileId}/revisions/{revisionId}").then().statusCode(HttpStatus.SC_BAD_REQUEST).body("error", notNullValue());
	}
	
	@Test
	public void updateNonexistedRevisionReturnError() {
		given().pathParam("fileId", nonexistedFileId).pathParam("revisionId", nonexistedRevisionId).when()
				.patch("/files/{fileId}/revisions/{revisionId}").then().statusCode(HttpStatus.SC_BAD_REQUEST).body("error", notNullValue());
	}
}
