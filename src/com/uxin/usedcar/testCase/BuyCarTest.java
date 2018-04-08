package com.uxin.usedcar.testCase;

import com.uxin.usedcar.maidianlib.*;

import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Environment;
import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Watchable;
import java.security.PublicKey;
import java.sql.Driver;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.smartcardio.Card;
import javax.sound.midi.MidiDevice.Info;

import org.apache.xalan.templates.ElemApplyImport;
import org.junit.AfterClass;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STEditAs;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.org.apache.xpath.internal.operations.And;
import com.uxin.usedcar.test.libs.BaseTest;
import com.uxin.usedcar.test.libs.CaseConfig;
import com.uxin.usedcar.test.libs.DateUtil;

public class BuyCarTest extends BaseTest  {
	@BeforeClass
	  public static void first() throws Exception {
		System.out.println("初始化报告");
		 reports_BuyCarTest.init("./report/BuyCar/reportBuyCar.html",true);
		
	  }

	@AfterClass
	public static void last(){
		System.out.println("关闭报告");
		System.out.println("*******************tearDown**************");
		reports_BuyCarTest.endTest();
	}

	//点击买车tab，进入车市页
		public void clickCheShiTab() {
			reports_BuyCarTest.log(LogStatus.INFO, "点击买车tab，进入车市页");
			if (os == 2) {  //android
				clickElementById("rbCheShi");
			}else if (os == 1) {  //ios
//				iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name='买车']")).click();
				jsTapyDistance_X_Y_By(MobileBy.iOSNsPredicateString("type=='XCUIElementTypeButton' AND name=='买车'"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击买车按钮");
				if (!(iosDriver.findElementByName("button rexiaocheyuan shuanglie").getAttribute("value") == null)) {
					System.out.println("单双列值--->>   " + iosDriver.findElementByName("button rexiaocheyuan shuanglie").getAttribute("value"));
					System.out.println("当前车辆是双列");
					clickByNameCount("button rexiaocheyuan shuanglie", 1, 8);// 切换至单列
				}
			}
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "buycar_bottom", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=4", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=2", "", "");
		}
		
		/**
		 * @Name 2501_carShow
		 * @catalogue 切换单双列,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2501_carShow() {
			try {
				reports_BuyCarTest.startTest("test_2501_carShow");
				EventManager.fileName = "test_2501_carShow.txt";
				launchApp();
				clickCheShiTab();	
				if (os == 2) {
					reports_BuyCarTest.log(LogStatus.INFO, "点击车市页的视图按钮");
//					clickElementById("vgChangeShowTypeLayout");//android
					clickElementById("cbChangeShowType");
				}else if (os == 1){
					//TODO:
					jsTapyDistance_X_Y_By(MobileBy.name("button rexiaocheyuan shuanglie"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击视图单双列切换按钮");
					reports_BuyCarTest.log(LogStatus.INFO, "点击车市页的视图按钮");
				}	
				wait(1);
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_show#type=2/page=2", "", "", "");
				String carid = "";
				if (os == 2) {
					reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入车辆详情页");
					carid =  clickFirstCarInList(2, "");//android
					reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的返回按钮");
					clickElementById("imgBtBack");//android
				}else if (os == 1){
					//TODO:
					carid =  clickFirstCarInList(2, "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入车辆详情页");
					backBTN();
					reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的返回按钮");
					
				}
				System.out.println(carid);
				EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+ carid+"/time=[value]", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=0/from=3", "", "", "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购按钮，进入分期购列表");
				if(os == 2) {
					ClickOnFYB();
				}else if(os == 1){
					//TODO
					jsTapyDistance_X_Y_By(MobileBy.name("newCallgray"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击分期购按钮");
				}
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "list_switch#tab=2/type=1", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=5", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=3", "", "");
				if (os == 2) {
					reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入车辆详情页");
					carid =  clickFirstCarInList(3, "");//android
					reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的返回按钮，返回到列表");
					clickElementById("imgBtBack");//android
					wait(2);
				}else if (os == 1){
					//TODO:
					carid =  clickFirstCarInList(3, "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入车辆详情页");
					backBTN();
					reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的返回按钮，返回到列表");
					
				}
				EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+ carid+"/time=[value]", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=0/from=3", "", "", "");
				
				if (os == 2) {
					reports_BuyCarTest.log(LogStatus.INFO, "点击视图按钮，切换到单列");
//					clickElementById("cbChangeShowType");//android
					clickElementById("vgChangeShowTypeLayout");//android
				}else if (os == 1){
					//TODO:
					jsTapyDistance_X_Y_By(MobileBy.name("button rexiaocheyuan shuanglie"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击视图单双列切换按钮");
					reports_BuyCarTest.log(LogStatus.INFO, "点击视图按钮，切换到单列");
				}
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_show#type=1/page=3", "", "", "");
				pullFileAndCompare("test_2501_carShow");
			} finally {
				//收尾
				//将车市页列表显示模式恢复为单列模式
				if (os==2) {
					if(CheckViewVisibilty(By.id("root_right"))) {//双列
						clickElementById("cbChangeShowType");
						wait(2);
					}
				}else if (os==1) {
//					if(findElementByXpath("//XCUIElementTypeButton[@name='button rexiaocheyuan shuanglie']").getAttribute("value").equals("1")) {//双列button rexiaocheyuan shuanglie
//						jsTapyDistance_X_Y_By(MobileBy.name("button rexiaocheyuan shuanglie"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击视图单双列切换按钮");
//						wait(2);
//					}
				}
			}
		}
		
		/**
		 * @Name 2502_serch_1
		 * @catalogue 搜索框搜索,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2502_serch_1() {
			reports_BuyCarTest.startTest("test_2502_serch_1");
			EventManager.fileName = "test_2502_serch_1.txt";
			launchApp();
			clickCheShiTab();
			
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO,"点击搜索框");
				clickElementById("tv_search");//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("button rexiaocheyuan shuanglie"), 50*screenRatioX, 0*screenRatioY, "+", "+", "点击点击搜索框搜索🔍按钮");
				reports_BuyCarTest.log(LogStatus.INFO,"点击搜索框");
			}
			wait(1);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "search_bar#page=2", "", "", "");
			
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO,"点击第一个热门搜索词:付一成开豪车");
				clickElementByName("付一成开豪车");//android
			}else if (os == 1){
				//TODO:
				if (CheckViewVisibilty(By.name("历史搜索"))) {
					clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='icon 垃圾桶']"), 1, 1);
					clickByCT(MobileBy.name("确定"), 1, 1);
				}
				String searchHistoryString= carList_endValue("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell"
						 , "//XCUIElementTypeButton[1]", "name", 1);
				System.out.println("热门搜索第一个为："+searchHistoryString);
				clickByCT(MobileBy.name(searchHistoryString), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO,"点击第一个热门搜索词:付一成开豪车");
			}
			wait(1);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "search_car#result=1/retri_word=3-30万元/input_word=/rank=1/type=4/page=2/word=付一成开豪车", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=2/result=1/page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_carage_expo#page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=3", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO,"先清除付一成开豪车的搜索词，再次点击搜索框,搜索宝马");
				clickElementById("iv_pop_delete");
				wait(1);
				clickElementById("tv_search");//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("button rexiaocheyuan shuanglie"), 50*screenRatioX, 0*screenRatioY, "+", "+", "点击点击搜索框搜索🔍按钮");
				jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='清除文本']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击清除文本按钮");
				if (CheckViewVisibilty(By.name("历史搜索"))) {
					clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='icon 垃圾桶']"), 1, 1);
					clickByCT(MobileBy.name("确定"), 1, 1);
				}
				reports_BuyCarTest.log(LogStatus.INFO,"先清除付一成开豪车的搜索词，再次点击搜索框,搜索宝马");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "search_bar#page=3", "", "", "");
			if (os == 2) {
				inputById("etSearchText", "宝马");//android
				androidDriver.pressKeyCode(AndroidKeyCode.ENTER);
				wait(2);
			}else if (os == 1){
				//TODO:
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 0, "value")), "宝马");
			    clickByCT(MobileBy.name("Search"), 1, 5);
			    reports_BuyCarTest.log(LogStatus.INFO, "回车键执行搜索🔍");
			}	
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "search_car#result=1/retri_word=宝马/input_word=宝马/rank=/type=1/page=3/word=宝马", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=2/result=1/page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_series_expo#page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=3", "", "");
			String carid = null;
			if (os == 2) {//android
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入车辆详情页");
				carid = clickFirstCarInList(2,"宝马");
			}else if (os == 1){
				//TODO:
				carid = clickFirstCarInList(2,"宝马");
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入车辆详情页");
			}	
			sliding("up");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid=[value]/pos=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_expo", "", "");
			pullFileAndCompare("test_2502_serch_1");
		}
		
		/**
		 * @Name 2503_serch_2
		 * @catalogue 点击搜索框suggest,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2503_serch_2() {
			reports_BuyCarTest.startTest("test_2503_serch_2");
			EventManager.fileName = "test_2503_serch_2.txt";
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO,"点击搜索框");
				clickElementById("tv_search");//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("button rexiaocheyuan shuanglie"), 50*screenRatioX, 0*screenRatioY, "+", "+", "点击点击搜索框搜索🔍按钮");
				reports_BuyCarTest.log(LogStatus.INFO,"点击搜索框");
			}
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "search_bar#page=2", "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO,"搜索宝马");
				inputById("etSearchText", "宝马");
				clickElementByName("宝马5系");//android
			}else if (os == 1){
				//TODO:
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 0, "value")), "宝马");
			   clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='宝马5系']"), 1, 3);
				reports_BuyCarTest.log(LogStatus.INFO,"搜索宝马");
			}
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "search_car#result=1/retri_word=宝马,5系/input_word=宝马/rank=2/type=2/page=2/word=宝马5系", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=2/result=1/page=2", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_carage_expo#page=2", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=2", "", "");
//			if (os == 2) {
//				reports_BuyCarTest.log(LogStatus.INFO,"删除车系词");
//				clickElementByName("5系");//android
//			}else if (os == 1){
//				//TODO:
//			}
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "delete_sign#type=1/word=5系/retri_word=宝马,5系", "", "", "");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_series_expo#page=2", "", "");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=2", "", "");
//			if (os == 2) {
//				reports_BuyCarTest.log(LogStatus.INFO,"删除品牌词");
//				clickElementByName("宝马");//android
//			}else if (os == 1){
//				//TODO:
//			}	
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "delete_sign#type=1/word=宝马/retri_word=宝马", "", "", "");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=2", "", "");
			pullFileAndCompare("test_2503_serch_2");
		}
		
		/**
		 * @Name 2504_city
		 * @catalogue 点击车市页的城市按钮以及价格最低筛选,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2504_city() {
			try {
				reports_BuyCarTest.startTest("test_2504_city");
				EventManager.fileName = "test_2504_city.txt";
				launchApp();
				clickCheShiTab();
				if (os == 2) {
					reports_BuyCarTest.log(LogStatus.INFO, "点击城市按钮");
					clickElementById("btChooseCity");//android
					wait(1);
					clickElementByName("阿克苏");
				}else if (os == 1){
					//TODO:
					CaseConfig.CITY_NAME=getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] ' '`]"), 1).getAttribute("label");
//					 CaseConfig.CITY_NAME=getListTextByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@name,' ')]"), 1, "name");111
//					 clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] 'navibarCompare'`]"), 0), 1, 3,"");
					 jsTapyDistance_X_Y_By(MobileBy.name(CaseConfig.CITY_NAME), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击点击城市按钮");
					reports_BuyCarTest.log(LogStatus.INFO, "点击城市按钮");
					jsTapyDistance_X_Y_By(MobileBy.name("阿克苏"), 0*screenRatioX, 0*screenRatioY, "+", "+", "切换城市到阿克苏");
					reports_HomePageTest.log(LogStatus.INFO, "切换城市到阿克苏");
				}	
				wait(1);
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "city_choice#page=2", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=0/page=2", "", "");
				if (os == 2) {
					clickElementById("btChooseCity");
					wait(1);
					clickElementByName("北京");
					wait(1);//android
				}else if (os == 1){
					//TODO:
					 jsTapyDistance_X_Y_By(MobileBy.name(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] ' '`]"), 1).getAttribute("label")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击点击城市按钮");
						reports_BuyCarTest.log(LogStatus.INFO, "点击城市按钮");
						jsTapyDistance_X_Y_By(MobileBy.name("北京"), 0*screenRatioX, 0*screenRatioY, "+", "+", "切换城市到北京");
						reports_HomePageTest.log(LogStatus.INFO, "切换城市到北京");
				}
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "city_choice#page=2", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=2", "", "");
				if (os == 2) {
					reports_BuyCarTest.log(LogStatus.INFO, "点击默认排序按钮，选择价格最低");
					clickElementById("tvSort");//android
				}else if (os == 1){
					//TODO:
					clickByNameCount("排序 ", 1, 5);
			        reports_HomePageTest.log(LogStatus.INFO, "点击默认排序");
			        
				}
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_sort#button=0/page=2", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=2", "", "");
				if (os == 2) {
					clickElementByName("价格最低");//android
				}else if (os == 1){
					//TODO:
					clickByNameCount("价格最低", 1, 5);
					reports_HomePageTest.log(LogStatus.INFO, "点击价格最高");
				}
				wait(2);
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_sort#button=1/page=2", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=2", "", "");
				String carid = null;
				if (os == 2) {//android
					reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
					carid = clickFirstCarInList(2, "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的返回按钮，返回到列表");
					clickElementById("imgBtBack");//android
					ClickOnFYB();
					reports_BuyCarTest.log(LogStatus.INFO, "点击分期购按钮，进入分期购列表");
				}else if (os == 1){
					//TODO:
					carid = clickFirstCarInList(2, "");
					reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
					backBTN();
					reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的返回按钮，返回到列表");
					jsTapyDistance_X_Y_By(MobileBy.name("newCallgray"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击分期购按钮");
					reports_BuyCarTest.log(LogStatus.INFO, "点击分期购按钮，进入分期购列表");
				}		
				EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+ carid+"/time=[value]", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=0/from=3", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "list_switch#tab=2/type=1", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=5", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=3", "", "");
				if (os == 2) {
					reports_BuyCarTest.log(LogStatus.INFO, "点击排序按钮，选择价格最高");
					clickElementById("tvSort");//android
				}else if (os == 1){
					//TODO:
//					clickByNameCount("排序 ", 1, 5);
					jsTapyDistance_X_Y_By(MobileBy.name(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] ' '`]"), 2).getAttribute("label")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击排序按钮");
					reports_BuyCarTest.log(LogStatus.INFO, "点击排序按钮，选择价格最高");
				}
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_sort#button=1/page=3", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=3", "", "");
				if (os == 2) {
					clickElementByName("价格最高");//android
				}else if (os == 1){
					//TODO:
					clickByNameCount("价格最高", 1, 5);
					reports_HomePageTest.log(LogStatus.INFO, "点击价格最高");
				}
				wait(2);
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_sort#button=2/page=3", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=3", "", "");
				if (os == 2) {//android
					reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
					carid = clickFirstCarInList(3, "");
				}else if (os == 1){
					//TODO:
					carid = clickFirstCarInList(3, "");
					reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				}
				pullFileAndCompare("test_2504_city");
			} finally {
				if (os == 2) {
					// 再次恢复城市为北京
					androidDriver.pressKeyCode(AndroidKeyCode.BACK);
					wait(1);
					clickElementById("btChooseCity");
					wait(1);
					clickElementByName("北京");
					wait(1);//android
				}else if (os == 1){
					//TODO:
//					jsTapyDistance_X_Y_By(MobileBy.name(CaseConfig.CITY_NAME), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击点击城市按钮");
//					reports_BuyCarTest.log(LogStatus.INFO, "点击城市按钮");
//					jsTapyDistance_X_Y_By(MobileBy.name("北京"), 0*screenRatioX, 0*screenRatioY, "+", "+", "切换城市到北京");
//					reports_HomePageTest.log(LogStatus.INFO, "切换城市到北京");
				}
			}
		}
		
		/**
		 * @Name 2505_brand
		 * @catalogue 点击品牌筛选,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2505_brand() {
			reports_BuyCarTest.startTest("test_2505_brand");
			EventManager.fileName = "test_2505_brand.txt";
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择不限品牌");
				clickElementById("tvBrand");
				clickElementByName("不限品牌");//android
			}else if (os == 1){
				//TODO:
		        clickByNameCount("品牌 ", 1, 5);
		        scrollToElementClick("不限品牌", 2,"TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择不限品牌");
			}
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "brand_filter#brandid=0/seriesid=0/page=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=2", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众--不限车系");
				clickElementById("tvBrand");
				wait(1);
				clickElementByName("大众");
				clickElementByName("不限车系");//android
			}else if (os == 1){
				//TODO:
				clickByNameCount("品牌 ", 1, 5);
				scrollToElementClick("大众", 2,"TRUE");
				scrollToElementClick("不限车系", 2,"TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众--不限车系");
			}
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "brand_filter#brandid=84/seriesid=0/page=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_expo#type=", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_series_expo#page=2", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=2", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众--帕萨特");
				clickElementById("tvBrand");
				wait(1);
				clickElementByName("大众");
				clickElementByName("帕萨特");//android
			}else if (os == 1){
				//TODO:
				clickByNameCount("品牌 ", 1, 5);
//				scrollToElementClick("大众", 2,"TRUE");
				scrollToElementClick("帕萨特", 2,"TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众--帕萨特");
			}
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "brand_filter#brandid=84/seriesid=329/page=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_expo#type=", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_carage_expo#page=2", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=2", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=2", "", "");
			pullFileAndCompare("test_2505_brand");
		}
		
		/**
		 * @Name 2506_brand_fyb
		 * @catalogue 点击分期购列表的品牌筛选,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2506_brand_fyb() {
			reports_BuyCarTest.startTest("test_2506_brand_fyb");
			EventManager.fileName = "test_2506_brand_fyb.txt";
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购，进入分期购列表");
				ClickOnFYB();//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("newCallgray"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击分期购按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购按钮，进入分期购列表");
			}
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "list_switch#tab=2/type=1", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=5", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=3", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择不限品牌");
				clickElementById("tvBrand");
				clickElementByName("不限品牌");//android
			}else if (os == 1){
				//TODO:
				clickByNameCount("品牌 ", 1, 5);
		        scrollToElementClick("不限品牌", 2,"TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择不限品牌");
			}
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "brand_filter#brandid=0/seriesid=0/page=3", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=3", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众--不限车系");
				clickElementById("tvBrand");
				wait(1);
				clickElementByName("大众");
				clickElementByName("不限车系");//android
			}else if (os == 1){
				//TODO:
				clickByNameCount("品牌 ", 1, 5);
				jsTapyDistance_X_Y_By(MobileBy.iOSNsPredicateString("type=='XCUIElementTypeStaticText' AND visible==1 AND name=='大众'"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击大众");
				scrollToElementClick("不限车系", 2,"TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众--不限车系");
			}
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "brand_filter#brandid=84/seriesid=0/page=3", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_expo#type=", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_series_expo#page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=3", "", "");
			if (os == 2) {
				clickElementById("tvBrand");
				wait(1);
				clickElementByName("大众");
				clickElementByName("帕萨特");//android
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众--帕萨特");
			}else if (os == 1){
				//TODO:
				clickByNameCount("品牌 ", 1, 5);
//				scrollToElementClick("大众", 2,"TRUE");当前的iOS第二次搜索的话会自动带入品牌
				wait(2);
				scrollToElementClick("帕萨特", 2,"TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众--帕萨特");
			}
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "brand_filter#brandid=84/seriesid=329/page=3", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_carage_expo#page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=3", "", "");
			pullFileAndCompare("test_2506_brand_fyb");
		}
		
		/**
		 * @Name 2507_price
		 * @catalogue 点击价格筛选,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin
		 */
		@Test
		public void test_2507_price() {
			reports_BuyCarTest.startTest("test_2507_price");
			EventManager.fileName = "test_2507_price.txt";
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击车市页价格按钮--选择不限价格");
				clickElementById("tvPrice");
				wait(1);
				clickElementByName("不限价格");//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='价格 ']"), 1, 1);
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='不限价格']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击车市页价格按钮--选择不限价格");
			}
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "price_filter#price=0/page=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=2", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击车市页价格按钮--选择5万以内");
				clickElementById("tvPrice");
				wait(2);
				clickElementByName("5万以下");//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='价格 ']"), 1, 1);
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='5万以下']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击车市页价格按钮--选择5万以内");
			}
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "price_filter#price=0-5/page=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=2", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购，进入分期购列表");
				ClickOnFYB();//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("newCallgray"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击分期购按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购按钮，进入分期购列表");
			}
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "list_switch#tab=2/type=1", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=5", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=3", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击车市页价格按钮--选择不限价格");
				clickElementById("tvPrice");
				wait(1);
				clickElementByName("不限价格");//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='价格 ']"), 1, 1);
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='不限价格']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击车市页价格按钮--选择不限价格");
			}
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "price_filter#price=0/page=3", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=3", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击车市页价格按钮--选择5万以内");
				clickElementById("tvPrice");
				wait(1);
				clickElementByName("5万以下");//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='价格 ']"), 1, 1);
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='5万以下']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击车市页价格按钮--选择5万以内");
			}
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "price_filter#price=0-5/page=3", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=3", "", "");
			pullFileAndCompare("test_2507_price");
	}

		
		/**
		 * @Name 2508_filter
		 * @catalogue 点击高级筛选,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin  iOS liyiwan
		 */
		@Test
		public void test_2508_filter() {
			reports_BuyCarTest.startTest("test_2508_filter");
			EventManager.fileName = "test_2508_filter.txt";
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				int width = androidDriver.manage().window().getSize().width;
				int height = androidDriver.manage().window().getSize().height;
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				clickElementById("tv_filter");
				wait(1);
				clickElementById("advanced_search_counttv");   //找到XXX辆车//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
			}
			
			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "more_filter#carcat=不限/carage=0/colourid=不限/fule=不限/mile=0/standard=不限/displace=不限/engine=不限/seat=不限/button=1/brandid=0/price=0/countryid=不限/origin=不限/hotcar=不限/site=本地/page=2/cartypeid=不限", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=2", "", "");	
			String carid = null;
			if (os == 2) {//android
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的返回按钮，返回到列表");
				clickElementById("imgBtBack");
			}else if (os == 1){
				//TODO:
				 carid = clickFirstCarInList(2, "");
				 reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页。");
				 backBTN();
				 reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的返回按钮，返回到列表");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+ carid+"/time=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=0/from=3", "", "", "");
			wait(1);
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购，进入分期购列表");
				ClickOnFYB();//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("newCallgray"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击分期购按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购按钮，进入分期购列表");
			}
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "list_switch#tab=2/type=1", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=5", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=3", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				clickElementById("tv_filter");
				wait(3);
				clickElementById("advanced_search_counttv");   //找到XXX辆车
				wait(2);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "more_filter#carcat=不限/carage=0/colourid=不限/fule=不限/mile=0/standard=不限/displace=不限/engine=不限/seat=不限/button=1/brandid=0/price=0/countryid=不限/origin=不限/hotcar=不限/site=本地/page=3/cartypeid=不限", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=3", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				carid = clickFirstCarInList(3, "");
			}else if (os == 1){
				//TODO:
				carid = clickFirstCarInList(3, "");
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
			}
			wait(1);
			pullFileAndCompare("test_2508_filter");
		}
		
		/**
		 * @Name 2509_HelpFindCar
		 * @catalogue 点击帮我找车相关操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin
		 */
		@Test
		public void test_2509_HelpFindCar() {
			reports_BuyCarTest.startTest("test_2509_HelpFindCar");
			EventManager.fileName = "test_2509_HelpFindCar.txt";
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "滑动列表页，点击帮我找车");
				swipeUntilElementAppear(By.id("bt_post_wishlist"), "up", 5);
				wait(3);//android
			}else if (os == 1){
				//TODO:
				sliding("up", 1, 2);
//				wait(2);
				jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeCollectionView"), "3");
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carlist_expo#class=1/result=1/page=2", "", "", "");	
			if (os == 2) {
				clickElementById("bt_post_wishlist");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				scrollToElementClick("帮我找车", 2,"TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动列表页，点击帮我找车");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "wish#page=2", "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "输入帮我找车的信息");
				clickElementByName("请选择爱车");
				wait(1);
				clickElementByName("大众");
				clickElementByName("开迪");
				inputById("et_price", "20");
				inputById("et_phone", "14725836911");
				reports_BuyCarTest.log(LogStatus.INFO, "点击提交帮我找车按钮");
				clickElementById("bt_submit");//帮我找车
				wait(1);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='请选择爱车']"), 1, 2);
//				jsTapyDistance_X_Y_By(MobileBy.iOSNsPredicateString("type=='XCUIElementTypeStaticText' AND visible==1 AND name=='大众'"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击大众");
//				jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeStaticText[@name='大众' and @visible='true']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击大众");
				scrollToElementClick("大众", 2,"TRUE");
				scrollToElementClick("开迪", 2,"TRUE");
				inputByLocator(MobileBy.xpath("//XCUIElementTypeTextField[@value='请输入预算']"), "20");
				clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='请选择车龄']"), 1, 2);
				clickByCT(MobileBy.name("确定"), 1, 2);
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='帮我找车']"), 1, 2);
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "wish_submit#tel_num=14725836911/page=2", "", "", "");	
			pullFileAndCompare("test_2509_HelpFindCar");
		}
		
		/**
		 * @Name 2510_HelpFindCar_fyb
		 * @catalogue 点击分期购列表的帮我找车相关操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin
		 */
		@Test
		public void test_2510_HelpFindCar_fyb() {
			reports_BuyCarTest.startTest("test_2510_HelpFindCar_fyb");
			EventManager.fileName = "test_2510_HelpFindCar_fyb.txt";
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购，进入分期购列表");
				ClickOnFYB();//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("newCallgray"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击分期购按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购按钮，进入分期购列表");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "list_switch#tab=2/type=1", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=5", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=3", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "滑动列表页，点击帮我找车");
				swipeUntilElementAppear(By.id("bt_post_wishlist"), "up", 5);
				wait(3);//android
			}else if (os == 1){
				//TODO:
				sliding("up");
				jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeCollectionView"), "3");
				scrollToElementClick("帮我找车", 2,"TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动列表页，点击帮我找车");
			}
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carlist_expo#class=1/result=1/page=3", "", "", "");	
			if (os == 2) {
				clickElementById("bt_post_wishlist");
				wait(1);//android
			}else if (os == 1){
				//TODO:
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "wish#page=3", "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "输入帮我找车的信息");
				clickElementByName("请选择爱车");
				wait(1);
				clickElementByName("大众");
				clickElementByName("开迪");
				inputById("et_price", "20");
				inputById("et_phone", "14725836911");
				reports_BuyCarTest.log(LogStatus.INFO, "点击提交帮我找车按钮");
				clickElementById("bt_submit");//帮我找车
				wait(1);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='请选择爱车']"), 1, 2);
				scrollToElementClick("大众", 2,"TRUE");
				scrollToElementClick("开迪", 2,"TRUE");
				inputByLocator(MobileBy.xpath("//XCUIElementTypeTextField[@value='请输入预算']"), "20");
				clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='请选择车龄']"), 1, 2);
				clickByCT(MobileBy.name("确定"), 1, 2);
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='帮我找车']"), 1, 2);
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "wish_submit#tel_num=14725836911/page=3", "", "", "");		
			pullFileAndCompare("test_2510_HelpFindCar_fyb");
		}
			
		/**
		 * @Name 2511_list_recommend
		 * @catalogue 点击列表页推荐车系相关操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin
		 */
		@Test
		public void test_2511_list_recommend() {
			reports_BuyCarTest.startTest("test_2511_list_recommend");
			EventManager.fileName = "test_2511_list_recommend.txt";
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众-不限车系");
				clickElementById("tvBrand");
				clickElementByName("大众");
				clickElementByName("不限车系");
				wait(2);//android
			}else if (os == 1){
				//TODO:
				clickByNameCount("品牌 ", 1, 5);
				scrollToElementClick("大众", 2,"TRUE");
				scrollToElementClick("不限车系", 2,"TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众--不限车系");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "brand_filter#brandid=84/seriesid=0/page=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_expo#type=", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_series_expo#page=2", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=2", "", "");
			if (os == 2) {
//				sliding("up");
//				wait(3);
				clickElementByName("大众帕萨特");
				wait(2);//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("大众帕萨特"), 0*screenRatioX, 0*screenRatioY, "-", "-", "点击大众帕萨特");
			}
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "series_choice_expo#page=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_carage_expo#page=2", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=2", "", "");
			if (os == 2) {
				sliding("up");
				wait(3);
				clickElementByName("3年以内");
				wait(2);//android
			}else if (os == 1){
				//TODO:
				sliding("up", 1, 1);
				scrollToElementClick_classNameFalse("3年以内");
//			 	jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
			 	
			}
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "suggest_carage_click#page=2/rank=1", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=2", "", "");
			if (os == 2) {
				swipeUntilElementAppear(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v7.widget.RecyclerView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]"), "up", 10);
				wait(3);//android
			}else if (os == 1){
				//TODO:
				sliding("up", 6, 1);
//				jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "2");
				 String json = getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] \"还搜过\"`]");
				 System.out.println(json);
