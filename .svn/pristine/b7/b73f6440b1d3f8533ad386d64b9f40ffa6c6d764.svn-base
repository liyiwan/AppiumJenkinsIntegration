package com.uxin.usedcar.testCase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import static org.junit.Assert.*;

import java.awt.Desktop.Action;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.MidiDevice.Info;

import org.apache.http.client.ClientProtocolException;
import org.apache.poi.hssf.record.ExtSSTRecord;
import org.apache.poi.ss.formula.functions.Even;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
import org.omg.CORBA.COMM_FAILURE;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.seleniumhq.jetty9.server.Authentication.Failed;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.event.Event;
import com.google.gson.Gson;
import com.relevantcodes.extentreports.LogStatus;
import com.uxin.usedcar.maidianlib.AppStatisticBean;
import com.uxin.usedcar.maidianlib.Compare;
import com.uxin.usedcar.maidianlib.EventEntity;
import com.uxin.usedcar.maidianlib.EventManager;
import com.uxin.usedcar.maidianlib.TEventEntity;
import com.uxin.usedcar.maidianlib.TestStatisticBean;
import com.uxin.usedcar.test.libs.BaseTest;
import com.uxin.usedcar.test.libs.CaseConfig;

public class HomePageTest extends BaseTest{
	
	 @BeforeClass
	  public static void first()  throws Exception {
		 reports_HomePageTest.init("./report/HomePage/reportHomePage.html", true);
	
	  }

