package com.example.floriangoeteyn.androidproject3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.floriangoeteyn.androidproject3.Fragments.DatePickerFragment;
import com.example.floriangoeteyn.androidproject3.domein.DomeinController;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class GebruikersInfoActivity extends AppCompatActivity {

    @Bind(R.id.titel) TextView titel;
    @Bind(R.id.voornaam) EditText voornaam;
    @Bind(R.id.geboortedatum) EditText geboortedatum;
    @Bind(R.id.leefsituatie) Spinner leefsituatie;
    @Bind(R.id.gezinsleden) Spinner gezinsleden;
    @Bind(R.id.ervaring) Spinner ervaring;

    private DomeinController dc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gebruikers_info);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        dc = DomeinController.getInstance();

        if(voornaam.getText().toString().isEmpty()) {
            titel.setText(R.string.info_titel_eerste_keer);
        }

        //geboortedatum dag van vandaag invullen
        geboortedatum.setText("1/1/2015");

        //leefsituatie default: getrouwd (?)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.leefsituaties, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leefsituatie.setAdapter(adapter);

        //range van gezinsleden: 1-8 (?)
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.gezinsleden, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gezinsleden.setAdapter(adapter2);

        //ervaring default: starter (?)
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.ervaringen, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ervaring.setAdapter(adapter3);


    }

    //TODO: day/month/year ipv month/day/year
    //TODO: "Cancel/Set" values
    @OnClick(R.id.geboortedatum)
    public void setGeboortedatum() {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datePicker");

    }

    @OnClick(R.id.btnSlaOp)
    public void slaOp() {
        Intent intent = new Intent(GebruikersInfoActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /*
    @OnClick(R.id.leefsituatie)
    public void setLeefsituatie() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.leefsituatie)
                .setItems(R.array.leefsituaties, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        leefsituatie.setText(
                                getResources().getStringArray(R.array.leefsituaties)[which]);
                    }
                });
        builder.create().show();
    }

    @OnClick(R.id.gezinsleden)
    public void setGezinsleden() {


    }

    @OnClick(R.id.ervaring)
    public void setErvaring() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.ervaring)
                .setItems(R.array.ervaringen, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ervaring.setText(
                                getResources().getStringArray(R.array.ervaringen)[which]);
                    }
                });
        builder.create().show();
    }
    */

}
