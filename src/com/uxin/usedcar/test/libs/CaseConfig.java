package com.uxin.usedcar.test.libs;

import java.io.File;

import org.junit.internal.runners.statements.Fail;

import com.google.common.base.FinalizablePhantomReference;

public class CaseConfig {
	
		//账号信息
		public static final String USERNAME_PUBLIC = "14725836914";
		public static final String USERNAME_BEIYONG1 = "14725836911";
		public static final String USERNAME_BEIYONG2 = "14725836912";
		//密码
		public static final String PASSWORD = "666666";
		public static final String BUNDLEID = "com.autounion.usedcar";
		public static final int  WDAPORT = 2770;
		//liyifeng server
		public static final String SERVICEURL_LYF = "http://192.168.92.152:4723/wd/hub";
		//liyifeng device
		public static final String UDID_LYF = "6b147ffd36b13b105afd97f136f827b21a55a8f0";
		public static final String UDID_LYW = "1851e96c01d50f6fbc2032a31a4e903181cdc159";
		public static final String UDID_LZX = "4df9b6366f9950f6ae7c73e81a776493cc9021d5";
		public static final String UDID_HYW = "6b147ffd36b13b105afd97f136f827b21a55a8f0";
		//liyifeng deviceIosVersion
		public static final String IOS_VERSION="10.3.1";
		//liyiwan device
		public static final String SERVICEURL_LYW = "http://127.0.0.1:4770/wd/hub";
		public static final String SERVICEURL_HYW = "http://192.168.92.152:4723/wd/hub";
		public static String sTestScreenshotName="";
		public static final String xcodeConfigFile = "CODE_SIGN_IDENTITY = iPhone Developer DEVELOPMENT_TEAM = 9H4SUA4596";
		/** 跳过欢迎页状态 */
		public static final String SKIPHELLOAPGESTATE = "欢迎页状态";
		/** 跳过欢迎页状态 - 已跳过欢迎页面 */
		public static final String SKIPHELLOPAGESTATE_SKIPED = "已经跳过欢迎页";
		/**登录状态*/
		public static final String LOGIN = "登录状态";
		/** 登陆状态 - 已登陆 */
		public static final String LOGINSTATE = "已登录账号";
		/** 登陆状态 - 未登陆 */
		public static final String LOGINSTATE_UNLOGIN = "未登录";
		/** 启动状态- 首次启动 */
		public static final String START_STATE = "启动状态";
		/** 启动状态- 非首次启动 */
		public static final String START_UNFIRST = "非首次启动";
		
		//步骤执行成功
		public static final String STEPRESULTPASS="PASS";
		public static final String STEPRESULTFAIL="FAIL";
		//每步执行成功与否标志
		public static String STEPRESULT="";
		//执行步骤名称
		public static String STEPNAME="";
		
		public static String ReportPath=System.getProperty("user.dir")+"/report/HomePage/reportHomePage.html";
//		public static String ReportPath=System.getProperty("user.dir")+"/report/HomePage"+reportType+"*.html";
	
	  public final static String CATCH_PICTURE = "./ExceptionPic";
	  public static String sResult="";
	  public static String sTestCase_Data="";
	  public static String sTestCase_CheckData="";
	  public static String TestStepResult="";
	  public static int AB;
	  public static int  originHeight=0;
	  public static int TestSuitePass=0;
	  public static int TestSuiteFail=0;
	  public static int TestCasePass=0;
	  public static int TestCaseFail=0;		
      public static int TestStepPass=0;
      public static int TestStepFail=0;
	  public static  String SuiteName= "";     //测试套件的名称
	  public static  String Suite_DataIndex= "";  //测试数据索引
	  public static  String Suite_module= "";  //测试模块名称
	  public static  String Suite_CaseName= ""; //测试用例名称
	  public static  String Test_FileName=""; //测试用例文件名称
	  public static String Suite_Loop=""; //循环次数
	  public static  String Plan_Suite= ""; 
	  public static  String Plan_RunModel= ""; 
	  public static int    iTestCase_Step=0;
	  public static int    iTestCase_Type=4;
	  public static int    iTestCase_ObjectName=3;
	  public static int    iTestCase_Object=5;
	  public static int    iTestCase_Operation=6;
	  public static int    iTestCase_Data=7;
	  public static int    iTestCase_CheckData=8;
	  public static int    iTestCase_DriverType=9;
	  public static String    sTestCase_Step="";
	  public static String    sTestCase_Type="";
	  public static String    sTestCase_ObjectName="";
	  public static String    sTestCase_Object="";
	  public static String   sTestCase_Operation="";
	  public static String    sTestCase_DriverType="";
	  public static String  TestResultDesc="";
	  public static String  TestACTIONVALUE="";
	  public static String OldTime;
	  public static String FirstTime="";
	  public static String secondTime="";
	  public static String FirstTime_Str="";
	  public static String secondTime_Str="";
	  
	  
	  public static String Num1;
	  
