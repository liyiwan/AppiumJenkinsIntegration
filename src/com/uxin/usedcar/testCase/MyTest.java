package com.uxin.usedcar.testCase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;
import com.uxin.usedcar.maidianlib.Compare;
import com.uxin.usedcar.maidianlib.EventEntity;
import com.uxin.usedcar.maidianlib.EventManager;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidKeyCode;

import com.relevantcodes.extentreports.LogStatus;
import com.uxin.usedcar.test.libs.BaseTest;
import com.uxin.usedcar.test.libs.CaseConfig;
import com.uxin.usedcar.test.libs.ComparisonTextByLin;


public class MyTest extends BaseTest {
    @BeforeClass
    public static void first() throws Exception {
        reports_MyTest.init("./report/MyTest/reportMyTest.html", true);
        System.out.println("------setUp-------");
    }

    @AfterClass
    public static void last() {
        reports_MyTest.endTest();
//		driver.quit();
        System.out.println("------tearDown-------");
    }

    /**
     * 逐行比对2个文件
     *
     * @param expectedPath
     * @param actualPath
     */
    public static void read(String expectedPath, String actualPath) {
        ComparisonTextByLin comparison = new ComparisonTextByLin();
        int i = 1;
        String tempString = null;
        try {
            FileReader reader = new FileReader(new File(expectedPath));
            BufferedReader br = new BufferedReader(reader);
            while ((tempString = br.readLine()) != null) {
//				System.out.println(tempString+"<第"+i+"行>");
                if (tempString.equals(comparison.readAppointedLineNumber(new File(actualPath), i))) {
                    System.out.println("找到" + comparison.readAppointedLineNumber(new File(actualPath), i) + "，行号为" + i);
//					break;
                } else {
                    reports_MyTest.log(LogStatus.ERROR, "埋点" + tempString + "不存在");
                    System.out.println(tempString + "不存在");
//					Assert.fail(tempString+"不存在");

                }
                i++;
//			return tempString;	
            }
            br.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 登录
     */
    @Test
    public void test_5710_ZADL() {
    	startMyTest("test_5710_ZADL");
        if (os == 2) {
            clickElementById("imgTouXiang");
            reports_MyTest.log(LogStatus.INFO, "点击头像");
            if (SellCarTest.Res("textView2")) {
				System.out.println("去登陆");
	            input("id", "etPhoneNum", "", CaseConfig.USERNAME_BEIYONG2, "");
	            clickElementByName("获取验证码");
	            input("id", "etYanZhengMa", "", "666666", "");
	            EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "login", "", "", "");
	            EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "used_login#tel_num=" + CaseConfig.USERNAME_BEIYONG2, "", "", "");
            }
		}else if (os == 1) {
			if(CheckViewVisibilty(By.name("点击登录"))) {//ios未登录
				iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name='img me mrtouxiang']")).click();
				reports_MyTest.log(LogStatus.INFO, "点击头像登录");
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 0, "value")), CaseConfig.USERNAME_BEIYONG1);
				clickElementByName("获取验证码");
				inputByLocator(MobileBy.name(getListByLocator(MobileBy.xpath("//XCUIElementTypeTextField[contains(@type,'XCUIElementTypeTextField')]"), 1, "value")), "666666");
				sleep(500);
				EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "login", "", "", "");
		        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "used_login#tel_num=" + CaseConfig.USERNAME_BEIYONG2, "", "", "");    
		    }else {
				reports_BuyCarTest.log(LogStatus.INFO, "ios-已登录");
				System.out.println("ios-已登录");
			}	
		}
        pullFileAndCompare("test_5710_ZADL");
    }

    /**
     * 订阅车源通知开关测试
     */
    @Test
    public void test_5900_dykg() {
        startMyTest("test_5900_dykg");
        if (os == 2) {
         	clickByCT(MobileBy.id("vgSetting"), 1, 2);
            reports_MyTest.log(LogStatus.INFO, "点击设置");
		}else if (os == 1) {
			clickByCT(By.name("设置"), 1, 1);
			reports_MyTest.log(LogStatus.INFO, "点击设置");
		}
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "set", "", "", "");
        if (os == 2) {
        	clickByCT(MobileBy.id("vgNoticeManagerid"), 1, 2);
            reports_MyTest.log(LogStatus.INFO, "点击推送通知");
        	clickByCT(MobileBy.id("ivSubSwitch"), 1, 2);
             reports_MyTest.log(LogStatus.INFO, "点击关闭\"订阅车源通知\"");
//           ev：close_push_set（仅安卓）（type=c）
             EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "close_push_set", "", "", "");      
             clickByCT(MobileBy.id("ivSubSwitch"), 1, 2);
             reports_MyTest.log(LogStatus.INFO, "点击开启\"订阅车源通知\"");
             EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "open_push_set", "", "", "");
        }else if (os == 1) {
        	clickByCT(By.name("推送通知"), 1, 1);
			reports_MyTest.log(LogStatus.INFO, "点击推送通知");
		}
        pullFileAndCompare("test_5900_dykg");
    }

    /**
     * 点击消息测试
     */
    @Test
    public void test_5100_xx() {
        startMyTest("test_5100_xx");
        if (os == 2) {
         	clickByCT(MobileBy.id("ivMessage"), 1, 2);
            reports_MyTest.log(LogStatus.INFO, "点击消息按钮");
		}else if (os == 1) {
			clickByCT(By.xpath("//XCUIElementTypeButton[@name='我的 信息']"), 1, 1);
			reports_MyTest.log(LogStatus.INFO, "点击消息按钮");
		}
        wait(1);
//        ev：message_center（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "message_center", "", "", "");
        if (os == 2) {
        	androidDriver.pressKeyCode(AndroidKeyCode.BACK);
            reports_MyTest.log(LogStatus.INFO, "点击返回");
		}else if (os == 1) {
			backBTN();
			reports_MyTest.log(LogStatus.INFO, "点击返回");
		}
//      ev：appoint_record（type=c）
        if (os == 2) {
        	clickByCT(MobileBy.id("rlWatchCarSchedule"), 1, 2);
            reports_MyTest.log(LogStatus.INFO, "点击看车日程");
		}else if (os == 1) {
//			clickByCT(By.xpath("//XCUIElementTypeApplication[@name='二手车']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]"), 1, 1);
//			getTextByChain("**/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]");
			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 3), 1, 1,"");
			reports_MyTest.log(LogStatus.INFO, "点击看车日程");
			wait(1);
		}
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "appoint_record", "", "","");
        pullFileAndCompare("test_5100_xx");
    }

    /**
     * 我的关注里点击关注列表中的第一条数据
     */
    @Test
    public void test_5710_wdsc() {
        startMyTest("test_5700_wdsc");
        if (os == 2) {
        	clickElementById("vgFavorateCar");
        	sleep(200);
            reports_MyTest.log(LogStatus.INFO, "点击我的关注");
		}else if (os == 1) {
//			clickByCT(By.xpath("//XCUIElementTypeApplication[@name='二手车']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeOther[2]/XCUIElementTypeButton[1]"), 1, 1);
			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 2), 1, 1,"");
			sleep(200);
			reports_MyTest.log(LogStatus.INFO, "点击我的关注");
		}
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "my_collect", "", "", "");
        String carid = null;
        String valid = null;
        if (os == 2) {
             //TODO:此点击走不通，不知道什么问题
        	wait(2);
          jsTapyDistance_X_Y_Matches("text", getListTextByXpath("//android.widget.TextView[contains(@text,'款')]", 0, "name"),0, 0, "+", "+", "进去点击第一条订阅中的第一辆车");
        	 reports_MyTest.log(LogStatus.INFO, "点击我的关注的车中的第一辆");
             sleep(500);
             carid = getCarid(getTextById("tvVehicleDetailCityName"));
             System.out.println("carid:" + carid);
             valid = getMyDataFromFile("valid");
             System.out.println("valid:" + valid);
		}else if (os == 1) {
			clickByCT(By.xpath("//XCUIElementTypeStaticText[contains(@name,'款')]"), 1, 1);
			reports_MyTest.log(LogStatus.INFO, "点击我的关注的车中的第一辆");
			carid = getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
		}
