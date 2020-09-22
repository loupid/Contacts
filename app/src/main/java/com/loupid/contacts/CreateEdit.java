package com.loupid.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class CreateEdit extends AppCompatActivity {

    TextView firstname, lastname, email, cellPhone, workPhone;

    RadioButton isCellDefault, isWorkDefault;

    Button confirm, cancel;

    Boolean isCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit);

        firstname = findViewById(R.id.editFirstName);
        lastname = findViewById(R.id.editLastName);
        email = findViewById(R.id.editEmail);
        cellPhone = findViewById(R.id.editCellPhone);
        workPhone = findViewById(R.id.editWorkPhone);
        isCellDefault = findViewById(R.id.isCellDefault);
        isWorkDefault = findViewById(R.id.isWorkDefault);

        confirm = findViewById(R.id.confirm);
        cancel = findViewById(R.id.cancel);

        Contact contact = getIntent().getParcelableExtra("contact");

        if (contact != null) {
            firstname.setText(contact.getFirstName());
            lastname.setText(contact.getLastName());
            email.setText(contact.getEmail());
            cellPhone.setText(contact.getCellPhone());
            workPhone.setText(contact.getWorkPhone());
            isCellDefault.setChecked(contact.isCellPhoneDefault());
            isWorkDefault.setChecked(contact.isWorkPhoneDefault());
            isCreate = false;
        }


    }
}