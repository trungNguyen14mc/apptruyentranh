package edu.fpt.apptruyentranh.api;

import android.os.AsyncTask;

import java.io.IOException;

import edu.fpt.apptruyentranh.data.GetTruyen;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiGetTruyen extends AsyncTask<Void, Void, Void> {
    String data;
    GetTruyen getTruyen;

    public ApiGetTruyen(GetTruyen getTruyen) {
        this.getTruyen = getTruyen;
        this.getTruyen.mStart();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://6374892508104a9c5f822be9.mockapi.io/thongTinTruyenTranh")
                .build();

        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch (IOException e){
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if (data == null){
            this.getTruyen.mError();
        }else {
            this.getTruyen.mEnd(data);
        }
    }
}
