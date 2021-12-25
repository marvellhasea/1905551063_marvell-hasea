package id.ifent.tugas_modul_individu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private TextView no_data;
    private ImageView empty_imageview;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter; //Jembatan antara data arraylist dengan recyclerview
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton add_button;
    private Object MahasiswaHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        ArrayList<MahasiswaHandler> mahasiswaHandler = new ArrayList<MahasiswaHandler>();

        Intent intent = getIntent();
        String nama_ahasiswa = intent.getStringExtra("nama");
        String nim_mahasiswa = intent.getStringExtra("NIM");
        String jeniskelamin_mahasiswa = intent.getStringExtra("JenikKelamin");
        String prodi_mahasiswa = intent.getStringExtra("Prodi");
        String hobi_mahasiswa = intent.getStringExtra("Hobi");
        String semester_mahasiswa = intent.getStringExtra("Semester");

            no_data = findViewById(R.id.no_data);
            empty_imageview = findViewById(R.id.empty_imageview);
            recyclerView = findViewById(R.id.recyclerView);
            add_button = findViewById(R.id.add_button);
            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RecyclerActivity.this, FormActivity.class);
                    startActivity(intent);
                }
            });

        final DatabaseMahasiswa db = new DatabaseMahasiswa(getApplicationContext());
        Cursor cursor = db.getMahasiswa();
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            no_data.setVisibility(View.GONE);
            empty_imageview.setVisibility(View.GONE);
            while (!cursor.isAfterLast()) {
                MahasiswaHandler mahasiswaHandlerList = new MahasiswaHandler();
                mahasiswaHandlerList.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                mahasiswaHandlerList.setNama((cursor.getString(cursor.getColumnIndexOrThrow("nama_mahasiswa"))));
                mahasiswaHandlerList.setNim((cursor.getString(cursor.getColumnIndexOrThrow("nim_mahasiswa"))));
                mahasiswaHandlerList.setJenisKelamin((cursor.getString(cursor.getColumnIndexOrThrow("jeniskelamin_mahasiswa"))));
                mahasiswaHandlerList.setProgramStudi((cursor.getString(cursor.getColumnIndexOrThrow("prodi_mahasiswa"))));
                mahasiswaHandlerList.setHobi((cursor.getString(cursor.getColumnIndexOrThrow("hobi_mahasiswa"))));
                mahasiswaHandlerList.setSemester((cursor.getString(cursor.getColumnIndexOrThrow("semester_mahasiswa"))));
                mahasiswaHandler.add(mahasiswaHandlerList);
                cursor.moveToNext();
            }
            db.close();
        }
        else {
            no_data.setVisibility(View.VISIBLE);
            empty_imageview.setVisibility(View.VISIBLE);
        }

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter(mahasiswaHandler);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    protected void onStart(){
        Toast.makeText(getApplicationContext(), "Selamat Datang", Toast.LENGTH_SHORT).show();
        super.onStart();
        Log.i("State", "on Start Activity");
    }

    protected void onResume(){
        Toast.makeText(getApplicationContext(), "Selamat Datang Kembali", Toast.LENGTH_SHORT).show();
        super.onResume();
        Log.i("State", "on Resume Activity");
    }

    protected void onStop(){
        super.onStop();
        Log.i("State", "on Stop Activity");
    }

    protected void onDestroy(){
        Toast.makeText(getApplicationContext(), "Selamat Tinggal", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        Log.i("State", "on Destroy Activity");
    }

    protected void onPause(){
        super.onPause();
        Log.i("State", "on Pause Activity");
    }
}
