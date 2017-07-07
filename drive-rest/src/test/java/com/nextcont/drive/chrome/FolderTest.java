package com.nextcont.drive.chrome;

import com.nextcont.drive.utils.HttpClient;
import com.nextcont.drive.utils.Tuple;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA. User: Wangxudong Date: 2017/3/24 Time: 15:36 To
 * change this template use File | Settings | File Templates.
 */
@Slf4j
public class FolderTest {

	private final static String domainGenerateIdUrl = "http://139.196.136.113/drive/v1/files/generateIds";

	private final static String domainCreateFileUrl = "http://139.196.136.113/drive/v1/files/create";

	private final static String domainUploadFileUrl = "http://139.196.136.113/drive/v1/files/upload";

	private final static String mimeType = "application/octet-stream";

	private final static String rootId = "0APymvC2SzZuDUk9PVA";

	@Test
	public void createFilesFromFolder() {
		String folderId = createFolder("测试文件夹33");

		// createFiles();
		String folderPath = "D:/demo/samples/archive";
		folderPath = "D:/demo/samples/3d";
		createFiles(folderId, folderPath);
	}

	private String createFolder(String folderName) {
		return createFolder(rootId, folderName);
	}

	private String createFolder(String parent, String folderName) {
		String generateId = HttpClient.httpGetRequest(domainGenerateIdUrl, new HashMap<>()).v2();

		log.debug(generateId);

		String createFileJson = "{\n" + "\t\"mimeType\":\"application/vnd.google-apps.folder\",\n"
				+ "\t\"id\":\"{id}\",\n" + "\t\"name\" : \"{name}\",\n" + "\t\"parent\" : \"{parent}\"\n" + "}";
		Tuple<Integer, String> result = HttpClient.httpPostRequest(domainCreateFileUrl,
				createFileJson.replace("{id}", generateId).replace("{name}", folderName).replace("{parent}", parent));
		log.debug(result.toString());

		return generateId;
	}

	private void createFiles(String folderPath) {
		createFiles(rootId, folderPath);
	}

	private void createFiles(String parent, String folderPath) {
		File file = new File(folderPath);
		if (file.exists()) {
			List<File> uploadFiles = Arrays.asList(file.listFiles(new FilenameFilter() {// 使用匿名内部类的方法
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".igs");// 通过匿名内部类的返回值来控制指定目录下面的文件和文件夹的显示，只显示.java文件
				}
			}));

			AtomicInteger atomicInteger = new AtomicInteger(uploadFiles.size());
			ExecutorService executorService = Executors.newFixedThreadPool(8);

			CountDownLatch cdl = new CountDownLatch(atomicInteger.get());
			uploadFiles.forEach(uploadFile -> {

				Runnable task = () -> {
					String generateId = HttpClient.httpGetRequest(domainGenerateIdUrl, new HashMap<>()).v2();
					try {
						MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
						builder.addFormDataPart("file", uploadFile.getName(),
								RequestBody.create(MediaType.parse(mimeType), uploadFile));
						builder.addFormDataPart("fileId", generateId);
						builder.addFormDataPart("parent", parent);

						RequestBody requestBody = builder.build();

						Request request = new Request.Builder().url(domainUploadFileUrl).post(requestBody).build();

						OkHttpClient client = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)
								.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS)
								.retryOnConnectionFailure(true).build();

						Response response = client.newCall(request).execute();
						log.debug(response.body().string());
						cdl.countDown();
						log.info("file upload success , unfinished task count=>{}", atomicInteger.addAndGet(-1));
					} catch (Exception e) {
						e.printStackTrace();
					}
				};

				executorService.execute(task);
			});

			try {
				cdl.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("test over");
		}
	}
}
