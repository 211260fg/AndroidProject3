package com.example.floriangoeteyn.androidproject3.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.floriangoeteyn.androidproject3.R;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
                            implements DatePickerDialog.OnDateSetListener {

    private EditText geboortedatum;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        geboortedatum = (EditText) getActivity().findViewById(R.id.geboortedatum);

        String[] datum = geboortedatum.getText().toString().split("/");
        int day = Integer.parseInt(datum[0]);
        int month = Integer.parseInt(datum[1])-1;
        int year = Integer.parseInt(datum[2]);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        DatePicker picker = dialog.getDatePicker();

        final Calendar c = Calendar.getInstance();
        picker.setMaxDate(c.getTimeInMillis());
        c.add(Calendar.YEAR, -100);
        picker.setMinDate(c.getTimeInMillis());

        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        geboortedatum.setText(day + "/" + (month + 1) + "/" + year);
    }
}
