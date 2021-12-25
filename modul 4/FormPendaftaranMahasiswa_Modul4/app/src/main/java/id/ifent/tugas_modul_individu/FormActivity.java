package id.ifent.tugas_modul_individu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    private EditText nim, nama;
    private Spinner prodi;
    private CheckBox c_menyanyi, c_olahraga, c_gambar, c_game, c_travel, c_baca;
    private Button reset, submit;
    private Intent toRecycler;
    private RadioButton kelaminTerpilih, radiolaki;
    private RadioGroup grup_kelamin;
    private SeekBar semester;

    private String nama_mahasiswa, nim_mahasiswa, jeniskelamin_mahasiswa, prodi_mahasiswa, hobi_mahasiswa, semester_mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

//      Set ID komponen form java dan xml
        nim = findViewById(R.id.inputnim);
        nama = findViewById(R.id.inputnama);
        grup_kelamin = findViewById(R.id.grup_kelamin);
        radiolaki = findViewById(R.id.laki_laki);
        prodi = findViewById(R.id.inputprodi);
        semester = findViewById(R.id.semester);

        c_menyanyi = findViewById(R.id.C_menyanyi);
        c_olahraga = findViewById(R.id.C_olahraga);
        c_gambar = findViewById(R.id.C_menggambar);
        c_game = findViewById(R.id.C_game);
        c_travel = findViewById(R.id.C_travel);
        c_baca = findViewById(R.id.C_baca);

        reset = findViewById(R.id.buttonResetForm);
        submit = findViewById(R.id.buttonSubmitForm);


//      Instance object Intent
        toRecycler = new Intent(this, RecyclerActivity.class);

//      Atur input nama menjadi focus pertama
        nama.requestFocus();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Berhasil di Reset";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                resetForm();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Berhasil di Submit";
                int duration = Toast.LENGTH_SHORT;

                nama_mahasiswa = nama.getText().toString();
                nim_mahasiswa = nim.getText().toString();
                jeniskelamin_mahasiswa = "";
                int idKelamin = grup_kelamin.getCheckedRadioButtonId();
                kelaminTerpilih = (RadioButton) findViewById(idKelamin);
                jeniskelamin_mahasiswa = kelaminTerpilih.getText().toString();
                prodi_mahasiswa = prodi.getSelectedItem().toString();
                hobi_mahasiswa = "";
                if (c_menyanyi.isChecked()) {
                    hobi_mahasiswa = hobi_mahasiswa + c_menyanyi.getText().toString() + ",";
                }
                if (c_olahraga.isChecked()) {
                    hobi_mahasiswa = hobi_mahasiswa + c_olahraga.getText().toString() + ",";
                }
                if (c_gambar.isChecked()) {
                    hobi_mahasiswa = hobi_mahasiswa + c_gambar.getText().toString() + ",";
                }
                if (c_game.isChecked()) {
                    hobi_mahasiswa = hobi_mahasiswa + c_game.getText().toString() + ",";
                }
                if (c_travel.isChecked()) {
                    hobi_mahasiswa = hobi_mahasiswa + c_travel.getText().toString() + ",";
                }
                if (c_baca.isChecked()) {
                    hobi_mahasiswa = hobi_mahasiswa + c_baca.getText().toString() + ",";
                }
                if (!hobi_mahasiswa.isEmpty()) {
                    hobi_mahasiswa = hobi_mahasiswa.substring(0, hobi_mahasiswa.length() - 1);
                }
                Integer semesterTerpilih = semester.getProgress() + 1;
                semester_mahasiswa = semesterTerpilih.toString();

                if (nama_mahasiswa.isEmpty()) {
                    nama.requestFocus();
                    nama.setError("Silahkan isi nama dahulu!");
                } else if (nim_mahasiswa.isEmpty()) {
                    nim.requestFocus();
                    nim.setError("Silahkan isi NIM dahulu!");
                } else if (jeniskelamin_mahasiswa.isEmpty()) {
                    grup_kelamin.requestFocus();
                } else if (prodi_mahasiswa.isEmpty()) {
                    prodi.requestFocus();
                } else if (hobi_mahasiswa.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Pilih minimal 1 hobi", Toast.LENGTH_LONG).show();
                } else if (semester_mahasiswa.isEmpty()) {
                    semester.requestFocus();
                } else {
                    simpanDataMahasiswa();
                }
            }
        });
        Log.i("State", "on Create Activity");
    }


    private void simpanDataMahasiswa() {

        StringBuilder rekapData = new StringBuilder();
        rekapData.append("Nama : " + nama_mahasiswa + "\n\n");
        rekapData.append("NIM : " + nim_mahasiswa + "\n\n");
        rekapData.append("Kelamin : " + jeniskelamin_mahasiswa + "\n\n");
        rekapData.append("Prodi : " + prodi_mahasiswa + "\n\n");
        rekapData.append("Hobi : " + hobi_mahasiswa + "\n\n");
        rekapData.append("Semester : " + semester_mahasiswa + "\n\n");


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Apakah data sudah benar?");


        // set pesan dari dialog
        alertDialogBuilder
                .setMessage(rekapData.toString())
                .setCancelable(false)
                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        DatabaseMahasiswa databaseMahasiswa = new DatabaseMahasiswa(getApplicationContext());
                        MahasiswaHandler mahasiswaHandler = new MahasiswaHandler();
                        mahasiswaHandler.setNama(nama_mahasiswa);
                        mahasiswaHandler.setNim(nim_mahasiswa);
                        mahasiswaHandler.setJenisKelamin(jeniskelamin_mahasiswa);
                        mahasiswaHandler.setProgramStudi(prodi_mahasiswa);
                        mahasiswaHandler.setHobi(hobi_mahasiswa);
                        mahasiswaHandler.setSemester(semester_mahasiswa);

                        boolean tambah_mahasiswa = databaseMahasiswa.addMahasiswa(mahasiswaHandler);
                        if (tambah_mahasiswa) {
                            Toast.makeText(FormActivity.this, "Tambah Mahasiswa Berhasil", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FormActivity.this, "Tambah Mahasiswa Gagal", Toast.LENGTH_SHORT).show();
                        }
                        databaseMahasiswa.close();

                        Intent intent = new Intent(getApplicationContext(),RecyclerActivity.class);
                        intent.putExtra("nama",nama_mahasiswa );
                        intent.putExtra("NIM",nim_mahasiswa );
                        intent.putExtra("JenikKelamin",jeniskelamin_mahasiswa );
                        intent.putExtra("Prodi",prodi_mahasiswa );
                        intent.putExtra("Hobi",hobi_mahasiswa );
                        intent.putExtra("Semester",semester_mahasiswa );
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "Data telah disimpan!", Toast.LENGTH_SHORT).show();
                    }

                })
                .setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

    public void resetForm() {
        nim.setText(null);
        nama.setText(null);
        prodi.setSelection(0);
        grup_kelamin.clearCheck();
        radiolaki.setChecked(true);
        semester.setProgress(0);
        c_menyanyi.setChecked(false);
        c_olahraga.setChecked(false);
        c_gambar.setChecked(false);
        c_game.setChecked(false);
        c_travel.setChecked(false);
        c_baca.setChecked(false);
    }
}