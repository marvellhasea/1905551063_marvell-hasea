package id.ifent.tugas_modul_individu;

public class RecyclerItem {
    private String Nama;
    private String Nim;
    private String JenisKelamin;
    private String ProgramStudi;
    private String Hobi;
    private String Semester;

    public RecyclerItem(String nama, String nim, String jenisKelamin, String programStudi, String hobi, String semester) {
        Nama = nama;
        Nim = nim;
        JenisKelamin = jenisKelamin;
        ProgramStudi = programStudi;
        Hobi = hobi;
        Semester = semester;
    }

    public String getNama() {
        return Nama;
    }

    public String getNim() {
        return Nim;
    }

    public String getJenisKelamin() {
        return JenisKelamin;
    }

    public String getProgramStudi() {
        return ProgramStudi;
    }

    public String getHobi() {
        return Hobi;
    }

    public String getSemester() {
        return Semester;
    }
}