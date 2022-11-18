package edu.fpt.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.fpt.apptruyentranh.adapter.TruyenTranhAdapter;
import edu.fpt.apptruyentranh.api.ApiGetTruyen;
import edu.fpt.apptruyentranh.data.GetTruyen;
import edu.fpt.apptruyentranh.data.TruyenTranh;

public class ListTruyenTranh extends AppCompatActivity implements GetTruyen {
    GridView gvdDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;

    Button btnDocTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_truyen_tranh);

        btnDocTruyen = findViewById(R.id.btn_doc_truyen);
        btnDocTruyen.setOnClickListener(v -> {
            startActivity(new Intent(ListTruyenTranh.this, NoiDungTruyenTranh.class));
        });

        init();
        anhXa();
        setUp();
        setClick();

        new ApiGetTruyen(this).execute();
    }

    private void init(){
        truyenTranhArrayList = new ArrayList<>();

//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 967", "https://dw9to29mmj727.cloudfront.net/products/1974732177.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1000", "https://img.etimg.com/thumb/msid-92073673,width-650,imgsize-67262,,resizemode-4,quality-100/one-piece.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1004", "https://kenh14cdn.com/203336854389633024/2022/8/28/photo-1-1661685040579548327859.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1034", "https://vtv1.mediacdn.vn/zoom/700_438/2020/4/20/one-piece-anime-hiatus-15873776419971960853722.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 967", "https://dw9to29mmj727.cloudfront.net/products/1974732177.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1000", "https://img.etimg.com/thumb/msid-92073673,width-650,imgsize-67262,,resizemode-4,quality-100/one-piece.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1004", "https://kenh14cdn.com/203336854389633024/2022/8/28/photo-1-1661685040579548327859.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1034", "https://vtv1.mediacdn.vn/zoom/700_438/2020/4/20/one-piece-anime-hiatus-15873776419971960853722.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 967", "https://dw9to29mmj727.cloudfront.net/products/1974732177.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1000", "https://img.etimg.com/thumb/msid-92073673,width-650,imgsize-67262,,resizemode-4,quality-100/one-piece.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1004", "https://kenh14cdn.com/203336854389633024/2022/8/28/photo-1-1661685040579548327859.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1034", "https://vtv1.mediacdn.vn/zoom/700_438/2020/4/20/one-piece-anime-hiatus-15873776419971960853722.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 967", "https://dw9to29mmj727.cloudfront.net/products/1974732177.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1000", "https://img.etimg.com/thumb/msid-92073673,width-650,imgsize-67262,,resizemode-4,quality-100/one-piece.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1004", "https://kenh14cdn.com/203336854389633024/2022/8/28/photo-1-1661685040579548327859.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1034", "https://vtv1.mediacdn.vn/zoom/700_438/2020/4/20/one-piece-anime-hiatus-15873776419971960853722.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 967", "https://dw9to29mmj727.cloudfront.net/products/1974732177.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1000", "https://img.etimg.com/thumb/msid-92073673,width-650,imgsize-67262,,resizemode-4,quality-100/one-piece.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1004", "https://kenh14cdn.com/203336854389633024/2022/8/28/photo-1-1661685040579548327859.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Onepiece", "Chapter 1034", "https://vtv1.mediacdn.vn/zoom/700_438/2020/4/20/one-piece-anime-hiatus-15873776419971960853722.jpg"));

        adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
    }
    private void anhXa(){
        gvdDSTruyen = findViewById(R.id.gvdDSTruyen);
    }
    private void setUp(){
        gvdDSTruyen.setAdapter(adapter);
    }
    private void setClick(){}

    @Override
    public void mStart() {
        Toast.makeText(this, "Dang lay data ve", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mEnd(String data) {
        try {
            truyenTranhArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i=0; i<arr.length(); i++){
                JSONObject object = arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(object));
            }
            adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
            gvdDSTruyen.setAdapter(adapter);
        }catch (JSONException e){

        }
    }

    @Override
    public void mError() {
        Toast.makeText(this, "Loi ket noi", Toast.LENGTH_SHORT).show();
    }
}