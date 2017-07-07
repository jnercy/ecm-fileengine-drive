package com.nextcont.drive.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nextcont.drive.utils.IdGenService;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.file.DriveFile;
import com.nextcont.file.FileList;
import com.nextcont.file.request.file.FileCreateRequestBody;
import com.nextcont.file.request.file.FileLockRequest;
import com.nextcont.file.request.file.FileRequestbody;

import io.restassured.response.Response;

public class DriveOperateControllerTest extends AbstractControllerTest {
	@Autowired
	private IdGenService idGenService;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void createTestData() {
		FileCreateRequestBody bodyData = new FileCreateRequestBody();
		bodyData.setId(existedFileId);
		bodyData.setName("testNameexistedFileId");
		bodyData.setMimeType("testMimeTypeexistedFileId");

		give().header(tokenHeader, validToken).contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.post("/files/create").then().statusCode(HttpStatus.SC_OK);

		bodyData.setId(deletedFileID);
		bodyData.setName("testNamedeletedFileID");
		bodyData.setMimeType("testMimeTypedeletedFileID");

		give().header(tokenHeader, validToken).contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.post("/files/create").then().statusCode(HttpStatus.SC_OK);
	}

	@After
	public void deleteTestData() {
		give().header(tokenHeader, validToken).pathParam("fileId", existedFileId).when().delete("/files/{fileId}")
				.then().statusCode(HttpStatus.SC_OK);
		give().header(tokenHeader, validToken).pathParam("fileId", deletedFileID).when().delete("/files/{fileId}")
				.then().statusCode(HttpStatus.SC_OK);
		give().header(tokenHeader, validToken).when().delete("/files/trash").then().statusCode(HttpStatus.SC_OK);
	}

	//@Test
	public void getReturnErrorCode404() {
		give().header(tokenHeader, validToken).when().get("/filesErrorAction").then().assertThat()
				.statusCode(HttpStatus.SC_NOT_FOUND).body("error", equalTo("Not Found"));
	}

	//@Test
	public void copyExistedFileReturnOK() {
		FileRequestbody bodyData = new FileRequestbody();
		bodyData.setId("testId");
		give().header(tokenHeader, validToken).pathParam("fileId", existedFileId)
				.contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when().post("/files/{fileId}/copy").then()
				.statusCode(HttpStatus.SC_OK).body(notNullValue());
	}

	//@Test
	public void copyNonexistedFileReturnOK() {
		FileRequestbody bodyData = new FileRequestbody();
		bodyData.setId("testId");
		give().header(tokenHeader, validToken).pathParam("fileId", nonexistedFileId)
				.contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when().post("/files/{fileId}/copy").then()
				.statusCode(HttpStatus.SC_BAD_REQUEST).body(notNullValue());
	}

