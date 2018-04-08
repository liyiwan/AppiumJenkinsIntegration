package com.uxin.usedcar.maidianlib;

/**
 * Created by xianeng on 2018/2/28.
 */
public class EventEntity {
    public String ev;
    public String pl;
    public String ds;
    public String pid;
    public String ref;
    public String url;
    public EventEntity.Event type;
    public String ts;

    public EventEntity() {
        ev = "";
        pl = "";
        ds = "";
        pid = "";
        ref = "";
        url = "";
        ts = "";
    }

    public static enum LifeCycleEvent {
        APP_START("start"),
        APP_ACTIVE("active"),
        APP_BACKGROUND("background"),
        APP_EXIT("exit");

        public final String event;

        private LifeCycleEvent(String e) {
            this.event = e;
        }
    }

    public static enum Event {
        CLICK_C("c"),
        PAGE_W("w"),
        SHOW_E("e"),
        QUITPAGE_Q("q"),
        QUITAPP_A("a");

        public final String event;

        private Event(String e) {
            this.event = e;
        }
    }

}
