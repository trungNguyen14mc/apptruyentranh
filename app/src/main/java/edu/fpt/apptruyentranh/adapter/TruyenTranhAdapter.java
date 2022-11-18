package edu.fpt.apptruyentranh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import edu.fpt.apptruyentranh.R;
import edu.fpt.apptruyentranh.data.TruyenTranh;

public class TruyenTranhAdapter extends ArrayAdapter<TruyenTranh> {
    private Context ct;
    private ArrayList<TruyenTranh> arr;
    public TruyenTranhAdapter(@NonNull Context context, int resource, @NonNull List<TruyenTranh> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list_truyen, null);
        }

        if (arr.size() > 0){
            TruyenTranh truyenTranh = this.arr.get(position);

            TextView tvTenTruyen = convertView.findViewById(R.id.tvTenTruyen);
            TextView tvTenChap = convertView.findViewById(R.id.tvTenChap);
            ImageView imgAnhTruyen = convertView.findViewById(R.id.imgAnhTruyen);
            TextView tvNamSX = convertView.findViewById(R.id.tvNamSX);
            TextView tvTenTG = convertView.findViewById(R.id.tvTenTG);

            tvTenTruyen.setText(truyenTranh.getTenTruyen());
            tvTenChap.setText(truyenTranh.getTenChap());
            tvNamSX.setText(truyenTranh.getNamSanXuat());
            tvTenTG.setText(truyenTranh.getTenTacGia());
            Glide.with(this.ct).load(truyenTranh.getLinkAnh()).into(imgAnhTruyen);
        }
        return convertView;
    }
}
