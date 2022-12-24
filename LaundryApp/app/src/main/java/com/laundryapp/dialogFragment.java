package com.laundryapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.laundryapp.Model.ModelListPesanan;

import java.util.Arrays;
import java.util.Scanner;

public class dialogFragment extends DialogFragment {
    private Button cancel;
    private TextView nama, tgl, jenisbarang, totalbarang, totalitem, totalprice;

    public static dialogFragment newInstance(){
        return new dialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.bg_circle_radius);
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        getDialog().setTitle("Detail Pesanan");
        nama = view.findViewById(R.id.namadetail);
        tgl = view.findViewById(R.id.tgldetail);
        jenisbarang = view.findViewById(R.id.jenisBarang);
        totalbarang = view.findViewById(R.id.totalBarang);
        totalitem = view.findViewById(R.id.totalItem);
        totalprice = view.findViewById(R.id.totalPrice);
        cancel = view.findViewById(R.id.btcancel);

        Bundle bundle = this.getArguments();
        nama.setText(bundle.getString("nama"));
        tgl.setText(bundle.getString("tanggal"));
        jenisbarang.setText(bundle.getString("jenis_barang"));
        totalbarang.setText(bundle.getString("jumlah_barang"));
        totalitem.setText(String.valueOf(bundle.getInt("total_barang")));
        totalprice.setText(String.valueOf(bundle.getInt("total_harga")));


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return view;

    }


}
