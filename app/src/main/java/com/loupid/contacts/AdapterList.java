package com.loupid.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter<AdapterList.MyViewHolder> {
     List<Contact> contactList;

    public AdapterList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contact, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.fullName.setText(contactList.get(position).getFullName());
        holder.email.setText(contactList.get(position).getEmail());
        holder.cellPhone.setText(contactList.get(position).getCellPhone());
        holder.workPhone.setText(contactList.get(position).getWorkPhone());
        holder.isCellDefault.setChecked(contactList.get(position).isCellPhoneDefault);
        holder.isWorkDefault.setChecked(contactList.get(position).isWorkPhoneDefault);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView fullName, email, cellPhone, workPhone;

        RadioButton isCellDefault, isWorkDefault;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.fullname);
            email = itemView.findViewById(R.id.email);
            cellPhone = itemView.findViewById(R.id.cellphone);
            workPhone = itemView.findViewById(R.id.workphone);
            isCellDefault = itemView.findViewById(R.id.cellDefault);
            isWorkDefault = itemView.findViewById(R.id.workDefault);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
        }
    }
}
