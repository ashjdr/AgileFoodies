package marshmallow.cis2003.tees.ac.uk.agilefoodies;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

import static java.util.Locale.UK;

/**
 * Created by t7039352 on 02/04/19.
 *
 */

public class UnitConverterFragment extends Fragment {

    /* cup-ml   =  1-284.131
     * pint-ml  =  1-568.261
     * tbsp-ml  =  1-17.7582
     * tsp-ml   =  1-5.91939
     * cup-tsp  =  1-48
     * cup-tbsp =  1-16
     * tbsp-tsp =  1-3
     */

    private double cupToml = 284.131;
    private double pintToml = 568.261;
    private double tbspToml = 17.7582;
    private double tspToml = 5.91939;

    private boolean flag;

    private String toUnit;
    private String fromUnit;

    private double toValue;
    private double fromValue;

    private Spinner fromSpin;
    private Spinner toSpin;

    private TextView fromTxt;


    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        flag = true;
        v = inflater.inflate(R.layout.fragment_unit_converter, container, false);

        fromSpin = v.findViewById(R.id.spinnerFrom);
        toSpin = v.findViewById(R.id.spinnerTo);

        fromTxt = v.findViewById(R.id.fromTxt);

        fromSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                fromUnit = fromSpin.getSelectedItem().toString();
                callConvert(getFromUnit(), getToUnit(), getFromValue(), "from");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        toSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                toUnit = toSpin.getSelectedItem().toString();
                callConvert(getFromUnit(), getToUnit(), getFromValue(), "from");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        /*final TextView fromTxt = v.findViewById(R.id.fromTxt);

        Button sadButton = v.findViewById(R.id.sadbutton);

        sadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                fromValue = Double.parseDouble(fromTxt.getText().toString());
                callConvert(fromUnit, toUnit, fromValue, "from");
            }
        });*/


        /*final TextView toTxt = v.findViewById(R.id.toTxt);
        toTxt.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s)
            {
                if (flag)
                {
                    toValue = Double.parseDouble(toTxt.getText().toString());
                    callConvert(fromUnit, toUnit, toValue, "to");
                    flag = false;
                }
                else
                    flag = true;
            }
        });*/



        fromTxt.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s)
            {
                if (fromTxt.getText().toString().equals("") )
                {
                    fromValue = Double.parseDouble("0");
                    callConvert(fromUnit, toUnit, fromValue, "from");
                }
                else
                    fromValue = Double.parseDouble(fromTxt.getText().toString());
                callConvert(fromUnit, toUnit, fromValue, "from");
            }
        });

        return v;
    }

    private String getFromUnit()
    {
        return fromSpin.getSelectedItem().toString();
    }

    private String getToUnit()
    {
        return toSpin.getSelectedItem().toString();
    }

    private Double getFromValue()
    {
        return Double.parseDouble(fromTxt.getText().toString());
    }


    @SuppressLint("SetTextI18n")
    private void callConvert(String from, String to, Double value, String tofrm)
    {
        if (tofrm.equals("to"))
        {
            EditText fromTxt = v.findViewById(R.id.fromTxt);
            fromTxt.setText(String.format(UK,"%.2f", convert(to, from, value)));
        }
        else if (tofrm.equals("from"))
        {
            EditText toTxt = v.findViewById(R.id.toTxt);
            toTxt.setText(String.format(UK,"%.2f", convert(from, to, value)));
        }
}


    private Double convert(String from, String to, Double value)
    {
        double ml = 0;
        
        switch (from) 
        {
            case "Cups":
                ml = value*cupToml;
                break;
                
            case "Table Spoons":
                ml = value * tbspToml;
                break;
                
            case "Tea Spoons":
                ml = value*tspToml;
                break;
                
            case "Pints":
                ml = value*pintToml;
                break;

            case "Milliliters":
                ml = value;
                break;

            default:
                System.out.println("error in the 'From' section of the UnitConverterFragment.Java");      //temp error code
                System.out.println(from + " " + to);
                break;
        }

        switch (to)
        {
            case "Cups":
                return (ml / cupToml);

            case "Milliliters":
                return ml;

            case "Pints":
                return (ml/pintToml);

            case "Tea Spoons":
                return (ml / tspToml);

            case "Table Spoons":
                return (ml / tbspToml);

            default:
                System.out.println("error in the 'To' section of the UnitConverterFragment.Java");      //temp error code
                return 0.0;
        }

    }
}




























