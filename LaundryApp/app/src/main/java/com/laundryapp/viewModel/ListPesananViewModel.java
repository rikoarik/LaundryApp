package com.laundryapp.viewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import com.laundryapp.Model.ModelListPesanan;
import com.laundryapp.Model.ModelRiwayat;
import com.laundryapp.R;
import com.laundryapp.Riwayat;
import com.laundryapp.dialogFragment;
import com.laundryapp.utils.DatabaseHandler;
import com.laundryapp.utils.FunctionHelper;

import java.util.ArrayList;
import java.util.List;

public class ListPesananViewModel extends RecyclerView.Adapter<ListPesananViewModel.myViewModel> {
    Context context;
    List<ModelListPesanan> modelListPesanan;
    DatabaseHandler db;


    public ListPesananViewModel(Context context, List<ModelListPesanan> modelListPesanan){
        this.context = context;
        this.modelListPesanan = modelListPesanan;
    }

    @NonNull
    @Override
    public ListPesananViewModel.myViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pesanan, parent, false);
        return new myViewModel(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListPesananViewModel.myViewModel holder, int position) {

        ModelListPesanan item = modelListPesanan.get(position);
        holder.tvName.setText(item.getNama());
        holder.tvDate.setText("Pemesanan : "+item.getTanggal());
        holder.tvEstimasi.setText("Estimasi : "+item.getEstimasi());
        holder.btDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogFragment dialog = dialogFragment.newInstance();
                Bundle bundle =new Bundle();
                bundle.putString("nama", item.getNama());
                bundle.putString("tanggal", item.getTanggal());
                bundle.putString("jenis_barang", item.getJenis());
                bundle.putString("jumlah_barang", item.getJumlahBarang());
                bundle.putInt("total_barang", item.getTotalItem());
                bundle.putInt("total_harga", item.getJumlah());
                dialog.setArguments(bundle);
                dialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"Detail Pesanan");
            }
        });
        holder.btfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                db = new DatabaseHandler(context);
                                ModelRiwayat riwayat = new ModelRiwayat();
                                riwayat.setNamariwayat(item.getNama());
                                riwayat.setTanggalriwayat(item.getTanggal());
                                riwayat.setEstimasiriwayat(item.getEstimasi());
                                riwayat.setJenisriwayat(item.getJenis());
                                riwayat.setJumlahBarangriwayat(item.getJumlahBarang());
                                riwayat.setTotalItemriwayat(Integer.parseInt(String.valueOf(item.getTotalItem())));
                                riwayat.setJumlahriwayat(Integer.parseInt(String.valueOf(item.getJumlah())));
                                db.addRiwayat(riwayat);


                                int newPosition = holder.getAdapterPosition();

                                db.delete(item.getNama());
                                modelListPesanan.remove(newPosition);
                                notifyItemRemoved(newPosition);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Pesanan telah selesai", Toast.LENGTH_LONG).show();
                                Toast.makeText(context, "Silahkan cek riwayat", Toast.LENGTH_LONG).show();

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Apakah anda ingin menyelesaikan pesanan?").setPositiveButton("Ya", dialogClickListener)
                        .setNegativeButton("Tidak", dialogClickListener).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelListPesanan.size();
    }


    public static class myViewModel extends RecyclerView.ViewHolder{
        TextView tvName, tvDate,tvEstimasi,tvJenisBarang, tvJumlahBarang,tvTotalBarang, tvJumlah;
        Button btfinish, btDetail;

        public myViewModel(@NonNull View itemView) {
            super(itemView);

            btfinish = itemView.findViewById(R.id.btnhistory);
            tvEstimasi = itemView.findViewById(R.id.estimasi);
            tvName = itemView.findViewById(R.id.nama);
            tvDate = itemView.findViewById(R.id.tanggal);
            tvJenisBarang = itemView.findViewById(R.id.jenisBarang);
            tvJumlahBarang = itemView.findViewById(R.id.totalBarang);
            tvTotalBarang = itemView.findViewById(R.id.totalItem);
            tvJumlah = itemView.findViewById(R.id.totalPrice);
            btDetail = itemView.findViewById(R.id.btDetail);


        }


    }
}