//        "ev：car_click_collect#carid=[value]/rank=1（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_click_collect#carid=" + carid + "/rank=1", "", "", "");
//        ev：vehicle_details_page#carid=[value]/valid=[value]（type=w）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "vehicle_details_page#carid="+carid+"/valid="+valid, "", "", "");
//        ev：browse_depth_detail#carid=[value]/pos=trend（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid=" + carid + "/pos=trend", "", "", "");
//        ev：browse_depth_detail#carid=[value]/pos=file（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid=" + carid + "/pos=file", "", "", "");
//        pl：similar_detail_expo（type=e）
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "similar_detail_expo", "", "", "");

//        pl：price_analyze_expo#carid=[value]/type=0（type=e）
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid=" + carid + "/type=0", "", "");

//        pl：car_trend_expo#carid=[value]（type=e）
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid=" + carid, "", "");
        if (os == 2) {
            androidDriver.pressKeyCode(AndroidKeyCode.BACK);
            reports_MyTest.log(LogStatus.INFO, "点击返回");
		}else if (os == 1) {
			backBTN();
			reports_MyTest.log(LogStatus.INFO, "点击返回");
		}
//		ev：detail_quit#carid=[value]/time=[value]（type=q）TODO:确定time字段
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "detail_quit#carid=" + carid + "/time=[value]", "", "", "");
        sleep(200);
        pullFileAndCompare("test_5700_wdsc");
    }

    /**
     * 点击浏览历史中的第一条数据
     */
    @Test
    public void test_5710_llls() {
        startMyTest("test_5710_llls");
        if (os == 2) {
        	 findElementById("vgHistoryCar").click();
        	 sleep(1000);
             reports_MyTest.log(LogStatus.INFO, "点击浏览历史");
		}else if (os == 1) {
//			clickByCT(By.xpath("//XCUIElementTypeApplication[@name='二手车']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeOther[2]/XCUIElementTypeButton[3]"), 1, 1);
			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 4), 1, 1,"");
			reports_MyTest.log(LogStatus.INFO, "点击浏览历史");
		}
