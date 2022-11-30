package edu.fpt.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import java.net.URISyntaxException;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        apiAppDocTruyen= RetrofitClient.getInstane(Utils.BASE_URL_hai).create(ApiAppDocTruyen.class);
        recyclerView=findViewById(R.id.listimg);
        Bundle b = getIntent().getExtras();
        idtruyen=b.getInt("idtruyen");
        Log.d("idchuyen", "onCreate: "+idtruyen);
        getcontent(idtruyen);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
    private void getcontent(int i) {
        compositeDisposable.add(apiAppDocTruyen.getcontent(i).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        imgModel -> {
                            if(imgModel.isSuccess()){
                                List<img_truyen> list=imgModel.getResult();
                                imgAdapter adapter=new imgAdapter(list,this);
                                RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),1);
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(adapter);
                            }
                        },throwable -> {

                        }


                ));
    }

}