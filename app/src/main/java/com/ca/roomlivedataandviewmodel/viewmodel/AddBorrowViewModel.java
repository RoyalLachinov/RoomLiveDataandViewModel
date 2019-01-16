package com.ca.roomlivedataandviewmodel.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.ca.roomlivedataandviewmodel.model.AppDatabase;
import com.ca.roomlivedataandviewmodel.model.BorrowModel;

public class AddBorrowViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddBorrowViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDataBase(this.getApplication());

    }

    public void addBorrow(final BorrowModel borrowModel) {
        new addAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class addAsyncTask extends AsyncTask<BorrowModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final BorrowModel... params) {
            db.itemAndPersonModel().addBorrow(params[0]);
            return null;
        }

    }


}