//		ev：browse_history（type=c)
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_history", "", "", "");
        String carid = null;
        Boolean valid = false;//默认为valid=1
        if (os == 2) {
        	findElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]"
        			+ "/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]"
        			+ "/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]"
        			+ "/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.ListView[1]"
        			+ "/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]").click();
            reports_MyTest.log(LogStatus.INFO, "点击浏览历史中的第一辆");
            sleep(200);
            carid = getCarid(getTextById("tvVehicleDetailCityName"));
            valid = Boolean.parseBoolean(getMyDataFromFile("valid"));
		}else if (os == 1) {
			clickByCT(By.xpath("//XCUIElementTypeStaticText[contains(@name,'款')]"), 1, 1);
			reports_MyTest.log(LogStatus.INFO, "点击我的关注的车中的第一辆");
			carid = getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
		}
//		"ev：car_click_history#carid=[value]/rank=1（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_click_history#carid=" + carid + "/rank=1", "", "", "");
//		ev：Page/vehicle_details/[carid]/valid/[value]（type=w）
        EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid=" + carid + "/valid=" + (valid ? "0" : "1"), "", "", "");
//		ev：browse_depth_detail#carid=[value]/pos=trend（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid=" + carid + "/pos=trend", "", "", "");
//		pl：car_trend_expo#carid=[value]（type=e）
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid=" + carid, "", "");
//		pl：bottomparice#carid=[value]（type=e）
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid=" + carid, "", "");
        //        pl：similar_detail_expo（type=e）

        //TODO:客户端少带了/
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "similar_detail_expo", "", "", "");
//		pl：price_analyze_expo#carid=[value]/type=0（type=e）"
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid="+carid+"/type=0", "", "");

        //        pl：newcar_detail_expo（type=e）不确定有无"
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");

        sleep(200);
        pullFileAndCompare("test_5710_llls");
    }


//    /**
//     * 点击看车日程埋点路径
//     */
//    @Test
//    public void test_5730_kcrc() {
//        startMyTest("test_5730_kcrc");
//        findElementById("vgOrder").click();
//        //ev：appoint_record（type=c）
//        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "appoint_record", "", "", "");
//        sleep(200);
//        findElementById("rb_new_car").click();
//        sleep(200);
//        findElementById("rb_user_car").click();
//        sleep(200);
//        driver.quit();
//
//    }

    /**
     * 点击我的订阅的埋点路径
     */
    @Test
    public void test_5720_wddy() {
        startMyTest("test_5720_wddy");
        String carid = null;
        Boolean valid = false;
        if (os == 2) {
//            findElementById("vgSubscribe").click();
          	clickByCT(MobileBy.xpath("//android.widget.TextView[@text='我的订阅']"), 1, 2);
            reports_MyTest.log(LogStatus.INFO, "点击我的订阅");
        }else if (os == 1) {
//        	clickByCT(By.xpath("//XCUIElementTypeApplication[@name='二手车']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeOther[2]/XCUIElementTypeButton[4]"), 1, 1);
        	clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 5), 1, 1,"");
        	reports_MyTest.log(LogStatus.INFO, "点击我的订阅");
		}    
//      ev：subscribe_my（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "subscribe_my","","","");
        if (os == 2) {
        	jsTapyDistance_X_Y_Matches("text", getListTextByXpath("//android.widget.TextView[contains(@text,'款')]", 0, "name"),0, 0, "+", "+", "进去点击第一条订阅中的第一辆车");
            reports_MyTest.log(LogStatus.INFO, "点击第一条订阅中的第一辆车");
            sleep(200);
        }else if (os == 1) {
        	clickByCT(By.xpath("//XCUIElementTypeStaticText[contains(@name,'款')]"), 1, 1);
        	reports_MyTest.log(LogStatus.INFO, "点击第一条订阅中的第一辆车");
            sleep(200);
            carid = getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
		}   
//        "ev：car_click_subscript#carid=[value]/from=1/rank=1（type=c）
//        ev：browse_depth_detail#carid=[value]/pos=trend（type=c）
//        ev：browse_depth_detail#carid=[value]/pos=file（type=c）
//        ev：vehicle_details_page#carid=[value]/valid=[value]（type=w）
//        pl：bottomparice#carid=[value]（type=e）
//        pl：price_analyze_expo#carid=[value]/type=[value]（type=e）
//        pl：car_trend_expo#carid=[value]（type=e）
//        pl：similar_detail_expo（type=e）
//        pl：newcar_detail_expo（type=e）不确定有无"