//				 String jso2 = getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] \"还搜过\"` AND visible == 1]");
				 
				 scrollToElementClick(json, 2,"TRUE");
//				scrollToElementClick_className_NS("搜该款车的人还搜过","TRUE");
				
			}
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carlist/pos/2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_series_expo#page=2", "", "");	
			if (os == 2) {
				clickElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v7.widget.RecyclerView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]");
				wait(3);//android
			}else if (os == 1){
				//TODO:
			}
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "intro_series#page=2/seriesid=[value]/rank=1", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_expo#type=", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=2", "", "");
			pullFileAndCompare("test_2511_list_recommend");
		}
		
		/**
		 * @Name 2512_list_recommend_fyb
		 * @catalogue 点击分期购列表的推荐车系相关操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin
		 */
		@Test
		public void test_2512_list_recommend_fyb() {
			reports_BuyCarTest.startTest("test_2512_list_recommend_fyb");
			EventManager.fileName = "test_2512_list_recommend_fyb.txt";
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购，进入分期购列表");
				ClickOnFYB();//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("newCallgray"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击分期购按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购按钮，进入分期购列表");
			}
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "list_switch#tab=2/type=1", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=5", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=3", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众-不限车系");
				clickElementById("tvBrand");
				wait(2);
				clickElementByName("大众");
				wait(2);
				clickElementByName("不限车系");
				wait(2);//android
			}else if (os == 1){
				//TODO:
				clickByNameCount("品牌 ", 1, 5);
				scrollToElementClick("大众", 2,"TRUE");
				scrollToElementClick("不限车系", 2,"TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众--不限车系");
			}
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "brand_filter#brandid=84/seriesid=0/page=3", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_series_expo#page=3", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=3", "", "");
			if (os == 2) {
//				jsTapyDistance_X_Y_Matches("text","大众帕萨特", 0, 0, "+", "+", "点击大众 帕萨特","up");
				clickElementByName("大众帕萨特");
				wait(2);//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("大众帕萨特"), 0*screenRatioX, 0*screenRatioY, "-", "-", "点击大众帕萨特");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "series_choice_expo#page=3", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_carage_expo#page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=3", "", "");
//			sliding("up");
			if (os == 2) {
				jsTapyDistance_X_Y_Matches("text","3年以内", 0, 0, "+", "+", "点击3年以内","up");
				wait(3);//android
			}else if (os == 1){
				//TODO:
			}
//			clickElementByName("3年以内");
//			wait(2);
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "suggest_carage_click#page=3/rank=1", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=3", "", "");
//			swipeUntilElementAppear(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v7.widget.RecyclerView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]"), "up", 10);
//			wait(3);
			if (os == 2) {
				wait(2);
				sliding("up",9);
//				clickElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v7.widget.RecyclerView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]");
//				wait(3);
//				sliding("", "", "589,1561,575,1209", "", "");
				jsTapyDistance_X_Y_Matches("text","搜该款车的人还搜过:", 0, 0, "+", "+", "点击3年以内","up");
//				sliding("", "", "589,1561,575,1209", "", "");
				String series_1= getListTextByLocator(MobileBy.id("com.uxin.usedcar:id/ll_similar_series").xpath("//android.widget.LinearLayout[1]/android.widget.TextView"), 0, "name","");//热门资讯第一个
				String series_2= getListTextByLocator(MobileBy.xpath("//android.support.v7.widget.RecyclerView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView"), 0, "name");//热门资讯第一个
//				jsTapyDistance_X_Y_Matches("text",series_2, 0, 0, "+", "+", "搜该款车的人还搜过:  第一个item");
				findElementByName(series_2).click();
//				androidDriver.findElement(MobileBy.className("android.widget.LinearLayout")).findElement(MobileBy.xpath("//android.widget.RelativeLayout["+i+"]/android.widget.TextView[1]")).getAttribute("name")
				//android
			}else if (os == 1){
				//TODO:
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "halfcarlist/pos/2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_series_expo#page=3", "", "");	
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "intro_series#page=3/seriesid=[value]/rank=1", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=3", "", "");
			pullFileAndCompare("test_2512_list_recommend_fyb");
		}
		
		/**
		 * @Name 2513_car_compare
		 * @catalogue 车辆对比相关操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin
		 */
		@Test
		public void test_2513_car_compare() {
			reports_BuyCarTest.startTest("test_2513_car_compare");
			EventManager.fileName = "test_2513_car_compare.txt";
			launchApp();
			clickCheShiTab();
			String carid = null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的对比按钮");
				clickElementById("ivCompare");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] 'navibarCompare'`]"), 0), 1, 3,"");//navibarCompareWhite  X navibarCompareWhite
//				clickByLocator(MobileBy.name(getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] 'navibarCompare'`]")), 1, 3, "");
//				jsTapyDistance_X_Y_By(getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] 'navibarCompare'`]"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击对比按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的对比按钮");
			}		
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "compare_car#carid="+carid, "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "compare_page", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_add#type=1/carid="+carid, "", "", "");		
			String carid1 = null;
			if (os == 2) {
//				jsTapyDistance_X_Y_Matches("text", getListTextByXpath("//android.widget.TextView[contains(@text,'款')]", 1, "name"),0, 0, "+", "+", "进去第二辆车进入详情页");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第二辆浏览历史的车获取carid");
				clickElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.ListView[1]/android.widget.FrameLayout[2]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]");
				wait(2);
				carid1 = findElementById("tvVehicleDetailCityName").getAttribute("text").substring(6);//获取carid
				System.out.println(carid1);
				androidDriver.pressKeyCode(AndroidKeyCode.BACK);
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "勾选第一辆浏览历史的车");
				clickElementById("iv_check");//android
			}else if (os == 1){
				//TODO:
				 jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 1, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击点击第二辆浏览历史");
				 reports_BuyCarTest.log(LogStatus.INFO, "点击第二辆浏览历史的车获取carid");
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				 backBTN();
				 jsTapyDistance_X_Y_By(MobileBy.name("compareUnSelected"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击勾选第一辆浏览历史的车");
				 reports_BuyCarTest.log(LogStatus.INFO, "勾选第一辆浏览历史的车");

			}
//			clickElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.ListView[1]/android.widget.FrameLayout[2]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_add#type=1/carid="+carid1, "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击开始对比按钮");
				clickElementById("tv_commit");
				wait(3);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='开始对比']"), 1, 5);
				reports_BuyCarTest.log(LogStatus.INFO, "点击开始对比按钮");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "compare_start#carid="+carid+","+carid1, "", "", "");	
			if (os == 2) {
				clickElementById("tvBack");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				backBTN();
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "compare_page", "", "", "");
			pullFileAndCompare("test_2513_car_compare");
		}
		
		/**
		 * @Name 2514_YYKC_Delete
		 * @catalogue 看车清单-删除操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2514_YYKC_Delete() {
			reports_BuyCarTest.startTest("test_2514_YYKC_Delete");
			EventManager.fileName = "test_2514_YYKC_Delete.txt";
			gotocate(2);
			wait(2);
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选。");
				clickElementById("tv_filter");
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击车源类型。");
				clickElementByName("车源类型");
				reports_BuyCarTest.log(LogStatus.INFO, "点击一成购。");
				clickElementByName("一成购");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				 scrollToElementClick("车源类型", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				 scrollToElementClick("一成购", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击一成购");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "more_filter#carcat=一成购/carage=0/colourid=不限/fule=不限/mile=0/standard=不限/displace=不限/engine=不限/seat=不限/button=1/brandid=0/price=0/countryid=不限/origin=不限/hotcar=不限/site=不限/page=2/cartypeid=不限", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=3", "", "");		
			String carid = null;
			if (os == 2) {
				clickElementById("ll_search");//找到XX辆车
				wait(2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页。");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击关注按钮");
				clickElementById("rlFocus");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
				 carid = clickFirstCarInList(2, "");
				 reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页。");
				 clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '关注'`]"), 0), 1, 3,"");
//				 jsTapyDistance_X_Y_By(MobileBy.name(CaseConfig.FOCUS), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击关注");
				 reports_BuyCarTest.log(LogStatus.INFO, "点击关注");
			}	
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "collect_detail#carid="+carid+"/dealerid=[value]", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击看车清单入口");
				clickElementById("llSecond");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] 'naviAppointList'`]"), 0), 1, 3,"");
//				jsTapyDistance_X_Y_By(MobileBy.name("naviAppointList"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击看车清单");
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页顶部第三个按钮看车清单");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "see_list_detail#carid="+carid+"/type=0", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "see_list_page", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_see_list_expo", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击看车清单-编辑按钮");
				clickElementById("tv_edit");//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.name("编辑"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击看车清单-编辑按钮");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "edit_see_list", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "勾选一辆车，点击删除");
				clickElementById("iv_check");
				clickElementById("tv_commit");  //删除
				if(CheckViewVisibilty(By.id("tvDialogMessage"))) {  //请确认删除所选车源
					clickElementById("bt_confirm_ok");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "delete_see_list#operation=2", "", "", "");	
				}//android
			}else if (os == 1){
				//TODO:

	             jsTapyDistance_X_Y_By(MobileBy.name("compareUnSelected"), 0*screenRatioX, 0*screenRatioY, "-", "+", "点击勾选一辆车");
				reports_BuyCarTest.log(LogStatus.INFO, "勾选一辆车，点击删除");
				jsTapyDistance_X_Y_By(MobileBy.name("删除"), 0*screenRatioX, 0*screenRatioY, "-", "+", "点击删除按钮");
//				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='删除']"), 1, 1);
				if(CheckViewVisibilty(By.name("确定"))) {  //请确认删除所选车源
					clickByCT(MobileBy.name("确定"), 1, 1);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "delete_see_list#operation=2", "", "", "");	
				}
			}
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击返回按钮，返回到详情页");
				clickElementById("iv_back");//android
			}else if (os == 1){
				//TODO:
				backBTN();
				reports_BuyCarTest.log(LogStatus.INFO, "点击返回按钮，返回到详情页");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "see_list_quit#time=[value]", "", "", "");	
			if (os == 2) {
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "再次点击关注");
				clickElementById("rlFocus");
				wait(1);//android
			}else if (os == 1){
				//TODO:
//				 CaseConfig.FOCUS=getListTextByXpath("//XCUIElementTypeButton[contains(@name,'关注')]", 0, "name","");
//				 jsTapyDistance_X_Y_By(MobileBy.name(CaseConfig.FOCUS), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击关注");
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '关注'`]"), 0), 1, 3,"");
				 reports_BuyCarTest.log(LogStatus.INFO, "再次点击关注");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "collect_detail#carid="+carid+"/dealerid=[value]", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击看车清单入口");
				clickElementById("llSecond");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] 'naviAppointList'`]"), 0), 1, 3,"");
//				jsTapyDistance_X_Y_By(MobileBy.name("naviAppointList"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击看车清单");
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页顶部第三个按钮看车清单");
				reports_BuyCarTest.log(LogStatus.INFO, "点击看车清单入口");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "see_list_detail#carid=[value]/type=0", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=[value]/time=[value]", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "see_list_page", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击看车清单-编辑按钮");
				clickElementById("tv_edit");//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.name("编辑"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击看车清单-编辑按钮");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "edit_see_list", "", "", "");	
			if (os == 2) {
				clickElementById("tv_all_select");//全选//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("全选"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击全选");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "select_all_see_list", "", "", "");
			if (os == 2) {
				clickElementById("tv_commit");  //删除
				if(CheckViewVisibilty(By.id("tvDialogMessage"))) {  //请确认删除所选车源
					clickElementById("bt_confirm_ok");
				}//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("删除"), 0*screenRatioX, 0*screenRatioY, "-", "+", "点击删除按钮");
//				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='删除']"), 1, 1);
				if(CheckViewVisibilty(By.name("确定"))) {  //请确认删除所选车源
					clickByCT(MobileBy.name("确定"), 1, 1);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "delete_see_list#operation=2", "", "", "");	
				}
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "delete_see_list#operation=2", "", "", "");
			if(os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击猜你喜欢的第一辆车");
				clickElementById("rootLine");
				wait(2);
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").split(":")[1];
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击猜你喜欢第一辆车");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页");
				
				 carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_click_see_list#rank=1/carid="+carid+"/type=0/icon=1", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid=0", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_detail_expo", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=file", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=trend", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid="+carid+"/type=0", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
			if (os == 2) {
				callBriadcastAndPullFile();
				Compare compare = new Compare(this.reports_BuyCarTest);
				compare.compare("./expected/test_2514_YYKC_Delete.txt", "./actual/statistic.json","test_2514_YYKC_Delete");
			}else if (os == 1) {
				pullFileAndCompare("test_2514_YYKC_Delete");
			}
		}
		
		/**
		 * @Name 2515_YYKC
		 * @catalogue 看车清单-预约看车操作-左滑删除,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2515_YYKC() {
			reports_BuyCarTest.startTest("test_2515_YYKC");
			EventManager.fileName = "test_2515_YYKC.txt";
			gotocate(2);
			wait(2);
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选。");
				clickElementById("tv_filter");
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击车源类型。");
				clickElementByName("车源类型");
				reports_BuyCarTest.log(LogStatus.INFO, "点击一成购。");
				clickElementByName("一成购");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				 scrollToElementClick("车源类型", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				 scrollToElementClick("一成购", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击一成购");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "more_filter#carcat=一成购/carage=0/colourid=不限/fule=不限/mile=0/standard=不限/displace=不限/engine=不限/seat=不限/button=1/brandid=0/price=0/countryid=不限/origin=不限/hotcar=不限/site=不限/page=2/cartypeid=不限", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=3", "", "");		
			String carid = null;
			if (os == 2) {
				clickElementById("ll_search");//找到XX辆车
				wait(2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页。");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击关注");
				clickElementById("rlFocus");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				 jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
				 carid = clickFirstCarInList(2, "");
				 wait(2);
				 reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页。");
				 clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '关注'`]"), 0), 1, 3,"");
				 reports_BuyCarTest.log(LogStatus.INFO, "点击关注");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "collect_detail#carid="+carid+"/dealerid=[value]", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击看车清单入口");
				clickElementById("llSecond");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] 'naviAppointList'`]"), 0), 1, 3,"");
//				jsTapyDistance_X_Y_By(MobileBy.name("naviAppointList"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击看车清单");
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页顶部第三个按钮看车清单");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "see_list_detail#carid="+carid+"/type=0", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "see_list_page", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_see_list_expo", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "左滑删除第一辆车");
				slidingInElement(findElementById("rootLine"), "left");
				clickElementById("rl_delete");//android
			}else if (os == 1){
				//TODO:
				action.press((int)(360*screenRatioX),(int)(215*screenRatioY)).waitAction(Duration.ofMillis(200)).moveTo((int)(61*screenRatioX),(int)(217*screenRatioY)).release().perform();
				reports_BuyCarTest.log(LogStatus.INFO, "左滑删除第一辆车");
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='删除']"), 1, 2);
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "delete_see_list#operation=1", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击返回按钮");
				clickElementById("iv_back");//android
			}else if (os == 1){
				//TODO:
				backBTN();
				reports_BuyCarTest.log(LogStatus.INFO, "点击返回按钮");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "see_list_quit#time=[value]", "", "", "");	
			if (os == 2) {
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "再次点击关注");
				clickElementById("rlFocus");
				wait(1);//android
			}else if (os == 1){
				//TODO:
//				 CaseConfig.FOCUS=getListTextByXpath("//XCUIElementTypeButton[contains(@name,'关注')]", 0, "name","");
//				 jsTapyDistance_X_Y_By(MobileBy.name(CaseConfig.FOCUS), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击关注");
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '关注'`]"), 0), 1, 3,"");
				reports_BuyCarTest.log(LogStatus.INFO, "再次点击关注");
			}	
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "collect_detail#carid="+carid+"/dealerid=[value]", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击看车清单入口");
				clickElementById("llSecond");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] 'naviAppointList'`]"), 0), 1, 3,"");
//				jsTapyDistance_X_Y_By(MobileBy.name("naviAppointList"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击看车清单");
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页顶部第三个按钮看车清单");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "see_list_detail#carid="+carid+"/type=0", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "see_list_page", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_see_list_expo", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击看车列表的第一辆车进入车辆详情页");
				clickElementById("tvCarWholeName");
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").substring(6);//获取carid
				System.out.println(carid);//android
			}else if (os == 1){
				//TODO:
				 jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击看车列表的第一辆车进入车辆详情页");
				 reports_BuyCarTest.log(LogStatus.INFO,"进入点击看车列表的第一辆车进入车辆详情页");
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				 System.out.println("当前carid  --->    "+carid);
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "see_list_quit#time=[value]", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_detail_expo", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=file", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=trend", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid="+carid+"/type=[value]", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
			if (os == 2) {
				clickElementById("imgBtBack");//android
			}else if (os == 1){
				//TODO:
				backBTN();
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+ carid+"/time=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "see_list_page", "", "", "");
			if (os == 2) {
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击预约");
				clickElementByName("去预约");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='去预约']"), 1, 5);
				reports_BuyCarTest.log(LogStatus.INFO, "点击预约");	
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "appointment_see_list#carid="+carid, "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "see_list_quit#time=[value]", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "appointment_car_page", "", "", "");	
			if (os == 2) {
				clickElementByName("请选择时间");//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='请选择时间']"), 1,1);
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "date_appointment_car#carid="+carid, "", "", "");	
			if (os == 2) {
				clickElementByName("确定");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.name("确定"), 1, 1);
			}
//			clickElementByName("提交");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "appointment_car_submit#carid="+carid+"/tel_num=[value]", "", "", "");	
//			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "appointment_car_quit#time=[value]", "", "", "");	
			pullFileAndCompare("test_2515_YYKC");
		}
		
		/**
		 * @Name 2517_YYKC_KCRC
		 * @catalogue 看车清单-看车日程操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2517_YYKC_KCRC() {
			reports_BuyCarTest.startTest("test_2517_YYKC_KCRC");
			EventManager.fileName = "test_2517_YYKC_KCRC.txt";
			gotocate(2);
			wait(2);
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选。");
				clickElementById("tv_filter");
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击车源类型。");
				clickElementByName("车源类型");
				reports_BuyCarTest.log(LogStatus.INFO, "点击一成购。");
				clickElementByName("一成购");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				 scrollToElementClick("车源类型", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				 scrollToElementClick("一成购", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击一成购");
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "more_filter#carcat=一成购/carage=0/colourid=不限/fule=不限/mile=0/standard=不限/displace=不限/engine=不限/seat=不限/button=1/brandid=0/price=0/countryid=不限/origin=不限/hotcar=不限/site=不限/page=2/cartypeid=不限", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=3", "", "");		
			String carid=null;
			if (os == 2) {
				clickElementById("ll_search");//找到XX辆车
				wait(2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页。");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击关注");
				clickElementById("rlFocus");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				 jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
				 carid = clickFirstCarInList(2, "");
				 reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页。");
				 clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '关注'`]"), 0), 1, 3,"");
//				 jsTapyDistance_X_Y_By(MobileBy.name(CaseConfig.FOCUS), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击关注");
				 reports_BuyCarTest.log(LogStatus.INFO, "点击关注");
				 
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "collect_detail#carid="+carid+"/dealerid=[value]", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击看车清单入口");
				clickElementById("llSecond");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				 clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] 'naviAppointList'`]"), 0), 1, 3,"");
//				jsTapyDistance_X_Y_By(MobileBy.name("naviAppointList"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击看车清单");
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页顶部第三个按钮看车清单");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "see_list_detail#carid=[value]/type=0", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=[value]/time=[value]", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "see_list_page", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击切换到看车日程");
				clickElementById("tv_see_car_schedule");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				subMenuLogin();//检查是否登陆 如果未登陆就执行  防止下一步Case阻断
				clickByCT(MobileBy.name("看车日程"), 1, 3);
				reports_BuyCarTest.log(LogStatus.INFO, "进入清单列表页点击顶部看车日程(已登录账号)");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "switch_see_list#tab=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "see_list_quit#time=[value]", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "see_schedule_page", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "判断是否有预约车辆，即是否有联系TA按钮");
				if(CheckViewVisibilty(By.id("ll_buy_car_assistant_phone"))) {
					reports_BuyCarTest.log(LogStatus.INFO, "点击联系TA");
					clickElementByName("联系TA");//android
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tel_consulting_see_schedule#400_num=[value]", "", "", "");	
					wait(3);
					androidDriver.pressKeyCode(AndroidKeyCode.BACK);
					wait(1);
					reports_BuyCarTest.log(LogStatus.INFO, "点击待安排或者看车结束的车辆");
					clickElementById("tvCarWholeName");
					wait(2);
					if(CheckViewVisibilty(By.id("tvXiaJia"))) {
						//下架
					}else {
						reports_BuyCarTest.log(LogStatus.INFO, "点击我的待看车辆进入车辆详情页");
						clickElementById("tvCarWholeName");
						wait(1);
						carid = findElementById("tvVehicleDetailCityName").getAttribute("text").substring(6);//获取carid
						System.out.println(carid);
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "schedule_detail_quit#time=[value]", "", "", "");	
						EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid=[value]", "", "", "");	
						EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_detail_expo", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=file", "", "", "");	
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=trend", "", "", "");	
						EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid="+carid, "", "");	
						EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid="+carid, "", "");	
						EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid="+carid+"/type=0", "", "");	
						EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "click_see_schedule#rank=1carid="+carid+"/status=2", "", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "see_schedule_quit#time=[value]", "", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "schedule_detail_page", "", "", "");
						
						reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
						clickElementById("imgBtBack");
						EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+ carid+"/time=[value]", "", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "schedule_detail_page", "", "", "");
					}
					reports_BuyCarTest.log(LogStatus.INFO, "判断是否有喜欢标签");
					if(CheckViewVisibilty(By.id("iv_islike"))) {
						reports_BuyCarTest.log(LogStatus.INFO, "有喜欢标签，并且点击。");
						clickElementById("tvCarWholeName");
						wait(2);
						carid = findElementById("tvVehicleDetailCityName").getAttribute("text").substring(6);//获取carid
						System.out.println(carid);
						androidDriver.pressKeyCode(AndroidKeyCode.BACK);
						wait(1);
						clickElementById("iv_islike");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "like_schedule_detail#carid="+carid+"/rank=1", "", "", "");
					}else {
						reports_BuyCarTest.log(LogStatus.INFO, "看车日程没有喜欢标签。");
						System.out.println("看车日程没有喜欢标签。");
					}
				}else {
					reports_BuyCarTest.log(LogStatus.INFO, "没有看车日程");
					System.out.println("没有看车日程");
				}
			}else if (os == 1){
				//TODO:
			}
			pullFileAndCompare("test_2517_YYKC_KCRC");
		}
		
		/**
		 * @Name 2518_SHARE
		 * @catalogue 详情页分享操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2518_SHARE() {
			reports_BuyCarTest.startTest("test_2518_SHARE");
			EventManager.fileName = "test_2518_SHARE.txt";
			gotocate(2);
			wait(2);
			launchApp();
			clickCheShiTab();
			String carid =null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页。");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击分享按钮");
				clickElementById("ivShare");//android
			}else if (os == 1){
				//TODO:
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页。");
//				anviBarShareWhite 6sPlus
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] 'anviBarShare'`]"), 0), 1, 1,"");
//				jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='anviBarShare']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击分享按钮");
//				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='anviBarShare']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击分享按钮");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "share_car#carid="+carid, "", "", "");	
			pullFileAndCompare("test_2518_SHARE");
		}
		
		/**
		 * @Name 2519_BIG_PICTURE
		 * @catalogue 点击头图相关操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2519_BIG_PICTURE() {
			reports_BuyCarTest.startTest("test_2519_BIG_PICTURE");
			EventManager.fileName = "test_2519_BIG_PICTURE.txt";
			gotocate(2);
			wait(2);
			launchApp();
			clickCheShiTab();
			String carid=null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页。");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击头图");
				clickElementById("ivVehicleDetailsTop");//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				 scrollToElementClick("车源类型", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				 scrollToElementClick("视频检测", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击视频检测");
				 jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
//				 jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击买车页面第一辆车");
				 reports_BuyCarTest.log(LogStatus.INFO,"进入列表第一辆车");
				 carid = clickFirstCarInList(2, "");
//				clickByCT(MobileBy.className("XCUIElementTypeScrollView"), 1, 1);
				jsTapyDistance_X_Y_By(MobileBy.className("XCUIElementTypeScrollView"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击头图");
				reports_BuyCarTest.log(LogStatus.INFO, "点击头图");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "pic_browse#carid="+carid+"/type=0/button=1", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "detail_quit#carid="+carid+"/time=[value]", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "car_picture_page#carid="+carid, "", "", "");	
			if (os == 2) {
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击图片");
				clickElementByName("图片");
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆内饰");
				clickElementByName("车辆内饰");//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='图片']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击图片");
				reports_BuyCarTest.log(LogStatus.INFO, "点击图片");
				jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='车辆内饰']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击车辆内饰");
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆内饰");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tab_car_picture#carid="+carid+"/tab=车辆内饰/rank=2", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击结构及发动机");
				clickElementByName("结构及发动机");//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='结构及发动机']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击结构及发动机");
				reports_BuyCarTest.log(LogStatus.INFO, "点击结构及发动机");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tab_car_picture#carid="+carid+"/tab=结构及发动机/rank=3", "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击更多细节");
				clickElementByName("更多细节");//android
			}else if (os == 1){
				//TODO:
				scrollToElementClick("更多细节", 3, "TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "点击更多细节");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tab_car_picture#carid="+carid+"/tab=更多细节/rank=4", "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击查看全部");
				clickElementByName("查看全部");
				wait(2);//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='查看全部']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击查看全部");
				reports_BuyCarTest.log(LogStatus.INFO, "点击查看全部");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "viewall_pic#carid="+carid, "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
				androidDriver.pressKeyCode(AndroidKeyCode.BACK);
				wait(1);
//				reports_BuyCarTest.log(LogStatus.INFO, "点击在线聊天，再点击返回");   //测试包点击在线聊天没反应
//				clickElementByName("在线聊天");
//				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "im_pic#tel_num=[value]/carid="+carid+"/type=[value]/from=[value]", "", "", "");	
//				androidDriver.pressKeyCode(AndroidKeyCode.BACK);
				reports_BuyCarTest.log(LogStatus.INFO, "点击免费电话");
				clickElementByName("免费电话");//android
			}else if (os == 1){
				//TODO:
				backBTN();
				reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
				if (CaseConfig.CallSIM==1) {
					if (CheckViewVisibilty(By.name("免费电话"))) {
		         		clickByCT(MobileBy.name("免费电话"), 1, 2);
		             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "vehicle_details/tel_free/67586363/1/type/0", "", "", "");
		    		    }
				}
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tel_free_pic#carid="+carid+"/type=0", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击免费通话");
				clickElementById("btnFreePhone");//免费通话
				wait(5);//android
			}else if (os == 1){
				//TODO:
//				if (CheckViewVisibilty(By.name("免费通话"))) {
//	         		clickByCT(MobileBy.name("免费通话"), 1, 2);
//	             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "vehicle_details/tel_free/67586363/1/type/0", "", "", "");
//	    		    }
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "tel_free_confirm_pic#carid="+carid+"/tel_num=[value]/type=0", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击电话咨询");
				clickElementByName("电话咨询");//android
			}else if (os == 1){
				//TODO:
				if (CaseConfig.CallSIM==1) {
					if (CheckViewVisibilty(By.name("电话咨询"))) {
						reports_BuyCarTest.log(LogStatus.INFO, "点击电话咨询");
		             	clickByCT(MobileBy.name("电话咨询"), 1, 2);
		             	reports_BuyCarTest.log(LogStatus.INFO, "点击电话咨询");
					}
				}
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "tel_consulting_pic#carid="+carid+"/400_num=[value]/type=0", "", "", "");	
			pullFileAndCompare("test_2519_BIG_PICTURE");
		}
		
		/**
		 * @Name 2520_CHAOZHI
		 * @catalogue 点击车辆超值以及询底价相关操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2520_CHAOZHI() {
			reports_BuyCarTest.startTest("test_2520_CHAOZHI");
			EventManager.fileName = "test_2520_CHAOZHI.txt";
			gotocate(2);
			wait(2);
			launchApp();
			clickCheShiTab();
			String carid=null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击带有超值图标的车进入详情页。");
//				clickElementById("tvCarWholeName");
				clickElementById("chaozhi_iv");
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").substring(6);//获取carid
				System.out.println(carid);//android
			}else if (os == 1){
				//TODO:
				  if (CheckViewVisibilty(By.name("chao_zhi_icon"))) {
					  jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeImage[@name='chao_zhi_icon']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "超值");
					  reports_BuyCarTest.log(LogStatus.INFO, "点击带有超值图标的车进入详情页。");
				  }
				  carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				System.out.println(carid);
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carlist_click#AI_num=[value]/result=1/icon=[value]/class=1/mold=3/rank=1/type=[value]/page=2/carid="+carid+"/word=/label=[value]/video=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_detail_expo", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=file", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=trend", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid="+carid+"/type=0", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的车价超值按钮");
				clickElementByName("车价超值");
				wait(2);//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("车价超值"), 0*screenRatioX, 0*screenRatioY, "+", "+", "车价超值");
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的车价超值按钮");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "price_analyze_detail#carid="+carid+"/type=0", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");	
			if (os == 2) {
				int width = androidDriver.manage().window().getSize().width;
				int height = androidDriver.manage().window().getSize().height;
				androidDriver.swipe(width/2, height*2/3, width/2, height/3, 1000);
				reports_BuyCarTest.log(LogStatus.INFO, "点击在售车源的车进入详情页。");
				clickElementById("tvCarWholeName");
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").substring(6);//获取carid
				System.out.println(carid);//android
			}else if (os == 1){
				//TODO:
				jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
				 jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击在售车源的车进入详情页");
				 reports_BuyCarTest.log(LogStatus.INFO,"进入列表第一辆车");
				 carid = clickFirstCarInList(2, "");
				wait(2);
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_click_sold#carid="+carid+"/rank=1", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_detail_expo", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=file", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=trend", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid="+carid+"/type=0", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
			if (os == 2) {
				clickElementById("imgBtBack");//android
			}else if (os == 1){
				//TODO:
				backBTN();
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+ carid+"/time=[value]", "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击电话咨询");
				clickElementByName("电话咨询");//android
			}else if (os == 1){
				//TODO:
				if (CaseConfig.CallSIM==1) {
					jsTapyDistance_X_Y_By(MobileBy.name("电话咨询"), 0*screenRatioX, 0*screenRatioY, "+", "+", "电话咨询");
					reports_BuyCarTest.log(LogStatus.INFO, "点击电话咨询");
					jsTapyDistance_X_Y_By(MobileBy.name("取消"), 0*screenRatioX, 0*screenRatioY, "+", "+", "电话咨询");
				}
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "tel_consulting_detail#400_num=[value]/carid="+carid+"/button=4/type=0", "", "", "");	
			if (os == 2) {
				androidDriver.pressKeyCode(AndroidKeyCode.BACK);
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击询底价按钮");
				clickElementByName("询底价");
				wait(1);//android
			}else if (os == 1){
				//TODO:iOS 是 我要优惠
				jsTapyDistance_X_Y_By(MobileBy.name("我要优惠"), 0*screenRatioX, 0*screenRatioY, "+", "+", "我要优惠");
				reports_BuyCarTest.log(LogStatus.INFO, "点击我要优惠按钮");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "bottomprice_vehicle_details#carid="+carid+"/type=0/button=1", "", "", "");	
//			clickElementByName("询底价");//提交询底价
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "bottomprice_vehicle_details#carid=[value]/type=[value]/button=3", "", "", "");	
//			clickElementByName("确定");
//			wait(1);
				pullFileAndCompare("test_2520_CHAOZHI");
		}
		
		/**
		 * @Name 2521_FQG_Detail
		 * @catalogue 点击详情页的分期购操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2521_FQG_Detail() {
			reports_BuyCarTest.startTest("test_2521_FQG_Detail");
			EventManager.fileName = "test_2521_FQG_Detail.txt";
			gotocate(2);
			wait(2);
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购，进入分期购列表");
				ClickOnFYB();//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("newCallgray"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击分期购按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购按钮，进入分期购列表");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "list_switch#tab=2/type=1", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=5", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=3", "", "");
			String carid =null;
			if (os == 2) {
				wait(2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入详情页。");
				carid = clickFirstCarInList(3, "");
			}else if (os == 1){
				//TODO:
				carid = clickFirstCarInList(3, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入详情页。");
			}
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击分期购了解详情");
				clickElementById("rlVehicleDetailHalfPrice");
				wait(3);//android
			}else if (os == 1){
				//TODO:
				 String sf = getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '月供'`]");
			        System.out.println("首付+月供价格--->>    "+sf);
			        jsTapyDistance_X_Y_By(MobileBy.name(sf), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击分期购了解详情");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "half_plan#carid="+carid, "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");	
			pullFileAndCompare("test_2521_FQG_Detail");
		}
		
		/**
		 * @Name 2522_CYDT
		 * @catalogue 点击详情页的车源动态操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin iOS liyiwan
		 */
		@Test
		public void test_2522_CYDT() {
			reports_BuyCarTest.startTest("test_2522_CYDT");
			EventManager.fileName = "test_2522_CYDT.txt";
			gotocate(2);
			wait(2);
			launchApp();
			clickCheShiTab();
			String carid=null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入详情页。");
				carid = clickFirstCarInList(2, "");
				sliding("up");
				reports_BuyCarTest.log(LogStatus.INFO, "点击车源动态");
				clickElementById("rl_head");
				wait(3);//android
			}else if (os == 1){
				//TODO:
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入详情页。");
				jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
				reports_BuyCarTest.log(LogStatus.INFO, "点击车源动态");
				jsTapyDistance_X_Y_By(MobileBy.name("车源动态"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击车源动态");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "state_track#carid="+carid, "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");	
//			if (os == 2) {
//				reports_BuyCarTest.log(LogStatus.INFO, "点击我要优惠");
//				clickElementByName("我要优惠");
//				wait(1);//android
//			}else if (os == 1){
//				//TODO:
//			}
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "bottomprice_track#carid=[value]", "", "", "");
//			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
//			wait(1);
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击电话咨询");
				clickElementByName("电话咨询");
				wait(3);//android
			}else if (os == 1){
				//TODO:
				if (CaseConfig.CallSIM==1) {
					jsTapyDistance_X_Y_By(MobileBy.name("电话咨询"), 0*screenRatioX, 0*screenRatioY, "+", "+", "电话咨询");
					reports_BuyCarTest.log(LogStatus.INFO, "点击电话咨询");
					jsTapyDistance_X_Y_By(MobileBy.name("取消"), 0*screenRatioX, 0*screenRatioY, "+", "+", "电话咨询");
				}
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "tel_consulting_detail#400_num=[value]/carid="+carid+"/button=4/type=0", "", "", "");	
			pullFileAndCompare("test_2522_CYDT");
		}
		
		/**
		 * @Name test_2523_PZLD
		 * @catalogue 点击详情页的配置亮点等操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin  iOS liyiwan
		 */
		@Test
		public void test_2523_PZLD() {
			reports_BuyCarTest.startTest("test_2523_PZLD");
			EventManager.fileName = "test_2523_PZLD.txt";
			gotocate(2);
			wait(2);
			launchApp();
			clickCheShiTab();
			String carid=null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入详情页。");
				carid = clickFirstCarInList(2, "");
			}else if (os == 1){
				//TODO:
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入详情页。");
			}
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "滑动屏幕到配置亮点");
				int width = androidDriver.manage().window().getSize().width;
				int height = androidDriver.manage().window().getSize().height;
				androidDriver.swipe(width/2, height*4/5, width/2, height/5, 1000);
				wait(3);
				androidDriver.swipe(width/2, height*4/5, width/2, height/5, 1000);
				wait(3);//android
			}else if (os == 1){
				//TODO:
				jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动屏幕到配置亮点");
				jsTapyDistance_X_Y_By(MobileBy.name("档案"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击档案");
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=mainten", "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一个配置亮点");
				clickElementById("llD");
				wait(1);//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("档案"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击档案");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一个配置亮点");
				jsTapyDistance_X_Y_By(MobileBy.name("上架时间"), 0*screenRatioX, 100*screenRatioY, "+", "+", "点击上架时间对应的正下方第一个配置亮点");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "collocation_point#carid="+carid+"/rank=1", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");	
			if (os == 2) {
				androidDriver.pressKeyCode(AndroidKeyCode.BACK);
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击查看详细参数配置");
				clickElementById("tv");
				wait(2);//android
			}else if (os == 1){
				//TODO:
				if (CheckViewVisibilty(By.name("返回"))) {
					backBTN();
				}
				jsTapyDistance_X_Y_By(MobileBy.name("查看详细参数配置"), 0*screenRatioX, 0*screenRatioY, "-", "+", "点击查看详细参数配置");//
				reports_BuyCarTest.log(LogStatus.INFO, "点击查看详细参数配置");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "collocation_more#carid="+carid, "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");	
			pullFileAndCompare("test_2523_PZLD");
		}
		
		
		/**
		 * @Name test_2524_CKGY
		 * @catalogue 点击详情页的车况概要等操作,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin  iOS liyiwan
		 */
		@Test
		public void test_2524_CKGY() {
			reports_BuyCarTest.startTest("test_2524_CKGY");
			EventManager.fileName = "test_2524_CKGY.txt";
			gotocate(2);
			wait(2);
			launchApp();
			clickCheShiTab();
			String carid=null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入详情页。");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动到咨询车况");
				swipeUntilElementAppear(By.id("tvCarConsult"), "up", 2);
				wait(3);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				 scrollToElementClick("车源类型", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				 scrollToElementClick("视频检测", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击视频检测");
				 jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
				 ///预加载 视频的 车源 防止下面的Case问题 后期调整
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆进入详情页。");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动到咨询车况");
				jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
				jsTapyDistance_X_Y_By(MobileBy.name("档案"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击档案按钮");
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=mainten", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击咨询车况");
				clickElementById("tvCarConsult");
				wait(3);
//				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tel_consulting_detail#400_num=[value]/carid="+carid+"/button=1/type=[value]", "", "", "");	
				androidDriver.pressKeyCode(AndroidKeyCode.BACK);
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "滑动到查看车况详情按钮，点击车身结构件tab然后再切到车身覆盖件控件tab");
				swipeUntilElementAppear(By.id("tv_maintenance_title"), "up", 1);//android
			}else if (os == 1){///需要注意
				//TODO:
//				CaseConfig.CallSIM=1;
				if (CaseConfig.CallSIM==1) {
					
					 jsTapyDistance_X_Y_By(MobileBy.iOSNsPredicateString("wdName CONTAINS[cd] '咨询车况' "), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击");
//					 jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '咨询车况' AND visible == 1`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击");
//					clickByCT(By.xpath("//XCUIElementTypeButton[@name='咨询车况'and @visible='true']"), 1, 3);
//					action.tap((int)(359*screenRatioX),(int)(416*screenRatioY)).perform();
//					scrollToElementClick("咨询车况", 3, "");
//					jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='咨询车况' and @visible='true']"), 0*screenRatioX, 0*screenRatioY, "-", "+", "点击咨询车况坐标偏移");//
					reports_BuyCarTest.log(LogStatus.INFO, "点击咨询车况");
					wait(3);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tel_consulting_detail#400_num=[value]/carid="+carid+"/button=1/type=[value]", "", "", "");	
					backBTN();
					
				}
//				sliding("up");
				jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动到查看车况详情按钮，点击车身结构件tab然后再切到车身覆盖件控件tab");
				
			}
			
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "examine_report#carid="+carid, "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=buycar", "", "", "");	
			if (os == 2) {
				wait(3);
				clickElementById("tv_carconstruction");//车身结构件tab//android 漆面修复图
			}else if (os == 1){
				//TODO:
//				scrollToElementClick("查看详细检测报告", 3, "");//位移到 XX项下次偏移处
				scrollToElementClick("骨架件", 3, "");//位移到 XX项下次偏移处
				jsTapyDistance_X_Y_By(MobileBy.name("漆面修复图"), 0*screenRatioX, 0*screenRatioY, "-", "+", "点击漆面修复图");//
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "switch_examine#carid="+carid+"/tab=2", "", "", "");	
			if (os == 2) {
				wait(1);
				clickElementById("tv_carcoveringparts");//车身覆盖件控件tab 车辆瑕疵图
				wait(2);//车身结构件tab//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("车辆瑕疵图"), 0*screenRatioX, 0*screenRatioY, "-", "+", "点击车辆瑕疵图");//
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "switch_examine#carid="+carid+"/tab=1", "", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击查看车况详情");
				clickElementByName("查看车况详情");
				wait(2);//车身结构件tab//android
			}else if (os == 1){
				//TODO:
			}	
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "two_maintenance_record#carid="+carid, "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
			if (os == 2) {
				androidDriver.pressKeyCode(AndroidKeyCode.BACK);
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "滑动到优信购车助手的服务咨询按钮");
				swipeUntilElementAppear(By.id("tvServiceConsult"), "up", 2);
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=pic", "", "", "");	
				reports_BuyCarTest.log(LogStatus.INFO, "点击优信购车助手的服务咨询按钮");
				clickElementById("tvServiceConsult");//android
			}else if (os == 1){
				//TODO:
				scrollToElementClick("查看详情", 3, "");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动到优信购车助手的服务咨询按钮");
				if (CaseConfig.CallSIM==1) {
					jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='服务咨询']"), 0*screenRatioX, 0*screenRatioY, "-", "+", "点击优信购车助手");//
					reports_BuyCarTest.log(LogStatus.INFO, "点击优信购车助手的服务咨询按钮");// 服务咨询
					jsTapyDistance_X_Y_By(MobileBy.name("取消"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击电话取消按钮");
					reports_BuyCarTest.log(LogStatus.INFO, "点击取消按钮");
				}
				
			}	
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tel_consulting_detail#400_num=[value]/carid=[value]/button=2/type=[value]", "", "", "");	
			wait(1);
			pullFileAndCompare("test_2524_CKGY");
		}	
		
		/**
		 * @Name test_2625_carDetailAll
		 * @catalogue 点击点击买车tab 进去详情页-->滑动到车辆实拍，等待3s-->点击详情页里的图片-->点击查看全部 ,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2625_carDetailAll() {//视屏检测最后一条Case
			reports_BuyCarTest.startTest("test_2625_carDetailAll");
			EventManager.fileName = "test_2625_carDetailAll.txt";
			launchApp();
			clickCheShiTab();
			String carid =null;
			if (os == 2) {
				wait(3);
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动到车辆实拍");//android
				sliding("up",5);
	         	sliding("down",2);
	         	reports_BuyCarTest.log(LogStatus.INFO, "滑动到车辆实拍，等待3s(点击顶上角 车图)");
	         	clickByCT(MobileBy.xpath("//android.widget.TextView[@text='车图']"), 1, 3);
			}else if (os == 1){
				//TODO:
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动到车辆实拍");
				jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
				reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏的车图按钮");
	         	jsTapyDistance_X_Y_By(MobileBy.name("车图"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击车图按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动到车辆实拍，等待3s(点击顶上角 车图)");
				
			}
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid+"/tab=examine","","","");//点击顶上角车图
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid+"/pos=pic","","","");
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid+"/pos=buycar","","","");
	     	if(os == 2) {
	     		reports_BuyCarTest.log(LogStatus.INFO, "点击车辆图片");
	         	androidDriver.findElement(MobileBy.xpath("//android.support.v7.widget.RecyclerView[1]/android.widget.RelativeLayout[2]")).click();
	     	}else if (os == 1){
				//TODO:
	     		scrollToElementClick_className("车辆外观");
	     		clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeImage[contains(@type,'XCUIElementTypeImage') and @visible='true']"), 1), 1, 3,"");
//	     		clickByCT(MobileBy.className("XCUIElementTypeCell").className("XCUIElementTypeImage"), 1, 3);
	     		reports_BuyCarTest.log(LogStatus.INFO, "点击车辆图片");
			}
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT,"detail_quit#carid="+carid+"/time=[value]","","","");
	        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"pic_browse#carid="+carid+"/type=[value]/button=2","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "car_picture_page#carid="+carid, "", "", "");
			if(os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击查看全部");
				clickByCT(MobileBy.xpath("//android.widget.TextView[@text='查看全部']"), 1, 1);
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='查看全部']"), 1, 1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击查看全部");
			}
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "viewall_pic#carid="+carid, "", "", "");
	     	sleep(200);
				pullFileAndCompare("test_2625_carDetailAll");
		}
		
		/**
		 * @Name test_2626_navigation_tab
		 * @catalogue 点击视屏检测车辆 ,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2626_navigation_tab() {//视屏检测最后一条Case
			reports_BuyCarTest.startTest("test_2626_navigation_tab");
			EventManager.fileName = "test_2626_navigation_tab.txt";
			launchApp();
			clickCheShiTab();
			String carid =null;
			if (os == 2) {
				wait(3);
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动到相似推荐");//android
				sliding("up");
	         	clickByCT(MobileBy.xpath("//android.widget.TextView[@text='推荐']"), 1, 3);
			}else if (os == 1){
				//TODO:
				carid = clickFirstCarInList(3, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页");
				jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='推荐']"), 1, 1);
			}
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid+"/pos=examine","","","");
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid+"/pos=pic","","","");
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid+"/pos=buycar","","","");
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid+"/pos=recommend","","","");
	      	String carid2=null;
	      	if (os == 2) {
	      		reports_BuyCarTest.log(LogStatus.INFO, "点击相似推荐 第一辆车进入详情页");
	      		jsTapyDistance_X_Y_Matches("text", getListTextByXpath("//android.widget.TextView[contains(@text,'款')]", 0, "name"),0, 0, "+", "+", "进去相似推荐第一辆车进入详情页");
	          	wait(6);
	    		carid2 = findElementById("tvVehicleDetailCityName").getAttribute("text").substring(6);//获取carid
	    		System.out.println(carid2);
	    		wait(6);//android
			}else if (os == 1){
				//TODO:
				 jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 1, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击买车页面第一辆车 因为款排序2");
				 reports_BuyCarTest.log(LogStatus.INFO, "点击相似推荐 第一辆车进入详情页");
				 wait(2);
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
			}
	        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"recommendation#operation=1/rank=1/carid="+carid+"/type=[value]","","","");
	        EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=[value]/time=[value]", "", "", "");
	        EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid=[value]/valid=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","similar_detail_expo","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid2+"/pos=file","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid2+"/pos=trend","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid="+ carid2, "", "");
	        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid=" + carid2, "", "");
		    EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "","price_analyze_expo#carid="+carid2+"/type=[value]","","");
		    EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
		    if (os == 2) {
		    	reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
	     		backBTN();	
			}else if (os == 1){
				//TODO:
				backBTN();
				reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
			}
		    EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid2+"/time=[value]", "", "", "");
	      	if(os == 2) {
	      		reports_BuyCarTest.log(LogStatus.INFO, "判断详情页的相似推荐中是否有同系新车");
	      		if(CheckViewVisibilty(By.id("ivZhiGouTag"))) {
	      			reports_BuyCarTest.log(LogStatus.INFO, "点击同系新车");
	      			clickElementById("ivZhiGouTag");
	      			wait(2);
	      			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "newcar_click_detail#seriesid=[value]", "", "", "");
	      			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid2+"/time=[value]", "", "", "");
	      			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
	      			wait(1);
	      		}else {
	      			reports_BuyCarTest.log(LogStatus.INFO, "本车的相似推荐处没有同系新车");
	      			System.out.println("本车的相似推荐处没有同系新车");
				}
	      	}else if (os == 1){
				//TODO:
	      		reports_BuyCarTest.log(LogStatus.INFO, "判断详情页的相似推荐中是否有同系新车");
	      		jsTapyDistance_X_Y_By(MobileBy.name("推荐"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击推荐按钮");
	      		if(CheckViewVisibilty(By.id("同系新车"))) {
	      			jsTapyDistance_X_Y_By(MobileBy.name("同系新车"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击推荐按钮");
	      			reports_BuyCarTest.log(LogStatus.INFO, "点击同系新车");
	      			wait(2);
	      			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "newcar_click_detail#seriesid=[value]", "", "", "");
	      			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid2+"/time=[value]", "", "", "");
	      			backBTN();
	      		}else {
	      			reports_BuyCarTest.log(LogStatus.INFO, "本车的相似推荐处没有同系新车");
	      			System.out.println("本车的相似推荐处没有同系新车");
				}
			}
		    if(os == 2) {
		    	sliding("up", 3);
	          	sliding("down", 3);
	          	reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏中的档案");//android
	          	clickByCT(MobileBy.xpath("//android.widget.TextView[@text='档案']"), 1, 3);
		    }else if (os == 1){
				//TODO:
		    	sliding("up");
//		    	jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
		    	jsTapyDistance_X_Y_By(MobileBy.name("档案"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击档案按钮");
			}
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"navigation_tab_detail#carid="+carid2+"/type=[value]/tab=file","","","");
	     	if (os == 2) {
	     		reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏的车况按钮");
	     		if (CheckViewVisibilty(By.xpath("//*[@text=\"车况\"]"))) {
	         		clickByCT(MobileBy.xpath("//android.widget.TextView[@text='车况']"), 1, 3);
	         		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"navigation_tab_detail#carid="+carid2+"/type=0/tab=maintenance","","","");
	             	reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏的车图按钮");
	             	clickByCT(MobileBy.xpath("//android.widget.TextView[@text='车图']"), 1, 3);//android
	    		     }
			}else if (os == 1){
				//TODO:
				reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏的车况按钮");
	     		if (CheckViewVisibilty(By.name("车况"))) {
	     		 	jsTapyDistance_X_Y_By(MobileBy.name("车况"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击车况按钮");
	         		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"navigation_tab_detail#carid="+carid2+"/type=0/tab=maintenance","","","");
	             	reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏的车图按钮");
	             	jsTapyDistance_X_Y_By(MobileBy.name("车图"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击车图按钮");
	    		     }
			}
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"navigation_tab_detail#carid="+carid2+"/type=0/tab=picture","","","");
	     	if (os == 2) {
	     		reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏的推荐按钮");
	         	clickByCT(MobileBy.xpath("//android.widget.TextView[@text='推荐']"), 1, 3);//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("推荐"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击推荐按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏的推荐按钮");
			}
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"navigation_tab_detail#carid="+carid2+"/type=[value]/tab=recommend","","","");
	     	if (os == 2) {
	     		reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏的车辆按钮");
	         	clickByCT(MobileBy.xpath("//android.widget.TextView[@text='车辆']"), 1, 3);//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("车辆"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击车辆按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏的车辆按钮");
			}
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"navigation_tab_detail#carid="+carid2+"/type=[value]/tab=car","","","");	
	     	wait(2);
	     	pullFileAndCompare("test_2626_navigation_tab");
		}
		
		/**
		 * @Name test_2627_halfcarlist
		 * @catalogue 点击买车tab--->点击高级筛选--->点击一成购-->点击第一辆车进入（新）详情页--->点击在线客服 发送信息 点击返回->点击收藏2次 点击免费电话 点击电话咨询,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2627_halfcarlist() {//视屏检测最后一条Case
			reports_BuyCarTest.startTest("test_2627_halfcarlist");
			EventManager.fileName = "test_2627_halfcarlist.txt";
			clickCheShiTab();
			if (os == 2) {
				initLaunch();
				checkLoin();
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				clickByCT(MobileBy.xpath("//android.widget.TextView[@text='筛选']"), 1, 3);
				reports_BuyCarTest.log(LogStatus.INFO, "点击车源类型");
				clickElementByName("车源类型");
				reports_BuyCarTest.log(LogStatus.INFO, "点击一成购");
				clickByCT(MobileBy.xpath("//android.widget.RadioButton[@text='一成购']"), 1, 1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击找到XX辆车");//android
				clickGaoji();
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				 scrollToElementClick("车源类型", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				 scrollToElementClick("视频检测", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击视频检测");
				 jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"more_filter#carcat=不限/cartypeid=不限/brandid=0/price=0/carage=0/mile=0-0/engine=不限/countryid=不限/colourid=不限/displace=不限/fule=不限/seat=不限/page=2/button=1/standard=不限/origin=不限/site=不限/hotcar=不限","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"halfcarlist_page#from=2/type=1","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","carlist_expo#page=3/class=3/result=1","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","reset_carlist_expo#page=3","","");
			String carid=null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页");
				carid = clickFirstCarInList(3, "");
			}else if (os == 1){
				//TODO:
				carid = clickFirstCarInList(3, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页");
				
			}
			if (os == 2) {
//	     		if (CheckViewVisibilty(By.name("在线聊天"))) {
//	         		clickByCT(MobileBy.xpath("//android.widget.TextView[@text='在线聊天']"), 1, 5);
//	             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "im#tel_num=[value]/carid=" + carid+"/type=[value]/from=1", "", "", "");
//	             	EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=" + carid+"/time=[value]", "", "", "");
//	    		}else {
//	    			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='在线客服']"), 1, 5);
//	             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "im#tel_num=[value]/carid=" + carid+"/type=[value]/from=1", "", "", "");
//	             	EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=" + carid+"/time=[value]", "", "", "");
//	    		}
//	     		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
				reports_BuyCarTest.log(LogStatus.INFO, "点击关注/收藏/加入购物车");
	         	clickByCT(MobileBy.xpath("//android.widget.TextView[@text='收藏']"), 1, 2);//android
			}else if (os == 1){
				//TODO:
				reports_BuyCarTest.log(LogStatus.INFO, "点击关注/收藏/加入购物车");
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '关注'`]"), 0), 1, 3,"");
			}   	
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "collect_detail#carid="+carid+"/dealerid=[value]", "", "", "");
	     	if (os == 2) {
	         	if (CheckViewVisibilty(By.xpath("//android.widget.TextView[@text='免费电话']"))) {
	         		clickByCT(MobileBy.xpath("//android.widget.TextView[@text='免费电话']"), 1, 2);
	             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tel_free_detail#carid="+carid+"/AI_num=[value]/type=[value]", "", "", "");
	             	reports_BuyCarTest.log(LogStatus.INFO, "点击电话咨询");
	             	clickByCT(MobileBy.xpath("//android.widget.TextView[@text='电话咨询']"), 1, 2);
	             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tel_consulting#carid="+carid+"/400_num=[value]/type=[value]", "", "", "");
	             	if (CheckViewVisibilty(MobileBy.AndroidUIAutomator("new UiSelector().text(\"允许\")"))) {
	             		clickByCT(MobileBy.xpath("//android.widget.Button[@text='允许']"), 1, 1);
	    			}
	             	clickByCT(MobileBy.xpath("//android.widget.Button[@text='确定']"), 1, 1);
	    		}//android
			}else if (os == 1){
				//TODO:
				if (CaseConfig.CallSIM==1) {
					if (CheckViewVisibilty(By.name("免费电话"))) {
		         		clickByCT(MobileBy.name("免费电话"), 1, 2);
		             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tel_free_detail#carid="+carid+"/AI_num=[value]/type=[value]", "", "", "");
		             	reports_BuyCarTest.log(LogStatus.INFO, "点击电话咨询");
		             	clickByCT(MobileBy.name("电话咨询"), 1, 2);
		             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tel_consulting#carid="+carid+"/400_num=[value]/type=[value]", "", "", "");
		             	clickByCT(MobileBy.name("确定"), 1, 1);
				   }
				}
			}
	     	wait(2);
	     	pullFileAndCompare("test_2627_halfcarlist");
		}
		
		
		/**
		 * @Name test_2628_carlistSeeListExpo
		 * @catalogue 点击全国直购的车，进去详情页-->（老详情页）线聊天 发送信息 返回-->点击关注/收藏/加入购物车 点击顶部清单入口 点返回-->点击车源动态 点击询底价/我要优惠/砍价-->确定 ,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2628_carlistSeeListExpo() {
			reports_BuyCarTest.startTest("test_2628_carlistSeeListExpo");
			EventManager.fileName = "test_2628_carlistSeeListExpo.txt";
			launchApp();
			clickCheShiTab();
			String carid =null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				jsTapyDistance_X_Y_Matches("text", getListTextByXpath("//android.widget.TextView[contains(@text,'款')]", 0, "name"),0, 0, "+", "+", "进去第三辆车进入详情页");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第三辆车进入详情页");
				wait(6);
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").substring(6);//获取carid
				System.out.println(carid);//android
			}else if (os == 1){
				//TODO:
				
				jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 2, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击猜你喜欢第一辆车");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第三辆车进入详情页");
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"carlist_click#AI_num=[value]/result=1/icon=[value]/class=1/mold=3/rank=[value]/type=1/page=2/carid="+carid+"/word=/label=[value]/video=[value]","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"vehicle_details_page#carid=[value]/valid=[value]","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_detail_expo", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid="+carid,"","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid+"/pos=file","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"browse_depth_detail#carid="+carid+"/pos=trend","","","");
	        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid=" + carid, "", "");
		    EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","price_analyze_expo#carid="+carid+"/type=[value]","","");
		    EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","newcar_detail_expo","","");
//	     	if (CheckViewVisibilty(By.name("在线聊天"))) {
//	     		clickByCT(MobileBy.xpath("//android.widget.TextView[@text='在线聊天']"), 1, 5);
//	         	//ev :	im#carid=70392564/type=0/from=1/tel_num=14725836912/button=1
//	         	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "im#tel_num=[value]/carid=" + carid+"/type=[value]/from=1", "", "", "");
//	         	//ev：detail_quit#carid=[value]/time=[value]（type=q）
//	         	EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=" + carid+"/time=[value]", "", "", "");
//				
//			}
	     	if (os == 2) {
	     		if (CheckViewVisibilty(By.name("在线客服"))) {
	         		clickByCT(MobileBy.xpath("//android.widget.TextView[@text='在线客服']"), 1, 5);
	         		reports_BuyCarTest.log(LogStatus.INFO, "点击关注/收藏/加入购物车");
	             	clickByCT(MobileBy.xpath("//android.widget.TextView[@text='收藏']"), 1, 2);//android
	             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "im#tel_num=[value]/carid=" + carid+"/type=[value]/from=1", "", "", "");
	             	EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=" + carid+"/time=[value]", "", "", "");
	    		}
	     		
			}else if (os == 1){
				//TODO:
				if (CheckViewVisibilty(By.name("在线客服"))) {
	         		clickByCT(MobileBy.name("在线客服"), 1, 5);
	         		reports_BuyCarTest.log(LogStatus.INFO, "点击关注/收藏/加入购物车");
	         		backBTN();
	         		
	             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "im#tel_num=[value]/carid=" + carid+"/type=[value]/from=1", "", "", "");
	             	EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=" + carid+"/time=[value]", "", "", "");
	    		}
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '关注'`]"), 0), 1, 3,"");
			}	
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "collect_detail#carid="+carid+"/dealerid=[value]", "", "", "");
	     	if (os == 2) {
	     		reports_BuyCarTest.log(LogStatus.INFO, "点击顶部清单入口");
	         	clickByCT(MobileBy.id("com.uxin.usedcar:id/imgCarList"), 1, 2);//android
			}else if (os == 1){
				//TODO:
//				jsTapyDistance_X_Y_By(MobileBy.name("naviAppointList"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击看车清单");
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] 'naviAppointList'`]"), 0), 1, 3,"");
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页顶部第三个按钮看车清单");
			}
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "see_list_detail#carid="+carid+"/type=[value]", "", "", "");
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "see_list_page", "", "", "");
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_see_list_expo", "", "");
	     	if (os == 2) {
	     		reports_BuyCarTest.log(LogStatus.INFO, "点击返回");//android
	         	clickByCT(By.id("com.uxin.usedcar:id/iv_back"), 1, 1);
			}else if (os == 1){
				//TODO:
				backBTN();
				reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
			}
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "see_list_quit#time=[value]", "", "", "");
	     	if (os == 2) {
	     		reports_BuyCarTest.log(LogStatus.INFO, "点击车源动态");//android
	     		clickByCT(MobileBy.xpath("//android.widget.TextView[@text='保证真实车源']"), 1, 2);
//	         	jsTapyDistance_X_Y_Matches("text","车源动态", 0, 0, "+", "+", "车源动态",true);	
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("保证真实车源"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击保证真实车源");
				reports_BuyCarTest.log(LogStatus.INFO, "点击车源动态");
			}
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "state_track#carid="+carid, "", "", "");
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
	     	if (os == 2) {
	     		backBTN();
	         	sliding("up");
	         	reports_BuyCarTest.log(LogStatus.INFO, "滑动一屏等待 等待3秒");
	         	wait(3);//android
			}else if (os == 1){
				//TODO:
				backBTN();
				jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
			}
	     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#pos=mainten/carid="+carid, "", "", "");
	     	if (os == 2) {
	     		if (CheckViewVisibilty(By.name("免费电话"))) {
	         		clickByCT(MobileBy.xpath("//android.widget.TextView[@text='免费电话']"), 1, 2);
	             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "vehicle_details/tel_free/67586363/1/type/0", "", "", "");
	    		    }
	         	if (CheckViewVisibilty(By.name("我要优惠"))) {
	         		clickByCT(MobileBy.xpath("//android.widget.TextView[@text='我要优惠']"), 1, 2);
	             	//点击询底价/我要优惠/砍价
	         		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "bottomprice_track#carid="+carid, "", "", "");
//	             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "bottomprice_submit_track#carid="+carid+"/tel_num=[value]/valid=1", "", "", "");
	    		 }
	    		wait(2);//android
			}else if (os == 1){
				//TODO:
				if (CaseConfig.CallSIM==1) {
					if (CheckViewVisibilty(By.name("免费电话"))) {
		         		clickByCT(MobileBy.name("免费电话"), 1, 2);
		         		clickByCT(By.name("取消"), 1, 1);
		             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "vehicle_details/tel_free/67586363/1/type/0", "", "", "");
		    		    }
				}
				
	         	if (CheckViewVisibilty(By.name("我要优惠"))) {
	         		clickByCT(MobileBy.name("我要优惠"), 1, 2);
	             	//点击询底价/我要优惠/砍价
	         		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "bottomprice_track#carid="+carid, "", "", "");
//	             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "bottomprice_submit_track#carid="+carid+"/tel_num=[value]/valid=1", "", "", "");
	    		   }
			}
				pullFileAndCompare("test_2628_carlistSeeListExpo");
		}
		
		

		/**
		 * @Name test_2629_YXRZ
		 * @catalogue 优信认证处检测 : 点击买车tab--->点击高级筛选-->车源类型点击优信认证 点击找到XX辆车-->进去第一辆车进入详情页-->滑动到优信认证卡片处，等待3s-->点击查看详情 30天包退  一年保修 315项排查 点击全国联保  ,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2629_YXRZ() {
			reports_BuyCarTest.startTest("test_2629_YXRZ");
			EventManager.fileName = "test_2629_YXRZ.txt";
			launchApp();
			clickCheShiTab();
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				clickByCT(MobileBy.xpath("//android.widget.TextView[@text='筛选']"), 1, 1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击车源类型");
				clickElementByName("车源类型");
				reports_BuyCarTest.log(LogStatus.INFO, "点击优信认证");
				clickElementByName("优信认证");
				reports_BuyCarTest.log(LogStatus.INFO, "点击找到XX辆车");//android
				clickGaoji();
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				 scrollToElementClick("车源类型", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				 scrollToElementClick("优信认证", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"优信认证");
				 jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "more_filter#carcat=优信认证/cartypeid=不限/brandid=0/price=0/carage=0/mile=0-0/engine=不限/countryid=不限/colourid=不限/displace=不限/fule=不限/seat=不限/page=2/button=1/standard=不限/origin=不限/site=不限/hotcar=不限","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "","reset_carlist_expo#page=2","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "","carlist_expo#page=2/class=1/result=1","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2","","","");
			String carid=null;
			if (os == 2) {
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击第二辆车进入详情页");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动到优信认证卡片处，等待3s");//android
				sliding("up", 1);
	            jsTapyDistance_X_Y_Matches("text","检测", 0, 0, "+", "+", "滑动到优信认证卡片");
//	            jsTapyDistance_X_Y_Matches("text","优信购车助手", 0, 0, "+", "+", "滑动到优信认证卡片",false);
//	            sliding("", "", "589,1561,575,1209", "", "");
//	    		jsTapyDistance_X_Y_Matches("text","优信认证", 0, 0, "+", "+", "滑动到优信认证卡片");
	    		swipeUntilElementAppear(By.id("tv_warrant_title"), "", 6);
	            wait(3);
			}else if (os == 1){
				//TODO:
				reports_BuyCarTest.log(LogStatus.INFO, "点击第二辆车进入详情页");
				carid = clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "滑动到优信认证卡片处，等待3s");//android
				jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "3");
//				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='车图']"), 1, 1);
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#pos=buycar/carid=" + carid, "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击查看详情");
				clickByCT(MobileBy.xpath("//android.widget.TextView[@text='查看详情']"), 1, 1);
			}else if (os == 1){
				//TODO:
				scrollToElementClick("查看详情", 3, "TRUE");//位移到 XX项下次偏移处车辆历史
				reports_BuyCarTest.log(LogStatus.INFO, "点击查看详情");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "uxin_guarantee#carid=" + carid + "/button=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=" + carid + "/time=[value]", "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
				backBTN();
				reports_BuyCarTest.log(LogStatus.INFO, "点击30天包退");
				clickByCT(MobileBy.xpath("//android.widget.TextView[@text='30天包退']"), 1, 1);
			}else if (os == 1){
				//TODO:
				backBTN();
				reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
				jsTapyDistance_X_Y_By(MobileBy.name("30天包退"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击30天包退");//由于浮层问题
				reports_BuyCarTest.log(LogStatus.INFO, "点击30天包退");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "uxin_standard#carid=" + carid + "/rank=1", "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击关闭按钮");
				clickByCT(MobileBy.id("com.uxin.usedcar:id/iv_report_finish"), 1, 1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击一年保修");//android
				clickByCT(MobileBy.xpath("//android.widget.TextView[@text='一年保修']"), 1, 1);
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("img tanchang close"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击关闭按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击关闭按钮");
				jsTapyDistance_X_Y_By(MobileBy.name("一年保修"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击一年保修");
				reports_BuyCarTest.log(LogStatus.INFO, "点击一年保修");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "uxin_standard#carid=" + carid + "/rank=2", "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击关闭按钮");
				clickByCT(MobileBy.id("com.uxin.usedcar:id/iv_report_finish"), 1, 1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击315项排查");//android
				clickByCT(MobileBy.xpath("//android.widget.TextView[@text='315项排查']"), 1, 1);
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("img tanchang close"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击关闭按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击关闭按钮");
				jsTapyDistance_X_Y_By(MobileBy.name("315项排查"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击315项排查");
				reports_BuyCarTest.log(LogStatus.INFO, "点击315项排查");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "uxin_standard#carid=" + carid + "/rank=3", "", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击关闭按钮");
				clickByCT(MobileBy.id("com.uxin.usedcar:id/iv_report_finish"), 1, 1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击全国联保");//android
				clickByCT(MobileBy.xpath("//android.widget.TextView[@text='全国联保']"), 1, 1);
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("img tanchang close"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击关闭按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击关闭按钮");
				jsTapyDistance_X_Y_By(MobileBy.name("全国联保"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击全国联保");
				reports_BuyCarTest.log(LogStatus.INFO, "点击全国联保");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "uxin_standard#carid=" + carid + "/rank=3", "", "", "");
			wait(2);
			pullFileAndCompare("test_2629_YXRZ");
		}
		
		
		
//		/**
//		 * @Name test_2630_personalCar
//		 * @catalogue 个人车源
//		 * @Grade 高级
//		 * @author liyiwan
//		 */
//		@Test
//		public void test_2630_personalCar() {//视屏检测最后一条Case
//			reports_BuyCarTest.startTest("test_2630_personalCar");
//			EventManager.fileName = "test_2630_personalCar.txt";
//			launchApp();
//			clickCheShiTab();
//			String carid=null;
//			if (os == 2) {
//				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
//				jsTapyDistance_X_Y_Matches("text", getListTextByXpath("//android.widget.TextView[contains(@text,'款')]", 2, "name"),0, 0, "+", "+", "进去第三辆车进入详情页");
//				reports_BuyCarTest.log(LogStatus.INFO, "点击第三辆车进入详情页");
//				wait(6);
//				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").substring(6);//获取carid
//				System.out.println(carid);//android
//			}else if (os == 1){
//				//TODO:
//			}
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"carlist_click#type=0/rank=3/AI_num=1/carid="+carid+"/mold=3/page=2/word=/class=1/result=1/icon=0","","","");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"price_analyze_expo#carid="+carid+"/type=[value]","","","");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"bottomparice#carid="+carid,"","","");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid+"/pos=trend","","","");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"browse_depth_detail#carid="+carid,"","","");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/vehicle_details/"+carid+"/valid/[value]", "", "", "");
//			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid=" + carid, "", "");
//	     	EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"/vehicle_details/similar_expo","","","");
//	     	if (os == 2) {
//	     		if (CheckViewVisibilty(MobileBy.id("com.uxin.usedcar:id/tvOnlineService"))) {
//	         		clickByCT(MobileBy.id("com.uxin.usedcar:id/tvOnlineService"), 1, 5);
//	             	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "im#tel_num=[value]/carid=" + carid+"/type=[value]/from=1", "", "", "");
//	             	EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=" + carid+"/time=[value]", "", "", "");
//	             	wait(2);
//	    		}//获取carid//android
//			}else if (os == 1){
//				//TODO:
//			}
//			callBriadcastAndPullFile();
//			Compare compare = new Compare(this.reports_BuyCarTest);
//			compare.compare("./expected/test_2630_personalCar.txt", "./actual/statistic.json","test_2630_personalCar");
//		}

		/**
		 * @Name test_2631_examineVideoDetail
		 * @catalogue 买车Tab-->点击视频车辆，进入其详情页-->点击头图-->点击头图的视频播放按钮 ,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2631_examineVideoDetail() {//视频自动播放 需求改变 不需要点击播放
			reports_BuyCarTest.startTest("test_2631_examineVideoDetail");
			EventManager.fileName = "test_2631_examineVideoDetail.txt";
			launchApp();
			clickCheShiTab();
			String carid = null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO,"点击高级筛选");
				clickElementByName("筛选");
				reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				clickElementByName("车源类型");
				reports_BuyCarTest.log(LogStatus.INFO,"点击视频检测");
				clickElementByName("视频检测");
				reports_BuyCarTest.log(LogStatus.INFO,"点击找到XX辆车");
				clickElementById("advanced_search_counttv");
				reports_BuyCarTest.log(LogStatus.INFO,"进入列表第一辆车");
				carid = clickFirstCarInList(2, "");
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				 scrollToElementClick("车源类型", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				 scrollToElementClick("视频检测", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击视频检测");
				 jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
//				 jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击买车页面第一辆车");
				 reports_BuyCarTest.log(LogStatus.INFO,"进入列表第一辆车");
				 carid = clickFirstCarInList(2, "");
				
			}
			if (os == 2) {
				if (CheckViewVisibilty(By.id("com.uxin.usedcar:id/imVideoIconVehicleDetail"))) {
					reports_BuyCarTest.log(LogStatus.INFO, "点击头图");
					clickByCT(MobileBy.id("com.uxin.usedcar:id/imVideoIconVehicleDetail"), 1, 1);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "pic_browse#carid=" + carid+"/type=[value]/button=1", "", "", "");
					EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "car_picture_page#carid=" + carid, "", "", "");
					EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=" + carid+"/time=[value]", "", "", "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击检测视频中的视频播放按钮");
					androidDriver.findElement(MobileBy.xpath("//android.widget.FrameLayout[1]/android.widget.ImageView[2]")).click();
					wait(2);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "examine_video_detail#carid="+carid+"/button=2/start=[value]", "", "", "");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "loading_start_video#carid="+carid+"/operation=1/clarity=1", "", "", "");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "loading_end_video#carid="+carid+"/operation=1/clarity=1/ts1=[value]/ts2=[value]", "", "", "");
					}
				wait(2);//android
			}else if (os == 1){
				//TODO:
				 if (CheckViewVisibilty(By.name("Player Centerplay"))) {
						reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏中的检测 方便切换到播放器界面");
//						jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
//						clickByCT(MobileBy.className("XCUIElementTypeScrollView"), 1, 1);
						jsTapyDistance_X_Y_By(MobileBy.className("XCUIElementTypeScrollView"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击头图");
						reports_BuyCarTest.log(LogStatus.INFO, "点击头图");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "pic_browse#carid=" + carid+"/type=[value]/button=1", "", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "car_picture_page#carid=" + carid, "", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=" + carid+"/time=[value]", "", "", "");
						reports_BuyCarTest.log(LogStatus.INFO, "点击检测视频中的视频播放按钮");
						//需求改变  视频自动播放 注释掉
//						jsTapyDistance_X_Y_By(MobileBy.name("Player play"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击检测视频中的视频播放按钮");//由于浮层问题
						wait(2);
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "examine_video_detail#carid="+carid+"/button=2/start=[value]", "", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "loading_start_video#carid="+carid+"/operation=1/clarity=1", "", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "loading_end_video#carid="+carid+"/operation=1/clarity=1/ts1=[value]/ts2=[value]", "", "", "");
				 }
			}
			pullFileAndCompare("test_2631_examineVideoDetail");
		}
		
		/**
		 * @Name 2632_33_examineReportPage
		 * @catalogue 点击视屏检测车辆 ,点击详情页视频播放的按钮，检测触发的埋点(对应文档32、33列)
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2632_33_examineReportPage() {//视屏检测最后一条Case
			reports_BuyCarTest.startTest("test_2632_33_examineReportPage");
			EventManager.fileName = "test_2632_33_examineReportPage.txt";
			launchApp();
			clickCheShiTab();
			String carid =null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO,"点击高级筛选");
				clickElementByName("筛选");
				reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				clickElementByName("车源类型");
				reports_BuyCarTest.log(LogStatus.INFO,"点击视频检测");
				clickElementByName("视频检测");
				reports_BuyCarTest.log(LogStatus.INFO,"点击找到XX辆车");
				clickElementById("advanced_search_counttv");
				reports_BuyCarTest.log(LogStatus.INFO,"进入列表第一辆车");
				carid = clickFirstCarInList(2, "");
				if (CheckViewVisibilty(By.id("com.uxin.usedcar:id/imVideoIconVehicleDetail"))) {
					reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏中的检测 方便切换到播放器界面");
					sliding("up");
					clickByCT(MobileBy.xpath("//android.widget.TextView[@text='检测']"), 1, 3);
					reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的视频播放按钮");
			     	clickElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]"
			     			+ "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]"
			     			+ "/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]"
			     			+ "/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v7.widget.RecyclerView[1]"
			     			+ "/android.widget.LinearLayout[2]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]"
			     			+ "/android.widget.ImageView[2]");
			     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "examine_video_detail#carid="+carid+"/button=1/start=[value]", "", "", "");
			     	reports_BuyCarTest.log(LogStatus.INFO, "点击事故排查");
			     	clickElementByName("事故排查");
					EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "examine_report_page#carid="+carid, "", "", "");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "examine_list_detail#carid="+carid+"/button=1", "", "", "");
					EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击轻微碰撞tab");
					clickByCT(MobileBy.xpath("//android.widget.TextView[@text='轻微碰撞']"), 1, 1);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tab_examine#carid="+carid+"/tab=2", "", "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击易损耗部件tab");
					clickByCT(MobileBy.xpath("//android.widget.TextView[@text='易损耗部件']"), 1, 1);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tab_examine#carid="+carid+"/tab=3", "", "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击常用功能tab");
					clickByCT(MobileBy.xpath("//android.widget.TextView[@text='常用功能']"), 1, 1);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tab_examine#carid="+carid+"/tab=4", "", "");	
					reports_BuyCarTest.log(LogStatus.INFO, "点击启动检测tab");
					clickByCT(MobileBy.xpath("//android.widget.TextView[@text='启动检测']"), 1, 1);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tab_examine#carid="+carid+"/tab=5", "", "");	
					reports_BuyCarTest.log(LogStatus.INFO, "点击外观内饰tab");
					jsTapyDistance_X_Y_Matches("text","外观内饰", 0, 0, "+", "+", "点击外观内饰tab");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tab_examine#carid="+carid+"/tab=6", "", "");	
					reports_BuyCarTest.log(LogStatus.INFO, "点击瑕疵及修复tab");
					jsTapyDistance_X_Y_Matches("text","瑕疵及修复", 0, 0, "+", "+", "点击瑕疵及修复tab");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tab_examine#carid="+carid+"/tab=8", "", "");		
					reports_BuyCarTest.log(LogStatus.INFO, "点击咨询车况");
					clickByCT(MobileBy.xpath("//android.widget.TextView[@text='咨询车况']"), 1, 1);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tel_consulting_examine#400_num=[value]/carid="+carid, "", "");
					androidDriver.pressKeyCode(AndroidKeyCode.BACK);
					reports_BuyCarTest.log(LogStatus.INFO, "点击询底价按钮");
					clickByCT(MobileBy.xpath("//android.widget.TextView[@text='询底价']"), 1, 1);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "bottomprice_examine#carid="+carid, "", "");
				}
				wait(2);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				 scrollToElementClick("车源类型", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				 scrollToElementClick("视频检测", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击视频检测");
				 jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
//				 jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击买车页面第一辆车");
				 reports_BuyCarTest.log(LogStatus.INFO,"进入列表第一辆车");
				 carid = clickFirstCarInList(2, "");
				wait(2);
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				 if (CheckViewVisibilty(By.name("Player Centerplay"))) {
						reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏中的检测 方便切换到播放器界面");
						jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
//						clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='车图']"), 1, 1);
						reports_BuyCarTest.log(LogStatus.INFO, "点击车图方便切换ScrollView按钮");
						jsTapyDistance_X_Y_By(MobileBy.name("检测"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击检测按钮");
						reports_BuyCarTest.log(LogStatus.INFO, "点击详情页的视频播放按钮");
						jsTapyDistance_X_Y_By(MobileBy.name("Player Centerplay"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击详情页视频播放按钮");
				     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "examine_video_detail#carid="+carid+"/button=1/start=[value]", "", "", "");
				     
				     	defectDetail("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[1]/XCUIElementTypeButton"
				             		, "", 1);
				     	EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "examine_video_detail#carid="+carid+"/button=1/start=[value]", "", "", "");
				     	reports_BuyCarTest.log(LogStatus.INFO, "点击事故排查");
						EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "examine_report_page#carid="+carid, "", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "examine_list_detail#carid="+carid+"/button=1", "", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
						reports_BuyCarTest.log(LogStatus.INFO, "点击轻微碰撞tab");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tab_examine#carid="+carid+"/tab=2", "", "");
						reports_BuyCarTest.log(LogStatus.INFO, "点击易损耗部件tab");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tab_examine#carid="+carid+"/tab=3", "", "");
						reports_BuyCarTest.log(LogStatus.INFO, "点击常用功能tab");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tab_examine#carid="+carid+"/tab=4", "", "");	
						reports_BuyCarTest.log(LogStatus.INFO, "点击启动检测tab");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tab_examine#carid="+carid+"/tab=5", "", "");	
						reports_BuyCarTest.log(LogStatus.INFO, "点击外观内饰tab");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tab_examine#carid="+carid+"/tab=6", "", "");	
						reports_BuyCarTest.log(LogStatus.INFO, "点击瑕疵及修复tab");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tab_examine#carid="+carid+"/tab=8", "", "");		
						if (CaseConfig.CallSIM==1) {
							jsTapyDistance_X_Y_By(MobileBy.name("咨询车况"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击咨询车况");
							jsTapyDistance_X_Y_By(MobileBy.name("取消"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击电话取消按钮");
							reports_BuyCarTest.log(LogStatus.INFO, "点击咨询车况");
						}
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "tel_consulting_examine#400_num=[value]/carid="+carid, "", "");
						reports_BuyCarTest.log(LogStatus.INFO, "点击我要优惠按钮");
						jsTapyDistance_X_Y_By(MobileBy.name("我要优惠"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击我要优惠");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "bottomprice_examine#carid="+carid, "", "");
					}
			}
				pullFileAndCompare("test_2632_33_examineReportPage");
		}
		
		/**
		 * @Name test_2634_defectDetail
		 * @catalogue 点击视屏检测车辆 ,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2634_defectDetail() {//视屏检测最后一条Case
			reports_BuyCarTest.startTest("test_2634_defectDetail");
			EventManager.fileName = "test_2634_defectDetail.txt";
			launchApp();
			clickCheShiTab();
			String carid = null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO,"点击高级筛选");
				clickElementByName("筛选");
				reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				clickElementByName("车源类型");
				reports_BuyCarTest.log(LogStatus.INFO,"点击视频检测");
				clickElementByName("视频检测");
				reports_BuyCarTest.log(LogStatus.INFO,"点击找到XX辆车");
				clickElementById("advanced_search_counttv");
				reports_BuyCarTest.log(LogStatus.INFO,"进入列表第一辆车");
				carid = clickFirstCarInList(2, "");
				if (CheckViewVisibilty(By.id("com.uxin.usedcar:id/imVideoIconVehicleDetail"))) {
					reports_BuyCarTest.log(LogStatus.INFO, "点击漆面修复图tab");
					sliding("up",1);
					jsTapyDistance_X_Y_Matches("text","检测", 0, 0, "+", "+", "点击漆面修复图");
					jsTapyDistance_X_Y_Matches("text","漆面修复图", 0, 0, "+", "+", "点击漆面修复图");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#operation=0/tab1=2/carid="+carid+"/tab2=0", "", "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击骨架件");
					jsTapyDistance_X_Y_Matches("text","骨架件", 0, 0, "+", "+", "点击骨架件");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#operation=0/tab1=2/carid="+carid+"/tab2=3", "", "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击外观件");
					jsTapyDistance_X_Y_Matches("text","外观件", 0, 0, "+", "+", "点击外观件");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#operation=0/tab1=2/carid="+carid+"/tab2=1", "", "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击车辆瑕疵图");
					jsTapyDistance_X_Y_Matches("text","车辆瑕疵图", 0, 0, "+", "+", "点击车辆瑕疵图");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#operation=0/tab1=1/carid="+carid+"/tab2=0", "", "");	
					reports_BuyCarTest.log(LogStatus.INFO, "点击内饰件");
					jsTapyDistance_X_Y_Matches("text","内饰件", 0, 0, "+", "+", "点击内饰件");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#carid="+carid+"/tab1=1/tab2=2/operation=0", "", "");	
					reports_BuyCarTest.log(LogStatus.INFO, "点击骨架件");
					jsTapyDistance_X_Y_Matches("text","骨架件", 0, 0, "+", "+", "点击骨架件");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#operation=0/tab1=1/carid="+carid+"/tab2=3", "", "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击外观件");
					jsTapyDistance_X_Y_Matches("text","外观件", 0, 0, "+", "+", "点击外观件");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#operation=0/tab1=1/carid="+carid+"/tab2=1", "", "");
				}
				wait(2);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				 scrollToElementClick("车源类型", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				 scrollToElementClick("视频检测", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击视频检测");
				 jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
//				 jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击买车页面第一辆车");
				 reports_BuyCarTest.log(LogStatus.INFO,"进入列表第一辆车");
				 carid = clickFirstCarInList(2, "");
				wait(2);
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				 
					jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
//					   CarList_IndexValueForClick("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton"
//	                           , "//XCUIElementTypeButton[1]", 0);
					 CarDetail("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton"
			             		, "", 1);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#operation=0/tab1=2/carid="+carid+"/tab2=0", "", "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击骨架件");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#operation=0/tab1=2/carid="+carid+"/tab2=3", "", "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击外观件");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#operation=0/tab1=2/carid="+carid+"/tab2=1", "", "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击车辆瑕疵图");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#operation=0/tab1=1/carid="+carid+"/tab2=0", "", "");	
					reports_BuyCarTest.log(LogStatus.INFO, "点击内饰件");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#carid="+carid+"/tab1=1/tab2=2/operation=0", "", "");	
					reports_BuyCarTest.log(LogStatus.INFO, "点击骨架件");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#operation=0/tab1=1/carid="+carid+"/tab2=3", "", "");
					reports_BuyCarTest.log(LogStatus.INFO, "点击外观件");
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_defect_detail#operation=0/tab1=1/carid="+carid+"/tab2=1", "", "");
			}
			wait(2);
			pullFileAndCompare("test_2634_defectDetail");
		}

		/**
		 * @Name test_2635_defectDetail
		 * @catalogue 点击买车tab 点击视频车辆，进入其详情页--> 点击查看详细检测报告,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2635_defectDetail() {//视屏检测最后一条Case
			reports_BuyCarTest.startTest("test_2635_defectDetail");
			EventManager.fileName = "test_2635_defectDetail.txt";
			launchApp();
			clickCheShiTab();
			String carid =null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO,"点击高级筛选");
				clickElementByName("筛选");
				reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				clickElementByName("车源类型");
				reports_BuyCarTest.log(LogStatus.INFO,"点击视频检测");
				clickElementByName("视频检测");
				reports_BuyCarTest.log(LogStatus.INFO,"点击找到XX辆车");
				clickElementById("advanced_search_counttv");
				reports_BuyCarTest.log(LogStatus.INFO,"进入列表第一辆车");
				carid = clickFirstCarInList(2, "");
				if (CheckViewVisibilty(By.id("com.uxin.usedcar:id/imVideoIconVehicleDetail"))) {
					reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏中的检测 方便切换到播放器界面");
					sliding("up");
					clickByCT(MobileBy.xpath("//android.widget.TextView[@text='检测']"), 1, 3);
					reports_BuyCarTest.log(LogStatus.INFO, "点击检测底部的查看详细检测报告按钮");
					swipeUntilElementAppear(By.id("tv_maintenance_title"), "up", 5);
					wait(3);
					clickElementById("tv_maintenance_title");
					wait(2);
					EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "examine_report_detail#carid="+carid, "", "", "");
					EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
					EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "examine_report_page#carid="+carid, "", "", "");
				}
				wait(2);//android
			}else if (os == 1){
				//TODO:111
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				 scrollToElementClick("车源类型", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击车源类型");
				 scrollToElementClick("视频检测", 2,"TRUE");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击视频检测");
				 jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
				 
//				 jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击买车页面第一辆车");
				 reports_BuyCarTest.log(LogStatus.INFO,"进入列表第一辆车");
				 carid = clickFirstCarInList(2, "");
				wait(2);
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				 if (CheckViewVisibilty(By.name("Player Centerplay"))) {
						reports_BuyCarTest.log(LogStatus.INFO, "点击导航栏中的检测 方便切换到播放器界面");
						jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "3");
//						clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='车图']"), 1, 1);
						reports_BuyCarTest.log(LogStatus.INFO, "点击车图方便切换ScrollView按钮");
//						String answer1=getListTextByLocator(MobileBy.xpath("//XCUIElementTypeStaticText[contains(@name,'项瑕疵')]"), 0, "name");//加上执行
//						scrollToElementClick("骨架件", 3, "");//位移到 XX项下次偏移处车辆历史
//						jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "3");
//						swipeUntilElementAppear(By.className("XCUIElementTypeTable"), "down", 1);
//						scrollToElementClick(MobileBy.iOSNsPredicateString("type=='XCUIElementTypeButton' AND name=='查看详细检测报告'"), 1, "", "");
//						scrollToElementClick("车辆历史", 3, "");
//						scrollToElementClick(MobileBy.iOSNsPredicateString("type=='XCUIElementTypeButton' AND name=='查看详细检测报告' AND visible == 1"), 1, "", "type=='XCUIElementTypeButton' AND name=='查看详细检测报告' AND visible == 1");
//						scrollToElementClick("查看详情", 3, "");//位移到 XX项下次偏移处车辆历史
//						reports_BuyCarTest.log(LogStatus.INFO, "点击查看详情");
//						sliding("down");
						jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='查看详细检测报告' and @visible='true']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击查看详细检测报告按钮");
//						clickByCT(MobileBy.iOSNsPredicateString("type=='XCUIElementTypeButton' AND name==''"), 1, 2);
						reports_BuyCarTest.log(LogStatus.INFO, "点击检测底部的查看详细检测报告按钮");
						EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "examine_report_detail#carid="+carid, "", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
						EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "examine_report_page#carid="+carid, "", "", "");
					}
			}
			pullFileAndCompare("test_2635_defectDetail");
		}

		/**
		 * @Name test_260X_compare
		 * @catalogue 点击视屏检测车辆 ,检测触发的埋点
		 * @Grade 高级
		 * @author yanxin
		 */
