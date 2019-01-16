package com.ca.roomlivedataandviewmodel.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.ca.roomlivedataandviewmodel.model.AppDatabase;
import com.ca.roomlivedataandviewmodel.model.BorrowModel;

import java.util.ArrayList;
import java.util.List;

public class ShowBorrowListViewModel extends AndroidViewModel {

    private final AppDatabase appDatabase;
    private final LiveData<List<BorrowModel>> borrowedItemList;

    public ShowBorrowListViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDataBase(this.getApplication());
        borrowedItemList = appDatabase.itemAndPersonModel().getAllBorrowedItemsArrayListLiveData();
    }

    public LiveData<List<BorrowModel>> getBorrowedItemList() {
        return borrowedItemList;
    }

    public void deleteItem(BorrowModel borrowModel) {
        new DeleteAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class DeleteAsyncTask extends AsyncTask<BorrowModel, Void, Void> {
        private AppDatabase db;

        public DeleteAsyncTask(AppDatabase db) {
            this.db = db;
        }


        @Override
        protected Void doInBackground(BorrowModel... borrowModels) {

            db.itemAndPersonModel().deleteBorrow(borrowModels[0]);
            return null;
        }
    }
}
