package com.example.neycasilla.calcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CalcActivity extends AppCompatActivity {
    //variables privadas
    private TextView editText;
    private StringBuilder currenText;
    private static List<String>  numbers, symbols, hisTory;
    private String lasItmVal;
    private int request_Code;
    private Intent intent;


    //onActivityResult le permite a la aplicacion tomar el resultado enviado de otra actividad cuando esta termina
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                String hisData = data.getExtras().getString("selected");
                editText.setText(hisData);
                currenText.append(hisData);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        //cambio de titulo

        //gridView y editText
        GridView gridView = (GridView) findViewById(R.id.pad);
        editText = (TextView) findViewById(R.id.edit);

        //listas del historialm numeros y symbolos
        List<String> btns = new ArrayList<>();
        hisTory = new ArrayList<>();
        numbers = new ArrayList<>();
        symbols = new ArrayList<>();
        //texto presente en pantalla y variable de request
        currenText = new StringBuilder();
        request_Code = 5;

        //añadiendo simbolos para que aparesncan en pantalla
        btns.add("C");
        btns.add("/");
        btns.add("x");
        btns.add("Del");
        //añadiendo simbolos para referencias
        symbols.add("C");
        symbols.add("/");
        symbols.add("x");
        symbols.add("Del");

        for (int i = 9; i >= 0; i--) {
            btns.add(Integer.toString(i));
            numbers.add(Integer.toString(i));
            switch (i) {
                case 0:
                    btns.add("History");
                    break;
                case 1:
                    btns.add("SPC");
                    symbols.add("SPC");
                    break;
                case 4:
                    btns.add("+");
                    symbols.add("+");
                    break;
                case 7:
                    btns.add("-");
                    symbols.add("-");
                    break;
            }
        }

        gridView.setAdapter(new ArrayAdapter<>(this, R.layout.btns_layout, btns));

        //OnclickItems
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                intent = new Intent(CalcActivity.this, history_Activity.class);
                switch (selectedItem) {
                    case "C":
                        clearScreem();
                        break;
                    case "Del":
                        backSpace();
                        break;
                    case "History":
                        intent.putExtra("dataList", (ArrayList<String>) hisTory);
                        startActivityForResult(intent, request_Code);
                        break;
                    default:
                        updateScreem(selectedItem);
                        break;
                }

            }
        });

    }

    /**
     * se encarga de mostar en pantalla los input del usuario
     *
     * @param item el boton seleccinado por el usuario
     */
    private void updateScreem(String item) {
        operations ops = new operations();
        if (currenText.length() == 0) {
            if (numbers.contains(item)) {
                currenText.insert(currenText.length(), item);
                lasItmVal = item;
            }
        } else if (numbers.contains(item)) {
            currenText.insert(currenText.length(), item);
            lasItmVal = item;
        } else if (!item.equals(lasItmVal)) {
            if (!symbols.contains(lasItmVal)) {
                if (item.equals("SPC")) {
                    currenText.insert(currenText.length(), " ");
                    lasItmVal = item;
                } else {
                    currenText = ops.operate(currenText, item);
                    hisTory.add(currenText.toString());
                    if (hisTory.size() > 5) {
                        hisTory.remove(0);
                    }
                }

            }

        }

        editText.setText(currenText);
    }

    /**
     * se encarga de limpuar la pantalla completa
     */
    private void clearScreem() {
        editText.clearComposingText();
        currenText.setLength(0);
        editText.setText(currenText);
    }

    /**
     * se encarga de hacer la funcion de BackSpace
     */
    private void backSpace() {
        if (!(currenText.length() == 0)) {
            currenText.setLength(currenText.length() - 1);
            lasItmVal = " ";
        }
        editText.setText(currenText);
    }

}
