package com.example.android.udacitycard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.recycler_view);
        RecyclerView.Adapter adapter=new CustomAdapter(this,rv);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

    }
}
