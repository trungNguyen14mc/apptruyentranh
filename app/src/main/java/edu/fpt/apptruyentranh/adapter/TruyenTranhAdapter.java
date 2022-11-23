package edu.fpt.apptruyentranh.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import edu.fpt.apptruyentranh.NoiDungTruyenTranh;
import edu.fpt.apptruyentranh.R;

import edu.fpt.apptruyentranh.model.truyentranh;

public class TruyenTranhAdapter extends RecyclerView.Adapter<TruyenTranhAdapter.myViewHolder> {
    Context mcontext;
    List<truyentranh> list;

    public TruyenTranhAdapter(Context mcontext, List<truyentranh> list) {
        this.mcontext = mcontext;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_truyen,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        truyentranh truyentranh=list.get(position);

        Glide.with(mcontext).load(truyentranh.getAnhBia()).into(holder.img);
        holder.tvnametruyen.setText(truyentranh.getTenTruyen());
        holder.tacgia.setText(truyentranh.getTenTacGia());
        holder.namXb.setText(truyentranh.getNamXuatBan());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void ocClick(View view, int pos, boolean isClick) {
                if(!isClick){
                    Intent intent=new Intent(mcontext, NoiDungTruyenTranh.class);
                    intent.putExtra("chitiet",truyentranh);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView tvnametruyen,tacgia,namXb;
        ItemClickListener itemClickListener;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imgAnhTruyen);
            tvnametruyen=itemView.findViewById(R.id.tvTenTruyen);
            tacgia=itemView.findViewById(R.id.tvTenTG);
            namXb=itemView.findViewById(R.id.tvNamSX);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            itemClickListener.ocClick(view,getAdapterPosition(),false);
        }
    }
}