//		"ev：car_click_subscript#carid=[value]/from=1/rank=1（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "car_click_subscript#carid=" + carid + "/from=1/rank=1", "", "", "");
//		ev：browse_depth_detail#carid=[value]/pos=trend（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid=" + carid + "/pos=trend", "", "", "");
//		ev：browse_depth_detail#carid=[value]/pos=file（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid=" + carid + "/pos=file", "", "", "");
//        ev：vehicle_details_page#carid=[value]/valid=[value]（type=w）
        EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid="+valid,"","","");
//		pl：bottomparice#carid=[value]（type=e）
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid=" + carid, "", "");
//		pl：price_analyze_expo#carid=[value]/type=[value]（type=e）
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid=" + carid + "/type=0", "", "");
//		pl：car_trend_expo#carid=[value]（type=e）
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid=" + carid, "", "");
//// 9.3.1去掉		pl：vehicle_details/similar_expo（type=e）"
//        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "vehicle_details/similar_expo", "", "");

        //        pl：similar_detail_expo（type=e）
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"similar_detail_expo","","","");
//        pl：newcar_detail_expo（type=e）不确定有无"
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "newcar_detail_expo","","","");
        if (os == 2) {
        	 sleep(200);
             androidDriver.pressKeyCode(AndroidKeyCode.BACK);
             sleep(200);
             reports_MyTest.log(LogStatus.INFO, "点击返回");
		}else if (os == 1) {
			backBTN();
			reports_MyTest.log(LogStatus.INFO, "点击返回");
		}
//		ev：detail_quit#carid=[value]/time=[value]（type=q） TODO:time需要单独摘出来
        EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "detail_quit#carid=" + carid + "/time=[value]", "", "", "");
        if (os == 2) {
        	findElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/" +
                    "android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/" +
                    "android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/" +
                    "android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.ListView[1]/" +
                    "android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/" +
                    "android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]").click();
        	 reports_MyTest.log(LogStatus.INFO, "点击更多进入订阅列表页");
             sleep(200);
		}else if (os == 1) {
			if(CheckViewVisibilty(By.name("暂无更新"))) {
				clickByCT(By.name("暂无更新"), 1, 1);
			}else {
				String str =getListTextByXpath("//XCUIElementTypeStaticText[contains(@name,'更新')]", 0, "name","");
//				System.out.println(str);
				clickWD("wdType == 'element1' AND name == 'element2'", "XCUIElementTypeStaticText", str, 1, 2);
				
//				clickByCT(By.xpath("//XCUIElementTypeStaticText[contains(@name,'更新')]"), 1, 1);
			}
			reports_MyTest.log(LogStatus.INFO, "点击更多进入订阅列表页");
            sleep(200);
		}
//		"ev：mypage/subscribe/viewall（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "mypage/subscribe/viewall", "", "", "");
//		pl：carlist_expo#class=1/result=1/page=2（type=e）"TODO:type
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo#class=1/result=1/page=2", "", "");
        if (os == 2) {
        	jsTapyDistance_X_Y_Matches("text", getListTextByXpath("//android.widget.TextView[contains(@text,'款')]", 0, "name"),0, 0, "+", "+", "进去点击第一条订阅中的第一辆车");
        	reports_MyTest.log(LogStatus.INFO, "点击订阅列表中的第一辆车");
		}else if (os == 1) {
			clickByCT(By.xpath("//XCUIElementTypeStaticText[contains(@name,'款')]"), 1, 1);
			reports_MyTest.log(LogStatus.INFO, "点击订阅列表中的第一辆车");
		}
//		"ev：car_click_subscript#carid=[value]/from=2/rank=1
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "carlist_expo#class=1/result=1/page=2", "", "", "");
//		ev：Page/vehicle_details/[carid]/valid/[value]（type=c）9.3.1替换掉
//        vehicle_details_page#carid=[value]/valid=[value]（type=w）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, " vehicle_details_page#carid=" + carid + "valid="+valid, "", "", "");
//		ev：browse_depth_detail#carid=[value]/pos=trend（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid=" + carid + "/pos=trend", "", "", "");
//		ev：browse_depth_detail#carid=[value]/pos=file（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid=" + carid + "/pos=file", "", "", "");
//		pl：bottomparice#carid=[value]（type=e）
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid=" + carid, "", "");

//        pl：similar_detail_expo（type=e）
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW,"","similar_detail_expo","","");
////		pl：vehicle_details/similar_expo（type=e）9.3.1去掉
//        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "vehicle_details/similar_expo", "", "");
//		pl：car_trend_expo#carid=[value]（type=e）
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid=" + carid, "", "");
//		pl：price_analyze_expo#carid=[value]/type=[value]（type=e）"TODO:type
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid=" + carid + "/type=0", "", "");
//        pl：newcar_detail_expo（type=e）不确定有无
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "","newcar_detail_expo","","");
        if (os == 2) {
            androidDriver.pressKeyCode(AndroidKeyCode.BACK);
            reports_MyTest.log(LogStatus.INFO, "点击返回");
            sleep(200);
		}else if (os == 1) {
			backBTN();
			reports_MyTest.log(LogStatus.INFO, "点击返回");
            sleep(200);
		}
