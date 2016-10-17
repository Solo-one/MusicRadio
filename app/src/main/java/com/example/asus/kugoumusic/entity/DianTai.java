package com.example.asus.kugoumusic.entity;

import java.util.List;

/**
 * Created by asus on 2016/10/2.
 */
public class DianTai {

    /**
     * title : 公共频道
     * cid : 1
     * channellist : [{"name":"漫步春天","channelid":"62","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/838ba61ea8d3fd1fb4912e42354e251f95ca5f2a.jpg","ch_name":"public_tuijian_spring","value":1000000,"cate_name":"tuijian","cate_sname":"推荐频道"},{"name":"秋日私语","channelid":"63","thumb":"http://c.hiphotos.baidu.com/ting/pic/item/b3119313b07eca80b93485cf932397dda14483e1.jpg","ch_name":"public_tuijian_autumn","value":1000000,"cate_name":"tuijian","cate_sname":"推荐频道"},{"name":"温暖冬日","channelid":"61","thumb":"http://b.hiphotos.baidu.com/ting/pic/item/eac4b74543a9822659d378408982b9014a90eb43.jpg","ch_name":"public_tuijian_winter","value":36,"cate_name":"tuijian","cate_sname":"推荐频道"},{"name":"热歌","channelid":"58","thumb":"http://d.hiphotos.baidu.com/ting/pic/item/30adcbef76094b36a098488aa0cc7cd98d109d4a.jpg","ch_name":"public_tuijian_rege","value":3,"cate_name":"tuijian","cate_sname":"推荐频道"},{"name":"KTV金曲","channelid":"45","thumb":"http://d.hiphotos.baidu.com/ting/pic/item/728da9773912b31b106b9e4e8518367adab4e156.jpg","ch_name":"public_tuijian_ktv","value":4,"cate_name":"tuijian","cate_sname":"推荐频道"},{"name":"Billboard","channelid":"55","thumb":"http://b.hiphotos.baidu.com/ting/pic/item/a2cc7cd98d1001e92b3c4768bb0e7bec54e79750.jpg","ch_name":"public_tuijian_billboard","value":20,"cate_name":"tuijian","cate_sname":"推荐频道"},{"name":"成名曲","channelid":"48","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/838ba61ea8d3fd1f0b0bf15d334e251f95ca5f52.jpg","ch_name":"public_tuijian_chengmingqu","value":5,"cate_name":"tuijian","cate_sname":"推荐频道"},{"name":"网络歌曲","channelid":"51","thumb":"http://c.hiphotos.baidu.com/ting/pic/item/29381f30e924b8991a462a5f6d061d950a7bf65b.jpg","ch_name":"public_tuijian_wangluo","value":9,"cate_name":"tuijian","cate_sname":"推荐频道"},{"name":"开车","channelid":"42","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/d439b6003af33a871d2b537dc55c10385343b517.jpg","ch_name":"public_tuijian_kaiche","value":21,"cate_name":"tuijian","cate_sname":"推荐频道"},{"name":"影视","channelid":"57","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/0e2442a7d933c89550c6cb1cd21373f082020018.jpg","ch_name":"public_tuijian_yingshi","value":31,"cate_name":"tuijian","cate_sname":"推荐频道"},{"name":"随便听听","channelid":"1","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/8b13632762d0f7035531c6bb0bfa513d2797c5d2.jpg","ch_name":"public_tuijian_suibiantingting","value":12,"cate_name":"tuijian","cate_sname":"推荐频道"},{"name":"经典老歌","channelid":"3","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/060828381f30e9240a58cf564f086e061c95f7dd.jpg","ch_name":"public_shiguang_jingdianlaoge","value":2,"cate_name":"shiguang","cate_sname":"时光频道"},{"name":"70后","channelid":"43","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/4ec2d5628535e5ddf8d5f6b875c6a7efcf1b62df.jpg","ch_name":"public_shiguang_70hou","value":25,"cate_name":"shiguang","cate_sname":"时光频道"},{"name":"80后","channelid":"2","thumb":"http://c.hiphotos.baidu.com/ting/pic/item/6a63f6246b600c3367818daf194c510fd8f9a1d9.jpg","ch_name":"public_shiguang_80hou","value":7,"cate_name":"shiguang","cate_sname":"时光频道"},{"name":"90后","channelid":"25","thumb":"http://c.hiphotos.baidu.com/ting/pic/item/242dd42a2834349be8ae3de2caea15ce36d3be29.jpg","ch_name":"public_shiguang_90hou","value":10,"cate_name":"shiguang","cate_sname":"时光频道"},{"name":"火爆新歌","channelid":"6","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/0e2442a7d933c8955088cb1cd21373f0830200e6.jpg","ch_name":"public_shiguang_xinge","value":15,"cate_name":"shiguang","cate_sname":"时光频道"},{"name":"儿歌","channelid":"24","thumb":"http://c.hiphotos.baidu.com/ting/pic/item/b151f8198618367a7e792cba2d738bd4b21ce5e1.jpg","ch_name":"public_shiguang_erge","value":34,"cate_name":"shiguang","cate_sname":"时光频道"},{"name":"旅行","channelid":"16","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/dbb44aed2e738bd41bcce156a28b87d6267ff9ec.jpg","ch_name":"public_shiguang_lvxing","value":35,"cate_name":"shiguang","cate_sname":"时光频道"},{"name":"夜店","channelid":"41","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/024f78f0f736afc3f7176d02b019ebc4b74512b9.jpg","ch_name":"public_shiguang_yedian","value":17,"cate_name":"shiguang","cate_sname":"时光频道"},{"name":"流行","channelid":"46","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/8ad4b31c8701a18b2dfa40289d2f07082838fe40.jpg","ch_name":"public_fengge_liuxing","value":6,"cate_name":"fengge","cate_sname":"风格频道"},{"name":"摇滚","channelid":"10","thumb":"http://b.hiphotos.baidu.com/ting/pic/item/1f178a82b9014a906c3514daaa773912b31bee4a.jpg","ch_name":"public_fengge_yaogun","value":24,"cate_name":"fengge","cate_sname":"风格频道"},{"name":"民谣","channelid":"60","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/d833c895d143ad4b536214a781025aafa40f060c.jpg","ch_name":"public_fengge_minyao","value":33,"cate_name":"fengge","cate_sname":"风格频道"},{"name":"轻音乐","channelid":"29","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/8b82b9014a90f603e8b3ce203a12b31bb051ed52.jpg","ch_name":"public_fengge_qingyinyue","value":14,"cate_name":"fengge","cate_sname":"风格频道"},{"name":"小清新","channelid":"49","thumb":"http://c.hiphotos.baidu.com/ting/pic/item/f703738da97739122dd1d706fb198618377ae2e6.jpg","ch_name":"public_fengge_xiaoqingxin","value":23,"cate_name":"fengge","cate_sname":"风格频道"},{"name":"中国风","channelid":"53","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/d62a6059252dd42ac0e553cc003b5bb5c8eab8ed.jpg","ch_name":"public_fengge_zhongguofeng","value":30,"cate_name":"fengge","cate_sname":"风格频道"},{"name":"DJ舞曲","channelid":"50","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/0824ab18972bd40743e88e9a78899e510fb309ad.jpg","ch_name":"public_fengge_dj","value":11,"cate_name":"fengge","cate_sname":"风格频道"},{"name":"电影","channelid":"38","thumb":"http://d.hiphotos.baidu.com/ting/pic/item/cb8065380cd7912368c9c913ae345982b2b780a8.jpg","ch_name":"public_fengge_dianyingyuansheng","value":29,"cate_name":"fengge","cate_sname":"风格频道"},{"name":"轻松假日","channelid":"40","thumb":"http://c.hiphotos.baidu.com/ting/pic/item/6c224f4a20a44623d811f7059b22720e0df3d7fa.jpg","ch_name":"public_xinqing_qingsongjiari","value":18,"cate_name":"xinqing","cate_sname":"心情频道"},{"name":"欢快旋律","channelid":"4","thumb":"http://c.hiphotos.baidu.com/ting/pic/item/bf096b63f6246b60d111ef13e8f81a4c510fa2be.jpg","ch_name":"public_xinqing_huankuai","value":11,"cate_name":"xinqing","cate_sname":"心情频道"},{"name":"甜蜜感受","channelid":"17","thumb":"http://d.hiphotos.baidu.com/ting/pic/item/c995d143ad4bd113f1d6ea5559afa40f4bfb05b9.jpg","ch_name":"public_xinqing_tianmi","value":26,"cate_name":"xinqing","cate_sname":"心情频道"},{"name":"寂寞","channelid":"37","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/08f790529822720ed8dbbf9d78cb0a46f21fab44.jpg","ch_name":"public_xinqing_jimo","value":27,"cate_name":"xinqing","cate_sname":"心情频道"},{"name":"单身情歌","channelid":"56","thumb":"http://d.hiphotos.baidu.com/ting/pic/item/7acb0a46f21fbe09b1349e7368600c338744ad6b.jpg","ch_name":"public_xinqing_qingge","value":28,"cate_name":"xinqing","cate_sname":"心情频道"},{"name":"舒缓节奏","channelid":"11","thumb":"http://d.hiphotos.baidu.com/ting/pic/item/342ac65c103853430cadda469013b07eca808873.jpg","ch_name":"public_xinqing_shuhuan","value":13,"cate_name":"xinqing","cate_sname":"心情频道"},{"name":"慵懒午后","channelid":"19","thumb":"http://b.hiphotos.baidu.com/ting/pic/item/5882b2b7d0a20cf437cea3b875094b36acaf997e.jpg","ch_name":"public_xinqing_yonglanwuhou","value":22,"cate_name":"xinqing","cate_sname":"心情频道"},{"name":"伤感","channelid":"36","thumb":"http://b.hiphotos.baidu.com/ting/pic/item/e850352ac65c1038a52693a5b1119313b07e8979.jpg","ch_name":"public_xinqing_shanggan","value":16,"cate_name":"xinqing","cate_sname":"心情频道"},{"name":"华语","channelid":"32","thumb":"http://b.hiphotos.baidu.com/ting/pic/item/5ab5c9ea15ce36d33b50de5739f33a87e950b105.jpg","ch_name":"public_yuzhong_huayu","value":1,"cate_name":"yuzhong","cate_sname":"语种频道"},{"name":"欧美","channelid":"33","thumb":"http://c.hiphotos.baidu.com/ting/pic/item/279759ee3d6d55fb785d2b416e224f4a20a4dd3a.jpg","ch_name":"public_yuzhong_oumei","value":8,"cate_name":"yuzhong","cate_sname":"语种频道"},{"name":"日语","channelid":"34","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/8694a4c27d1ed21b66857382ae6eddc451da3f0c.jpg","ch_name":"public_yuzhong_riyu","value":38,"cate_name":"yuzhong","cate_sname":"语种频道"},{"name":"韩语","channelid":"44","thumb":"http://b.hiphotos.baidu.com/ting/pic/item/10dfa9ec8a136327ddd49e54928fa0ec08fac798.jpg","ch_name":"public_yuzhong_hanyu","value":32,"cate_name":"yuzhong","cate_sname":"语种频道"},{"name":"粤语","channelid":"18","thumb":"http://a.hiphotos.baidu.com/ting/pic/item/d50735fae6cd7b89e7e067e40c2442a7d8330ecc.jpg","ch_name":"public_yuzhong_yueyu","value":19,"cate_name":"yuzhong","cate_sname":"语种频道"}]
     */

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String title;
        private int cid;
        /**
         * name : 漫步春天
         * channelid : 62
         * thumb : http://a.hiphotos.baidu.com/ting/pic/item/838ba61ea8d3fd1fb4912e42354e251f95ca5f2a.jpg
         * ch_name : public_tuijian_spring
         * value : 1000000
         * cate_name : tuijian
         * cate_sname : 推荐频道
         */

        private List<ChannellistBean> channellist;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public List<ChannellistBean> getChannellist() {
            return channellist;
        }

        public void setChannellist(List<ChannellistBean> channellist) {
            this.channellist = channellist;
        }

        public static class ChannellistBean {
            private String name;
            private String channelid;
            private String thumb;
            private String ch_name;
            private int value;
            private String cate_name;
            private String cate_sname;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getChannelid() {
                return channelid;
            }

            public void setChannelid(String channelid) {
                this.channelid = channelid;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getCh_name() {
                return ch_name;
            }

            public void setCh_name(String ch_name) {
                this.ch_name = ch_name;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }

            public String getCate_sname() {
                return cate_sname;
            }

            public void setCate_sname(String cate_sname) {
                this.cate_sname = cate_sname;
            }
        }
    }
}
