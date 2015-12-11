package com.casallas.diego.mycalculator;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[] buttonsNumber;
    private Button[] buttons;
    private TextView textViewBig;
    private TextView textViewLittle;
    private String sDataCalculater;
    private String sOperation;
    private int iSelection;
    private boolean bValidate;
    private String sPreviousData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        loadView();
    }

    public void loadView() {
        bValidate = true;
        buttonsNumber = new Button[8];
        buttons = new Button[12];
        buttons[0] = (Button) findViewById(R.id.Btn0);
        buttons[1] = (Button) findViewById(R.id.Btn1);
        buttons[2] = (Button) findViewById(R.id.Btn2);
        buttons[3] = (Button) findViewById(R.id.Btn3);
        buttons[4] = (Button) findViewById(R.id.Btn4);
        buttons[5] = (Button) findViewById(R.id.Btn5);
        buttons[6] = (Button) findViewById(R.id.Btn6);
        buttons[7] = (Button) findViewById(R.id.Btn7);
        buttons[8] = (Button) findViewById(R.id.Btn8);
        buttons[9] = (Button) findViewById(R.id.Btn9);
        buttons[10] = (Button) findViewById(R.id.Btn00);
        buttons[11] = (Button) findViewById(R.id.BtnP);

        buttonsNumber[0] = (Button) findViewById(R.id.BtnCleanTotal);
        buttonsNumber[1] = (Button) findViewById(R.id.BtnClean);
        buttonsNumber[2] = (Button) findViewById(R.id.BtnPrevious);
        buttonsNumber[3] = (Button) findViewById(R.id.BtnDivided);
        buttonsNumber[4] = (Button) findViewById(R.id.BtnMultiply);
        buttonsNumber[5] = (Button) findViewById(R.id.BtnSubtraction);
        buttonsNumber[6] = (Button) findViewById(R.id.BtnSum);
        buttonsNumber[7] = (Button) findViewById(R.id.BtnI);


        for (int i = 0; i < buttons.length; i++) {

            buttons[i].setOnClickListener(this);
        }
        for (int i = 0; i < buttonsNumber.length; i++) {

            buttonsNumber[i].setOnClickListener(this);
        }
        textViewBig = (TextView) findViewById(R.id.textViewBig);
        textViewLittle = (TextView) findViewById(R.id.textViewLittle);

        sDataCalculater = "";
        sPreviousData="";
        iSelection=0;

    }

    public void loadText(String number) {
        if (bValidate) {
            if (sDataCalculater.length() < 11) {
                sDataCalculater += number;
                textViewBig.setText(sDataCalculater);
            }
        } else {
            String viewDataNumber="";




                if(sPreviousData.equals("")){

                    viewDataNumber=number+" "+sOperation;
                }else{

                    viewDataNumber=sPreviousData+" "+sOperation+" "+number;
                }



            textViewLittle.setText(viewDataNumber);
            bValidate=false;
            iSelection++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        boolean bValidateSelection = true;


        switch (v.getId()) {

            case R.id.BtnCleanTotal:
                cleanCalculator();
                bValidateSelection = false;
                break;
            case R.id.BtnClean:
                bValidateSelection = false;
                break;
            case R.id.BtnPrevious:
                bValidateSelection = false;
                break;
            case R.id.BtnDivided:
                sOperation = "÷";
                break;
            case R.id.BtnMultiply:
                sOperation = "x";
                break;
            case R.id.BtnSubtraction:
                sOperation = "-";
                break;
            case R.id.BtnSum:
                sOperation = "+";
                break;
            case R.id.BtnI:
                bValidateSelection = false;
                calculater();
                break;

            default:
                if(iSelection>=1){

                    sDataCalculater="";
                    bValidate=true;
                    iSelection=0;
                }
                boolean bValidateCicle = true;
                int i = 0;
                while (bValidateCicle) {

                    if (buttons[i].getId() == v.getId()) {
                        loadText(buttons[i].getText().toString());

                        bValidateCicle = false;
                    }
                    i++;
                }
                bValidateSelection = false;
                break;
        }
        if (bValidateSelection) {
            bValidate=false;


                loadText(sDataCalculater);
            if(iSelection==1){
                sPreviousData=sDataCalculater;
            }

        }


    }
    private void calculater(){

        String []strings=new String[2];
        strings[0]=this.textViewLittle.getText().toString().substring(0,this.textViewLittle.getText().toString().indexOf(sOperation));
        strings[1]=this.textViewLittle.getText().toString().substring(this.textViewLittle.getText().toString().indexOf(sOperation)+1,this.textViewLittle.getText().toString().length());
        Toast.makeText(this,"Dato 1:"+strings[0]+"Dato 2:"+strings[1],Toast.LENGTH_LONG).show();

    }

    private void cleanCalculator() {

        textViewBig.setText("0");
        textViewLittle.setText("0");
        sDataCalculater="";
        sPreviousData="";
        bValidate=true;
    }

    private boolean isNumeric(String data) {
        try {


            return true;

        } catch (NumberFormatException nfe) {


            return false;
        }

    }
}