//		"ev：carlist_page#type=0/from=3（type=w）
        EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carlist_page#type=0/from=3", "", "", "");
//		ev：detail_quit#carid=[value]/time=[value]（type=q）
        EventManager.sendPoint(EventManager.UXIN_EVENT_QUIT, "carlist_page#type=0/from=3", "", "", "");
//		pl：carlist_expo_new#class=1/result=1/page=2（type=e）"
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "carlist_expo_new#class=1/result=1/page=2", "", "");
        if (os == 2) {
        	 reports_MyTest.log(LogStatus.INFO, "点击返回");
             androidDriver.pressKeyCode(AndroidKeyCode.BACK);
             findElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/" +
                     "android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/" +
                     "android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/" +
                     "android.widget.LinearLayout[2]").click();
             reports_MyTest.log(LogStatus.INFO, "点击添加订阅按钮");
		}else if (os == 1) {
			backBTN();
			reports_MyTest.log(LogStatus.INFO, "点击返回");
			clickByCT(By.name("新增订阅"), 1, 1);
			reports_MyTest.log(LogStatus.INFO, "点击新增/添加订阅按钮");
		}
////        "ev：mypage/subscribe/add（type=c）9.3.1去掉
//        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "mypage/subscribe/add", "", "", "");
////        ev：more_filter_reset#page=2（type=c）"9.3.1去掉
//        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "more_filter_reset#page=2", "", "", "");

//        ev：add_my_subscribe（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "add_my_subscribe","","","");
        sleep(200);
        pullFileAndCompare("test_5720_wddy");
    }

    /**
     * 点击我买的车的打点路径
     */
    @Test
    public void test_5510_wmdc() {
        startMyTest("test_5510_wmdc");
        if (os == 2) {
        	 findElementById("vgUserMyBuyCarDefault").click();
        	 sleep(200);
             reports_MyTest.log(LogStatus.INFO, "点击我买的车");
		}else if (os == 1) {
			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 6), 1, 1,"");
			reports_MyTest.log(LogStatus.INFO, "点击我买的车");
		}
//        ev：my_buycar（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "my_buycar", "", "", "");
        if (os == 2) {
        	androidDriver.pressKeyCode(AndroidKeyCode.BACK);
        	reports_MyTest.log(LogStatus.INFO, "点击返回");
            findElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.Linear" +
                    "Layout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/an" +
                    "droid.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.Scroll" +
                    "View[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[4]/android.widget.RelativeLayout[1]").click();
            reports_MyTest.log(LogStatus.INFO, "点击优信养车");
		}else if (os == 1) {
			backBTN();
			reports_MyTest.log(LogStatus.INFO, "点击返回");
			clickByCT(By.name("优信养车"), 1, 1);
			reports_MyTest.log(LogStatus.INFO, "点击优信养车");
		}
//        ev：/mypage/maintenance（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "/mypage/maintenance", "", "", "");
        pullFileAndCompare("test_5510_wmdc");
    }

//    /**
//     * 优信养车
//     */
//    @Test
//    public void test_5520_yxyc() {
//        startMyTest("test_5520_yxyc");
//        findElementById("tvUserMaintenanceDesc").click();
////        ev：/mypage/maintenance（type=c）
//        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "/mypage/maintenance", "", "", "");
//        sleep(2000);
//        findElementById("ivRefresh").click();
//        sleep(200);
//
//        driver.quit();
//    }

    /**
     * 测试我卖的车-预约卖车的埋点路径
     */
    @Test
    public void test_5530_wmaidc() {
        //TODO:后面全部都是卖车发布流程，需找文芳确认
        startMyTest("test_5530_wmaidc");
        if (os == 2) {
        	findElementById("vgPublishCar").click();
        	sleep(200);
            reports_MyTest.log(LogStatus.INFO, "点击我卖的车");
		}else if (os == 1) {
			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 7), 1, 1,"");
			reports_MyTest.log(LogStatus.INFO, "点击我卖的车");
		}
//        "ev：my_sellcar（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "my_sellcar", "", "", "");
//        ev：sellcar_record_page#from=1（type=w）"
        EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "sellcar_record_page#from=1", "", "", "");
        if (os == 2) {
        	androidDriver.pressKeyCode(AndroidKeyCode.BACK);
            reports_MyTest.log(LogStatus.INFO, "返回");
            findElementById("vgMyQuestion").click();
            reports_MyTest.log(LogStatus.INFO, "点击我的宝典");
		}else if (os == 1) {
			backBTN();
			reports_MyTest.log(LogStatus.INFO, "返回");
			clickByCT(By.name("我的宝典"), 1, 1);
			reports_MyTest.log(LogStatus.INFO, "点击我的宝典");
		}
//      ev：my_baodian（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "my_baodian", "", "", "");
        sleep(200);
        pullFileAndCompare("test_5530_wmaidc");
    }

