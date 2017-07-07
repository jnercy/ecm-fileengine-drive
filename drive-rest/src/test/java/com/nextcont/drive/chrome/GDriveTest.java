package com.nextcont.drive.chrome;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Ordering;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class GDriveTest {
	private static final float default_wait_seconds = 1;
	private static final int default_page_down_numbers = 30;
	// private static final String url =
	// "https://apps.nextcont.com/drive/#!/drive/my-drive";
	private static final String url = "https://apps.inecm.cn/drive/#!/drive/my-drive";
	private static final String file_new_remove = "D:\\demo\\samples\\test\\audio\\Chains.amr";
	private static final String file_new_trash = "D:\\demo\\samples\\test\\audio\\Chains.ogg";
	private static final String file_new_trash_changed = "Chains_changed.amr";
	private static final String folder_new_remove = "folder_new_remove";
	private static final String folder_new_trash = "folder_new_trash";
	private static final String folder_new_trash_changed = "folder_new_trash_changed";
	private static final String files_from_folder = "D:\\demo\\samples\\test";// "D:\\demo\\samples\\image
	private static final String folder_new_sub = "folder_new_sub";
	private static final String files_from_folder_sub = "D:\\demo\\samples\\test";
	private static final String[] files_init = { "D:\\demo\\samples\\image\\7562-160RG40038.jpg",
			"D:\\demo\\samples\\image\\7562-160RG40038.gif" };
	private static final String[] folders_init = { "initFolder1", "initFolder2" };
	private static final String folder_tree = "rootFolder/aaa/bbb/ccc/ddd/eee/fff/ggg/hhh/iii/jjj";

	private static String file_download_path = System.getProperty("user.home") + File.separator + "Downloads"
			+ File.separator;
	private static long time_start;

	private WebDriver driver;
	private WebDriverWait wait;
	private Actions action;
	private Mouse mouse;
	private JavascriptExecutor jse;
	private WebElement element;
	private String xpath;

	@BeforeClass
	public static void setupClass() {
//		ChromeDriverManager.getInstance().setup();
		// FirefoxDriverManager.getInstance().setup();
		time_start = System.currentTimeMillis();
	}

	@AfterClass
	public static void clearClass() {
		System.out.println("test used time: " + (System.currentTimeMillis() - time_start));
	}

	@Before
	public void setupTest() {
		try {
			driver = createChromeDriverWithDownloadFolder(file_download_path);
			// driver = new ChromeDriver();
			// driver = new FirefoxDriver();

			driver.manage().window().maximize();
			// 15 seconds of timeout
			wait = new WebDriverWait(driver, 15);
			// driver.manage().timeouts().implicitlyWait(10000,
			// TimeUnit.MILLISECONDS);
			// driver.manage().deleteAllCookies();
			// driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
			// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			// driver.manage().timeouts().setScriptTimeout(10,
			// TimeUnit.SECONDS);
			// WebElement frame =
			// driver.findElement(By.className("demo-frame"));
			// driver.switchTo().frame(frame);

//			driver.get(url);
			driver.get("localhost:12345");
			// driver.navigate().refresh();
			action = new Actions(driver);
			mouse = ((HasInputDevices) driver).getMouse();
			jse = (JavascriptExecutor) driver;

			login("test@nextcont.com", "12345678");

			initData();
		} catch (Exception ex) {
			if (driver != null) {
				driver.quit();
				driver = null;
			}

			wait = null;
			action = null;
			mouse = null;

			ex.printStackTrace();
		}
	}

	@After
	public void teardown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}

		wait = null;
		action = null;
		mouse = null;
		element = null;
		xpath = "";
	}

	private void login(String userName, String password) {
		// 输入用户名
		xpath = "//div[@class='loginBox']/div[@class='loginCon']/div[@class='account']/div[@class='inputbox']/input[@id='username']";
		click(By.xpath(xpath));
		driver.findElement(By.xpath(xpath)).sendKeys(userName);
		waitAwhile();
		// 点击下一步
		xpath = "//div[@class='loginBox']/div[@class='loginCon']/div[@class='account']/div[@class='btn email']/div[@class='btn_self']";
		click(By.xpath(xpath));
		waitAwhile();
		// 输入密码
		xpath = "//div[@class='loginBox']/div[@class='loginCon']/div[@class='password']/div[@class='inputbox']/input[@id='password']";
		click(By.xpath(xpath));
		driver.findElement(By.xpath(xpath)).sendKeys(password);
		waitAwhile();
		// 点击登录
		xpath = "//div[@class='loginBox']/div[@class='loginCon']/div[@class='password']/div[@class='btn submit']/div[@class='btn_self']";
		click(By.xpath(xpath));
		waitAwhile();

		driver.navigate().to(url);
		waitAwhile(5);
	}

	private void initData() {
		setTitle("创建测试数据");
		// listAllFiles("mydrive");

		if (!hasFile()) {
			for (String file : files_init) {
				if (StringUtils.isNotBlank(file)) {
					fileCreate(file, false);
				}
			}
		}

		if (!hasFolder()) {
			for (String folder : folders_init) {
				if (StringUtils.isNotBlank(folder)) {
					folderCreate(folder, false);
				}
			}

			String[] folderTree = folder_tree.split("/");
			for (String folderName : folderTree) {
				folderCreate(folderName, false);
				waitAwhile(0.5f);
				folderSwitch(folderName, false);
			}
		}

		listFiles("mydrive");
		waitAwhile();
	}

	// 文件夹切换测试
	@Test
	public void folderSwitchTest() {
		setTitle("文件夹切换测试");

		folderSwitch(folder_tree);
		waitAwhile();
		folderSwitchBack(folder_tree, false);
		waitAwhile();
	}

	// 选中测试
	@Test
	public void selectTest() {
		setTitle("列表视图选中测试");
		select("list");
		waitAwhile();

		setTitle("网格视图选中测试试");
		changeMode("grid");
		select("grid");
		waitAwhile();
	}

	// 多个文件创建
	// @Ignore // TODO Auto-generated
	@Test
	public void createFiles() {
		setTitle("多个文件创建");
		fileCreate(files_from_folder);
	}

	// 滚动测试
	@Test
	public void scrollTest() {
		setTitle("滚动测试");

		changeMode("grid");
		scroll();

		changeMode("list");
		scroll();
	}

	// 列表视图文件拖拽
	// @Ignore // TODO Auto-generated
	@Test
	public void listFileDragDrop() {
		setTitle("列表视图文件拖拽");
		// 我的云端硬盘
		// listAllFiles("mydrive");
		waitAwhile();

		dragDrop("list");
		waitAwhile();
	}

	// 网格视图文件拖拽
	// @Ignore // TODO Auto-generated
	@Test
	public void gridFileDragDrop() {
		setTitle("网格视图文件拖拽");
		// 我的云端硬盘
		// listAllFiles("mydrive");
		waitAwhile();

		changeMode("grid");
		waitAwhile();

		dragDrop("grid");
		waitAwhile();
	}

	// 排序
	@Test
	public void sortTest() {
		setTitle("排序");

		changeMode("grid");
		waitAwhile();
		gridSort();
		waitAwhile();

		changeMode("list");
		waitAwhile();
		listSort();
		waitAwhile();
	}

	// 视图切换
	@Test
	public void viewModeChange() {
		setTitle("视图切换");

		changeMode("grid");
		waitAwhile();
		changeMode("list");
		waitAwhile();

		// listFiles("mydrive");
		changeMode("grid");
		detail();
		waitAwhile();
		changeMode("list");
		detail();
		waitAwhile();

		// listFiles("star");
		// changeMode("grid");
		// waitAwhile();
		// gridSort();
		// waitAwhile();
		// detail();
		// waitAwhile();
		// changeMode("list");
		// waitAwhile();
		// listSort();
		// waitAwhile();
		// detail();
		// waitAwhile();
		//
		// listFiles("trash");
		// changeMode("grid");
		// waitAwhile();
		// gridSort();
		// waitAwhile();
		// detail();
		// waitAwhile();
		// changeMode("list");
		// waitAwhile();
		// listSort();
		// waitAwhile();
		// detail();
		// waitAwhile();
	}

	// 文件夹创建、文件上传
	@Test
	public void folderCreateAndUploadFiles() {
		setTitle("文件夹创建、文件上传");
		String folderPath = files_from_folder_sub;
		String folderName = folder_new_sub;

		// 删除测试残留数据
		// folderRemove(folderName, false);

		listFiles("mydrive");
		waitAwhile();

		folderCreate(folderName, false);
		waitAwhile();

		folderSwitch(folderName);
		waitAwhile();

		fileCreate(folderPath);
		waitAwhile();
	}

	// 文件创建、移除、恢复
	@Test
	public void fileCreateAndTrashAndUntrash() {
		setTitle("文件创建、移除、恢复");

		String filePath = file_new_trash;
		String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
		String fileChangedName = file_new_trash_changed;

		// 删除测试残留数据
		// fileRemove(fileChangedName, false);

		listFiles("mydrive");
		waitAwhile();

		fileCreate(filePath, false);
		waitAwhile();

		fileStarred(fileName);
		waitAwhile();

		fileStarred(fileName);
		waitAwhile();

		fileStarred(fileName);
		waitAwhile();

		fileUnstar(fileName);
		waitAwhile();

		// 我的云端硬盘
		listFiles("mydrive");
		waitAwhile();

		fileRename(fileName, fileChangedName, true);
		waitAwhile();

		fileTrash(fileChangedName, true);
		waitAwhile();

		fileUntrash(fileChangedName, true);
		waitAwhile();
	}

	// 文件创建、移除、彻底删除
	@Test
	public void fileCreateAndTrashAndRemove() {
		setTitle("文件创建、移除、彻底删除");

		String filePath = file_new_remove;
		String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);

		fileCreate(filePath, false);
		waitAwhile();

		fileTrash(fileName, true);
		waitAwhile();

		fileRemove(fileName);
		waitAwhile();
	}

	// 文件夹创建、移除、恢复
	@Test
	public void folderCreateAndTrashAndUntrash() {
		setTitle("文件夹创建、移除、恢复");

		String folderName = folder_new_trash;
		String folderChangedName = folder_new_trash_changed;

		// 删除测试残留数据
		folderRemove(folderChangedName, false);

		listFiles("mydrive");
		waitAwhile();

		folderCreate(folderName, false);
		waitAwhile();

		folderSwitch(folderName);
		waitAwhile();

		// 我的云端硬盘
		listFiles("mydrive");
		waitAwhile();

		folderStarred(folder_new_trash);
		waitAwhile();

		folderStarred(folder_new_trash);
		waitAwhile();

		folderStarred(folder_new_trash);
		waitAwhile();

		folderUnstar(folder_new_trash);
		waitAwhile();

		// 我的云端硬盘
		listFiles("mydrive");
		waitAwhile();

		folderRename(folderName, folderChangedName, true);
		waitAwhile();

		folderTrash(folderChangedName, true);
		waitAwhile();

		folderUntrash(folderChangedName, false);
		waitAwhile();
	}

	// 文件夹创建、移除、彻底删除
	@Test
	public void folderCreateAndTrashAndRemove() {
		setTitle("文件夹创建、移除、彻底删除");

		String folderName = folder_new_remove;

		folderCreate(folderName, false);
		waitAwhile();
		folderTrash(folderName, true);
		waitAwhile();

		folderRemove(folderName);
		waitAwhile();
	}

	// 详细信息查看测试
	@Test
	public void detailsTest() {
		setTitle("详细信息查看测试");

		xpath = "//div[contains(@class,'icon_bar')]/div[contains(@class,'right_icon_bar')]/i[contains(@class,'details')]";
		click(By.xpath(xpath));
		waitAwhile();
		xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//div[contains(@class,'detailMsg') and contains(@class,'active')]";
		Assert.assertEquals("detail display failed: ", true, isExisted(By.xpath(xpath)));

		xpath = "//div[contains(@class,'icon_bar')]/div[contains(@class,'right_icon_bar')]/i[contains(@class,'details')]";
		click(By.xpath(xpath));
		waitAwhile();
		xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//div[contains(@class,'detailMsg') and contains(@class,'active')]";
		Assert.assertEquals("detail hide failed: ", false, isExisted(By.xpath(xpath)));

		xpath = "//div[contains(@class,'icon_bar')]/div[contains(@class,'right_icon_bar')]/i[contains(@class,'details')]";
		click(By.xpath(xpath));
		waitAwhile();

		xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//div[contains(@class,'detailMsg')]/div[contains(@class,'title')]/span[not(@class)]";
		click(By.xpath(xpath));
		waitAwhile();

		xpath = "//div[contains(@class,'icon_bar')]/div[contains(@class,'right_icon_bar')]/i[contains(@class,'details')]";
		click(By.xpath(xpath));
		waitAwhile();

		xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//div[contains(@class,'detailMsg')]//ul[contains(@class,'myDriveList')]/li[not(@class)]";
		click(By.xpath(xpath));
		waitAwhile();

		xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//div[contains(@class,'detailMsg')]//ul[contains(@class,'myDriveList')]/li[not(@class)]";
		click(By.xpath(xpath));
		waitAwhile();

		// folder details
		xpath = getFolderXpath(1);
		click(By.xpath(xpath));
		waitAwhile();

		// file details
		xpath = getFileXpath(1);
		click(By.xpath(xpath));
		waitAwhile();

		changeMode("grid");
		waitAwhile();

		xpath = "//div[contains(@class,'icon_bar')]/div[contains(@class,'right_icon_bar')]/i[contains(@class,'details')]";
		click(By.xpath(xpath));
		waitAwhile();
		click(By.xpath(xpath));
		waitAwhile();

		// folder details
		xpath = getFolderXpath(1);
		click(By.xpath(xpath));
		waitAwhile();

		// file details
		xpath = getFileXpath(1);
		click(By.xpath(xpath));
		waitAwhile();
	}

	// 右键详细信息查看测试
	@Test
	public void contextMenuDetailsTest() {
		setTitle("右键详细信息查看测试");

		// folder details
		xpath = getFolderXpath(1);
		click(By.xpath(xpath));
		waitAwhile();

		// file details
		xpath = getFileXpath(1);
		click(By.xpath(xpath));
		waitAwhile();

		changeMode("grid");
		waitAwhile();

		// folder details
		xpath = getFolderXpath(1);
		rightClick(By.xpath(xpath));
		waitAwhile();

		xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'desc')]";
		click(By.xpath(xpath));
		waitAwhile();
		xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//div[contains(@class,'detailMsg') and contains(@class,'active')]";
		Assert.assertEquals("folder detail display failed: ", true, isExisted(By.xpath(xpath)));

		// file details
		xpath = getFileXpath(1);
		rightClick(By.xpath(xpath));
		waitAwhile();

		xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'desc')]";
		click(By.xpath(xpath));
		waitAwhile();
		xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//div[contains(@class,'detailMsg') and contains(@class,'active')]";
		Assert.assertEquals("file detail display failed: ", true, isExisted(By.xpath(xpath)));
	}

	// 文件列表
	@Test
	public void fileList() {
		setTitle("文件列表");

		listFiles("sharewithme");
		waitAwhile();

		// 星标文件
		listFiles("star");
		waitAwhile();

		listFiles("recent");
		waitAwhile();

		listFiles("trash");
		waitAwhile();
	}

	// 清空回收站
	@Test
	public void emptyTrash() {
		setTitle("清空回收站");

		// 显示回收站
		listFiles("trash");

		if (hasFile()) {
			xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//div[contains(@class,'boxTitle')]/div[contains(@class,'breadNav')]/span";
			click(By.xpath(xpath));
			waitAwhile();

			xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//div[contains(@class,'boxTitle')]/div[contains(@class,'emptyTrash')]/span";
			click(By.xpath(xpath));
			waitAwhile();

			xpath = "//div[contains(@class,'popup') and contains(@class,'show')]/div[contains(@class,'popup-submit')]/span[contains(@class,'confirm_btn') and contains(text(),'确定')]";
			click(By.xpath(xpath));
			waitAwhile();

			if (hasFile()) {
				Assert.fail("empty trash failed");
			}
		} else {
			Assert.fail("no files to remove");
		}
	}

	// 列表视图文件分享
	@Ignore // TODO Auto-generated
	@Test
	public void listFileShare() {// TODO
		setTitle("列表视图文件分享");

		// file share
		fileShare(false);
		waitAwhile();
	}

	// 网格视图文件分享
	@Ignore // TODO Auto-generated
	@Test
	public void gridFileShare() {// TODO
		setTitle("网格视图文件分享");

		changeMode("grid");
		waitAwhile();

		// file share
		fileShare(true);
		waitAwhile();
	}

	private boolean downloadFileExists(String fileName) {
		System.out.println("checking file: " + file_download_path + fileName);
		return (new File(file_download_path + fileName)).exists();
	}

	// 列表视图文件下载
	@Test
	public void listFileDownload() {
		setTitle("列表视图文件下载");

		fileDownload("list");
	}

	// 网格视图文件下载
	@Test
	public void gridFileDownload() {
		setTitle("网格视图文件下载");

		changeMode("grid");
		waitAwhile();

		fileDownload("grid");
	}

	// 列表视图文件拷贝
	@Test
	public void listFileCopy() {
		setTitle("列表视图文件拷贝");

		fileCopy("list");
	}

	// 网格视图文件拷贝
	@Test
	public void gridFileCopy() {
		setTitle("网格视图文件拷贝");

		changeMode("grid");
		waitAwhile();

		fileCopy("grid");
	}

	// 列表视图文件预览
	@Test
	public void listFilePreview() {
		setTitle("列表视图文件预览");
		String fileNameContains = ".gif";
		// 我的云端硬盘
		listAllFiles("mydrive");
		waitAwhile();

		// changeMode("list");
		// waitAwhile();

		// file preview
		xpath = "//tbody[@id='listViewBox']/tr[not(@class)]/td/span[contains(@class,'filename') and contains(text(),'"
				+ fileNameContains + "')][1]";
		dblClick(By.xpath(xpath));
		waitAwhile();

		previewAction(true, false);
		waitAwhile();

		fileNameContains = ".dll";
		// file preview
		xpath = "//tbody[@id='listViewBox']/tr[not(@class)]/td/span[contains(@class,'filename') and contains(text(),'"
				+ fileNameContains + "')][1]";
		dblClick(By.xpath(xpath));
		waitAwhile();

		previewAction(false);
		waitAwhile();
	}

	// 网格视图文件预览
	@Test
	public void gridFilePreview() {
		setTitle("网格视图文件预览");
		String fileNameContains = ".gif";
		// 我的云端硬盘
		listAllFiles("mydrive");
		waitAwhile();

		changeMode("grid");
		waitAwhile();

		// file preview
		xpath = "//ul[@id='fileList']/li[contains(@title,'" + fileNameContains + "')][1]";
		dblClick(By.xpath(xpath));
		waitAwhile();

		previewAction(true, false);
		waitAwhile();

		fileNameContains = ".dll";
		// file preview
		xpath = "//ul[@id='fileList']/li[contains(@title,'" + fileNameContains + "')][1]";
		dblClick(By.xpath(xpath));
		waitAwhile();

		previewAction(false);
		waitAwhile();
	}

	private void gridSort() {
		gridSort("1");
		// 验证是否排序成功
		xpath = "//ul[@id='fileList']/li/div[@class='fileNameView']/span";
		List<WebElement> eleList = driver.findElements(By.xpath(xpath));
		List<String> attrList = new ArrayList<String>();
		attrList.clear();
		for (WebElement ele : eleList) {
			attrList.add(ele.getText());
		}
		Assert.assertEquals("grid sort name desc failed", true, Ordering.natural().reverse().isOrdered(attrList));

		gridSort("2");
		gridSort("3");
		gridSort("4");
		gridSort("5");
	}

	private void gridSort(String nth) {
		xpath = "//div[@id='gridViewBox']/div[contains(@class,'gridViewTop')]/div[contains(@class,'gridViewKK')]/span";
		click(By.xpath(xpath));
		waitAwhile();

		xpath = "//div[@id='gridViewBox']/div[contains(@class,'gridViewTop')]/div[contains(@class,'gridViewKK')]/div[contains(@class,'gridViewVal')]/div["
				+ nth + "]";
		click(By.xpath(xpath));
		waitAwhile();

		// not(ancestor::div[contains(@style,'display: none')])
		// not(contains(@style,'display:none'))
		xpath = "//div[@id='gridViewBox']/div[contains(@class,'gridViewTop')]/div[contains(@class,'gridViewKK')]/i[contains(@class,'svgicon') and not(contains(translate(normalize-space(@style), ' ', ''),'display:none'))]";
		click(By.xpath(xpath));
		waitAwhile();

		xpath = "//div[@id='gridViewBox']/div[contains(@class,'gridViewTop')]/div[contains(@class,'gridViewKK')]/i[contains(@class,'svgicon') and not(contains(translate(normalize-space(@style), ' ', ''),'display:none'))]";
		click(By.xpath(xpath));
		waitAwhile();
	}

	private long getFileSize(String[] fileSizeArr) {
		long fileSize = 0;
		if (fileSizeArr[1].equals("B")) {
			fileSize = Long.parseLong(fileSizeArr[0]);
		} else if (fileSizeArr[1].equals("KB")) {
			fileSize = Long.parseLong(fileSizeArr[0]) * 1024;
		} else if (fileSizeArr[1].equals("MB")) {
			fileSize = Long.parseLong(fileSizeArr[0]) * 1024 * 1024;
		} else if (fileSizeArr[1].equals("GB")) {
			fileSize = Long.parseLong(fileSizeArr[0]) * 1024 * 1024 * 1024;
		}

		return fileSize;
	}

	private void listSort() {
		listSort("1");
		// 验证是否排序成功
		xpath = "//tbody[@id='listViewBox']/tr[not(@class)]/td/span[@class='filename']";
		List<WebElement> eleList = driver.findElements(By.xpath(xpath));
		List<String> attrList = new ArrayList<String>();
		attrList.clear();
		for (WebElement ele : eleList) {
			attrList.add(ele.getText());
		}
		Assert.assertEquals("list sort name asc failed", true, Ordering.natural().isOrdered(attrList));

		listSort("1");
		// 验证是否排序成功
		xpath = "//tbody[@id='listViewBox']/tr[not(@class)]/td/span[@class='filename']";
		eleList = driver.findElements(By.xpath(xpath));
		attrList.clear();
		for (WebElement ele : eleList) {
			attrList.add(ele.getText());
		}
		Assert.assertEquals("list sort name desc failed", true, Ordering.natural().reverse().isOrdered(attrList));

		listSort("5");
		// 验证是否排序成功
		xpath = "//tbody[@id='listViewBox']/tr[not(@class)]/td[4]";
		eleList = driver.findElements(By.xpath(xpath));
		List<Long> sizeList = new ArrayList<Long>();
		sizeList.clear();
		for (WebElement ele : eleList) {
			sizeList.add(getFileSize(ele.getText().split(" ")));
		}
		Assert.assertEquals("list sort size asc failed", true, Ordering.natural().isOrdered(sizeList));

		listSort("5");
		// 验证是否排序成功
		xpath = "//tbody[@id='listViewBox']/tr[not(@class)]/td[4]";
		eleList = driver.findElements(By.xpath(xpath));
		sizeList.clear();
		for (WebElement ele : eleList) {
			sizeList.add(getFileSize(ele.getText().split(" ")));
		}
		Assert.assertEquals("list sort size desc failed", true, Ordering.natural().reverse().isOrdered(sizeList));

		listSort2("1");
		// 验证是否排序成功
		xpath = "//tbody[@id='listViewBox']/tr[not(@class)]/td[3]";
		eleList = driver.findElements(By.xpath(xpath));
		attrList.clear();
		for (WebElement ele : eleList) {
			attrList.add(ele.getText());
		}
		Assert.assertEquals("list sort time-lastmodify desc failed", true,
				Ordering.natural().reverse().isOrdered(attrList));

		listSort2("2");
		listSort2("3");
	}

	private void listSort(String nth) {
		xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//table//th[" + nth
				+ "]/span[not(contains(translate(normalize-space(@style), ' ', ''),'display:none'))]/i";
		click(By.xpath(xpath));
		waitAwhile();
	}

	private void listSort2(String nth) {
		xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//table//th[3]/span[contains(@class,'hoveEle')]";
		click(By.xpath(xpath));
		waitAwhile();

		xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//table//th[3]/ul[contains(@class,'boxFolder')]/li["
				+ nth + "]";
		click(By.xpath(xpath));
		waitAwhile();

		xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//table//th[3]/span[contains(@class,'marginTop2') and not(contains(translate(normalize-space(@style), ' ', ''),'display:none'))]/i[contains(@class,'svgicon') and not(contains(translate(normalize-space(@style), ' ', ''),'display:none'))]";
		click(By.xpath(xpath));
		waitAwhile();

		xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]//table//th[3]/span[contains(@class,'marginTop2') and not(contains(translate(normalize-space(@style), ' ', ''),'display:none'))]/i[contains(@class,'svgicon') and not(contains(translate(normalize-space(@style), ' ', ''),'display:none'))]";
		click(By.xpath(xpath));
		waitAwhile();
	}

	private void detail() {
		xpath = "//div[contains(@class,'icon_bar')]/div[contains(@class,'right_icon_bar')]/i[contains(@class,'details')]";
		click(By.xpath(xpath));
		waitAwhile();

		click(By.xpath(xpath));
		waitAwhile();
	}

	private void select(String mode) {
		String selected = "//tbody[@id='listViewBox']/tr[contains(@class,'active')]";
		String nonselected = "//tbody[@id='listViewBox']/tr[not(contains(@class,'active'))]";
		if ("grid".equalsIgnoreCase(mode)) {
			selected = "//ul[@id='fileList']/li[contains(@class,'active')]";
			nonselected = "//ul[@id='fileList']/li[not(contains(@class,'active'))]";
		}

		waitAwhile();
		action.keyDown(Keys.CONTROL).sendKeys(Keys.chord("A")).keyUp(Keys.CONTROL).sendKeys(Keys.NULL).perform();
		// element.sendKeys(Keys.chord(Keys.CONTROL, "a"));

		int iCount = 0;
		iCount = driver.findElements(By.xpath(nonselected)).size();
		Assert.assertEquals(mode + " select all failed", 0, iCount);

		waitAwhile(2);
		xpath = "//div[@class='rightBox']/div[contains(@class,'rightShow')]//div[@class='boxTitle']/div[contains(@class,'breadNav')]/span";
		// 点击“新建”后面的位置，我的云端硬盘前面
		action.moveToElement(driver.findElement(By.xpath(xpath)), -20, 10).click().build().perform();

		waitAwhile(2);
		iCount = driver.findElements(By.xpath(selected)).size();
		Assert.assertEquals(mode + " deselect all failed", 0, iCount);

		xpath = getXpath(1);
		WebElement ele1 = driver.findElement(By.xpath(xpath));
		xpath = getXpath(5);
		WebElement ele2 = driver.findElement(By.xpath(xpath));
		xpath = getXpath(8);
		WebElement ele3 = driver.findElement(By.xpath(xpath));

		action.keyDown(Keys.SHIFT).click(ele1).click(ele2).keyUp(Keys.SHIFT).sendKeys(Keys.NULL).build().perform();
		waitAwhile(3);
		iCount = driver.findElements(By.xpath(selected)).size();
		Assert.assertEquals(mode + " shift select failed", 5, iCount);
		click();
		waitAwhile(2);

		action.keyDown(Keys.CONTROL).click(ele1).click(ele3).keyUp(Keys.CONTROL).sendKeys(Keys.NULL).build().perform();
		waitAwhile(3);
		iCount = driver.findElements(By.xpath(selected)).size();
		Assert.assertEquals(mode + " ctl select failed", 3, iCount);
	}

	private void dragDrop(String mode) {
		List<WebElement> ddFileList;
		List<String> ddFileNameList = new ArrayList<String>();
		String selectedXpath = "//ul[@id='fileList']/li[contains(@class,'active')]/div[@class='fileNameView']/span";
		if ("list".equalsIgnoreCase(mode)) {
			selectedXpath = "//tbody[@id='listViewBox']/tr[contains(@class,'active')]/td/span";
		}

		xpath = getFolderXpath(1);
		WebElement folder = driver.findElement(By.xpath(xpath));
		xpath = getFileXpath(1);
		WebElement file1 = driver.findElement(By.xpath(xpath));
		xpath = getFileXpath(3);
		WebElement file3 = driver.findElement(By.xpath(xpath));
		xpath = getFileXpath(6);
		WebElement file6 = driver.findElement(By.xpath(xpath));

		// 单个文件拖拽
		mouse.click(((Locatable) file1).getCoordinates());
		waitAwhile(2);
		ddFileList = driver.findElements(By.xpath(selectedXpath));
		for (WebElement ele : ddFileList) {
			ddFileNameList.add(ele.getText());
		}
		waitAwhile(2);
		mouse.mouseDown(((Locatable) file1).getCoordinates());
		waitAwhile(2);
		mouse.mouseMove(((Locatable) file1).getCoordinates(), 60, -20);
		waitAwhile(3);
		// xpath = "//div[@class='drag']/div[@class='drag-text']";
		xpath = "//div[@class='drag']/div[@class='drag-circle']";
		element = driver.findElement(By.xpath(xpath));
		String fileCount = element.getText();
		Assert.assertEquals(mode + " single file d&d failed", "1", fileCount);
		mouse.mouseMove(((Locatable) folder).getCoordinates());
		waitAwhile(3);
		mouse.mouseUp(((Locatable) folder).getCoordinates());
		waitAwhile(2);
		// 检查文件是否拖拽成功
		folderSwitch(1);
		waitAwhile(2);
		for (String fileName : ddFileNameList) {
			Assert.assertEquals(mode + " single file d&d failed, not exist:" + fileName, true,
					isExisted(By.xpath(getFileXpath(fileName))));
		}
		listFiles("mydrive");
		waitAwhile();
		changeMode("grid");
		waitAwhile();

		// 多个连续文件拖拽
		xpath = getFolderXpath(1);
		folder = driver.findElement(By.xpath(xpath));
		xpath = getFileXpath(1);
		file1 = driver.findElement(By.xpath(xpath));
		xpath = getFileXpath(3);
		file3 = driver.findElement(By.xpath(xpath));
		xpath = getFileXpath(6);
		file6 = driver.findElement(By.xpath(xpath));
		action.keyDown(Keys.SHIFT).click(file1).click(file6).keyUp(Keys.SHIFT).sendKeys(Keys.NULL).build().perform();
		waitAwhile(2);
		ddFileList = driver.findElements(By.xpath(selectedXpath));
		ddFileNameList.clear();
		for (WebElement ele : ddFileList) {
			ddFileNameList.add(ele.getText());
		}
		waitAwhile(2);
		mouse.mouseDown(((Locatable) file1).getCoordinates());
		waitAwhile(2);
		mouse.mouseMove(((Locatable) file1).getCoordinates(), 60, -20);
		waitAwhile(3);
		// xpath = "//div[@class='drag']/div[@class='drag-text']";
		xpath = "//div[@class='drag']/div[@class='drag-circle']";
		element = driver.findElement(By.xpath(xpath));
		fileCount = element.getText();
		Assert.assertEquals(mode + " multiple continuous files d&d failed", "6", fileCount);
		mouse.mouseMove(((Locatable) folder).getCoordinates());
		waitAwhile(3);
		mouse.mouseUp(((Locatable) folder).getCoordinates());
		waitAwhile(2);
		// 检查文件是否拖拽成功
		folderSwitch(1);
		waitAwhile(2);
		for (String fileName : ddFileNameList) {
			Assert.assertEquals(mode + " multiple continuous files d&d failed, not exist:" + fileName, true,
					isExisted(By.xpath(getFileXpath(fileName))));
		}
		listFiles("mydrive");
		waitAwhile();

		// 多个间隔文件拖拽
		xpath = getFolderXpath(1);
		folder = driver.findElement(By.xpath(xpath));
		xpath = getFileXpath(1);
		file1 = driver.findElement(By.xpath(xpath));
		xpath = getFileXpath(3);
		file3 = driver.findElement(By.xpath(xpath));
		xpath = getFileXpath(6);
		file6 = driver.findElement(By.xpath(xpath));
		action.keyDown(Keys.CONTROL).click(file1).click(file3).click(file6).keyUp(Keys.CONTROL).sendKeys(Keys.NULL)
				.build().perform();
		waitAwhile(2);
		ddFileList = driver.findElements(By.xpath(selectedXpath));
		ddFileNameList.clear();
		for (WebElement ele : ddFileList) {
			ddFileNameList.add(ele.getText());
		}
		waitAwhile(2);
		mouse.mouseDown(((Locatable) file1).getCoordinates());
		waitAwhile(2);
		mouse.mouseMove(((Locatable) file1).getCoordinates(), 60, -20);
		waitAwhile(3);
		// xpath = "//div[@class='drag']/div[@class='drag-text']";
		xpath = "//div[@class='drag']/div[@class='drag-circle']";
		element = driver.findElement(By.xpath(xpath));
		fileCount = element.getText();
		Assert.assertEquals(mode + " multiple discontinuous files d&d failed", "3", fileCount);
		mouse.mouseMove(((Locatable) folder).getCoordinates());
		waitAwhile(3);
		mouse.mouseUp(((Locatable) folder).getCoordinates());
		// 检查文件是否拖拽成功
		folderSwitch(1);
		waitAwhile(2);
		for (String fileName : ddFileNameList) {
			Assert.assertEquals(mode + " multiple discontinuous files d&d failed, not exist:" + fileName, true,
					isExisted(By.xpath(getFileXpath(fileName))));
		}
		listFiles("mydrive");
		waitAwhile();
	}

	private void fileCreate(String folderPath) {
		// 我的云端硬盘
		// listFiles("mydrive");
		// view mode change to grid
		// changeMode("grid");
		// waitAwhile();

		element = driver.findElement(By.id("file_upload_btn"));
		jse.executeScript(
				"arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; arguments[0].style.width = '1px'; arguments[0].style.opacity = 1",
				element);

		waitAwhile();

		File dir = new File(folderPath);
		Assert.assertEquals("create file failed, folder not exist: " + folderPath, true, dir.exists());

		String fileName;
		try {
			System.out.println("Getting all files in " + dir.getCanonicalPath() + " including those in subdirectories");
			List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
			for (File file : files) {
				System.out.println("file: " + file.getCanonicalPath());

				fileName = file.getCanonicalPath();
				element.sendKeys(fileName);
				fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

				// 检查文件是否创建成功
				Assert.assertEquals("create file failed: " + fileName, true,
						isExisted(By.xpath(getFileXpath(fileName)), true));
			}
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("create file failed");
		}
	}

	private void fileCreate(String fileName, boolean useContextMenu) {
		File file = new File(fileName);
		Assert.assertEquals("create file failed, not exist: " + fileName, true, file.exists());

		// 我的云端硬盘
		listFiles("mydrive");
		// view mode change to grid
		// changeMode("grid");
		waitAwhile();

		if (useContextMenu) {
			xpath = "//div[contains(@class,'scrollBox')]/div[@id='gridViewBox']/div[contains(@class,'folderView')]";
			mouseMove(By.xpath(xpath), 40, 0);
			// Right Click
			rightClick();
			waitAwhile();
			xpath = "//div[contains(@class,'menu-list') and contains(@class,'newmenu')]/li[i[contains(@class,'uploadfile')]]";
		} else {
			// 新建
			xpath = "//div[contains(@class,'leftNav')]//div[contains(@class,'uploadFile')]//span[1]";
			click(By.xpath(xpath));
			waitAwhile();

			// 新建文件
			xpath = "//div[contains(@class,'leftNav')]//div[contains(@class,'uploadFile')]/ul/li/i[contains(@class,'uploadfile')]";
		}

		// click(By.xpath(xpath));
		waitAwhile();

		element = driver.findElement(By.id("file_upload_btn"));
		jse.executeScript(
				"arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; arguments[0].style.width = '1px'; arguments[0].style.opacity = 1",
				element);

		waitAwhile();
		element.sendKeys(fileName);

		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
		System.out.println(fileName);

		// 检查文件是否创建成功
		Assert.assertEquals("create file failed: " + fileName, true, isExisted(By.xpath(getFileXpath(fileName)), true));
	}

	private void fileTrash(String fileName, boolean useContextMenu) {
		// listFiles("my_drive");
		// changeMode("grid");
		// waitAwhile();

		xpath = getFileXpath(fileName);

		if (useContextMenu) {
			// 右键删除
			// Right Click
			rightClick(By.xpath(xpath));
			waitAwhile();
			xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'remove')]";
		} else {
			click(By.xpath(xpath));
			xpath = "//div[contains(@class,'icon_bar')]/div[contains(@class,'left_icon_bar')]/i[contains(@class,'delete')]";
		}

		click(By.xpath(xpath));
		waitAwhile();
	}

	private void fileRemove(String fileName) {
		fileRemove(fileName, true);
	}

	private void fileRemove(String fileName, boolean fail) {
		// 回收站
		listAllFiles("trash");
		// changeMode("grid");
		waitAwhile();

		// 文件是否已删除
		xpath = getFileXpath(fileName);
		if (!isExisted(By.xpath(xpath))) {
			if (fail) {
				Assert.fail("file trash failed");
			} else {
				return;
			}
		}

		rightClick(By.xpath(xpath));
		waitAwhile();

		// 点击彻底删除
		xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'trash') and not(contains(@class,'untrash'))]/li[contains(@class,'remove')]";
		click(By.xpath(xpath));
		waitAwhile();

		// 点击确认
		xpath = "//div[contains(@class,'popup') and contains(@class,'show')]/div[contains(@class,'popup-submit')]/span[contains(@class,'confirm_btn')]";
		click(By.xpath(xpath));
		waitAwhile();

		// 检查文件是否已删除
		xpath = getFileXpath(fileName);
		if (isExisted(By.xpath(xpath))) {
			if (fail) {
				Assert.fail("remove file failed");
			} else {
				return;
			}
		}
	}

	private void fileUntrash(String fileName, boolean useContextMenu) {
		// 回收站
		listAllFiles("trash");
		// changeMode("grid");
		waitAwhile();

		// 我的文件
		// listFiles("my_drive");
		// changeMode("grid");
		// waitAwhile();

		// 文件是否已删除
		xpath = getFileXpath(fileName);
		Assert.assertEquals("file untrash failed: " + fileName, true, isExisted(By.xpath(xpath)));

		if (useContextMenu) {
			// Right Click
			rightClick(By.xpath(xpath));
			waitAwhile();
			// 恢复
			xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu')]/li[contains(@class,'version')]";
			click(By.xpath(xpath));
		} else {
			dblClick(By.xpath(xpath));
			waitAwhile();

			xpath = "//div[contains(@class,'popup') and contains(@class,'show')]/div[contains(@class,'popup-submit')]/span[contains(@class,'confirm_btn') and contains(text(),'还原')]";
			click(By.xpath(xpath));
		}
		waitAwhile();

		// 我的文件
		listFiles("mydrive");
		changeMode("grid");
		waitAwhile();
	}

	private void fileRename(String fileName, String changedFileName, boolean useContextMenu) {
		// changeMode("grid");
		waitAwhile();

		xpath = getFileXpath(fileName);

		if (useContextMenu) {
			// Right Click
			rightClick(By.xpath(xpath));
			waitAwhile();
			xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'rename')]";
		} else {
			click(By.xpath(xpath));
			xpath = "//div[contains(@class,'icon_bar')]/div[contains(@class,'left_icon_bar')]/i[contains(@class,'rename')]";
		}

		// rename
		click(By.xpath(xpath));

		// set file new name
		By ele = By.id("newfilename");
		wait.until(ExpectedConditions.presenceOfElementLocated(ele));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		driver.findElement(ele).clear();
		driver.findElement(ele).sendKeys(changedFileName);
		waitAwhile();

		// submit
		xpath = "//div[contains(@class,'popup') and contains(@class,'show')]/div[contains(@class,'popup-submit')]/span[contains(@class,'confirm_btn')]";
		click(By.xpath(xpath));
		waitAwhile();

		// check new file name exists
		Assert.assertEquals("rename file failed: " + changedFileName, true,
				isExisted(By.xpath(getFileXpath(changedFileName))));
	}

	private void fileStarred(String fileName) {
		// changeMode("grid");
		waitAwhile();

		// 文件夹是否加星标
		xpath = getFileXpath(fileName);
		// if (!isExisted(By.xpath(xpath))) {
		// Assert.fail("file starred failed");
		// }

		// Right Click
		rightClick(By.xpath(xpath));
		waitAwhile();

		xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'starred')]";
		click(By.xpath(xpath));
		waitAwhile();
	}

	private void fileUnstar(String fileName) {
		// 星标文件
		listAllFiles("star");
		// changeMode("grid");
		waitAwhile();

		// 文件夹是否加星标
		xpath = getFileXpath(fileName);
		Assert.assertEquals("file starred failed: " + fileName, true, isExisted(By.xpath(xpath)));

		// Right Click
		rightClick(By.xpath(xpath));
		waitAwhile();

		// 恢复
		xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'starred')]";
		click(By.xpath(xpath));
		waitAwhile();

		// 检查文件夹是否已移除星标
		Assert.assertEquals("remove star failed: " + fileName, false, isExisted(By.xpath(getFileXpath(fileName))));
	}

	private void fileShare(boolean useContextMenu) {
		fileShare(null, useContextMenu);
	}

	private void fileShare(String fileName, boolean useContextMenu) {// TODO
		// changeMode("grid");
		waitAwhile();

		// 我的文件
		// listFiles("my_drive");
		// changeMode("grid");
		// waitAwhile();

		// 文件是否存在
		if (StringUtils.isNotBlank(fileName)) {
			xpath = getFileXpath(fileName);
		} else {
			xpath = getFileXpath(1);
		}
		Assert.assertEquals("file share failed: " + fileName, true, isExisted(By.xpath(xpath)));

		if (useContextMenu) {
			// Right Click
			rightClick(By.xpath(xpath));
			waitAwhile();
			xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'share')]";
		} else {
			click(By.xpath(xpath));
			xpath = "//div[contains(@class,'icon_bar')]/div[contains(@class,'left_icon_bar')]/i[contains(@class,'share')]";
		}

		// rename
		click(By.xpath(xpath));

		// // set file new name
		// By ele = By.id("newfilename");
		// wait.until(ExpectedConditions.presenceOfElementLocated(ele));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		// driver.findElement(ele).clear();
		// driver.findElement(ele).sendKeys(changedFileName);
		// waitAwhile();
		//
		// // submit
		// xpath = "//div[contains(@class,'popup') and
		// contains(@class,'show')]/div[contains(@class,'popup-submit')]/span[contains(@class,'confirm_btn')]";
		// click(By.xpath(xpath));
		// waitAwhile();
		//
		// // check new file name exists
		// xpath = "//ul[@id='fileList']/li[contains(@title,'" + changedFileName
		// + "')]";
		// if (!isExisted(By.xpath(xpath))) {
		// Assert.fail("rename file failed");
		// }
	}

	// 文件下载
	private void fileDownload(String mode) {
		String selectedXpath = "//ul[@id='fileList']/li[contains(@class,'active')]/div[@class='fileNameView']/span";
		if ("list".equalsIgnoreCase(mode)) {
			selectedXpath = "//tbody[@id='listViewBox']/tr[contains(@class,'active')]/td/span";
		}

		xpath = getFileXpath(1);
		element = driver.findElement(By.xpath(xpath));
		String fileName = element.getText();
		// file download
		rightClick(By.xpath(xpath));
		waitAwhile();

		xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'download') and span[text()='下载']]";
		click(By.xpath(xpath));
		waitAwhile(8);
		Assert.assertEquals(mode + " download file failed: " + fileName, true, downloadFileExists(fileName));

		xpath = getFileXpath(3);
		WebElement file3 = driver.findElement(By.xpath(xpath));
		click(By.xpath(xpath));
		xpath = getFileXpath(6);
		WebElement file6 = driver.findElement(By.xpath(xpath));
		action.keyDown(Keys.SHIFT).click(file3).click(file6).keyUp(Keys.SHIFT).sendKeys(Keys.NULL).build().perform();
		List<WebElement> ddFileList = driver.findElements(By.xpath(selectedXpath));
		List<String> ddFileNameList = new ArrayList<String>();
		for (WebElement ele : ddFileList) {
			ddFileNameList.add(ele.getText());
		}
		// multiple file download
		rightClick(By.xpath(xpath));
		waitAwhile();
		xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'download') and span[text()='下载']]";
		click(By.xpath(xpath));
		waitAwhile(10);
		for (String fName : ddFileNameList) {
			Assert.assertEquals(mode + " multiple download file failed: " + fName, true, downloadFileExists(fName));
		}
	}

	// 文件拷贝
	private void fileCopy(String mode) {
		xpath = getFileXpath(1);
		element = driver.findElement(By.xpath(xpath));
		String fileName = element.getText();
		fileName += "（复制）";
		// file copy
		rightClick(By.xpath(xpath));
		waitAwhile();

		xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'copy')]";
		click(By.xpath(xpath));
		waitAwhile();

		// 检查文件是否拷贝成功
		Assert.assertEquals(mode + " copy file failed: " + fileName, true, isExisted(By.xpath(getFileXpath(fileName))));
		click(By.xpath(xpath));
		waitAwhile();
	}

	private void folderSwitch(int nth) {
		xpath = getFolderXpath(nth);
		dblClick(By.xpath(xpath));
	}

	private void folderSwitch(String folderName) {
		folderSwitch(folderName, true);
	}

	private void folderCreate(String folderName, boolean useContextMenu) {
		// view mode change to grid
		// changeMode("grid");
		waitAwhile();

		if (useContextMenu) {
			xpath = "//div[contains(@class,'scrollBox')]/div[@id='gridViewBox']/div[contains(@class,'folderView')]";
			mouseMove(By.xpath(xpath), 40, 0);
			// Right Click
			rightClick();
			waitAwhile();

			xpath = "//div[contains(@class,'menu-list') and contains(@class,'newmenu')]/li[i[contains(@class,'newfolder')]]";
		} else {
			// 新建
			xpath = "//div[contains(@class,'leftNav')]//div[contains(@class,'uploadFile')]//span[1]";
			click(By.xpath(xpath));
			waitAwhile();

			// 新建文件夹
			xpath = "//div[contains(@class,'leftNav')]//div[contains(@class,'uploadFile')]/ul/li/i[contains(@class,'newfolder')]";
		}

		click(By.xpath(xpath));
		waitAwhile();

		// set new folder name
		element = driver.findElement(By.id("newfolderinput"));
		element.sendKeys(folderName);
		waitAwhile();

		// submit
		xpath = "//div[contains(@class,'popup') and contains(@class,'show')]/div[contains(@class,'popup-submit')]//span[contains(@class,'confirm_btn')]";
		click(By.xpath(xpath));
		waitAwhile();

		// check new folder exists
		Assert.assertEquals("create folder failed: " + folderName, true,
				isExisted(By.xpath(getFolderXpath(folderName)), true));
	}

	private void folderTrash(String folderName, boolean useContextMenu) {
		// listFiles("mydrive");
		// changeMode("grid");
		// waitAwhile();

		xpath = getFolderXpath(folderName);
		if (useContextMenu) {
			// 右键删除
			// Right Click
			rightClick(By.xpath(xpath));
			waitAwhile();

			xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'remove')]";
		} else {
			click(By.xpath(xpath));
			xpath = "//div[contains(@class,'icon_bar')]/div[contains(@class,'left_icon_bar')]/i[contains(@class,'delete')]";
		}

		// trash
		click(By.xpath(xpath));
		waitAwhile();
	}

	private void folderRemove(String folderName) {
		folderRemove(folderName, true);
	}

	private void folderRemove(String folderName, boolean fail) {
		// 回收站
		listFiles("trash");
		// changeMode("grid");
		waitAwhile();

		// 文件夹是否已删除
		xpath = getFolderXpath(folderName);
		if (!isExisted(By.xpath(xpath))) {
			if (fail) {
				Assert.fail("remove folder failed");
			} else {
				return;
			}
		}

		rightClick(By.xpath(xpath));
		waitAwhile();

		// 点击彻底删除
		xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'trash') and not(contains(@class,'untrash'))]/li[contains(@class,'remove')]";
		click(By.xpath(xpath));
		waitAwhile();

		// 点击确认
		xpath = "//div[contains(@class,'popup') and contains(@class,'show')]/div[contains(@class,'popup-submit')]/span[contains(@class,'confirm_btn')]";
		click(By.xpath(xpath));
		waitAwhile();

		// 检查文件夹是否已删除
		xpath = getFolderXpath(folderName);
		if (isExisted(By.xpath(xpath))) {
			if (fail) {
				Assert.fail("remove folder failed");
			} else {
				return;
			}
		}
	}

	private void folderUntrash(String folderName, boolean useContextMenu) {
		// 回收站
		listFiles("trash");
		// changeMode("grid");
		waitAwhile();

		// 我的文件
		// listFiles("mydrive");
		// changeMode("grid");
		// waitAwhile();

		// 文件夹是否已删除
		xpath = getFolderXpath(folderName);
		Assert.assertEquals("recovery folder failed: " + folderName, true, isExisted(By.xpath(xpath)));

		if (useContextMenu) {
			// Right Click
			rightClick(By.xpath(xpath));
			waitAwhile();

			xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu')]/li[contains(@class,'version')]";
			// 恢复
			click(By.xpath(xpath));
		} else {
			dblClick(By.xpath(xpath));
			waitAwhile();

			xpath = "//div[contains(@class,'popup') and contains(@class,'show')]/div[contains(@class,'popup-submit')]/span[contains(@class,'confirm_btn') and contains(text(),'还原')]";
			// 还原
			click(By.xpath(xpath));
		}
		waitAwhile();

		// 我的文件
		listFiles("mydrive");
		changeMode("grid");
		waitAwhile();
	}

	private void folderRename(String folderName, String changedFolderName, boolean useContextMenu) {
		// changeMode("grid");
		waitAwhile();

		xpath = getFolderXpath(folderName);
		Assert.assertEquals("folder rename failed: " + folderName, true, isExisted(By.xpath(xpath)));

		if (useContextMenu) {
			// Right Click
			rightClick(By.xpath(xpath));
			waitAwhile();

			xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'rename')]";
		} else {
			click(By.xpath(xpath));
			xpath = "//div[contains(@class,'icon_bar')]/div[contains(@class,'left_icon_bar')]/i[contains(@class,'rename')]";
		}

		// rename
		click(By.xpath(xpath));
		waitAwhile();

		// set folder new name
		By ele = By.id("newfilename");
		wait.until(ExpectedConditions.presenceOfElementLocated(ele));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		driver.findElement(ele).clear();
		driver.findElement(ele).sendKeys(changedFolderName);
		// element = driver.findElement(By.id("newfilename"));
		// element.clear();
		// element.sendKeys(changedFolderName);
		waitAwhile();

		// submit
		xpath = "//div[contains(@class,'popup') and contains(@class,'show')]/div[contains(@class,'popup-submit')]//span[contains(@class,'confirm_btn')]";
		click(By.xpath(xpath));
		waitAwhile();

		// check new folder name exists
		Assert.assertEquals("folder rename failed: " + changedFolderName, true,
				isExisted(By.xpath(getFolderXpath(changedFolderName))));
	}

	private void folderStarred(String folderName) {
		// changeMode("grid");
		waitAwhile();

		// 文件夹是否加星标
		xpath = getFolderXpath(folderName);
		// if (!isExisted(By.xpath(xpath))) {
		// Assert.fail("folder starred failed");
		// }

		// Right Click
		rightClick(By.xpath(xpath));
		waitAwhile();

		xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'starred')]";
		click(By.xpath(xpath));
		waitAwhile();
	}

	private void folderUnstar(String folderName) {
		// 星标文件
		listFiles("star");
		// changeMode("grid");
		waitAwhile();

		// 文件夹是否加星标
		xpath = getFolderXpath(folderName);
		Assert.assertEquals("folde starred failed: " + folderName, true, isExisted(By.xpath(xpath)));

		// Right Click
		rightClick(By.xpath(xpath));
		waitAwhile();

		xpath = "//ul[contains(@class,'forfiles') and contains(@class,'contextmenu') and contains(@class,'untrash')]/li[contains(@class,'starred')]";
		// 恢复
		click(By.xpath(xpath));
		waitAwhile();

		// 检查文件夹是否已移除星标
		xpath = getFolderXpath(folderName);
		Assert.assertEquals("remove star failed: " + folderName, false, isExisted(By.xpath(xpath)));
	}

	private void folderSwitchBack(String folderTreeName, boolean useTree) {
		String[] folderTree = folderTreeName.split("/");

		if (useTree) {
			xpath = "//div[contains(@class,'leftNav')]/ul[contains(@class,'navBox')]/div/li/div/div/div[contains(@class,'drive-icon')]/i[contains(@class,'unfolded')]";
			click(By.xpath(xpath));
			waitAwhile(0.5f);

			String reps = "li[@class='toggle-li']/";

			for (String folderName : folderTree) {

				xpath = "//div[contains(@class,'leftNav')]/ul[contains(@class,'navBox')]//div/" + reps
						+ "div/div[contains(@class,'enter-file')]" + "/div[contains(@class,'tree-file') and text()='"
						+ folderName + "']";

				click(By.xpath(xpath));
				waitAwhile(0.5f);

				xpath = "//div[contains(@class,'leftNav')]/ul[contains(@class,'navBox')]//div/" + reps
						+ "div/div[contains(@class,'enter-file')]" + "/div[contains(@class,'tree-file') and text()='"
						+ folderName + "']" + "/../../div/div[contains(@class,'toggle-file')]/i";
				click(By.xpath(xpath));
				waitAwhile(0.5f);

				reps += "ul/div/li[@class='toggle-li']/";
			}

		} else {
			for (int i = folderTree.length - 2; i > -1; i--) {
				String folderName = folderTree[i];
				xpath = "//div[contains(@class,'rightBox')]/div[contains(@class,'rightShow')]/div/div[contains(@class,'boxTitle')]/div[contains(@class,'breadNav')]/div/p[strong[text()='"
						+ folderName + "']]";
				click(By.xpath(xpath));
				waitAwhile(0.5f);
			}
		}

		waitAwhile();
	}

	private void folderSwitch(String folderTreeName, boolean useTree) {
		// view mode change to grid
		// changeMode("grid");
		// waitAwhile();

		String[] folderTree = folderTreeName.split("/");

		if (useTree) {
			xpath = "//div[contains(@class,'leftNav')]/ul[contains(@class,'navBox')]/div/li/div/div/div[contains(@class,'drive-icon')]/i[contains(@class,'unfolded')]";
			click(By.xpath(xpath));
			waitAwhile(0.5f);

			String reps = "li[@class='toggle-li']/";
			for (String folderName : folderTree) {
				xpath = "//div[contains(@class,'leftNav')]/ul[contains(@class,'navBox')]//div/" + reps
						+ "div/div[contains(@class,'enter-file')]" + "/div[contains(@class,'tree-file') and text()='"
						+ folderName + "']";

				click(By.xpath(xpath));
				waitAwhile(0.5f);

				xpath = "//div[contains(@class,'leftNav')]/ul[contains(@class,'navBox')]//div/" + reps
						+ "div/div[contains(@class,'enter-file')]" + "/div[contains(@class,'tree-file') and text()='"
						+ folderName + "']" + "/../../div/div[contains(@class,'toggle-file')]/i";
				click(By.xpath(xpath));
				waitAwhile(0.5f);

				reps += "ul/div/li[@class='toggle-li']/";
			}

		} else {
			changeMode("grid");
			waitAwhile();

			for (String folderName : folderTree) {
				xpath = getFolderXpath(folderName);
				dblClick(By.xpath(xpath));
				waitAwhile(0.5f);
			}
		}
	}

	private void previewAction(boolean canPreview) {
		previewAction(canPreview, true);
	}

	private void previewAction(boolean canPreview, boolean download) {
		if (canPreview) {
			xpath = "//div[contains(@class,'previewWindow')]/div[contains(@class,'previewBox')]/div[not(@class)]";
			if (!isVisible(By.xpath(xpath)) && !isVisible(
					By.xpath("//div[contains(@class,'previewWindow')]/div[contains(@class,'previewBox')]/iframe"))) {
				Assert.fail("preview window fails");
			}
		} else {
			xpath = "//div[contains(@class,'previewWindow')]/div[contains(@class,'previewBox')]/div[contains(@class,'nopreview')]";
			if (!isVisible(By.xpath(xpath))) {
				Assert.fail("preview window fails");
			}
		}

		// preview window next
		xpath = "//div[contains(@class,'previewWindow')]/div[contains(@class,'previewRight')]/i[contains(@class,'pre_icon')]";
		click(By.xpath(xpath));
		waitAwhile();

		// preview window prev
		xpath = "//div[contains(@class,'previewWindow')]/div[contains(@class,'previewleft')]/i[contains(@class,'pre_icon')]";
		click(By.xpath(xpath));
		waitAwhile();

		if (download) {
			// preview window download
			xpath = "//div[contains(@class,'previewWindow')]/div[contains(@class,'functionnav')]/div[contains(@class,'right_icons')]/span[i[contains(@class,'download')]]";
			click(By.xpath(xpath));
			waitAwhile(6);
			xpath = "//div[contains(@class,'previewWindow')]/div[contains(@class,'functionnav')]/div[contains(@class,'previewname')]";
			element = driver.findElement(By.xpath(xpath));
			String fileName = element.getText();
			Assert.assertEquals("preview download file failed: " + fileName, true, downloadFileExists(fileName));
		}

		// preview window close
		xpath = "//div[contains(@class,'previewWindow')]/div[contains(@class,'functionnav')]/div[contains(@class,'right_icons')]/span[i[contains(@class,'close')]]";
		click(By.xpath(xpath));
		waitAwhile();
	}

	private String getXpath() {
		if (isGrid()) {
			xpath = "//ul[@id='fileList']/li/div[@class='fileNameView']/span";
		} else if (isList()) {
			xpath = "//tbody[@id='listViewBox']/tr/td[1]/span[@class='filename']";
		} else {
			xpath = "";
			Assert.fail("failed when get xpath");
		}

		return xpath;
	}

	private String getXpath(int nth) {
		if (isGrid()) {
			xpath = "//ul[@id='fileList']/li[" + nth + "]/div[@class='fileNameView']/span";
		} else if (isList()) {
			xpath = "//tbody[@id='listViewBox']/tr[" + nth + "]/td[1]/span[@class='filename']";
		} else {
			xpath = "";
			Assert.fail("failed when get xpath");
		}

		return xpath;
	}

	private String getFileXpath() {
		if (isGrid()) {
			xpath = "//ul[@id='fileList']/li/div[@class='fileNameView']/span";
		} else if (isList()) {
			xpath = "//tbody[@id='listViewBox']/tr[not(contains(@class,'folder'))]/td[1]/span[@class='filename']";
		} else {
			xpath = "";
			Assert.fail("failed when get file xpath");
		}

		return xpath;
	}

	private String getFileXpath(String fileName) {
		if (isGrid()) {
			// xpath = "//ul[@id='fileList']/li[@title='" + fileName + "']";
			xpath = "//ul[@id='fileList']/li/div[@class='fileNameView']/span[text()='" + fileName + "']";
		} else if (isList()) {
			xpath = "//tbody[@id='listViewBox']/tr[not(contains(@class,'folder'))]/td[1]/span[@class='filename' and text()='"
					+ fileName + "']";
		} else {
			xpath = "";
			Assert.fail("failed when get file xpath");
		}

		return xpath;
	}

	private String getFileXpath(int nth) {
		if (isGrid()) {
			xpath = "//ul[@id='fileList']/li[" + nth + "]/div[@class='fileNameView']/span";
		} else if (isList()) {
			xpath = "//tbody[@id='listViewBox']/tr[not(contains(@class,'folder'))][" + nth
					+ "]/td[1]/span[@class='filename']";
		} else {
			xpath = "";
			Assert.fail("failed when get file xpath");
		}

		return xpath;
	}

	private String getFolderXpath() {
		if (isGrid()) {
			xpath = "//ul[@id='folderList']/li[contains(@class,'folder')]/span";
		} else if (isList()) {
			xpath = "//tbody[@id='listViewBox']/tr[contains(@class,'folder')]/td[1]/span[@class='filename']";
		} else {
			xpath = "";
			Assert.fail("failed when get folder xpath");
		}

		return xpath;
	}

	private String getFolderXpath(String folderName) {
		if (isGrid()) {
			xpath = "//ul[@id='folderList']/li[contains(@class,'folder')]/span[text()='" + folderName + "']";
		} else if (isList()) {
			xpath = "//tbody[@id='listViewBox']/tr[contains(@class,'folder')]/td[1]/span[@class='filename' and text()='"
					+ folderName + "']";
		} else {
			xpath = "";
			Assert.fail("failed when get folder xpath");
		}

		return xpath;
	}

	private String getFolderXpath(int nth) {
		if (isGrid()) {
			xpath = "//ul[@id='folderList']/li[contains(@class,'folder')][" + nth + "]/span";
		} else if (isList()) {
			xpath = "//tbody[@id='listViewBox']/tr[contains(@class,'folder')][" + nth
					+ "]/td[1]/span[@class='filename']";
		} else {
			xpath = "";
			Assert.fail("failed when get folder xpath");
		}

		return xpath;
	}

	private boolean isGrid() {
		xpath = "//div[contains(@class,'right_icon_bar')]/i[contains(@class,'viewmode')]/i[contains(@class,'list')]";

		return isExisted(By.xpath(xpath));
	}

	private boolean isList() {
		xpath = "//div[contains(@class,'right_icon_bar')]/i[contains(@class,'viewmode')]/i[contains(@class,'grid')]";

		return isExisted(By.xpath(xpath));
	}

	private boolean hasFile() {
		xpath = getFileXpath();

		if (StringUtils.isBlank(xpath)) {
			return false;
		}

		return isExisted(By.xpath(xpath));
	}

	private boolean hasFolder() {
		// xpath = getFolderXpath();
		//
		// if (StringUtils.isBlank(xpath)) {
		// return false;
		// }

		if (isGrid()) {
			xpath = "//ul[@id='folderList']/li[contains(@class,'folder')]/span[text()!='CloudScan']";
		} else if (isList()) {
			xpath = "//tbody[@id='listViewBox']/tr[contains(@class,'folder')]/td[1]/span[@class='filename' and text()!='CloudScan']";
		} else {
			xpath = "";
			Assert.fail("failed when get folder xpath");
		}

		return isExisted(By.xpath(xpath));
	}

	// 切换视图模式
	private void changeMode(String modeName) {
		if ("grid".equalsIgnoreCase(modeName)) {
			// 已经是grid模式
			if (isGrid()) {
				return;
			}

			xpath = "//div[contains(@class,'right_icon_bar')]/i[contains(@class,'viewmode')]/i[contains(@class,'grid')]";
		} else {
			// 已经是list模式
			if (isList()) {
				return;
			}

			xpath = "//div[contains(@class,'right_icon_bar')]/i[contains(@class,'viewmode')]/i[contains(@class,'list')]";
		}

		// 点击切换
		click(By.xpath(xpath));
		waitAwhile();

		// 检查是否切换成功
		if ("grid".equalsIgnoreCase(modeName)) {
			xpath = "//div[contains(@class,'right_icon_bar')]/i[contains(@class,'viewmode')]/i[contains(@class,'list')]";
		} else {
			xpath = "//div[contains(@class,'right_icon_bar')]/i[contains(@class,'viewmode')]/i[contains(@class,'grid')]";
		}
		Assert.assertEquals("change mode failed: " + modeName, true, isExisted(By.xpath(xpath)));
	}

	// 切换文件列表
	private void listFiles(String category) {
		listFiles(category, false, false);
	}

	// 切换文件列表显示全部
	private void listAllFiles(String category) {
		listFiles(category, true, true);
	}

	// 切换文件列表
	private void listFiles(String category, boolean forceToList, boolean showAll) {
		xpath = "//div[contains(@class,'leftNav')]//ul[contains(@class,'navBox')]//div[a[contains(@class,'my_drive')]]";
		if ("mydrive".equalsIgnoreCase(category)) {
			// 我的云端硬盘
		} else if ("sharewithme".equalsIgnoreCase(category)) {
			// 与我共享
			xpath = "//div[contains(@class,'leftNav')]//ul/li[a/i[contains(@class,'share')]]";
		} else if ("star".equalsIgnoreCase(category)) {
			// 星标文件
			xpath = "//div[contains(@class,'leftNav')]//ul/li[a/i[contains(@class,'star')]]";
		} else if ("recent".equalsIgnoreCase(category)) {
			// 最近使用
			xpath = "//div[contains(@class,'leftNav')]//ul/li[a/i[contains(@class,'recent')]]";
		} else if ("trash".equalsIgnoreCase(category)) {
			// 回收站
			xpath = "//div[contains(@class,'leftNav')]//ul/li[contains(@class,'trash') and a/i[contains(@class,'trash')]]";
		}

		click(By.xpath(xpath));
		waitAwhile();
		// action.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
		// waitAwhile();

		if (forceToList) {
			changeMode("list");
		}

		if (showAll) {
			scroll();
		}
	}

	// 下拉滚动
	private void scroll() {
		scroll(default_page_down_numbers);
	}

	// 下拉滚动
	private void scroll(int pages) {
		// mouseMove(By.className("scrollBox"), 200, 200);

		// 滚动
		// for (int i = 0; i < 5; i++) {
		// jse.executeScript("window.scrollBy(0, 50)");
		// waitAwhile(1);
		// }

		// jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// jse.executeScript(
		// "window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");

		try {
			click(By.xpath(getFileXpath(1)));

			Robot robot = new Robot();
			for (int i = 0; i < pages; i++) {
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				// jse.executeScript("window.scrollBy(0, 600)");

				waitAwhile(0.12f);
			}
			waitAwhile();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void click() {
		action.click().build().perform();
	}

	private void click(By by) {
		click(by, true, true, true);
	}

	private void click(By by, boolean located, boolean moveTo, boolean clickable) {
		try {
			if (located) {
				wait.until(ExpectedConditions.presenceOfElementLocated(by));
			}

			if (moveTo) {
				mouseMove(by);
			}

			if (clickable) {
				wait.until(ExpectedConditions.elementToBeClickable(by));
			}

			action.click(driver.findElement(by)).build().perform();
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("failed when click: " + by);
		}
	}

	private void dblClick() {
		action.doubleClick().build().perform();
	}

	private void dblClick(By by) {
		dblClick(by, true);
	}

	private void dblClick(By by, boolean moveTo) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			wait.until(ExpectedConditions.elementToBeClickable(by));

			if (moveTo) {
				mouseMove(by);
			}

			action.doubleClick(driver.findElement(by)).build().perform();
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("failed when double click: " + by);
		}
	}

	private void rightClick() {
		action.contextClick().build().perform();
	}

	private void rightClick(By by) {
		rightClick(by, true);
	}

	private void rightClick(By by, boolean moveTo) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			wait.until(ExpectedConditions.elementToBeClickable(by));

			if (moveTo) {
				mouseMove(by);
			}

			action.contextClick(driver.findElement(by)).build().perform();
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("failed when right click: " + by);
		}
	}

	private boolean exist(By by) {
		boolean existed = false;
		try {
			driver.findElement(by);
			existed = true;
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}

		return existed;
	}

	private boolean isExisted(By by, boolean mustWait) {
		if (mustWait) {
			boolean existed = false;

			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(by));
				existed = true;
			} catch (Exception ex) {
				ex.printStackTrace();
				// Assert.fail("failed when check existed: " + by);
			}

			return existed;
		} else {
			return exist(by);
		}

	}

	private boolean isExisted(By by) {
		return isExisted(by, false);
	}

	private boolean isVisible(By by) {
		boolean visible = false;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			visible = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			// Assert.fail("failed when check visibility: " + by);
		}

		return visible;
	}

	private void mouseMove(By by) {
		mouseMove(by, 0, 0);
	}

	private void mouseMove(By by, int x, int y) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			element = driver.findElement(by);
			if (x == 0 && y == 0) {
				// action.moveToElement(element).build().perform();

				mouse.mouseMove(((Locatable) element).getCoordinates());
			} else {
				Point coordinates = element.getLocation();
				Robot robot;
				robot = new Robot();
				// 移动鼠标到指定位置
				robot.mouseMove(coordinates.getX() + x, coordinates.getY() + y);
			}

			waitAwhile(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("failed when mouse move: " + by);
		}
	}

	private void setTitle(String title) {
		jse.executeScript("document.title = '" + title + "'");
	}

	private void waitAwhile() {
		waitAwhile(default_wait_seconds);
	}

	private void waitAwhile(float seconds) {
		try {
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	private ChromeDriver createChromeDriverWithDownloadFolder(String folder) {
		Map<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		// 自定义下载文件夹
		chromePrefs.put("download.default_directory", folder);
		// 多个文件下载不弹出提示
		chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
		chromePrefs.put("download.prompt_for_download", false);

		// chromePrefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads",
		// 1);
		// chromePrefs.put("profile.default_content_setting_values.automatic_downloads",
		// 1);
		 System.setProperty("webdriver.chrome.driver",
		 "C:/Users/Juexin010/.m2/repository/webdriver/chromedriver/win32/2.30/chromedriver.exe");
		ChromeDriverService service = ChromeDriverService.createDefaultService();

		ChromeOptions options = new ChromeOptions();
		// options.addExtensions(new File("D:\\Tasks\\Bug\\V10_test\\IDE.bat"));
		// options.setBinary("D:\\UnifaceInstall\\U10103\\common\\bin\\ide.exe");
		// options.addArguments("/adm=D:\\UnifaceInstall\\U10103\\uniface\\adm");
		// options.addArguments("/dir=D:\\UnifaceInstall\\U10103\\Data\\project");
		// options.addArguments("disable-extensions");
		// options.addArguments("--user-data-dir=");
		// //options.addArguments("--load-extension=\\");
		// options.setExperimentalOption("excludeSwitches",
		// Arrays.asList("disable-default-apps","disable-background-networking",
		// "disable-client-side-phishing-detection",
		// "disable-default-apps", "disable-hang-monitor",
		// "disable-prompt-on-repost",
		// "disable-sync", "disable-web-resources", "enable-logging",
		// "ignore-certificate-errors",
		// "load-extension", "log-level", "metrics-recording-only",
		// "no-first-run",
		// "password-store", "safebrowsing-disable-auto-update",
		// "safebrowseing-disable-protection", "test-type",
		// "use-mock-keychain", "user-data-dir", "disable-component-update",
		// "safebrowsing-disable-download-protection", "remote-debugging-port",
		// "disable-extensions", "load-component-extension",
		// "disable-popup-blocking"));
		// options.setExperimentalOption("excludeSwitches",
		// Arrays.asList("load-extension"));
		// options.setExperimentalOption("debuggerAddress", "localhost:2012");

//		options.addArguments("url=data:,");
//		options.addArguments("--log-level=3");
//		options.addArguments("--disable-extensions");
//		options.addArguments("--start-maximized");
		options.setBinary("C:\\Program Files (x86)\\NextCont\\ECM_DEV\\NextCont_4_DEV.exe");
		// options.sebuggerAddress = "localhost:8080";
//		 options.addArguments("--remote-debugging-port=12345");
//		options.setExperimentalOption("debuggerAddress", "127.0.0.1:12345");
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability("debuggerAddress", "localhost:12345");
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(cap);
//		return (ChromeDriver) new RemoteWebDriver(cap);
	}
}
