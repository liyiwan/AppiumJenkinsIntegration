
package com.uxin.usedcar.testCase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.uxin.usedcar.maidianlib.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSElement;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.remote.server.rest.RestishHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.server.Authentication.Failed;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.google.gson.Gson;
import com.relevantcodes.extentreports.LogStatus;
import com.uxin.usedcar.test.libs.BaseTest;
import com.uxin.usedcar.test.libs.CaseConfig;

public class SellCarTest extends BaseTest {
	 @BeforeClass
	  public static void first() throws Exception {
		 reports_SellCarTest.init("./report/SellCar/reportSellCar.html",true);
	  }

	@AfterClass 
	public static void last() throws Exception {
		reports_SellCarTest.endTest();
		System.out.println("tearDown");
	}
/**
 * 判断返回值类型
 * 
 */
	public static boolean Res(String id){
		try {
			String titleString = androidDriver.findElementById(id).getText();
			System.out.println(titleString);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("111");
			return false;
		}
		return true;
	}
	
	//点击卖车tab
	public void clickSellCarTab() {
		reports_SellCarTest.log(LogStatus.INFO, "点击卖车tab");
		if(os == 2) {
			clickElementById("rbSellCar");//android
		}else if(os ==1) {
			iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name='卖车']")).click();
		}
		wait(2);
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "sellcar_bottom", "", "", "");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "c2b_home_page", "", "", "");
	}
	
	/**
	 * @Name 3001_YYMC
	 * @catalogue 卖车-预约卖车，点击提交资料出发埋点
	 * @Grade 高级
	 */
	@Test
	public void test_3001_YYMC(){
		reports_SellCarTest.startTest("test_3001_YYMC");
		EventManager.fileName = "test_3001_YYMC.txt";
		launchApp();	
		if (os == 2) {//android
			reports_SellCarTest.log(LogStatus.INFO, "进入我的页面，检查是否登录账号");
			clickElementById("rbWo");
			wait(2);
//			if ((CheckViewVisibilty(By.id("tv_order_to_shop"))) == false) {	
			if (findElementById("tvTips").getText().equals("点击登录")) {  // 未登陆
			
			}else {//已登录
				sleep(1000);
		        clickElementById("ivSetting");
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "set", "", "", "");
				sleep(2000);
				androidDriver.findElement(By.xpath("//android.widget.TextView[@text='退出登录']")).click();
				sleep(500);
				clickElementById("bt_confirm_ok");
				sleep(500);
			}
			clickSellCarTab();	
			clickElementById("rbSellCar");
			reports_SellCarTest.log(LogStatus.INFO,"未登陆账号点击卖车按钮");//android
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"buttom/sellcar","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"/Page/c2b_home","","","");
			clickElementById("tv_order_sellcar");
			reports_SellCarTest.log(LogStatus.INFO,"点击预约卖车");//android
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"sellcar_c2b","","","");
			EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carinfo_sellcar#type=A","","","");
			sleep(500);
			clickElementById("car_name");
			reports_SellCarTest.log(LogStatus.INFO,"点击请选择品牌&车型");
			sleep(1000);
			clickElementByName("奥迪");
			sleep(200);
			clickElementByName("A6");
			sleep(200);
			clickElementByName("2006款 1.8 手动");
			reports_SellCarTest.log(LogStatus.INFO,"选择品牌(奥迪)-->A6-->-->2006款 1.8 手动");
			sleep(200);
			findElementById("et_vehicle_condition").sendKeys("2");
			reports_SellCarTest.log(LogStatus.INFO,"输入行驶里程2公里");
			clickElementById("tv_plateTime");
			reports_SellCarTest.log(LogStatus.INFO, "点击 请选择上牌时间");
			sleep(2000);
			int width = androidDriver.manage().window().getSize().width;
			int height = androidDriver.manage().window().getSize().height;
