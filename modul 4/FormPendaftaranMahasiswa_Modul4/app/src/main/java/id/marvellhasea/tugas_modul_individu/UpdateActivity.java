package id.marvellhasea.tugas_modul_individu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private EditText nim, nama;
    private Spinner prodi;
    private CheckBox c_menyanyi, c_olahraga, c_gambar, c_game, c_travel, c_baca;
    private Button delete, update;
    private Intent toRecycler;
    private RadioButton kelaminTerpilih, radioperempuan, radiolaki;
    private RadioGroup grup_kelamin;
    private SeekBar semester;
    private Integer id_mahasiswa;
    private String nama_mahasiswa, nim_mahasiswa, jeniskelamin_mahasiswa, prodi_mahasiswa, hobi_mahasiswa, semester_mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nim = findViewById(R.id.update_inputnim);
        nama = findViewById(R.id.update_inputnama);
        grup_kelamin = findViewById(R.id.update_grup_kelamin);
        radiolaki = findViewById(R.id.update_laki_laki);
        radioperempuan = findViewById(R.id.update_perempuan);
        prodi = findViewById(R.id.update_inputprodi);
        semester = findViewById(R.id.update_semester);

        c_menyanyi = findViewById(R.id.update_C_menyanyi);
        c_olahraga = findViewById(R.id.update_C_olahraga);
        c_gambar = findViewById(R.id.update_C_menggambar);
        c_game = findViewById(R.id.update_C_game);
        c_travel = findViewById(R.id.update_C_travel);
        c_baca = findViewById(R.id.update_C_baca);

        delete = findViewById(R.id.update_buttonDeleteForm);
        update = findViewById(R.id.update_buttonUpdateForm);

        Intent getData = getIntent();
        id_mahasiswa = getData.getIntExtra("id", 0);

        if (id_mahasiswa > 0) {
            final DatabaseMahasiswa dh = new DatabaseMahasiswa(getApplicationContext());
            Cursor cursor = dh.getDetailMahasiswa(id_mahasiswa);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                while (!cursor.isAfterLast()) {
                    nama.setText((cursor.getString(cursor.getColumnIndexOrThrow("nama_mahasiswa"))));
                    nim.setText((cursor.getString(cursor.getColumnIndexOrThrow("nim_mahasiswa"))));
                    jeniskelamin_mahasiswa = cursor.getString(cursor.getColumnIndexOrThrow("jeniskelamin_mahasiswa"));
                    if (jeniskelamin_mahasiswa.toString().equals("Laki-laki")) {
                        radiolaki.setChecked(true);
                    } else if (jeniskelamin_mahasiswa.toString().equals("Perempuan")) {
                        radioperempuan.setChecked(true);
                    }
                    prodi.setSelection(((ArrayAdapter<String>)prodi.getAdapter()).getPosition((cursor.getString(cursor.getColumnIndexOrThrow("prodi_mahasiswa")))));
                    hobi_mahasiswa = cursor.getString(cursor.getColumnIndexOrThrow("hobi_mahasiswa"));
                    if (hobi_mahasiswa.toString().contains("Menyanyi")) {
                        c_menyanyi.setChecked(true);
                    } else if (hobi_mahasiswa.toString().contains("Olahraga")) {
                        c_olahraga.setChecked(true);
                    } else if (hobi_mahasiswa.toString().contains("Menggambar")) {
                        c_gambar.setChecked(true);
                    } else if (hobi_mahasiswa.toString().contains("Bermain Game")) {
                        c_game.setChecked(true);
                    } else if (hobi_mahasiswa.toString().contains("Travelling")) {
                        c_travel.setChecked(true);
                    } else if (hobi_mahasiswa.toString().contains("Membaca")) {
                        c_baca.setChecked(true);
                    }
                    semester.setProgress(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("semester_mahasiswa"))));
                    cursor.moveToNext();
                }
                dh.close();
            }
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogAlertBuilder = new AlertDialog.Builder(UpdateActivity.this);
                dialogAlertBuilder.setTitle("Konfirmasi");
                dialogAlertBuilder
                        .setMessage("Hapus data?")
                        .setPositiveButton("Konfirmasi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DatabaseMahasiswa databaseMahasiswa = new DatabaseMahasiswa(getApplicationContext());

                                boolean hapusMahasiswa = databaseMahasiswa.deleteMahasiswa(id_mahasiswa);

                                if (hapusMahasiswa) {
                                    Toast.makeText(UpdateActivity.this, "Hapus Data Berhasil", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(UpdateActivity.this, "Hapus Data Gagal", Toast.LENGTH_SHORT).show();
                                }
                                databaseMahasiswa.close();
                                Intent intent = new Intent(getApplicationContext(),RecyclerActivity.class);
                                startActivity(intent);

                                Toast.makeText(getApplicationContext(), "Data telah dihapus!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog dialog = dialogAlertBuilder.create();
                dialog.show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "Berhasil di Update";
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
                    updateDataMahasiswa();
                }
            }
        });
    }

    private void updateDataMahasiswa() {

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

                        boolean update_mahasiswa = databaseMahasiswa.updateMahasiswa(mahasiswaHandler, id_mahasiswa);
                        if (update_mahasiswa) {
                            Toast.makeText(UpdateActivity.this, "Update Mahasiswa Berhasil", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpdateActivity.this, "Update Mahasiswa Gagal", Toast.LENGTH_SHORT).show();
                        }
                        databaseMahasiswa.close();

                        Intent intent = new Intent(getApplicationContext(),RecyclerActivity.class);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "Data telah diupdate!", Toast.LENGTH_SHORT).show();
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
}