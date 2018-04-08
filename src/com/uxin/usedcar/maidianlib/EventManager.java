package com.uxin.usedcar.maidianlib;

import com.google.gson.Gson;
import com.uxin.usedcar.test.libs.CaseConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by xianeng on 2018/2/28.
 */
public class EventManager {

    private static TestStatisticBean mTestStatisticBean;

    public static String fileName = "";

    /**
     * 事件统计
     * */
    public static final String UXIN_EVENT_CLICK = "c";
    /**
     * 页面统计
     * */
    public static final String UXIN_EVENT_PAGE = "w";
    /**
     * 7.5新增 效果呈现
     */
    public static final String UXIN_EVENT_SHOW = "e";

    /**
     * 9.1  新增 退出
     */
    public static final String UXIN_EVENT_QUIT = "q";

    public static void initEventManager() {
        mTestStatisticBean = null;
        mTestStatisticBean = new TestStatisticBean();
    }

    public static void sendPoint(String eventType, String event, String pl, String ds, String pid) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.ev = event;
        eventEntity.pl = pl;
        eventEntity.ds = ds;

        if (eventType.equals(UXIN_EVENT_CLICK)) {
            eventEntity.type = EventEntity.Event.CLICK_C;
        }else if (eventType.equals(UXIN_EVENT_PAGE)){
            eventEntity.type = EventEntity.Event.PAGE_W;
        }else if (eventType.equals(UXIN_EVENT_SHOW)){
            eventEntity.type = EventEntity.Event.SHOW_E;
        }else if (eventType.equals(UXIN_EVENT_QUIT)){
            eventEntity.type = EventEntity.Event.QUITPAGE_Q;
        }
        eventEntity.pid = pid;

        saveEvent(eventEntity);
    }

    public static void saveEvent(EventEntity eventEntity) {
        saveEvInStorage(eventEntity);
    }

    public static void saveLifeCycleEvent(EventEntity.LifeCycleEvent lifeCycleEvent) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.type = EventEntity.Event.QUITAPP_A;
        eventEntity.ev = lifeCycleEvent.event;
        saveEvInStorage(eventEntity);
    }

    public static void saveEvInStorage(EventEntity ee) {
        String key = "";
        String[] p;
        if (ee.ev != null && !"".equals(ee.ev)) {
            p = ee.ev.split("#");
            key = p[0];
            if (p.length == 2) {
                ee.ev = createEV(key, p[1].split("/"));
            }
        } else if (ee.pl != null && !"".equals(ee.pl)) {
            p = ee.pl.split("#");
            key = p[0];
            if(p.length == 2) {
                ee.pl = createEV(key, p[1].split("/"));
            }
        }


        List<EventEntity> list = mTestStatisticBean.mData.get(key);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(ee);

        mTestStatisticBean.mData.put(key, list);

    }

    public static String createEV(String key, String[] params) {
        params = getParams(params);
        StringBuilder sbParams;
        if (params == null || params.length == 0) {
            sbParams = new StringBuilder(key);
        } else {
            sbParams = new StringBuilder(32 + 8 * params.length);
            sbParams.append(key);
            sbParams.append("#");

            //先通过TreeMap对key进行一个排序，然后再拼接，这样保证无论参数传递成什么顺序最终拼接出来的字符串一致
            Map<String, String> map = new TreeMap<>();
            for (int i = 1; i < params.length; i += 2) {
                map.put(params[i - 1], params[i]);
            }
            for (Map.Entry entry : map.entrySet()) {
                sbParams.append(entry.getKey()).append("=").append(entry.getValue()).append("/");
            }
            sbParams.deleteCharAt(sbParams.length() - 1);
        }
        return sbParams.toString();
    }

    public static String[] getParams(String[] params) {
        List<String> paramList = new ArrayList<>();
        for (String p : params) {
            String[] valuearray = p.split("=");
            for (String v : valuearray) {
                paramList.add(v);
            }

        }
        return paramList.toArray(new String[paramList.size()]);
    }

    public static void writeData() {
        Gson gson = new Gson();
        mTestStatisticBean.endTime = System.currentTimeMillis() + "";
        String es = gson.toJson(mTestStatisticBean);
        BufferedWriter bw = null;
        try {
        	   if (CaseConfig.OS == 1) {
				String dir_name = System.getProperty("user.dir") + File.separator + "expected_iOS";
				if (!(new File(dir_name).isDirectory())) { // 判断是否存在该目录
					System.out.println("当前目录全文路径   " + dir_name);
					new File(dir_name).mkdir();
				}
         		 bw = new BufferedWriter(new FileWriter(new File("./expected_iOS/" + fileName), false));//1IOS
             }else {
				String dir_name = System.getProperty("user.dir") + File.separator + "expected";
				if (!(new File(dir_name).isDirectory())) { // 判断是否存在该目录
					System.out.println("当前目录全文路径   " + dir_name);
					new File(dir_name).mkdir();
				}
              	 bw = new BufferedWriter(new FileWriter(new File("./expected/" + fileName), false));// 2 ANDROID
			}
            bw.write(es);
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


}
