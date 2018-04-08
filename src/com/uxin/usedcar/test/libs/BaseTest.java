package com.uxin.usedcar.test.libs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import jdk.internal.dynalink.beans.StaticClass;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.google.gson.Gson;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.uxin.usedcar.maidianlib.Compare;
import com.uxin.usedcar.maidianlib.EventEntity;
import com.uxin.usedcar.maidianlib.EventManager;
import com.uxin.usedcar.testCase.BuyCarTest;
import com.uxin.usedcar.testCase.HomePageTest;
import com.uxin.usedcar.testCase.MyTest;
import com.uxin.usedcar.testCase.SellCarTest;
import com.uxin.usedcar.testCase.ValuableBookTest;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;


public class BaseTest {
	/** 当前状态保存路径 */
	public static String qaName;
	public static String qaId;//1李逸锋，2闫鑫，3亚伟，4，5夏能，6李艺宛
	public static final String CURRENT_STATE = "CaseRunTempFiles"
			+ File.separator + "CaseRunStateInfo.xml";
	public static AndroidDriver androidDriver;
	public static IOSDriver iosDriver;
	public static final ExtentReports reports_HomePageTest = ExtentReports.get(HomePageTest.class);
	public static final ExtentReports reports_BuyCarTest = ExtentReports.get(BuyCarTest.class);
	public static final ExtentReports reports_MyTest = ExtentReports.get(MyTest.class);
	public static final ExtentReports reports_ValuableBookTest = ExtentReports.get(ValuableBookTest.class);
	public static final ExtentReports reports_SellCarTest = ExtentReports.get(SellCarTest.class);
	public String profile = "system_profiler SPUSBDataType | sed -n -E -e '/(iPhone|iPad|iPod)/"
	            + ",/Serial/s/ *Serial Number: *(.+)/\\1/p'";
	//选择平台1=ios,2=android
    public static final int os =1;
    //test为1时不上传ftp，test等于0时上传ftp
    public static final int test = 1;
    protected static double screenRatioX;
    protected static double screenRatioY;
    public static TouchAction action;
    @BeforeSuite
    //开始前输入名字和id
    public static void inputQaNameAndQaId(){
    	 System.out.println("输入你的qaName:");
	        Scanner scannerQaName = new Scanner(System.in);
	        qaName = scannerQaName.next();
	        System.out.println("输入你的qaId:");
	        Scanner scannerQaId = new Scanner(System.in);
	        qaId = scannerQaId.next();
    }
	@BeforeMethod
	public void setUp() throws Exception {
		if (os==1) {
			System.out.println("IOS自动化初始化");
			DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
	        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
	        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone");
	        capabilities.setCapability("udid", CaseConfig.UDID_LYW);
//			capabilities.setCapability("udid", CaseConfig.UDID_HYW);
//			capabilities.setCapability("udid", IOSDeviceConfiguration.getDeviceUDIDiOS());
//			capabilities.setCapability("udid", CaseConfig.UDID_LYF);
	        capabilities.setCapability("bundleId", CaseConfig.BUNDLEID);
	        capabilities.setCapability("noReset", true);
	        capabilities.setCapability("logTimestamp", true);
	        capabilities.setCapability("newCommandTimeout", 0);
	        capabilities.setCapability("xcodeOrgId", "9H4SUA4596");
	        capabilities.setCapability("xcodeSigningId", "iPhone Developer");
	        capabilities.setCapability("wdaLocalPort", CaseConfig.WDAPORT);
	        capabilities.setCapability("clearSystemFiles", true);	
	        capabilities.setCapability("useNewWDA", true);
			capabilities.setCapability("preventWDAAttachments", true);
			capabilities.setCapability("simpleIsVisibleCheck", true);//初始化HashMap节点
	        capabilities.setCapability("launchTimeout", 60000);
	        iosDriver = new IOSDriver(new URL(CaseConfig.SERVICEURL_LYW), capabilities);
//			iosDriver = new IOSDriver(new URL(CaseConfig.SERVICEURL_HYW), capabilities);
	        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        CaseConfig.OS=os;
	        CaseConfig.CallSIM=0;//SIM Call 是否触发Case开关 1为开启  0关闭
	        action = new TouchAction(iosDriver);
	        double currentScreenWidth = iosDriver.manage().window().getSize().width;
	        double currentScreenHeight = iosDriver.manage().window().getSize().height;
	        System.out.println("当前分辨率宽度为===>   " + currentScreenWidth);
	        System.out.println("当前分辨率高度为===>   " + currentScreenHeight);
	        CaseConfig.CURRENTScreenWidth=currentScreenWidth;
	        CaseConfig.CURRENTScreenHeight=currentScreenHeight;
	        System.out.println("已调试脚本机器分辨率宽度为------>   " + CaseConfig.originScreenWidth);
	        System.out.println("已调试脚本机器分辨率高度为------>   " + CaseConfig.originScreenHeight);
	        screenRatioX = currentScreenWidth / CaseConfig.originScreenWidth;
	        screenRatioY = currentScreenHeight / CaseConfig.originScreenHeight;
//	        setScreenRatioY(currentScreenHeight / CaseConfig.originScreenHeight);
	        System.out.println("X 轴比例------>   " + screenRatioX);
	        System.out.println("Y 轴比例------>   " + screenRatioY);
	        EventManager eventManager = new EventManager();
	        eventManager.initEventManager();
	        //	        return driver;
		}else if(os == 2){
			System.out.println("android自动化初始化");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "AndroidBrowser");
		    capabilities.setCapability("platformName",MobilePlatform.ANDROID);
		    capabilities.setCapability("deviceName", "androidDevice");
		    //设置安卓系统版本
//		    capabilities.setCapability("platformVersion", "7.0");
		    //设置app的主包名和主类名
	        capabilities.setCapability("appPackage", "com.uxin.usedcar");
	        capabilities.setCapability("appActivity", ".ui.fragment.SplashActivity");
	        //设置安卓马甲包的包名和主类名
//	        capabilities.setCapability("appPackage", "com.uxin.uxinusedcar");
//	        capabilities.setCapability("appActivity", "com.uxin.uxinusedcar.ui.fragment.MainActivity");
	        capabilities.setCapability("unicodeKeyboard", true);
	        capabilities.setCapability("resetKeyboard", true);
	        capabilities.setCapability("recreateChromeDriverSessions", true);
	        capabilities.setCapability("newCommandTimeout", 0);
			capabilities.setCapability("session-override", true);
			capabilities.setCapability("RECREATE_CHROME_DRIVER_SESSIONS", true);
//			capabilities.setCapability("webdriver.chrome.driver", "/usr/local/lib/node_modules/appium/node_modules/appium-chromedriver/chromedriver/mac/chromedriver");
			capabilities.setCapability("automationName", "uiautomator2");
			capabilities.setCapability("noReset", true);
			capabilities.setCapability("nativeWebScreenshot", true);
			capabilities.setCapability("showChromedriverLog", true);
			//初始化
	        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	        EventManager eventManager = new EventManager();
	        eventManager.initEventManager();

		}
	}
	
	@AfterMethod
	public void tearDown(){
		if (os == 1) {
			wait(5);
			iosDriver.closeApp();
		}else if(os == 2){
			wait(5);
			System.out.println("关闭app");
			androidDriver.closeApp();
		}
		
	}
	/** 保存当前状态 */
	public static void SaveCurrentState(String key, String value) {
		String separator = System.getProperty("line.separator", "\n");
		FileWriter mFileWriter;
		BufferedWriter mBufferedWriter = null;
		try {
			mFileWriter = new FileWriter(new File(CURRENT_STATE), true);
			mBufferedWriter = new BufferedWriter(mFileWriter);
			mBufferedWriter.write("#key" + key + "#value" + value
					+ "#timestamp" + System.currentTimeMillis() + separator+"\n");
			mBufferedWriter.write("\n");
			mBufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void writerForTxt(String fileName,String content){
		try {
			String dir_name = System.getProperty("user.dir")  + File.separator+"expected";
			if (!(new File(dir_name).isDirectory())) { // 判断是否存在该目录
				System.out.println("当前目录全文路径   "+dir_name);
				new File(dir_name).mkdir();
			}
			FileWriter filewriter = new FileWriter(new File("./expected/"+fileName), true);
			BufferedWriter bufferewriter = new BufferedWriter(filewriter);
			bufferewriter.write(content+"\n");
			System.out.println("写入数据为>>>"+content);
			bufferewriter.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void writerForTxt(String fileName,String content,int times){
		try {
			FileWriter filewriter = new FileWriter(new File("./expected/"+fileName), true);
			BufferedWriter bufferewriter = new BufferedWriter(filewriter);
			bufferewriter.write(content+"\n");
			System.out.println("写入数据为>>>"+content);
			bufferewriter.close();
			wait(times);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void getPhonefile(String order){
		try {
			Process p = Runtime.getRuntime().exec(order);
			//读取命令返回值
			InputStream is = p.getInputStream();
			InputStreamReader ir = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(ir);
			String lin;
			while ((lin=br.readLine())!=null) {
				System.out.println(lin);
				if (lin.contains("KB/s")) {
					break;
				}else {
					Assert.fail(order+"执行失败");
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** 按行读取文件 */
	public static ArrayList<String> ReadFileByLine(String filepath) {
		ArrayList<String> readstrings = new ArrayList<String>();
		BufferedReader mReader = null;
		try {
			FileReader mFileReader = new FileReader(new File(filepath));
			mReader = new BufferedReader(mFileReader);
			String tempString = null;
			// 一次读入一行，知道文件为空
			while ((tempString = mReader.readLine()) != null) {
				if (!StringUtils.replaceBlank(tempString).equals("")) {
					readstrings.add(tempString);
				}
			}
			mReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readstrings;
	}
	
	/**
	 * 输入tab位置跳转到指定tab
	 * 首页 ---- 1,
	 * 买车 ---- 2,
	 * 卖车 ---- 3,
	 * 宝典 ---- 4,
	 * 我的 ---- 5
	 */
	public void gotocate(int i) {
		if (os == 2) {
			//首次启动忽略系统弹框，确保初始化成功
			if(!ReadCurrentState(CaseConfig.START_STATE, "首次启动").equals(
					CaseConfig.START_UNFIRST)){
				SaveCurrentState(CaseConfig.START_STATE, "非首次启动");
				System.out.println("首次启动");
				try {
					wait(3);
					//关闭欢迎页
					if (CheckViewVisibilty(By.id("title"))) {
						clickElementById("skip");
					}
					//关闭升级弹框
					if(CheckViewVisibilty(By.id("alertdialog_confirm"))) {
						clickElementById("dialog_cancelId");
					}
					//关闭城市切换弹层
					 if (CheckViewVisibilty(By.xpath("//android.widget.Button[@text='切换']"))) {
			    		 clickByCT(MobileBy.id("com.uxin.usedcar:id/bt_confirm_ok"), 1, 0);
			    		 }
//				//关闭运营开屏图
//				if(CheckViewVisibilty(By.id("tv_countdown"))){
//					clickElementById("tv_countdown");
//				}
//				//城市错误是处理
//				if (waitForVisible(By.id("tvDialogMessage"), 3).getText().equals("您当前的位置在【北京】，需要切换城市吗？")) {
//					clickElementById("bt_confirm_ok");
//				}
				wait(6);
				login();
				SaveCurrentState(CaseConfig.LOGIN, "已登录账号");
				if(!ReadCurrentState(CaseConfig.LOGIN, "已登录账号").equals(CaseConfig.LOGINSTATE)){
					System.out.println("登录失败，重新登录");
					wait(1);
					login();
					SaveCurrentState(CaseConfig.LOGIN, "已登录账号");
					if(!ReadCurrentState(CaseConfig.LOGIN, "已登录账号").equals(CaseConfig.LOGINSTATE)){
						failAndMessage("再次登录失败，请人工检查");
					}
				}
				gotoCateSet(i-1);
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println("启动时发生异常");
					wait(1);
					login();
					SaveCurrentState(CaseConfig.LOGIN, "已登录账号");
					if(!ReadCurrentState(CaseConfig.LOGIN, "已登录账号").equals(CaseConfig.LOGINSTATE)){
						System.out.println("登录失败，重新登录");
						wait(1);
						login();
						SaveCurrentState(CaseConfig.LOGIN, "已登录账号");
						if(!ReadCurrentState(CaseConfig.LOGIN, "已登录账号").equals(CaseConfig.LOGINSTATE)){
							failAndMessage("再次登录失败，请人工检查");
						}
					}
					gotoCateSet(i-1);
				}
			}else{
				try {			
//					//关闭运营开屏图
//					if(CheckViewVisibilty(By.id("tv_countdown"))){
//						clickElementById("tv_countdown");
//					}
//					//城市错误是处理
//					if (waitForVisible(By.id("tvDialogMessage"), 3).getText().equals("您当前的位置在【北京】，需要切换城市吗？")) {
//						clickElementById("bt_confirm_ok");
//					}
				wait(3);
				if (CheckViewVisibilty(By.id("title"))) {
					clickElementById("skip");
				}
				//关闭升级弹框
				if(CheckViewVisibilty(By.id("alertdialog_confirm"))) {
					clickElementById("dialog_cancelId");
				}
				wait(6);
				if(!ReadCurrentState(CaseConfig.LOGIN, "已登录账号").equals(CaseConfig.LOGINSTATE)){
					System.out.println("非首次启动，try中登录失败，重新登录");
					wait(1);
					login();
					SaveCurrentState(CaseConfig.LOGIN, "已登录账号");
					if(!ReadCurrentState(CaseConfig.LOGIN, "已登录账号").equals(CaseConfig.LOGINSTATE)){
						failAndMessage("再次登录失败，请人工检查");
					}
				}
				gotoCateSet(i-1);
				System.out.println("非首次启动");
				}catch(Exception e){
					System.out.println("广告没有配置或加载失败");
					if(!ReadCurrentState(CaseConfig.LOGIN, "已登录账号").equals(CaseConfig.LOGINSTATE)){
						System.out.println("非首次启动，catch登录失败，重新登录");
						wait(1);
						login();
						SaveCurrentState(CaseConfig.LOGIN, "已登录账号");
						if(!ReadCurrentState(CaseConfig.LOGIN, "已登录账号").equals(CaseConfig.LOGINSTATE)){
							failAndMessage("再次登录失败，请人工检查");
						}
					}else {
						System.out.println(ReadCurrentState(CaseConfig.LOGIN, "已登录账号"));
					}
					gotoCateSet(i-1);
					System.out.println("走catch时失败了");
				}
			}
		}else if (os == 1) {//判断城市入口  所在城市 判断所在首页的展示显示  主流城市入口 山东-青岛   广东省-东莞  辽宁省-大连     河南-郑州  
			iOSLogin();
		        if (CaseConfig.CURRENTScreenHeight==812.0) {
		        	System.out.println("当前系统为iPhoneX  ----->赋值全局控制参数    ");
		        	   CaseConfig.originHeight=8;
		        		System.out.println("当前 CaseConfig.originHeight  ----->    "+CaseConfig.originHeight);
				}
		        if (CaseConfig.CURRENTScreenHeight==736.0) {
		        	System.out.println("当前系统为iPhone 6S Plus iPhone7  ----->赋值全局控制参数    ");
		        	   CaseConfig.originHeight=7;
		        	   System.out.println("当前 CaseConfig.originHeight  ----->    "+CaseConfig.originHeight);
				}
		        if (CaseConfig.CURRENTScreenHeight==667.0) {
		        	System.out.println("当前系统为iPhone 6  ----->赋值全局控制参数    ");
		        	   CaseConfig.originHeight=6;
		        	   System.out.println("当前 CaseConfig.originHeight  ----->    "+CaseConfig.originHeight);
				}
		        
		        if (CaseConfig.CURRENTScreenHeight==568.0) {
		        	System.out.println("当前系统为iPhone 5S  ----->赋值全局控制参数    ");
		        	   CaseConfig.originHeight=7;
		        	   System.out.println("当前 CaseConfig.originHeight  ----->    "+CaseConfig.originHeight);
				}
		        if (Integer.parseInt(sessionDetail("sdkVersion").split("\\.")[0])<11) {
		         	System.out.println("默认值    "+CaseConfig.SDKVERSION);
		          	 CaseConfig.SDKVERSION=Integer.parseInt(sessionDetail("sdkVersion").split("\\.")[0]);
		      	    System.out.println("当前系统版本号11以下   对应版本号   ------>    "+CaseConfig.SDKVERSION);
		      	    System.out.println("默认值    "+CaseConfig.STATICTEXT);
		      	    System.out.println("默认值    "+CaseConfig.NAVIGATIONBAR);
		      	    CaseConfig.STATICTEXT=1;
		      	    CaseConfig.NAVIGATIONBAR="XCUIElementTypeStaticText[1]";
		      	    CaseConfig.TableViewTypeOther="XCUIElementTypeOther[2]";
		      	    System.out.println(CaseConfig.NAVIGATIONBAR);
		      	  System.out.println(CaseConfig.TableViewTypeOther);
		      	    
		        }else {
		          	CaseConfig.SDKVERSION=Integer.parseInt(sessionDetail("sdkVersion").split("\\.")[0]);
		          	System.out.println("当前系统版本号是11以上   对应版本号   ------>    "+CaseConfig.SDKVERSION);
		          	 System.out.println("默认值    "+CaseConfig.STATICTEXT);
		          	CaseConfig.STATICTEXT=2;
		          	CaseConfig.NAVIGATIONBAR="XCUIElementTypeOther[1]";
		          	 CaseConfig.TableViewTypeOther="XCUIElementTypeOther[1]";
		          	 System.out.println(CaseConfig.TableViewTypeOther);
				}
	    	   
			if (i == 1) {
//				click("xpath", "//XCUIElementTypeButton[@name='element']", "首页", "", "");
				jsTapyDistance_X_Y_By(MobileBy.name("首页"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击 首页按钮");
				reports_HomePageTest.log(LogStatus.INFO, "点击 首页按钮");
				CaseConfig.GPS=iosDriver.getPageSource();
				System.out.println("=========================================当前首页为新的ABTest  新的============================================================");
				CaseConfig.AB = 1;
				if (CaseConfig.originHeight == 8) {
					System.out.println("=========================================iPhoneX ============================================================");
					CaseConfig.CITY_NAME =  getListTextByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@label,' ')]"), 0,"name");
					String currentCityName = CaseConfig.CITY_NAME.trim();
					CaseConfig.currentCityName = currentCityName;
				}
				if (CaseConfig.originHeight == 7) {
					System.out.println("=========================================iPhone 6S Plus  iPhone7 ============================================================");
					// iPhone (11.2.1) [1851e96c01d50f6fbc2032a31a4e903181cdc159]
					try {
						CaseConfig.CITY_NAME =getListTextByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@label,' ')]"), 0,"name");
					} catch (Exception e) {
					}
					// 以下为特定机型适配 如果不考虑11以下的系统的话 可以采用Chain方法替代 目前暂时保留替换值方式
					// if (sessionDetail("udid").equals("16a33224ab79b1e805e12c1bce6ea96145374067"))
					// {//iPhone (11.1.2)
					// [16a33224ab79b1e805e12c1bce6ea96145374067]//生成脚本的App占用屏幕尺寸(414,736)
					// CaseConfig.CITY_NAME=getListTextByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@label,' ')]"), 0,"name");
					// }
					// if (sessionDetail("udid").equals("1851e96c01d50f6fbc2032a31a4e903181cdc159"))
					// {//李想的 iPhone (11.2.5)
					// [1851e96c01d50f6fbc2032a31a4e903181cdc159]//生成脚本的App占用屏幕尺寸(414,736)
					// CaseConfig.CITY_NAME=getListTextByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@label,' ')]"), 0,"name");
					// }
					// if (sessionDetail("udid").equals("bbf063c11ce3a1756fb69dafff1db5656d1c5270"))
					// {//李想的 iPhone (11.2.5)
					// [1851e96c01d50f6fbc2032a31a4e903181cdc159]//生成脚本的App占用屏幕尺寸(414,736)
					// CaseConfig.CITY_NAME=getListTextByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@label,' ')]"), 0,"name");
					// }
					String currentCityName = CaseConfig.CITY_NAME.trim();
					CaseConfig.currentCityName = currentCityName;
				}
				if (CaseConfig.originHeight == 6) {// 李想的 iPhone (10.3.3)// [bbf063c11ce3a1756fb69dafff1db5656d1c5270]生成脚本的App占用屏幕尺寸(375,667)
					System.out.println("=========================================iPhone 6 ============================================================");
					CaseConfig.CITY_NAME =getListTextByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@label,' ')]"), 0,"name");
					String currentCityName = CaseConfig.CITY_NAME.trim();
					CaseConfig.currentCityName = currentCityName;
				}
				if (CaseConfig.originHeight == 5) {
					System.out.println("=========================================iPhone5S 4S============================================================");
					CaseConfig.CITY_NAME = getListTextByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@label,' ')]"), 0,"name");
					String currentCityName = CaseConfig.CITY_NAME.trim();
					CaseConfig.currentCityName = currentCityName;
				}
				if (CaseConfig.CITY_NAME != null) {
					String currentCityName = CaseConfig.CITY_NAME.trim();
					CaseConfig.currentCityName = currentCityName;
					if (currentCityName.equals("北京") || currentCityName.trim().equals("广东省")
							|| currentCityName.trim().equals("云贵川") || currentCityName.trim().equals("江浙沪")
							|| currentCityName.trim().equals("京津冀")) {
						// 一成购
						System.out.println(
								"=========================================当前首页包含1⃣️ 新车一成购  2⃣️二手车一成购  3⃣️热门推荐============================================================");
						System.out.println(currentCityName);
						CaseConfig.SW1 = CaseConfig.CITY_NAME.trim();
						System.out.println("当前标记为SW3对应的属性城市值-------->      " + CaseConfig.SW1
								+ "  ------>   当前首页包含1⃣️ 新车一成购  2⃣️二手车一成购  3⃣️热门推荐");
					}
					if (currentCityName.trim().equals("山东省") || currentCityName.trim().equals("辽宁省")
							|| currentCityName.trim().equals("全国")) {
						System.out.println(
								"=========================================当前首页包含1⃣️ 三成首付 • 还款灵活  2⃣️⃣️热门推荐============================================================");
						System.out.println(currentCityName);
						CaseConfig.SW2 = CaseConfig.CITY_NAME.trim();
						System.out.println("当前标记为SW3对应的属性城市值-------->      " + CaseConfig.SW2
								+ "  ------>   当前首页包含1⃣️ 三成首付 • 还款灵活  2⃣️⃣️热门推荐");
					}
					if (currentCityName.equals("河南省")) {
						System.out.println(
								"=========================================当前首页包含1⃣️二手车一成购  2⃣️⃣️热门推荐============================================================");
						System.out.println(currentCityName);
						CaseConfig.SW3 = CaseConfig.CITY_NAME.trim();
						System.out.println("当前标记为SW3对应的属性城市值-------->      " + CaseConfig.SW3
								+ "  ------>   当前首页包含1⃣️二手车一成购  2⃣️⃣️热门推荐  ");
					}
				}
			}
			
			if (i == 2) {// 判断城市入口 所在城市
				jsTapyDistance_X_Y_By(MobileBy.name("买车"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击 买车按钮");
				reports_BuyCarTest.log(LogStatus.INFO, "点击 买车按钮");
				CaseConfig.GPS=iosDriver.getPageSource();
				if (CheckViewVisibilty(By.name("img_jiazaishibai-wife"))) {
					slidingInElement(findElementByClassName("XCUIElementTypeWebView"), "down");
				}
				if (CheckViewVisibilty(By.name("加载失败，请点击屏幕重新加载"))) {
					slidingInElement(findElementByClassName("XCUIElementTypeWebView"), "down");
				}
				try {
					if (iosDriver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]")) != null) {
						slidingInElement(findElementByClassName("XCUIElementTypeCollectionView"), "down");
						slidingCountClassName(10, "XCUIElementTypeCollectionView", "down");
						if (!(iosDriver.findElementByName("button rexiaocheyuan shuanglie").getAttribute("value") == null)) {
							System.out.println("单双列值--->>   " + iosDriver.findElementByName("button rexiaocheyuan shuanglie").getAttribute("value"));
							System.out.println("当前车辆是双列");
							clickByNameCount("button rexiaocheyuan shuanglie", 1, 8);// 切换至单列
						}
					}
				} catch (Exception e) {
					if (!(iosDriver.findElementByName("button rexiaocheyuan shuanglie").getAttribute("value") == null)) {
						System.out.println("单双列值--->>   "+ iosDriver.findElementByName("button rexiaocheyuan shuanglie").getAttribute("value"));
						System.out.println("当前车辆是双列");
						clickByNameCount("button rexiaocheyuan shuanglie", 1, 8);// 切换至单列
					}
				}
				if (CaseConfig.originHeight == 8) {
					System.out.println("=========================================iPhoneX ============================================================");
					CaseConfig.CITY_NAME =getListTextByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@label,' ')]"), 1,"name");
					String currentCityName = CaseConfig.CITY_NAME.trim();
					CaseConfig.currentCityName = currentCityName;
				}
				if (CaseConfig.originHeight == 7) {
					System.out.println("=========================================iPhone 6S Plus  iPhone7 ============================================================");
					CaseConfig.CITY_NAME = getListTextByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@label,' ')]"), 1,"name");
					String currentCityName = CaseConfig.CITY_NAME.trim();
					CaseConfig.currentCityName = currentCityName;
				}
				if (CaseConfig.originHeight == 6) {// 李想的 iPhone (10.3.3) [bbf063c11ce3a1756fb69dafff1db5656d1c5270]
					System.out.println("=========================================iPhone 6 ============================================================");
					CaseConfig.CITY_NAME = getListTextByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@label,' ')]"), 1,"name");
					String currentCityName = CaseConfig.CITY_NAME.trim();
					CaseConfig.currentCityName = currentCityName;
				}
				if (CaseConfig.originHeight == 5) {
					System.out.println("=========================================iPhone5S 4S============================================================");
					CaseConfig.CITY_NAME = getListTextByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@label,' ')]"), 1,"name");
					String currentCityName = CaseConfig.CITY_NAME.trim();
					CaseConfig.currentCityName = currentCityName;
				}
			}
			if (i == 3) {
//				click("xpath", "//XCUIElementTypeButton[@name='element']", "卖车", "", "");
				jsTapyDistance_X_Y_By(MobileBy.name("卖车"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击 卖车按钮");
				reports_SellCarTest.log(LogStatus.INFO, "点击 卖车按钮");
				CaseConfig.GPS=iosDriver.getPageSource();
			}
			if (i == 4) {
//				click("xpath", "//XCUIElementTypeButton[@name='element']", "宝典", "", "");
				jsTapyDistance_X_Y_By(MobileBy.name("宝典"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击 宝典按钮");
				reports_ValuableBookTest.log(LogStatus.INFO, "点击 宝典按钮");
				CaseConfig.GPS=iosDriver.getPageSource();
			}
			if (i == 5) {
//				click("xpath", "//XCUIElementTypeButton[@name='element']", "我的", "", "");
				jsTapyDistance_X_Y_By(MobileBy.name("我的"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击 我的按钮");
				reports_MyTest.log(LogStatus.INFO, "点击 我的按钮");
				 CaseConfig.GPS=iosDriver.getPageSource();
			}
		}
	}
    /**
     * 按照Name属性点击
     *
     * @param name  name属性
     * @param count 执行次数
     * @param times 执行时间
     */
    public static void clickByNameCount(String name, int count, int times) {
    	if (os == 1) {
    		try {
                WebDriverWait wait = new WebDriverWait(iosDriver, 50);
                wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
                if (null != name) {
                    for (int i = 0; i < count; i++) {
                    	    System.out.println("当前点击的名称是---->       "+name);
                    	    iosDriver.findElement(By.name(name)).click();
                        wait(times);
                    }
                }

            } catch (Exception e) {
                GetScreenshot(name);
                CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;

            }
		}else if (os == 2) {
			try {
                WebDriverWait wait = new WebDriverWait(androidDriver, 50);
                wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
                if (null != name) {
                    for (int i = 0; i < count; i++) {
                    	    System.out.println("当前点击的名称是---->       "+name);
                    	    androidDriver.findElement(By.name(name)).click();
                        wait(times);
                    }
                }

            } catch (Exception e) {
                GetScreenshot(name);
                CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;

            }
		}
        
    }
	 /**
     * 重载滑动，需传入方向
     *
     * @param data 参数可为：上滑，下滑，左滑，右滑
     */
    public static void slidingCountClassName(int  count,String className,String direction) {
    	 if (null != className) {
             for (int i = 0; i < count; i++) {
            	 	slidingInElement(findElementByClassName(className), direction);
             }
         }
    }
	/**
     * 通过classname查找元素
     *
     * @param driver
     * @param name
     * @return
     * @throws NoSuchElementException
     */
    public static WebElement findElementByClassName(String classname) {
    	WebElement Element;
    	if (os == 1) {
    		  Element = iosDriver.findElement(By.className(classname));
    	      return Element;
		}else if (os == 2){
			 Element = androidDriver.findElement(By.className(classname));
   	         return Element;
		}
		return Element;
      
    }
	
	//登录，默认使用14725836914
		public void login() {
			gotoCateSet(4);
			if (findElementById("tvTips").getText().equals("点击登录")) {
				System.out.println("********未登录*********");
				clickElementById("imgTouXiang");
				input("id", "etPhoneNum", "",CaseConfig.USERNAME_BEIYONG2, "");
				clickElementByName("获取验证码");
				input("id", "etYanZhengMa", "", "666666", "");
				clickElementById("btnLogin");
				wait(3);
				System.out.println("开始寻找");
				String userName=findElementById("tvTips").getText();
				System.out.println(userName);
				if (userName.equals("147****6911")) {
					System.out.println("登录成功");
				}else {
					failAndMessage("登录失败请人工检查");
				}
			}else if (!findElementById("tvTips").getText().toString().substring(0, 3).equals("147")) {
				System.out.println(findElementById("tvTips").getText());
				clickElementById("ivSetting");
				clickElementByName("退出登录");
				
				failAndMessage("登录账号不是自动化账号，请退出后再执行");
			}else {
				System.out.println("******已登录*******");
			}
			
		}
		/**
	/**
	 * 传入tab值跳转到对应tab
	 * @param int i
	 **/
	public static void gotoCateSet(int i){
		if (i==0) {
			sleep(200);
			clickElementById("rbShouYe");
		}
		if (i==1) {
			sleep(200);
			clickElementById("rbCheShi"); 
		}
		
		if (i==2) {
			sleep(200);
			clickElementById("rbSellCar"); 
		}
		
		if (i==3) {
			sleep(200);
			clickElementById("rbFaXian");
		}
		if (i==4) {
			sleep(200);
			clickElementById("rbWo");
		}
	}
	
	/**
     * 通过xpath查找元素
     * @param driver
     * @param xmlPath
     * @return
     * @throws NoSuchElementException
     */
	public static WebElement findElementByXpath(String xmlPath){
		WebElement Element = null;
		if (os == 1) {
			try {
				 Element = iosDriver.findElement(By.xpath(xmlPath));
				return Element;
			} catch (NoSuchElementException e) {
				Element=null;
				// TODO: handle exception
			}
		}else if(os == 2){
			try {
				 Element = androidDriver.findElement(By.xpath(xmlPath));
				 return Element;
			} catch (NoSuchElementException e) {
				Element=null;
				// TODO: handle exception
			}
		}
		return Element;
	}
	 
	
	/**
	 * 根据xpath获取索引个数
	 *
	 * @param xpath
	 * @return 文本
	 */
	public static String getListTextByXpath(String xpath, int index, String attribute) {
		try {
			WebDriverWait wait = new WebDriverWait(androidDriver, 20);
//				System.out.println(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			List<WebElement> list = androidDriver.findElements(By.xpath(xpath));

			if (null != list && list.size() > 0) {
				System.out.println("当前列表子集合总数:---->   " + list.size());
				System.out.println("当前索引对应文本返回值是:---->   " + list.get(index).getAttribute(attribute));
				return list.get(index).getAttribute(attribute);
			} else {
				return null;
			}
		} catch (Exception e) {
			GetScreenshot("索引错误" + xpath);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据xpath获取索引个数
	 *
	 * @param xpath
	 * @return 文本
	 */
	public static String getListTextByXpath(String xpath, int index, String attribute,String target) {
		try {
			WebDriverWait wait = new WebDriverWait(iosDriver, 20);
//				System.out.println(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			List<WebElement> list = iosDriver.findElements(By.xpath(xpath));

			if (null != list && list.size() > 0) {
				System.out.println("当前列表子集合总数:---->   " + list.size());
				System.out.println("当前索引对应文本返回值是:---->   " + list.get(index).getAttribute(attribute));
				return list.get(index).getAttribute(attribute);
			} else {
				return null;
			}
		} catch (Exception e) {
			GetScreenshot("索引错误" + xpath);
			e.printStackTrace();
			return null;
		}
	}
	 public static String xpathReplace(String objectProper, String wdType,String value, String label) {
//			clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", "返回", "");
//	    	clickWD("//android.widget.element1[@'element2'='element3']", "RadioButton","text", "宝典");
	        if (objectProper.contains("element1") && !wdType.equals("") && !value.equals("")&&!label.equals("")) {

	            objectProper = objectProper.replace("element1", wdType).replace("element2", value).replace("element3", label);
	            System.out.println("当前拼接的objectProper对象   " + objectProper);
	        }
	        return objectProper;
	    }
	 /**
	     * @param objectProper 需要传递的组合控件集合
	     * @param wdType       谓词类型
	     * @param label        谓词标签
	     * @param property     属性value /name
	     * @param getTxt
	     * @return
	     */
	    public static void inputByLocator(final By objectProper, String property) {
	       
	        if (os==2) {
	         	WebDriverWait wait = new WebDriverWait(androidDriver, 20);
	         	AndroidElement webElement = (AndroidElement) androidDriver.findElement(objectProper);
	         	try {
	                if (webElement.getText() != null) {
	                    String txt = webElement.getText();
	                    System.out.println(txt);
	                    System.out.println(txt.length());
	                    if (!(txt.length() == 0)) {
	                        webElement.clear();
	                    }
	                    webElement.sendKeys(property);
	                    System.out.println("控件输入数据成功输入的数据是==" + property);
	                    CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
	                }
	            } catch (Exception e) {
	                webElement.setValue(property);
	                GetScreenshot(property);
	                CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
	            }
			}else if (os==1) {
				 WebDriverWait wait = new WebDriverWait(iosDriver, 20);
				 IOSElement webElement = (IOSElement) iosDriver.findElement(objectProper);
				 try {

			            if (webElement.getText() != null) {
			                String txt = webElement.getText();
			                System.out.println(txt);
			                System.out.println(txt.length());
			                if (!(txt.length() == 0)) {
			                    webElement.clear();
			                }
			                webElement.sendKeys(property);
			                System.out.println("控件输入数据成功输入的数据是==" + property);
			                CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
			            }
			        } catch (Exception e) {
			            webElement.setValue(property);
			            GetScreenshot(property);
			            CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
			        }
			}
	    }
	    
	    /**
	     * @param objectProper 需要传递的组合控件集合
	     * @param wdType       谓词类型
	     * @param label        谓词标签
	     * @param property     属性value /name
	     * @param getTxt
	     * @return
	     */
	    public String getTextByLocator(String objectProper, String wdType, String label, String property, String getTxt) {
//				clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", "返回", "");
	        if (objectProper.contains("element1") && !wdType.equals("") && !label.equals("")) {
	            objectProper = objectProper.replace("element1", wdType).replace("element2", label);
	            System.out.println("当前拼接的objectProper对象   " + objectProper);
	        }
	        try {
	            if (property.equals("name")) {
	                String returnTxt =iosDriver.findElement(MobileBy.iOSNsPredicateString(objectProper)).getAttribute(property);
	                System.out.println("当前拼接的谓词为--->   " + returnTxt);
	                return returnTxt;
	            }
	            if (property.equals("label")) {
	                String returnTxt = iosDriver.findElement(MobileBy.iOSNsPredicateString(objectProper)).getAttribute(property);
	                System.out.println("当前拼接的谓词为--->   " + returnTxt);
	                return returnTxt;

	            } else if (property.equals("value")) {
	                String returnTxt = iosDriver.findElement(MobileBy.iOSNsPredicateString(objectProper)).getAttribute(property);
	                System.out.println("当前拼接的谓词为--->   " + returnTxt);
	                return returnTxt;
	            }
	        } catch (Exception e) {

	            GetScreenshot(objectProper);
	            CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
	        }
	        return getTxt;
	  }
	
	 
	 public static String getListByLocator(final By  locator,int index,String value) {
			
			try {
//				WebDriverWait wait = new WebDriverWait(androidDriver, 20);
//					System.out.println(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
				List<WebElement> list = iosDriver.findElements(locator);
				 if (null != list && list.size() > 0) {
					 System.out.println("当前列表子集合总数:---->   " + list.size());
		             System.out.println("当前索引对应文本返回值是:---->   " + list.get(index).getAttribute(value));
		                return list.get(index).getAttribute(value);
		            } else {
		              	System.out.println("索引错误 请检查是否为List集合:---->   ");
		                return null;
		            }
			} catch (Exception e) {
				GetScreenshot("索引错误");
				e.printStackTrace();
				return null;
		   }
	  }
		
		public static   WebElement getListByLocator(final By  locator,int index) {
			try {
//				WebDriverWait wait = new WebDriverWait(androidDriver, 20);
//					System.out.println(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
				List<WebElement> list = iosDriver.findElements(locator);

				 if (null != list && list.size() > 0) {
		            	System.out.println(list.size());
		                return list.get(index);
		            } else {
		                return null;
		            }
			} catch (Exception e) {
				GetScreenshot("索引错误");
				e.printStackTrace();
				return null;
		   }
	  }
	
	
	public static String getListTextByLocator(final By  locator, int index, String attribute) {
			try {
				WebDriverWait wait = new WebDriverWait(iosDriver, 20);
//					System.out.println(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
				List<WebElement> list = iosDriver.findElements(locator);

				if (null != list && list.size() > 0) {
					System.out.println("当前列表子集合总数:---->   " + list.size());
					System.out.println("当前索引对应文本返回值是:---->   " + list.get(index).getAttribute(attribute));
					return list.get(index).getAttribute(attribute);
				} else {
					return null;
				}
			} catch (Exception e) {
				GetScreenshot("索引错误");
				e.printStackTrace();
				return null;
		}
  }
	
	public static String getListTextByLocator(final By  locator, int index, String attribute,String target) {
		try {
			WebDriverWait wait = new WebDriverWait(androidDriver, 20);
//				System.out.println(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			List<WebElement> list = androidDriver.findElements(locator);

			if (null != list && list.size() > 0) {
				System.out.println("当前列表子集合总数:---->   " + list.size());
				System.out.println("当前索引对应文本返回值是:---->   " + list.get(index).getAttribute(attribute));
				return list.get(index).getAttribute(attribute);
			} else {
				return null;
			}
		} catch (Exception e) {
			GetScreenshot("索引错误");
			e.printStackTrace();
			return null;
	}
}
	
	public static    List<WebElement> getListByLocatorSize(final By  locator) {
		try {
			List<WebElement> list = iosDriver.findElements(locator);

			 if (null != list && list.size() > 0) {
	            	System.out.println(list.size());
	                return list;
	            } else {
	                return null;
	            }
		} catch (Exception e) {
			GetScreenshot("索引错误");
			e.printStackTrace();
			return null;
	   }
  }
	
	
	 /**
     * 按照Name属性点击
     *
     * @param name  name属性
     * @param count 执行次数
     * @param times 执行时间
     */
    public static void clickByCT(final By by, int count, int times) {
    	if (os == 1) {
    		try {
                WebDriverWait wait = new WebDriverWait(iosDriver, 50);
                wait.until(ExpectedConditions.elementToBeClickable(by));
                if (null != by) {
                    for (int i = 0; i < count; i++) {
                    	    System.out.println("当前点击的名称是---->       "+by);
                    	    iosDriver.findElement(by).click();
                        wait(times);
                    }
                }
            } catch (Exception e) {
                GetScreenshot(iosDriver.findElement(by).getAttribute("name"));
                CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
            }
		}else if(os == 2){
			try {
	            WebDriverWait wait = new WebDriverWait(androidDriver, 50);
	            wait.until(ExpectedConditions.elementToBeClickable(by));
	            if (null != by) {
	                for (int i = 0; i < count; i++) {
	                	    System.out.println("当前点击的名称是---->       "+by);
	                    androidDriver.findElement(by).click();
	                    wait(times);
	                }
	            }
	        } catch (Exception e) {
	            GetScreenshot(androidDriver.findElement(by).getAttribute("name"));
	            CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
	        }
		}
    }
    public void clickGaoji() {
    	  jsTapyDistance_X_Y_Matches("text", getListTextByXpath("//android.widget.TextView[contains(@text,'找到')]", 0, "name"),0, 0, "+", "+", "点击找到XX辆车 或者 查看周边城市车辆");
  		
  	}
//    /**
//     * @param NsPredicate 谓词定位方式传
//     * @param x           X轴
//     * @param y           Y轴
//     * @param add         加坐标
//     * @param minus       减坐标
//     * @param target      备注操作目标控件名称
//     * @return 
//     */
//    public List<String> jsTapyDistance_X_Y(String Automator, int x, int y, String add, String minus, String target,String className) {
//    	List<String> list = new ArrayList<String>();
//    	AndroidElement webElement = (AndroidElement) driver.findElement(MobileBy.xpath("//android.widget.FrameLayout[1]/android.widget.ImageView[2]"));
//        
////    		new TouchAction((PerformsTouchActions) driver).press(webElement).waitAction(70).release().perform();
//        System.out.println("X--->   " + String.valueOf(webElement.getCenter().getX()));
//        System.out.println("Y--->   " + String.valueOf(webElement.getCenter().getY()));
//        // mobile:tap
//        
//        
//        if (add.equals("+") && minus.equals("-")) {
//            System.out.println("当前表达式为---->     " + "  X轴  +" + "  Y轴  -");
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            HashMap<String, String> tapObject = new HashMap<String, String>();
//            tapObject.put("x", String.valueOf(webElement.getCenter().getX() + x));
//            tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() - y)));
//
//            System.out.println("x  " + String.valueOf(webElement.getCenter().getX() + x));
//            System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() - y)));
//            String X_=String.valueOf(webElement.getCenter().getX() + x);
//            String Y_=String.valueOf(Math.abs(webElement.getCenter().getY() + y));
//            list.add(X_);
//            list.add(Y_);
//            return list;
//        }
//        if (add.equals("-") && minus.equals("+")) {
//            System.out.println("当前表达式为---->     " + "  X轴  -" + "  Y轴  +");
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            HashMap<String, String> tapObject = new HashMap<String, String>();
//            tapObject.put("x", String.valueOf(webElement.getCenter().getX() - x));
//            tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
//
//            System.out.println("x  " + String.valueOf(webElement.getCenter().getX() - x));
//            System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
//            String X_=String.valueOf(webElement.getCenter().getX() + x);
//            String Y_=String.valueOf(Math.abs(webElement.getCenter().getY() + y));
//            list.add(X_);
//            list.add(Y_);
//            return list;
//        }
//        if (add.equals("+") && minus.equals("+")) {
//            System.out.println("当前表达式为---->     " + "  X轴  +" + "  Y轴  +");
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            HashMap<String, String> tapObject = new HashMap<String, String>();
//            tapObject.put("x", String.valueOf(webElement.getCenter().getX() + x));
//            tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
//            
//            System.out.println("x  " + String.valueOf(webElement.getCenter().getX() + x));
//            System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
//            String X_=String.valueOf(webElement.getCenter().getX() + x);
//            String Y_=String.valueOf(Math.abs(webElement.getCenter().getY() + y));
//            list.add(X_);
//            list.add(Y_);
////            System.out.println(list.size());
////            System.out.println(list.get(0));
////            System.out.println(list.get(1));
//            return list;
//        }
//        if (add.equals("-") && minus.equals("-")) {
//            System.out.println("当前表达式为---->     " + "  X轴  -" + "  Y轴  -");
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            HashMap<String, String> tapObject = new HashMap<String, String>();
//            tapObject.put("x", String.valueOf(Math.abs(webElement.getCenter().getX() - x)));
//            tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() - y)));
//
//            System.out.println("x  " + String.valueOf(Math.abs(webElement.getCenter().getX() - x)));
//            System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() - y)));
//            String X_=String.valueOf(webElement.getCenter().getX() + x);
//            String Y_=String.valueOf(Math.abs(webElement.getCenter().getY() + y));
//            list.add(X_);
//            list.add(Y_);
//            return list;
//        }
//		return list;
//    }
//    
    
    /**
     * 目前为谓词操作方式点击
     *
     * @param objectProper 传递的未参数化的谓词组合
     * @param wdType       谓词类型 相当于控件类型
     * @param label        谓词标识符
     * @param Count        操作调用次数
     * @param times        延迟时间
     */
    public static void clickWD(String objectProper, String wdType,String value, String label, int Count, int times) {
//		clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", "返回", "");
//    	clickWD("//android.widget.element1[@'element2'='element3']", "RadioButton","text", "宝典", 1, 1);
        if (objectProper.contains("element1") && !wdType.equals("") && !value.equals("")&&!label.equals("")) {

            objectProper = objectProper.replace("element1", wdType).replace("element2", value).replace("element3", label);
            System.out.println("当前拼接的objectProper对象   " + objectProper);
        }

        try {
            if (Count == 1) {
                androidDriver.findElement(MobileBy.xpath(objectProper)).click();
                wait(times);
                System.out.println("当前拼接的组合XPATH为--->   " + MobileBy.xpath(objectProper));
                CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
            }
            while (Count > 1) {
                for (int i = 0; i < Count; i++) {
                	androidDriver.findElement(MobileBy.xpath(objectProper)).click();
                    wait(times);
                    System.out.println("当前拼接的组合XPATH为--->   " + MobileBy.xpath(objectProper));
                    CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
                }
                break;
            }

        } catch (Exception e) {
            GetScreenshot("点击--->     失败");
            CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
        }
    }
    
   
    
    public static void initLaunch() {
    	writerForTxt("test_405_BDWD.txt", "ev：start（type=a）");
		EventManager.saveLifeCycleEvent(EventEntity.LifeCycleEvent.APP_START);

		writerForTxt("test_405_BDWD.txt", "ev：openapp/start_click（type=c）",1);
		EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"openapp/start_click","","","");

		writerForTxt("test_405_BDWD.txt", "ev：home_page（type=w）",1);
		EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"home_page","","","");

		writerForTxt("test_405_BDWD.txt", "pl：a_home/guess_like_expo/algorithm/bus（type=e）",3);
		EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"a_home/guess_like_expo/algorithm/bus","","","");

		androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		reports_ValuableBookTest.log(LogStatus.INFO,"关闭升级弹框");
		
    	 if (CheckViewVisibilty(By.name("切换"))) {
    		 clickByCT(MobileBy.id("com.uxin.usedcar:id/bt_confirm_ok"), 1, 0);
    		 }
    	 clickByLocator(MobileBy.xpath("//android.widget.RadioButton[@text='我的']"), 1, 1);
     }
    
    public static String scrollToElementClick(String text, int times) {
        IOSElement element = (IOSElement) iosDriver.findElementByIosNsPredicate("wdName = '" + text + "'");
        try {
            JavascriptExecutor js = (JavascriptExecutor) iosDriver;
            HashMap<String, String> scrollObject = new HashMap<>();
            scrollObject.put("toVisible", "true");
            //value 为false时候wdLabel
//            scrollObject.put("predicateString", "wdValue == '" + text + "'");
//		    scrollObject.put("predicateString", "wdLabel == '" + text + "'");
            js.executeScript("mobile:scroll", scrollObject);
//          clickWD("wdLabel == 'element1' AND visible == 1",element, 1, 5);
            clickWD("wdName == 'element1' AND visible == 1", text, 1, times,"TRUE");
            return element.getText();//blank
        } catch (Exception e) {
            return element.getText();
        }
    }
    
    public static String getTextByChain(String iOSClassChainString) {
        try {
//				WebDriverWait wait=new WebDriverWait(driver, 20);
//				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            String text = iosDriver.findElement(MobileBy.iOSClassChain(iOSClassChainString)).getText();
            System.out.println("当前返回的文本--->    " + text);
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
    
    public static void backBTN() {
        if (os==2) {
        	if (CheckViewVisibilty(By.id("imgBtBack"))) {
            	clickByCT(MobileBy.id("com.uxin.usedcar:id/imgBtBack"), 1, 2);
            } else {
            	clickByCT(MobileBy.id("com.uxin.usedcar:id/tvBack"), 1, 2);
            }
			
		}else if (os==1) {
			 if (CheckViewVisibilty(By.name("icon chexiangqing titlebar bac"))) {
		            clickWD("wdType == 'element1' AND name == 'element2'", "XCUIElementTypeButton", "icon chexiangqing titlebar bac", 1, 3);
		        } 
			 if(CheckViewVisibilty(By.name("返回"))){
		            clickWD("wdType == 'element1' AND name == 'element2'", "XCUIElementTypeButton", "返回", 1, 2);
			}
			 else if (CheckViewVisibilty(By.xpath("//XCUIElementTypeButton[@name='detection naviback']"))) {
				 jsTapyDistance_X_Y_By(MobileBy.name("detection naviback"), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击瑕疵页面返回");
			}
		}
	}
    public static String scrollToElementClick_className(String text) {
        IOSElement element = (IOSElement) iosDriver.findElementByIosNsPredicate("name = '" + text + "'");
        try {
            JavascriptExecutor js = (JavascriptExecutor) iosDriver;
            HashMap<String, String> scrollObject = new HashMap<>();
            scrollObject.put("toVisible", "true");
//		        scrollObject.put("predicateString", "wdLabel == '" + text + "'");
            scrollObject.put("predicateString", "wdLabel == '" + text + "'");
            System.out.println("---------------------开始执行谓词操作---------------------------");
            js.executeScript("mobile:scroll", scrollObject);
            element.click();
            return element.getText();//blank
        } catch (Exception e) {
            return element.getText();
        }
    }
    
    /**
     * 当控件定位到服务端仍旧无法解析时候用该方法 
     * @param text   谓词
     * @param Scroll 是否执行谓词滚动位移操作
     */
    public static void scrollToElementClick(String text,String Scroll) {
        IOSElement element = (IOSElement) iosDriver.findElementByIosNsPredicate("name = '" + text + "'");
        try {
            JavascriptExecutor js = (JavascriptExecutor) iosDriver;
            HashMap<String, String> scrollObject = new HashMap<>();
            scrollObject.put("toVisible", "true");
//		        scrollObject.put("predicateString", "wdLabel == '" + text + "'");
            scrollObject.put("predicateString", "wdLabel == '" + text + "'");
            if (Scroll.equals("Scroll")) {
            	  System.out.println("---------------------开始执行谓词操作---------------------------");
              js.executeScript("mobile:scroll", scrollObject);
			}
            element.click();
//            return element.getText();//blank
        } catch (Exception e) {
//            return element.getText();
        }
	
    }
    
    public static void checkLoin(){
    	
    	if(getListTextByXpath("//android.widget.TextView[contains(@text,'***')]", 0, "name") != null) {
			System.out.println("=====当前处于登陆状态======");
		} else {
			clickElementById("imgTouXiang");
			reports_MyTest.log(LogStatus.INFO,"点击头像");
			input("id", "etPhoneNum", "",CaseConfig.USERNAME_BEIYONG2, "");
			clickElementByName("获取验证码");
			input("id", "etYanZhengMa", "", "666666", "");

			writerForTxt("test_5200_DL.txt", "ev：login（type=c）");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "login","","","");

			writerForTxt("test_5200_DL.txt", "ev：used_login#tel_num=[value]（type=c）");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "used_login#tel_num="+CaseConfig.USERNAME_BEIYONG2,"","","");

			clickElementById("btnLogin");
			reports_MyTest.log(LogStatus.INFO,"点击登录");
//		read("./expected/test_5200_DL.txt", "./actual/test_5200_DL.txt");
			androidDriver.pressKeyCode(AndroidKeyCode.BACK);

		}
    }
    
    /**
     * @param objectProper 需要传递的组合控件集合
     * @param wdType       谓词类型
     * @param label        谓词标签
     * @param property     属性value /name
     * @param getTxt
     * @return
     */
    public static void clickByLocator(final By objectProper,  int count, int times) {
    	if (os == 1) {
    		 WebDriverWait wait = new WebDriverWait(iosDriver, 20);
    	        wait.until(ExpectedConditions.elementToBeClickable(objectProper));
    	        try {

    	            if (objectProper != null) {
    	            	for (int i = 0; i < count; i++) {
    	            		iosDriver.findElement(objectProper).click();
    	            		wait(times);
    	                    System.out.println("当前点击的控件是==" + objectProper);
    	                    CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
    	            	}
    	            }
    	        } catch (Exception e) {
//    	            GetScreenshot(driver.findElement(objectProper));
    	            CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
    	        }
		}else if(os == 2){
			 WebDriverWait wait = new WebDriverWait(androidDriver, 20);
		        wait.until(ExpectedConditions.elementToBeClickable(objectProper));
		        try {

		            if (objectProper != null) {
		            	for (int i = 0; i < count; i++) {
		            		androidDriver.findElement(objectProper).click();
		            		wait(times);
		                    System.out.println("当前点击的控件是==" + objectProper);
		                    CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
		            	}
		            }
		        } catch (Exception e) {
//		            GetScreenshot(driver.findElement(objectProper));
		            CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
		        }
		}
    }
    
    /**
     * @param objectProper 需要传递的组合控件集合
     * @param wdType       谓词类型
     * @param label        谓词标签
     * @param property     属性value /name
     * @param getTxt
     * @return
     */
    public static void clickByLocator(WebElement objectProper,  int count, int times,String target) {
    	if (os == 1) {
//    		 WebDriverWait wait = new WebDriverWait(iosDriver, 20);
//    	        wait.until(ExpectedConditions.elementToBeClickable(objectProper));
    	        try {

    	            if (objectProper != null) {
    	            	for (int i = 0; i < count; i++) {
    	            		objectProper.click();
    	            		wait(times);
    	                    System.out.println("当前点击的控件是==" + objectProper);
    	                    CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
    	            	}
    	            }
    	        } catch (Exception e) {
//    	            GetScreenshot(driver.findElement(objectProper));
    	            CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
    	        }
		}else if(os == 2){
			 WebDriverWait wait = new WebDriverWait(androidDriver, 20);
		        wait.until(ExpectedConditions.elementToBeClickable(objectProper));
		        try {

		            if (objectProper != null) {
		            	for (int i = 0; i < count; i++) {
		            		objectProper.click();
		            		wait(times);
		                    System.out.println("当前点击的控件是==" + objectProper);
		                    CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
		            	}
		            }
		        } catch (Exception e) {
//		            GetScreenshot(driver.findElement(objectProper));
		            CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
		        }
		}
    }
    
  
    
    
    /**
     * 可变形式的参数校验存在 如果不存在则执行滑动操作 如果出异常再次滑动
     *
     * @param driver Driver 实例
     * @return str 入参可变参数2
     */
    public void checkvalueTxtPara(String... str) {
    	if (os == 1) {
    		for (int j = 0; j < str.length; j++) {
                try {
                    System.out.println("========" + CheckViewVisibilty(By.name(str[j])));
                    if (CheckViewVisibilty(By.name(str[j]))) {
                        System.out.println("界面校验点✅   " + str[j]);
                        System.out.println(ExpectedConditions.elementToBeClickable(By.name(str[j])));
                    } else {
                        System.out.println("======当前界面找不到 开始执行下一步操作======");
                        IOSElement webElement = (IOSElement) iosDriver.findElement(By.name(str[j]));
//                        webElement.click();
                        System.out.println("============");
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("==========当前界面无法找到开始执行上滑==========");
                    jsScroll("down");
                    System.out.println("==========当前界面无法找到开始执行上滑==========   " + " 第一级滑动----->   " + str[j]);
                }
            }
		}else if(os == 2){
			for (int j = 0; j < str.length; j++) {
	            try {
	                System.out.println("========" + CheckViewVisibilty(By.name(str[j])));
	                if (CheckViewVisibilty(By.name(str[j]))) {
	                    System.out.println("界面校验点✅   " + str[j]);
	                    System.out.println(ExpectedConditions.elementToBeClickable(By.name(str[j])));
	                } else {
	                    System.out.println("======当前界面找不到 开始执行下一步操作======");
	                    AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + str[j] + "\").instance(0))"));
//	                    webElement.click();
	                    System.out.println("============");
	                }
	            } catch (Exception e) {
	                // TODO: handle exception
	                System.out.println("==========当前界面无法找到开始执行上滑==========");
	                jsScroll("down");
	                System.out.println("==========当前界面无法找到开始执行上滑==========   " + " 第一级滑动----->   " + str[j]);
	            }
	        }
		}
    }
    
    
    public void checkShare() {
            findElementByName("微信");
            findElementByName("朋友圈");
            findElementByName("QQ");
            findElementByName("链接");
    }
    
    /**
     * @param NsPredicate 谓词定位方式传
     * @param x           X轴
     * @param y           Y轴
     * @param add         加坐标
     * @param minus       减坐标
     * @param target      备注操作目标控件名称
     */
    public void jsTapyDistance_X_Y(String Automator, int x, int y, String add, String minus, String target) {

//        IOSElement webElement = (IOSElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text('\" + NsPredicate + \"')"));
    	AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceIdMatches(\"" + Automator + "\").instance(0))"));
//    	AndroidElement webElement = (AndroidElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + Automator + "\").instance(0))"));
//    	driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"买车\").instance(0))")).click();
        
        
//    		new TouchAction((PerformsTouchActions) driver).press(webElement).waitAction(70).release().perform();
        System.out.println("X--->   " + String.valueOf(webElement.getCenter().getX()));
        System.out.println("Y--->   " + String.valueOf(webElement.getCenter().getY()));
        // mobile:tap

        if (add.equals("+") && minus.equals("-")) {
            System.out.println("当前表达式为---->     " + "  X轴  +" + "  Y轴  -");
            JavascriptExecutor js = (JavascriptExecutor) androidDriver;
            HashMap<String, String> tapObject = new HashMap<String, String>();
            tapObject.put("x", String.valueOf(webElement.getCenter().getX() + x));
            tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() - y)));

            System.out.println("x  " + String.valueOf(webElement.getCenter().getX() + x));
            System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() - y)));
            int X_=webElement.getCenter().getX() + x;
            int Y_=Math.abs(webElement.getCenter().getY() - y);
            androidDriver.tap(1, X_, Y_, 1);
