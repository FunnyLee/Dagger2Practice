package com.funny.geek.model.bean;

import java.util.List;

/**
 * Created by codeest on 16/8/21.
 */

public class HotBean {

    /**
     * news_id : 8701066
     * url : http://news-at.zhihu.com/api/2/news/8701066
     * thumbnail : http://pic1.zhimg.com/f5169eb70e3a6823737dc55fbab051c0.jpg
     * title : 瞎扯 · 如何正确地吐槽
     */

    public List<RecentBean> recent;
    

    public static class RecentBean {
        public int news_id;
        public String url;
        public String thumbnail;
        public String title;
        public boolean readState;
        
    }
}
