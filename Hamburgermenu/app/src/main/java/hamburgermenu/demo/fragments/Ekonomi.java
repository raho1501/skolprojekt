package hamburgermenu.demo.fragments;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.markus.hamburgermenu.R;

/**
 * Created by Markus on 2017-10-11.
 */

public class Ekonomi extends Fragment {

    private Switch moneySwitch;
    private TextView title;
    private boolean moneyFlow;
    private Button saveEkonomi;
    private EditText descBox;
    private EditText moneyBox;
    private Budget budget;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ekonomi, container, false);

        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        moneySwitch = (Switch) getView().findViewById(R.id.moneySwitch);
        title = (TextView) getView().findViewById(R.id.titleTextView);
        saveEkonomi = (Button) getView().findViewById(R.id.ekonomiPostBtn);
        descBox = (EditText) getView().findViewById(R.id.infoEditText);
        moneyBox = (EditText) getView().findViewById(R.id.moneyEditText);


        moneySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    //do stuff when Switch is ON
                    moneySwitch.setText("Inkomst");
                    moneyFlow = true;
                } else {
                    //do stuff when Switch if OFF
                    moneySwitch.setText("Utgift");
                    moneyFlow = false;
                }
            }
        });

        saveEkonomi.setOnClickListener(new View.OnClickListener() {
                                           @Override
            public void onClick(View view) {
                String description = descBox.getText().toString();
                String moneyValue = moneyBox.getText().toString();
                int money;
                if (!moneyFlow) {
                    moneyValue = "-" + moneyValue;
                    money = Integer.parseInt(moneyValue);

                } else {
                    money = Integer.parseInt(moneyValue);
                }

                Budget budget = new Budget();
                budget.setInfo(description);
                budget.setAmount(money);
                Calendar c = Calendar.getInstance();
                String s = "";
                String year = Integer.toString(c.get(Calendar.YEAR));

                String month = Integer.toString(c.get(Calendar.MONTH)+1);
                String day = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
                s = year + "-" + month + "-" + day + "T00:00:00+02:00";
                System.out.println(s);
                budget.setDateTime(s);

                RetrofitWrapper rw = new RetrofitWrapper();


                rw.postBudget(budget, new RetroCallback<Budget>() {
                      @Override
                      public void onResponse(Budget entity) {
                          Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG);
                      }

                  });

                  //Toast.makeText(getActivity(), "Händelse tillagd!", Toast.LENGTH_SHORT).show();


             }
          }
        );
    }
}