//    @Test
//    public void test_5540_wdtw() {
//        startMyTest("test_5540_wdtw");
//
//        findElementById("vgPublishCar").click();
////        "ev：my_sellcar（type=c）
////        ev：sellcar_record_page#from=1（type=w）"
//        sleep(200);
//        androidDriver.pressKeyCode(AndroidKeyCode.BACK);
//        sleep(200);
//
//        findElementById("vgMyQuestion").click();
////        ev：my_baodian（type=c）
//
//
//    }

    /**
     * 点击估价
     */
    @Test
    public void test_5620_yxgj() {
        startMyTest("test_5620_yxgj");
        if (os == 2) {
            reports_MyTest.log(LogStatus.INFO, "点击常用工具");
             clickByCT(MobileBy.xpath("//android.widget.TextView[@text='常用工具']"), 1, 2);
        }else if (os == 1) {
        	clickByCT(By.name("常用工具"), 1, 1);
        	reports_MyTest.log(LogStatus.INFO, "点击常用工具");
		}
        //  ev：tool_my（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"tool_my","","","");
        if(os == 2) {
            reports_MyTest.log(LogStatus.INFO, "点击我要还款");
            clickByCT(MobileBy.xpath("//android.widget.TextView[@text='我要还款']"), 1, 2);
        } else if(os == 1) {
//        	clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 7), 1, 1,"");
        	clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 1), 1, 1,"");
        	reports_MyTest.log(LogStatus.INFO, "点击我要还款");
        }
//        ev：intro_tool#rank=1（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "intro_tool#rank=1", "", "", "");
        if(os == 2) {
            reports_MyTest.log(LogStatus.INFO, "点击返回");
            androidDriver.pressKeyCode(AndroidKeyCode.BACK);
            reports_MyTest.log(LogStatus.INFO, "点击车辆估价");
            clickByCT(MobileBy.xpath("//android.widget.TextView[@text='车辆估价']"), 1, 2);
        } else if(os == 1) {
        	backBTN();
        	reports_MyTest.log(LogStatus.INFO, "点击返回");
//        	clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 8), 1, 1,"");
        	clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 2), 1, 1,"");
        	reports_MyTest.log(LogStatus.INFO, "点击车辆估价");
        }
//        "ev：intro_tool#rank=2（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "intro_tool#rank=2", "", "", "");
//        ev：carinfo_evaluate#type=D（type=w）
        EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carinfo_evaluate#type=D", "", "", "");
//        ev：evaluate_car#button=2（type=c）"
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "evaluate_car#button=2", "", "", "");
        if (os == 2) {
        	 reports_MyTest.log(LogStatus.INFO, "点击估价历史");
             findElementById("btManage").click();
             sleep(200);
		}else if (os == 1) {
			clickByCT(By.name("估价历史"), 1, 1);
			reports_MyTest.log(LogStatus.INFO, "点击估价历史");
			sleep(200);
		}
//        ev：evaluate_history（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "evaluate_history", "", "", "");
        if (os == 2) {
        	 reports_MyTest.log(LogStatus.INFO, "点击清空");
             findElementById("btManage").click();
             sleep(200);
		}else if (os == 1) {
			clickByCT(By.name("清空"), 1, 1);
			reports_MyTest.log(LogStatus.INFO, "点击清空");
			sleep(200);
		}
//        ev：evaluate_empty（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "evaluate_empty", "", "", "");
        if (os == 2) {
        	 if (CheckViewVisibilty(MobileBy.id("alertdialog_confirm"))) {
        		 	reports_MyTest.log(LogStatus.INFO, "点击确定");
        		 	clickByCT(MobileBy.id("alertdialog_confirm"), 1, 2);
        		 	wait(1);
			}
            reports_MyTest.log(LogStatus.INFO, "点击现在估价");
            clickByCT(MobileBy.id("btEmptyMsgButton"), 1, 2);
		}else if (os == 1) {
			if(CheckViewVisibilty(By.name("确定"))) {
				reports_MyTest.log(LogStatus.INFO, "点击确定");
				clickByCT(By.name("确定"), 1, 1);
				wait(1);
			}
			reports_MyTest.log(LogStatus.INFO, "点击现在估价");
            clickByCT(MobileBy.name("现在估价"), 1, 2);
		}
