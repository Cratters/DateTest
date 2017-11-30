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
    int timeGap;
    long newTime,oldTime;
    String tempOldTime="0";
    String[] tempHasMod = new String[6];
    Calendar rightNow = Calendar.getInstance();
    TextView mob[];

    private void restorePrefs() { //讀取的位置
        SharedPreferences settings = getSharedPreferences(DATE_PREF, 0);
        for(int i=0; i<6; i++){
            tempHasMod[i] = settings.getString(PREF_HASMOB[i], "");
        }
        tempOldTime = settings.getString(PREF_OLD_TIME, "");
        if(tempOldTime.equals("")) //玩家第一次開啟沒有舊時間// ，就將oldTime設置為now
            oldTime = rightNow.getTimeInMillis();
        else
            oldTime = Long.parseLong(tempOldTime);
        Log.d("LOGDATE_NewTime", newTime+"");
        Log.d("LOGDATE_OldTime", oldTime+"");
    }

    protected void onSave() { //儲存的位置，目前在onCreate最底下呼叫
        SharedPreferences settings = getSharedPreferences(DATE_PREF, 0);
        settings.edit().putString(PREF_OLD_TIME, String.valueOf(rightNow.getTimeInMillis())).commit();
        for(int i=0; i<6; i++){
            settings.edit().putString(PREF_HASMOB[i], tempHasMod[i]).commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newTime = rightNow.getTimeInMillis();
        tempHasMod = new String[]{"0", "0", "0", "0", "0", "0"}; //不知為何new在class外部會有問題所以先寫在這裡
        restorePrefs();
        findViews();
        DateTest();
        setVisible();
        onSave();
    }

    @Override
    protected void onDestroy() {
        onSave();
        super.onDestroy();
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
            mob[i].setVisibility(View.INVISIBLE);
            tempHasMod[i] = "0";
        }
        onSave();
    }
    int random,randSet;
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
                    while (true) {
                        randSet = (int) (Math.random() * 6); //隨機 0-5
                        Log.d("LOGDATE_RandSet:", randSet + "");
                        if (tempHasMod[randSet].equals("0")) {
                            tempHasMod[randSet] = "1";
                            break;
                        }
                    }
            }
        }
    }

    void setVisible () {
        Log.d("LOGDATE",tempHasMod[0]+tempHasMod[1]+tempHasMod[2]+tempHasMod[3]+tempHasMod[4]+tempHasMod[5]);
        for(int i=0; i<tempHasMod.length; i++) {
            if(tempHasMod[i].equals("0"))
                mob[i].setVisibility(View.INVISIBLE);
            else
                mob[i].setVisibility(View.VISIBLE);
        }
    }
}
