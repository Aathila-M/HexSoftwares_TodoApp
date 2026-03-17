package com.hexsoftwares.todoapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    ListView listView;

    ArrayList<String> taskList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 🔹 Keep this (for padding)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 🔹 Connect UI
        editText = findViewById(R.id.editTextTask);
        button = findViewById(R.id.buttonAdd);
        listView = findViewById(R.id.listViewTasks);

        // 🔹 Setup list
        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(adapter);

        // 🔹 Button click
        button.setOnClickListener(v -> {
            String task = editText.getText().toString().trim();

            if (!task.isEmpty()) {
                taskList.add(task);
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
    }
}