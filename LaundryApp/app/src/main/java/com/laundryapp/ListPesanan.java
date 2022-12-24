package com.laundryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.laundryapp.Model.ModelListPesanan;
import com.laundryapp.utils.DatabaseHandler;
import com.laundryapp.viewModel.ListPesananViewModel;

import java.util.List;

public class ListPesanan extends AppCompatActivity {

    public static final String DATA_TITLE = "TITLE";

    List<ModelListPesanan> listPesanan;
    RecyclerView recyclerView;
    DatabaseHandler db;
    ListPesananViewModel myAdapter;
    String strTitle;
    TextView Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pesanan);
        recyclerView = findViewById(R.id.itemPesanan);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setStatusbar();

        Title = findViewById(R.id.tvTitle);

        strTitle = getIntent().getExtras().getString(DATA_TITLE);
        if (strTitle != null) {
            Title.setText(strTitle);
        }
        db = new DatabaseHandler(this);
        listPesanan = db.getListPesanan();
        myAdapter = new ListPesananViewModel(this, listPesanan);
        recyclerView.setAdapter(myAdapter);



    }
    @SuppressLint("ObsoleteSdkInt")
    private void setStatusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }
}