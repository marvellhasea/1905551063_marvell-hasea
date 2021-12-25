package id.ifent.tugas_modul_individu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import static android.provider.Telephony.Mms.Part.TEXT;
import static java.text.Collator.PRIMARY;

public class DatabaseMahasiswa extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "Pendaftaran_Mahasiswa.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public DatabaseMahasiswa(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
        //context.deleteDatabase(DATABASE_NAME);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE mahasiswa(id INTEGER PRIMARY KEY AUTOINCREMENT, nama_mahasiswa TEXT, nim_mahasiswa TEXT, jeniskelamin_mahasiswa TEXT, prodi_mahasiswa TEXT, hobi_mahasiswa TEXT, semester_mahasiswa TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS mahasiwa");
    }

    public boolean addMahasiswa(MahasiswaHandler mahasiswaHandler){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nama_mahasiswa", mahasiswaHandler.getNama());
        cv.put("nim_mahasiswa", mahasiswaHandler.getNim());
        cv.put("jeniskelamin_mahasiswa", mahasiswaHandler.getJenisKelamin());
        cv.put("prodi_mahasiswa", mahasiswaHandler.getProgramStudi());
        cv.put("hobi_mahasiswa", mahasiswaHandler.getHobi());
        cv.put("semester_mahasiswa", mahasiswaHandler.getSemester());
        return db.insert("mahasiswa",null, cv) > 0;
    }
    public Cursor getMahasiswa() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from " + "mahasiswa", null);
    }

    public Cursor getDetailMahasiswa(int id_mahasiswa) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from mahasiswa where id = " + id_mahasiswa, null);
    }

    public boolean updateMahasiswa(MahasiswaHandler mahasiswaHandler, int id_mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nama_mahasiswa", mahasiswaHandler.getNama());
        cv.put("nim_mahasiswa", mahasiswaHandler.getNim());
        cv.put("jeniskelamin_mahasiswa", mahasiswaHandler.getJenisKelamin());
        cv.put("prodi_mahasiswa", mahasiswaHandler.getProgramStudi());
        cv.put("hobi_mahasiswa", mahasiswaHandler.getHobi());
        cv.put("semester_mahasiswa", mahasiswaHandler.getSemester());
        return db.update("mahasiswa", cv, "id" + "=" + id_mahasiswa, null) > 0;
    }

    public boolean deleteMahasiswa(int id_mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("mahasiswa", "id" + "=" + id_mahasiswa, null) > 0;
    }

    public DatabaseMahasiswa(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DatabaseMahasiswa(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }
}
