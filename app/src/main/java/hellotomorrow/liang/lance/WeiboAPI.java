package hellotomorrow.liang.lance;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import hellotomorrow.liang.lance.jsons.*;

public class WeiboAPI
{
    static private String api_url = "https://m.weibo.cn/api/container/getIndex?" +
            "uid=2803301701&luicode=10000011" +
            "&lfid=100103type%3D1%26q%3D%E4%BA%BA%E6%B0%91%E6%97%A5%E6%8A%A5" +
            "&featurecode=20000320&type=uid&value=2803301701" +
            "&containerid=1076032803301701&page=";

//    static private String api_ur = "https://m.weibo.cn/api/container/getIndex?uid=2803301701&luicode=10000011&lfid=100103type%3D1%26q%3D%E4%BA%BA%E6%B0%91%E6%97%A5%E6%8A%A5&featurecode=20000320&type=uid&value=2803301701&containerid=1076032803301701&page=";

    static public JsonRootBean parseJson(String json) {
        return new Gson().fromJson(json, JsonRootBean.class);
    }


    static public void getPage(int page, StringCallback callback) {
        String url = api_url + Integer.toString(page);
        OkGo.<String>get(url)
                .execute(callback);
    }
}
