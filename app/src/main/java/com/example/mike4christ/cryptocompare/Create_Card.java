package com.example.mike4christ.cryptocompare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mike4christ.cryptocompare.BitcoinCard.Bitcoin_Card;
import com.example.mike4christ.cryptocompare.EthereumCard.Ethereum_card;


//Hi ! Coded By Alu Michael Damilola(alumichael9@gmail.com)
//This is the Class for creating Card for both BTC or ETH

public class Create_Card extends AppCompatActivity {
    private Spinner spinner_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__card);




        spinner_card = (Spinner) findViewById(R.id.spinner_card);
        //Calling this Method Below will Setup the Adapter for spinner
        // item Bitcoin and Ethereum(BTC_ETH) and perfrom Some Logics on it
        cardSpinner();
    }
    private void cardSpinner() {
        //Creating Adapter for the Main Card Array i.e Bitcoin and Ethereum
        ArrayAdapter cardSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_main, android.R.layout.simple_spinner_item);

        // Specifying dropdown layout style - simple list view with 1 item per line
        cardSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Applying the Adapter to Spinner
        spinner_card.setAdapter(cardSpinnerAdapter);

        spinner_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    try {
                        if (selection.equals(getString(R.string.btc))) {
                            Intent intent = new Intent(Create_Card.this, Bitcoin_Card.class);
                            startActivity(intent);
                        } else if (selection.equals(getString(R.string.eth))) {
                            Intent intent = new Intent(Create_Card.this, Ethereum_card.class);
                            startActivity(intent);
                        }
                    }catch (Exception e) {
                        Log.e("No Internet Connection", e.getMessage(), e);
                        Toast.makeText(Create_Card.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parent.getItemAtPosition(0);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_about:
                Intent intent=new Intent(Create_Card.this,About.class);
                startActivity(intent);
                break;
            case R.id.action_help:
                // about();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