//            System.out.println(webElement.getId());
//            tapObject.put("webElement", webElement.getId());
//            System.out.println("tapObject: " + tapObject.toString());
//            js.executeScript("mobile:tap", tapObject);
        }
        if (add.equals("-") && minus.equals("+")) {
            System.out.println("当前表达式为---->     " + "  X轴  -" + "  Y轴  +");
            JavascriptExecutor js = (JavascriptExecutor) androidDriver;
            HashMap<String, String> tapObject = new HashMap<String, String>();
            tapObject.put("x", String.valueOf(webElement.getCenter().getX() - x));
            tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() + y)));

            System.out.println("x  " + String.valueOf(webElement.getCenter().getX() - x));
            System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
            int X_=Math.abs(webElement.getCenter().getX() - x);
            int Y_=Math.abs(webElement.getCenter().getY() + y);
            androidDriver.tap(1, X_, Y_, 1);
//            System.out.println(webElement.getId());
//            tapObject.put("webElement", webElement.getId());
//            System.out.println("tapObject: " + tapObject.toString());
//            js.executeScript("mobile:tap", tapObject);
        }
        if (add.equals("+") && minus.equals("+")) {
            System.out.println("当前表达式为---->     " + "  X轴  +" + "  Y轴  +");
            JavascriptExecutor js = (JavascriptExecutor) androidDriver;
            HashMap<String, String> tapObject = new HashMap<String, String>();
            tapObject.put("x", String.valueOf(webElement.getCenter().getX() + x));
            tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
            
            System.out.println("x  " + String.valueOf(webElement.getCenter().getX() + x));
            System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
            int X_=Math.abs(webElement.getCenter().getX() + x);
            int Y_=Math.abs(webElement.getCenter().getY() + y);
            androidDriver.tap(1, X_, Y_, 1);
//            System.out.println(webElement.getId());
//            tapObject.put("webElement", webElement.getId());
//            System.out.println("tapObject: " + tapObject.toString());
//            js.executeScript("mobile:tap", tapObject);
        }
        if (add.equals("-") && minus.equals("-")) {
            System.out.println("当前表达式为---->     " + "  X轴  -" + "  Y轴  -");
            JavascriptExecutor js = (JavascriptExecutor) androidDriver;
            HashMap<String, String> tapObject = new HashMap<String, String>();
            tapObject.put("x", String.valueOf(Math.abs(webElement.getCenter().getX() - x)));
            tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() - y)));

            System.out.println("x  " + String.valueOf(Math.abs(webElement.getCenter().getX() - x)));
            int X_=Math.abs(webElement.getCenter().getX() - x);
            System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() - y)));
            int Y_=Math.abs(webElement.getCenter().getY() - y);
            System.out.println(webElement.getId());
            androidDriver.tap(1, X_, Y_, 1);
