package edu.fpt.apptruyentranh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.fpt.apptruyentranh.R;
import edu.fpt.apptruyentranh.model.img_truyen;
import edu.fpt.apptruyentranh.model.truyentranh;

public class imgAdapter extends RecyclerView.Adapter<imgAdapter.myViewHolder> {
    List<img_truyen> list;
    Context context;

    public imgAdapter(List<img_truyen> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anh,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        img_truyen truyentranh=list.get(position);
        Glide.with(context).load(truyentranh.getLink_anh()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_anh);
        }
    }
}
