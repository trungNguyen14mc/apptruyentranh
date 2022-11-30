package edu.fpt.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.fpt.apptruyentranh.adapter.TruyenTranhAdapter;
import edu.fpt.apptruyentranh.model.truyentranh;
import edu.fpt.apptruyentranh.retrofit.ApiAppDocTruyen;
import edu.fpt.apptruyentranh.retrofit.RetrofitClient;
import edu.fpt.apptruyentranh.utils.Utils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListTruyenTranh extends AppCompatActivity {
    RecyclerView recyclerView;
    TruyenTranhAdapter adapter;
    List<truyentranh> truyenTranhArrayList;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    ApiAppDocTruyen apiAppDocTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_truyen_tranh);
        apiAppDocTruyen= RetrofitClient.getInstane(Utils.BASE_URL_hai).create(ApiAppDocTruyen.class);

        anhXa();
        getData();
        init();
    }

    private void getData() {
        compositeDisposable.add(apiAppDocTruyen.getalltruyen().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        truyentranhModel -> {
                            if(truyentranhModel.isSuccess()){
                                truyenTranhArrayList=truyentranhModel.getResult();
                                adapter=new TruyenTranhAdapter(getApplicationContext(),truyenTranhArrayList);
                                RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),3);
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(adapter);
                            }

                        },throwable -> {

                        }
                ));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void init(){
        truyenTranhArrayList = new ArrayList<>();


    }
    private void anhXa(){
        recyclerView = findViewById(R.id.reyc_list);

    }

}