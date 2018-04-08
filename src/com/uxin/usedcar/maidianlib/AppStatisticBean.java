package com.uxin.usedcar.maidianlib;

import java.util.List;
import java.util.Map;

import com.uxin.usedcar.test.libs.BaseTest;

/**
 * Created by xianeng on 2018/3/10.
 */
public class AppStatisticBean {
    public Map<String, List<TEventEntity>> mData;
    public String qaName;
	public String qaId;
    public String pkgName;
    public String versionName;
    public String versionCode;
    public String startTime;
    public String endTime;
    public String deviceId;
    public String platform = BaseTest.os ==1?"iOS":"android";
    public String sc;

    public String app;
    public String appId;
    public String uid;
    public String cid;
    public String cityid;
    public String source;
    public String abversion;
    public double longitude;
    public double latitude;
    public String coorType;

    public String xdid;
    public String imei;
    public String mac;
    public String mUrl;


}
