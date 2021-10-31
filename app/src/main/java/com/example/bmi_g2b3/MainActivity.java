package com.example.bmi_g2b3;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton rb;
    EditText et1,et2;
    Double a,b,r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void bmical(View view){
        et1=findViewById(R.id.weight);
        et2=findViewById(R.id.height);
        a=Double.parseDouble(et1.getText().toString());
        b=Double.parseDouble(et2.getText().toString());
        int in=7,ft=0;
        try{
            radioGroup=findViewById(R.id.radioGroup);
            rb=findViewById(radioGroup.getCheckedRadioButtonId());
            String cal="";
            cal=rb.getText().toString();
            if (cal.equals("cm")){
                b=b*0.01;
            }
            else if(cal.equals("feet")){
                String h=String.valueOf(et2.getText());
                h=h.substring(h.indexOf(".")+1);
                in=Integer.parseInt(h);
                h=String.valueOf(et2.getText());
                h=h.substring(0,h.indexOf('.'));
                ft=Integer.parseInt(h);
                b=((ft*12)+in)*2.54*0.01;
            }
            else if(cal.equals("m")){
                b=b;
            }
            if(!(in>=0||in<=12)){
                Toast.makeText(getApplicationContext(), "Lenghth should be in 1 to 12", Toast.LENGTH_SHORT).show();
            }
            else{
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("BMI RESULT");
                alert.setCancelable(false);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                r=(a/(b*b));
                if(r<18.5){
                    alert.setMessage("BMI"+r+"Under Weight");
                }
                else  if(r>=18.5 && r<=24.9){
                    alert.setMessage("BMI"+r+"Normal Weight");
                }
                else  if(r>=25 && r<=29.9){
                    alert.setMessage("BMI"+r+"Over Weight");
                }
                else if(r>30){
                    alert.setMessage("BMI"+r+"Obesity");
                }
                alert.show();
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Please Select the units", Toast.LENGTH_SHORT).show();
        }
    }
}