//            tapObject.put("webElement", webElement.getId());
//            System.out.println("tapObject: " + tapObject.toString());
//            js.executeScript("mobile:tap", tapObject);
        }
    }

    /**
     * @param NsPredicate 谓词定位方式传
     * @param x           X轴
     * @param y           Y轴
     * @param add         加坐标
     * @param minus       减坐标
     * @param target      备注操作目标控件名称
     * @param time        等待执行时间
     */
    public void jsTapyDistance_X_Y(final By by, int x, int y, String add, String minus, String target,int time) {

//    	AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceIdMatches(\"" + Automator + "\").instance(0))"));
    	AndroidElement webElement = (AndroidElement) androidDriver.findElement(by);
        
        System.out.println("X--->   " + String.valueOf(webElement.getCenter().getX()));
        System.out.println("Y--->   " + String.valueOf(webElement.getCenter().getY()));
        // mobile:tap

        if (add.equals("+") && minus.equals("-")) {
            System.out.println("当前表达式为---->     " + "  X轴  +" + "  Y轴  -");
            JavascriptExecutor js = (JavascriptExecutor) androidDriver;
            HashMap<String, String> tapObject = new HashMap<String, String>();
            tapObject.put("x", String.valueOf(webElement.getCenter().getX() + x));
            tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() - y)));

            System.out.println("x  " + String.valueOf(webElement.getCenter().getX() + x));
            System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() - y)));
            int X_=webElement.getCenter().getX() + x;
            int Y_=Math.abs(webElement.getCenter().getY() - y);
            androidDriver.tap(1, X_, Y_, 1);
            wait(time);
        }
        if (add.equals("-") && minus.equals("+")) {
            System.out.println("当前表达式为---->     " + "  X轴  -" + "  Y轴  +");
            JavascriptExecutor js = (JavascriptExecutor) androidDriver;
            HashMap<String, String> tapObject = new HashMap<String, String>();
            tapObject.put("x", String.valueOf(webElement.getCenter().getX() - x));
            tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() + y)));

            System.out.println("x  " + String.valueOf(webElement.getCenter().getX() - x));
            System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
            int X_=Math.abs(webElement.getCenter().getX() - x);
            int Y_=Math.abs(webElement.getCenter().getY() + y);
            androidDriver.tap(1, X_, Y_, 1);
            wait(time);
        }
        if (add.equals("+") && minus.equals("+")) {
            System.out.println("当前表达式为---->     " + "  X轴  +" + "  Y轴  +");
            JavascriptExecutor js = (JavascriptExecutor) androidDriver;
            HashMap<String, String> tapObject = new HashMap<String, String>();
            tapObject.put("x", String.valueOf(webElement.getCenter().getX() + x));
            tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
            
            System.out.println("x  " + String.valueOf(webElement.getCenter().getX() + x));
            System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
            int X_=Math.abs(webElement.getCenter().getX() + x);
            int Y_=Math.abs(webElement.getCenter().getY() + y);
            androidDriver.tap(1, X_, Y_, 1);
            wait(time);
        }
        if (add.equals("-") && minus.equals("-")) {
            System.out.println("当前表达式为---->     " + "  X轴  -" + "  Y轴  -");
            JavascriptExecutor js = (JavascriptExecutor) androidDriver;
            HashMap<String, String> tapObject = new HashMap<String, String>();
            tapObject.put("x", String.valueOf(Math.abs(webElement.getCenter().getX() - x)));
            tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() - y)));

            System.out.println("x  " + String.valueOf(Math.abs(webElement.getCenter().getX() - x)));
            int X_=Math.abs(webElement.getCenter().getX() - x);
            System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() - y)));
            int Y_=Math.abs(webElement.getCenter().getY() - y);
            System.out.println(webElement.getId());
            androidDriver.tap(1, X_, Y_, 1);
            wait(time);
        }
    }
    
    /**
     * 
     * @param regex     正则匹配方式
     * @param Automator 定位方式传入
     * @param x         X轴
     * @param y         Y轴
     * @param add         加坐标
     * @param minus       减坐标
     * @param target      备注操作目标控件名称
     */
    public void jsTapyDistance_X_Y_Matches(String regex,String Automator, int x, int y, String add, String minus, String target) {

    	if (regex.contains("resourceId")) {
    		AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceIdMatches(\"" + Automator + "\").instance(0))"));
    		matchXY(webElement, x, y, add, minus, target);
    		
    	}
    	if (regex.contains("text")) {
    		AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + Automator + "\").instance(0))"));
    		matchXY(webElement, x, y, add, minus, target);
    	}
    	if (regex.contains("description")) {
    		AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionMatches(\"" + Automator + "\").instance(0))"));
    		matchXY(webElement, x, y, add, minus, target);
    	}
    	if (regex.contains("className")) {
    		AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().classNameMatches(\"" + Automator + "\").instance(0))"));
    		matchXY(webElement, x, y, add, minus, target);
    	}
   }


    /**
     * 
     * @param regex     正则匹配方式
     * @param Automator 定位方式传入
     * @param x         X轴
     * @param y         Y轴
     * @param add         加坐标
     * @param minus       减坐标
     * @param target      备注操作目标控件名称
     * @param direction  方向
     */
    public void jsTapyDistance_X_Y_Matches(String regex,String Automator, int x, int y, String add, String minus, String target,String direction) {

    	if (regex.contains("resourceId")) {
    		AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceIdMatches(\"" + Automator + "\").instance(0))"));
    		matchXY(webElement, x, y, add, minus, target);
    	}
    	if (regex.contains("text")) {
    		
//    		AndroidElement webElement = (AndroidElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + Automator + "\").instance(0))"));
    		swipeUntilElement(Automator, direction, 15);
    		matchXY((AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + Automator + "\").instance(0))")), x, y, add, minus, target);
    	}
    	if (regex.contains("description")) {
    		AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionMatches(\"" + Automator + "\").instance(0))"));
    		matchXY(webElement, x, y, add, minus, target);
    	}
    	if (regex.contains("className")) {
    		AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().classNameMatches(\"" + Automator + "\").instance(0))"));
    		matchXY(webElement, x, y, add, minus, target);
    	}
 }
    
    
    
    
    public static void Scrotswip(String Data){
    	if (os == 1) {
    		int width=iosDriver.manage().window().getSize().width;
			//获取屏的高度
	        int height=iosDriver.manage().window().getSize().height;  
				 try {
					for (int i=0;i<10;i++) {
						        try {
									System.out.println("现在执行滑动的次数是"+i);
									if (Data.equals("上滑")) {
										Thread.sleep(500);
									    System.out.println("起始点："+width/5+";"+height*7/10+"终点==="+width/5+";"+height*3/10);
									    iosDriver.swipe(width/5,height*7/10, width/5,height*3/10, 1000);	
									}
									if (Data.equals("下滑")) {
										Thread.sleep(500);
										  System.out.println("起始点："+width/5+";"+height*3/10+"终点==="+width/5+";"+height*7/10);
										  iosDriver.swipe(width/5,height*3/10, width/5,height*7/10, 1000);	
									}
									  if (Data.equals("左滑")) {
										Thread.sleep(500);
										 System.out.println("起始点："+width*3/10+";"+height/2+"终点==="+width*7/10+";"+height/2);
										 iosDriver.swipe(width*3/10,height/2, width*7/10,height/2, 1000);
									  }
									if (Data.equals("右滑")) {
										Thread.sleep(500);
										System.out.println("起始点："+width*7/10+";"+height/2+"终点==="+width*3/10+";"+height/2);
										iosDriver.swipe(width*7/10,height/2, width*3/10,height/2, 1000);
									  }
//									       WebElement  element=element(driver,bytype,Object,"");
//									       element.click();
//									       Log.error("循环滑动到第"+i+"次操作正常，对象是===="+Object);
									       //如果滑动后能点击到控件就结束
									       break;
								} catch (Exception e) {
									System.out.println("在该区块仍末能找到对应控件继续循环");
								}
					 }
				} catch (Exception e) {
					System.out.println("循环执行10次仍末能找到控件抛异常");
//					Log.error("循环执行10次仍末能找到控件抛异常对象名称是=="+Object);
				}
		}else if (os == 2){
			int width=androidDriver.manage().window().getSize().width;
			//获取屏的高度
	        int height=androidDriver.manage().window().getSize().height;  
				 try {
					for (int i=0;i<10;i++) {
						        try {
									System.out.println("现在执行滑动的次数是"+i);
									if (Data.equals("上滑")) {
										Thread.sleep(500);
									    System.out.println("起始点："+width/5+";"+height*7/10+"终点==="+width/5+";"+height*3/10);
									    androidDriver.swipe(width/5,height*7/10, width/5,height*3/10, 1000);	
									}
									if (Data.equals("下滑")) {
										Thread.sleep(500);
										  System.out.println("起始点："+width/5+";"+height*3/10+"终点==="+width/5+";"+height*7/10);
										  androidDriver.swipe(width/5,height*3/10, width/5,height*7/10, 1000);	
									}
									  if (Data.equals("左滑")) {
										Thread.sleep(500);
										 System.out.println("起始点："+width*3/10+";"+height/2+"终点==="+width*7/10+";"+height/2);
										 androidDriver.swipe(width*3/10,height/2, width*7/10,height/2, 1000);
									  }
									if (Data.equals("右滑")) {
										Thread.sleep(500);
										System.out.println("起始点："+width*7/10+";"+height/2+"终点==="+width*3/10+";"+height/2);
										androidDriver.swipe(width*7/10,height/2, width*3/10,height/2, 1000);
									  }
//									       WebElement  element=element(driver,bytype,Object,"");
//									       element.click();
//									       Log.error("循环滑动到第"+i+"次操作正常，对象是===="+Object);
									       //如果滑动后能点击到控件就结束
									       break;
								} catch (Exception e) {
									System.out.println("在该区块仍末能找到对应控件继续循环");
								}
					 }
				} catch (Exception e) {
					System.out.println("循环执行10次仍末能找到控件抛异常");
//					Log.error("循环执行10次仍末能找到控件抛异常对象名称是=="+Object);
				}
		}
        }
    
    public static void Scrotswip(String Data,int count)   {
    	   for (int i = 0; i < count; i++) {
    		   Scrotswip(Data);
		}
    }
    
    public AndroidElement swipeUntilElement(String Automator,String direction,int findCount){
		//this.implicitlyWait(3);
		AndroidElement element=null;
		boolean flag=false;
		while(!flag&&findCount>0){
			try {
//				sliding("", "", "589,1561,575,1209", "", "");
				Scrotswip("上滑",1);
//				sliding("", "", "589,1561,575,1209", "", "");
//				Scrotswip("下滑",1);
				AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + Automator + "\").instance(0))"));
//				if (CheckViewVisibilty(By.name(Automator))) {
//					
//				}
				
				flag=true;
			} catch (Exception e) {
				// TODO: handle exception
//				sliding(direction);
//				sliding("", "", "589,1561,575,1209", "", "");
				Scrotswip("下滑");
				if (CheckViewVisibilty(By.id("com.uxin.usedcar:id/tvBack"))) {
					clickByCT(MobileBy.id("com.uxin.usedcar:id/tvBack"), 1, 2);
					findCount--;
				}
				findCount--;
			}
		}
		return element;
	}
    
    /**
     * 
     * @param regex     正则匹配方式
     * @param Automator 定位方式传入
     * @param x         X轴
     * @param y         Y轴
     * @param add         加坐标
     * @param minus       减坐标
     * @param target      备注操作目标控件名称
     */
    public void jsTapyDistance_X_Y_Matches(String regex,String Automator, int x, int y, String add, String minus, String target,boolean tap) {

    	if (regex.contains("resourceId")) {
    		AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceIdMatches(\"" + Automator + "\").instance(0))"));
    		  if (tap) {
    			  matchXY(webElement, x, y, add, minus, target);
		   }
     	}
    	if (regex.contains("text")) {
    		AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + Automator + "\").instance(0))"));
    		 if (tap) {
  			  matchXY(webElement, x, y, add, minus, target);
		   }
    	}
    	if (regex.contains("description")) {
    		AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionMatches(\"" + Automator + "\").instance(0))"));
    		 if (tap) {
    			  matchXY(webElement, x, y, add, minus, target);
  		   }
    	}
    	if (regex.contains("className")) {
    		AndroidElement webElement = (AndroidElement) androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().classNameMatches(\"" + Automator + "\").instance(0))"));
    		 if (tap) {
    			  matchXY(webElement, x, y, add, minus, target);
			}
		}
	}

    /**
     * 
     * @param webElement 继承的AndroidElement
     * @param x         X轴
     * @param y         Y轴
     * @param add
     * @param minus
     * @param target
     */
    public void matchXY(AndroidElement webElement, int x, int y, String add, String minus, String target) {
    	 System.out.println("X--->   " + String.valueOf(webElement.getCenter().getX()));
    	    System.out.println("Y--->   " + String.valueOf(webElement.getCenter().getY()));

    	    if (add.equals("+") && minus.equals("-")) {
    	        System.out.println("当前表达式为---->     " + "  X轴  +" + "  Y轴  -");
    	        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
    	        HashMap<String, String> tapObject = new HashMap<String, String>();
    	        tapObject.put("x", String.valueOf(webElement.getCenter().getX() + x));
    	        tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() - y)));

    	        System.out.println("x  " + String.valueOf(webElement.getCenter().getX() + x));
    	        System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() - y)));
    	        int X_=webElement.getCenter().getX() + x;
    	        int Y_=Math.abs(webElement.getCenter().getY() - y);
    	        androidDriver.tap(1, X_, Y_, 1);
