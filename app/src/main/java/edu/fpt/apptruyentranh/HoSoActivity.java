package edu.fpt.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import edu.fpt.apptruyentranh.utils.Utils;

public class HoSoActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView title_toolbar,txtHosoname,txthosoEmail;
    ImageView imgAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_so);
        ActionBar();
        InnitView();
    }

    private void InnitView() {
        toolbar =findViewById(R.id.toolbar);
        txtHosoname=findViewById(R.id.txtHoSoName);
        txthosoEmail=findViewById(R.id.txthoSoEmail);
        imgAvatar=findViewById(R.id.roundedImageView);
        Glide.with(HoSoActivity.this).load(Utils.user_current.getLinkavatar()).placeholder(R.drawable.ic_baseline_person_24).into(imgAvatar);
        txtHosoname.setText(Utils.user_current.getFullname());
        txthosoEmail.setText(Utils.user_current.getEmail());

    }

    private void ActionBar() {
        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        title_toolbar=toolbar.findViewById(R.id.toolbar_title);

        title_toolbar.setText("Hồ Sơ Của Tôi");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}