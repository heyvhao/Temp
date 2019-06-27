package cn.edu.gdpt.libraryhome.until;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Okhttpuntil {
    public static OkHttpClient okHttpClient=new OkHttpClient();
    //工具类
    public static <T>T GetSynsResponse(String api,String[] key,String[] values,Class<T> myclass) throws IOException {
        StringBuffer parm=new StringBuffer();
        parm.append("?");
        for (int i = 0; i <key.length ; i++) {
            parm.append(key[i]).append("=").append(values[i]);
            if(i!=key.length-1){
                parm.append("&");
            }
        }
        Request build = new Request.Builder().get().url(api + parm.toString()).build();
        Response execute = okHttpClient.newCall(build).execute();
        return new Gson().fromJson(execute.body().string(),myclass);
    }
    public static <T>T GetSynsResponse(String api,Class<T> myclass) throws IOException {

        Request build = new Request.Builder().get().url(api .toString()).build();
        Response execute = okHttpClient.newCall(build).execute();
        return new Gson().fromJson(execute.body().string(),myclass);
    }
}