//			driver.swipe(width*5/17, height*3/8, width*5/17, height*3/8+150, 1000);
			androidDriver.swipe(width*1/4, height*1/2, width*1/4, height*1/2+150, 1000);
			sleep(2000);
			androidDriver.swipe(width*2/3, height*3/8, width*2/3, height*3/8+100, 1000);
			sleep(2000);
			clickElementByName("完成");
			reports_SellCarTest.log(LogStatus.INFO,"选择年2017年03月");
			sleep(200);
			clickElementById("btn_post");
			reports_SellCarTest.log(LogStatus.INFO,"点击提交资料");//android
		}else if (os == 1){
			reports_SellCarTest.log(LogStatus.INFO, "进入我的页面，检查是否登录账号");
			iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name='我的']")).click();
			wait(1);
			if (getListTextByLocator(MobileBy.xpath("//XCUIElementTypeStaticText[contains(@label,'****')]"), 0, "name") == null) {
				System.out.println("=====当前处于登陆状态======");
				//未登录，不用操作
			}else {//已登录，需要退出
				reports_SellCarTest.log(LogStatus.INFO, "进入设置页面，退出登录");
				clickByCT(By.name("设置"), 1, 1);
				clickByCT(By.name("退出当前账号"), 1, 1);
				clickByCT(By.name("退出登录"), 1, 1);
			}
			reports_SellCarTest.log(LogStatus.INFO, "未登录情况下，点击卖车tab");
			clickSellCarTab();
			reports_SellCarTest.log(LogStatus.INFO, "点击预约卖车");
			clickByCT(By.name("预约卖车"), 1, 1);
			reports_SellCarTest.log(LogStatus.INFO,"点击请选择品牌&车型");
			clickByCT(By.xpath("//XCUIElementTypeStaticText[@name='请选择品牌&车型']"), 1, 1);
			wait(1);
			clickByCT(By.name("奥迪"), 1, 1);
			sleep(500);
			clickByCT(By.name("A6"), 1, 1);
			sleep(500);
			clickByCT(By.name("2006款 1.8 手动"), 1, 1);
			sleep(500);
			reports_SellCarTest.log(LogStatus.INFO,"输入行驶里程2公里");
			inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 0, "value")), "2");
			reports_SellCarTest.log(LogStatus.INFO, "点击 请选择上牌时间");
			clickByCT(By.xpath("//XCUIElementTypeStaticText[@name='请选择上牌时间']"), 1, 1);
			sleep(500);
			clickByCT(By.name("确定"), 1, 1);
			sleep(500);
			//			clickByCT(By.name("确定"), 1, 1);
			reports_SellCarTest.log(LogStatus.INFO,"点击下一步");
			clickByCT(By.name("下一步"), 1, 1);
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"sellcar_submit#tel_num=/valid=1","","","");
		pullFileAndCompare("test_3001_YYMC");
	}
	
	/**
	 * @Name 3002_YCJL
	 * @catalogue 卖车-卖车记录，点击卖车记录埋点
	 * @Grade 高级
	 */
	@Test
	public void test_3002_YCJL(){
		reports_SellCarTest.startTest("test_3002_YCJL");
		EventManager.fileName = "test_3002_YCJL.txt";
		wait(5);
		launchApp();
		clickSellCarTab();
		if (os == 2) {
			sleep(200);
			clickElementById("btManage");
			reports_SellCarTest.log(LogStatus.INFO,"点击卖车记录");//android
			sleep(2000);
			SellCarTest SellCarTest= new SellCarTest();
			if (SellCarTest.Res("textView2")) {
				System.out.println("去登陆");
				androidDriver.findElementById("etPhoneNum").sendKeys("14725836911");
				sleep(500);
				androidDriver.findElementById("etYanZhengMa").sendKeys("666666");
				sleep(500);
				sleep(1000);
//				clickElementById("btnLogin");
				reports_SellCarTest.log(LogStatus.INFO,"点击登陆");//android
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "login","","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "used_login#tel_num="+CaseConfig.USERNAME_BEIYONG1,"","","");	
			}//android
		}else if (os == 1){
			clickByCT(By.name("卖车记录"), 1, 1);
			reports_SellCarTest.log(LogStatus.INFO,"点击卖车记录");
			if (CheckViewVisibilty(By.name("登录优信二手车"))) {//ios未登录
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 0, "value")), CaseConfig.USERNAME_BEIYONG1);
				clickElementByName("获取验证码");
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 1, "value")), "666666");
				wait(2);
				reports_SellCarTest.log(LogStatus.INFO,"点击登陆");//android
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "login","","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "used_login#tel_num="+CaseConfig.USERNAME_BEIYONG1,"","","");
				sleep(1000);
			}
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"sellcar_record","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"sellcar_record_page#from=2","","","");
		pullFileAndCompare("test_3002_YCJL");
	}
	
	/**
	 * @Name 3003_MFZX
	 * @catalogue 卖车-免费咨询，点击免费咨询埋点
	 * @Grade 高级 
	 */
	@Test
	public void test_3003_MFZX(){
		reports_SellCarTest.startTest("test_3003_MFZX");
		EventManager.fileName = "test_3003_MFZX.txt";
		wait(5);
		launchApp();
		clickSellCarTab();
		if (os == 2) {
			sleep(200);
			clickElementById("tv_free_consultation");
			reports_SellCarTest.log(LogStatus.INFO,"点击 免费咨询");//android
		}else if (os == 1){
			clickByCT(By.name("免费咨询"), 1, 1);
			reports_SellCarTest.log(LogStatus.INFO,"点击 免费咨询");
			wait(1);
			clickByCT(By.name("取消"), 1, 1);
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"tel_consulting_c2b#400_num=4006131628","","","");
		pullFileAndCompare("test_3003_MFZX");
	}
	
	/**
	 * @Name 3004_MCGJ
	 * @catalogue 卖车-先估个价，点击先估个价--估价历史
	 * @Grade 高级 
	 */
	@Test
	public void test_3004_MCGJ(){
		reports_SellCarTest.startTest("test_3004_MCGJ");
		EventManager.fileName = "test_3004_MCGJ.txt";
		wait(5);
		launchApp();
		clickSellCarTab();
		if (os == 2) {
			sleep(200);
			clickElementById("tv_evaluation");
			reports_SellCarTest.log(LogStatus.INFO,"点击 先估个价");//android
		}else if (os == 1){
			clickByCT(By.xpath("//XCUIElementTypeButton[@name='先估个价']"), 1, 1);
			reports_SellCarTest.log(LogStatus.INFO,"点击 先估个价");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"evaluate_c2b","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carinfo_evaluate#type=B","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT,"c2b_home_quit#time=[value]","","","");
		if (os == 2) {
			sleep(200);
			clickElementById("btManage");
			reports_SellCarTest.log(LogStatus.INFO,"点击估价历史按钮");//android
		}else if (os == 1){
			clickByCT(By.xpath("//XCUIElementTypeButton[@name='估价历史']"), 1, 1);
			reports_SellCarTest.log(LogStatus.INFO,"点击 估价历史");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"evaluate_history","","","");
		if (os == 2) {
			sleep(2000);
			if (SellCarTest.Res("textView2")) {//未登录
				System.out.println("去登陆");
				androidDriver.findElementById("etPhoneNum").sendKeys("14725836911");
				sleep(2000);
				androidDriver.findElementById("etYanZhengMa").sendKeys("666666");
				sleep(500);
//				clickElementById("btnLogin");
				reports_SellCarTest.log(LogStatus.INFO,"点击登陆");//android
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "login","","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "used_login#tel_num="+CaseConfig.USERNAME_BEIYONG2,"","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"evaluate_history","","","");
				sleep(1000);
				clickElementById("btEmptyMsgButton");
				reports_SellCarTest.log(LogStatus.INFO,"点击现在估价按钮");//android
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"evaluate_car#button=4","","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carinfo_evaluate#type=E","","","");
				pullFileAndCompare("test_3004_MCGJ");
			}else {//已登录
				sleep(1000);
				clickElementById("btEmptyMsgButton");
				reports_SellCarTest.log(LogStatus.INFO,"点击现在估价按钮");//android
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"evaluate_car#button=4","","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carinfo_evaluate#type=E","","","");
				pullFileAndCompare("test_3004_MCGJ");
			}//android
		}else if (os == 1){
			if (CheckViewVisibilty(By.name("登录优信二手车"))) {//ios未登录
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 0, "value")), CaseConfig.USERNAME_BEIYONG1);
				clickElementByName("获取验证码");
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 1, "value")), "666666");
				wait(2);
				reports_SellCarTest.log(LogStatus.INFO,"点击登陆");//android
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "login","","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "used_login#tel_num="+CaseConfig.USERNAME_BEIYONG2,"","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"evaluate_history","","","");
				sleep(1000);
				clickByCT(By.name("现在估价"), 1, 1);
				reports_SellCarTest.log(LogStatus.INFO,"点击现在估价按钮");//android
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"evaluate_car#button=4","","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carinfo_evaluate#type=E","","","");
				pullFileAndCompare("test_3004_MCGJ");
			}else {//ios已登录
				clickByCT(By.name("现在估价"), 1, 1);
				reports_SellCarTest.log(LogStatus.INFO,"点击现在估价按钮");//android
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"evaluate_car#button=4","","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carinfo_evaluate#type=E","","","");
				pullFileAndCompare("test_3004_MCGJ");
			}	
		}
	}
	
	/**
	 * @Name 3005_GJTJ
	 * @catalogue 卖车-估价提交资料，点击估价提交资料埋点
	 * @Grade 高级 
	 */
	@Test
	public void test_3005_GJTJ(){
		reports_SellCarTest.startTest("test_3005_GJTJ");
		EventManager.fileName = "test_3005_GJTJ.txt";
		wait(5);
		launchApp();
		clickSellCarTab();
		if (os == 2) {
			sleep(200);
			clickElementById("tv_evaluation");
			reports_SellCarTest.log(LogStatus.INFO,"点击 先估个价");//android
		}else if (os == 1){
			clickByCT(By.xpath("//XCUIElementTypeButton[@name='先估个价']"), 1, 1);
			reports_SellCarTest.log(LogStatus.INFO,"点击 先估个价");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"evaluate_c2b","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"carinfo_evaluate#type=B","","","");
		if (os == 2) {
			sleep(200);
			clickElementById("tv_car_name");
			reports_SellCarTest.log(LogStatus.INFO,"点击请选择品牌&车型");
			clickElementByName("奥迪");
			sleep(200);
			clickElementByName("A6");
			sleep(200);
			clickElementByName("2006款 1.8 手动");
			reports_SellCarTest.log(LogStatus.INFO,"选择品牌(奥迪)-->A6-->-->2006款 1.8 手动");
			sleep(200);
			findElementById("et_vehicle_condition").sendKeys("2");
			reports_SellCarTest.log(LogStatus.INFO,"输入行驶里程2公里");
			sleep(200);
			clickElementByName("确定");
			reports_SellCarTest.log(LogStatus.INFO,"选择上牌时间");
			sleep(200);
//			clickElementById("btn_post");
//			reports_SellCarTest.log(LogStatus.INFO,"点击开始估价");//android
		}else if (os == 1){
			clickByCT(By.xpath("//XCUIElementTypeStaticText[@name='请选择品牌&车型']"), 1, 1);
			reports_SellCarTest.log(LogStatus.INFO,"点击请选择品牌&车型");
			sleep(200);
			clickByCT(By.name("奥迪"), 1, 1);
			sleep(200);
			clickByCT(By.name("A6"), 1, 1);
			sleep(200);
			clickByCT(By.name("2006款 1.8 手动"), 1, 1);
			sleep(200);
			reports_SellCarTest.log(LogStatus.INFO,"选择品牌(奥迪)-->A6-->-->2006款 1.8 手动");
			inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 0, "value")), "2");
			reports_SellCarTest.log(LogStatus.INFO,"输入行驶里程2公里");
			sleep(200);
