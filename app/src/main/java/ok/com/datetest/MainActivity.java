package ok.com.datetest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    SharedPreferences sharedPref = context.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE);

    TextView mob1,mob2,mob3,mob4,mob5,mob6;
    Boolean[] mobs = new Boolean[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    void findViews() {
        mob1 = (TextView) findViewById(R.id.textView);
        mob2 = (TextView) findViewById(R.id.textView2);
        mob3 = (TextView) findViewById(R.id.textView3);
        mob4 = (TextView) findViewById(R.id.textView4);
        mob5 = (TextView) findViewById(R.id.textView5);
        mob6 = (TextView) findViewById(R.id.textView6);
    }

    public void onClear (View v) {
        mob1.setVisibility(View.INVISIBLE);
        mob2.setVisibility(View.INVISIBLE);
        mob3.setVisibility(View.INVISIBLE);
        mob4.setVisibility(View.INVISIBLE);
        mob5.setVisibility(View.INVISIBLE);
        mob6.setVisibility(View.INVISIBLE);
        for (int i=0;i<6;i++){
            mobs[i] = false;
        }
    }

    public class DateTest {

    }
}
