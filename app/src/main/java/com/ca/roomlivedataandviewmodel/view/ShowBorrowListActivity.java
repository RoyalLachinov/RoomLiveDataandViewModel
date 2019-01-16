package com.ca.roomlivedataandviewmodel.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ca.roomlivedataandviewmodel.R;
import com.ca.roomlivedataandviewmodel.model.BorrowModel;
import com.ca.roomlivedataandviewmodel.viewmodel.ShowBorrowListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowBorrowListActivity extends AppCompatActivity implements View.OnLongClickListener {

    private ShowBorrowListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reduce image size
        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        //options.inSampleSize = 3;
//        options.inSampleSize = calculateInSampleSize(options,100,100);
//        BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<BorrowModel>(), ShowBorrowListActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowBorrowListActivity.this));
        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(ShowBorrowListActivity.this).get(ShowBorrowListViewModel.class);
        viewModel.getBorrowedItemList().observe(ShowBorrowListActivity.this, new Observer<List<BorrowModel>>() {
            @Override
            public void onChanged(@Nullable List<BorrowModel> borrowModels) {
                recyclerViewAdapter.addItems(borrowModels);
            }
        });
    }

    @Override
    public boolean onLongClick(View v) {
        BorrowModel borrowModel = (BorrowModel) v.getTag();
        viewModel.deleteItem(borrowModel);
        return true;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
