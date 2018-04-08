package com.uxin.usedcar.maidianlib;

import com.google.gson.Gson;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.uxin.usedcar.test.libs.BaseTest;
import com.uxin.usedcar.test.libs.ftpUtil;

import java.io.*;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Tab;

import org.apache.commons.exec.OS;
import org.apache.http.impl.cookie.BestMatchSpec;


/**
 * =
 * Created by xianeng on 2018/2/28.
 */
public class Compare {

    public ExtentReports report;

    public Compare(ExtentReports report) {
        this.report = report;
    }

    public static void main(String[] args) {

    }

    //对比方法，不通过过时输出错误，且多余的点输出到控制台
    public void compare(String expectedPath, String actualPath, String caseName) {
        BaseTest baseTest = new BaseTest();
        Gson gson = new Gson();
        baseTest.recurDelete(caseName);
        TestStatisticBean testData = gson.fromJson(getDatafromFile(expectedPath), TestStatisticBean.class);
        AppStatisticBean appData = gson.fromJson(getDatafromFile(actualPath), AppStatisticBean.class);
        convertAndWriteData(testData, appData, expectedPath);
        if (BaseTest.test == 0) {
        	if (BaseTest.os == 2) {
            	addQaInfo(appData,"./actual/statistic.json");
                //上传ftp
                ftpUtil ftpUtil = new ftpUtil();
           		ftpUtil.ftpUploadForAndroidActual(caseName);
    		}else if (BaseTest.os == 1){
    			addQaInfo(appData,"./actual_iOS/statistic.json");
                //上传ftp
                ftpUtil ftpUtil = new ftpUtil();
           		ftpUtil.ftpUploadForIosActual(caseName);
    		}
		}else if (BaseTest.test == 0) {
			System.out.println("这是测试不做FTP上传操作");
		}
        
        
        try {

            for (Map.Entry<String, List<EventEntity>> entry : testData.mData.entrySet()) {
                String key = entry.getKey();
                List<EventEntity> expectedList = testData.mData.get(key);
                List<TEventEntity> actualList = appData.mData.get(key);

                if (actualList == null) {
                    System.out.println(key + "标识类型点全部未找到");
                    this.report.log(LogStatus.ERROR, key + "标识类型点全部未找到");
                    continue;
                }

                boolean isFind;

                for (EventEntity expected : expectedList) {
                    isFind = false;
                    for (TEventEntity actual : actualList) {
                        if (actual.ev.equals(expected.ev) && actual.type.equals(expected.type) &&
                                actual.pl.equals(expected.pl) && !actual.isFind) {
                            isFind = true;
                            expected.ts = actual.ts;
                            actual.isFind = true;
                            break;
                        }
                    }

                    if (!isFind) {
                        if (expected.ev != null && !"".equals(expected.ev)) {
                            System.out.println(expected.ev + " 未找到 ");
                            this.report.log(LogStatus.ERROR, expected.ev + " 未找到 ");
                        } else if (expected.pl != null && !"".equals(expected.pl)) {
                            System.out.println(expected.pl + " 未找到 ");
                            this.report.log(LogStatus.ERROR, expected.pl + " 未找到 ");
                        }
                    }
                }

            }
        } finally {
            writeDataToFile(testData, expectedPath);
            System.out.println("客户端中多出来的点有:");
            this.report.log(LogStatus.ERROR, "客户端中多出来的点有:");
            //打印出所有app里多出来的点，也就是isFind未被标记成true的点
            for (Map.Entry<String, List<TEventEntity>> entry : appData.mData.entrySet()) {
                String key = entry.getKey();
                List<TEventEntity> list = entry.getValue();

                for (TEventEntity te : list) {
                    if (!te.isFind) {
                        if (te.ev != null && !"".equals(te.ev)) {
                            System.out.println(te.ev);
                            this.report.log(LogStatus.ERROR, te.ev);
                            baseTest.writerByline(caseName, te.ev);
                        } else if (te.pl != null && !"".equals(te.pl)) {
                            System.out.println(te.pl);
                            this.report.log(LogStatus.ERROR, te.pl);
                            baseTest.writerByline(caseName, te.pl);
                        }
                    }
                }

            }
        }

    }
    
    public static void addQaInfo(AppStatisticBean asb,String path){
    	asb.qaName = BaseTest.qaName;
    	asb.qaId = BaseTest.qaId;
    	System.out.println("开始写入"+BaseTest.qaName+","+BaseTest.qaId);
    	writeQaInfoToFile(asb, path);
    }
    //格式数据，并存储
    public static void convertAndWriteData(TestStatisticBean tsb, AppStatisticBean asb, String path) {
    	tsb.qaName = BaseTest.qaName;
    	tsb.qaId = BaseTest.qaId;
        tsb.pkgName = asb.pkgName;
        tsb.versionCode = asb.versionCode;
        tsb.versionName = asb.versionName;
        tsb.startTime = asb.startTime;
        tsb.endTime = asb.endTime;
        tsb.deviceId = asb.deviceId;
        tsb.platform = asb.platform;
        tsb.sc = asb.sc;

        tsb.app = asb.app;
        tsb.appId = asb.appId;
        tsb.uid = asb.uid;
        tsb.cid = asb.cid;
        tsb.cityid = asb.cityid;
        tsb.source = asb.source;
        tsb.abversion = asb.abversion;
        tsb.longitude = asb.longitude;
        tsb.latitude = asb.latitude;
        tsb.coorType = asb.coorType;

        tsb.xdid = asb.xdid;
        tsb.imei = asb.imei;
        tsb.mac = asb.mac;
        tsb.mUrl = asb.mUrl;

        writeDataToFile(tsb, path);
    }

    //格式化数据为josn后存储到指定目录
    public static void writeDataToFile(TestStatisticBean tsb, String fileName) {
        Gson gson = new Gson();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName, false));
            bw.write(gson.toJson(tsb));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //格式化数据为josn后存储到指定目录
    public static void writeQaInfoToFile(AppStatisticBean asb, String fileName) {
        Gson gson = new Gson();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName, false));
            bw.write(gson.toJson(asb));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //读取格式化存储以后的数据
    public static String getDatafromFile(String fileName) {

        String Path = fileName;
        BufferedReader reader = null;
        String laststr = "";
        try {
            InputStream fileInputStream = new FileInputStream(Path);
            Reader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
}
