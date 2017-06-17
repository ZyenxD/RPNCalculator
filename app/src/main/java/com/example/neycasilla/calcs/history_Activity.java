package com.example.neycasilla.calcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class history_Activity extends AppCompatActivity {
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        data = getIntent().getStringArrayListExtra("dataList");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_);
        ListView history = (ListView) findViewById(R.id.opList);
        history.setAdapter(new ArrayAdapter<>(this, R.layout.hist_layout, data));


        history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                Intent intent = new Intent(history_Activity.this, CalcActivity.class);
                intent.putExtra("selected", selectedItem);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
