package com.derekgillett.clashercalendar;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        MySQLiteHelper db = new MySQLiteHelper(this);
        db.createDB();
//        Element element = db.getElement(1);

    }

}
