package com.loupid.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterList adapterList;
    List<Contact> contactList;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactList = getContactList();

        adapterList = new AdapterList(contactList, this);

        recyclerView.setAdapter(adapterList);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CreateEdit.class);
                startActivityForResult(intent, 22);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 11){
            if (resultCode == RESULT_OK){
                Contact contact = data.getParcelableExtra("newContact");
            }
        }
        else if (requestCode == 22){
            if (resultCode == RESULT_OK){

            }
        }
    }

    private List<Contact> getContactList(){
        List<Contact> list = new ArrayList<>();
        list.add(new Contact("william","hudon","(819) 384-3485", "(819) 555-5555","loupidmasta@gmail.com",true, false));
        list.add(new Contact("mohammed","benabbassi","(819) 384-3485", "(819) 555-5555","loupidmasta@gmail.com",false, true));
        list.add(new Contact("alexis", "l'ecuyer","(819) 384-3485", "(819) 555-5555","loupidmasta@gmail.com",true, false));
        list.add(new Contact("simon","bolduc","(819) 384-3485", "(819) 555-5555","loupidmasta@gmail.com",true, false));
        list.add(new Contact("antoine","st-arnaud","(819) 384-3485", "(819) 555-5555","loupidmasta@gmail.com",false, true));
        list.add(new Contact("ann-gabrielle","duchesne","(819) 384-3485", "(819) 555-5555","loupidmasta@gmail.com",false, true));
        return list;
    }
}