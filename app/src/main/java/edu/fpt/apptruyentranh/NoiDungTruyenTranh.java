package edu.fpt.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.fpt.apptruyentranh.adapter.CommentAdapter;
import edu.fpt.apptruyentranh.model.comment;
import edu.fpt.apptruyentranh.model.truyentranh;
import edu.fpt.apptruyentranh.retrofit.ApiAppDocTruyen;
import edu.fpt.apptruyentranh.retrofit.RetrofitClient;
import edu.fpt.apptruyentranh.utils.Utils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NoiDungTruyenTranh extends AppCompatActivity {
    truyentranh truyenTranh;
    TextView tvName,tvTenTg,tvNamxb,tvmota;
    ImageView imgAnhbia;
    Button btnDocTruyen;
    ListView listView;
    CommentAdapter commentAdapter;
    EditText editTextcomment;
    List<comment> list;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    ApiAppDocTruyen apiAppDocTruyen;
    ImageView imgsend;
    int idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung_truyen_tranh);
        apiAppDocTruyen= RetrofitClient.getInstane(Utils.BASE_URL_hai).create(ApiAppDocTruyen.class);
        SharedPreferences preferences= getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);
        idUser= Integer.parseInt(preferences.getString("idUser",""));
        anhXa();
        initView();
        getData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void initView() {
        truyenTranh= (truyentranh) getIntent().getSerializableExtra("chitiet");

        tvName.setText(truyenTranh.getTenTruyen());
        tvTenTg.setText(truyenTranh.getTenTacGia());
        tvNamxb.setText(truyenTranh.getNamXuatBan());
        Glide.with(getApplicationContext()).load(truyenTranh.getAnhBia()).into(imgAnhbia);
        tvmota.setText(truyenTranh.getMota());
        btnDocTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),DocTruyenActivity.class);
                i.putExtra("idtruyen",truyenTranh.getIdtruyen());
                startActivity(i);
            }
        });
        imgsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postComment();
            }
        });
    }

    private void postComment() {
//        Utils.user_current.getId();
//        Log.d("sssssssssssss", "postComment: "+Utils.user_current.getId());

        Date currentTime = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        compositeDisposable.add(apiAppDocTruyen.post_comment(truyenTranh.getIdtruyen(),editTextcomment.getText().toString(),idUser,date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        commentModel -> {
                            if(commentModel.isSuccess()){
                                Toast.makeText(getApplicationContext(),commentModel.getMessage(),Toast.LENGTH_SHORT).show();
                                editTextcomment.setText("");
                                commentAdapter.notifyDataSetChanged();
                            }

                        },throwable -> {}


                ));

    }

    private void getData(){

        compositeDisposable.add(apiAppDocTruyen.getComment(String.valueOf(truyenTranh.getIdtruyen())).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        commentModel -> {
                            if(commentModel.isSuccess()){
                                list=commentModel.getResult();
                                commentAdapter=new CommentAdapter(getApplicationContext(),list);
                                listView.setAdapter(commentAdapter);
                                commentAdapter.notifyDataSetChanged();
                            }

                        },throwable -> {}


                ));

    }
    private void anhXa() {
        tvName=findViewById(R.id.txtTenTruyen);
        tvTenTg=findViewById(R.id.txtTacGia);
        tvNamxb=findViewById(R.id.txtNamXb);
        tvmota=findViewById(R.id.motaTruyen);
        imgAnhbia=findViewById(R.id.imgNoiDung);
        btnDocTruyen=findViewById(R.id.btnDocTruyen);
        listView=findViewById(R.id.listview_comment);
        list=new ArrayList<>();
        editTextcomment=findViewById(R.id.edit_comment);
        imgsend=findViewById(R.id.imgsend);
    }
}