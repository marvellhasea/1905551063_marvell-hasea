package id.marvellhasea.tugas_modul_individu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        buttonback = findViewById(R.id.buttonback);

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FormActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerItems.add(new RecyclerItem(nama_ahasiswa, nim_mahasiswa, jeniskelamin_mahasiswa, prodi_mahasiswa, hobi_mahasiswa, semester_mahasiswa));

        recyclerItems.add(new RecyclerItem("adam", "1905551086", "laki-laki","Teknologi Informasi", "Menyanyi","8"));
//        recyclerItems.add(new RecyclerItem("Tio Alexander", "Teknik Arsitektur", "tio@gmail.com",
//                "1905551011"));
//        recyclerItems.add(new RecyclerItem("Ifentius Ciputra", "Teknik Elektro", "ifent" +
//                "@gmail.com",
//                "1905551033"));
//        recyclerItems.add(new RecyclerItem("Ananta Putra", "Teknologi Informasi", "ananta" +
//                ".ptr@gmail" +
//                ".com",
//                "1905551017"));
//        recyclerItems.add(new RecyclerItem("Putri", "Teknik Arsitektur", "putri@gmail.com",
//                "1905551019"));
//        recyclerItems.add(new RecyclerItem("Agus", "Teknik Mesin", "agus@gmail.com",
//                "1905551020"));

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter(recyclerItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}