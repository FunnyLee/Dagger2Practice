package com.funny.geek.model.bean;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/1/17
 * Description: This is SectionListBean
 */
public class SectionListBean {
    /**
     * description : 看别人的经历，理解自己的生活
     * id : 1
     * name : 深夜惊奇
     * thumbnail : http://pic3.zhimg.com/91125c9aebcab1c84f58ce4f8779551e.jpg
     */

    public List<DataBean> data;
    

    public static class DataBean {
        public String description;
        public int id;
        public String name;
        public String thumbnail;
        
    }
}
