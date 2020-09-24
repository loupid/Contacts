package com.loupid.contacts;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.service.autofill.RegexValidator;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.regex.Pattern;

public class CreateEdit extends AppCompatActivity {

    TextView firstname, lastname, email, cellPhone, workPhone;

    RadioButton isCellDefault, isWorkDefault;

    TextView isdefault;

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
        isdefault = findViewById(R.id.isdefault);

        confirm = findViewById(R.id.confirm);
        cancel = findViewById(R.id.cancel);

        final Contact contact;

        if (getIntent().hasExtra("contact")){
            position = getIntent().getIntExtra("position",-1);
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

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final int finalPosition = position;

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()) {
                    Intent intent = new Intent();
                    Contact finalContact = new Contact(firstname.getText().toString(),
                            lastname.getText().toString(),
                            cellPhone.getText().toString(),
                            workPhone.getText().toString(),
                            email.getText().toString(),
                            isCellDefault.isChecked(),
                            isWorkDefault.isChecked());
                    intent.putExtra("newContact", finalContact);
                    if (finalPosition > -1) {
                        intent.putExtra("position", finalPosition);
                    }
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }

            private boolean isValidate() {
                boolean approve = true;

                Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+" +
                        "(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                        "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" +
                        "|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:" +
                        "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

                Pattern phonePattern = Pattern.compile("^[0-9]{0,1}\\([0-9]{3}\\)\\ {1}[0-9]{3}\\-[0-9]{4}$");
                if (firstname.getText().toString().trim().isEmpty()){
                    firstname.setError("Le champ prénom ne peut pas être vide");
                    approve = false;
                    //return false;
                }else{
                    firstname.setError(null);
                }

                if (lastname.getText().toString().trim().isEmpty()){
                    lastname.setError("Le champs nom ne peut pas être vide");
                    approve = false;
                    //return false;
                }else{
                    lastname.setError(null);
                }

                if (email.getText().toString().trim().isEmpty()){
                    email.setError("Le champs email ne peut pas être vide");
                    approve = false;
                    //return false;
                }else if (!emailPattern.matcher(email.getText().toString().trim()).matches()){
                    email.setError("Entrer une adresse email valide");
                    approve = false;
                    //return false;
                }
                else{
                    email.setError(null);
                }

                if (cellPhone.getText().toString().trim().isEmpty()){
                    cellPhone.setError("Le champs du téléphone ne peut pas être vide");
                    approve = false;
                    //return false;
                }else if (!phonePattern.matcher(cellPhone.getText().toString().trim()).matches()){
                    cellPhone.setError("Entrez un numéros de téléphone valide");
                    approve = false;
                    //return false;
                }
                else{
                    cellPhone.setError(null);
                }

                if (workPhone.getText().toString().trim().isEmpty()){
                    workPhone.setError("Le champs du téléphone ne peut pas être vide");
                    approve = false;
                    //return false;
                }else if (!phonePattern.matcher(workPhone.getText().toString().trim()).matches()){
                    workPhone.setError("Entrez un numéros de téléphone valide");
                    approve = false;
                    //return false;
                }
                else{
                    workPhone.setError(null);
                }

                if (!isCellDefault.isChecked() && !isWorkDefault.isChecked()) {
                    isdefault.setError("Veillez choisir le téléphone par défaut");
                } else {
                    isdefault.setError(null);
                }

                return approve;
            }
        });
    }
}