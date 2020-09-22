package com.loupid.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Objects;

public class CreateEdit extends AppCompatActivity {

    TextView firstname, lastname, email, cellPhone, workPhone;

    RadioButton isCellDefault, isWorkDefault;

    Button confirm, cancel;

    Boolean isCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit);

        int position = 0;

        firstname = findViewById(R.id.editFirstName);
        lastname = findViewById(R.id.editLastName);
        email = findViewById(R.id.editEmail);
        cellPhone = findViewById(R.id.editCellPhone);
        workPhone = findViewById(R.id.editWorkPhone);
        isCellDefault = findViewById(R.id.isCellDefault);
        isWorkDefault = findViewById(R.id.isWorkDefault);

        confirm = findViewById(R.id.confirm);
        cancel = findViewById(R.id.cancel);

        Contact contact;

        if (getIntent().hasExtra("contact")){
            position = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("position")));
            contact = getIntent().getParcelableExtra("contact");
            isCreate = false;

            if (contact != null) {
                firstname.setText(contact.getFirstName());
                lastname.setText(contact.getLastName());
                email.setText(contact.getEmail());
                cellPhone.setText(contact.getCellPhone());
                workPhone.setText(contact.getWorkPhone());
                isCellDefault.setChecked(contact.isCellPhoneDefault());
                isWorkDefault.setChecked(contact.isWorkPhoneDefault());
            }
        }

        contact = new Contact(firstname.getText().toString(),
                lastname.getText().toString(),
                cellPhone.getText().toString(),
                workPhone.getText().toString(),
                email.getText().toString(),
                isCellDefault.isChecked(),
                isWorkDefault.isChecked());

        final Contact finalContact = contact;
        final int finalPosition = position;

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("newContact", finalContact);
                intent.putExtra("position", finalPosition);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}