//        "ev：evaluate_car#button=4（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "evaluate_car#button=4", "", "", "");
//        ev：carinfo_evaluate#type=E（type=w）"
        EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "carinfo_evaluate#type=E", "", "", "");
        if (os == 2) {
        	  reports_MyTest.log(LogStatus.INFO, "点击品牌车系");
        	      clickByCT(MobileBy.id("tv_car_name"), 1, 2);
              reports_MyTest.log(LogStatus.INFO, "点击大众");
              clickByCT(MobileBy.xpath("//android.widget.TextView[@text='大众']"), 1, 2);
              reports_MyTest.log(LogStatus.INFO, "点击捷达");
              clickByCT(MobileBy.xpath("//android.widget.TextView[@text='捷达']"), 1, 2);
              reports_MyTest.log(LogStatus.INFO, "2017款 1.4 手动 舒适型");
              clickByCT(MobileBy.xpath("//android.widget.TextView[@text='2017款 1.4 手动 舒适型']"), 1, 2);
              reports_MyTest.log(LogStatus.INFO, "点击行驶里程 输入25万公里");
              inputById("et_vehicle_condition", "25");
              sleep(200);
//              reports_MyTest.log(LogStatus.INFO, "点击开始估价");
//              findElementById("btn_post").click();
        }else if (os == 1) {
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
//        ev：evaluate_inquiry#valid=0/from=4（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "evaluate_inquiry#from=4/tel_num=14725836912/valid=2", "", "", "");
        sleep(200);
        pullFileAndCompare("test_5620_yxgj");
    }

    /**
     * 点击下部的工具箱包括我的油卡、优信认证、限迁标准
     */
    @Test
    public void test_5630_gjx() {
        startMyTest("test_5630_gjx");
        if(os == 2) {
            reports_MyTest.log(LogStatus.INFO, "点击常用工具");
            clickByCT(MobileBy.xpath("//android.widget.TextView[@text='常用工具']"), 1, 2);
        } else if(os == 1) {
        	clickByCT(By.name("常用工具"), 1, 1);
        	reports_MyTest.log(LogStatus.INFO, "点击常用工具");
        }
        //            ev：tool_my（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK,"tool_my","","","");
        wait(1);
        if (os == 2) {
            reports_MyTest.log(LogStatus.INFO, "点击我的油卡");
            clickByCT(MobileBy.xpath("//android.widget.TextView[@text='我的油卡']"), 1, 2);
		}else if (os == 1) {
//			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"),9), 1, 1,"");
			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"),3), 1, 1,"");
			reports_MyTest.log(LogStatus.INFO, "点击我的油卡");
		}
        wait(1);
//        ev：intro_tool#rank=3（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "intro_tool#rank=3", "", "", "");
        if (os == 2) {
        	wait(2);
             reports_MyTest.log(LogStatus.INFO, "点击返回");
             androidDriver.pressKeyCode(AndroidKeyCode.BACK);
             sleep(200);
             reports_MyTest.log(LogStatus.INFO, "点击优信认证");
             clickByCT(MobileBy.xpath("//android.widget.TextView[@text='优信认证']"), 1, 2);
 		}else if (os == 1) {
 			backBTN();
 			reports_MyTest.log(LogStatus.INFO, "点击返回");
 			sleep(200);
// 			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 11), 1, 1,"");
 			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 5), 1, 1,"");
 			reports_MyTest.log(LogStatus.INFO, "点击优信认证");
		}
//        ev：intro_tool#rank=5（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "intro_tool#rank=5", "", "", "");
        if (os == 2) {
        	wait(2);
            reports_MyTest.log(LogStatus.INFO, "点击返回");
            androidDriver.pressKeyCode(AndroidKeyCode.BACK);
            wait(3);
            reports_MyTest.log(LogStatus.INFO, "点击限迁标准");
            clickByCT(MobileBy.xpath("//android.widget.TextView[@text='限迁标准']"), 1, 2);
		}else if (os == 1) {
			backBTN();
 			reports_MyTest.log(LogStatus.INFO, "点击返回");
 			wait(3);
//			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 12), 1, 1,"");
			clickByLocator(getListByLocator(MobileBy.xpath("//XCUIElementTypeButton[contains(@type,'XCUIElementTypeButton')]"), 6), 1, 1,"");
			reports_MyTest.log(LogStatus.INFO, "点击限迁标准");
		}