//			clickByCT(By.name("确定"), 1, 1);
			reports_SellCarTest.log(LogStatus.INFO,"请选择上牌时间");
			clickByCT(By.xpath("//XCUIElementTypeStaticText[@name='请选择上牌时间']"), 1, 1);
			sleep(500);
			clickByCT(By.name("确定"), 1, 1);
//			clickByCT(By.xpath("//XCUIElementTypeButton[@name='开始估价']"), 1, 1);
//			reports_SellCarTest.log(LogStatus.INFO,"点击开始估价");
		}
//		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"evaluate_inquiry#from=5/valid=1/tel_num=","","","");	
		pullFileAndCompare("test_3005_GJTJ");
	}
	/**
	 * @Name 3006_YYMC
	 * @catalogue 卖车-滑动底部预约卖车，滑动底部点击预约卖车埋点
	 * @Grade 高级 
	 */
	@Test
	public void test_3006_YYMC(){
		reports_SellCarTest.startTest("test_3006_YYMC");
		EventManager.fileName = "test_3006_YYMC.txt";
		wait(5);
		launchApp();
		clickSellCarTab();
		if (os == 2) {
			sleep(200);
			int width = androidDriver.manage().window().getSize().width;  
		    int height = androidDriver.manage().window().getSize().height;  
		    for (int i = 0; i < 5; i++) {  
		    	androidDriver.swipe(width * 1/ 2, height *8/9, width/ 2, height / 9+300, 1000);  
		        sleep(3000);  
		    }  
			clickElementById("tv_order_sellcar_bottom");
			reports_SellCarTest.log(LogStatus.INFO,"滑动到底部，点击底部的预约卖车");//android
		}else if (os == 1){
//			jsPressSwipe_para_by("up", "swipe", MobileBy.name("预约卖车"), "5");
			sliding("up", 5);
			wait(1);
//			iosDriver.findElement(By.xpath(("//XCUIElementTypeButton[@name='预约卖车'])"))).click();
			clickWD("wdType == 'element1' AND label == 'element2' AND visible == 1","XCUIElementTypeButton", "预约卖车", 1, 5);
			reports_SellCarTest.log(LogStatus.INFO,"滑动到底部，点击底部的预约卖车");
			wait(2);
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"sellcar_c2b","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carinfo_sellcar#type=A","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "c2b_home_quit#time=[value]","","","");
		pullFileAndCompare("test_3006_YYMC");
	}
	
	/**
	 * @Name 3007_CJJL
	 * @catalogue 卖车-滑动两个屏幕，点击查看更多成交记录埋点
	 * @Grade 高级 
	 * @author yanxin
	 */
	@Test
	public void test_3007_CJJL(){
		reports_SellCarTest.startTest("test_3007_CJJL");
		EventManager.fileName = "test_3007_CJJL.txt";
		wait(5);
		launchApp();
		clickSellCarTab();
		if (os == 2) {
			sleep(200);
			int width = androidDriver.manage().window().getSize().width;  
		    int height = androidDriver.manage().window().getSize().height;  
		    for (int i = 0; i < 2; i++) {  
		    	androidDriver.swipe(width * 1/ 2, height *8/9, width/ 2, height / 9+300, 1000);  
		        sleep(3000);  
		    }  
			clickElementByXpath("//android.widget.TextView[contains(@text,'查看更多成交记录')]");
			reports_SellCarTest.log(LogStatus.INFO,"滑动到查看更多成交记录，并且点击查看更多成交记录");//android
		}else if (os == 1){
			scrollToElementClick("查看更多成交记录 >", 2, "TRUE");
			reports_SellCarTest.log(LogStatus.INFO,"滑动到查看更多成交记录，并且点击查看更多成交记录");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"more_record_c2b","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT,"c2b_home_quit#time=[value]","","","");
		sleep(200);
		pullFileAndCompare("test_3007_CJJL");
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
            Compare compare = new Compare(this.reports_SellCarTest);
            compare.compare("./expected_iOS/" + test + ".txt", "./actual_iOS/statistic.json", test);
		}else if (os == 2) {//android对比
			callBriadcastAndPullFile();
			Compare compare = new Compare(this.reports_SellCarTest);
			compare.compare("./expected/"+test+".txt", "./actual/statistic.json",test);
		}
	       
	    }
}

