package com.laundryapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.laundryapp.Model.ModelListPesanan;
import com.laundryapp.utils.DatabaseHandler;
import com.laundryapp.utils.FunctionHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PesananBaru extends AppCompatActivity {
    public static final String DATA_TITLE = "TITLE";
    EditText tvName, tvDate, tvEstimasi;

    int hargaKaos = 7000, hargaCelanaPendek = 5000,hargaCelanaPanjang = 6000, hargaRok = 5000, hargaGamis = 7000, hargaKerudung = 5000;
    int itemCount1 = 0, itemCount2 = 0, itemCount3 = 0, itemCount4 = 0, itemCount5 = 0, itemCount6 = 0;
    int countKaos, countCelanaPendek, countCelanaPanjang, countRok, countGamis, countKerudung, totalItems, totalPrice;
    int Checkbox1,Checkbox2,Checkbox3,Checkbox4,Checkbox5,Checkbox6;
    CheckBox check1,check2,check3,check4,check5,check6;

    String strTitle;
    Button btn;
    String kaos, clnpndk, clnpanjang, rok, gamis, kerudung;
    ImageView Add1, Add2, Add3, Add4, Add5,Add6,
            Minus1, Minus2, Minus3, Minus4, Minus5, Minus6;
    TextView Title, JumlahBarang, TotalPrice, Kaos, CelanaPendek, CelanaPanjang, Rok, Gamis, Kerudung,
            PriceKaos, PriceCelanaPendek, PriceCelanaPanjang, PriceRok, PriceGamis, PriceKerudung;

    private String jenis1,jenis2,jenis3,jenis4,jenis5,jenis6;
    final Calendar myCalendar= Calendar.getInstance();
    final Calendar cl = Calendar.getInstance();

    ArrayList<String> jenisList;
    List<String> jumlahitem;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_baru);

        db = new DatabaseHandler(PesananBaru.this);

        tvName = findViewById(R.id.namaInput);
        tvDate = findViewById(R.id.dateInput);
        tvEstimasi = findViewById(R.id.estimasiInput);
        Title = findViewById(R.id.tvTitle);

        JumlahBarang = findViewById(R.id.tvJumlahBarang);
        TotalPrice = findViewById(R.id.tvTotalPrice);

        Kaos = findViewById(R.id.tvKaos);
        CelanaPendek = findViewById(R.id.tvCelanaPendek);
        CelanaPanjang = findViewById(R.id.tvJeans);
        Rok = findViewById(R.id.tvRok);
        Gamis = findViewById(R.id.tvGamis);
        Kerudung = findViewById(R.id.tvKerudung);
        PriceKaos = findViewById(R.id.tvPriceKaos);
        PriceCelanaPendek = findViewById(R.id.tvPriceCelanaPendek);
        PriceCelanaPanjang = findViewById(R.id.tvPriceJeans);
        PriceRok = findViewById(R.id.tvPriceRok);
        PriceGamis = findViewById(R.id.tvPriceGamis);
        PriceKerudung = findViewById(R.id.tvPriceKerudung);

        Add1 = findViewById(R.id.imageAdd1);
        Add2 = findViewById(R.id.imageAdd2);
        Add3 = findViewById(R.id.imageAdd3);
        Add4 = findViewById(R.id.imageAdd4);
        Add5 = findViewById(R.id.imageAdd5);
        Add6 = findViewById(R.id.imageAdd6);

        Minus1 = findViewById(R.id.imageMinus1);
        Minus2 = findViewById(R.id.imageMinus2);
        Minus3 = findViewById(R.id.imageMinus3);
        Minus4 = findViewById(R.id.imageMinus4);
        Minus5 = findViewById(R.id.imageMinus5);
        Minus6 = findViewById(R.id.imageMinus6);

        check1 =findViewById(R.id.checkbox1);
        check2 =findViewById(R.id.checkbox2);
        check3 =findViewById(R.id.checkbox3);
        check4 =findViewById(R.id.checkbox4);
        check5 =findViewById(R.id.checkbox5);
        check6 =findViewById(R.id.checkbox6);

        btn = findViewById(R.id.btnCheckout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                saveData();
                                tvName.getText().clear();
                                tvDate.getText().clear();
                                tvEstimasi.getText().clear();
                                PriceKaos.setText("0");
                                PriceCelanaPendek.setText("0");
                                PriceCelanaPanjang.setText("0");
                                PriceRok.setText("0");
                                PriceGamis.setText("0");
                                PriceKerudung.setText("0");
                                TotalPrice.setText(FunctionHelper.rupiahFormat(0));
                                JumlahBarang.setText("0 items");
                                check1.setChecked(false);
                                check2.setChecked(false);
                                check3.setChecked(false);
                                check4.setChecked(false);
                                check5.setChecked(false);
                                check6.setChecked(false);

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(PesananBaru.this);
                builder.setMessage("Apakah Pesanan Sudah Benar?").setPositiveButton("Ya", dialogClickListener)
                        .setNegativeButton("Tidak", dialogClickListener).show();


            }
        });

        strTitle = getIntent().getExtras().getString(DATA_TITLE);
        if (strTitle != null) {
            Title.setText(strTitle);
        }
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                datePesanan();
            }
        };
        DatePickerDialog.OnDateSetListener tgl =new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                dateEstimasi();
            }
        };
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(PesananBaru.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        tvEstimasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(PesananBaru.this,tgl,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        JumlahBarang.setText("0 items");
        TotalPrice.setText("Rp 0");
        setStatusbar();
        setDataKaos();
        setDataCelanaPendek();
        setDataRok();
        setDataCelanaPanjang();
        setDataGamis();
        setDataKerudung();



    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void datePesanan(){
        String myFormat="dd MMMM YYYY";
        @SuppressLint({"NewApi", "LocalSuppress"}) SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        tvDate.setText(dateFormat.format(myCalendar.getTime()));

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void dateEstimasi(){
        String Format="dd MMMM YYYY";
        @SuppressLint({"NewApi", "LocalSuppress"}) SimpleDateFormat dateFormat=new SimpleDateFormat(Format, Locale.US);
        tvEstimasi.setText(dateFormat.format(myCalendar.getTime()));
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

    private void setDataKaos() {
        Kaos.setText(FunctionHelper.rupiahFormat(hargaKaos));

        Add1.setOnClickListener(v -> {
            check1.setChecked(true);
            itemCount1 = itemCount1 + 1;
            PriceKaos.setText(String.valueOf(itemCount1));
            countKaos = hargaKaos * itemCount1;
            setTotalPrice();
        });
        Minus1.setOnClickListener(v -> {
            if (itemCount1 > 0) {
                itemCount1 = itemCount1 - 1;
                PriceKaos.setText(String.valueOf(itemCount1));
            }
            if (itemCount1 == 0) {
                check1.setChecked(false);
            }
            countKaos = hargaKaos * itemCount1;
            setTotalPrice();
        });
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    jenis1 = "Kaos";
                    Checkbox1=1;
                    int jumlahitem = Integer.parseInt(PriceKaos.getText().toString());
                    countKaos = hargaKaos * jumlahitem;
                    setTotalPrice();
                } else {
                    jenis1="Kaos";
                    Checkbox1=0;
                    itemCount1 = 0;
                    PriceKaos.setText(String.valueOf(itemCount1));
                    countKaos = hargaKaos * itemCount1;
                    setTotalPrice();
                }
            }
        });
    }

    private void setDataCelanaPendek() {
        CelanaPendek.setText((hargaCelanaPendek));

        Add2.setOnClickListener(v -> {
            check2.setChecked(true);
            itemCount2 = itemCount2 + 1;
            PriceCelanaPendek.setText(String.valueOf(itemCount2));
            countCelanaPendek = hargaCelanaPendek * itemCount2;
            setTotalPrice();
        });

        Minus2.setOnClickListener(v -> {
            if (itemCount2 > 0) {
                itemCount2 = itemCount2 - 1;
                PriceCelanaPendek.setText(String.valueOf(itemCount2));
            }
            if (itemCount2 == 0){
                check2.setChecked(false);
            }
            countCelanaPendek = hargaCelanaPendek * itemCount2;
            setTotalPrice();
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    jenis2 = "Celana Pendek";
                    Checkbox2=1;
                    int jumlahitem = Integer.parseInt(PriceCelanaPendek.getText().toString());
                    countCelanaPendek = hargaCelanaPendek * jumlahitem;
                    setTotalPrice();
                } else {
                    jenis2="Celana Pendek";
                    itemCount2 = 0;
                    Checkbox2=0;
                    PriceCelanaPendek.setText(String.valueOf(itemCount2));
                    countCelanaPendek = hargaCelanaPendek * itemCount2;
                    setTotalPrice();
                }
            }
        });
    }

    private void setDataCelanaPanjang() {

        CelanaPanjang.setText(FunctionHelper.rupiahFormat(hargaCelanaPanjang));

        Add3.setOnClickListener(v -> {
            check3.setChecked(true);
            itemCount3 = itemCount3 + 1;
            PriceCelanaPanjang.setText(String.valueOf(itemCount3));
            countCelanaPanjang = hargaCelanaPanjang * itemCount3;
            setTotalPrice();
        });

        Minus3.setOnClickListener(v -> {
            if (itemCount3 > 0) {
                itemCount3 = itemCount3 - 1;
                PriceCelanaPanjang.setText(String.valueOf(itemCount3));
            }
            if (itemCount3 == 0) {
                check3.setChecked(false);
            }
            countCelanaPanjang = hargaCelanaPanjang * itemCount3;
            setTotalPrice();
        });
        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    jenis3 = "Celana Panjang";
                    Checkbox3=1;
                    int jumlahitem = Integer.parseInt(PriceCelanaPanjang.getText().toString());
                    countCelanaPanjang = hargaCelanaPanjang * jumlahitem;
                    setTotalPrice();
                } else {
                    jenis3="Celana Panjang";
                    Checkbox3=0;
                    itemCount3 = 0;
                    PriceCelanaPanjang.setText(String.valueOf(itemCount3));
                    countCelanaPanjang = hargaCelanaPanjang * itemCount3;
                    setTotalPrice();
                }
            }
        });
    }

    private void setDataRok() {
        Rok.setText(FunctionHelper.rupiahFormat(hargaRok));

        Add4.setOnClickListener(v -> {
            check4.setChecked(true);
            itemCount4 = itemCount4 + 1;
            PriceRok.setText(String.valueOf(itemCount4));
            countRok = hargaRok * itemCount4;
            setTotalPrice();
        });

        Minus4.setOnClickListener(v -> {
            if (itemCount4 > 0) {
                itemCount4 = itemCount4 - 1;
                PriceRok.setText(String.valueOf(itemCount4));
            }
            if (itemCount4 == 0) {
                check4.setChecked(false);
            }
            countRok = hargaRok * itemCount4;
            setTotalPrice();
        });
        check4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    jenis4 = "Rok";
                    Checkbox4=1;
                    int jumlahitem = Integer.parseInt(PriceRok.getText().toString());

                    countRok = hargaRok * jumlahitem;
                    setTotalPrice();
                } else {
                    jenis4="Rok";
                    Checkbox4=0;
                    itemCount4 = 0;
                    PriceRok.setText(String.valueOf(itemCount4));
                    countRok = hargaRok * itemCount4;
                    setTotalPrice();
                }
            }
        });
    }

    private void setDataGamis() {
        Gamis.setText(FunctionHelper.rupiahFormat(hargaGamis));

        Add5.setOnClickListener(v -> {
            check5.setChecked(true);
            itemCount5 = itemCount5 + 1;
            PriceGamis.setText(String.valueOf(itemCount5));
            countGamis = hargaGamis * itemCount5;
            setTotalPrice();
        });

        Minus5.setOnClickListener(v -> {
            if (itemCount5 > 0) {
                itemCount5 = itemCount5 - 1;
                PriceGamis.setText(String.valueOf(itemCount5));
            }
            if (itemCount5 == 0) {
                check5.setChecked(false);
            }
            countGamis = hargaGamis * itemCount5;
            setTotalPrice();
        });
        check5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    jenis5 = "Gamis";
                    Checkbox5=1;
                    int jumlahitem = Integer.parseInt(PriceGamis.getText().toString());
                    countGamis = hargaGamis * jumlahitem;
                    setTotalPrice();
                } else {
                    jenis5="Gamis";
                    Checkbox5=0;
                    itemCount5 = 0;
                    PriceGamis.setText(String.valueOf(itemCount5));
                    countGamis = hargaGamis * itemCount5;
                    setTotalPrice();
                }
            }
        });
    }
    private void setDataKerudung() {
        Kerudung.setText(FunctionHelper.rupiahFormat(hargaKerudung));

        Add6.setOnClickListener(v -> {
            check6.setChecked(true);
            itemCount6 = itemCount6 + 1;
            PriceKerudung.setText(String.valueOf(itemCount6));
            countKerudung = hargaKerudung * itemCount6;
            setTotalPrice();
        });
        Minus6.setOnClickListener(v -> {
            if (itemCount6 > 0) {
                itemCount6 = itemCount6 - 1;
                PriceKerudung.setText(String.valueOf(itemCount6));
            }
            if (itemCount6 == 0) {
                check1.setChecked(false);
            }
            countKerudung = hargaKerudung * itemCount6;
            setTotalPrice();
        });
        check6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    Checkbox6=1;
                    jenis6 = "Kerudung atau Sarung Tangan";
                    int jumlahitem = Integer.parseInt(PriceKerudung.getText().toString());
                    countKerudung = hargaKerudung * jumlahitem;
                    setTotalPrice();
                } else {
                    jenis6 = "Kerudung atau Sarung Tangan";
                    Checkbox6=0;
                    PriceKerudung.setText(String.valueOf(itemCount6));
                    countKerudung = hargaKerudung * itemCount6;
                    setTotalPrice();
                }
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void setTotalPrice() {
        totalItems = itemCount1 + itemCount2 + itemCount3 + itemCount4 + itemCount5 + itemCount6;
        totalPrice = countKaos + countCelanaPendek + countCelanaPanjang + countRok + countGamis + countKerudung;

        int totalBarang = Checkbox1+Checkbox2+Checkbox3+Checkbox4+Checkbox5+Checkbox6;
        JumlahBarang.setText(totalBarang + " items");
        TotalPrice.setText(FunctionHelper.rupiahFormat(totalPrice));
    }


    public void saveData(){
            if (TextUtils.isEmpty(tvName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Masukkan Nama Pelanggan", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(tvDate.getText().toString()))
                Toast.makeText(getApplicationContext(), "Masukkan Tanggal Pemesanan", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(tvEstimasi.getText().toString()))
                Toast.makeText(getApplicationContext(), "Masukkan Estimasi Pemesanan", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(TotalPrice.getText().toString()))
                Toast.makeText(getApplicationContext(), "Masukkan Estimasi Pemesanan", Toast.LENGTH_SHORT).show();
            else if (!check1.isChecked()&!check2.isChecked()&!check3.isChecked()&!check4.isChecked()&!check5.isChecked()&!check6.isChecked())
                Toast.makeText(getApplicationContext(), "Pilih Barang Terlebih Dahulu", Toast.LENGTH_SHORT).show();
            else {
                ModelListPesanan pesanan = new ModelListPesanan();
                pesanan.setNama(tvName.getText().toString());
                pesanan.setTanggal(tvDate.getText().toString());
                pesanan.setEstimasi(tvEstimasi.getText().toString());
                pesanan.setTotalItem(totalItems);




                String item = itemCount1 + System.getProperty("line.separator")
                        + itemCount2+ System.getProperty("line.separator")
                        + itemCount3+ System.getProperty("line.separator")
                        + itemCount4+ System.getProperty("line.separator")
                        + itemCount5+ System.getProperty("line.separator")
                        + itemCount6+ System.getProperty("line.separator");

                String jenis = "Kaos" + System.getProperty("line.separator")
                        +"Celana Pendek"+ System.getProperty("line.separator")
                        +"Celana Panjang"+ System.getProperty("line.separator")
                        +"Rok" + System.getProperty("line.separator")
                        +"Gamis"+ System.getProperty("line.separator")
                        +"Kerudung atau Sapu Tangan"+ System.getProperty("line.separator");

                pesanan.setJenis(jenis);
                pesanan.setJumlahBarang(item);
                pesanan.setJumlah(totalPrice);
                db.addPesanan(pesanan);

                Toast.makeText(getApplicationContext(), "Data Pesanan Tersimpan", Toast.LENGTH_SHORT).show();
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