	@AfterClass 
	public static void last() {
		reports_HomePageTest.endTest();
		System.out.println("tearDown");
	}
	/**
	 * @Name 1001_CSXZ
	 * @catalogue 首页-城市选择，触发城市选择埋点
	 * @Grade 高级
	 * @author liyifeng
	 */
	@Test
	public void test_1001_CSXZ(){
		reports_HomePageTest.startTest("test_1001_CSXZ");
		EventManager.fileName = "test_1001_CSXZ.txt";
		wait(5);
		launchApp();
		if (os == 2) {
			clickElementById("btChooseCity");
			sleep(1000);
			reports_HomePageTest.log(LogStatus.INFO, "点击城市选择按钮");
		}else if (os == 1){
			//TODO:
//			jsTapyDistance_X_Y_By(MobileBy.name(CaseConfig.CITY_NAME), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击城市选择按钮");
			CaseConfig.CITY_NAME=getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] ' '`]"), 0).getAttribute("label");
			jsTapyDistance_X_Y_By(MobileBy.name(CaseConfig.CITY_NAME), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击城市选择按钮");
			reports_HomePageTest.log(LogStatus.INFO, "点击城市选择按钮");
		}
		if (os == 2) {
			clickElementByName("阿克苏");
			sleep(1000);
			reports_HomePageTest.log(LogStatus.INFO, "切换城市到阿克苏");
		}else if (os == 1){
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.name("阿克苏"), 0*screenRatioX, 0*screenRatioY, "+", "+", "切换城市到阿克苏");
			reports_HomePageTest.log(LogStatus.INFO, "切换城市到阿克苏");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "city_choice#page=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "home_page","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "","guess_like_expo#algorithm=bus","","");
		if (os == 2) {
			clickElementById("btChooseCity");
			sleep(1000);
			reports_HomePageTest.log(LogStatus.INFO, "点击城市选择按钮");
		}else if(os == 1){
			//TODO:
//			jsTapyDistance_X_Y_By(MobileBy.name(" 阿克苏"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击城市选择按钮");
			CaseConfig.CITY_NAME=getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] ' '`]"), 0).getAttribute("label");
			jsTapyDistance_X_Y_By(MobileBy.name(CaseConfig.CITY_NAME), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击城市选择按钮");
			reports_HomePageTest.log(LogStatus.INFO, "点击城市选择按钮");
		}
		if (os == 2) {
			clickElementByName("北京");
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO, "切换城市到北京");
		}else if (os == 1){
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.name("北京"), 0*screenRatioX, 0*screenRatioY, "+", "+", "切换城市到北京");
			reports_HomePageTest.log(LogStatus.INFO, "切换城市到北京");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "city_choice#page=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "home_page","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "","guess_like_expo#algorithm=bus","","");
		if (os == 2) {
			clickElementById("tv_search");
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO, "点击搜索输入框");
		}else if(os == 1){
			//TODO:
			CaseConfig.CITY_NAME=getListByLocator(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] ' '`]"), 0).getAttribute("label");
			jsTapyDistance_X_Y_By(MobileBy.name(CaseConfig.CITY_NAME), 100*screenRatioX, 0*screenRatioY, "+", "+", "点击相对于城市选择按钮的---->搜索输入框");
//			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeOther[@value='SSID']"), 178*screenRatioX, 32*screenRatioY, "+", "+", "点击相对于城市选择按钮的---->搜索输入框");
			reports_HomePageTest.log(LogStatus.INFO, "点击搜索输入框");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "search_bar#page=1","","","");
		String tvSearchHistoryString =null;
		if (os == 2){
			tvSearchHistoryString = findElementById("tvSearchHistory").getAttribute("text");
			System.out.println("热门搜索第一个为："+tvSearchHistoryString);
			clickElementById("tvSearchHistory");
			reports_HomePageTest.log(LogStatus.INFO, "点击第一个热门搜索");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, 
					"search_car#word="+tvSearchHistoryString+"/result=1/rank=1/type=4/page=1/input_word="
			+tvSearchHistoryString+"/retri_word="+tvSearchHistoryString,"","","");
		}else if (os == 1){
			//TODO:
//			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeStaticText[@name='热门搜索']"), 0*screenRatioX, 38*screenRatioY, "+", "+", "点击第一个热门搜索");
			if (CheckViewVisibilty(By.name("历史搜索"))) {
				clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='icon 垃圾桶']"), 1, 1);
				clickByCT(MobileBy.name("确定"), 1, 1);
			}
			String searchHistoryString= carList_endValue("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell"
					 , "//XCUIElementTypeButton[1]", "name", 1);
			System.out.println("热门搜索第一个为："+searchHistoryString);
			clickByCT(MobileBy.name(searchHistoryString), 1, 2);
			reports_HomePageTest.log(LogStatus.INFO, "点击第一个热门搜索");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, 
					"search_car#word="+tvSearchHistoryString+"/result=1/rank=1/type=4/page=1/input_word="
			+tvSearchHistoryString+"/retri_word="+tvSearchHistoryString,"","","");
			
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=1", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=2/result=1/page=1", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_carage_expo#page=1", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=1", "", "");
		wait(2);
		if (os == 2) {
			clickElementById("tv_search");
			sleep(200);
			reports_HomePageTest.log(LogStatus.INFO, "点击搜索框");
		}else if (os == 1){
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeOther[@value='SSID']"), 0*screenRatioX, 32*screenRatioY, "+", "+", "点击相对于城市选择按钮的---->搜索输入框");
			reports_HomePageTest.log(LogStatus.INFO, "点击搜索输入框");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "search_bar#page1", "", "", "");
		pullFileAndCompare("test_1001_CSXZ");
	}
//	/**
//	 * @name 1002_PZSC
//	  * @catalogue 首页-拍照识车，触发拍照识车的埋点
//	 * @Grade 高级
//	 * @author liyifeng
//	 */
//	@Test
//	public void test_1002_PZSC(){
//		reports_HomePageTest.startTest("test_1100_PZSC");
//		EventManager.fileName = "test_1100_PZSC.txt";
//		launchApp();
//		if (os == 2) {
//			clickElementById("ivCameraIcon");
//			wait(2);
//			reports_HomePageTest.log(LogStatus.INFO, "点击首页拍照识车按钮");
//		}else if (os == 1){
//			//TODO:
//		}
//		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "camera_recognition#page=1", "", "", "");
//		if (os == 2) {
//			clickElementById("photograph_btn");
//			reports_HomePageTest.log(LogStatus.INFO, "点击拍照按钮");
//		}else if (os == 1) {
//			//TODO:
//		}
//		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "photo_page#page=1", "", "", "");
//		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "photo_success_page#page=1", "", "", "");
//		callBriadcastAndPullFile();
//		Compare compare = new Compare(this.reports_HomePageTest);
//		compare.compare("./expected/test_1100_PZSC.txt", "./actual/statistic.json","test_1100_PZSC");
//		}
	/**
	 * @name 1101_MC
	  * @catalogue 首页-买车，触发买车的所有埋点
	 * @Grade 高级
	 * @author liyifeng
	 */
	@Test
	public void test_1101_MC(){
		reports_HomePageTest.startTest("test_1101_MC");
		EventManager.fileName = "test_1101_MC.txt";
		wait(5);
		launchApp();
		if (os == 2) {
			clickElementById("buycarll");
			wait(3);
			reports_HomePageTest.log(LogStatus.INFO, "点击买车按钮");
		}else if (os == 1){
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='买车']"), 1, 5);
			reports_HomePageTest.log(LogStatus.INFO, "点击买车按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tab_tool_home#tab=买车/rank=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=2", "", "");
		if (os == 2) {
			clickElementById("tvSort");
			reports_HomePageTest.log(LogStatus.INFO, "点击默认排序");
		}else if(os == 1){
			//TODO:
			clickByNameCount("排序 ", 1, 5);
	        reports_HomePageTest.log(LogStatus.INFO, "点击默认排序");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_sort#button=0/page=2", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=2", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
		if (os == 2) {
			clickElementById("relativePriceHight");
			reports_HomePageTest.log(LogStatus.INFO, "点击价格最高");
		}else if (os == 1){
			//TODO:
			clickByNameCount("价格最高", 1, 5);
			reports_HomePageTest.log(LogStatus.INFO, "点击价格最高");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_sort#button=1/page=2", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=2", "", "");
		if (os == 2) {
			clickElementById("linLayPrice");
			reports_HomePageTest.log(LogStatus.INFO, "点击价格");
			clickElementByName("10万-15万");
			reports_HomePageTest.log(LogStatus.INFO, "点击10万-15万");
		}else if (os == 1){
			//TODO:
			
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "price_filter#price=2-3/page=2", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=2", "", "");
		if (os == 2) {
			clickElementById("btAdvancedFilter");
			reports_HomePageTest.log(LogStatus.INFO, "点击高级筛选按钮");
			sleep(700);
           clickElementByName("车源类型");
           sleep(200);
           reports_HomePageTest.log(LogStatus.INFO, "点击车源类型按钮");
		}else if (os == 1){
			//TODO:
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "navigation_more_filter#tab=车源类型", "", "", "");
		if (os == 2) {
			clickElementByName("优信认证");
			sleep(200);
			reports_HomePageTest.log(LogStatus.INFO,"点击优信认证");
		}else if (os == 1) {
			//TODO:
		}
		if (os == 2) {
			clickElementById("advanced_search_counttv");
			wait(3);
			reports_HomePageTest.log(LogStatus.INFO, "点击找到xx辆车");
		}else if (os == 1) {
			
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "more_filter#carcat=优信认证/cartypeid=不限/brandid=0/"
				+ "price=0/carage=0/mile=0/engine=不限/countryid=不限/colourid=不限/displace=0/fule=不限/seat=不限"
				+ "/page=2/button=1/standard=不限/origin=不限/site=不限/hotcar=不限", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=2", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");
		pullFileAndCompare("test_1101_MC");
	}
	
	/**
	 * @name 1102_YCG
	  * @catalogue 首页-一成购，触发一成购的所有埋点
	 * @Grade 高级
	 * @author liyifeng
	 */
	@Test
	public void test_1102_YCG(){
		reports_HomePageTest.startTest("test_1102_YCG");
		EventManager.fileName = "test_1102_YCG.txt";
		launchApp();
		if (os == 2) {
			clickElementById("fenqigou");
			wait(4);
			reports_HomePageTest.log(LogStatus.INFO, "点击一成购按钮");
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.name("一成购"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击一成购");//由于浮层问题
			reports_HomePageTest.log(LogStatus.INFO, "点击一成购按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tab_tool_home#tab=一成购/rank=2", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=1/from=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=3", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "", "reset_carlist_expo#page=3", "", "");
		String carid = clickFirstCarInList(3,"");
		System.out.println("返回的carid："+carid);
		if (os == 2) {
			clickElementById("imgBtBack");
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO, "点击返回按钮");
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_HomePageTest.log(LogStatus.INFO, "点击返回按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfcarlist_page#type=0/from=3", "", "", "");
		if (os == 2) {
			clickElementById("rbShouYe");
			reports_HomePageTest.log(LogStatus.INFO, "点击首页");
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.name("首页"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击首页 ");//由于浮层问题
			reports_HomePageTest.log(LogStatus.INFO, "点击首页");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "buttom/home", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "home_page", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "guess_like_expo#algorithm=bus", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","recent_filter_expo","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "subscribe_expo", "", "");
		pullFileAndCompare("test_1102_YCG");
	}
	/**
	 * @name 1103_MC
	 * @catalogue 首页-卖车，触发卖车的所有埋点
	 * @Grade 高级
	 * @author liyifeng
	 */
	@Test
	public void test_1103_MC(){
		reports_HomePageTest.startTest("test_1103_MC");
		EventManager.fileName = "test_1103_MC.txt";
		launchApp();
		if (os == 2) {
			clickElementById("fuyichengll");
			reports_HomePageTest.log(LogStatus.INFO, "点击卖车按钮");
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.name("卖车"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击卖车按钮");//由于浮层问题
			reports_HomePageTest.log(LogStatus.INFO, "点击卖车按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tab_tool_home#tab=卖车/rank=3", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "c2b_home_page", "", "", "");
		if (os == 2) {
			clickElementById("btManage");
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO, "点击卖车记录");
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.name("卖车记录"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击卖车记录按钮");
			reports_HomePageTest.log(LogStatus.INFO, "点击卖车记录");
			subMenuLogin();//检查是否登陆 如果未登陆就执行  防止下一步Case阻断
		}

		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "sellcar_record", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "sellcar_record_page#from=2", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "c2b_home_quit#time=[value]", "", "", "");
		if (os == 2) {
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
			reports_HomePageTest.log(LogStatus.INFO, "返回");
			sleep(500);
			clickElementById("tv_order_sellcar");
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO, "点击预约卖车");
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_HomePageTest.log(LogStatus.INFO, "返回");
			jsTapyDistance_X_Y_By(MobileBy.name("预约卖车"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击预约卖车按钮");
			reports_HomePageTest.log(LogStatus.INFO, "点击预约卖车");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "sellcar_c2b", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carinfo_sellcar#type=B", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "c2b_home_quit#time=[value]", "", "", "");
		if (os == 2) {
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
			sleep(500);
			clickElementById("tv_free_consultation");
			sleep(2000);
			reports_HomePageTest.log(LogStatus.INFO, "点击免费咨询按钮");
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_HomePageTest.log(LogStatus.INFO, "返回");
//			jsTapyDistance_X_Y_By(MobileBy.name("免费咨询"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击免费咨询按钮");
//			reports_HomePageTest.log(LogStatus.INFO, "点击免费咨询按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tel_consulting_c2b#400_num=4006131628", "", "", "");
		if (os == 2) {
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
			sleep(500);
			clickElementById("tv_evaluation");
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO, "点击先估个价");
		}else if (os == 1) {
			//TODO:
//			jsTapyDistance_X_Y_By(MobileBy.name("好"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击免费咨询取消按钮");
//			jsTapyDistance_X_Y_By(MobileBy.name("取消"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击免费咨询取消按钮");
//			backBTN();
			jsTapyDistance_X_Y_By(MobileBy.name("先估个价"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击先估个价按钮");
			reports_HomePageTest.log(LogStatus.INFO, "点击先估个价");
			
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "evaluate_c2b", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "", "carinfo_evaluate#type=B", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "", "c2b_home_quit#time=[value]", "", "");
		if (os == 2) {
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
			sleep(500);
			sliding("up");
			sleep(3000);
			clickElementById("fm_see_more_business_records");
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO, "点击更多成交记录");
		}else if (os == 1) {
			//TODO:
			backBTN();
		 	jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "2");
		 	scrollToElementClick("查看更多成交记录 >", 3, "TRUE");
		 	reports_HomePageTest.log(LogStatus.INFO, "点击更多成交记录");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "more_record_c2b", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "c2b_home_quit#time=[value]", "", "", "");
		if (os == 2) {
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
			reports_HomePageTest.log(LogStatus.INFO, "点击返回");
			sleep(500);
			sliding("up", 3);
			reports_HomePageTest.log(LogStatus.INFO, "向上滑动3屏");
			clickElementById("tv_order_sellcar_bottom");
			reports_HomePageTest.log(LogStatus.INFO, "点击底部预约卖车按钮");
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_HomePageTest.log(LogStatus.INFO, "点击返回");
			jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "4");
			reports_HomePageTest.log(LogStatus.INFO, "向上滑动3屏");
			jsTapyDistance_X_Y_By(MobileBy.name("预约卖车"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击先估个价按钮");
			reports_HomePageTest.log(LogStatus.INFO, "点击底部预约卖车按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "sellcar_c2b", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carinfo_sellcar#type=A", "", "", "");
		pullFileAndCompare("test_1103_MC");
	}
	/**
	 * @name 1104_GJ
	  * @catalogue 首页-估价，触发估价的所有埋点
	 * @Grade 高级
	 * @author liyifeng
	 */
	@Test
	public void test_1104_GJ(){
		reports_HomePageTest.startTest("test_1104_GJ");
		EventManager.fileName = "test_1104_GJ.txt";
		launchApp();
		if (os == 2) {
			clickElementById("sellcarll");
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO, "点击估价");
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.name("估价"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击首页估价按钮");
			reports_HomePageTest.log(LogStatus.INFO, "点击首页估价");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tab_tool_home#tab=估价/rank=4", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carinfo_evaluate#type=C", "", "", "");
		if (os == 2) {
			clickElementById("btManage");
			sleep(2000);
			reports_HomePageTest.log(LogStatus.INFO, "点击估价历史");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='估价历史']"), 1, 1);
			reports_HomePageTest.log(LogStatus.INFO, "点击估价历史");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "evaluate_history", "", "", "");
		if (os == 2) {
			clickElementById("imgBtBack");
			sleep(1000);
			reports_HomePageTest.log(LogStatus.INFO, "点击估价历史的返回按钮");
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_HomePageTest.log(LogStatus.INFO, "点击估价历史的返回按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carinfo_evaluate#type=E", "", "", "");
		if (os == 2) {
			clickElementById("imgBtBack");
			reports_HomePageTest.log(LogStatus.INFO, "点击估价的返回按钮");	
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_HomePageTest.log(LogStatus.INFO, "点击估价历史的返回按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "home_page", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "guess_like_expo#algorithm=bus", "", "");
		pullFileAndCompare("test_1104_GJ");
	}
	/**
	 * @name 1105_ZNXC
	 * @catalogue 首页-智能选车，触发智能选车的所有埋点
	 * @Grade 高级
	 * @author liyifeng
	 */
	@Test
	public void test_1105_ZNXC(){
		reports_HomePageTest.startTest("test_1105_ZNXC");
		EventManager.fileName = "test_1105_ZNXC.txt";
		launchApp();
		if (os == 2) {
			clickElementById("gujiall");
			sleep(1000);
			reports_HomePageTest.log(LogStatus.INFO, "点击智能选车按钮");
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.name("智能选车"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击智能选车按钮");
			reports_HomePageTest.log(LogStatus.INFO, "点击智能选车按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "tab_tool_home#tab=智能选车/rank=5", "", "", "");
		if (os == 2) {
			clickScreen(206, 1786, 1);//点击三箱轿车
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO, "点击三箱轿车");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='三厢轿车']"), 1, 2);
			reports_HomePageTest.log(LogStatus.INFO, "点击三箱轿车");
			
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carcat_intelligence#carcat=三厢轿车/operation=1", "", "", "");
	    if (os == 2) {
	    	clickElementById("confirm");
		    wait(1);
		    reports_HomePageTest.log(LogStatus.INFO, "点击想好了！确定");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='想好了! 确定']"), 1, 3);
			reports_HomePageTest.log(LogStatus.INFO, "点击想好了！确定");
		}
	    EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carcat_confirm_intelligence#carcat=三厢轿车", "", "", "");
	    if (os == 2) {
	    	clickScreen(203, 1260, 1);//点击品牌
		    sleep(500);
		    reports_HomePageTest.log(LogStatus.INFO, "点击选择品牌");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='品牌']"), 1, 2);
			 reports_HomePageTest.log(LogStatus.INFO, "点击选择品牌");
		}
	    EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "follow_intelligence#operation=1/option=品牌", "", "", "");
	    if (os == 2) {
	    	clickScreen(1125, 1260, 1);//点击动力
		    sleep(500);
		    reports_HomePageTest.log(LogStatus.INFO, "点击选择动力");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='动力']"), 1, 2);
			reports_HomePageTest.log(LogStatus.INFO, "点击选择动力");
		}
	    EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "follow_intelligence#operation=1/option=动力", "", "", "");
	    if (os == 2) {
	    	 clickElementById("confirm");
	 	    reports_HomePageTest.log(LogStatus.INFO, "点击确定");
	 	    wait(2);
	    }else if (os == 1) {
	    	clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='选好了(2/6)']"), 1, 2);
			reports_HomePageTest.log(LogStatus.INFO, "点击确定 选好了");
		}
	    EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "follow_confirm_intelligence#option=品牌,动力 ", "", "", "");
	    if (os== 2) {
	 	    androidDriver.swipe(135, 2223, 332, 2223, 4);//滑动到10万以上
	 	    reports_HomePageTest.log(LogStatus.INFO, "价格滑动到10万以上");
		}else if (os == 1) {
			//TODO:
			action.press((int)(25*screenRatioX),(int)(631*screenRatioY)).waitAction(Duration.ofMillis(200)).moveTo((int)(76*screenRatioX),(int)(-4*screenRatioY)).release().perform();
			 reports_HomePageTest.log(LogStatus.INFO, "价格滑动到10万以上");
		}
	    EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "price_intelligence#price=10万以上", "", "", "");
	    String seriesNum = null;
	    if (os == 2) {
	    	seriesNum = findElementById("confirm").getAttribute("text").substring(4, 6);
	 	    System.out.println(seriesNum);
	 	    clickElementById("confirm");
	 	    wait(2);
	 	    reports_HomePageTest.log(LogStatus.INFO, "点击为您推荐xx个车系");
		}else if (os == 1) {
			//TODO:
//			seriesNum =getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '万以上'`]");
			seriesNum =getListTextByXpath("//XCUIElementTypeStaticText[contains(@value,'万以上')]", 0, "value","");
