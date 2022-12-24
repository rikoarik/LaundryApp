package com.laundryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.laundryapp.Adapter.MenuAdapter;
import com.laundryapp.Model.ModelMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MenuAdapter menuAdapter;
    ModelMenu modelMenu;
    List<ModelMenu> modelMenuList = new ArrayList<>();
    RecyclerView rvMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusbar();
        setInitLayout();
        setMenu();



    }
    private void setInitLayout() {
        rvMenu = findViewById(R.id.rvmenu);

        rvMenu.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL,false));
        rvMenu.setHasFixedSize(true);
    }
    private void setMenu() {
        modelMenu = new ModelMenu("Pesanan Baru", R.drawable.ic_pesanan_baru);
        modelMenuList.add(modelMenu);
        modelMenu = new ModelMenu("List Pesanan", R.drawable.ic_list_pesanan);
        modelMenuList.add(modelMenu);
        modelMenu = new ModelMenu("Riwayat", R.drawable.ic_riwayat);
        modelMenuList.add(modelMenu);


        menuAdapter = new MenuAdapter(this, modelMenuList);
        rvMenu.setAdapter(menuAdapter);

        menuAdapter.setOnItemClickListener(modelMenu -> {
            if (modelMenu.getTvTitle().equals("Pesanan Baru")) {
                Intent intent = new Intent(new Intent(MainActivity.this, PesananBaru.class));
                intent.putExtra(PesananBaru.DATA_TITLE, modelMenu.getTvTitle());
                startActivity(intent);
            } else if (modelMenu.getTvTitle().equals("List Pesanan")) {
                Intent intent = new Intent(new Intent(MainActivity.this, ListPesanan.class));
                intent.putExtra(ListPesanan.DATA_TITLE, modelMenu.getTvTitle());
                startActivity(intent);
            } else if (modelMenu.getTvTitle().equals("Riwayat")) {
                Intent intent = new Intent(new Intent(MainActivity.this, Riwayat.class));
                intent.putExtra(Riwayat.DATA_TITLE, modelMenu.getTvTitle());
                startActivity(intent);
            }
        });
    }
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