//		@Test
//		public void test_260X_compare() {//视屏检测最后一条Case
//			reports_BuyCarTest.startTest("test_260X_compare");
//			EventManager.fileName = "test_260X_compare.txt";
//			launchApp();
//			clickCheShiTab();
//			reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
//			
//			jsTapyDistance_X_Y_Matches("resourceId","com.uxin.usedcar:id/ivvideoTag", 0, 0, "+", "+", "点击视频车辆，进入其详情页");
//			wait(2);
//			String carid = findElementById("tvVehicleDetailCityName").getAttribute("text").substring(6);//获取carid
//			System.out.println(carid);
//			//ev：Page/vehicle_details/[carid]/valid/[value]（type=w）
//			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"Page/vehicle_details/"+carid+"/valid/[value]","","","");
//			//ev：browse_depth_detail#carid=[value]/pos=trend（type=c）
//			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"browse_depth_detail#pos=trend/carid="+carid,"","","");
//			
//		    //ev：browse_depth_detail#carid=[value]/pos=file（type=c）
//		    EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"browse_depth_detail#carid=[value]/pos=file"+carid+"/pos=file","","","");
	//	
//			//ev	: searchcarlist/rank/1/type/0/carid/24841997
////			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"searchcarlist/rank/1/type/0/carid/24841997","","","");
//			//ev	carlist_click#type=0/rank=2/AI_num=1/carid=65124948/mold=3/page=2/word=/class=1/result=1/icon=0
//			//ev：carlist_click#AI_num=[value]/result=1/icon=[value]/class=1/mold=3/rank=[value]/type=[value]/page=2/carid=[value]/word=
//			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carlist_click#AI_num=[value]/result=1/icon=[value]/class=1/mold=3/rank=[value]/type=[value]/page=2/carid=[value]/word=","","","");
//			
//			//pl：vehicle_details/similar_expo（type=e）
//			 EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "vehicle_details/similar_expo", "", "");
//				
//			//pl：bottomparice#carid=[value]（type=e）
//			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid=" + carid, "", "");
//			//pl：car_trend_expo#carid=[value]（type=e）
//			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid" + carid, "", "");
//			//pl：price_analyze_expo#carid=[value]/type=[value]（type=e）
//			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid=" + carid+"/type=[value]","", "");
//			
//			if (CheckViewVisibilty(By.id("com.uxin.usedcar:id/imVideoIconVehicleDetail"))) {
//				clickByCT(MobileBy.id("com.uxin.usedcar:id/imVideoIconVehicleDetail"), 1, 1);
//				//ev 	pic_browse#carid=73916250/type=0/button=1  ev：pic_browse#carid=[value]/type=[value]/button=1（type=c）
//				EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "pic_browse#carid=" + carid+"/type=[value]/button=1", "", "");
//				//ev	 car_picture_page#carid=73916250  ev：car_picture_page#carid=[value]（type=w）   ev	car_picture_page#carid=73916250  W 
//				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "", "car_picture_page#carid=" + carid, "", "");
//				//ev	 detail_quit#carid=73916250/time=915    ev	detail_quit#carid=73916250/time=915
//				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "", "detail_quit#carid=" + carid+"/time=915", "", "");
//				reports_BuyCarTest.log(LogStatus.INFO, "点击头图");
//				
//				
//				androidDriver.findElement(MobileBy.xpath("//android.widget.FrameLayout[1]/android.widget.ImageView[2]")).click();
//				//ev	examine_video_detail#carid=73916250/button=2    ev：examine_video_detail#carid=[value]/button=2（type=c）  C 
//				EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "examine_video_detail#carid=" + carid+"/button=2", "", "");
//				reports_BuyCarTest.log(LogStatus.INFO, "点击检测视频中的视频播放按钮");
//			}
//			
//			jsTapyDistance_X_Y_Matches("text", "事故排查", 0, 0, "+", "+", "滑动2屏到事故排查页面等待3s ",false);
//			reports_BuyCarTest.log(LogStatus.INFO, "滑动2屏到事故排查页面等待3s");
//			wait(3);
//			//ev：browse_depth_detail#carid=[value]/pos=examine（type=c）
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "browse_depth_detail#pos=examine/carid="+carid, "", "");
//			wait(3);
//			jsTapyDistance_X_Y("android.widget.ImageView", 0, 0, "+", "+", "播放视屏控件", "类名方式");
//			String X1=jsTapyDistance_X_Y("android.widget.ImageView", 0, 0, "+", "+", "播放视屏控件", "类名方式").get(0);
//			String Y1=jsTapyDistance_X_Y("android.widget.ImageView", 0, 0, "+", "+", "播放视屏控件", "类名方式").get(1);
//			
//			int X_=Integer.parseInt(X1);//539
//	        int Y_=Integer.parseInt(Y1);//1079
////	        androidDriver.tap(1, X_, Y_, 1);//视屏播放位置
////	        androidDriver.tap(1, (X_+404), (Y_-309), 1);//
//	        int X_mute=X_+404;
//	        int Y_mute=Y_-309;
//	        
//	        int X_start=X_-411;
//	        int Y_start=Y_-105;
//	        
//	        int X_fullscreen=X_+383;
//	        int Y_fullscreen=Y_-105;
//	        
////			jsTapyDistance_X_Y("android.widget.ImageView", 400, 0, "+", "+", "播放视屏控件", "类名方式");
////			 androidDriver.tap(1, jsTapyDistance_X_Y("android.widget.ImageView", 400, 0, "+", "+", "播放视屏控件", "类名方式").size(), Y_, 1);
//			
//			androidDriver.findElement(MobileBy.className("android.widget.FrameLayout")).findElement(MobileBy.xpath("//android.widget.FrameLayout[1]/android.widget.ImageView[2]")).click();
////			jsTapyDistance_X_Y_Matches("text", playbtn,0, 0, "+", "+", "播放视屏按钮");
//			//ev：browse_depth_detail#carid=[value]/pos=examine（type=c）
////			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "browse_depth_detail#pos=examine/carid="+carid, "", "");
//			//ev: examine_video_detail#carid=53752317/button=1
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_video_detail#carid="+carid+"/button=1", "", "");
//			reports_BuyCarTest.log(LogStatus.INFO, "点击检测视频中的视频播放按钮");
//			
//			
//			 androidDriver.tap(1, X_, Y_, 1);//视屏播放位置
//			clickByCT(MobileBy.id("com.uxin.usedcar:id/mute"), 1, 0);
////			new TouchAction((PerformsTouchActions) androidDriver).press(X_mute, Y_mute);
////			androidDriver.tap(1, X_mute, Y_mute, 0);
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "mute_examine_video#carid="+carid+"/operation=0", "","");
////			reports_BuyCarTest.log(LogStatus.INFO, "点击开启声音按钮");
//			System.out.println("点击开启声音按钮");
//			//ev：mute_examine_video#carid=[value]/operation=0（type=c）文档
//			//埋点返回的 
//			 androidDriver.tap(1, X_, Y_, 1);//视屏播放位置
//			clickByCT(MobileBy.id("com.uxin.usedcar:id/mute"), 1, 0);
////			new TouchAction((PerformsTouchActions) androidDriver).press(X_mute, Y_mute);
////			androidDriver.tap(1, X_mute, Y_mute, 0);
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "mute_examine_video#carid="+carid+"/operation=1", "","");
////			reports_BuyCarTest.log(LogStatus.INFO, "点击关闭声音按钮");
//			System.out.println("点击关闭声音按钮");
//			
//			 androidDriver.tap(1, X_, Y_, 1);//视屏播放位置
//			clickByCT(MobileBy.id("com.uxin.usedcar:id/start"), 1, 0);
////			androidDriver.tap(1, X_start, Y_start, 1);
////			new TouchAction((PerformsTouchActions) androidDriver).press(X_start, Y_start);
////			androidDriver.tap(1, X_start, Y_start, 0);
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_video_pause_detail#carid="+carid+"/from=1/time=668/max=672", "","");
////			reports_BuyCarTest.log(LogStatus.INFO, "点击暂停按钮");
//			System.out.println("点击暂停按钮");
//			
//			 androidDriver.tap(1, X_, Y_, 1);//视屏播放位置
//			clickByCT(MobileBy.id("com.uxin.usedcar:id/start"), 1, 0);
////			androidDriver.tap(1, X_start, Y_start, 1);
////			new TouchAction((PerformsTouchActions) androidDriver).press(X_start, Y_start);
////			androidDriver.tap(1, X_start, Y_start, 0);
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_video_detail#carid="+carid+"/button=1", "","");
////			reports_BuyCarTest.log(LogStatus.INFO, "点击开始按钮");
//			System.out.println("点击开始按钮");
//			
//			 androidDriver.tap(1, X_, Y_, 1);//视屏播放位置
//			jsTapyDistance_X_Y_Matches("resourceId", "com.uxin.usedcar:id/fullscreen", 0, 0, "+", "+", "点击放大按钮");
////			clickByCT(MobileBy.id("com.uxin.usedcar:id/fullscreen"), 1, 0);
////			androidDriver.tap(1, X_fullscreen, Y_fullscreen, 1);
////			new TouchAction((PerformsTouchActions) androidDriver).press(X_fullscreen, Y_fullscreen);
////			androidDriver.tap(1, X_fullscreen, Y_fullscreen, 0);
////			reports_BuyCarTest.log(LogStatus.INFO, "点击放大按钮");
//			System.out.println("点击放大按钮");
//			
//			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='高清']"), 1, 0);
//			reports_BuyCarTest.log(LogStatus.INFO, "点击点击高清");
//			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='标清']"), 1, 0);
//			reports_BuyCarTest.log(LogStatus.INFO, "点击点击标清");
//			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='极速']"), 1, 0);
//			reports_BuyCarTest.log(LogStatus.INFO, "点击点击极速");
//			clickByCT(MobileBy.id("com.uxin.usedcar:id/fullscreen"), 1, 0);
//			reports_BuyCarTest.log(LogStatus.INFO, "点击缩小视频按钮");
//			
//			clickByCT(MobileBy.id("com.uxin.usedcar:id/fullscreen"), 1, 0);
//			reports_BuyCarTest.log(LogStatus.INFO, "点击暂停按钮");
//			//ev：examine_video_pause_detail#max=[value]/carid=[value]/time=[value]/from=3（type=c）
//			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "", "examine_video_pause_detail#carid="+carid+"/from=1/time=442/max=716", "","");
//			wait(2);
//			callBriadcastAndPullFile();
//			Compare compare = new Compare(this.reports_BuyCarTest);
//			compare.compare("./expected/test_260X_compare.txt", "./actual/statistic.json","test_260X_compare");		
//		}
		
		/**
		 * @Name test_2736_tuijianCard
		 * @catalogue 点击买车  点击品牌  点击大众  点击不限 向左滑动呈现推荐卡片 点击推荐卡片的第一个（大众迈腾） 点击第一辆车进入详情页  点击详情页返回按钮 再次点击迈腾 重置 ,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2736_tuijianCard() {
			reports_BuyCarTest.startTest("test_2736_tuijianCard");
			EventManager.fileName = "test_2736_tuijianCard.txt";
			launchApp();
			clickCheShiTab();
			String carid =null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众-不限车系");
				clickElementById("tvBrand");
				wait(2);
				clickElementByName("大众");
				wait(2);
				clickElementByName("不限车系");
				wait(2);//android
				slidingInElement(findElementById("recommend_series_recyclerview"), "right");
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				jsTapyDistance_X_Y_Matches("text","大众迈腾", 0, 0, "+", "+", "点击视频车辆，进入其详情页");
				wait(2);
				clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页");
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").split(":")[1];//获取carid
				System.out.println(carid);//android
//				System.out.println("返回车系ID    "+clickFirstCarInList(2, "")+"============   "+ carid);
				clickByCT(MobileBy.id("imgBtBack"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页返回按钮");
				clickByCT(MobileBy.id("iv_pop_delete"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击重置按钮");
				
			}else if (os == 1){
				//TODO:
				clickByNameCount("品牌 ", 1, 5);
				scrollToElementClick("大众", 2,"TRUE");
				scrollToElementClick("不限车系", 2,"TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "点击品牌筛选，选择大众-不限车系");
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				action.press((int)(368*screenRatioX),(int)(263*screenRatioY)).waitAction(Duration.ofMillis(200)).moveTo((int)(70*screenRatioX),(int)(256*screenRatioY)).release().perform();
				new TouchAction((PerformsTouchActions) iosDriver).press((int)(368*screenRatioX),(int)(263*screenRatioY)).waitAction(Duration.ofMillis(200)).moveTo((int)(70*screenRatioX),(int)(256*screenRatioY)).release().perform();
//				iosDriver.findElementByIosNsPredicate("type=='XCUIElementTypeStaticText' AND name=='大众迈腾' AND visible == 1");
				scrollToElementClick("大众迈腾", "Scroll");
				reports_HomePageTest.log(LogStatus.INFO, "点击大众迈腾");
				
				clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页");
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				 backBTN();
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页返回按钮");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "brand_filter#brandid=84/seriesid=0/page=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");
			//pl：newcar_expo#（type=e）
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_expo#", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=2", "", "");	
			//ev：series_card_slide#page=2/operation=1（type=c） 向左滑动呈现推荐卡片
		     EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "series_card_slide#page=2/operation=1", "", "", "");
		   //****\
		     //点击推荐卡片的第一个（大众迈腾）ev：series_card_click#rank=1/seriesid=135/page=2（type=c）
		     EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "series_card_click#rank=1/seriesid=135/page=2", "", "", "");
		     //ev：carlist_page#type=1/from=2（type=w）
		     EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
		     //pl：carlist_expo#class=3/result=1/page=2（type=e）
		     EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");	
		     //pl：newcar_expo#（type=e）
		     EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_expo#", "", "");	
		     //pl：suggest_series_expo#page=2（type=e）
		     EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_series_expo#page=2", "", "");
		     //****/