//    	        System.out.println(webElement.getId());
//    	        tapObject.put("webElement", webElement.getId());
//    	        System.out.println("tapObject: " + tapObject.toString());
//    	        js.executeScript("mobile:tap", tapObject);
    	    }
    	    if (add.equals("-") && minus.equals("+")) {
    	        System.out.println("当前表达式为---->     " + "  X轴  -" + "  Y轴  +");
    	        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
    	        HashMap<String, String> tapObject = new HashMap<String, String>();
    	        tapObject.put("x", String.valueOf(webElement.getCenter().getX() - x));
    	        tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() + y)));

    	        System.out.println("x  " + String.valueOf(webElement.getCenter().getX() - x));
    	        System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
    	        int X_=Math.abs(webElement.getCenter().getX() - x);
    	        int Y_=Math.abs(webElement.getCenter().getY() + y);
    	        androidDriver.tap(1, X_, Y_, 1);
    	    }
    	    if (add.equals("+") && minus.equals("+")) {
    	        System.out.println("当前表达式为---->     " + "  X轴  +" + "  Y轴  +");
    	        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
    	        HashMap<String, String> tapObject = new HashMap<String, String>();
    	        tapObject.put("x", String.valueOf(webElement.getCenter().getX() + x));
    	        tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
    	        
    	        System.out.println("x  " + String.valueOf(webElement.getCenter().getX() + x));
    	        System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() + y)));
    	        int X_=Math.abs(webElement.getCenter().getX() + x);
    	        int Y_=Math.abs(webElement.getCenter().getY() + y);
    	        androidDriver.tap(1, X_, Y_, 1);
    	    }
    	    if (add.equals("-") && minus.equals("-")) {
    	        System.out.println("当前表达式为---->     " + "  X轴  -" + "  Y轴  -");
    	        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
    	        HashMap<String, String> tapObject = new HashMap<String, String>();
    	        tapObject.put("x", String.valueOf(Math.abs(webElement.getCenter().getX() - x)));
    	        tapObject.put("y", String.valueOf(Math.abs(webElement.getCenter().getY() - y)));

    	        System.out.println("x  " + String.valueOf(Math.abs(webElement.getCenter().getX() - x)));
    	        int X_=Math.abs(webElement.getCenter().getX() - x);
    	        System.out.println("y  " + String.valueOf(Math.abs(webElement.getCenter().getY() - y)));
    	        int Y_=Math.abs(webElement.getCenter().getY() - y);
    	        System.out.println(webElement.getId());
    	        androidDriver.tap(1, X_, Y_, 1);
    	    }
    	
    }
    
    /**
     * 根据方向滑动页面
     *
     * @param directionName up/down/left/right
     */
    public static void jsScroll(String directionName) {
        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", directionName);
        js.executeScript("mobile: scroll", scrollObject);
    }
	
	/**
	 *通过id查找元素
	 * @param driver 
	 * @param id
	 * @return
	 * @throws NoSuchElementException
	 */
	public static WebElement findElementById(String id){
		WebElement Element = null;
		if (os == 1) {
			Element= iosDriver.findElement(By.id(id));
			return Element;
		}else if (os == 2){
			Element= androidDriver.findElement(By.id(id));
			return Element;
		}
		
		return Element;
	}
	
	/**
	 *通过name查找元素
	 * @param driver
	 * @param name
	 * @return
	 * @throws NoSuchElementException
	 */
	public static WebElement findElementByName(String name){
		WebElement Element;
//		WebElement Element = driver.findElement(By.name(name));
		if (os == 1) {
			 Element = iosDriver.findElement(By.name(name));
		}else if (os == 2){
			Element = androidDriver.findElementByAndroidUIAutomator("text(\""+name+"\")");
			return Element;
		}
		return Element;
	}
	/**
	 * 通过xpath找到元素并点击
	 * @param driver
	 * @param xmlPath
	 */
	public static void clickElementByXpath(String xmlPath){
		if (os == 1) {
			iosDriver.findElement(By.xpath(xmlPath)).click();
		}else if (os == 2){
			androidDriver.findElement(By.xpath(xmlPath)).click();
		}
	}
	
	/**
	 * 通过id找到元素并点击
	 * @param driver
	 * @param id
	 */
	public static void clickElementById(String id){
		if (os == 1) {
			iosDriver.findElement(By.id(id)).click();
		}else if (os == 2){
			androidDriver.findElement(By.id("com.uxin.usedcar:id/"+id)).click();
		}
		
	}
	
	/**
	 * 通过name找到元素并点击
	 * @param driver
	 * @param name
	 */
	public static void clickElementByName(String name){
		findElementByName(name).click();
	}
	
	/**
	 * 控件点击
	 * @param driver       Driver 实例
	 * @param type         定位方式
	 * @param objectProper 元素属性
	 * @param data         测试参数化数据
	 * @param checkvalue   校验数据
	 * @throws IOException
	 */
	public static void click(String type,String objectProper, String objectProperParamer,String data, String string)
			{
		if (objectProper.contains("element") && !objectProperParamer.equals("")) {
			// 当属性字段中存在element 字符且data 有值，第一步先将属性值替换
			objectProper = objectProper.replace("element", objectProperParamer);
		}
		System.out.println("元素路径=="+objectProper);
		try {
			// 控件定位
			
			WebElement webElement = getWebelement(type, objectProper, objectProperParamer, data, string);
			if (webElement != null) {
				// 点击控件
				webElement.click();
				System.out.println("控件点击成功==");
				CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
			}
		} catch (Exception e) {
			// 异常截图
			GetScreenshot();
			CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
//			reports.log(LogStatus.FAIL, "点击失败");
		}
	}
	
	/**
	 * 
	 * @param type 传入定位方式 如id,name
	 * @param objectProper 传入定位属性
	 * @return   String type,String objectProper, String objectProperParamer,String data, String string
	 */
	public static WebElement getWebelement(String type,String objectProper, String objectProperParamer,String data, String string) {
		WebElement webElement = null;
		if (os == 1) {
			String locatorValue = objectProper;
			try {
				WebDriverWait wait=new WebDriverWait(iosDriver, 20);
				
				switch (type) {

				case "xpath":
					// 当检测到是androidDriver 时
					if (androidDriver instanceof AndroidDriver) {
						if (locatorValue.contains("//*[@text")) {
							String text = locatorValue.split("=")[1].replace("'", "")
									.replace("]", "").replace("\"", "");
							String uiautomatorExpress = "new UiSelector().text(\""
									+ text + "\")";
						} else if (locatorValue.contains("//*[contains(@text")) {
							String text = locatorValue.split(",")[1].replace("'", "")
									.replace("]", "").replace("\"", "")
									.replace(")", "");
							String uiautomatorExpress = "new UiSelector().textContains(\""
									+ text + "\")";
						} else {
							webElement = androidDriver.findElement(By.xpath(objectProper));
						}
						
					} else if (androidDriver instanceof WebDriver) {
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue))); 
					}else if (iosDriver instanceof IOSDriver){
						webElement = iosDriver.findElement(By.xpath(objectProper));
					}
					break;
				case "id":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
					break;
				case "cssSelector":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
					break;
				case "name":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
					break;
					//By.className(locatorValue)
				case "className":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
					break;
				case "linkText":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
					break;
				case "partialLinkText":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
					break;
				case "tagName":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
					break;
				default:
					webElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
					
					break;

				}
				
			} catch (Exception e) {
				webElement=null;
				// TODO: handle exception
			}
		}else if (os == 2){
			String locatorValue = objectProper;
			try {
				WebDriverWait wait=new WebDriverWait(androidDriver, 20);
				
				switch (type) {

				case "xpath":
					// 当检测到是androidDriver 时
					if (androidDriver instanceof AndroidDriver) {
						if (locatorValue.contains("//*[@text")) {
							String text = locatorValue.split("=")[1].replace("'", "")
									.replace("]", "").replace("\"", "");
							String uiautomatorExpress = "new UiSelector().text(\""
									+ text + "\")";
						} else if (locatorValue.contains("//*[contains(@text")) {
							String text = locatorValue.split(",")[1].replace("'", "")
									.replace("]", "").replace("\"", "")
									.replace(")", "");
							String uiautomatorExpress = "new UiSelector().textContains(\""
									+ text + "\")";
						} else {
							webElement = androidDriver.findElement(By.xpath(objectProper));
						}
						
					} else if (androidDriver instanceof WebDriver) {
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue))); 
					}
					break;
				case "id":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
					break;
				case "cssSelector":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
					break;
				case "name":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
					break;
					//By.className(locatorValue)
				case "className":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
					break;
				case "linkText":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
					break;
				case "partialLinkText":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
					break;
				case "tagName":
					webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
					break;
				default:
					webElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
					
					break;

				}
				
			} catch (Exception e) {
				webElement=null;
				// TODO: handle exception
			}
		}
		
		return webElement;
	}

	/**
	 * 截图方法，不需要自定义图片名称时使用
	 */
	public static void GetScreenshot(){
	GetScreenshot("");
	}
	/**
	 * 截图方法，必须传入caseName以区别图片
	 *@param String name图片名称
	 */
	public static void GetScreenshot(String caseName){
		if (os == 1){
			File screenShotFile = ((TakesScreenshot)iosDriver).getScreenshotAs(OutputType.FILE);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = sdf.format(new Date());
			//目录不存在时创建目录
			if (new File(CaseConfig.CATCH_PICTURE).isDirectory()) {
				System.out.println("创建文件夹");
				new File(CaseConfig.CATCH_PICTURE).mkdir();
			}
			System.out.println("准备复制");
			try {
				FileUtils.copyFile(screenShotFile, new File(CaseConfig.CATCH_PICTURE+File.separator+ caseName + "-" + date+ ".png"));
			} catch (IOException e) {
				failAndMessage("截图失败");
				e.printStackTrace();
			}
		}else if (os == 2){
			File screenShotFile = ((TakesScreenshot)androidDriver).getScreenshotAs(OutputType.FILE);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = sdf.format(new Date());
			//目录不存在时创建目录
			if (new File(CaseConfig.CATCH_PICTURE).isDirectory()) {
				System.out.println("创建文件夹");
				new File(CaseConfig.CATCH_PICTURE).mkdir();
			}
			System.out.println("准备复制");
			try {
				FileUtils.copyFile(screenShotFile, new File(CaseConfig.CATCH_PICTURE+File.separator+ caseName + "-" + date+ ".png"));
			} catch (IOException e) {
				failAndMessage("截图失败");
				e.printStackTrace();
			}
		}
		
	}
	//通过id输入
	public void inputById(String id,String data) {
		input("id", id, "", data, "");
	}
	
	//通过name输入
		public void inputByName(String name,String data) {
			input("id", name, "", data, "");
		}
		//通过xpath输入
		public void inputByXpath(String xpath,String data) {
			input("id", xpath, "", data, "");
		}
	
	/**
	 * 控件输入
	 * 
	 * @param driver
	 *            Driver 实例
	 * @param type
	 *            定位方式
	 * @param objectProper
	 *            元素属性
	 * @param data
	 *            测试参数化数据
	 * @param checkvalue
	 *            校验数据
	 * @throws IOException
	 */
	public static void input(String type,String objectProper,String objectProperParamer, String data, String checkvalue)
			{
		try {
			if (objectProper.contains("element") && !objectProperParamer.equals("")) {
				// 当属性字段中存在element 字符且data 有值，第一步先将属性值替换
				objectProper = objectProper.replace("element", objectProperParamer);
			}
			System.out.println("元素路径=="+objectProper);
			// 控件定位
			AndroidElement webElement = (AndroidElement) getWebelement(type, objectProper, objectProperParamer, data, checkvalue);
			
			 String txt=webElement.getText();
			 System.out.println(txt);
			 System.out.println(txt.length());
			 if(!(txt.length()==0)){
				 webElement.clear();
			 }
//				webElement.setValue(data);
			 	webElement.sendKeys(data);
				System.out.println("控件输入数据成功输入的数据是=="+data);
				CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
			
		} catch (Exception e) {
			// 异常截图
			GetScreenshot();
			CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
			e.printStackTrace();
		}
	}
	
	/**
	 * 滑动一屏，需传入方向
	 * @param data 参数可为：up，down，left，right
	 */
	public void  sliding(String data) {
	sliding("", "", "", data, "");
	}
	
	
	public void  sliding(String data,int count) {
		   for (int i = 0; i < count; i++) {
			   sliding("", "", "", data, "");
			
		   }
   }
	
	public void  sliding(String data,int count,int times) {
		   for (int i = 0; i < count; i++) {
			   sliding("", "", "", data, "");
			   wait(times);
		}
	}
	
	
	/**
	 * 控件滑动
	 * 
	 * @param driver
	 *            Driver 实例
	 * @param type
	 *            定位方式
	 * @param objectProper
	 *            元素属性
	 * @param data 传入up，down，left，right
	 * @param checkvalue
	 *            校验数据
	 * @throws IOException
	 */
	public static void sliding(String type,String objectProper,String objectProperParamer, String data, String checkvalue){
		if (os == 1) {
			// 获取屏的宽度
			int width = iosDriver.manage().window().getSize().width;
			// 获取屏的高度
			int height = iosDriver.manage().window().getSize().height;
			try {
				if (!objectProperParamer.equals("")) {
					int xcoordStart = (int) (Double.parseDouble(objectProperParamer.split(",")[0]));//589
					int ycoordStart = (int) (Double.parseDouble(objectProperParamer.split(",")[1]));//575  1561
					int xcoordEnd = (int) (Double.parseDouble(objectProperParamer.split(",")[2]));//1561    575
					int ycoordEnd = (int) (Double.parseDouble(objectProperParamer.split(",")[3]));//1209
					System.out.println("起点坐标==  " + xcoordStart + "," + ycoordStart+ "结束坐标===  " + xcoordEnd + "," + ycoordEnd);
					TouchAction action = new TouchAction(iosDriver);
//					action.press(589, 575).waitAction(100).moveTo(1561,1209).perform();
					action.press((int)(xcoordStart),(int)(ycoordStart)).waitAction(Duration.ofMillis(200)).moveTo((int)(xcoordEnd),(int)(ycoordEnd)).release().perform();
//					driver.swipe(xcoordStart, ycoordStart, xcoordEnd, ycoordEnd, 1000);
					CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;	
						}
						if (data.equals("up")) {
							System.out.println("上滑一屏，开始位置"+height*8/10+"结束位置"+height*1/10);
							iosDriver.swipe(width/2, height*8/10, width/2, height*1/10, 1000);
						}
						if (data.equals("down")) {
						    System.out.println("下滑一屏，开始位置"+height*1/10+"结束位置"+height*8/10);
						    iosDriver.swipe(width/2, height*1/10, width/2, height*8/10, 1000);
							}
						if (data.equals("left")) {
							System.out.println("左滑一屏，开始位置"+width*1/10+"结束位置"+width*8/10);
							iosDriver.swipe(width*1/10, height/2, width*8/10, height/2, 1000);
							}
						if (data.equals("right")) {
							System.out.println("右滑一屏，开始位置"+width*8/10+"结束位置"+width*1/10);
							iosDriver.swipe(width*8/10, height/2, width*1/10, height/2, 1000);
							}
						} catch (Exception e) {
							// 异常截图
							GetScreenshot();
							CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
						}
		}else if(os == 2){
			// 获取屏的宽度
			int width = androidDriver.manage().window().getSize().width;
			// 获取屏的高度
			int height = androidDriver.manage().window().getSize().height;

			try {
				if (!objectProperParamer.equals("")) {
					int xcoordStart = (int) (Double.parseDouble(objectProperParamer.split(",")[0]));//589
					int ycoordStart = (int) (Double.parseDouble(objectProperParamer.split(",")[1]));//575  1561
					int xcoordEnd = (int) (Double.parseDouble(objectProperParamer.split(",")[2]));//1561    575
					int ycoordEnd = (int) (Double.parseDouble(objectProperParamer.split(",")[3]));//1209
					System.out.println("起点坐标==  " + xcoordStart + "," + ycoordStart+ "结束坐标===  " + xcoordEnd + "," + ycoordEnd);
					TouchAction action = new TouchAction(androidDriver);
//					action.press(589, 575).waitAction(100).moveTo(1561,1209).perform();
					action.press((int)(xcoordStart),(int)(ycoordStart)).waitAction(Duration.ofMillis(200)).moveTo((int)(xcoordEnd),(int)(ycoordEnd)).release().perform();
//					driver.swipe(xcoordStart, ycoordStart, xcoordEnd, ycoordEnd, 1000);
					CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;	
				}
				if (data.equals("up")) {
					System.out.println("上滑一屏，开始位置"+height*8/10+"结束位置"+height*1/10);
					androidDriver.swipe(width/2, height*8/10, width/2, height*1/10, 1000);
				}
				if (data.equals("down")) {
					System.out.println("下滑一屏，开始位置"+height*1/10+"结束位置"+height*8/10);
					androidDriver.swipe(width/2, height*1/10, width/2, height*8/10, 1000);
				}
				
				if (data.equals("left")) {
					System.out.println("左滑一屏，开始位置"+width*1/10+"结束位置"+width*8/10);
					androidDriver.swipe(width*1/10, height/2, width*8/10, height/2, 1000);
				}
				if (data.equals("right")) {
					System.out.println("右滑一屏，开始位置"+width*8/10+"结束位置"+width*1/10);
					androidDriver.swipe(width*8/10, height/2, width*1/10, height/2, 1000);
				}
			} catch (Exception e) {
				// 异常截图
				GetScreenshot();
				CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
			}
		}
		

	}
	
	/**   点击控件如果点击不到就执行滑动操作
	 * 
	 * @param driver
	 * @param bytype
	 * @param object
	 * @param data
	 * @param checkvalue
	 *  
	 */
	public static void slidingTo(String type,String objectProper, String objectProperParamer,String data, String checkvalue)   {
		if (os == 1) {
			//获取屏的宽度
			int width=iosDriver.manage().window().getSize().width;
			//获取屏的高度
			int height=iosDriver.manage().window().getSize().height; 
			try {
				WebElement we = null;
				iosDriver.swipe(width/5,height*9/10, width/5,height/10, 1000);
				iosDriver.swipe(width/5,height*9/10, width/5,height/10, 1000);
				iosDriver.swipe(width/5,height*9/10, width/5,height/10, 1000);
//				clickElementByName("1.在优信能卖到理想价位吗？");
//				we = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='']");
				
				}
	      catch (Exception e) {
			
				for (int i=0;i<20;i++){
					System.out.print("滑动的次数"+i);
			   
				if (checkvalue.equals("左滑")) {
					iosDriver.swipe(width*9/10,height/2, width*2/10,height/2, 1000);//从宽度的9/10 滑动到1/10
				}
				if (checkvalue.equals("右滑")) {
					iosDriver.swipe(width*2/10,height/2, width*9/10,height/2, 1000);//从宽度的1/10 滑动到9/10
				}
				if (checkvalue.equals("上滑")) {
					iosDriver.swipe(width/5,height*9/10, width/5,height/10, 1000);	//从高度的9/10 滑动到1/10
					System.out.println("---------------Debug2 上滑---------------------");
				}
				if (checkvalue.equals("下滑")) {
					iosDriver.swipe(width/5,height/10, width/5,height*9/10, 1000);	//从高度的1/10 滑动到9/10
				}
				}
			}
		}else if (os == 2){
			//获取屏的宽度
			int width=androidDriver.manage().window().getSize().width;
			//获取屏的高度
			int height=androidDriver.manage().window().getSize().height; 
			try {
				WebElement we = null;
				androidDriver.swipe(width/5,height*9/10, width/5,height/10, 1000);
				androidDriver.swipe(width/5,height*9/10, width/5,height/10, 1000);
				androidDriver.swipe(width/5,height*9/10, width/5,height/10, 1000);
//				clickElementByName("1.在优信能卖到理想价位吗？");
//				we = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='']");
				
				}
	      catch (Exception e) {
			
				for (int i=0;i<20;i++){
					System.out.print("滑动的次数"+i);
			   
				if (checkvalue.equals("左滑")) {
					androidDriver.swipe(width*9/10,height/2, width*2/10,height/2, 1000);//从宽度的9/10 滑动到1/10
				}
				if (checkvalue.equals("右滑")) {
					androidDriver.swipe(width*2/10,height/2, width*9/10,height/2, 1000);//从宽度的1/10 滑动到9/10
				}
				if (checkvalue.equals("上滑")) {
					androidDriver.swipe(width/5,height*9/10, width/5,height/10, 1000);	//从高度的9/10 滑动到1/10
					System.out.println("---------------Debug2 上滑---------------------");
				}
				if (checkvalue.equals("下滑")) {
					androidDriver.swipe(width/5,height/10, width/5,height*9/10, 1000);	//从高度的1/10 滑动到9/10
				}
				
				}
			}
		}
}
	
	/**
	 * 在指定元素内滑动，需传入webElement，及滑动方向
	 * @param webElement 传入webEelement
	 * @param direction 传入方向值up,down,left,right
	 */
	public static void slidingInElement(WebElement webElement,String direction){
		//获取控件开始位置
		Point location = webElement.getLocation();
		int startX = location.getX();
		int startY = location.getY();
		System.out.println("控件开始X："+startX+"控件开始Y："+startY);
		//获取坐标轴差
		 Dimension q = webElement.getSize();
	     int x = q.getWidth();
	     int y = q.getHeight();
	     System.out.println("坐标差值X："+x+"坐标差值Y："+y);
	     // 计算出控件结束坐标
	     int endX = x + startX;
	     int endY = y + startY;
	     System.out.println("控件结束坐标X："+endX+"控件结束坐标Y："+endY);
	     if (os == 1) {
	    	 try {
					if (direction.equals("down")) {
						System.out.println("向上滑动，开始像素值"+startY+"滑动到"+endY*9/10);
						iosDriver.swipe(endX/2, startY, endX/2, endY*9/10, 1000);
					}
					if (direction.equals("up")) {
						System.out.println("向下滑动，开始像素值"+endY*9/10+"滑动到"+startY);
						iosDriver.swipe(endX/2, endY*9/10, endX/2, startY, 1000);
					}
					
					if (direction.equals("left")) {
						System.out.println("向左滑动，开始像素值"+endX*9/10+"滑动到"+endX*1/10);
						iosDriver.swipe(endX*9/10, endY-30, endX*1/10, endY-30, 1000);
					}
					if (direction.equals("right")) {
						System.out.println("向右滑动，开始像素值"+endX*1/10+"滑动到"+endX*9/10);
						iosDriver.swipe(endX*1/10, endY/2, endX*9/10, endY/2, 1000);
					}
				} catch (Exception e) {
					// 异常截图
					GetScreenshot();
					CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
				}
		}else if (os == 2){
			try {
				if (direction.equals("down")) {
					System.out.println("向上滑动，开始像素值"+startY+"滑动到"+endY*9/10);
					androidDriver.swipe(endX/2, startY, endX/2, endY*9/10, 1000);
				}
				if (direction.equals("up")) {
					System.out.println("向下滑动，开始像素值"+endY*9/10+"滑动到"+startY);
					androidDriver.swipe(endX/2, endY*9/10, endX/2, startY, 1000);
				}
				
				if (direction.equals("left")) {
					System.out.println("向左滑动，开始像素值"+endX*9/10+"滑动到"+endX*1/10);
					androidDriver.swipe(endX*9/10, endY-30, endX*1/10, endY-30, 1000);
				}
				if (direction.equals("right")) {
					System.out.println("向右滑动，开始像素值"+endX*1/10+"滑动到"+endX*9/10);
					androidDriver.swipe(endX*1/10, endY/2, endX*9/10, endY/2, 1000);
				}
			} catch (Exception e) {
				// 异常截图
				GetScreenshot();
				CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
			}
		}
	}
