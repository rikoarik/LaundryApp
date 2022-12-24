package com.laundryapp.viewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.security.AppUriAuthenticationPolicy;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.laundryapp.Model.ModelListPesanan;
import com.laundryapp.Model.ModelRiwayat;
import com.laundryapp.R;
import com.laundryapp.Riwayat;
import com.laundryapp.dialogFragment;

import java.util.List;

public class RiwayatViewModel extends RecyclerView.Adapter<RiwayatViewModel.riwayatViewModel> {
    Context context;
    List<ModelRiwayat> modelRiwayat;


    public RiwayatViewModel(Context context, List<ModelRiwayat> modelRiwayat){
        this.context = context;
        this.modelRiwayat = modelRiwayat;
    }
    @NonNull
    @Override
    public RiwayatViewModel.riwayatViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_riwayat, parent, false);
        return new riwayatViewModel(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RiwayatViewModel.riwayatViewModel holder, int position) {
        ModelRiwayat item = modelRiwayat.get(position);
        holder.tvName.setText(item.getNamariwayat());
        holder.tvDate.setText("Pemesanan : "+item.getTanggalriwayat());
        holder.tvEstimasi.setText("Estimasi : "+item.getEstimasiriwayat());

        holder.btDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogFragment dialog = dialogFragment.newInstance();
                Bundle bundle =new Bundle();
                bundle.putString("nama", item.getNamariwayat());
                bundle.putString("tanggal", item.getTanggalriwayat());
                bundle.putString("estimasi", item.getEstimasiriwayat());
                bundle.putString("jenis_barang", item.getJenisriwayat());
                bundle.putString("jumlah_barang", item.getJumlahBarangriwayat());
                bundle.putInt("total_barang", item.getTotalItemriwayat());
                bundle.putInt("total_harga", item.getJumlahriwayat());
                dialog.setArguments(bundle);
                dialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"Detail Pesanan");
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelRiwayat.size();
    }
    public static class riwayatViewModel extends RecyclerView.ViewHolder {
        TextView tvName, tvDate,tvEstimasi,tvJenisBarang, tvJumlahBarang,tvTotalBarang, tvJumlah;
        Button  btDetail;

        public riwayatViewModel(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.namaHistory);
            tvDate = itemView.findViewById(R.id.tanggalHistory);
            tvEstimasi = itemView.findViewById(R.id.estimasiHistory);
            tvJenisBarang = itemView.findViewById(R.id.jenisBarang);
            tvJumlahBarang = itemView.findViewById(R.id.totalBarang);
            tvTotalBarang = itemView.findViewById(R.id.totalItem);
            tvJumlah = itemView.findViewById(R.id.totalPrice);
            btDetail = itemView.findViewById(R.id.btDetailHistory);

        }
    }




}
