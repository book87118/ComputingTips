package ching.computingtips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText edit_price;
    TextView text_tips;
    TextView text_tex;
    TextView text_answer;
    Spinner spinner_percent;
    Spinner spinner_people;

    //List spinner
    List<String> list_percent = new ArrayList<String>();
    List<String> list_people = new ArrayList<String>();

    double d_percent;
    double i_people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        list_percent.add("10%");
        list_percent.add("12%");
        list_percent.add("15%");
        list_percent.add("18%");
        list_percent.add("20%");
        list_people.add("1");
        list_people.add("2");
        list_people.add("3");
        list_people.add("4");
        list_people.add("5");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_percent);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> dataAdapter_people = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_people);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner_percent.setAdapter(dataAdapter);
        spinner_percent.setOnItemSelectedListener(this);
        spinner_people.setAdapter(dataAdapter_people);
        spinner_people.setOnItemSelectedListener(this);

    }
    private void init(){
        spinner_percent= (Spinner) findViewById(R.id.spinner_percent);
        spinner_people = (Spinner) findViewById(R.id.spinner_people);
        edit_price = (EditText)findViewById(R.id.edit_price);
        text_tips =(TextView)findViewById(R.id.text_tip);
        text_tex =(TextView)findViewById(R.id.text_tax);
        text_answer = (TextView)findViewById(R.id.text_answer);


        edit_price.addTextChangedListener(mTextWatcher);

    }

    TextWatcher  mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {



        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            countAnswer();
//10 12 15 18 20  and 幾%

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        switch (adapterView.getId()){
            case R.id.spinner_percent:
                switch (i){
                    case 0:
                        d_percent = 0.1;
                        break;
                    case 1:
                        d_percent = 0.12;
                        break;
                    case 2:
                        d_percent = 0.15;
                        break;
                    case 3:
                        d_percent = 0.18;
                        break;
                    case 4:
                        d_percent = 0.2;
                        break;
                }
                break;
            case R.id.spinner_people:
                switch (i){
                    case 0:
                        i_people = 1;
                        break;
                    case 1:
                        i_people = 2;
                        break;
                    case 2:
                        i_people = 3;
                        break;
                    case 3:
                        i_people = 4;
                        break;
                    case 4:
                        i_people = 5;
                        break;
                }
        }
        countAnswer();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void   countAnswer(){

        double price ;
        double showTax;
        double showTip;
        double showAnswer;
        try {
            price = Double.parseDouble(edit_price.getText().toString());
            showTax = price/1.065*0.065;
            text_tex.setText("Tax : "+String.valueOf(showTax));
            showTip = price/1.065 * d_percent;
            text_tips.setText("Tip : "+String.valueOf(showTip));

            showAnswer = (price+ (price/1.065*d_percent))/i_people;
            text_answer.setText("Total : "+String.valueOf(showAnswer));

        }catch (NumberFormatException e){
        }

    }
}