//	
//	/**
//	 * 适配卖车精确滑动
//	 */
//	public static void slidingInElement1(WebElement webElement,String direction){
//		//获取控件开始位置
//		Point location = webElement.getLocation();
//		int startX = location.getX();
//		int startY = location.getY();
//		System.out.println("控件开始X："+startX+"控件开始Y："+startY);
//		//获取坐标轴差
//		 Dimension q = webElement.getSize();
//	     int x = q.getWidth();
//	     int y = q.getHeight();
//	     System.out.println("坐标差值X："+x+"坐标差值Y："+y);
//	     // 计算出控件结束坐标
//	     int endX = x + startX;
//	     int endY = y + startY;
//	     System.out.println("控件结束坐标X："+endX+"控件结束坐标Y："+endY);
//		try {
//			if (direction.equals("down")) {
//				System.out.println("向上滑动，开始像素值"+startY+"滑动到"+endY*9/10);
//				driver.swipe(endX/2, startY*5/10, endX/2, endY*6/10, 1000);
//			}
//			if (direction.equals("up")) {
//				System.out.println("向下滑动，开始像素值"+endY*9/10+"滑动到"+startY);
//				driver.swipe(endX/2, endY*9/10, endX/2, startY, 1000);
//			}
//			
//			if (direction.equals("left")) {
//				System.out.println("向左滑动，开始像素值"+endX*9/10+"滑动到"+endX*1/10);
//				driver.swipe(endX*9/10, endY-30, endX*1/10, endY-30, 1000);
//			}
//			if (direction.equals("right")) {
//				System.out.println("向右滑动，开始像素值"+endX*1/10+"滑动到"+endX*9/10);
//				driver.swipe(endX*1/10, endY/2, endX*9/10, endY/2, 1000);
//			}
//		} catch (Exception e) {
//			// 异常截图
//			GetScreenshot();
//			CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
//		}
//
//	}
	/** 读取当前状态 */
	public static String ReadCurrentState(String key, String defaultvalue) {
		ArrayList<String> readstrings = ReadFileByLine(CURRENT_STATE);
		// 获取所有状态信息中包含key的信息
		ArrayList<String> existkeystrings = new ArrayList<String>();
		for (String string : readstrings) {
			if (string.contains("#key" + key)) {
				existkeystrings.add(string);
			}
		}
		// 获取所有包含key的信息中最新的
		String tempString = defaultvalue;
		Long temptimestamp = 0L;
		for (String string : existkeystrings) {
//			System.out.println("值为:"+string);
			try {
				Long timestamp = Long.parseLong(string.substring(string
						.indexOf("#timestamp") + 10));
				if (timestamp > temptimestamp) {
					temptimestamp = timestamp;
					tempString = string;
//					System.out.println("tp:"+tempString);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if (tempString.equals(defaultvalue)) {
//			System.out.println("等于时："+tempString);
			return tempString;
		} else {
//			System.out.println("不等于时："+tempString.substring(tempString.indexOf("#value") + 6,
//					tempString.indexOf("#timestamp")));
			return tempString.substring(tempString.indexOf("#value") + 6,
					tempString.indexOf("#timestamp"));
		}
	}
	
	//失败提示
		public static void failAndMessage(String msg){
			Assert.assertTrue(false,msg);
		}
		/**
		 * 等待N秒
		 * @param num
		 */
		public static void wait(int num){
			try {
				Thread.sleep(num * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void sleep(int num){
			try {
				Thread.sleep(num);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		/**
//		 * 列表页下拉刷新
//		 */
//		public static void Check_Pull_Refresh_List() {
//			driver.switchTo();
//		}
		/**
		 * 判断控件是否存在并返回控件
		 * @param by
		 * @param waitTime
		 * @return
		 */
		public static WebElement waitForVisible(final By by, int waitTime) {
			Date start = new Date();
			WebDriverWait wait;
			if (os == 1){
				wait = new WebDriverWait(iosDriver, waitTime);
				for (int attempt = 0; attempt < waitTime; attempt++) {
					try {
						iosDriver.findElement(by);
						break;
						} catch (Exception e) {
							iosDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
							}
					}
				Date end = new Date();
				System.out.println("耗时:"+(end.getTime()-start.getTime())+"ms");
				return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			}else if(os == 2){
				wait = new WebDriverWait(androidDriver, waitTime);
				for (int attempt = 0; attempt < waitTime; attempt++) {
					try {
						androidDriver.findElement(by);
						break;
						} catch (Exception e) {
							androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
							}
					}
				Date end = new Date();
				System.out.println("耗时:"+(end.getTime()-start.getTime())+"ms");
				return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			}
			return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			}
		/**
		 * 判断控件是否存在，并返回boolean值
		 * @param by
		 * @return boolean返回按钮存在与否的状态，存在时返回ture，不存在是返回false
		 */
		public static boolean CheckViewVisibilty(final By by) {
			if (os == 1) {
				Date start = new Date();
				WebElement wb = null;
				WebDriverWait wait = new WebDriverWait(iosDriver, 3);
				for (int attempt = 0; attempt < 3; attempt++) {
						try {
							wb= iosDriver.findElement(by);
							break;
							} catch (Exception e) {
								iosDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
								return false;
								}
				}
				if(wb != null){
					Date end = new Date();
					System.out.println("耗时:"+(end.getTime()-start.getTime())+"ms");
					return true;
				}else {
					Date end = new Date();
					System.out.println("耗时:"+(end.getTime()-start.getTime())+"ms");
					return false;
				}
			}else if (os == 2){
				Date start = new Date();
				WebElement wb = null;
				WebDriverWait wait = new WebDriverWait(androidDriver, 3);
				for (int attempt = 0; attempt < 3; attempt++) {
						try {
							wb= androidDriver.findElement(by);
							break;
							} catch (Exception e) {
								androidDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
								return false;
								}
				}
				if(wb != null){
					Date end = new Date();
					System.out.println("耗时:"+(end.getTime()-start.getTime())+"ms");
					return true;
				}else {
					Date end = new Date();
					System.out.println("耗时:"+(end.getTime()-start.getTime())+"ms");
					return false;
				}
			}
			return false;
			
		}
		
		/**
		 * 点击坐标
		 * @param x
		 * @param y
		 * @param duration
		 */
		public static void clickScreen(int x, int y, int duration) {
			if (os == 1) {
				iosDriver.tap(1, x, y, duration);
			}else if (os == 2){
				//适用于appium 1.4.16版本
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//				HashMap tapObject = new HashMap();
//				tapObject.put("x", x);
//				tapObject.put("y", y);
//				tapObject.put("duration", duration);
//				js.executeScript("mobile: tap", tapObject);
			    androidDriver.tap(1, x, y, duration);
			}
		}
//		
//		/**
//		 * 从买车列表进入详情页,城市为全国直购城市时可以使用
//		 * @param i 进入第几个详情页
//		 */
//		public void toDetail(int i){
//			System.out.println("进入第"+i+"个详情页");
//				String listTitle =getTextByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]"
//						+ "/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]"
//						+ "/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]"
//						+ "/android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]"
//						+ "/android.widget.FrameLayout[1]/android.support.v7.widget.RecyclerView[1]"
//						+ "/android.widget.FrameLayout["+i+"]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]"
//						+ "/android.widget.TextView[1]"); //列表页要点击的item的title文案
//				findElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]"
//						+ "/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]"
//						+ "/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]"
//						+ "/android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]"
//						+ "/android.widget.FrameLayout[1]/android.support.v7.widget.RecyclerView[1]"
//						+ "/android.widget.FrameLayout["+i+"]").click();
//				wait(3);
//				String detailTitle = getTextById("tvVehicleDetailsCarName");
//				System.out.println("list:"+listTitle+"***************"+"detail:"+detailTitle);
//				//.trim()是为了去掉字符串首尾的空格
//				if (listTitle.trim().equals(detailTitle.trim()) || detailTitle.trim().contains(listTitle.trim())) {
//					
//				}else {
//					failAndMessage("未进入指定详情页");
//				}
//		}
//		
		/**
		 * 根据xpath获取元素文本
		 * 
		 * @param xpath
		 * @return 文本
		 */
		public String getTextByXpath(String xpath) {
			String text = null;
			if (os == 1) {
				try {
					WebDriverWait wait=new WebDriverWait(iosDriver, 20);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
					text = iosDriver.findElement(By.xpath(xpath)).getText();
					System.out.println("当前返回的文本--->    "+text);
					return text;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}else if(os == 2){
				try {
					WebDriverWait wait=new WebDriverWait(androidDriver, 20);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
					text = androidDriver.findElement(By.xpath(xpath)).getText();
					System.out.println("当前返回的文本--->    "+text);
					return text;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
			return text;
		}
		
		/**
		 * 根据id获取元素文本
		 * 
		 * @param id
		 * @return 文本
		 */
		public String getTextById(String id) {
			String text = null;
			if (os == 1) {
				try {
					WebDriverWait wait=new WebDriverWait(iosDriver, 20);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
					text = iosDriver.findElement(By.id(id)).getText();
					System.out.println("当前返回的文本--->    "+text);
					return text;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}else if (os == 2) {
				try {
					WebDriverWait wait=new WebDriverWait(androidDriver, 20);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
					text = androidDriver.findElement(By.id(id)).getText();
					System.out.println("当前返回的文本--->    "+text);
					return text;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
			return text;
		}
//		
//		/**
//		 * 检查详情页titlebar显示
//		 */
//		public void checkTitlebar_Detail() {
//			if (CheckViewVisibilty(By.id("relLayTopSearchBar"))) {
//	            if(CheckViewVisibilty(By.id("rlReserve"))) {  //带有预约看车的新的详情页        
//	            	System.out.println("进入带有预约看车的新的详情页");
//	            	findElementById("rlCollect").click();//操作收藏   
//					wait(1);
//					findElementById("rlCollect").click();//取消收藏
//					wait(1);
//					findElementById("rlOnlineService");//在线客服
//					findElementById("rlDianHua"); //免费电话
//					findElementById("rlBargin");  //电话咨询
//					findElementById("llSecond");  //预约看车
//	            }else {//不带有预约看车的老详情页
//	            	System.out.println("进入不带预约看车的老的详情页");
////					findElementById("llShouCang").click();//操作收藏
////					wait(1);
////					findElementById("llShouCang").click();//取消收藏
//					findElementById("rlShouCang").click();//操作收藏    9.1版本收藏id更新为rlShouCang
//					wait(1);
//					findElementById("rlShouCang").click();//取消收藏
//					wait(1);
//				}
//				findElementById("imgBtBack");//返回按钮
//				findElementById("ivCompare");//PK按钮
//
//				findElementById("ivShare").click();//分享按钮
//				Assert.assertEquals("微信显示不正常", "微信", getTextByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]"
//						+ "/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]"
//						+ "/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]"));
//				Assert.assertEquals("朋友圈显示不正常", "朋友圈", getTextByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]"
//						+ "/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]"
//						+ "/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]"));
//				Assert.assertEquals("QQ显示不正常", "QQ", getTextByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]"
//						+ "/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]"
//						+ "/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.TextView[1]"));
//				Assert.assertEquals("链接显示不正常", "链接", getTextByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]"
//						+ "/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]"
//						+ "/android.widget.LinearLayout[1]/android.widget.LinearLayout[4]/android.widget.TextView[1]"));
//				findElementById("share_cancel").click();
//			}else {
//				failAndMessage("详情页title未正常展示");
//			}
//		}
//		
//		/**
//		 * 检查webview的titlebar显示
//		 */
//		public void checkTitlebar_Webview(String titleString){
//			for (int i = 0; i < 29; i++) {
//				if (findElementById("tvTitle").getText().equals(titleString)) {
//					break;
//				}else if(i == 28){
//					GetScreenshot();
//					failAndMessage("title文案"+titleString+"15s没有正常展示");
//				}
//			}
//		}
//		
//	/**
//	 * 获取当前页面信息并储存
//	 */
//		public void getPageSourseToSave(){
//			String temp = driver.getPageSource();
//			BufferedWriter bw = null;
//			try {
//				FileWriter fw = new FileWriter(new File("./CaseRunTempFiles/PageSourse.xml"),false);
//				bw = new BufferedWriter(fw);
//				bw.write(temp+"\n");
//				bw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		/**
//		 * 检查返回按钮+文案类型的titlebar
//		 * @param titleString 标题文案
//		 */
//public void checkTitlebar1(String titleString) {
//	for (int i = 0; i < 29; i++) {
//		if (CheckViewVisibilty(By.id("imgBtBack"))&&
//				findElementById("tvTitle").getText().equals(titleString)) {
//			break;
//		}else if (i==28) {
//			GetScreenshot("checktltlebar1");
//			failAndMessage("title文案显示不正常，应为："+titleString+",实际为："+findElementById("tvTitle").getText());
//		}
//	}
//}
//
///**
// * 检查返回按钮+文案类型+button的titlebar
// * @param titleString 标题文案
// * @param buttonString 按钮文案
// */
//public void checkTitlebar2(String titleString,String buttonString) {
//for (int i = 0; i < 29; i++) {
//if (CheckViewVisibilty(By.id("imgBtBack"))&&
//		findElementById("tvTitle").getText().equals(titleString)
//		&&findElementById("btManage").getText().equals(buttonString)) {
//	break;
//}else if (i==28) {
//	GetScreenshot("checktltlebar1");
//	failAndMessage("title文案显示不正常，应为："+titleString+",实际为："
//	+findElementById("tvTitle").getText()+"或button文案显示错误应为"+buttonString+findElementById("btManage").getText());
//}
//}
//}
	/**
	 * 点击车市列表中的付一半悬浮按钮,需要先进入买车页
	 */
public void ClickOnFYB(){
	wait(2);
	int windowX = androidDriver.manage().window().getSize().width;
	int windowY = androidDriver.manage().window().getSize().height;
	int startY = findElementById("market_uiswitch").getLocation().y;
	int n = findElementById("market_uiswitch").getSize().getWidth()/2;//按钮X中间值
	int m = findElementById("market_uiswitch").getSize().getHeight()/2;//按钮Y中间坐标值
	int x = windowX/2+n/4;
	int y = startY+m;
	System.out.println("屏幕大小"+androidDriver.manage().window().getSize());
	System.out.println("控件大小"+findElementById("market_uiswitch").getSize());
	System.out.println("x "+x+"y "+y);
	androidDriver.tap(1, x, y, 1);
}
//
///**
// * 从native切换到webview，需要在debug包中使用
// */
//public void switchToWebView(){
//	Set<String> contextNames = driver.getContextHandles();
//	System.out.println(contextNames);
//	for (String contextName : contextNames) {
//		System.out.println("contextName是:"+contextName);
//		if (contextName.contains("WEBVIEW")){
//				try {
//					driver.context(contextName);
//				} catch (Exception e) {
//					// TODO: handle exception
//					System.out.println("使用switchto切换");
//					driver.switchTo().window(contextName);
//					e.printStackTrace();
//			}
//		}else
//		{
//		System.out.println("不是WEBVIEW"); 
//		}
//		}//测试切换到webview
//	}
//
///**
// * 从webview切换到native，需要在debug包中使用
// **/
//public void switchToNative(){
//	Set<String> contextNames = driver.getContextHandles();
//	System.out.println(contextNames);
//	for (String contextName : contextNames) {
//		System.out.println("contextName是:"+contextName);
//		if (contextName.contains("NATIVE_")){
//			try {
//				driver.context(contextName);
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//				System.out.println("走switchto");
//				driver.switchTo().window(contextName);
//			}
//		}else{
//		System.out.println("不是NATIVE"); 
//		}
//		}//测试切换到native
//}
//
///**
// * 列表页切换城市
// */
//public void switchCity(String name) {
//	clickElementById("btChooseCity");
//	wait(3);
//	if (name.equals("京津冀")||name.equals("江浙沪")
//			||name.equals("云贵川")||name.equals("guang")) {
//		clickElementByName(name);
//		clickElementByName("不限");
//	}else if (name.contains("市")) {
//		switch(name){
//			case "天津市" :
//				clickElementByName("京津冀");
//				clickElementByName(name);
//				break;
//			case "河北省" :
//				clickElementByName("京津冀");
//				clickElementByName(name);
//				break;
//			case "江苏省" :
//				clickElementByName("江浙沪");
//				clickElementByName(name);
//				break;
//			case "浙江省" :
//				clickElementByName("江浙沪");
//				clickElementByName(name);
//				break;
//			case "上海市" :
//				clickElementByName("江浙沪");
//				clickElementByName(name);
//				break;
//				
//		}
//	}else {
//		clickElementByName(name);
////		scrollToElementClick(name, 3);
//	}
//  }
//
//public String scrollToElementClick(String text,int times) {
//    WebElement element = driver.findElementByName(text);
//    try {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        HashMap<String, String> scrollObject = new HashMap<>();
//        scrollObject.put("direction", "down");
//        //value 为false时候wdLabel
//        scrollObject.put("element", ((RemoteWebElement) element).getId());
////        scrollObject.put("predicateString", "value == "+ text);
////        scrollObject.put("predicateString", "wdLabel == '" + text + "'");
//        js.executeScript("mobile:scroll", scrollObject);
//        element.click();
//        wait(times);
//        return element.getText();//blank
//    } catch (Exception e) {
//       e.printStackTrace();
//       return element.getText();
//    }
//}
//
////获取车辆档案中上牌时间，并格式为2017-08
//		public  String getCarDate(){
//			String nian = findElementById("tvVehicleDetailsRegistDate").getText().substring(0, 4);
//			String yue = findElementById("tvVehicleDetailsRegistDate").getText().substring(6, 8);
//			String date =nian+"-"+yue;
//			System.out.println(date);
//			return date;
//		}
//		/**
//		 * @param toast
//		 */
//		public static void toastCheck(String toast) {
//			try {
//				final WebDriverWait wait = new WebDriverWait(driver, 2);
//				Assert.assertNotNull(wait.until(
//						ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[contains(@text," + toast + ")]"))));
//				System.out.println("找到了toast:" + toast);
//			} catch (Exception e) {
//				throw new AssertionError("找不到：" + toast);
//			}
//		}
//		
//		/**
//		 * 控件比对，chekcLabel  区别于 chekcValue  通过data控制获取文本的方式 因为有些控件没有value只有name
//		 * @param driver       Driver 实例
//		 * @param type         定位方式
//		 * @param objectProper 元素属性(如id，xpath，name)
//		 * @param objectProperParamer 当选择xpath定位时最后一位可参数
//		 * @param attribute    需要检查的元素属性
//		 * @param checkvalue   校验数据
//		 * 
//		 */
//		public static void chekcLabel(String type,String objectProper,String objectProperParamer, String attribute, String checkvalue){
//			if (objectProper.contains("element") && !objectProperParamer.equals("")) {
//				// 当属性字段中存在element 字符且data 有值，第一步先将属性值替换
//				objectProper = objectProper.replace("element", objectProperParamer);
//			}
//			System.out.println("元素路径=="+objectProper);
//			// 控件定位
//			WebElement webElement = getWebelement(type, objectProper, objectProperParamer, attribute, checkvalue);
//			try {
//				if (webElement != null) {
//					// 点击控件
//					String checkText = webElement.getAttribute(attribute).trim();
//					Thread.sleep(2000);
//	                System.out.println("--------------打印ValueAttribute--------------------"+checkText);
//					if (checkText.equals(checkvalue.trim())) {
//						// 预期结果和实际结果相同
//						System.out.println("--->   "+checkText+"   等价于"+"   "+checkvalue.trim());
//						CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
//					} else {
//						// 预期结果和实际结果不同
//						CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
//						// 异常截图
//						GetScreenshot();
//						failAndMessage("预期值与实际值不符，预期为:"+checkvalue+"实际为"+checkText);
//					}
//				}
//			} catch (Exception e) {
//				CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
//				// 异常截图
//				GetScreenshot();
//				failAndMessage("指定控件不存在");
//			}
//		}
//		
//		/**
//		 * 控件比对，chekcLabel  区别于 chekcValue  通过data控制获取文本的方式 因为有些控件没有value只有name
//		 * @param driver       Driver 实例
//		 * @param type         定位方式
//		 * @param objectProper 元素属性
//		 * @param data         测试参数化数据
//		 * @param checkvalue   校验数据
//		 * 
//		 */
//		public static void chekcLabel_ex(String type,String objectProper,String objectProperParamer, String data, String checkvalue){
//			if (objectProper.contains("element") && !objectProperParamer.equals("")) {
//				// 当属性字段中存在element 字符且data 有值，第一步先将属性值替换
//				objectProper = objectProper.replace("element", objectProperParamer);
//			}
//			System.out.println("元素路径=="+objectProper);
//			// 控件定位
//			WebElement webElement = getWebelement(type, objectProper, objectProperParamer, data, checkvalue);
//			try {
//				if (webElement != null) {
//					// 点击控件
//					if(webElement.getText().trim() != null){
//						String checkText = webElement.getText().trim();
//						Thread.sleep(1000);
//		                System.out.println("--------------打印ValueAttribute--------------------"+checkText);
//						if (checkText.equals(checkvalue.trim())) {
//							// 预期结果和实际结果相同
//							System.out.println("--->   "+checkText+"   等价于"+"   "+checkvalue.trim());
//							CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
//						} else {
//							// 预期结果和实际结果不同
//							CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
//							// 异常截图
//							GetScreenshot();
//							failAndMessage("预期值与实际值不符，预期为:"+checkvalue+"实际为"+checkText);
//						}
//					}
//					
//
//				}
//			} catch (Exception e) {
//				CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
//				// 异常截图
//				GetScreenshot();
//				failAndMessage("指定控件不存在");
//			}
//		}
//		
//		/**
//		 * 检查店铺详情页titlebar显示
//		 */
//		public void checkTitleBarShop() {
//			for (int i = 0; i < 30; i++) {
//				if (CheckViewVisibilty(By.id("imgBtBack"))&&CheckViewVisibilty(By.id("ivShare"))
//						&&waitForVisible(By.id("tvTitle"), 3).getText().equals("店铺详情")) {
//					break;
//				}else if(i==29){
//					GetScreenshot();
//					failAndMessage("店铺详情页titlebar显示异常");
//				}
//			}
//		}
//		
//		/**
//		 * 某方向滑动直到边界，如底部，顶部(在没有边界标识的时候使用)
//		 * @param direction
//		 * @param strSrc
//		 * @param strDes
//		 * @return
//		 * 实现滑动前后的字符串集合list的对比
//		 */
//		public void swipeUntilBoundary(String direction,By by){
//			boolean flag=false;
//			while(!flag){
//				List<String> strSrc=new ArrayList<String>();
//				List<String> strDes=new ArrayList<String>();
//				//滑动前定位元素并将元素的text添加到集合strSrc里
//				List<AndroidElement> elementOld=driver.findElements(by);
//				for(AndroidElement ae:elementOld){
//					strSrc.add(ae.getText());
//				}
//				sliding(direction);
//				this.wait(1000);
//				//滑动后定位元素并将元素的text添加到集合strSrc里
//				List<AndroidElement> elementNew=driver.findElements(by);
//				for(AndroidElement ae:elementNew){
//					strDes.add(ae.getText());
//				}
//				flag=this.listStrEquals(strSrc,strDes);
//			}
//		}
//		
//		/**
//		 * 判断两个字符串集合list是否元素对应相等
//		 * @param strSrc
//		 * @param strDes
//		 * @return
//		 */
//		public static Boolean listStrEquals(List<String> strSrc,List<String> strDes){
//			Boolean flag=false;
//			if((!strSrc.isEmpty()&&strSrc!=null)&&(!strDes.isEmpty()&&strDes!=null)){
//				if(strSrc.size()==strDes.size()){
//					for(int i=0;i<strDes.size();i++){
//						if(strSrc.get(i).equals(strDes.get(i))){
//							flag=true;
//							continue;
//						}else{
//							flag=false;
//							//System.out.println(strSrc.get(i)+" "+strDes.get(i));
//							break;
//						}
//					}
//				}else{
//					System.out.println("两个list大小不相等");
//				}
//			}else{
//				System.out.println("list为空或者为null");
//			}
//			return flag;
//		}
//		
		/**
		 * 向某方向滑动直到某元素出现
		 * @param by 查找对象
		 * @param direction 方向
		 * @param duration 每次滑动时间，单位毫秒
		 * @param findCount 查找次数
		 */
		public boolean swipeUntilElementAppear(By by,String direction,int findCount){
			//this.implicitlyWait(3);
			boolean flag=false;
			while(!flag&&findCount>0){
				try {
					androidDriver.findElement(by);
					flag=true;
				} catch (Exception e) {
					// TODO: handle exception
					sliding(direction);
					findCount--;
				}
			}
			return flag;
		}
		public AndroidElement swipeUntilElement(By by,String direction,int findCount){
			//this.implicitlyWait(3);
			AndroidElement element=null;
			boolean flag=false;
			while(!flag&&findCount>0){
				try {
					element=(AndroidElement) androidDriver.findElement(by);
					flag=true;
				} catch (Exception e) {
					// TODO: handle exception
					sliding(direction);
					findCount--;
				}
			}
			return element;
		}
//		
//		
//		//获取当前登录的手机号
//		public String getLoginPhoneNum() {
//			String phoneNum = "";
//			gotoCateSet(4);
//			wait(1);
//			if(findElementById("tvTips").getText().equals("147****6911")) {
//				phoneNum = CaseConfig.USERNAME_BEIYONG1;
//			}else if(findElementById("tvTips").getText().equals("147****6912")) {
//				phoneNum = CaseConfig.USERNAME_BEIYONG2;
//			}else if(findElementById("tvTips").getText().equals("147****6914")) {
//				phoneNum = CaseConfig.USERNAME_PUBLIC;
//			}else {
//				failAndMessage("登录账号异常，请检查。");
//			}
//			return phoneNum;
//		}
		
		public void launchApp() {
			if (os == 2) {
				wait(7);
				EventManager.saveLifeCycleEvent(EventEntity.LifeCycleEvent.APP_START);
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"openapp/start_click","","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"home_page","","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","guess_like_expo#algorithm=bus","","");
				androidDriver.pressKeyCode(AndroidKeyCode.BACK);
				reports_HomePageTest.log(LogStatus.INFO,"关闭升级弹框");
				 if (CheckViewVisibilty(By.xpath("//android.widget.Button[@text='切换']"))) {
		    		 clickByCT(MobileBy.id("com.uxin.usedcar:id/bt_confirm_ok"), 1, 0);
		    		 }
				sleep(200);
			}else if (os == 1) {
				EventManager.saveLifeCycleEvent(EventEntity.LifeCycleEvent.APP_START);
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"openapp/start_click","","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE,"home_page","","","");
				EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","a_home/guess_like_expo/algorithm/bus","","");
				//TODO:
				 if (CheckViewVisibilty(By.name("好"))) {
					 clickByCT(By.name("好"), 1, 1);
				}
				if (CheckViewVisibilty(MobileBy.AccessibilityId("img wsgcn closeBtn"))) {
		    		 clickByCT(MobileBy.AccessibilityId("img wsgcn closeBtn"), 1, 0);
		    		 }
				 if (CheckViewVisibilty(By.name("切换"))) {
			      		clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", "切换", 2, 1);
				}
			}
		}
		 /**
	     * 目前为谓词操作方式点击
	     *
	     * @param objectProper 传递的未参数化的谓词组合
	     * @param wdType       谓词类型 相当于控件类型
	     * @param label        谓词标识符
	     * @param Count        操作调用次数
	     * @param times        延迟时间
	     */
	    public static void clickWD(String objectProper, String wdType, String label, int Count, int times) {
//			clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", "返回", "");
	        if (objectProper.contains("element1") && !wdType.equals("") && !label.equals("")) {

	            objectProper = objectProper.replace("element1", wdType).replace("element2", label);
	            System.out.println("当前拼接的objectProper对象   " + objectProper);
	        }

	        try {
	            if (Count == 1) {
	                iosDriver.findElement(MobileBy.iOSNsPredicateString(objectProper)).click();
	                wait(times);
	                System.out.println("当前拼接的谓词为--->   " + MobileBy.iOSNsPredicateString(objectProper));
	                CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
	            }
	            while (Count > 1) {
	                for (int i = 0; i < Count; i++) {
	                	iosDriver.findElement(MobileBy.iOSNsPredicateString(objectProper)).click();
	                    wait(times);
	                    System.out.println("当前拼接的谓词为--->   " + MobileBy.iOSNsPredicateString(objectProper));
	                    CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
	                }
	                break;
	            }
	        } catch (Exception e) {
	            GetScreenshot("点击--->     失败");
	            CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
	        }
	    }
	    
	    /**
	     * 执行时间次数RUN属性
	     * @param objectProper  控件操作对象
	     * @param label         标识
	     * @param Count         执行次数
	     * @param times         停留时间
	     * @param run           "TRUE" 执行
	     */
	    public static void clickWD(String objectProper, String label, int Count, int times ,String run) {
	        if (objectProper.contains("element1") && !label.equals("")) {
	            objectProper = objectProper.replace("element1", label);
	            System.out.println("当前拼接的objectProper对象   " + objectProper);
	            System.out.println("RUN   " + run);
	        }
	        if (run.contentEquals("TRUE")) {
	        	try {
	                if (Count == 1) {
	                	iosDriver.findElement(MobileBy.iOSNsPredicateString(objectProper)).click();
	                    wait(times);
	                    System.out.println("当前拼接的谓词为--->   " + MobileBy.iOSNsPredicateString(objectProper));
	                    CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
	                }
	                while (Count > 1) {
	                    for (int i = 0; i < Count; i++) {
	                    	iosDriver.findElement(MobileBy.iOSNsPredicateString(objectProper)).click();
	                        wait(times);
	                        System.out.println("当前拼接的谓词为--->   " + MobileBy.iOSNsPredicateString(objectProper));
	                        CaseConfig.STEPRESULT = CaseConfig.STEPRESULTPASS;
	                    }
	                    break;
	                }
	            } catch (Exception e) {
	                GetScreenshot("点击--->     失败");
	                CaseConfig.STEPRESULT = CaseConfig.STEPRESULTFAIL;
	            }
			}
	    }
	    
	    /**
		 * 
	     * @param directionName  滑动方向
		 * @param operate_Para   操作方法
		 * @param objectProper   控件属性
		 * @param objectProperParamer 滑动次数
		 */
		public void jsPressSwipe_para_by(String directionName, String operate_Para ,final By  objectProper, String objectProperParamer) {
			JavascriptExecutor js = (JavascriptExecutor) iosDriver;
		    HashMap scrollObjects = new HashMap();
		    scrollObjects.put("direction", directionName);
		    scrollObjects.put("element", ((RemoteWebElement) iosDriver.findElement(objectProper)).getId());
		   if (null!=objectProperParamer) {
			   for (int i = 0; i < Integer.parseInt(objectProperParamer); i++) {
					js.executeScript("mobile: " + operate_Para, scrollObjects);
					System.out.println("=================当前执行滑动次数===========================      "+i+"    次");
				}
			}
		}
		
		 public static String sessionDetail(String sessionDetail) {
		        try {
		        	String session=(String) iosDriver.getSessionDetail(sessionDetail);

		            if (null !=session) {
		            	System.out.println("当前iOS系统版本号--->    "+session);
		                return session;
		            } else {
		                return null;
		            }
		        } catch (Exception e) {
		            GetScreenshot("获取session错误  -->  " + sessionDetail);
		            e.printStackTrace();
		            return null;
		        }
		  }
		 /**
		     * 谓词方式操作
		     * @param text    谓词
		     * @param times   等待时间
		     * @param Run    "TRUE" 执行
		     * @return
		     */
		    public String scrollToElementClick(String text, int times,String Run) {
//		        IOSElement element = (IOSElement) driver.findElementByIosNsPredicate("wdName = '" + text + "'");
//		        IOSElement element = (IOSElement) driver.findElementByName("wdName = '" + text + "'");
		     	IOSElement element = (IOSElement) iosDriver.findElement(By.name(text));
		        try {
		            JavascriptExecutor js = (JavascriptExecutor) iosDriver;
		            HashMap<String, String> scrollObject = new HashMap<>();
		            scrollObject.put("toVisible", "true");
		            //value 为false时候wdLabel
//		            scrollObject.put("predicateString", "wdValue == '" + text + "'");
				    scrollObject.put("predicateString", "wdName == '" + text + "'");
		            js.executeScript("mobile:scroll", scrollObject);
//		          clickWD("wdLabel == 'element1' AND visible == 1",element, 1, 5);
		            clickWD("wdName == 'element1' AND visible == 1", text, 1, times,Run);
		            return element.getText();//blank
		        } catch (Exception e) {
		            return element.getText();
		        }
		    }
		    
		    
		    public static String scrollToElementClick_classNameFalse(String text) {
		        IOSElement element = (IOSElement) iosDriver.findElementByIosNsPredicate("name = '" + text + "'");
		        try {
		            JavascriptExecutor js = (JavascriptExecutor) iosDriver;
		            HashMap<String, String> scrollObject = new HashMap<>();
		            scrollObject.put("toVisible", "true");
//				        scrollObject.put("predicateString", "wdLabel == '" + text + "'");
		            scrollObject.put("predicateString", "wdLabel == '" + text + "'");
		            System.out.println("---------------------开始执行谓词操作---------------------------");
		            js.executeScript("mobile:scroll", scrollObject);
//		            clickByCT(MobileBy.iOSNsPredicateString("name = '" + text + "'"), 1, 1);
		            clickByCT(MobileBy.iOSNsPredicateString("name == '" + text + "'AND visible==1"), 1, 1);
//		            jsTapyDistance_X_Y_By(MobileBy.name(text), 0*screenRatioX, 0*screenRatioY, "+", "+", "点击列表按钮");
		            return element.getText();//blank
		        } catch (Exception e) {
		            return element.getText();
		        }
		    }
		    
		    
		    public void defectDetail(String locator, String locator_combined, int indexValue) {
		        List<WebElement> Lists = iosDriver.findElements(By.xpath(locator));
//		        System.out.println(driver.findElements(By.xpath(locator)));
		        System.out.println("当前List集合个数  " + Lists.size());
		        if (indexValue == 0 && null != Lists && Lists.size() > 0) {
		            for (int i = 1; i < Lists.size(); i++) {
		                System.out.println("当前节点返回文本--->    " + Lists.get(i).findElement(By.xpath(locator_combined)).getText());
		                Lists.get(i).findElement(By.xpath(locator_combined)).click();
		                wait(1);

		            }
		        }
		        if (indexValue == 1 && null != Lists && Lists.size() > 0) {
		            for (int i = 1; i < Lists.size(); i++) {
		            	   System.out.println("当前List集合个数  " + Lists.size());
		            	   System.out.println("当前节点返回文本--->    " + Lists.get(i).getAttribute("name"));
//		                System.out.println("当前节点返回文本--->    " + Lists.get(i).getText());
		                System.out.println("==================================");
		                scrollToElementClick_classNameFalse(Lists.get(i).getAttribute("name"));
		            }
		            clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", "车辆", 1, 1);
		        }
		        
		        if (indexValue == 2 && null != Lists && Lists.size() > 0) {
		            for (int i = 1; i < indexValue; i++) {
		                System.out.println("当前节点返回文本--->    " + Lists.get(i).getText());
		                Lists.get(i).click();
		            }
		        }
		    }
		    
		    
		    public void CarDetail(String locator, String locator_combined, int indexValue) {
		     	 jsPressSwipe_para_by("up", "swipe", MobileBy.className("XCUIElementTypeTable"), "1");
		        List<WebElement> Lists = iosDriver.findElements(By.xpath(locator));
//		        System.out.println(driver.findElements(By.xpath(locator)));
		        System.out.println("当前List集合个数  " + Lists.size());
		        if (indexValue == 0 && null != Lists && Lists.size() > 0) {
		            for (int i = 1; i < Lists.size(); i++) {
		                System.out.println("当前节点返回文本--->    " + Lists.get(i).findElement(By.xpath(locator_combined)).getText());
		                Lists.get(i).findElement(By.xpath(locator_combined)).click();
		                wait(1);

		            }
		        }
		        if (indexValue == 1 && null != Lists && Lists.size() > 0) {
		            for (int i = 1; i < Lists.size(); i++) {
		            	   System.out.println("当前List集合个数  " + Lists.size());
		            	   System.out.println("当前节点返回文本--->    " + Lists.get(i).getAttribute("name"));
//		                System.out.println("当前节点返回文本--->    " + Lists.get(i).getText());
		                System.out.println("==================================");
		                scrollToElementClick_className(Lists.get(i).getAttribute("name"));
		            }
		            clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", "车辆", 1, 1);
		        }
		        
		        if (indexValue == 2 && null != Lists && Lists.size() > 0) {
		            for (int i = 1; i < indexValue; i++) {
		                System.out.println("当前节点返回文本--->    " + Lists.get(i).getText());
		                Lists.get(i).click();
		            }
		        }
		    }
		    
		 /**
		     * @param NsPredicate 谓词定位方式传
		     * @param x           X轴
		     * @param y           Y轴
		     * @param add         加坐标
		     * @param minus       减坐标
		     * @param target      备注操作目标控件名称
		     */
		    public static void jsTapyDistance_X_Y_By(final By locator, double x, double y, String add, String minus, String target) {

		        IOSElement webElement = (IOSElement) iosDriver.findElement(locator);
//					new TouchAction((PerformsTouchActions) driver).press(webElement).waitAction(70).release().perform();
//		        System.out.println("X--->   " + String.valueOf(webElement.getCenter().getX()));
//		        System.out.println("Y--->   " + String.valueOf(webElement.getCenter().getY()));
		        // mobile:tap
		        CaseConfig.CENTER_X=webElement.getCenter().getX();
		        CaseConfig.CENTER_Y=webElement.getCenter().getY();
		        System.out.println("X--->   " + String.valueOf(CaseConfig.CENTER_X));
		        System.out.println("Y--->   " + String.valueOf(CaseConfig.CENTER_Y));

		        if (add.equals("+") && minus.equals("-")) {
		            System.out.println("当前表达式为---->     " + "  X轴  +" + "  Y轴  -");
		            JavascriptExecutor js = (JavascriptExecutor) iosDriver;
		            HashMap<String, String> tapObject = new HashMap<String, String>();
		            tapObject.put("x", String.valueOf(CaseConfig.CENTER_X + x));
		            tapObject.put("y", String.valueOf(Math.abs(CaseConfig.CENTER_Y - y)));

		            System.out.println("x  " + String.valueOf(CaseConfig.CENTER_X + x));
		            System.out.println("y  " + String.valueOf(Math.abs(CaseConfig.CENTER_Y - y)));
		            System.out.println(webElement.getId());
		            tapObject.put("webElement", webElement.getId());
		            System.out.println("tapObject: " + tapObject.toString());
		            js.executeScript("mobile:tap", tapObject);
		        }
		        if (add.equals("-") && minus.equals("+")) {
		            System.out.println("当前表达式为---->     " + "  X轴  -" + "  Y轴  +");
		            JavascriptExecutor js = (JavascriptExecutor) iosDriver;
		            HashMap<String, String> tapObject = new HashMap<String, String>();
		            tapObject.put("x", String.valueOf(CaseConfig.CENTER_X - x));
		            tapObject.put("y", String.valueOf(Math.abs(CaseConfig.CENTER_Y + y)));

		            System.out.println("x  " + String.valueOf(CaseConfig.CENTER_X - x));
		            System.out.println("y  " + String.valueOf(Math.abs(CaseConfig.CENTER_Y + y)));
		            System.out.println(webElement.getId());
		            tapObject.put("webElement", webElement.getId());
		            System.out.println("tapObject: " + tapObject.toString());
		            js.executeScript("mobile:tap", tapObject);
		        }
		        if (add.equals("+") && minus.equals("+")) {
		            System.out.println("当前表达式为---->     " + "  X轴  +" + "  Y轴  +");
		            JavascriptExecutor js = (JavascriptExecutor) iosDriver;
		            HashMap<String, String> tapObject = new HashMap<String, String>();
		            tapObject.put("x", String.valueOf(CaseConfig.CENTER_X + x));
		            tapObject.put("y", String.valueOf(Math.abs(CaseConfig.CENTER_Y + y)));

		            System.out.println("x  " + String.valueOf(CaseConfig.CENTER_X + x));
		            System.out.println("y  " + String.valueOf(Math.abs(CaseConfig.CENTER_Y + y)));
		            System.out.println(webElement.getId());
		            tapObject.put("webElement", webElement.getId());
		            System.out.println("tapObject: " + tapObject.toString());
		            js.executeScript("mobile:tap", tapObject);
		        }
		        if (add.equals("-") && minus.equals("-")) {
		            System.out.println("当前表达式为---->     " + "  X轴  -" + "  Y轴  -");
		            JavascriptExecutor js = (JavascriptExecutor) iosDriver;
		            HashMap<String, String> tapObject = new HashMap<String, String>();
		            tapObject.put("x", String.valueOf(Math.abs(CaseConfig.CENTER_X - x)));
		            tapObject.put("y", String.valueOf(Math.abs(CaseConfig.CENTER_Y - y)));

		            System.out.println("x  " + String.valueOf(Math.abs(CaseConfig.CENTER_X - x)));
		            System.out.println("y  " + String.valueOf(Math.abs(CaseConfig.CENTER_Y - y)));
		            System.out.println(webElement.getId());
		            tapObject.put("webElement", webElement.getId());
		            System.out.println("tapObject: " + tapObject.toString());
		            js.executeScript("mobile:tap", tapObject);
		        }
		    }
		
		 /**
	     * 需要退回到MainActivity页面之后再执行该方法
	     * 拉取文件并进行比对
	     *
	     * @param test 测试用例名称
	     */
	    public void callBriadcastAndPullFile() {
	     	EventManager.writeData();
//	        driver.pressKeyCode(AndroidKeyCode.BACK);
	        sleep(2000);
	        try {
	            Process broadprocess = Runtime.getRuntime().exec("adb shell am broadcast -a com.uxin.usedcar.writeDataToStorage");
	            broadprocess.waitFor();
	            sleep(3000);
	            Process process = Runtime.getRuntime().
	                    exec("adb pull /sdcard/Download/statistic.json ./actual/statistic.json");
	            int exitValue = process.waitFor();
	            System.out.println("exitValue:" + exitValue);
	            sleep(1000);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    /**
	     * 逐行写入文件
	     * @param fileName case名称
	     * @param content 输入内容
	     */
	    public static void writerByline(String fileName,String content) {
			try {
				if (CaseConfig.OS == 1) {
					String dir_name = System.getProperty("user.dir") +File.separator+"appMoreEvent_iOS";
					if (!(new File(dir_name).isDirectory())) { // 判断是否存在该目录
						System.out.println("当前目录全文路径   "+dir_name);
						new File(dir_name).mkdir();
					}
					FileWriter fw = new FileWriter("./appMoreEvent_iOS/"+fileName+".txt",true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content+"\n");
					bw.close();
				}else {
					String dir_name = System.getProperty("user.dir") +File.separator+"appMoreEvent";
					if (!(new File(dir_name).isDirectory())) { // 判断是否存在该目录
						System.out.println("当前目录全文路径   "+dir_name);
						new File(dir_name).mkdir();
					}
					FileWriter fw = new FileWriter("./appMoreEvent/"+fileName+".txt",true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content+"\n");
					bw.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    public static void recurDelete(String caseName){
	        	try {
	        		if (CaseConfig.OS == 1) {
	        			String dir_name = System.getProperty("user.dir") +File.separator+"appMoreEvent_iOS";
						if (!(new File(dir_name).isDirectory())) { // 判断是否存在该目录
							System.out.println("当前目录全文路径   "+dir_name);
							new File(dir_name).mkdir();
						}
	        			File appMoreEventTxt = new File("./appMoreEvent_iOS/"+caseName+".txt");
		        		appMoreEventTxt.delete();
		        		System.out.println("删除"+caseName+".txt老文件");
					}else {
						String dir_name = System.getProperty("user.dir") +File.separator+"appMoreEvent";
						if (!(new File(dir_name).isDirectory())) { // 判断是否存在该目录
							System.out.println("当前目录全文路径   "+dir_name);
							new File(dir_name).mkdir();
						}
					File appMoreEventTxt = new File("./appMoreEvent/"+caseName+".txt");
		        		appMoreEventTxt.delete();
		        		System.out.println("删除"+caseName+".txt老文件");
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("路径有问题");
					e.printStackTrace();
				}
	    }
	    //通过key获取文件中的值
	    public static String getDataFromFile(String name) {
	        try {

	            sleep(3000);
	            Process process = Runtime.getRuntime().
	                    exec("adb pull /sdcard/Download/prepare.json ./actual/prepare.json");
	            sleep(1000);
	            int exitValue = process.waitFor();
	            System.out.println("exitValue:" + exitValue);

	            String data = Compare.getDatafromFile("./actual/prepare.json");
	            if (data != null && !"".equals(data)) {
	                Map<String, Object> map = new Gson().fromJson(data, new com.google.gson.reflect.TypeToken<Map<String, Object>>() {
	                }.getType());
	                return map.get(name) + "";
	            }
	            sleep(2000);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        return "";
	    }
	    
	    
	    public static String getValidData(String name) {
        	 String validjson = getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] \"isValid\"`]");
		    	System.out.println(validjson);
		    	try {
		    		CaseConfig.VALIDJSON=validjson.split(":")[1].split("}")[0];
			    System.out.println("获取到的当前Valid值为----->    "+CaseConfig.VALIDJSON);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("获取到的当前Valid值为空  请检查！！！！！！！！！！----->    "+CaseConfig.VALIDJSON);
				}
				return CaseConfig.VALIDJSON;
   }
	    /**
	     * 点击列表中的第一辆车,不是搜索时可用
	     * @param pageNum 2=默认列表页，3=一成购列表页
	     * @param word 搜索时传入搜索值，不搜索是输入空字符""
	     * @return
	     */
	    public static String clickFirstCarInList(int pageNum,String word){
	     	String carid = null;
			String valid = null;
			word ="";
			if (os == 2) {
				clickElementById("tvCarWholeName");
				wait(3);
				reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				carid = findElementById("tvVehicleDetailCityName").getAttribute("text").split(":")[1];
				valid = getDataFromFile("valid");
				System.out.println("carid"+carid+";valid:"+valid);
				return carid;
			}else if (os == 1) {
				//TODO:
//				CaseConfig.CARSERIES=getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'款')]", 0, "name","");
				String a=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '款'`]");
//				System.out.println(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name"));
//				System.out.println(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 1, "name"));
				CaseConfig.CARSERIES=a;
				 scrollToElementClick(CaseConfig.CARSERIES, 10);//Index 从0开始计算1
//				 CaseConfig.CAR_NO=getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'编号')]", 0, "name");
				 carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
				 System.out.println("获取到的当前carid值为----->    "+carid);
				 reports_BuyCarTest.log(LogStatus.INFO, "获取车辆的carid");
				 valid = getValidData(valid);
				return carid;
			}
			if (pageNum == 3) {
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carlist_click#AI_num=[value]/result=1/icon=1/class=3/mold=3/rank=1/type=[value]/page=3/carid="+carid+"/word="+word+"/label=[value]/video=[value]", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "halfvehicle_details_page#carid="+carid+"/valid="+(Boolean.parseBoolean(valid)?"0" : "1"), "", "", "");
			}else if (pageNum == 2) {
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carlist_click#AI_num=[value]/result=1/icon=1/class=3/mold=3/rank=1/type=[value]/page=2/carid="+carid+"/word=/label=[value]/video=[value]", "", "", "");
				EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid="+(Boolean.parseBoolean(valid)? "0" : "1"), "", "", "");
			}
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_detail_expo", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid=" + carid +"/pos=file", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid=" + carid +"/pos=trend", "", "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid=" + carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid=" + carid, "", "");
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid=" + carid +"/type=1", "", "");
			//不确定有无
			EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
			return carid;
	    }
	    
	    /**
	     * 从买车列表进入详情页 优信认证 全国直购
	     *
	     * @param i 进入第几个详情页
	     */
	public void toDetailNoPrice(int i) {// i 不为0进入详情页
		stepScreenshot("🚘买车页面---> 第   " + i + "  辆车的买车页截图");
		String listTitle = getTextByChain("**/XCUIElementTypeCollectionView[1]/XCUIElementTypeCell[" + i
				+ "]/**/XCUIElementTypeStaticText[`name CONTAINS[cd] \"首付\"`]");
		String fyb = iosDriver.findElementByName("newCallgray").getAttribute("value");
		scrollToElementClick(getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'款')]", i - 1, "name"), 10);// Index
																														// 从0开始计算1
		CaseConfig.CARSERIES = getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'款')]", 0, "name");
		if (CheckViewVisibilty(By.name("我知道了"))) {
			clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", "我知道了", 1, 3);
			toDetailNoPrice(3);
		}
		if (CheckViewVisibilty(By.name("carDetail topBanner go"))) {
			CaseConfig.FINANCIAL = getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'金融')]", 0, "name");
			System.out.println("当前金融返回字符串---->>    " + CaseConfig.FINANCIAL);
		}
		String PS = iosDriver.getPageSource();
		System.out.println(PS);
		// CaseConfig.PS=PS;
		if (PS.contains("砍价")) {
			CaseConfig.C = getListTextByXpath("//XCUIElementTypeButton[contains(@name,'砍')]", 0, "name");// 砍价0
			CaseConfig.C_1 = getListTextByXpath("//XCUIElementTypeButton[contains(@name,'砍')]", 1, "name");// 砍价1
			System.out.println("当前为C 方案       砍价返回字符串---->>    " + CaseConfig.C + "   当前为C1 方案       砍价返回字符串---->>    "+ CaseConfig.C_1);
		}
		if (PS.contains("我要优惠")) {
			CaseConfig.DISCOUNT = getListTextByXpath("//XCUIElementTypeButton[contains(@name,'优惠')]", 0, "name");// 我要优惠0
			CaseConfig.DISCOUNT_1 = getListTextByXpath("//XCUIElementTypeButton[contains(@name,'优惠')]", 1, "name");// 我要优惠1
			System.out.println("当前为B 方案      我要优惠0返回字符串---->>    " + CaseConfig.DISCOUNT+ "   当前为B 方案       我要优惠1返回字符串---->>    " + CaseConfig.DISCOUNT_1);
		}
		if (PS.contains("询底价")) {
			CaseConfig.XDJ = getListTextByXpath("//XCUIElementTypeButton[contains(@name,'底价')]", 0, "name");
			;// 询底价0
			CaseConfig.XDJ_1 = getListTextByXpath("//XCUIElementTypeButton[contains(@name,'底价')]", 1, "name");
			;// 询底价1
			System.out.println("当前询底价返回字符串---->>    " + CaseConfig.XDJ);
			System.out.println("当前为A 方案      询底价0返回字符串---->>    " + CaseConfig.XDJ
					+ "    当前为A 方案       询底价1返回字符串---->>    " + CaseConfig.XDJ_1);
		}
		if (CheckViewVisibilty(By.name("免费电话"))) {
			CaseConfig.D = getListTextByXpath("//XCUIElementTypeButton[contains(@name,'免费')]", 0, "name");// null时候没有免费电话这个按钮
			System.out.println("当前为D方案返回字符串---->>    " + CaseConfig.D);
			if (CaseConfig.FINANCIAL != null) {
				System.out.println("当前金融返回字符串---->>    " + CaseConfig.FINANCIAL);
				System.out.println("该详情页 没有预约看车按钮🔘 只有询底价--->>  当前车型车系 ====>>>>>>    " + CaseConfig.CARSERIES);
			}
			if (CaseConfig.AppointCar != null) {
				System.out.println("当前预约看车返回字符串---->>    " + CaseConfig.AppointCar);
				if (CaseConfig.FINANCIAL.equals(null)) {
					System.out.println("该详情页 有预约看车按钮🔘  不是金融特惠价详情页--->>  当前车型车系 ====>>>>>>    " + CaseConfig.CARSERIES);
				}
			}
		}
		if (CheckViewVisibilty(By.name("砍价"))) {
			System.out.println("当前为C 方案 砍价返回字符串---->>    " + CaseConfig.C);
			System.out.println("砍价2⃣️---->>    " + CaseConfig.C_1);
			System.out.println("当前为C 方案      砍价0返回字符串---->>    " + CaseConfig.C + "   当前为C 方案       砍价1返回字符串---->>    "+ CaseConfig.C_1);
			clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", CaseConfig.C, 1, 2);
			CaseConfig.BARGAIN_0 = getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'万元')]", 0, "name");
			if (getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'款')]", 0, "name").equals(CaseConfig.CARSERIES)) {
				System.out.println("砍价列表页面车型车系和车辆详情页车型车系一致  ---->  ");
				clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton",
						"icon chexiangqing titlebar bac", 1, 1);
			}

			clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", CaseConfig.C_1, 1, 2);
			CaseConfig.BARGAIN_1 = getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'万元')]", 0, "name");
			if (getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'款')]", 0, "name").equals(CaseConfig.CARSERIES)) {
				System.out.println("砍价列表页面车型车系和车辆详情页车型车系一致  ---->  ");
				clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton",
						"icon chexiangqing titlebar bac", 1, 1);
			} else {
				System.out.println("砍价列表页面车型车系和车辆详情页车型车系不一致  ---->  ");
				clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton",
						"icon chexiangqing titlebar bac", 1, 1);
			}

			if (CaseConfig.BARGAIN_0.equals(CaseConfig.BARGAIN_1)) {
				System.out.println("2个砍价按钮进入的我的出价价格等价--->>    " + "右上角砍价按钮--->>    " + CaseConfig.BARGAIN_0+ "底部砍价按钮--->>    " + CaseConfig.BARGAIN_1);
			}
		}
		if (PS.contains("预约看车")) {
			CaseConfig.AppointCar = getListTextByXpath("//XCUIElementTypeButton[contains(@name,'看车')]", 0, "name");// 预约看车
			System.out.println("属于A 但是包含于我要优惠B方案  当前预约看车返回字符串---->>    " + CaseConfig.AppointCar);// 直购车右下角没有 预约看车按钮
																									// 当询底价和预约看车分别同时出现时候
		}
		if (PS.contains("月供")) {// 即使接口关闭依然能获取
			CaseConfig.MMTAGE = getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'月供')]", 0, "name");// 首付*** 月供***
			System.out.println("当前月供返回字符串---->>    " + CaseConfig.MMTAGE);
		}
		if (PS.contains("提车时间")) {//
			CaseConfig.PUT = getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'-')]", 0, "name");// 提车时间
			System.out.println("当前提车时间返回字符串---->>    " + CaseConfig.PUT);
		}

		if (PS.contains("首付")) {// 接口关闭依然有值
			CaseConfig.DPM = getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'首付')]", 0, "name");
			;// 金融首付
			System.out.println("当前首付返回字符串---->>    " + CaseConfig.DPM);
			if (CaseConfig.DPM.equals(CaseConfig.FINANCIAL)) {
				System.out.println("当前金融返回字符串  没有预约看车 按钮🔘---->>    " + CaseConfig.FINANCIAL + "当前首付1返回字符串---->>    "+ CaseConfig.DPM);
				CaseConfig.DPM_MMTAGE = getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'首付')]", 1,"name");
				;// 首付*** 月供***排序节点1
				if (CaseConfig.DPM_MMTAGE.equals(CaseConfig.MMTAGE)) {
					System.out.println("当前首付月供排序节点1返回字符串---->>    " + CaseConfig.DPM_MMTAGE + "当前首付月供返回字符串---->>    "+ CaseConfig.MMTAGE);
				}
			}
		}
		System.out.println("当前车辆车源编号ID---->>    "+ getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'编号')]", 0, "name"));
		CaseConfig.CAR_NO = getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'编号')]", 0, "name");
		stepScreenshot("🚗详情页面---> 第   " + i + "  辆车的详情页截图  " + "对应车辆编号===>  " + CaseConfig.CAR_NO);
		if (CheckViewVisibilty(By.name("已售"))) {
			clickByNameCount("icon chexiangqing titlebar bac", 1, 3);
			toDetailNoPrice(i + 1);
		}
	}
	  /**
     * 截图方法，必须传入caseName以区别图片
     *
     * @param String name图片名称
     */
    public static void stepScreenshot(String caseName) {
        File screenShotFile = ((TakesScreenshot) iosDriver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(new Date());
        //目录不存在时创建目录
        if (new File(CaseConfig.CATCH_PICTURE).isDirectory()) {
            System.out.println("生成截图目录");
            new File(CaseConfig.CATCH_PICTURE).mkdir();
        }
        try {
            FileUtils.copyFile(screenShotFile, new File(CaseConfig.CATCH_PICTURE + File.separator + caseName + "-" + date + ".png"));
        } catch (IOException e) {
            failAndMessage("截图失败");
            e.printStackTrace();
        }
    }
    
    
    public static void subMenuLogin(){
   	 if (CheckViewVisibilty(By.name("登录优信二手车"))) {
   			inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 0, "value")), CaseConfig.USERNAME_BEIYONG2);
   			clickElementByName("获取验证码");
   			inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 1, "value")), "666666");
   			}
   	}
    
    
    public void iOSLogin() {
		if (CheckViewVisibilty(By.name("切换"))) {
			clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", "切换", 2, 1);
		}
		clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", "我的", 1, 1);
		wait(2);
		if (getListTextByLocator(MobileBy.xpath("//XCUIElementTypeStaticText[contains(@label,'***')]"), 0, "name") != null) {
			System.out.println("=====当前处于登陆状态======");
		} else {
			System.out.println("=====当前未登陆状态======");
			clickWD("wdType == 'element1' AND label == 'element2'", "XCUIElementTypeButton", "img me mrtouxiang", 1, 1);
			if (CheckViewVisibilty(By.name("登录优信二手车"))) {
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 0,"value")), CaseConfig.USERNAME_BEIYONG2);
				clickElementByName("获取验证码");
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 1,"value")), "666666");
			}
		}
	}
    
    /**
     * @param locator          父级别定位控件
     * @param value            获取元素属性赋值方式
     * @param locator_combined 子级别控件
     * @param endValue         集合结束值
     * @return 
     */
    public String carList_endValue(String locator, String locator_combined,String value, int endValue) {
        List<WebElement> Lists = iosDriver.findElements(By.xpath(locator));
        System.out.println("当前List集合个数  " + Lists.size());

		if (null != Lists && Lists.size() > 0) {
			if (endValue == 0) {
				for (int i = 0; i < Lists.size(); i++) {
					System.out.println(Lists.get(i).findElement(By.xpath(locator_combined)).getText());
					return Lists.get(i).findElement(By.xpath(locator_combined)).getText();
				}
			} else {
				for (int i = 0; i < endValue; i++) {
					System.out.println(Lists.get(i).findElement(By.xpath(locator_combined)).getAttribute(value));
					return Lists.get(i).findElement(By.xpath(locator_combined)).getAttribute(value);
				}
			}
		}
		return null;
	}
    
    public String scrollToElementNew(String text) {
        IOSElement element = (IOSElement) iosDriver.findElementByIosNsPredicate("value = '" + text + "'");
//		    Find: Elements matching predicate 'wdValue == "汽车灯光揭秘" AND (1 == 1 OR identifier == 0 OR frame == 0 OR value == 0 OR title == 0 OR label == 0 OR elementType == 0 OR enabled == 0 OR placeholderValue == 0)'
        try {
            JavascriptExecutor js = (JavascriptExecutor) iosDriver;
            HashMap<String, String> scrollObject = new HashMap<>();
            scrollObject.put("toVisible", "true");
            scrollObject.put("predicateString", "value == '" + text + "'");
            js.executeScript("mobile:scroll", scrollObject);
            return element.getText();
        } catch (Exception e) {
            return element.getText();
        }
    }
    //IOS保存预期埋点
    public void saveActualEvent(){
    	 EventManager.writeData();
		 String json = getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] \"source\"`]");
	    	System.out.println(json);
	    	try {
 				String dir_name = System.getProperty("user.dir") +File.separator+"actual_iOS";
 				if (!(new File(dir_name).isDirectory())) { // 判断是否存在该目录
 					System.out.println("当前目录全文路径   "+dir_name);
 					new File(dir_name).mkdir();
 				}
 				FileWriter fw = new FileWriter("./actual_iOS/"+"statistic"+".json",false);
 				BufferedWriter bw = new BufferedWriter(fw);
 				bw.write(json+"\n");
 				bw.close();
 			} catch (Exception e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
    }
}

