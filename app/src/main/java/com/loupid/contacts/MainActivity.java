package com.loupid.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CreateEdit.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactList = getContactList();

        adapterList = new AdapterList(contactList);

        recyclerView.setAdapter(adapterList);
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