//		     ev：detail_quit#carid=[value]/time=[value]（type=q）
//		    		 ev：carlist_page#type=0/from=3（type=w）
		     EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
		     EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=0/from=3", "", "", "");
		     
//		     ev：carlist_reset#page=2/result=1/type=2（type=c）
//		    		 ev：carlist_page#type=1/from=2（type=w）
//		    		 pl：carlist_expo#class=3/result=1/page=2（type=e）
		     EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carlist_reset#page=2/result=1/type=2", "", "", "");
		     EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
		     EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");	
		     wait(5);
		     pullFileAndCompare("test_2736_tuijianCard");
		}
		
		
		/**
		 * @Name test_2737_tuijianCard
		 * @catalogue 点击买车  点击品牌  点击大众  点击不限 向左滑动呈现推荐卡片 点击推荐卡片的第一个（大众迈腾） 点击第一辆车进入详情页  点击详情页返回按钮 再次点击迈腾 重置 ,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2737_tuijianCard() {
			reports_BuyCarTest.startTest("test_2737_tuijianCard");
			EventManager.fileName = "test_2737_tuijianCard.txt";
			launchApp();
			clickCheShiTab();
			String carid =null;
			if (os == 2) {
				clickByCT(MobileBy.id("ivSearchIcon"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击搜索框");
				input("id", "com.uxin.usedcar:id/etSearchText", "", "白色奥迪", "");
				androidDriver.pressKeyCode(AndroidKeyCode.ENTER);
				reports_BuyCarTest.log(LogStatus.INFO,"点击顶部搜索按钮 输入白色奥迪   点击搜索");
				wait(3);
				String recommend=androidDriver.findElement(MobileBy.xpath("//android.support.v7.widget.RecyclerView[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")).getAttribute("name");
				jsTapyDistance_X_Y(MobileBy.xpath(xpathReplace("//android.widget.element1[@element2='element3']", "TextView","text", recommend)),0, 0, "+", "+", "点击推荐卡片的第一个", 5);
//				clickWD("//android.widget.element1[@element2='element3']", "TextView","text", answer1, 1, 1);
//				clickByCT(MobileBy.name(androidDriver.findElement(MobileBy.xpath("//android.support.v7.widget.RecyclerView[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")).getAttribute("name")), 1, 5);
				reports_BuyCarTest.log(LogStatus.INFO,"点击推荐卡片的第一个");
//				for (int i = 1; i < 5; i++) {
//					System.out.println(androidDriver.findElement(MobileBy.xpath("//android.support.v7.widget.RecyclerView[1]/android.widget.RelativeLayout["+i+"]/android.widget.TextView[1]")).getAttribute("name"));
//				}
				//点击第一辆车进入详情页
				clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页");
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").split(":")[1];//获取carid
				System.out.println(carid);//android
//				System.out.println("返回车系ID    "+clickFirstCarInList(2, "")+"============   "+ carid);
				sliding("up", 1,3);
				reports_BuyCarTest.log(LogStatus.INFO, "滑动3屏每次停留3s");
				wait(2);
				String answer1=getListTextByXpath("//android.widget.TextView[contains(@text,'项瑕疵')]", 0, "name");
				jsTapyDistance_X_Y(MobileBy.xpath(xpathReplace("//android.widget.element1[@element2='element3']", "TextView","text", answer1)),0, 0, "+", "+", "点击推荐卡片的第一个", 5);
//				clickWD("//android.widget.element1[@element2='element3']", "TextView","text", answer1, 1, 1);
				reports_ValuableBookTest.log(LogStatus.INFO, "点击x项瑕疵按钮");
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("button rexiaocheyuan shuanglie"), 50*screenRatioX, 0*screenRatioY, "+", "+", "点击点击搜索框搜索🔍按钮");
				reports_BuyCarTest.log(LogStatus.INFO,"点击搜索框");
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 0, "value")), "白色奥迪");
			    clickByCT(MobileBy.name("Search"), 1, 5);
			    reports_BuyCarTest.log(LogStatus.INFO, "回车键执行搜索🔍");
//			    action.press((int)(368*screenRatioX),(int)(263*screenRatioY)).waitAction(Duration.ofMillis(200)).moveTo((int)(70*screenRatioX),(int)(256*screenRatioY)).release().perform();
//				new TouchAction((PerformsTouchActions) iosDriver).press((int)(368*screenRatioX),(int)(263*screenRatioY)).waitAction(Duration.ofMillis(200)).moveTo((int)(70*screenRatioX),(int)(256*screenRatioY)).release().perform();
				scrollToElementClick("奥迪A4L", "Scroll");
				reports_BuyCarTest.log(LogStatus.INFO,"点击推荐卡片的第一个");
			  //点击第一辆车进入详情页
				clickFirstCarInList(2, "");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页");
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				 if (iosDriver.getPageSource().contains("项瑕疵")) {
						jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "2");
						clickByCT(MobileBy.name("车图"), 1, 2);
						String answer1=getListTextByLocator(MobileBy.xpath("//XCUIElementTypeStaticText[contains(@name,'项瑕疵')]"), 0, "name");//加上执行
						scrollToElementClick("骨架件", 3, "");//位移到 XX项下次偏移处
						jsTapyDistance_X_Y_By(MobileBy.name(answer1), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击X项瑕疵");
						reports_ValuableBookTest.log(LogStatus.INFO, "点击x项瑕疵按钮");
//						backBTN();
//						reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
//						jsTapyDistance_X_Y_By(MobileBy.name(answer1), 0*screenRatioX, 30*screenRatioY, "+", "+", "点击详情页瑕疵图");
					}
			}
			//ev：search_bar#page=1（type=c）
			 EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "search_bar#page=1", "", "", "");
//			 ev：search_car#word=白色奥迪/result=1/input_word=白色奥迪/type=1/retri_word=/page=2（type=c）
//					 ev：carlist_page#type=1/from=2（type=w）
//					 pl：carlist_expo#class=2/result=1/page=2（type=e)
//					 pl：series_card_expo#page=2（type=e）
			 EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "search_car#word=白色奥迪/result=1/input_word=白色奥迪/type=1/retri_word=/page=2", "", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=2/result=1/page=2", "", "");	
			 EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=2", "", "");	
				//输入白色奥迪
			 //点击搜索推荐第一个
//			 ev：series_card_click#rank=1/seriesid=[value]/page=2（type=c）
//					 ev：carlist_page#type=1/from=2（type=w）
//					 pl：carlist_expo#class=3/result=1/page=2（type=e）
//					 pl：newcar_expo#（type=e）
//					 pl：suggest_series_expo#page=2（type=e）
			 EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "series_card_click#rank=1/seriesid=[value]/page=2", "", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_expo#", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_series_expo#page=2", "", "");
				//进入详情页ev：carcondition_consult_expo#carid=[value]/from=[value]/operation=2（type=e） (clickFirstCarInList漏掉)
			 EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carcondition_consult_expo#carid="+carid+"/from=[value]/operation=2", "", "");
			 //滑动3屏每次停留3s  
//			 ev：browse_depth_detail#carid=[value]/pos=examine（type=c）
//			 ev：browse_depth_detail#carid=[value]/pos=buycar（type=c）
			 EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=examine", "", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=buycar", "", "", "");
			// 点击X项瑕疵按钮 
//			 ev：defect_point_detail#carid=[value]/operation=1（type=c）
//			 ev：detail_quit#carid=[value]/time=[value]（type=q）
//			 ev：examine_report_page#carid=[value]（type=w）
			 EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "defect_point_detail#carid="+carid+"/operation=1", "", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "examine_report_page#carid="+carid,"", "", "");
		     wait(5);
		     pullFileAndCompare("test_2737_tuijianCard");
		}
		
		
		/**
		 * @Name test_2738_tuijianCard
		 * @catalogue 点击列表页第一辆超值车，进入车辆详情页(我要优惠策略) -->点击车价超值>--->点击底部我要优惠 点击弹层 我知道了 点击返回按钮  ,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2738_tuijianCard() {
			reports_BuyCarTest.startTest("test_2738_tuijianCard");
			EventManager.fileName = "test_2738_tuijianCard.txt";
			launchApp();
			clickCheShiTab();
			String carid=null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击带有超值图标的车进入详情页。");
//				clickElementById("tvCarWholeName");
				clickElementById("chaozhi_iv");
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").split(":")[1];
				System.out.println(carid);//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name(getListTextByXpath("//XCUIElementTypeImage[contains(@name,'chao_zhi_icon')]", 0, "name","")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击列表页第一辆超值车");
				reports_BuyCarTest.log(LogStatus.INFO, "点击列表页第一辆超值车，进入车辆详情页(我要优惠策略)");
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carlist_click#AI_num=[value]/result=1/icon=[value]/class=1/mold=3/rank=1/type=[value]/page=2/carid="+carid+"/word=/label=[value]/video=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_detail_expo", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=file", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=trend", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid="+carid+"/type=0", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
			
			if (os == 2) {
				int width = androidDriver.manage().window().getSize().width;
				int height = androidDriver.manage().window().getSize().height;
				androidDriver.swipe(width/2, height*2/3, width/2, height/3, 1000);
				reports_BuyCarTest.log(LogStatus.INFO, "点击在售车源的车进入详情页。");
				clickElementById("tvCarWholeName");
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").split(":")[1];
				System.out.println(carid);//android
			}else if (os == 1){
				//TODO:
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_click_sold#carid="+carid+"/rank=1", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_detail_expo", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=file", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=trend", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid="+carid+"/type=0", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
			//进入详情页ev：carcondition_consult_expo#carid=[value]/from=[value]/operation=2（type=e） (clickFirstCarInList漏掉)
			 EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carcondition_consult_expo#carid="+carid+"/from=[value]/operation=2", "", "");
			 if (os == 2) {
				 jsTapyDistance_X_Y_Matches("text","车价超值", 0, 0, "+", "+", "点击车价超值>","up");
				 reports_BuyCarTest.log(LogStatus.INFO, "点击车价超值>");
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name("车价超值"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击车价超值>");
				reports_BuyCarTest.log(LogStatus.INFO, "点击车价超值>");
			}
			 EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "price_analyze_detail#carid="+carid+"/type=0", "", "", "");	
			 EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");	
			 
			if (os == 2) {
				clickByCT(MobileBy.id("tvCenter"), 1, 3);
				reports_BuyCarTest.log(LogStatus.INFO, "点击底部我要优惠");
				clickByCT(MobileBy.id("tvIKnown"), 1, 3);
				reports_BuyCarTest.log(LogStatus.INFO, "点击弹层 我知道了");
				
				clickElementById("imgBtBack");//android
				reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
				
			}else if (os == 1){
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='我要优惠']"), 1, 1);
				reports_BuyCarTest.log(LogStatus.INFO, "点击底部我要优惠");
				if (CheckViewVisibilty(By.name("我知道了"))) {
					clickByCT(MobileBy.name("我知道了"), 1, 1);
					reports_BuyCarTest.log(LogStatus.INFO, "点击弹层 我知道了");
					
				}
				backBTN();
				reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
			}
//			ev：bottomprice_vehicle_details#carid=[value]/type=[value]/button=2（type=c）
//			ev：bottomprice_submit_vehicle_details#button=2/tel_num=[value]/carid=[vaue]/type=[value]/valid=1（type=c)
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "bottomprice_vehicle_details#carid="+ carid+"/type=[value]/button=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "bottomprice_submit_vehicle_details#button=2/tel_num=[value]/carid="+ carid+"/type=[value]/valid=1", "", "", "");
			pullFileAndCompare("test_2738_tuijianCard");
		}
		
		
		
		/**
		 * @Name test_2739_switchSeeList
		 * @catalogue  点击列表页第一辆超值车，进入车辆详情页(我要优惠策略)--> 点击详情页顶部第三个按钮看车清单-->进入清单列表页点击顶部看车日程(已登录账号)-->,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2739_switchSeeList() {
			reports_BuyCarTest.startTest("test_2739_switchSeeList");
			EventManager.fileName = "test_2739_switchSeeList.txt";
			launchApp();
			clickCheShiTab();
			String carid=null;
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击列表页第一辆超值车，进入车辆详情页(我要优惠策略)");
//				clickElementById("tvCarWholeName");
				clickElementById("chaozhi_iv");
				wait(1);
				reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").split(":")[1];//获取carid
				System.out.println(carid);//android
			}else if (os == 1){
				//TODO:
				jsTapyDistance_X_Y_By(MobileBy.name(getListTextByXpath("//XCUIElementTypeImage[contains(@name,'chao_zhi_icon')]", 0, "name","")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击列表页第一辆超值车");
//				 scrollToElementClick("chao_zhi_icon", 2,"TRUE");
				reports_BuyCarTest.log(LogStatus.INFO, "点击列表页第一辆超值车，进入车辆详情页(我要优惠策略)");
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carlist_click#AI_num=[value]/result=1/icon=[value]/class=1/mold=3/rank=1/type=[value]/page=2/carid="+carid+"/word=/label=[value]/video=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_detail_expo", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=file", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=trend", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid="+carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid="+carid+"/type=0", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页顶部第三个按钮看车清单");
				clickByCT(MobileBy.id("imgCarList"), 1, 3);
				wait(2);//android
			}else if (os == 1){
				//TODO:
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] 'naviAppointList'`]"), 0), 1, 3,"");
//				jsTapyDistance_X_Y_By(MobileBy.name("naviAppointList"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击看车清单");
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页顶部第三个按钮看车清单");
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "see_list_detail#carid="+carid+"/type=[value]", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "see_list_page", "", "", "");	
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_see_list_expo", "", "");	
			if (os == 2) {
				reports_BuyCarTest.log(LogStatus.INFO, "进入清单列表页点击顶部看车日程(已登录账号)");
				clickByCT(MobileBy.id("tv_see_car_schedule"), 1, 3);
				wait(2);//android
			}else if (os == 1){
				//TODO:
				subMenuLogin();//检查是否登陆 如果未登陆就执行  防止下一步Case阻断
				clickByCT(MobileBy.name("看车日程"), 1, 3);
				reports_BuyCarTest.log(LogStatus.INFO, "进入清单列表页点击顶部看车日程(已登录账号)");
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "switch_see_list#tab=2", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "see_schedule_page", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "see_list_quit#time=[value]", "", "", "");
		     wait(5);
		     pullFileAndCompare("test_2739_switchSeeList");
		}
		
		
		
		/**
		 * @Name test_2740_defectPointDetail
		 * @catalogue  点击买车tab  点击第二辆视频车辆，进入车辆详情页--> 滑动3个屏幕到车辆瑕疵图区域 右下角 显示2项瑕疵点-->点击2项瑕疵 跳转检测报告页 点击返回按钮 -->滑动屏幕半个屏幕，点击瑕疵图 -->详情页瑕疵图 视频车辆 点击14项瑕疵 埋点,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2740_defectPointDetail() {
			reports_BuyCarTest.startTest("test_2740_defectPointDetail");
			EventManager.fileName = "test_2740_defectPointDetail.txt";
			launchApp();
			clickCheShiTab();
			String carid=null;
			if (os == 2) {
				clickByCT(MobileBy.id("tv_filter"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				clickByCT(MobileBy.xpath("//android.widget.TextView[@text='变速箱']"), 1, 1);
				clickByCT(MobileBy.xpath("//android.widget.TextView[@text='视频检测']"), 1, 1);
				clickByCT(MobileBy.id("advanced_search_counttv"), 1, 5);
				reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				jsTapyDistance_X_Y_Matches("resourceId","com.uxin.usedcar:id/ivvideoTag", 0, 0, "+", "+", "点击视频车辆，进入其详情页");
				wait(2);
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").split(":")[1];//获取carid
				System.out.println(carid);//android
			}else if (os == 1){
				//TODO:
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='筛选 ']"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击高级筛选");
				 scrollToElementClick("变速箱", 2,"TRUE");
				 scrollToElementClick("视频检测", 2,"TRUE");
				 jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '辆车'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击找到XXX辆车按钮筛选");
				 reports_BuyCarTest.log(LogStatus.INFO,"点击找到XXX辆车按钮筛选");
				 
				 jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击买车页面第一辆车");
				reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
				wait(2);
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"carlist_click#AI_num=[value]/result=1/icon=[value]/class=1/mold=3/rank=[value]/type=[value]/page=2/carid=[value]/word=","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"Page/vehicle_details/"+carid+"/valid/[value]","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","similar_detail_expo","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#pos=trend/carid="+carid,"","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid+"/pos=file","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid=" + carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid" + carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid=" + carid+"/type=[value]","", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carcondition_consult_expo#carid="+"/from=[value]/operation=2", "", "");
			if (os == 2) {
				sliding("up", 1,3);
				wait(5);
				reports_BuyCarTest.log(LogStatus.INFO, "滑动3个屏幕到车辆瑕疵图区域 右下角 显示2项瑕疵点");
				jsTapyDistance_X_Y_Matches("text", "漆面修复图",0, 0, "+", "+", "漆面修复图  不执行点击操作",false);
				if (androidDriver.getPageSource().contains("项瑕疵")) {
					String answer1=getListTextByXpath("//android.widget.TextView[contains(@text,'项瑕疵')]", 0, "name");
					
					jsTapyDistance_X_Y_Matches("text", answer1,0, 0, "+", "+", "进去第二辆车进入详情页");
//					jsTapyDistance_X_Y(MobileBy.xpath(xpathReplace("//android.widget.element1[@element2='element3']", "TextView","text", answer1)),0, 0, "+", "+", "点击推荐卡片的第一个", 5);
//					clickWD("//android.widget.element1[@element2='element3']", "TextView","text", answer1, 1, 1);
					reports_ValuableBookTest.log(LogStatus.INFO, "点击x项瑕疵按钮");
					clickByCT(MobileBy.id("imgVideoBtBack"), 1, 3);
					reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
					jsTapyDistance_X_Y_Matches("resourceId", "com.uxin.usedcar:id/ivVehicleDetailsTop", 0, 0, "+", "+", "点击瑕疵图");
				}
				
			}else if (os == 1){
				//TODO:
				if (iosDriver.getPageSource().contains("项瑕疵")) {
					jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "2");
					clickByCT(MobileBy.name("车图"), 1, 2);
//					sliding("down");
					
					String answer1=getListTextByLocator(MobileBy.xpath("//XCUIElementTypeStaticText[contains(@name,'项瑕疵')]"), 0, "name");//加上执行
					scrollToElementClick("骨架件", 3, "");
					jsTapyDistance_X_Y_By(MobileBy.name(answer1), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击X项瑕疵");
					backBTN();
					reports_BuyCarTest.log(LogStatus.INFO, "点击返回");
					//点击瑕疵图有必现崩溃 暂时注释掉
//					jsTapyDistance_X_Y_By(MobileBy.name(answer1), 0*screenRatioX, 30*screenRatioY, "+", "+", "点击详情页瑕疵图");
				}
				
			}
			 //滑动3屏每次停留3s  
//			 ev：browse_depth_detail#carid=[value]/pos=examine（type=c）
//			 ev：browse_depth_detail#carid=[value]/pos=buycar（type=c）
			 EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=examine", "", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=buycar", "", "", "");
			// 点击X项瑕疵按钮 
//			 ev：defect_point_detail#carid=[value]/operation=1（type=c）
//			 ev：detail_quit#carid=[value]/time=[value]（type=q）
//			 ev：examine_report_page#carid=[value]（type=w）
			 EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "defect_point_detail#carid="+carid+"/operation=1", "", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "", "examine_report_page#carid="+carid, "", "");
			 //滑动屏幕半个屏幕，点击瑕疵图
//			 ev：pic_browse#carid=[value]/type=[value]/button=0（type=c）
//			 ev：car_picture_page#carid=[value]（type=w）
//			ev：detail_quit#carid=[value]/time=[value]（type=q）
			 EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "pic_browse#carid="+carid+"/type=[value]/button=0", "", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "car_picture_page#carid="+carid, "", "", "");
			 EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
		     wait(5);
		     pullFileAndCompare("test_2740_defectPointDetail");
		}
		

		/**
		 * @Name test_2741_IM
		 * @catalogue  点击买车tab 车市列表页右上角IM会话按钮 --> 返回，点击第一辆车，进入车辆详情页-->点击详情页IM 顶部第一个聊天按钮 点击跳转我的会话页面,检测触发的埋点
		 * @Grade 高级
		 * @author liyiwan
		 */
		@Test
		public void test_2741_IM() {
			reports_BuyCarTest.startTest("test_2741_IM");
			EventManager.fileName = "test_2741_IM.txt";
			launchApp();
			clickCheShiTab();
			reports_BuyCarTest.log(LogStatus.INFO,"点击车辆，进入车辆详情页");
			String carid=null;
			if (os == 2) {
				
				jsTapyDistance_X_Y_Matches("resourceId","com.uxin.usedcar:id/ivmessage", 0, 0, "+", "+", "车辆列表页右上角IM会话按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆列表页右上角IM会话按钮");
				clickByCT(MobileBy.id("imgBtBack"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页返回按钮");
//				clickFirstCarInList(2, "");
				jsTapyDistance_X_Y_Matches("text", getListTextByXpath("//android.widget.TextView[contains(@text,'款')]", 0, "name"),0, 0, "+", "+", "进去第一辆车进入详情页");
				reports_BuyCarTest.log(LogStatus.INFO, "点击第一辆车进入详情页");
				wait(2);
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").split(":")[1];//获取carid
				System.out.println(carid);//android
			}else if (os == 1){
				//TODO:carlistchat
				wait(5);
				jsTapyDistance_X_Y_By(MobileBy.name("carlistchat"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击 车辆列表页右上角IM会话按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击车辆列表页右上角IM会话按钮");
				backBTN();
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页返回按钮");
				jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击第一辆车");
				reports_HomePageTest.log(LogStatus.INFO, "点击列表中的第一辆车");
				carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				 System.out.println(carid);
				
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"carlist_click#AI_num=[value]/result=1/icon=[value]/class=1/mold=3/rank=[value]/type=[value]/page=2/carid=[value]/word=","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"Page/vehicle_details/"+carid+"/valid/[value]","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","similar_detail_expo","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#pos=trend/carid="+carid,"","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth_detail#carid="+carid+"/pos=file","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid=" + carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid" + carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid=" + carid+"/type=[value]","", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carcondition_consult_expo#carid="+"/from=[value]/operation=2", "", "");
			
			if (os == 2) {
				clickByCT(MobileBy.id("imgChat"), 1, 2);
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页IM 顶部第一个聊天按钮 点击跳转我的会话页面");
			}else if (os == 1){
				//TODO:
//				navibarChatlistWhite
				clickByLocator(getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] 'navibarChatlist'`]"), 0), 1, 1,"");
//				jsTapyDistance_X_Y_By(MobileBy.name("navibarChatlist"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击详情页IM");
				reports_BuyCarTest.log(LogStatus.INFO, "点击详情页IM 顶部第一个聊天按钮 点击跳转我的会话页面");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "im_list_detail#carid="+carid+"/type=[value]", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
		    wait(5);
		    pullFileAndCompare("test_2741_IM");
		}
	
	/**
     * 需要退回到MainActivity页面之后再执行该方法
     * 拉取文件并进行比对
     * @param test 测试用例名称
     * @param FolderName 测试用例文件夹 iOS Android 
     */
	 private void pullFileAndCompare(String test) {
		 if (os == 1) {//ios对比
			saveActualEvent();
            Compare compare = new Compare(this.reports_BuyCarTest);
            compare.compare("./expected_iOS/" + test + ".txt", "./actual_iOS/statistic.json", test);
		}else if (os == 2) {//android对比
			callBriadcastAndPullFile();
			Compare compare = new Compare(this.reports_BuyCarTest);
			compare.compare("./expected/"+test+".txt", "./actual/statistic.json",test);
		}
	       
	    }
}
