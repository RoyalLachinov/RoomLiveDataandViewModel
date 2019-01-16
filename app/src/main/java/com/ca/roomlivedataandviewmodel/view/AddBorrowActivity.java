package com.ca.roomlivedataandviewmodel.view;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.ca.roomlivedataandviewmodel.R;
import com.ca.roomlivedataandviewmodel.model.BorrowModel;
import com.ca.roomlivedataandviewmodel.viewmodel.AddBorrowViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

public class AddBorrowActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private Date date;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;


    private EditText itemEditText;
    private EditText nameEditText;

    private AddBorrowViewModel addBorrowViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_borrow);

        itemEditText = findViewById(R.id.itemName);
        nameEditText = findViewById(R.id.personName);

        calendar = Calendar.getInstance();
        addBorrowViewModel = ViewModelProviders.of(this).get(AddBorrowViewModel.class);

        datePickerDialog = new DatePickerDialog(this, AddBorrowActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemEditText.getText() == null || nameEditText.getText() == null || date == null)
                    Toast.makeText(AddBorrowActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
                else {
                    addBorrowViewModel.addBorrow(new BorrowModel(
                            itemEditText.getText().toString(),
                            nameEditText.getText().toString(),
                            date
                    ));
                    finish();
                }
            }
        });


        findViewById(R.id.dateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        findViewById(R.id.showList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddBorrowActivity.this, ShowBorrowListActivity.class));
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();

    }
}