//        ev：intro_tool#rank=6（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "intro_tool#rank=6", "", "", "");
        if (os == 2) {
        	sleep(2000);
            reports_MyTest.log(LogStatus.INFO, "点击返回");
            androidDriver.pressKeyCode(AndroidKeyCode.BACK);
		}else if (os == 1) {
			backBTN();
 			reports_MyTest.log(LogStatus.INFO, "点击返回");
		}
        pullFileAndCompare("test_5630_gjx");
    }

    /**
	 * @Name 5640_CNXH
	 * @catalogue 我的-猜你喜欢，点击猜你喜欢任意车辆进入详情页检查埋点
	 * @Grade 高级 
	 */
    @Test
    public void test_5640_CNXH() {
        startMyTest("test_5640_CNXH");
        sleep(1000);
       if (os == 2) {
	    	// 获取屏的宽度
	    	int width = androidDriver.manage().window().getSize().width;
	    	// 获取屏的高度
	    	int height = androidDriver.manage().window().getSize().height;
	    	System.out.println("上滑一屏，开始位置"+height*6/10+"结束位置"+height*1/10);
			androidDriver.swipe(width/2, height*6/10, width/2, height*1/10, 1000);
       }else if (os == 1) {
    	    sliding("up");
       }
        sleep(500);
        String carid = null;
        String valid = null;
        if (os == 2) {
			clickElementById("tvCarWholeName");
			sleep(3000);
			carid = findElementById("tvVehicleDetailCityName").getAttribute("text").split(":")[1];
			valid = getMyDataFromFile("valid");
			reports_MyTest.log(LogStatus.INFO, "点击猜你喜欢列表中的第一辆车");
		}else if (os == 1) {
			scrollToElementClick(getListTextByLocator(MobileBy.iOSNsPredicateString("wdType == 'XCUIElementTypeStaticText' AND wdName CONTAINS[cd] '款' "), 0, "name"), 2,"TRUE");
			 reports_BuyCarTest.log(LogStatus.INFO, "点击第1辆浏览历史的车获取carid");
			carid=getTextByChain("**/XCUIElementTypeStaticText[`name CONTAINS[cd] '编号'`]").split(":")[1];
		}
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "rank=1/carid="+carid+"/type=[value]/icon=[value]/label=[value]/video=[value]", "", "", "");
        EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "vehicle_details_page#carid="+carid+"/valid="+(Boolean.parseBoolean(valid)?"0" : "1"), "", "", "");
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "similar_detail_expo", "", "");
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=file", "","", "");
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "browse_depth_detail#carid="+carid+"/pos=trend", "","", "");
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "bottomparice#carid="+carid, "", "");
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "car_trend_expo#carid="+carid, "", "");
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "price_analyze_expo#carid="+carid+"/type=[value]", "", "");
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "newcar_detail_expo", "", "");
        pullFileAndCompare("test_5640_CNXH");
    }

//    /**
//     * 点击违章查询
//     */
//    @Test
//    public void test_5640_wzcx() {
//        startMyTest("test_5640_wzcx");
//        slidingInElement(findElementById("userFragmentSV"), "up");
//        sleep(3000);
//        findElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/" +
//                "android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/" +
//                "android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]" +
//                "/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[4]" +
//                "/android.widget.FrameLayout[1]/android.support.v7.widget.RecyclerView[1]/android.widget.RelativeLayout[4]").click();
////        ev：intro_tool#rank=4（type=c）
//        sleep(2000);
//        findElementById("tvCity").click();
//        sleep(200);
//        findElementByXpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/" +
//                "android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/" +
//                "android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[2]/" +
//                "android.widget.TextView[1]").click();
//        sleep(200);
//
//        findElementById("tvCommit").click();
//
//        sleep(1000);
//
//        driver.quit();
//
//
//    }

    /**
     * 传过来一个完整的带中文的字符串需要把中文部分给截断
     *
     * @param text
     * @return
     */
    private String getCarid(String text) {
    	 String carid = text.split(":")[1];
        return carid;
    }

    private void statisticStart() {

        //        "ev：start（type=a）
        EventManager.saveLifeCycleEvent(EventEntity.LifeCycleEvent.APP_START);
        //android端多带了一个斜杠
        //        ev：openapp/start_click（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "openapp/start_click", "", "", "");
        //        ev：home_page（type=w）
        EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "home_page", "", "", "");
        //        pl：guess_like_expo#algorithm=bus（type=e）"
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "", "guess_like_expo#algorithm=bus", "", "");
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
            Compare compare = new Compare(this.reports_MyTest);
            compare.compare("./expected_iOS/" + test + ".txt", "./actual_iOS/statistic.json", test);
		}else if (os == 2) {//android对比
			callBriadcastAndPullFile();
			Compare compare = new Compare(this.reports_MyTest);
			compare.compare("./expected/"+test+".txt", "./actual/statistic.json",test);
		}   
	}
	 
    private void startMyTest(String test) {
        EventManager.initEventManager();
        reports_MyTest.startTest(test);
        EventManager.fileName = test + ".txt";
        wait(8);
//        androidDriver.pressKeyCode(AndroidKeyCode.BACK);
        statisticStart();
//        reports_MyTest.log(LogStatus.INFO, "关闭升级弹窗");
        sleep(200);
        if(os ==2) {
        	clickElementById("rbWo");
        }else if (os ==1) {
			iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name='我的']")).click();
		}
        sleep(200);
//        "ev：my_bottom（type=c）
        EventManager.sendPoint(EventManager.UXIN_EVENT_CLICK, "my_bottom","","","");
//        ev：my_page（type=w）
        EventManager.sendPoint(EventManager.UXIN_EVENT_PAGE, "my_page","","","");
//        pl：car_guesslike_my_expo（type=e）"
        EventManager.sendPoint(EventManager.UXIN_EVENT_SHOW, "car_guesslike_my_expo","","","");

        reports_MyTest.log(LogStatus.INFO, "点击我的tab，进入我的页面");
    }

    private String getMyDataFromFile(String name) {
        try {

            sleep(3000);

            Process process = Runtime.getRuntime().
                    exec("/Users/uxin/Desktop/00Build_Android/android-sdk-macosx/platform-tools/adb pull /storage/Download/prepare.json ./actual/prepare.json");
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

}
