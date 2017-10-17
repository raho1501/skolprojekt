package hamburgermenu.demo.fragments;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.WeekViewEvent;
import com.example.markus.hamburgermenu.R;

import retrofit.Budget;
import retrofit.Budgets;
import retrofit.RetroCallback;
import retrofit.RetrofitWrapper;
import retrofit2.Retrofit;

/**
 * Created by Markus on 2017-10-11.
 */

public class Ekonomi extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ekonomi, container, false);


        return rootView;
    }
     @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
         // Get a reference for the week view in the layout.
         final Button button = (Button) view.findViewById(R.id.Transaktion);
         button.setOnClickListener(
                 new View.OnClickListener() {
                     public void onClick(View v) {
                         FragmentManager fn = getFragmentManager();
                         fn.beginTransaction().replace(R.id.content_frame, new Transaktion()).commit();
                     }
                 }
         );
         RetrofitWrapper retro = new RetrofitWrapper();
         retro.getBudgets(new RetroCallback<Budgets>() {
                              @Override
                              public void onResponse(Budgets entity) {
                                   loopThrough(entity, view);
                              }
         }

         );
     }

     public void loopThrough(Budgets budgets, View view){
         final TextView scrollView = (TextView) view.findViewById(R.id.Scroll2);

         TextView et;
         et = new TextView(getActivity());
         for(int i=0; i<budgets.size();i++){
             scrollView.append("\n"+budgets.getBudget(i).getInfo());
             scrollView.append("\n"+budgets.getBudget(i).getAmount());

         }

     }

}