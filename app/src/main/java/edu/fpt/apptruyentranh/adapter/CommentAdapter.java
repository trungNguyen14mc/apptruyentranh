package edu.fpt.apptruyentranh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.fpt.apptruyentranh.R;
import edu.fpt.apptruyentranh.model.comment;

public class CommentAdapter extends BaseAdapter {
    Context mcontext;
    List<comment> list;

    public CommentAdapter(Context mcontext, List<comment> list) {
        this.mcontext = mcontext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class viewHolder{
        TextView textView;
        ImageView imageView;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewProduct;
        if(view ==null){
            viewProduct=View.inflate(viewGroup.getContext(),R.layout.item_binh_luan,null);
        }else {
            viewProduct=view;
        }
        comment comment=list.get(i);
        ((TextView) viewProduct.findViewById(R.id.tvIDnguoidung)).setText("NGười Dùng: "+comment.getIdUser());
        ((TextView) viewProduct.findViewById(R.id.comment)).setText(comment.getNoidung());
        ((TextView) viewProduct.findViewById(R.id.tvtime)).setText(comment.getNgay_gio());
        notifyDataSetChanged();
        return viewProduct;
    }
}
