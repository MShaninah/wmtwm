package com.example.wmt_wms_s23;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
public class ManageSalespersonsActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText regionEditText;
    private ArrayAdapter<String> adapter;

    private ArrayList<String> salespersonsList;

    private DatabaseHelper dbHelper;
    private boolean isAdministrator; // Set this based on user role


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_salespersons);
        dbHelper = new DatabaseHelper(this);

        nameEditText = findViewById(R.id.editTextName);
        regionEditText = findViewById(R.id.editTextRegion);
        Button saveButton = findViewById(R.id.buttonSave);
        ListView salespersonListView = findViewById(R.id.listViewSalespersons);

        salespersonsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, salespersonsList);
        salespersonListView.setAdapter(adapter);

       // isAdministrator = true; // Set this based on user role

        if (isAdministrator) {
            loadSalespersonsList();
        } else {
            salespersonListView.setVisibility(View.GONE);
        }

        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String region = regionEditText.getText().toString();

            if (!name.isEmpty() && !region.isEmpty()) {
                long newRowId = dbHelper.insertSalesperson(name, region);
                if (newRowId != -1) {
                    // Insert successful, add the salesperson to the list
                    salespersonsList.add(name);
                    adapter.notifyDataSetChanged();
                    // Clear the input fields
                    nameEditText.setText("");
                    regionEditText.setText("");
                } else {
                    Toast.makeText(ManageSalespersonsActivity.this, "Failed to save salesperson", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ManageSalespersonsActivity.this, "Name and region are required", Toast.LENGTH_SHORT).show();
            }
        });

        if (isAdministrator) {
            salespersonListView.setOnItemClickListener((parent, view, position, id) -> {
                String selectedName = salespersonsList.get(position);
                // Implement edit and delete functionality
                // Open an edit/delete dialog or a new activity
                // You'll need to implement this part for editing and deleting salespersons
            });
        }
    }

    private void loadSalespersonsList() {
        ArrayList<Salesperson> salespersons = dbHelper.getAllSalespersons();
        salespersonsList.clear();
        for (Salesperson salesperson : salespersons) {
            salespersonsList.add(salesperson.getName());
        }
        adapter.notifyDataSetChanged();
    }
}