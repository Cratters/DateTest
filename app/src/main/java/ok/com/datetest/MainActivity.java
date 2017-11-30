package ok.com.datetest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String DATE_PREF = "DATE_PREF";
    public static final String PREF_OLD_TIME = "DATE_OldTime";
    public static final String[] PREF_HASMOB = {"HasMob1","HasMob2","HasMob3","HasMob4","HasMob5","HasMob6"};
    public static final String DATE_NEXTDATE = "DATE_NextTime";
    int timeGap,tCount;
    long newTime,oldTime;
    String tempOldTime;
    String[] tempHasMod = new String[6];
    Calendar rightNow = Calendar.getInstance();

    TextView mob[];
    //Boolean[] mobs = new Boolean[6];

    private void restorePrefs() { //讀取的位置
        SharedPreferences settings = getSharedPreferences(DATE_PREF, 0);
        tempOldTime = settings.getString(PREF_OLD_TIME, "");
        for(int i=0; i<6; i++){
            tempHasMod[i] = settings.getString(PREF_HASMOB[i], "");
            Log.d("LOGDATE_HasMod" + i, tempHasMod[i]);
        }
        oldTime =  Long.parseLong(tempOldTime);
        Log.d("LOGDATE_NewTime", newTime+"");
        Log.d("LOGDATE_OldTime", oldTime+"");
    }

    protected void onSave() { //儲存的位置
        SharedPreferences settings = getSharedPreferences(DATE_PREF, 0);
        settings.edit().putString(PREF_OLD_TIME, String.valueOf(newTime)).commit();
        for(int i=0; i<6; i++){
            settings.edit().putString(PREF_HASMOB[i], tempHasMod[i]).commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newTime = rightNow.getTimeInMillis();
        tempHasMod = new String[]{"0", "0", "0", "0", "0", "0"};
        findViews();
        restorePrefs();
        DateTest();
        for(int i=0; i<tempHasMod.length; i++) {
            Log.d("LOGDATE_HasMod" + i, tempHasMod[i]+",");}
        setVisible();
        onSave();
    }

    void findViews() {
        mob = new TextView[6];
        mob[0] = (TextView) findViewById(R.id.textView);
        mob[1] = (TextView) findViewById(R.id.textView2);
        mob[2] = (TextView) findViewById(R.id.textView3);
        mob[3] = (TextView) findViewById(R.id.textView4);
        mob[4] = (TextView) findViewById(R.id.textView5);
        mob[5] = (TextView) findViewById(R.id.textView6);
    }

    public void onClear (View v) {
        for (int i=0;i<6;i++){
            mob[i].setVisibility(View.GONE);
            tCount = 0;
            tempHasMod[i] = "0";
        }
        onSave();
    }
    int random;
    void DateTest () {
        timeGap = (int)(newTime-oldTime)/1000;
        Log.d("LOGDATE_timeGap", timeGap + "");
        for(int i=0;i<6;i++) {
            if (tempHasMod[i].equals("0")) {
                random = (int) (Math.random() * 25) + 5; //隨機 5-30
                Log.d("LOGDATE_Random"+i+":", random + "");
                timeGap -= random;
                if (timeGap < 0) {
                    break;
                } else
                    tempHasMod[i] = "1";
            }
        }
    }

    void setVisible () {
        for(int i=0; i<tempHasMod.length; i++) {
            if(tempHasMod[i].equals("0"))
                mob[i].setVisibility(View.GONE);
            else
                mob[i].setVisibility(View.VISIBLE);
        }
    }
}