	  public static String Num2;
	  
	  
	  public static String Num3;
	  
	  public static String Num4;
	//郑光宪 iPhoneX (11.2.1) [4df9b6366f9950f6ae7c73e81a776493cc9021d5]
	//lixueqiong (11.1.2) [b8cf8bea07d2b25bf3b0ae8a129a5718f9c29c0c]
	//生成iPhoneX 脚本的App占用屏幕尺寸(375,812)
//			public static final  double originScreenWidth = 375.0;
//			public static final  double originScreenHeight = 812.0;
			
	//李想的 iPhone (11.2.5) [1851e96c01d50f6fbc2032a31a4e903181cdc159]
	//生成iPhone 6S Plus  iPhone7 脚本的App占用屏幕尺寸(414,736)
			public static final  double originScreenWidth = 414.0;
			public static final  double originScreenHeight = 736.0;
			
			
			
			public static  double CURRENTScreenWidth = 414.0;//先默认赋值
			public static  double CURRENTScreenHeight = 736.0;
			
	//李想的 iPhone (10.3.3) [bbf063c11ce3a1756fb69dafff1db5656d1c5270]
	//生成iPhone 6  7 脚本的App占用屏幕尺寸(375,667)
//			public static final  double originScreenWidth = 375.0;
//			public static final  double originScreenHeight = 667.0;
			
	//yiwan的 iPhone (10.3.3) [67755bb14d4bd855e33bc30b421c65f6e0fbce16]
	//lyw (9.3.5) [2e937288fcf6456d5fa37c78600949ed704ff128]

			//生成iPhone 4S脚本的App占用屏幕尺寸(320,568)
//			public static final  double originScreenWidth = 320.0;
//			public static final  double originScreenHeight = 568.0;
			
			
			//详情页时间
			public static final int TIMES_FYB = 20;
			public static int CallSIM = 0;
			public static String FOCUS = null;
			public static String BARGAIN_0 = null;
			public static Object FINANCIAL = null;
			public static int SDKVERSION = 11;
			public static int STATICTEXT = 2;
			public static String NAVIGATIONBAR="XCUIElementTypeOther[1]"; //默认该值为iOS11以后的系统 如果iOS10 则为XCUIElementTypeStaticText[1]
			
			public static boolean VISIBLE_STATE=true ;
			
			//车源编号
			public static String CAR_NO = "";
			//首页所在城市
			public static String CITY_NAME = "";
			public static String currentCityName;
			//1⃣️ 新车一成购  2⃣️二手车一成购  3⃣️热门推荐
			public static String SW1 = "";
			//1⃣️ 三成首付 • 还款灵活  2⃣️⃣️热门推荐
			public static String SW2 = "";
			//1⃣️二手车一成购  2⃣️⃣️热门推荐
			public static String SW3 = "";
//			public static final String BUNDLEID = "com.youxinpai.yxused";
//			public static final String BUNDLEID = "com.nc.uxinusednew";
//			wdaLocalPort
			//首页新车一成购		
			public static String P1="";//新车一成购1
			public static String P2="";//新车一成购2
			public static String P3="";//新车一成购3
			public static String P4="";//新车一成购4
			public static String P5="";//二手车一成购1
			public static String P6="";//二手车一成购2
			public static String P7="";//二手车一成购3
			public static String P8="";//二手车一成购4
			
			public static String PN="";//WEB详情页首付价格
			public static String CORRECT="";//纠正价格
			
			public static String CARSERIES1="";//新车一成购 车型车系名称
			public static String CARSERIES2="";
			public static String CARSERIES3="";
			public static String CARSERIES4="";
			public static int OS=2;
			public static int CENTER_X;
			public static int CENTER_Y;
			public static String CARSERIES;
			public static String DPM;
			public static String DPM_MMTAGE;
			public static String PUT;
			public static String MMTAGE;
			public static String AppointCar;
			public static String BARGAIN_1;
			public static String DISCOUNT;
			public static String DISCOUNT_1;
			public static String XDJ;
			public static String XDJ_1;
			public static String C_1;
			public static String C;
			public static String D;
			public static String GPS;
			public static String TableViewTypeOther;
			public static String VALIDJSON;
	  
	  
}
