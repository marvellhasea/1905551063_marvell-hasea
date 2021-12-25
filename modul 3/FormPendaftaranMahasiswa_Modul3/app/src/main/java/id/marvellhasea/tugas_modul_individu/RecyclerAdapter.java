package id.marvellhasea.tugas_modul_individu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private List<MahasiswaHandler> mahasiswaHandlerList;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView textNama;
        public TextView textNIM;
        public TextView textJenisKelamin;
        public TextView textProgramStudi;
        public TextView textHobi;
        public TextView textSemester;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            textNama = itemView.findViewById(R.id.Nama);
            textNIM = itemView.findViewById(R.id.NIM);
            textJenisKelamin = itemView.findViewById(R.id.textJenisKelamin);
            textProgramStudi = itemView.findViewById(R.id.programStudi);
            textHobi = itemView.findViewById(R.id.hobi);
            textSemester = itemView.findViewById(R.id.semester);
        }
    }

    public RecyclerAdapter(List<MahasiswaHandler> mahasiswaHandlerList){
        this.mahasiswaHandlerList = mahasiswaHandlerList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(v);
        return  recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        MahasiswaHandler mahasiswaHandler = mahasiswaHandlerList.get(position);
        holder.textNama.setText(mahasiswaHandler.getNama());
        holder.textNIM.setText(mahasiswaHandler.getNim());
        holder.textJenisKelamin.setText(mahasiswaHandler.getJenisKelamin());
        holder.textProgramStudi.setText(mahasiswaHandler.getProgramStudi());
        holder.textHobi.setText(mahasiswaHandler.getHobi());
        holder.textSemester.setText(mahasiswaHandler.getSemester());

    }

    @Override
    public int getItemCount() {
        return mahasiswaHandlerList.size();
    }
}

