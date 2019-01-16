package com.ca.roomlivedataandviewmodel.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ca.roomlivedataandviewmodel.R;
import com.ca.roomlivedataandviewmodel.model.BorrowModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<BorrowModel> borrowModelList;
    private View.OnLongClickListener longClickListener;

    public RecyclerViewAdapter(List<BorrowModel> borrowModelList, View.OnLongClickListener longClickListener) {
        this.borrowModelList = borrowModelList;
        this.longClickListener = longClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        BorrowModel borrowModel = borrowModelList.get(position);
        holder.itemTextView.setText(borrowModel.getItemName());
        holder.nameTextView.setText(borrowModel.getPersonName());
        holder.dateTextView.setText(borrowModel.getBorrowDate().toLocaleString().substring(0, 11));
        holder.itemView.setTag(borrowModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return borrowModelList.size();
    }

    public void addItems(List<BorrowModel> borrowModelList) {
        this.borrowModelList = borrowModelList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private TextView nameTextView;
        private TextView dateTextView;

        ViewHolder(View view) {
            super(view);
            itemTextView = (TextView) view.findViewById(R.id.itemTextView);
            nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            dateTextView = (TextView) view.findViewById(R.id.dateTextView);
        }
    }
}
