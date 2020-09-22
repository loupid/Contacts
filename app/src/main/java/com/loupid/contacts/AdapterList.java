package com.loupid.contacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

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
     Context context;

    public AdapterList(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contact, parent, false);

        return new MyViewHolder(view, this);
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

    public void addContact(Contact contact){
        contactList.add(contact);
        notifyItemInserted(contactList.size()-1);
    }

    public void modifyContact(int position, Contact contact){
        contactList.set(position, contact);
        notifyItemChanged(position);
    }

    public void delete(int position){
        contactList.remove(position);
        notifyItemRemoved(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView fullName, email, cellPhone, workPhone;

        RadioButton isCellDefault, isWorkDefault;

        public MyViewHolder(@NonNull View itemView, final AdapterList adapterList) {
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
                    Contact contact = adapterList.contactList.get(getLayoutPosition());

                    Intent intent = new Intent(adapterList.context, CreateEdit.class);

                    intent.putExtra("contact", contact);
                    intent.putExtra("position", getLayoutPosition());

                    ((Activity) context).startActivityForResult(intent, 11);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Contact contact = adapterList.contactList.get(getLayoutPosition());
                    AlertDialog.Builder builder = new AlertDialog.Builder(adapterList.context);
                    builder.setTitle("Warning")
                            .setMessage("Voulez vous vraiment supprimmer le contact " + contact.getFullName())
                            .setPositiveButton("Oui je veux supprimer " + contact.getFullName(), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    adapterList.delete(getLayoutPosition());
                                }
                            })
                            .setNegativeButton("Non je ne veux pas supprimer " + contact.getFullName(), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    builder.show();

                    return false;
                }
            });

        }
    }
}
