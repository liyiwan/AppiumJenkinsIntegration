package com.uxin.usedcar.testCase;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSElement;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ById;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.relevantcodes.extentreports.LogStatus;
import com.uxin.usedcar.test.libs.BaseTest;
import com.uxin.usedcar.test.libs.CaseConfig;
import com.uxin.usedcar.test.libs.StringUtils;
import com.uxin.usedcar.maidianlib.AppStatisticBean;
import com.uxin.usedcar.maidianlib.Compare;
import com.uxin.usedcar.maidianlib.EventEntity;
import com.uxin.usedcar.maidianlib.EventManager;
import com.uxin.usedcar.maidianlib.TEventEntity;
import com.uxin.usedcar.maidianlib.TestStatisticBean;




public class ValuableBookTest extends BaseTest {
	 @BeforeClass
	  public static void first() throws Exception {
		 reports_ValuableBookTest.init("./report/ValuableBook/reportValuableBook.html",true);
		
		 
	  }

	@AfterClass 
	public static void last() throws Exception {
		reports_ValuableBookTest.endTest();
		System.out.println("tearDown");
	}

    /**
     * 
     * @Name test_401_QGZG
	 * @catalogue 点击热门搜索第一个（全国直购）,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_401_QGZG() {
		reports_ValuableBookTest.startTest("test_401_QGZG");
		EventManager.fileName = "test_401_QGZG.txt";
		launchApp();
		if (os == 2) {
			clickElementById("rbFaXian");
			wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
			sleep(200);
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");//更新9.3case埋点
		//Page前不带/
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
        if (os == 2) {
    		clickElementById("ivSearchIcon");
    		reports_ValuableBookTest.log(LogStatus.INFO,"点击搜索框");
    		sleep(200);
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='宝典']"), 1, 3);
			clickByLocator(By.className("XCUIElementTypeNavigationBar"), 1, 1);
	        reports_ValuableBookTest.log(LogStatus.INFO, "光标获取焦点");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/search","","","");
		String hotSearch = null;
		if (os == 2) {
			hotSearch = findElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]"
					+ "/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]"
					+ "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]"
					+ "/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]"
					+ "/android.widget.TextView[1]").getAttribute("text");
			clickElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]"
					+ "/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]"
					+ "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]"
					+ "/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]"
					+ "/android.widget.TextView[1]");
			reports_ValuableBookTest.log(LogStatus.INFO,"点击热门搜索第一个");
			sleep(200);
		}else if (os == 1) {
			//TODO:
			hotSearch = findElementByXpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]"
					+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
					+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
					+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeCollectionView[1]"
					+ "/XCUIElementTypeCell[1]/XCUIElementTypeButton[1]").getAttribute("name");
			clickByCT(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]"
					+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
					+ "/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]"
					+ "/XCUIElementTypeOther[1]/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[1]"
					+ "/XCUIElementTypeButton[1]"), 1, 8);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击热门搜索第一个");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/search_select/rank/"+hotSearch,"","","");
		if (os == 2) {
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
			sleep(200);
			clickElementById("btCancel");
			reports_ValuableBookTest.log(LogStatus.INFO,"点击取消按钮");
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='icon chexiangqing titlebar bac']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='取消']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击取消按钮");
		}
		pullFileAndCompare("test_401_QGZG");
	}

	
	 /**
     * 
     * @Name test_402_searchBar
	 * @catalogue 点击搜索框,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_402_searchBar() {
		reports_ValuableBookTest.startTest("test_402_searchBar");
		EventManager.fileName = "test_402_searchBar.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		//Page前不带/
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
        if (os == 2) {
    		clickByCT(MobileBy.id("com.uxin.usedcar:id/ivSearchIcon"), 1, 1);
    		reports_ValuableBookTest.log(LogStatus.INFO,"点击搜索框");
    		sleep(200);
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='宝典']"), 1, 1);
			clickByLocator(By.className("XCUIElementTypeNavigationBar"), 1, 1);
	        reports_ValuableBookTest.log(LogStatus.INFO, "点击搜索框 光标获取焦点");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/search","","","");
		pullFileAndCompare("test_402_searchBar");
	}
	
	 /**
     * 
     * @Name test_403_wendaFocus
	 * @catalogue 点击问答  点击最新第一item 点击 关注按钮 ,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_403_wendaFocus() {
		reports_ValuableBookTest.startTest("test_403_wendaFocus");
		EventManager.fileName = "test_403_wendaFocus.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
	        wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
        if (os == 2) {
    		clickByCT(MobileBy.xpath("//android.widget.TextView[@text='问答']"), 1, 1);
    		reports_ValuableBookTest.log(LogStatus.INFO,"点击问答");
    		wait(1);
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='问答']"), 1, 3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa/more","","","");
		if (os == 2) {
			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='最新']"), 1, 1);
			wait(1);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击最新");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='最新']"), 1, 1);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击最新");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa/new","","","");
		if (os == 2) {
			clickByCT(MobileBy.id("com.uxin.usedcar:id/tvBibleQuestion"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"选择点击第一item");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"选择点击第一item");
		}
		if (os == 2) {
			clickByCT(MobileBy.id("com.uxin.usedcar:id/iv_foucs"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击 关注按钮");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='icon收藏']"), 1, 2);
//			int inputValue=iosDriver.findElements(By.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]")).size();
//			System.out.println(inputValue);
			List<WebElement> inputValue=getListByLocatorSize(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"));
			System.out.println(inputValue.size());
			System.out.println("规避 某些马甲包没有输入框=====");
			if (inputValue.size()<=4) {
				System.out.println("马甲包没有输入框=====  ");
				clickByLocator(inputValue.get(2), 1, 3,"");////由于浮层问题 拿到控件后转成点坐标
				reports_ValuableBookTest.log(LogStatus.INFO, "点击1次底部的收藏按钮 ");
				if (CheckViewVisibilty(By.name("登录优信二手车"))) {
					 System.out.println("=====当前未登陆状态======");
			            inputByXpath("//XCUIElementTypeTextField[@value='请输入手机号']", CaseConfig.USERNAME_BEIYONG2);
			            clickElementByName("获取验证码");
			            inputByXpath("//XCUIElementTypeTextField[@value='请输入验证码']", "666666");
			            System.out.println("登录成功");
				}
				clickByLocator(inputValue.get(2), 1, 3,"");
				reports_ValuableBookTest.log(LogStatus.INFO, "点击第2次底部的收藏按钮 ");
			}else if (inputValue.size()>=5) {
				System.out.println("===== 马甲包有输入框=====  ");
				clickByLocator(inputValue.get(3), 1, 3,"");
				reports_ValuableBookTest.log(LogStatus.INFO, "点击1次底部的收藏按钮 ");
				if (CheckViewVisibilty(By.name("登录优信二手车"))) {
					 System.out.println("=====当前未登陆状态======");
			            inputByXpath("//XCUIElementTypeTextField[@value='请输入手机号']", CaseConfig.USERNAME_BEIYONG2);
			            clickElementByName("获取验证码");
			            inputByXpath("//XCUIElementTypeTextField[@value='请输入验证码']", "666666");
			            System.out.println("登录成功");
				}
				clickByLocator(inputValue.get(3), 1, 3,"");
				reports_ValuableBookTest.log(LogStatus.INFO, "点击第2次底部的收藏按钮 ");
			}
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa_detail/follow","","","");
		pullFileAndCompare("test_403_wendaFocus");
	}
	
	
	 /**
     * 
     * @Name test_404_wendaFocus
	 * @catalogue 点击问答  点击最新第一item 点击 关注按钮 点击顶部搜索按钮  输入奥迪后跳转搜索结果页面后点击任意一个item ,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_404_wendaFocus() {
		reports_ValuableBookTest.startTest("test_404_wendaFocus");
		EventManager.fileName = "test_404_wendaFocus.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
	    if (os == 2) {
	    	clickByCT(MobileBy.xpath("//android.widget.TextView[@text='问答']"), 1, 1);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击问答");
			wait(1);
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='问答']"), 1, 3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击问答");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa/more","","","");
		if (os == 2) {
			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='最新']"), 1, 1);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击最新");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='最新']"), 1, 3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击最新");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa/new","","","");
		if (os == 2) {
			clickByCT(MobileBy.id("com.uxin.usedcar:id/tvBibleQuestion"), 1, 2);//问题列表1
			reports_ValuableBookTest.log(LogStatus.INFO,"选择点击第一item");
			clickByCT(MobileBy.id("com.uxin.usedcar:id/tvBack"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
		}else if (os == 1) {
			//TODO:
//			clickByCT(((By) getListByLocator(MobileBy.xpath("//XCUIElementTypeCell[contains(@type,'XCUIElementTypeCell')]"), 0)).xpath("//XCUIElementTypeStaticText[@type='XCUIElementTypeStaticText']"), 1, 2);
			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeCell[contains(@type,'XCUIElementTypeCell')]"), 0), 1, 3,"");
			reports_ValuableBookTest.log(LogStatus.INFO,"选择点击第一item");
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='返回']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
		}
//		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa_detail/follow","","","");
		if (os == 2) {
			clickByCT(MobileBy.id("com.uxin.usedcar:id/ivSearch"), 1, 2);//右上角🔍
			reports_ValuableBookTest.log(LogStatus.INFO,"点击顶部搜索按钮");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='icon shouye search']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击顶部搜索按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa/search","","","");
//		clickByCT(MobileBy.id("com.uxin.usedcar:id/ivSearch"), 1, 2);
//		reports_ValuableBookTest.log(LogStatus.INFO,"点击顶部搜索按钮");
		if (os == 2) {
			input("id", "com.uxin.usedcar:id/etSearchText", "", "奥迪", "");
			androidDriver.pressKeyCode(AndroidKeyCode.ENTER);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击顶部搜索按钮 输入（奥迪）点击搜索");
		}else if (os == 1) {
			//TODO:
			inputByLocator(MobileBy.xpath("//XCUIElementTypeTextField[@value='搜一下您关心的问题']"), "奥迪");
//			clickByCT(MobileBy.xpath(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 0, "value")), 1, 2);
		    clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='Search']"), 1, 5);
		    reports_ValuableBookTest.log(LogStatus.INFO, "触发键盘回车键");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa/search_input/word/奥迪","","","");
		pullFileAndCompare("test_404_wendaFocus");
}
	
	
	 /**
     * 
     * @Name test_405_BZB
	 * @catalogue 点击  顶部问答 ，点击  顶部资讯，  点击  顶部百科,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_405_BZB() {
		reports_ValuableBookTest.startTest("test_405_BZB");
		EventManager.fileName = "test_405_BZB.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
			
			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='问答']"), 1, 1);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击顶部问答");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='问答']"), 1, 3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击问答");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa","","","");
		if (os == 2) {
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='资讯']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击  顶部 资讯");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/information","","","");
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='资讯']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击  顶部 资讯");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/information","","","");
		}
		if (os == 2) {
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='百科']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击  顶部 百科");
		}else if (os ==1) {
			//TODO:
			backBTN();
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='百科']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击  顶部 百科");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/baike","","","");
		backBTN();
		reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
		pullFileAndCompare("test_405_BZB");
	}
	
	
	/**
     * 
     * @Name test_406_WDSubmit
	 * @catalogue 在 车辆问答列表页 点击任意一个列表item 我要追问 文本框输入(自动化自动化自动化埋点测试)点击提交按钮,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_406_WDSubmit() {
		reports_ValuableBookTest.startTest("test_406_WDSubmit");
		EventManager.fileName = "test_406_WDSubmit.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
			
			sliding("up");
			reports_ValuableBookTest.log(LogStatus.INFO, "上滑1屏");
			String answer1=getListTextByXpath("//android.widget.TextView[contains(@text,'人看过')]", 1, "name");
			jsTapyDistance_X_Y_By(MobileBy.name(answer1), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击XXX人看过 切换车辆问答列表页 点击任意一个列表");
			reports_ValuableBookTest.log(LogStatus.INFO, "在 车辆问答列表页 点击任意一个列表item");
//			clickWD("//android.widget.element1[@element2='element3']", "Button","text", "我要追问…", 1, 1);
			clickElementByXpath("//android.widget.Button[@text='我要追问...']");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击 我要追问");
		}else if (os == 1) {
			//TODO:
			jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
			String answer1=getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'人看过')]", 0, "name","");
			clickWD("wdType == 'element1' AND name == 'element2'", "XCUIElementTypeStaticText", answer1, 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO, "在 车辆问答列表页 点击任意一个列表item");
//			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 2), 1, 3,"");
//			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 3), 1, 3,"");
//			reports_ValuableBookTest.log(LogStatus.INFO, "点击 我要追问");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa_detail/continueasking","","","");
		if (os == 2) {
			String a="自动化自动化自动化埋点测试"+(8000 + (int) (Math.random() * ((9000 - 8000) + 1)));
			input("xpath", "//android.widget.EditText[@text='我要追问']", "", a, "");
			reports_ValuableBookTest.log(LogStatus.INFO,"我要追问 文本框输入(自动化自动化自动化埋点测试)");
			clickWD("//android.widget.element1[@element2='element3']", "Button","text", "提交", 1, 1);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击提交按钮");
		}else if (os == 1) {
			//TODO:
			List<WebElement> inputValue=getListByLocatorSize(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"));
			System.out.println(inputValue.size());
			System.out.println("规避 某些马甲包没有输入框=====");
			if (inputValue.size()<=4) {
				System.out.println("马甲包没有输入框=====  ");
				reports_ValuableBookTest.log(LogStatus.INFO, "马甲包没有输入框===== 请切换主站包 ");
			}else if (inputValue.size()>=5) {
				System.out.println("===== 马甲包有输入框=====  ");
				System.out.println("带文本输入框=====  开始执行输入");
				String a="自动化自动化自动化埋点测试"+(8000 + (int) (Math.random() * ((9000 - 8000) + 1)));
				clickByLocator(inputValue.get(2), 1, 3,"");
				reports_ValuableBookTest.log(LogStatus.INFO, "点击 我要追问");
//				clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 2), 1, 3,"");
				reports_ValuableBookTest.log(LogStatus.INFO, "触发输入框");
				inputByLocator(By.className("XCUIElementTypeTextView"), a);
				reports_ValuableBookTest.log(LogStatus.INFO,"我要追问 文本框输入(自动化自动化自动化埋点测试)");
			}
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/continueasking/submit","","",""); 
		pullFileAndCompare("test_406_WDSubmit");
	}
	
	
	
	/**
     * 
     * @Name test_407_WDSubmit
	 * @catalogue 在 车辆问答列表页 点击任意一个列表item 我要追问 文本框输入(自动化自动化自动化埋点测试)点击提交按钮 点击2次底部的收藏按钮  点击分享按钮,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_407_WDSubmit() {
		reports_ValuableBookTest.startTest("test_407_WDSubmit");
		EventManager.fileName = "test_407_WDSubmit.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
			
			sliding("up");
			String answer1=getListTextByXpath("//android.widget.TextView[contains(@text,'人看过')]", 2, "name");
			clickWD("//android.widget.element1[@element2='element3']", "TextView","text", answer1, 1, 1);
			reports_ValuableBookTest.log(LogStatus.INFO, "在 车辆问答列表页 点击任意一个列表item");
			clickByLocator(MobileBy.id("com.uxin.usedcar:id/iv_foucs"), 2, 2);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击2次底部的收藏按钮 ");
		}else if (os == 1) {
			//TODO:
//			sliding("up");
			jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
			String answer1=getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'人看过')]", 0, "name","");
//			clickWD("wdType == 'element1' AND name == 'element2'", "XCUIElementTypeStaticText", answer1, 1, 2);
			jsTapyDistance_X_Y_By(MobileBy.name(answer1), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击XXX人看过 切换车辆问答列表页 点击任意一个列表");
			reports_ValuableBookTest.log(LogStatus.INFO, "在 车辆问答列表页 点击任意一个列表item");
			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 3), 1, 3,"");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击1次底部的收藏按钮 ");
			if (CheckViewVisibilty(By.name("登录优信二手车"))) {
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 0, "value")), CaseConfig.USERNAME_BEIYONG2);
				clickElementByName("获取验证码");
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 1, "value")), "666666");
			
				}
			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 3), 1, 3,"");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击2次底部的收藏按钮 ");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa_detail/follow","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa_detail/follow","","","");
		if (os == 2) {
			clickByLocator(MobileBy.id("com.uxin.usedcar:id/iv_share"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击分享按钮");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='icon分享']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击分享按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa_detail/share","","","");
		checkShare();
		if (os == 2) {
			clickByLocator(MobileBy.xpath("//android.widget.TextView[@text='取消']"), 1, 1);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击取消按钮");
			clickElementByXpath("//android.widget.Button[@text='我要追问...']");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击 我要追问");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='取消']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击分享按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/qa_detail/continueasking","","","");////点击的时候已经产生埋点
		if (os == 2) {
			String a="自动化自动化自动化埋点测试";
			input("xpath", "//android.widget.EditText[@text='我要追问']", "", a, "");
			reports_ValuableBookTest.log(LogStatus.INFO,"我要追问 文本框输入(自动化自动化自动化埋点测试)");
		}else if (os == 1) {
			//TODO:
			int inputValue=iosDriver.findElements(By.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]")).size();
			System.out.println(inputValue);
			System.out.println("规避 某些马甲包没有输入框=====");
			if (inputValue>=5) {
				System.out.println("带文本输入框=====  开始执行输入");
				String a="自动化自动化自动化埋点测试";
				clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 2), 1, 3,"");
				reports_ValuableBookTest.log(LogStatus.INFO, "触发输入框");
				inputByLocator(By.className("XCUIElementTypeTextView"), a);
				reports_ValuableBookTest.log(LogStatus.INFO,"我要追问 文本框输入(自动化自动化自动化埋点测试)");
			}
			
		} 
		pullFileAndCompare("test_407_WDSubmit");
	}
	
	/**
     * 
     * @Name test_408_WDCarList
	 * @catalogue 滑动5次每次等待3s  在车辆问答列表页<查看列表页曝光>,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_408_WDCarList() {
		reports_ValuableBookTest.startTest("test_408_WDCarList");
		EventManager.fileName = "test_408_WDCarList.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		
		if (os == 2) {
			sliding("up", 10);//20 为2 3 4  如果153value 为3value 10为23value
			
		}else if (os == 1) {
			//TODO:
		 	jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "10");
		}
		
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "qa_baodian_pos#pos=2", "","","");	 
		pullFileAndCompare("test_408_WDCarList");
	}
	
	
	/**
     * 
     * @Name test_409_searchSuggest
	 * @catalogue 点击顶部的搜索框  输入奥迪 等待suggest词展现 点击取消按钮 点击顶部的搜索框 输入（奥迪）点搜索,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_409_searchSuggest() {
		reports_ValuableBookTest.startTest("test_409_searchSuggest");
		EventManager.fileName = "test_409_searchSuggest.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
			
			clickByCT(MobileBy.id("com.uxin.usedcar:id/ivSearchIcon"), 1, 2);//右上角🔍
			reports_ValuableBookTest.log(LogStatus.INFO,"点击顶部搜索按钮");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='宝典']"), 1, 1);
    		reports_ValuableBookTest.log(LogStatus.INFO,"点击搜索框");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/search","","","");
		if (os == 2) {
			input("id", "com.uxin.usedcar:id/etSearchText", "", "奥迪", "");
			wait(3);
			for (int i = 1; i < 5; i++) {
				System.out.println(androidDriver.findElement(MobileBy.className("android.widget.ListView")).findElement(MobileBy.xpath("//android.widget.RelativeLayout["+i+"]/android.widget.TextView[1]")).getAttribute("name"));
			}
			clickByLocator(MobileBy.xpath("//android.widget.TextView[@text='取消']"), 1, 1);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击取消按钮");
			
			clickByCT(MobileBy.id("com.uxin.usedcar:id/ivSearchIcon"), 1, 2);//右上角🔍
			reports_ValuableBookTest.log(LogStatus.INFO,"点击顶部搜索按钮");
		}else if (os == 1) {
			//TODO:
//			inputByLocator(By.className("XCUIElementTypeTextView"), "奥迪");
			inputByLocator(MobileBy.xpath("//XCUIElementTypeTextField[@value='搜一下您关心的问题']"), "奥迪");
			reports_ValuableBookTest.log(LogStatus.INFO,"我要追问 文本框输入(奥迪)");
			
			 clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='Search']"), 1, 5);
		    reports_ValuableBookTest.log(LogStatus.INFO, "触发键盘回车键");
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='icon shouye search']"), 1, 2);
//			reports_ValuableBookTest.log(LogStatus.INFO,"点击顶部搜索按钮");
			
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/search","","","");
		if (os == 2) {
			input("id", "com.uxin.usedcar:id/etSearchText", "", "奥迪", "");
			androidDriver.pressKeyCode(AndroidKeyCode.ENTER);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击顶部搜索按钮 输入（奥迪）点击搜索");
		}else if (os == 1) {
			//TODO:
//			inputByLocator(MobileBy.xpath("//XCUIElementTypeTextField[@value='搜一下您关心的问题']"), "奥迪");
//		    clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='Search']"), 1, 5);
//		    reports_ValuableBookTest.log(LogStatus.INFO, "触发键盘回车键");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/search_input/word/奥迪","","","");
//		sliding("up", 10);//20 为2 3 4  如果153value 为3value 10为23value
//		
//		writerForTxt("test_409_BDWD.txt", "ev：qa_baodian_pos#pos=[2]",1);
//		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "qa_baodian_pos#pos=[2]", "","","");
		pullFileAndCompare("test_409_searchSuggest");
	}
	
	/**
     * 
     * @Name test_410_searchSuggest
	 * @catalogue 点击顶部的搜索框  输入奥迪 等待suggest词展现 点击第一个suggest词,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_410_searchSuggest() {
		reports_ValuableBookTest.startTest("test_410_searchSuggest");
		EventManager.fileName = "test_410_searchSuggest.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
			
			clickByCT(MobileBy.id("com.uxin.usedcar:id/ivSearchIcon"), 1, 2);//右上角🔍
			reports_ValuableBookTest.log(LogStatus.INFO,"点击顶部搜索按钮");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='宝典']"), 1, 1);
    		    reports_ValuableBookTest.log(LogStatus.INFO,"点击搜索框");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/search","","","");
		if (os == 2) {
			input("id", "com.uxin.usedcar:id/etSearchText", "", "奥迪", "");
			wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"等待suggest词展现");
			for (int i = 1; i < 5; i++) {
				System.out.println(androidDriver.findElement(MobileBy.className("android.widget.ListView")).findElement(MobileBy.xpath("//android.widget.RelativeLayout["+i+"]/android.widget.TextView[1]")).getAttribute("name"));
			}
			String index1=androidDriver.findElement(MobileBy.className("android.widget.ListView")).findElement(MobileBy.xpath("//android.widget.RelativeLayout[1]/android.widget.TextView[1]")).getAttribute("name");
			jsTapyDistance_X_Y_Matches("text",index1, 0, 0, "+", "+", "第一个suggest词");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击第一个suggest词");
		}else if (os == 1) {
			//TODO:
			inputByLocator(MobileBy.xpath("//XCUIElementTypeTextField[@value='搜一下您关心的问题']"), "奥迪");
			reports_ValuableBookTest.log(LogStatus.INFO,"我要追问 文本框输入(奥迪)");
			clickByCT(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]"), 1, 3);
//			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeCell[contains(@type,'XCUIElementTypeCell')]"), 0), 1, 3,"");
			reports_ValuableBookTest.log(LogStatus.INFO,"点击第一个suggest词");
			
//			 clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='Search']"), 1, 5);
//		    reports_ValuableBookTest.log(LogStatus.INFO, "触发键盘回车键");
		}
//		clickByLocator(By.name(driver.findElement(MobileBy.className("android.widget.ListView")).findElement(MobileBy.xpath("//android.widget.RelativeLayout[1]/android.widget.TextView[1]")).getAttribute("name")), 1, 1);
//		clickByLocator(MobileBy.xpath("//android.widget.TextView[@text='取消']"), 1, 1);
		pullFileAndCompare("test_410_searchSuggest");
	}
	/**
     * 
     * @Name test_411_ZXShare
	 * @catalogue 点击资讯按钮 点击第一个item 点击2次收藏按钮  点击分享按钮 遍历分享,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_411_ZXShare() {
		reports_ValuableBookTest.startTest("test_411_ZXShare");
		EventManager.fileName = "test_411_ZXShare.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='资讯']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击资讯");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='资讯']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击资讯");
		}
		
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian/information", "","","");
		if (os == 2) {
			String index1=androidDriver.findElement(MobileBy.className("android.widget.ListView")).findElement(MobileBy.xpath("//android.widget.RelativeLayout[1]/android.widget.TextView[1]")).getAttribute("name");
			jsTapyDistance_X_Y_Matches("text",index1, 0, 0, "+", "+", "资讯页面 第一个item");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击资讯页面 第一个item");
			clickByCT(MobileBy.id("com.uxin.usedcar:id/ivRefresh"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击刷新 ");
			clickByLocator(MobileBy.id("com.uxin.usedcar:id/iv_foucs"), 2, 2);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击2次底部的收藏按钮 ");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]"), 1, 10);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击资讯页面 第一个item");
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='reload btn']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击刷新 ");
//			clickByLocator(MobileBy.xpath("type=='XCUIElementTypeButton' AND name=='icon 收藏'"), 1, 2);
			List<WebElement> inputValue=getListByLocatorSize(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"));
			System.out.println(inputValue.size());
			System.out.println("规避 某些马甲包没有输入框=====");
			if (inputValue.size()<=4) {
				System.out.println("马甲包没有输入框=====  ");
				clickByLocator(inputValue.get(2), 1, 3,"");////由于浮层问题 拿到控件后转成点坐标
				reports_ValuableBookTest.log(LogStatus.INFO, "点击1次底部的收藏按钮 ");
				if (CheckViewVisibilty(By.name("登录优信二手车"))) {
					 System.out.println("=====当前未登陆状态======");
			            inputByXpath("//XCUIElementTypeTextField[@value='请输入手机号']", CaseConfig.USERNAME_BEIYONG2);
			            clickElementByName("获取验证码");
			            inputByXpath("//XCUIElementTypeTextField[@value='请输入验证码']", "666666");
			            System.out.println("登录成功");
				}
				clickByLocator(inputValue.get(2), 1, 3,"");
				reports_ValuableBookTest.log(LogStatus.INFO, "点击第2次底部的收藏按钮 ");
			}else if (inputValue.size()>=5) {
				System.out.println("===== 马甲包有输入框=====  ");
				clickByLocator(inputValue.get(3), 1, 3,"");
				reports_ValuableBookTest.log(LogStatus.INFO, "点击1次底部的收藏按钮 ");
				if (CheckViewVisibilty(By.name("登录优信二手车"))) {
					 System.out.println("=====当前未登陆状态======");
			            inputByXpath("//XCUIElementTypeTextField[@value='请输入手机号']", CaseConfig.USERNAME_BEIYONG2);
			            clickElementByName("获取验证码");
			            inputByXpath("//XCUIElementTypeTextField[@value='请输入验证码']", "666666");
			            System.out.println("登录成功");
				}
				clickByLocator(inputValue.get(3), 1, 3,"");
				reports_ValuableBookTest.log(LogStatus.INFO, "点击第2次底部的收藏按钮 ");
			}
			
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/information_detail/collect","","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/information_detail/collect","","","");
		if (os == 2) {
			clickByLocator(MobileBy.id("com.uxin.usedcar:id/iv_share"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击分享按钮");
		}else if (os == 1) {
			//TODO:
//			clickByLocator(MobileBy.xpath("type=='XCUIElementTypeButton' AND name=='icon分享'"), 1, 2);
			jsTapyDistance_X_Y_By(MobileBy.name("icon分享"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击icon分享");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击分享按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/information_detail/share","","","");
		checkvalueTxtPara("微信","朋友圈","QQ","链接"); 
		pullFileAndCompare("test_411_ZXShare");
	}
	
	/**
     * 
     * @Name test_412_BaiKeCheck
	 * @catalogue 点击宝典按钮 点击百科按钮 点击返回按钮,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_412_BaiKeCheck() {
		reports_ValuableBookTest.startTest("test_412_BaiKeCheck");
		EventManager.fileName = "test_412_BaiKeCheck.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='百科']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击百科按钮");
		}else if (os == 1) {
			//TODO:
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='百科']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击百科按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian/baike", "","","");
		if (os == 2) {
			clickByCT(MobileBy.id("com.uxin.usedcar:id/tvBack"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
		}
		pullFileAndCompare("test_412_BaiKeCheck");
	}
	
	
	/**
     * 
     * @Name test_413_BaiKeCheck
	 * @catalogue 点击宝典按钮 点击  精选百科  点击返回按钮,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_413_BaiKeCheck() {
		reports_ValuableBookTest.startTest("test_413_BaiKeCheck");
		EventManager.fileName = "test_413_BaiKeCheck.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
//			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='精选百科']"), 1, 2);
			jsTapyDistance_X_Y_By(MobileBy.name("更多百科"), 0*screenRatioX, 0*screenRatioY, "-", "-", "点击精选百科按钮");
			reports_ValuableBookTest.log(LogStatus.INFO,"点击精选百科按钮");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='精选百科'] and @visible='true'"), 1, 3);
//			clickByCT(MobileBy.iOSNsPredicateString("type=='XCUIElementTypeStaticText' AND name=='精选百科' AND visible == 1"), 1, 3);
			scrollToElementClick("精选百科","FALSE");
			reports_ValuableBookTest.log(LogStatus.INFO,"点击精选百科按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian/baike/more", "","","");
		if (os == 2) {
			clickByCT(MobileBy.id("com.uxin.usedcar:id/tvBack"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
		} 
		pullFileAndCompare("test_413_BaiKeCheck");
	}
	/**
     * 
     * @Name test_414_ZXBaiKe
	 * @catalogue 点击宝典按钮 点击精选百科 精选百科第三个精选,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_414_ZXBaiKe() {
		reports_ValuableBookTest.startTest("test_414_ZXBaiKe");
		EventManager.fileName = "test_414_ZXBaiKe.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			wait(3);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='精选百科']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击精选百科按钮");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeStaticText[@name='更多百科']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.name("精选百科"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击精选百科按钮");
			reports_ValuableBookTest.log(LogStatus.INFO,"点击精选百科按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian/baike/more", "","","");
		if (os == 2) {
			clickByCT(MobileBy.id("com.uxin.usedcar:id/tvBack"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
			String index1=androidDriver.findElement(MobileBy.className("android.widget.HorizontalScrollView")).findElement(MobileBy.xpath("//android.widget.RelativeLayout[3]/android.widget.TextView[1]")).getAttribute("name");
			jsTapyDistance_X_Y_Matches("text",index1, 0, 0, "+", "+", "精选百科第三个精选");
			reports_ValuableBookTest.log(LogStatus.INFO,"点击精选百科第三个精选");
		}else if (os == 1) {
			//TODO:
			backBTN();
			reports_ValuableBookTest.log(LogStatus.INFO,"点击返回按钮");
		 	scrollToElementClick_className(iosDriver.findElement(MobileBy.className("XCUIElementTypeTable")).findElement(MobileBy.xpath("//XCUIElementTypeCell["+3+"]/XCUIElementTypeStaticText[1]")).getAttribute("name"));
            wait(15);
            if (CheckViewVisibilty(By.name("正在加载..."))) {
                wait(20);
            }
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian/baike/rank/3", "","","");
		pullFileAndCompare("test_414_ZXBaiKe");
	}
	
	
	/**
     * 
     * @Name test_415_TIWEN
	 * @catalogue 点击宝典按钮 点击提问 我要提问文本框里面输入 自动化测试自动化测试自动化测试001 选择选车买车标签 点击  提交按钮 点击 查看问题详情,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_415_TIWEN() {
		reports_ValuableBookTest.startTest("test_415_TIWEN");
		EventManager.fileName = "test_415_TIWEN.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
//		clickWD("//android.widget.element1[@element2='element3']", "Button","text", "我要追问…", 1, 1);
		if (os == 2) {
			clickElementByXpath("//android.widget.TextView[@text='提问']");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击提问");
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name CONTAINS[cd] \"我的\"`]"), 0*screenRatioX, 62*screenRatioY, "+", "-", "点击提问 (我的按钮正垂直62Y坐标偏移)");
//			  iosDriver.tap(1, 356, 647, 200);
//			clickByCT(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]"), 1, 3);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击提问");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/ask","","","");
		if (os == 2) {
			String a="自动化测试自动化测试自动化测试001"+(8000 + (int) (Math.random() * ((9000 - 8000) + 1)));
			input("id", "com.uxin.usedcar:id/etMyQuestion", "", a, "");
			reports_ValuableBookTest.log(LogStatus.INFO,"我要提问文本框里面输入 自动化测试自动化测试自动化测试001");
			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='全国直购']"), 1, 1);
			reports_ValuableBookTest.log(LogStatus.INFO, "选择选车买车标签");
			clickWD("//android.widget.element1[@element2='element3']", "TextView","text", "提交", 1, 1);
		}else if (os == 1) {
			//TODO:
			String a="自动化测试自动化测试自动化测试001"+(8000 + (int) (Math.random() * ((9000 - 8000) + 1)));
			inputByLocator(By.className("XCUIElementTypeTextView"), a);
			reports_ValuableBookTest.log(LogStatus.INFO,"我要提问文本框里面输入 自动化测试自动化测试自动化测试001");
			 clickWD("wdType == 'element1' AND wdName == 'element2'", "XCUIElementTypeButton", "Return", 2, 2);
//			 clickByCT(MobileBy.iOSClassChain("**/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[3]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]"), 1, 3);
//			 clickByCT(MobileBy.name(iosDriver.findElement(MobileBy.xpath("//XCUIElementTypeCell[3]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]")).getAttribute("name")), 1, 3);
//			 clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 2), 1, 3,"");//第三个 分期购
			 jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeButton' AND wdName CONTAINS[cd] '车' "), 1, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击点击第二个车排序");//卖车咨询
