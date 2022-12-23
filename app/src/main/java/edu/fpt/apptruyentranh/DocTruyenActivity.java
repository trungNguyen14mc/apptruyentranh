package edu.fpt.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;


import edu.fpt.apptruyentranh.adapter.imgAdapter;
import edu.fpt.apptruyentranh.model.img_truyen;

import edu.fpt.apptruyentranh.retrofit.ApiAppDocTruyen;
import edu.fpt.apptruyentranh.retrofit.RetrofitClient;
import edu.fpt.apptruyentranh.utils.Utils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DocTruyenActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    ApiAppDocTruyen apiAppDocTruyen;
    RecyclerView recyclerView;
    int idtruyen;
    int soluongchap;
    Spinner spinner;
    int chapter=1;
    List<img_truyen> list;
    ImageButton imgNext,imgback;
    imgAdapter adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list=new ArrayList<>();
        setContentView(R.layout.activity_doc_truyen);
        apiAppDocTruyen= RetrofitClient.getInstane(Utils.BASE_URL_hai).create(ApiAppDocTruyen.class);
        recyclerView=findViewById(R.id.listimg);
        spinner=findViewById(R.id.spinner_chapter);
        Bundle b = getIntent().getExtras();
        idtruyen=b.getInt("idtruyen");
        soluongchap=b.getInt("soluongchap");
        SpinnerCHapter(soluongchap);
        imgNext=findViewById(R.id.btnNextChapter);
        imgback=findViewById(R.id.btnbackChapter);
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = spinner.getSelectedItemPosition();
                if(position<=soluongchap-1){
                    position=position+1;
                    spinner.setSelection(1);
                }


            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = spinner.getSelectedItemPosition();
                if(position>=0){
                    position=position-1;
                    spinner.setSelection(position);}
            }
        });
        ActionBar();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
    private void ActionBar() {
        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void getcontent(int i,int chapter) {
        compositeDisposable.add(apiAppDocTruyen.getcontent(i,chapter).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        imgModel -> {
                            if(imgModel.isSuccess()){
                                list.clear();
                                 list=imgModel.getResult();
                                 adapter=new imgAdapter(list,this);
                                recyclerView.setAdapter(adapter);
                                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DocTruyenActivity.this, RecyclerView.VERTICAL,false);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                adapter.notifyDataSetChanged();
                            }
                            else {
                                list.clear();
                            }
                        },throwable -> {

                        }
                ));

    }

    private void SpinnerCHapter(int soluongchap) {
        List<Integer> listchapter=new ArrayList<>();
        for(int i=0; i<soluongchap;i++){
            listchapter.add(i+1);
        }

        ArrayAdapter spiner = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listchapter);
        spinner.setAdapter(spiner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                chapter=listchapter.get(i);
                Log.d("chapter", "onItemSelected: "+chapter);
                getcontent(idtruyen,chapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}