package id.marvellhasea.tugas_modul_individu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private ArrayList<RecyclerItem> recyclerItems;

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

    public RecyclerAdapter(ArrayList<RecyclerItem> recyclerItems){
        this.recyclerItems = recyclerItems;
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
        RecyclerItem currentItem = this.recyclerItems.get(position);
        holder.textNama.setText(currentItem.getNama());
        holder.textNIM.setText(currentItem.getNim());
        holder.textJenisKelamin.setText(currentItem.getJenisKelamin());
        holder.textProgramStudi.setText(currentItem.getProgramStudi());
        holder.textHobi.setText(currentItem.getHobi());
        holder.textSemester.setText(currentItem.getSemester());

    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }
}