//			 jsTapyDistance_X_Y_By(MobileBy.name(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeButton' AND wdName CONTAINS[cd] '车' "), 0, "name")), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击点击第一个车排序");//选车买车
//			 clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 4), 1, 3,"");//第一个
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='首付一半']"), 1, 2);//任意一个标签 由于标签没有埋点
			reports_ValuableBookTest.log(LogStatus.INFO, "选择选车买车标签");
//				  jsPressSwipe_para_by("down", "swipe", MobileBy.className("XCUIElementTypeTable"), "2");
		        jsPressSwipe_para_by("down", "swipe", MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeTable'"), "2");
		        reports_ValuableBookTest.log(LogStatus.INFO, "隐藏键盘 焦点");
		        clickByNameCount("提交", 1, 2);
		       
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/ask/submit","","","");
		if (os == 2) {
			clickByCT(MobileBy.xpath("//android.widget.TextView[@text='查看问题详情']"), 1, 1);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击 查看问题详情");
		}else if (os == 1) {
			//TODO:
			  if (CheckViewVisibilty(By.name("查看问题详情"))) {
		            clickByNameCount("查看问题详情", 1, 2);
		        }
		}
		pullFileAndCompare("test_415_TIWEN");
	}

	/**
     * 
     * @Name test_416_moreZIXUN
	 * @catalogue 点击宝典按钮 滑动半屏，等待3s  点击热门资讯中的更多资讯按钮 点击返回按钮 ,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_416_moreZIXUN() {
		reports_ValuableBookTest.startTest("test_416_moreZIXUN");
		EventManager.fileName = "test_416_moreZIXUN.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
			jsTapyDistance_X_Y_Matches("text","更多问答", 0, 0, "+", "+", "点击   更多问答按钮");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击更多问答按钮");
		}else if (os == 1) {
			//TODO:
			sliding("up", 1, 2);
			jsTapyDistance_X_Y_By(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] \"选车\"`]"), 0*screenRatioX, 140*screenRatioY, "+", "+", "点击更多资讯下面垂直的  更多问答  买车按钮正上方 更多问答");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击更多问答按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "baodian/qa/more", "","","");
		pullFileAndCompare("test_416_moreZIXUN");
	}
	
	
	/**
     * 
     * @Name test_417_RMZX
	 * @catalogue 点击宝典按钮 滑动半屏，等待3s  点击热门资讯中的第一个进去资讯详情页 点击刷新按钮  点击返回按钮 ,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_417_RMZX() {
		reports_ValuableBookTest.startTest("test_417_RMZX");
		EventManager.fileName = "test_417_RMZX.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
			jsTapyDistance_X_Y_Matches("text","热门资讯", 0, 0, "+", "+", "热门资讯页面 第一个item",false);
			System.out.println(androidDriver.findElement(By.id("com.uxin.usedcar:id/tvBibleTitle")));
			System.out.println(androidDriver.findElement(MobileBy.id("com.uxin.usedcar:id/tvBibleTitle")));
			String rmzx_1= getListTextByLocator(MobileBy.id("com.uxin.usedcar:id/tvBibleTitle"), 0, "name","");//热门资讯第一个
			jsTapyDistance_X_Y_Matches("text",rmzx_1, 0, 0, "+", "+", "资讯页面 第一个item");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击热门资讯中的第一个进去资讯详情页");
		}else if (os == 1) {
			//TODO:
			scrollToElementClick("热门资讯","FALSE");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击热门资讯第一个");
//			jsTapyDistance_X_Y_By(MobileBy.iOSNsPredicateString("type=='XCUIElementTypeStaticText' AND name=='更多资讯'"), 0*screenRatioX, 100*screenRatioY, "+", "+", "点击热门资讯下面第一个");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击热门资讯中的第一个");
			clickByCT(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]"), 1, 10);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击资讯页面 第一个item");
			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='reload btn']"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击刷新 ");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/information/rank/1","","","");
		pullFileAndCompare("test_417_RMZX");
	}
	
	
	/**
     * 
     * @Name test_418_RMZXMore
	 * @catalogue 点击宝典按钮 滑动半屏，等待3s  点击热门资讯中的更多资讯按钮   点击返回按钮,检测触发的埋点
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_418_RMZXMore() {
		reports_ValuableBookTest.startTest("test_418_RMZXMore");
		EventManager.fileName = "test_418_RMZXMore.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if (os == 1) {
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
            jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
			clickByCT(MobileBy.id("com.uxin.usedcar:id/tvArticleMore"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击热门资讯中的更多资讯按钮");
		}else if (os == 1) {
			//TODO:
			jsTapyDistance_X_Y_By(MobileBy.iOSNsPredicateString("type=='XCUIElementTypeStaticText' AND name=='更多资讯'"), 0*screenRatioX, 0*screenRatioY, "-", "-", "点击热门资讯 更多资讯");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击热门资讯中的更多资讯按钮");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/information/more","","","");
		backBTN();
		reports_ValuableBookTest.log(LogStatus.INFO, "点击 返回按钮");
		pullFileAndCompare("test_418_RMZXMore");
	}
	
	
	/**
     * 
     * @Name test_419_RMZXMore
	 * @catalogue 点击宝典按钮 滑动半屏，等待3s 点击热门资讯中的更多资讯按钮 上拉加载第二页  点击 更多的资讯,检测触发的埋点(由于埋点文档没有标记 所以注释掉最后一个点击更多的资讯)
	 * @Grade 高级
	 * @author liyiwan
     */
	@Test
	public void test_419_RMZXMore() {
		reports_ValuableBookTest.startTest("test_419_RMZXMore");
		EventManager.fileName = "test_419_RMZXMore.txt";
		launchApp();
		if (os == 2) {
			clickByCT(MobileBy.id("rbFaXian"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}else if(os == 1){
			//TODO:
//			clickByCT(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 1, 3);
			jsTapyDistance_X_Y_By(MobileBy.xpath("//XCUIElementTypeButton[@name='宝典']"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击宝典 ");//由于浮层问题
			reports_ValuableBookTest.log(LogStatus.INFO,"点击宝典");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "baodian_bottom", "","","");
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "Page/baodian_home", "","","");
		if (os == 2) {
			clickByCT(MobileBy.id("com.uxin.usedcar:id/tvArticleMore"), 1, 2);
			reports_ValuableBookTest.log(LogStatus.INFO, "点击热门资讯中的更多资讯按钮");
		}else if(os == 1){
			//TODO:
//			clickByCT(MobileBy.iOSNsPredicateString("type=='XCUIElementTypeStaticText' AND name=='更多资讯' AND visible == 1"), 1, 3);
			scrollToElementClick("更多资讯","FALSE");
//			jsTapyDistance_X_Y_By(MobileBy.iOSNsPredicateString("type=='XCUIElementTypeStaticText' AND name=='更多资讯'"), 0*screenRatioX, 0*screenRatioY, "-", "-", "点击热门资讯 更多资讯");
			reports_ValuableBookTest.log(LogStatus.INFO, "点击热门资讯中的更多资讯按钮");
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/information/more","","","");
		if (os == 2) {
			sliding("up", 5);
			reports_ValuableBookTest.log(LogStatus.INFO, "上拉加载第二页");
		}else if (os == 1) {
			//TODO:
			jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "5");
		}
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"baodian/information/pos/2","","","");
		pullFileAndCompare("test_419_RMZXMore");
	}

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
            Compare compare = new Compare(this.reports_ValuableBookTest);
            compare.compare("./expected_iOS/" + test + ".txt", "./actual_iOS/statistic.json", test);
		}else if (os == 2) {//android对比
			 callBriadcastAndPullFile();
			Compare compare = new Compare(this.reports_ValuableBookTest);
			compare.compare("./expected/"+test+".txt", "./actual/statistic.json",test);
		}
	       
	    }
}
