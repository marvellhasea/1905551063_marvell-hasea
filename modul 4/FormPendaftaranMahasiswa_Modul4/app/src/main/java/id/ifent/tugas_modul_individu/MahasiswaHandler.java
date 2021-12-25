package id.ifent.tugas_modul_individu;

public class MahasiswaHandler {

    private Integer id;
    private String Nama;
    private String Nim;
    private String JenisKelamin;
    private String ProgramStudi;
    private String Hobi;
    private String Semester;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getNim() {
        return Nim;
    }

    public void setNim(String nim) { Nim = nim; }

    public String getJenisKelamin() {
        return JenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        JenisKelamin = jenisKelamin;
    }

    public String getProgramStudi() {
        return ProgramStudi;
    }

    public void setProgramStudi(String programStudi) {
        ProgramStudi = programStudi;
    }

    public String getHobi() {
        return Hobi;
    }

    public void setHobi(String hobi) {
        Hobi = hobi;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }
}