	//@Test
	public void copyFileWithoutDataReturnError() {
		give().header(tokenHeader, validToken).pathParam("fileId", nonexistedFileId).when()
				.post("/files/{fileId}/copy").then().statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE)
				.body("error.message", containsString("not supported"));
	}

	//@Test
	public void createFileWithoutDataReturnErrorK() {
		give().header(tokenHeader, validToken).contentType(MediaType.APPLICATION_JSON_VALUE).when()
				.post("/files/create").then().statusCode(HttpStatus.SC_BAD_REQUEST)
				.body("error.message", containsString("Request"));
	}

	//@Test
	public void createNewFileReturnOK() {
		FileCreateRequestBody bodyData = new FileCreateRequestBody();
		long newFileId = idGenService.nextId();
		bodyData.setId(new Long(newFileId).toString());
		bodyData.setName("testName");
		bodyData.setMimeType("testMimeType");

		give().header(tokenHeader, validToken).contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.post("/files/create").then().statusCode(HttpStatus.SC_OK)
				.body("code", equalTo("success"), "message", containsString("file create success"));

		give().header(tokenHeader, validToken).pathParam("fileId", newFileId).when().delete("/files/{fileId}").then()
				.statusCode(HttpStatus.SC_OK);
	}

	//@Test
	public void createExistedFileReturnOK() {
		FileCreateRequestBody bodyData = new FileCreateRequestBody();
		bodyData.setId(existedFileId2);
		bodyData.setName("testName");
		bodyData.setMimeType("testMimeType");

		give().header(tokenHeader, validToken).contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.post("/files/create").then().statusCode(HttpStatus.SC_OK)
				.body("code", equalTo("success"), "message", containsString("file create success"));

		give().header(tokenHeader, validToken).pathParam("fileId", existedFileId2).when().delete("/files/{fileId}")
				.then().statusCode(HttpStatus.SC_OK);
	}

	//@Test
	public void createNewFileWithParentReturnOK() {
		FileCreateRequestBody bodyData = new FileCreateRequestBody();
		long newFileId = idGenService.nextId();
		bodyData.setId(new Long(newFileId).toString());
		bodyData.setName("testNameWithParent");
		bodyData.setMimeType("testMimeTypeWithParent");
//		bodyData.setParents(Arrays.asList("testParents1,testParents2"));

		give().header(tokenHeader, validToken).contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.post("/files/create").then().statusCode(HttpStatus.SC_OK)
				.body("code", equalTo("success"), "message", containsString("file create success"));

		give().header(tokenHeader, validToken).param("pageToken", "1").param("pageSize", "20")
				.param("parents", "testParents1").when().post("/files/list").then().statusCode(HttpStatus.SC_OK)
				.contentType(JSON).body("kind", equalTo("drive#fileList"), "files.id",
						hasItems(new Long(newFileId).toString()), "files.name", hasItems("testNameWithParent"));
		// .extract().response().prettyPrint();

		give().header(tokenHeader, validToken).pathParam("fileId", newFileId).when().delete("/files/{fileId}").then()
				.statusCode(HttpStatus.SC_OK);
	}

	//@Test
	public void deleteExistedFileReturnOK() {
		give().header(tokenHeader, validToken).pathParam("fileId", existedFileId).when().delete("/files/{fileId}")
				.then().statusCode(HttpStatus.SC_OK).body("message", containsString("trash success"));
	}

	//@Test
	public void deleteNonexistedFileReturnOK() {
		give().header(tokenHeader, validToken).pathParam("fileId", nonexistedFileId).when().delete("/files/{fileId}")
				.then().statusCode(HttpStatus.SC_OK).body("message", containsString("trash error"));
	}

	//@Test
	public void trashFileReturnOK() {
		give().header(tokenHeader, validToken).when().delete("/files/trash").then().statusCode(HttpStatus.SC_OK)
				.body("message", equalTo("trash success"));
	}

	//@Test
	public void exportFileWithoutMimeType() {
		// exception.expect(MissingServletRequestParameterException.class);
		// exception.expectMessage("Required String parameter 'mimeType' is not
		// present");

		// give().header(tokenHeader,validToken).pathParam("fileId",
		// nonexistedFileId).when().get("/files/{fileId}/export").then()
		// .statusCode(HttpStatus.SC_BAD_REQUEST).contentType(JSON).body("error",
		// equalTo("Bad Request"));

		give().header(tokenHeader, validToken).pathParam("fileId", existedFileId)
				// .param("mimeType", "testMimeType")
				.when().get("/files/{fileId}/export").then().statusCode(HttpStatus.SC_BAD_REQUEST)
				// .contentType(JSON).body("error.errors[0].reason",
				// equalTo("invalid parameter"), "error.errors[0].message",
				// containsString("'mimeType'"))
				.contentType(JSON).body("error.message", equalTo("Required String parameter 'mimeType' is not present"))
				.extract().response().prettyPrint();
	}

	//@Test
	public void exportExistedFileReturnOK() {
		give().header(tokenHeader, validToken).pathParam("fileId", existedFileId).param("mimeType", "testMimeType")
				.when().get("/files/{fileId}/export").then().statusCode(HttpStatus.SC_OK).body(equalTo(""));
	}

	//@Test
	public void exportNonexistedFileReturnOK() {
		give().header(tokenHeader, validToken).pathParam("fileId", nonexistedFileId).param("mimeType", "testMimeType")
				.when().get("/files/{fileId}/export").then().statusCode(HttpStatus.SC_OK).body(equalTo(""));
	}

	//@Test
	public void generateIdReturnNotNull() {
		give().header(tokenHeader, validToken).when().get("/files/generateIds").then().statusCode(HttpStatus.SC_OK)
				.body(notNullValue());
	}

	//@Test
	public void getExistedFileReturnOK() {
		give().header(tokenHeader, validToken).pathParam("fileId", existedFileId).when().get("/files/{fileId}").then()
				.statusCode(HttpStatus.SC_OK).body(notNullValue());
	}

	//@Test
	public void getFileWithoutParameterReturnOK() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(
				"Invalid number of path parameters. Expected 1, was 0. Undefined path parameters are: fileId.");

		give().header(tokenHeader, validToken).when().get("/files/{fileId}").then();

		// give().header(tokenHeader,validToken)
		// .pathParam("fileId", existedFileId)
		// .when().get("/files/{fileId}").then().statusCode(HttpStatus.SC_OK)
		// .body(notNullValue());
	}

	//@Test
	public void getNonexistedFileReturnOK() {
		give().header(tokenHeader, validToken).pathParam("fileId", nonexistedFileId).when().get("/files/{fileId}")
				.then().statusCode(HttpStatus.SC_NOT_FOUND).body("error.message", containsString("File not found"));
	}

	@Test
	public void listWithoutParameterShouldReturnError() {
		give().header(tokenHeader, validToken).when().post("/files/list").then()
				.statusCode(HttpStatus.SC_BAD_REQUEST);
	}

	//@Test
	public void listWithParameterShouldOK() {
		Response res = give().header(tokenHeader, validToken).param("pageToken", "1").param("pageSize", "20").when()
				.post("/files/list").then().statusCode(HttpStatus.SC_OK).contentType(JSON)
				.body("kind", equalTo("drive#fileList"), "nextPageToken", equalTo(2)).extract().response();

		// String jsonResponse = res.prettyPrint();
		String jsonResponse = res.asString();

		try {
			@SuppressWarnings("unchecked")
			FileList<DriveFile> result = JsonFormat.objectMapper.readValue(jsonResponse, FileList.class);
			logger.info("files count[{}]", result.getFiles().size());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void lockExistedFileReturnOK() {
		FileLockRequest bodyData = new FileLockRequest();
		bodyData.setFileId(existedFileId);
		bodyData.setUserId(existedUserId);
		bodyData.setQuantity(10);

		give().header(tokenHeader, validToken).contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.post("/files/lock").then().statusCode(HttpStatus.SC_OK)
				.body("message", containsString("lock update status"));
	}

	//@Test
	public void lockNonexistedFileReturnOK() {
		FileLockRequest bodyData = new FileLockRequest();
		bodyData.setFileId(nonexistedFileId);
		bodyData.setUserId("userId");
		bodyData.setQuantity(1);

		give().header(tokenHeader, validToken).contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when()
				.post("/files/lock").then().statusCode(HttpStatus.SC_BAD_REQUEST)
				.body("message", equalTo("file not found or check failed"));
	}

	//@Test
	public void shareFileReturnOK() {
		give().header(tokenHeader, validToken).when().post("/files/sharing").then()
				.statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED).body(notNullValue());
	}

	//@Test
	public void getNonexistedMetadataReturnOK() {
		give().header(tokenHeader, validToken).pathParam("fileId", nonexistedFileId).when()
				.get("/files/metadata/{fileId}").then().statusCode(HttpStatus.SC_BAD_REQUEST)
				.body("message", containsString("fileId not found"));
	}

	//@Test
	public void getExistedMetadataReturnOK() {
		give().header(tokenHeader, validToken).pathParam("fileId", existedFileId).when()
				.get("/files/metadata/{fileId}").then().statusCode(HttpStatus.SC_OK)
				.body("id", containsString(existedFileId));
	}

	//@Test
	public void updateNonexistedMetadataReturnOK() {
		FileRequestbody bodyData = new FileRequestbody();
		bodyData.setId("testId");

		give().header(tokenHeader, validToken).pathParam("fileId", nonexistedFileId)
				.contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when().patch("/files/metadata/{fileId}")
				.then().statusCode(HttpStatus.SC_BAD_REQUEST)
				.body("message", containsString("file not found or check failed"));
	}

	//@Test
	public void updateMetadataReturnSuccess() {
		FileRequestbody bodyData = new FileRequestbody();
		bodyData.setMimeType("editedmimeType");

		give().header(tokenHeader, validToken).pathParam("fileId", existedFileId)
				.contentType(MediaType.APPLICATION_JSON_VALUE).body(bodyData).when().patch("/files/metadata/{fileId}")
				.then().statusCode(HttpStatus.SC_OK).body("message", containsString("file metadata update success"));
	}

	// @RequestMapping(value = "/{fileId}/copy", method = RequestMethod.POST)
	// @RequestMapping(value = "/{fileId}", method = RequestMethod.DELETE)
	// @RequestMapping(value = "/{fileId}", method = RequestMethod.GET)
	// @RequestMapping(value = "/{fileId}/export", method = RequestMethod.GET)
	//
	// @RequestMapping(value = "/create", method = RequestMethod.POST)
	// @RequestMapping(value = "/trash", method = RequestMethod.DELETE)
	// @RequestMapping(value = "/generateIds", method = RequestMethod.GET)
	//
	// @RequestMapping(value = "/list", method = RequestMethod.POST)
	// @RequestMapping(value = "/lock", method = RequestMethod.POST)
	// @RequestMapping(value = "/sharing", method = RequestMethod.POST)
	//
	// @RequestMapping(value = "/metadata/{fileId}", method = RequestMethod.GET)
	// @RequestMapping(value = "/metadata/{fileId}", method =
	// RequestMethod.PATCH)
	//
	// @RequestMapping(value = "/upload" , method = RequestMethod.POST)
	//
	// @RequestMapping(value = "/{fileId}/permissions", method =
	// RequestMethod.POST)
	// @RequestMapping(value = "/{fileId}/permissions", method =
	// RequestMethod.GET)
	// @RequestMapping(value = "/{fileId}/permissions/{permissionId}", method =
	// RequestMethod.DELETE)
	// @RequestMapping(value = "/{fileId}/permissions/{permissionId}", method =
	// RequestMethod.GET)
	// @RequestMapping(value = "/{fileId}/permissions/{permissionId}", method =
	// RequestMethod.PATCH)
	//
	// @RequestMapping(value = "/{fileId}/revisions", method =
	// RequestMethod.GET)
	// @RequestMapping(value = "/{fileId}/revisions/{revisionId}", method =
	// RequestMethod.DELETE)
	// @RequestMapping(value = "/{fileId}/revisions/{revisionId}", method =
	// RequestMethod.GET)
	// @RequestMapping(value = "/{fileId}/revisions/{revisionId}", method =
	// RequestMethod.PATCH)
}
