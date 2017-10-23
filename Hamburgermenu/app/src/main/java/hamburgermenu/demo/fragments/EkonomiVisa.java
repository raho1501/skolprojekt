package hamburgermenu.demo.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.markus.hamburgermenu.R;

import java.util.List;

/**
 * Created by Mackan on 2017-10-23.
 */

public class EkonomiVisa extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ekonomivisa, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final TextView foo;
        foo = (TextView)getView().findViewById(R.id.multi_text_econ);
        foo.setText("HEEEJ");

        RetrofitWrapper retro = new RetrofitWrapper();
        retro.getBudgetsEvent(new RetroCallback<Budgets>()
                                   {
                                       @Override
                                       public void onResponse(Budgets entity) {
                                           int size = entity.size();
                                           int sum = 0;
                                           String budgetLine = "";
                                           for (int i = 0;i<size; ++i)
                                           {
                                               Budget bud = entity.getBudget(i);
                                               String line = bud.getInfo() + "  "+
                                                       Integer.toString(bud.getAmount())
                                                       +"   "+bud.getDateTime()+ "\n";
                                               sum +=bud.getAmount();
                                               budgetLine+=line;

                                           }
                                           foo.setText(budgetLine +"\n"+"Månadens ekonomi är: "+
                                           Integer.toString(sum));


                                       }

                                   }
        );
    }
}
