package com.example.patientmgt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.mail.setText(user.getMail());
        holder.name.setText(user.getName());
        holder.identification.setText(user.getIdentification());
        holder.phone.setText(user.getPhone());
        holder.maritalStatus.setText(user.getMaritalStatus());
        holder.patientAge.setText(user.getPatientAge());
        holder.kinNumber.setText(user.getKinNumber());
        holder.kidsNumber.setText(user.getKidsNumber());
        holder.insurance.setText(user.getInsurance());
        holder.patientAllergies.setText(user.getPatientAllergies());
        holder.patientMedication.setText(user.getPatientMedication());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mail, name, identification, insurance, phone, maritalStatus, patientAge, kinNumber, kidsNumber, patientAllergies, patientMedication;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mail = itemView.findViewById(R.id.viewmail);
            name = itemView.findViewById(R.id.viewname);
            identification = itemView.findViewById(R.id.viewid);
            insurance = itemView.findViewById(R.id.viewinsuarance);
            phone = itemView.findViewById(R.id.viewnumber);
            maritalStatus = itemView.findViewById(R.id.viewmarital);
            patientAge = itemView.findViewById(R.id.viewage);
            kinNumber = itemView.findViewById(R.id.viewkinnumber);
            kidsNumber = itemView.findViewById(R.id.viewkids);
            patientAllergies = itemView.findViewById(R.id.viewallergies);
            patientMedication = itemView.findViewById(R.id.viewmedication);
        }
    }


}
