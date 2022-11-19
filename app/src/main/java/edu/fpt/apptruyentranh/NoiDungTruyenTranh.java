package edu.fpt.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import edu.fpt.apptruyentranh.data.TruyenTranh;

public class NoiDungTruyenTranh extends AppCompatActivity {
    TruyenTranh truyenTranh;
    TextView tvName,tvTenTg,tvNamxb,tvmota;
    ImageView imgAnhbia;
    Button btnDocTruyen;
    RecyclerView recBinhLuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung_truyen_tranh);
        truyenTranh= (TruyenTranh) getIntent().getSerializableExtra("truyenTranh");
        Log.d("zxxxxxxxxxxxx", "onCreate: "+truyenTranh.getTenTruyen());
        anhXa();
        initView();
    }

    private void initView() {
        tvName.setText(truyenTranh.getTenTruyen());
        tvTenTg.setText(truyenTranh.getTenTacGia());
        tvNamxb.setText(truyenTranh.getNamSanXuat());
        Glide.with(getApplicationContext()).load(truyenTranh.getLinkAnh()).into(imgAnhbia);
        btnDocTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),DocTruyenActivity.class);
                startActivity(i);
            }
        });
    }

    private void anhXa() {
        tvName=findViewById(R.id.txtTenTruyen);
        tvTenTg=findViewById(R.id.txtTacGia);
        tvNamxb=findViewById(R.id.txtNamXb);
        tvmota=findViewById(R.id.txtMota);
        imgAnhbia=findViewById(R.id.imgNoiDung);
        btnDocTruyen=findViewById(R.id.btnDocTruyen);
        recBinhLuan=findViewById(R.id.rec_comment);
    }
}