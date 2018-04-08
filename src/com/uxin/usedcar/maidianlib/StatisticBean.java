package com.uxin.usedcar.maidianlib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xianeng on 2018/2/28.
 */
public class StatisticBean {
    public Map<String, List<EventEntity>> mData;
    public String pkgName;
    public String versionName;
    public String versionCode;
    public String startTime;
    public String endTime;
    public String deviceId;

    public StatisticBean() {
        mData = new HashMap<>();
        startTime = System.currentTimeMillis() + "";
    }

    public void initData() {
        //TODO 需要完善一下数据
    }
}
