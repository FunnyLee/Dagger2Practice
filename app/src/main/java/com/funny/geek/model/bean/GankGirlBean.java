package com.funny.geek.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author: Funny
 * Time: 2019/3/28
 * Description: This is GankGirlBean
 */
public class GankGirlBean implements Serializable {

    /**
     * error : false
     * results : [{"_id":"56cc6d29421aa95caa708160","createdAt":"2016-01-13T02:50:07.584Z","desc":"1.13","publishedAt":"2016-01-13T04:49:14.827Z","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1ezxog636o8j20du0kujsg.jpg","used":true,"who":"张涵宇"},{"_id":"57d8982f421aa95bd05015af","createdAt":"2016-09-14T08:22:07.587Z","desc":"9-14","publishedAt":"2016-09-14T11:35:01.991Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f7sszr81ewj20u011hgog.jpg","used":true,"who":"daimajia"},{"_id":"5a5bfc29421aa9115489927b","createdAt":"2018-01-15T08:56:09.429Z","desc":"1-15","publishedAt":"2018-01-16T08:40:08.101Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20180115085556_8AeReR_taeyeon_ss_15_1_2018_7_58_51_833.jpeg","used":true,"who":"daimajia"},{"_id":"5b1026ba421aa9029895ba44","createdAt":"2018-06-01T00:45:46.820Z","desc":"2018-06-02","publishedAt":"2018-06-01T00:00:00.0Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1frv03m8ky5j30iz0rltfp.jpg","used":true,"who":"lijinshanmx"},{"_id":"58645be0421aa94dbbd82bac","createdAt":"2016-12-29T08:42:08.389Z","desc":"12-29","publishedAt":"2016-12-29T11:56:56.946Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034gw1fb7d9am05gj20u011hq64.jpg","used":true,"who":"daimajia"}]
     */

    public boolean error;
    public List<ResultsBean> results;


    public static class ResultsBean implements Serializable {
        /**
         * _id : 56cc6d29421aa95caa708160
         * createdAt : 2016-01-13T02:50:07.584Z
         * desc : 1.13
         * publishedAt : 2016-01-13T04:49:14.827Z
         * type : 福利
         * url : http://ww2.sinaimg.cn/large/7a8aed7bjw1ezxog636o8j20du0kujsg.jpg
         * used : true
         * who : 张涵宇
         * source : chrome
         */

        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String type;
        public String url;
        public boolean used;
        public String who;
        public String source;

    }
}
