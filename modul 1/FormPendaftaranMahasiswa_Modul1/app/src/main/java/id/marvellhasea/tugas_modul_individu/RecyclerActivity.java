package id.marvellhasea.tugas_modul_individu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter; //Jembatan antara data arraylist dengan recyclerview
    private RecyclerView.LayoutManager layoutManager;
    private Button buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        ArrayList<RecyclerItem> recyclerItems = new ArrayList<>();

        Intent intent = getIntent();
        String nama_ahasiswa = intent.getStringExtra("nama");
        String nim_mahasiswa = intent.getStringExtra("NIM");
        String jeniskelamin_mahasiswa = intent.getStringExtra("JenikKelamin");
        String prodi_mahasiswa = intent.getStringExtra("Prodi");
        String hobi_mahasiswa = intent.getStringExtra("Hobi");
        String semester_mahasiswa = intent.getStringExtra("Semester");

        recyclerItems.add(new RecyclerItem(nama_ahasiswa, nim_mahasiswa, jeniskelamin_mahasiswa, prodi_mahasiswa, hobi_mahasiswa, semester_mahasiswa));

        recyclerItems.add(new RecyclerItem("adam", "1905551086", "laki-laki","Teknologi Informasi", "Menyanyi","8"));


        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter(recyclerItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}