//			seriesNum =getTextByChain("**/XCUIElementTypeStaticText[`value CONTAINS[cd] '万以上'`]");
	 	    System.out.println(seriesNum);
	 	   jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeButton[`name CONTAINS[cd] '个车系'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击 推荐车系按钮");
	 	   reports_HomePageTest.log(LogStatus.INFO, "点击为您推荐xx个车系");
		}
	    EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "series_result_intelligence#num="+seriesNum, "", "", "");
	    EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_intelligence_expo", "", "");
	    if (os == 2) {
		    clickElementById("llitem");
		    wait(2);
		    reports_HomePageTest.log(LogStatus.INFO, "点击列表页第一个车系");
		}else if (os == 1) {
			//TODO:
//			String index1=getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '推荐值' "), 0, "name");
			 jsTapyDistance_X_Y_By(MobileBy.name("推荐值"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击 点击列表页第一个车系");
			 reports_HomePageTest.log(LogStatus.INFO, "点击列表页第一个车系");
			
		}
	    EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "series_click_intelligence#seriesid=705/rank=1", "", "", "");
	    EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_intelligence_source_expo", "", "");
	    if (os == 2) {
		    clickElementById("rl_car_mileage");
		    sleep(500);
		    reports_HomePageTest.log(LogStatus.INFO, "点击里程");
		    clickElementByName("1万以内");
		    sleep(500);
		}else if (os == 1) {
			//TODO:
			 jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name=' 里程 ']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击里程");
			 reports_HomePageTest.log(LogStatus.INFO, "点击里程");
			 jsTapyDistance_X_Y_By(MobileBy.name("1万以内"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击1万以内");
			 reports_HomePageTest.log(LogStatus.INFO, "1万以内");
			 
		}
	    EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "mile_filter_intelligence_source#mile=0-1公里", "", "", "");
	    EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_intelligence_source_expo", "", "");
	    if (os == 2) {
	    	clickElementById("rl_car_age");
	 	    sleep(500);
	 	    reports_HomePageTest.log(LogStatus.INFO, "点击车龄");
	 	    clickElementByName("1-3年");
	 	    sleep(500);
		}else if (os == 1) {
			//TODO:
			 jsTapyDistance_X_Y_By(MobileBy.name(" 车龄 "), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击1万以内");
			 reports_HomePageTest.log(LogStatus.INFO, "点击车龄");
			 jsTapyDistance_X_Y_By(MobileBy.name("1-3年"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击1-3年");
			 reports_HomePageTest.log(LogStatus.INFO, "1-3年");
			 
		}
	    EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carage_fiter_intelligence_source#carage=1-3年", "", "", "");
	    EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_intelligence_source_expo", "", "");
	    String carid = clickFirstCarInList(2, "");
	    if (os == 2) {
			clickElementById("imgBtBack");
			sleep(1000);
			reports_HomePageTest.log(LogStatus.INFO, "点击返回按钮");
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_HomePageTest.log(LogStatus.INFO, "点击返回按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "detail_quit#carid="+carid+"/time=[value]", "", "", "");
		if (os == 2) {
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
			sleep(500);
			clickElementById("tv_car_types_modify");
			sleep(200);
			reports_HomePageTest.log(LogStatus.INFO, "点击三厢轿车后面的修改按钮");
		}else if (os == 1) {
			//TODO:
			backBTN();
			 jsTapyDistance_X_Y_By(MobileBy.name("修改"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击三厢轿车后面的修改按钮");
			reports_HomePageTest.log(LogStatus.INFO, "点击三厢轿车后面的修改按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "modify_intelligence#operation=carcat", "", "", "");
		if (os == 2) {
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		}else if (os == 1) {
			//TODO:
			backBTN();
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "return_intelligence", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "", "home_page", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "guess_like_expo#algorithm=bus", "", "");
		pullFileAndCompare("test_1105_ZNXC");
	}
	/**
	 * @name 1401_RMJG
	  * @catalogue 首页-热门价格，触发热门价格3万以下的所有埋点
	 * @Grade 高级
	 * @author liyifeng
	 */
	@Test
	public void test_1401_RMJG(){
		reports_HomePageTest.startTest("test_1401_RMJG");
		EventManager.fileName = "test_1401_RMJG.txt";
		launchApp();
		if (os == 2) {
			clickElementById("price01");
			wait(3);
			reports_HomePageTest.log(LogStatus.INFO, "点击3万以下");	
		}else if (os == 1) {
			//TODO:
			 jsTapyDistance_X_Y_By(MobileBy.name("3万以下"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击3万以下");
			 reports_HomePageTest.log(LogStatus.INFO, "点击3万以下");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "price_filter_home#price=3万以下/rank=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=2", "", "");
        clickFirstCarInList(2, "");
        pullFileAndCompare("test_1401_RMJG");
	}
	
	/**
	 * @name 1402_RMPP
	  * @catalogue 首页-热门品牌，触发热门品牌大众的所有埋点
	 * @Grade 高级
	 * @author liyifeng
	 */
	@Test
	public void test_1402_RMPP(){
		reports_HomePageTest.startTest("test_1402_RMPP");
		EventManager.fileName = "test_1402_RMPP.txt";
		launchApp();
		if (os == 2) {
			clickElementByName("大众");
			wait(3);
			sliding("up");
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.name("大众"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击大众");
			
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "brand_filter_home#brandid=84/rank=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_expo#", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=2", "", "");
		if (os == 2) {
			clickElementById("tvFirstPrice");
			reports_HomePageTest.log(LogStatus.INFO, "点击进入新车列表页");
			wait(5);
		}else if (os == 1) {
			//TODO:
			sliding("up");
			scrollToElementClick(getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '新车首付'`]"), 1, "TRUE");
			reports_HomePageTest.log(LogStatus.INFO, "点击进入新车列表页");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "newcar_click#seriesid=/rank=6/button=2/type=/modeid=/brandid=84", "", "", "");
		if (os == 2) {
			clickElementById("tvBack");
			wait(1);
			System.out.print("返回到列表页");
			reports_HomePageTest.log(LogStatus.INFO, "点击返回按钮");
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_HomePageTest.log(LogStatus.INFO, "点击返回按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carlist_page#type=0/from=3", "", "", "");
		String carid = null;
		String valid = null;
		if (os == 2) {
			clickElementById("tvAge");
			wait(3);
			reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
			carid = findElementById("tvVehicleDetailCityName").getAttribute("text").split(":")[1];//获取carid
			valid = getDataFromFile("valid");
			System.out.println(carid);
		}else if (os == 1) {
			//TODO:
//			jsTapyDistance_X_Y_By(MobileBy.name(getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '年'`]")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击车型车系第一个年");
			scrollToElementClick(getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '年'`]"), 1, "TRUE");
			carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
			valid = getValidData("valid");
			 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
			
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carlist_click#AI_num=0/result=1/icon=1/class=3/mold=3/rank=2/type=0/page=2/carid="+carid+"/word=/label=0/video=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid="+(Boolean.parseBoolean(valid)?"0" : "1"), "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_detail_expo", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid=" + carid +"/pos=file", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid=" + carid +"/pos=trend", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid=" + carid, "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid=" + carid, "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid=" + carid +"/type=0", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
		pullFileAndCompare("test_1402_RMPP");
	}
	
	/**
	 * @name 1403_QBCY
	  * @catalogue 首页-全部车源，触发全部车源的所有埋点
	 * @Grade 高级
	 * @author liyifeng
	 */
	@Test
	public void test_1403_QBCY(){
		reports_HomePageTest.startTest("test_1403_QBCY");
		EventManager.fileName = "test_1403_QBCY.txt";
		launchApp();
		if (os == 2) {
			clickElementById("viewBackground");
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO, "点击查看全部车源");
		}else if (os == 1) {
			//TODO:
			scrollToElementClick(getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '辆车源'`]"), "FALSE");
			reports_HomePageTest.log(LogStatus.INFO, "点击查看全部车源");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "all_car_home", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=2", "", "");
		clickFirstCarInList(2, "");
		pullFileAndCompare("test_1403_QBCY");
	}
	
	/**
	 * @name 1201_CNXH
	  * @catalogue 首页-猜你喜欢，触发猜你喜欢的所有埋点
	 * @Grade 高级
	 * @author liyifeng
	 */
	@Test
	public void test_1201_CNXH(){
		reports_HomePageTest.startTest("test_1201_CNXH");
		EventManager.fileName = "test_1201_CNXH.txt";
		launchApp();
		String carid = null;
		String valid = null;
		if (os == 2) {
			clickElementById("xuanfuflaw");
			reports_HomePageTest.log(LogStatus.INFO, "点击猜你喜欢悬浮按钮");
			wait(1);
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.name("猜你喜欢"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击猜你喜欢");
			reports_HomePageTest.log(LogStatus.INFO, "点击猜你喜欢悬浮按钮");
		}
		if (os == 2) {
			clickElementById("tvCarWholeName");
			reports_HomePageTest.log(LogStatus.INFO, "点击猜你喜欢列表中的第一辆车");
			wait(3);
			carid = findElementById("tvVehicleDetailCityName").getAttribute("text").split(":")[1];//获取carid
			valid = getDataFromFile("valid");
			System.out.println(carid);
		}else if (os == 1) {
			//TODO:
			String a=getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText'AND visible==1 AND wdName CONTAINS[cd] '款' "), 0, "name");
			jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击猜你喜欢");
			reports_HomePageTest.log(LogStatus.INFO, "点击猜你喜欢列表中的第一辆车");
			carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
			 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_click_guesslike_home#carid="+carid+"/icon=0/rank=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid="+(Boolean.parseBoolean(valid)? "0" : "1"), "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "vehicle_details/similar_expo", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=file", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=trend", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid="+carid, "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid="+carid, "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid="+carid+"/type=1", "", "");
		if (os == 2) {
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO, "从详情页返回到首页");
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_HomePageTest.log(LogStatus.INFO, "从详情页返回到首页");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "home_page", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "guess_like_expo#algorithm=bus", "", "", "");
		if (os == 2) {
			// 获取屏的宽度
			int width = androidDriver.manage().window().getSize().width;
			// 获取屏的高度
			int height = androidDriver.manage().window().getSize().height;
			System.out.println("上滑一屏，开始位置"+height*5/10+"结束位置"+height*4/10);
			androidDriver.swipe(width/2, height*5/10, width/2, height*4/10, 1000);
			sleep(500);
			clickElementById("ivDelete");
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO, "点击猜你喜欢列表第一辆车的x按钮");
		}else if (os == 1) {
			//TODO:
//			sliding("up");
			 jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='feedback delet']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击打叉删除");
			 reports_HomePageTest.log(LogStatus.INFO, "点击猜你喜欢列表第一辆车的x按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_close_click_guess#rank=1/carid="+carid, "", "", "");
		if (os == 2) {
			clickElementByName("品牌不喜欢");
			sleep(200);
			reports_HomePageTest.log(LogStatus.INFO, "点击品牌不喜欢");
			clickElementById("tvSubmit");
			sleep(200);
			reports_HomePageTest.log(LogStatus.INFO, "点击确定按钮");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.name("品牌不喜欢"), 1, 2);
			reports_HomePageTest.log(LogStatus.INFO, "点击品牌不喜欢");
			clickByCT(MobileBy.name("确定"), 1, 2);
			reports_HomePageTest.log(LogStatus.INFO, "点击确定按钮");
		 }
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_close_guess#rank=1/carid="+carid+"/operation=1", "", "", "");
		pullFileAndCompare("test_1201_CNXH");
	}
	
	/**
	 * @name 1301_YXRZJS
	  * @catalogue 首页-优信认证介绍页，触发优信认证介绍页的所有埋点
	 * @Grade 高级
	 * @author liyifeng
	 */
	@Test
	public void test_1301_YXRZJS(){
		reports_HomePageTest.startTest("test_1301_YXRZJS");
		EventManager.fileName = "test_1301_YXRZJS.txt";
		launchApp();
		if (os == 2) {
			// 获取屏的宽度
			int width = androidDriver.manage().window().getSize().width;
			// 获取屏的高度
			int height = androidDriver.manage().window().getSize().height;
			System.out.println("上滑一屏，开始位置"+height*5/10+"结束位置"+height*2/10);
			androidDriver.swipe(width/2, height*5/10, width/2, height*2/10, 1000);
			wait(3);
			reports_HomePageTest.log(LogStatus.INFO, "向上滑动一屏");
		}else if (os == 1) {
			//TODO:
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "one_percent_expo", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "uxin_intro_expo", "", "");
		if (os == 2) {
			clickElementById("type201");
			reports_HomePageTest.log(LogStatus.INFO, "点击优信认证介绍页入口");
			wait(3);
		}else if (os == 1){
			//TODO:
//			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeImage[contains(@type,'XCUIElementTypeImage')]"), 1), 1, 1,"");//优信认证介绍页面
			jsTapyDistance_X_Y_By(MobileBy.iOSClassChain("**/XCUIElementTypeTable[1]/XCUIElementTypeCell[3]/XCUIElementTypeImage[1]"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击优信认证介绍页面");	
			reports_HomePageTest.log(LogStatus.INFO, "点击优信认证介绍页入口");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "h5_intro_home#url=http://m.xin.com/service/?from=app/rank=1", "", "", "");
		if (os == 2) {
			clickElementById("ivRefresh");
			reports_HomePageTest.log(LogStatus.INFO, "点击刷新按钮");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.name("reload btn"), 1, 2);
			reports_HomePageTest.log(LogStatus.INFO, "点击刷新按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "guess_like_expo#algorithm=bus", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "home_page", "", "", "");
		pullFileAndCompare("test_1301_YXRZJS");
	}
	
	/**
	 * @name 1302_YCGJS
	  * @catalogue 首页-一成购介绍页，触发一成购介绍页的所有埋点
	 * @Grade 高级
	 * @author liyifeng
	 */
	@Test
	public void test_1302_YCGJS(){
		reports_HomePageTest.startTest("test_1302_YCGJS");
		EventManager.fileName = "test_1302_YCGJS.txt";
		launchApp();
		if (os == 2) {
			// 获取屏的宽度
			int width = androidDriver.manage().window().getSize().width;
			// 获取屏的高度
			int height = androidDriver.manage().window().getSize().height;
			System.out.println("上滑一屏，开始位置"+height*5/10+"结束位置"+height*2/10);
			androidDriver.swipe(width/2, height*5/10, width/2, height*2/10, 1000);
			reports_HomePageTest.log(LogStatus.INFO, "向上滑动一屏");
			wait(3);
		}else if (os == 1) {
			//TODO:
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "one_percent_expo", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "uxin_intro_expo", "", "");
		if (os == 2) {
			clickElementById("type201");
			reports_HomePageTest.log(LogStatus.INFO, "点击优信认证介绍页入口");
			wait(3);
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.iOSClassChain("**/XCUIElementTypeTable[1]/XCUIElementTypeCell[3]/XCUIElementTypeImage[2]"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击一成购介绍页面");
			reports_HomePageTest.log(LogStatus.INFO, "点击一成购介绍页入口");
			
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,
				"h5_intro_home#url=http://h5.xin.com/app/purchaseintro/?from=app&nb=b6bfdbc4a6f962c2c5b2f08fa89db6da&cityid=201&appver=9.1.0b/rank=2", "", "", "");
		if (os == 2) {
			clickElementById("ivRefresh");
			reports_HomePageTest.log(LogStatus.INFO, "点击刷新按钮");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.name("reload btn"), 1, 2);
			reports_HomePageTest.log(LogStatus.INFO, "点击刷新按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "guess_like_expo#algorithm=bus", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "home_page", "", "", "");
		pullFileAndCompare("test_1302_YCGJS");
	}
	/**
	 * @Name 1202_GDCY
	 * @catalogue 首页-底部查看更多车源，点击查看更多车源埋点
	 * @Grade 高级 
	 * @author liyifeng
	 */
	@Test
	public void test_1202_GDCY(){
		reports_HomePageTest.startTest("test_1202_GDCY");
		EventManager.fileName = "test_1202_GDCY.txt";
		wait(5);
		launchApp();
		if (os == 2) {
        	for (int i = 0; i <= 10; i++) {
    			sliding("","","","up","");
    			System.out.println(i);
    			sleep(3000);
    		}
    		reports_HomePageTest.log(LogStatus.INFO,"滑动10屏并停留3s");
		}else if (os == 1) {
			//TODO:
			if (sessionDetail("sdkVersion").contains("11")) {
	        	jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "6");
			}else {
				sliding("up",10);
			}
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","one_percent_expo","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","uxin_intro_expo","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"browse_depth","","","");
		if (os == 2) {
			clickElementById("lookupmore");
			reports_HomePageTest.log(LogStatus.INFO,"点击查看更多车源按钮");	
		}else if (os == 1) {
			//TODO:
			scrollToElementClick("查看更多车源", 2,"TRUE");
			reports_HomePageTest.log(LogStatus.INFO,"点击查看更多车源按钮");	
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"car_more","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carlist_page#type=1/from=1","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","carlist_expo#class=1/result=1/page=2","","");
		pullFileAndCompare("test_1202_GDCY");
	}
	
	/**
	 * @Name 1501_YCG1
	 * @catalogue 首页-一成购车辆，点击一成购第一辆车埋点
	 * @Grade 高级 
	 * @author liyifeng
	 */
	@Test
	public void test_1501_YCG1(){
		reports_HomePageTest.startTest("test_1501_YCG1");
		EventManager.fileName = "test_1501_YCG1.txt";
		wait(5);
		launchApp();
		if (os == 2) {
			sliding("up");
			sleep(3000);
			reports_HomePageTest.log(LogStatus.INFO,"滑动1屏并停留3s");
		}else if (os == 1) {
			//TODO:
			 if (sessionDetail("sdkVersion").contains("11")) {
		        	jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
				}else {
					sliding("up",10);
				}
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","one_percent_expo","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","uxin_intro_expo","","");
		if(os == 2){
			sleep(500);
			clickElementById("caricon01");
			reports_HomePageTest.log(LogStatus.INFO,"点击一成购推荐中的第一个");
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.name("猜你喜欢"), 260*screenRatioX, 99*screenRatioY, "-", "-", "二手车一成购1");//省油代步车
		  	reports_HomePageTest.log(LogStatus.INFO, "二手车一成购1 ", "埋点触发");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"one_percent_home#seriesid=[value]/rank=1","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carlist_page#type=1/from=1","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","carlist_expo#class=3/result=1/page=3","","");
		pullFileAndCompare("test_1501_YCG1");
	}
	
	/**
	 * @Name 1502_YCGBN
	 * @catalogue 首页-一成购banner，点击一成购banner埋点
	 * @Grade 高级 
	 */
	@Test
	public void test_1502_YCGBN(){
		reports_HomePageTest.startTest("test_1502_YCGBN");
		EventManager.fileName = "test_1502_YCGBN.txt";
		wait(5);
		launchApp();
        if (os == 2) {
    		sliding("","","","up","");
    		sleep(3000);
    		reports_HomePageTest.log(LogStatus.INFO,"滑动1屏并停留3s");
		}else if (os == 1) {
			//TODO:
			 if (sessionDetail("sdkVersion").contains("11")) {
		        	jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
				}else {
					sliding("up",10);
				}
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","one_percent_expo","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","uxin_intro_expo","","");
		if (os == 2) {
			sleep(200);
			clickElementById("yichenggouad");
			reports_HomePageTest.log(LogStatus.INFO,"点击二手车一成购banner");
		}else if (os == 1) {
			//TODO:
//			clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", getListTextByXpath("//XCUIElementTypeButton[contains(@name,'辆')]", 0, "name"), 1, 5);
//			clickWD("wdType == 'element1' AND wdName == 'element2'", "XCUIElementTypeButton", "买车", 1, 1);
//			clickWD("wdType == 'element1' AND wdName == 'element2'", "XCUIElementTypeButton", "首页", 1, 1);
			//规避iOS 10.3.3的
		    	jsTapyDistance_X_Y_By(MobileBy.name("猜你喜欢"), 260*screenRatioX, 99*screenRatioY, "-", "-", "二手车一成购1");//省油代步车
		  	  reports_HomePageTest.log(LogStatus.INFO, "二手车一成购1 ", "埋点触发");
//		  	  clickWD("wdType == 'element1' AND wdName == 'element2'", "XCUIElementTypeButton", "首页", 1, 1);
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"one_percent_more_home","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carlist_page#type=1/from=1","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","carlist_expo#class=3/result=1/page=3","","");
		pullFileAndCompare("test_1502_YCGBN");
	}
	/**
	 * @Name 1601_RMTJ
	 * @catalogue 首页-热门推荐，点击热门推荐中的第一个埋点
	 * @Grade 高级 
	 */
	@Test
	public void test_1601_RMTJ(){
		reports_HomePageTest.startTest("test_1601_RMTJ");
		EventManager.fileName = "test_1601_RMTJ.txt";
		wait(5);
		launchApp();
		if (os == 2) {
			// 获取屏的宽度
			int width = androidDriver.manage().window().getSize().width;
			// 获取屏的高度
			int height = androidDriver.manage().window().getSize().height;
			System.out.println("上滑一屏，开始位置"+height*6/10+"结束位置"+height*2/10);
			androidDriver.swipe(width/2, height*6/10, width/2, height*2/10, 1000);
			sleep(3000);
			reports_HomePageTest.log(LogStatus.INFO,"滑动1屏并停留3s");
		}else if (os == 1){
			//TODO:
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","one_percent_expo","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","uxin_intro_expo","","");
		if (os == 2) {
			sleep(200);
			clickElementById("hotrecommandad01");
			reports_HomePageTest.log(LogStatus.INFO,"点击热门推荐中的第一个");
		}else if (os == 1) {
			//TODO:
			 jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
//	        sliding("上滑");
//	        	  scrollToElementClick("热门推荐", 1);
//	        	clickWD("wdType == 'element1' AND wdName == 'element2'", "XCUIElementTypeButton", "买车", 1, 1);
//	        	clickWD("wdType == 'element1' AND wdName == 'element2'", "XCUIElementTypeButton", "首页", 1, 1);
	        	//规避iOS 10.3.3API
	        	  jsTapyDistance_X_Y_By(MobileBy.name("热门推荐"), 97*screenRatioX, 87*screenRatioY, "-", "+", "省油代步车");
	        	  reports_HomePageTest.log(LogStatus.INFO, "点击省油代步车 ", "然后点击首页返回当前界面");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"uxin_intro#icon=代步车首选/rank=1","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carlist_page#type=1/from=1","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","carlist_expo#class=3/result=1/page=2","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","reset_carlist_expo#page=2","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","series_card_expo#page=2","","");
		pullFileAndCompare("test_1601_RMTJ");
	}
	
	/**
	 * @Name 1700_WDDY
	 * @catalogue 首页-我的订阅，点击我的订阅埋点
	 * @Grade 高级 
	 */
	@Test
	public void test_1700_WDDY(){
		reports_HomePageTest.startTest("test_1700_WDDY");
		EventManager.fileName = "test_1700_WDDY.txt";
		wait(5);
		launchApp();
		if (os == 2) {
			int width = androidDriver.manage().window().getSize().width;
			int height = androidDriver.manage().window().getSize().height;
			androidDriver.swipe(width/2, height*5/10, width/2, height*2/10, 1000);
			sleep(3000);
			reports_HomePageTest.log(LogStatus.INFO,"滑动1屏并停留3s");
		}else if (os == 1) {
			//TODO:
			gotocate(1);
			sliding("up");
			if (CheckViewVisibilty(By.name("我的订阅"))) {
				System.out.println("当前界面存在订阅    ");
			}
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","one_percent_expo","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","uxin_intro_expo","","");
		if (os == 2) {
			sleep(200);
			clickElementById("sellcarcount");
			sleep(500);
			reports_HomePageTest.log(LogStatus.INFO,"点击我的订阅");
		}else if (os == 1) {
			//TODO:
			 jsTapyDistance_X_Y_By(MobileBy.name("我的订阅"), 0*screenRatioX, 0*screenRatioY, "-", "+", "点击首页 我的订阅");
			 reports_HomePageTest.log(LogStatus.INFO,"点击我的订阅");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"subscribe_home","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carlist_page#type=1/from=1","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","carlist_expo#class=3/result=1/page=2","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","reset_carlist_expo#page=2","","");
		pullFileAndCompare("test_1700_WDDY");
	}
	/**
	 * @Name 1800_CXKP
	 * @catalogue 首页-大众，点击车系卡片
	 * @Grade 高级 
	 */
	@Test
	public void test_1800_CXKP(){
		reports_HomePageTest.startTest("test_1800_CXKP");
		EventManager.fileName = "test_1800_CXKP.txt";
		wait(5);
		launchApp();
		if (os == 2) {
			clickElementByName("大众");
			wait(3);
			reports_HomePageTest.log(LogStatus.INFO, "点击大众");
		}else if (os == 1) {
			//TODO:
//			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeStaticText[@name='大众']"), 97*screenRatioX, 87*screenRatioY, "-", "+", "点击首页大众");
			scrollToElementClick("大众", "");
			reports_HomePageTest.log(LogStatus.INFO, "点击首页大众");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "brand_filter_home#brandid=84/rank=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=3/result=1/page=2", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_expo#", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "series_card_expo#page=2", "", "");
		if (os == 2) {
			slidingInElement(findElementById("recommend_series_recyclerview"), "left");
			clickElementByName("大众迈腾");
			wait(3);
			reports_HomePageTest.log(LogStatus.INFO, "点击大众迈腾");
		}else if (os == 1) {
			//TODO:
//			slidingInElement(findElementByClassName("XCUIElementTypeCollectionView"), "right");
//			scrollToElementClick("大众迈腾", "Scroll");
//			jsPressSwipe_para_by("left", "swipe", MobileBy.className("XCUIElementTypeCollectionView"), "1");
			action.press((int)(368*screenRatioX),(int)(263*screenRatioY)).waitAction(Duration.ofMillis(200)).moveTo((int)(70*screenRatioX),(int)(256*screenRatioY)).release().perform();
			new TouchAction((PerformsTouchActions) iosDriver).press((int)(368*screenRatioX),(int)(263*screenRatioY)).waitAction(Duration.ofMillis(200)).moveTo((int)(70*screenRatioX),(int)(256*screenRatioY)).release().perform();
//			iosDriver.findElementByIosNsPredicate("type=='XCUIElementTypeStaticText' AND name=='大众迈腾' AND visible == 1");
			scrollToElementClick("大众迈腾", "Scroll");
			reports_HomePageTest.log(LogStatus.INFO, "点击大众迈腾");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "series_card_click#page=2/seriesid=135/rank=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=1/from=1", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "","carlist_expo#class=3/result=1/page=2",  "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_expo#", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "suggest_carage_expo#page=2", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "reset_carlist_expo#page=2", "", "");
		clickFirstCarInList(2, "");
		wait(3);
		pullFileAndCompare("test_1800_CXKP");
	}
	/**
     * 需要退回到MainActivity页面之后再执行该方法
     * 拉取文件并进行比对
     * @param FolderName 测试用例文件夹 iOS Android 
     */
	 private void pullFileAndCompare(String test) {
		 if (os == 1) {//ios对比
			saveActualEvent();
            Compare compare = new Compare(this.reports_HomePageTest);
            compare.compare("./expected_iOS/" + test + ".txt", "./actual_iOS/statistic.json", test);
		}else if (os == 2) {//android对比
			callBriadcastAndPullFile();
			Compare compare = new Compare(this.reports_HomePageTest);
			compare.compare("./expected/"+test+".txt", "./actual/statistic.json",test);
		}
   }
}
