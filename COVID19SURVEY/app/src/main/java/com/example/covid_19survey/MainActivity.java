package com.example.covid_19survey;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RadioButton;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText surName;
    EditText city;
    EditText sideEffect,sym;
    Button register;
    RadioButton male, female, x, bio, sin, none;
    Button submit;
    private Button selectDate;
    private DatePickerDialog datePickerDialog;
    private TextView dateTxt;
    private Calendar calendar;
    private int year, month, dayOfMonth;
    boolean datePicked = false;
    boolean dateValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.editTextUsername);
        surName = (EditText)findViewById(R.id.editTextSurname);
        city = (EditText)findViewById(R.id.editTextCity);
        male = (RadioButton) findViewById(R.id.radioButtonMale);
        female = (RadioButton) findViewById(R.id.radioButtonFemale);
        x = (RadioButton) findViewById(R.id.radioButtonX);
        bio = (RadioButton) findViewById(R.id.radioButtonBio);
        sin = (RadioButton) findViewById(R.id.radioButtonSinovac);
        none = (RadioButton) findViewById(R.id.radioButtonNone);
        sideEffect = (EditText)findViewById(R.id.editTextSideEffect);
        sym = (EditText)findViewById(R.id.editTextSymptoms);
        submit = (Button)findViewById(R.id.buttonRegister);
        selectDate = findViewById(R.id.selectDate);
        dateTxt = findViewById(R.id.dateTxt);
        View.OnClickListener showSideEffects = new View.OnClickListener() {
            public void onClick(View v) {
                sideEffect.setVisibility(View.VISIBLE);
            }

        };
        View.OnClickListener hideSideEffects = new View.OnClickListener() {
             public void onClick(View v) {
                sideEffect.setVisibility(View.GONE);
            }
        };
        bio.setOnClickListener(showSideEffects);
        sin.setOnClickListener(showSideEffects);
        none.setOnClickListener(hideSideEffects);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dateTxt.setText("Birthday:"+ day + "/" + (month+1) + "/" + year);
                                datePicked = true;
                                if ( (year < 2022) && (year > 1900) ) {
                                    dateValid = true;
                                }
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();

            }
        });
    }

    public boolean containsIllegals(EditText text) {
        CharSequence str = text.getText().toString();
        Pattern pattern = Pattern.compile("[~#@*+0123456789%{}<>\\[\\]|\"\\_^]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /*
    public boolean containsIllegals(EditText toExamine) {
        CharSequence str = toExamine.getText().toString();
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if(c == '~')
                return true;
            else if(c == '#')
                return true;

            // etc...
        }
    }
    */

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(name)) {
            Toast t = Toast.makeText(this, "You must enter first name to submit form!", Toast.LENGTH_SHORT);
            t.show();
        }
        else if (containsIllegals(name)) {
            Toast t = Toast.makeText(this, "You must enter a valid first name!", Toast.LENGTH_SHORT);
            t.show();
        }
        else if (isEmpty(surName)) {
            Toast t2 = Toast.makeText(this, "You must enter last name to submit form!", Toast.LENGTH_SHORT);
            t2.show();
        }
        else if (containsIllegals(surName)) {
            Toast t = Toast.makeText(this, "You must enter a valid last name!", Toast.LENGTH_SHORT);
            t.show();
        }
        else if(!datePicked){
            Toast t2 = Toast.makeText(this, "Please select Birthday", Toast.LENGTH_SHORT);
            t2.show();
        }
        else if(!dateValid){
            Toast t2 = Toast.makeText(this, "Invalid date of birth, please enter another date", Toast.LENGTH_SHORT);
            t2.show();
        }
        else if (isEmpty(city)) {
            Toast t2 = Toast.makeText(this, "You must enter city to submit form!", Toast.LENGTH_SHORT);
            t2.show();
        }
        else if (containsIllegals(city)) {
            Toast t = Toast.makeText(this, "You must enter a valid city name!", Toast.LENGTH_SHORT);
            t.show();
        }
        else if (!male.isChecked() && !female.isChecked() && !x.isChecked()) {
            Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
        }
        else if (!bio.isChecked() && !sin.isChecked() && !none.isChecked()) {
            Toast.makeText(getApplicationContext(), "Please select Vaccine Type", Toast.LENGTH_SHORT).show();
        }
        else if(!isEmpty(name) && !isEmpty(surName) && !isEmpty(city)&&(male.isChecked() || female.isChecked() || x.isChecked()) && (bio.isChecked() || sin.isChecked() || none.isChecked()) && (datePicked) ){
            Toast.makeText(getApplicationContext(), "Submit Done", Toast.LENGTH_SHORT).show();
        }
    }
}