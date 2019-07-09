package com.example.androidroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidroom.R;
import com.example.androidroom.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ListAdapter<User,RecyclerView.ViewHolder> {

    private OnItemClickListener mListener;

    public UserAdapter() {
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getAddress().equals(newItem.getAddress()) &&
                    oldItem.getEmail().equals(newItem.getEmail()) &&
                    oldItem.getPhone().equals(newItem.getPhone());
        }
    };

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId;
        private TextView tvName;
        private TextView tvAddress;
        private TextView tvEmail;
        private TextView tvPhone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvPhone = itemView.findViewById(R.id.tv_phone);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext())
                .inflate(R.layout.user_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;

        final User user = getItem(position);

        holder.tvId.setText(String.valueOf(user.getId()));
        holder.tvName.setText(user.getName());
        holder.tvAddress.setText(user.getAddress());
        holder.tvEmail.setText(user.getEmail());
        holder.tvPhone.setText(user.getPhone());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mListener !=null){
                    mListener.onItemLongClick(v, position);
                }
                return false;
            }
        });
    }

    public User getUserAt(int position){
        return getItem(position);
    }


    public interface OnItemClickListener{
        void onItemClick(User user);
        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(@Nullable OnItemClickListener l){
        this.mListener = l;

    }
}
