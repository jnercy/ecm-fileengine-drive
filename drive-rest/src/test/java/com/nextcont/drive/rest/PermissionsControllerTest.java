package com.nextcont.drive.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.nextcont.file.request.file.FileCreateRequestBody;
import com.nextcont.file.request.permission.PermissionCreateRequestbody;
import com.nextcont.file.request.permission.PermissionUpdateRequestbody;

public class PermissionsControllerTest extends AbstractControllerTest {
	private void createTestFileData() {
		FileCreateRequestBody bodyData = new FileCreateRequestBody();
		bodyData.setId(existedFileId);
		bodyData.setName("testNameexistedFileId");
		bodyData.setMimeType("testMimeTypeexistedFileId");

		given().contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when().post("/files/create").then().statusCode(HttpStatus.SC_OK);

		bodyData.setId(deletedFileID);
		bodyData.setName("testNamedeletedFileID");
		bodyData.setMimeType("testMimeTypedeletedFileID");

		given().contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when().post("/files/create").then().statusCode(HttpStatus.SC_OK);
	}
	
	private void deleteTestFileData() {
		given().pathParam("fileId", existedFileId).when().delete("/files/{fileId}").then().statusCode(HttpStatus.SC_OK);
		given().pathParam("fileId", deletedFileID).when().delete("/files/{fileId}").then().statusCode(HttpStatus.SC_OK);
		given().when().delete("/files/trash").then().statusCode(HttpStatus.SC_OK);
	}
	
	@Before
	public void createTestData() {
		createTestFileData();
		
		PermissionCreateRequestbody bodyData = new PermissionCreateRequestbody();
		bodyData.setAllowFileDiscovery(false);
		bodyData.setDomain("domain");
		bodyData.setEmailAddress("test@nextcont.com");
		bodyData.setRole("testRole");
		bodyData.setType("testType");

		given().pathParam("fileId", existedFileId).contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.post("/files/{fileId}/permissions").then().statusCode(HttpStatus.SC_OK)
				.body("message", equalTo("sharing success"), "code", equalTo("success"));		


		given().pathParam("fileId", deletedFileID).contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.post("/files/{fileId}/permissions").then().statusCode(HttpStatus.SC_OK)
				.body("message", equalTo("sharing success"), "code", equalTo("success"));		
		
	}
	
	@After
	public void deleteTestData() {		
		given().pathParam("fileId", existedFileId).pathParam("permissionId", existedPermissionId)
		.param("supportsTeamDrives", notSupportTeamDrives).when()
		.delete("/files/{fileId}/permissions/{permissionId}").then().contentType(JSON).statusCode(HttpStatus.SC_OK);
		given().pathParam("fileId", deletedFileID).pathParam("permissionId", deletedPermissionId)
		.param("supportsTeamDrives", notSupportTeamDrives).when()
		.delete("/files/{fileId}/permissions/{permissionId}").then().contentType(JSON).statusCode(HttpStatus.SC_OK);
		
		deleteTestFileData();
	}
	
	@Test
	public void createExistedPermissionReturnError() {
		PermissionCreateRequestbody bodyData = new PermissionCreateRequestbody();
		bodyData.setAllowFileDiscovery(false);
		bodyData.setDomain("domain");
		bodyData.setEmailAddress("test@nextcont.com");
		bodyData.setRole("testRole");
		bodyData.setType("testType");

		given().pathParam("fileId", existedFileId).contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.post("/files/{fileId}/permissions").then().statusCode(HttpStatus.SC_OK)
				.body("message", equalTo("sharing success"), "code", equalTo("success"));
	}

	@Test
	public void createNonexistedPermissionReturnOK() {
		PermissionCreateRequestbody bodyData = new PermissionCreateRequestbody();
		bodyData.setAllowFileDiscovery(false);
		bodyData.setDomain("domain");
		bodyData.setEmailAddress("test@nextcont.com");
		bodyData.setRole("testRole");
		bodyData.setType("testType");

		given().pathParam("fileId", nonexistedFileId).contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.post("/files/{fileId}/permissions").then().statusCode(HttpStatus.SC_OK)
				.body("message", equalTo("file not found or check failed"), "code", equalTo("error"));
	}

	@Test
	public void deleteExistedPermissionReturnOK() {
		given().pathParam("fileId", existedFileId).pathParam("permissionId", existedPermissionId)
				.param("supportsTeamDrives", notSupportTeamDrives).when()
				.delete("/files/{fileId}/permissions/{permissionId}").then().contentType(JSON).statusCode(HttpStatus.SC_OK)
				.body("message", containsString("permissionId Not found"));
//				.body("message", containsString("delete execute success"));
	}

	@Test
	public void deleteNonexistedPermissionReturnError() {
		given().pathParam("fileId", nonexistedFileId).pathParam("permissionId", nonexistedPermissionId)
				.param("supportsTeamDrives", notSupportTeamDrives).when()
				.delete("/files/{fileId}/permissions/{permissionId}").then().contentType(JSON).statusCode(HttpStatus.SC_OK)
				.body("message", containsString("file not found or check failed"), "code", equalTo("error"));
	}

	@Test
	public void getExistedPermissionReturnOK() {
		given().pathParam("fileId", existedFileId).pathParam("permissionId", existedPermissionId)
				.param("supportsTeamDrives", notSupportTeamDrives).when()
				.get("/files/{fileId}/permissions/{permissionId}").then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("error", notNullValue());
	}

	@Test
	public void getNonexistedPermissionReturnError() {
		given().pathParam("fileId", nonexistedFileId).pathParam("permissionId", nonexistedPermissionId)
				.param("supportsTeamDrives", notSupportTeamDrives).when()
				.get("/files/{fileId}/permissions/{permissionId}").then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("error", notNullValue());
	}

	@Test
	public void listExistedPermissionsReturnOK() {
		given().pathParam("fileId", existedFileId).when().get("/files/{fileId}/permissions").then().statusCode(HttpStatus.SC_OK)
				.body("error", notNullValue());
	}

	@Test
	public void listNonexistedPermissionsReturnError() {
		given().pathParam("fileId", nonexistedFileId).when().get("/files/{fileId}/permissions").then().statusCode(HttpStatus.SC_OK)
				.body(equalTo(""));
	}

	@Test
	public void updateExistedPermissionReturnOK() {
		PermissionUpdateRequestbody bodyData = new PermissionUpdateRequestbody();
		bodyData.setExpirationTime("expirationTime");
		bodyData.setRole("testRole");
		given().pathParam("fileId", existedFileId).pathParam("permissionId", existedPermissionId)
				.contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.patch("/files/{fileId}/permissions/{permissionId}").then().statusCode(HttpStatus.SC_OK)
				.body("message", containsString("update execute success"), "code", equalTo("success"));
	}

	@Test
	public void updateNonexistedPermissionReturnError() {
		PermissionUpdateRequestbody bodyData = new PermissionUpdateRequestbody();
		bodyData.setExpirationTime("expirationTime");
		bodyData.setRole("testRole");
		given().pathParam("fileId", nonexistedFileId).pathParam("permissionId", nonexistedPermissionId)
				.contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.patch("/files/{fileId}/permissions/{permissionId}").then().statusCode(HttpStatus.SC_OK)
				.body("message", equalTo("file not found or file is locked"), "code", equalTo("error"));
	}
}
