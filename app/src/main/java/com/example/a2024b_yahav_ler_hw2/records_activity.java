package com.example.a2024b_yahav_ler_hw2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

public class records_activity extends AppCompatActivity {
    private MaterialTextView main_LBL_record;
    private MaterialTextView main_LBL_map;

    private Fragment_List fragmentList;
    private Fragment_Map fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.records_view);

        main_LBL_record = findViewById(R.id.main_LBL_record);
        main_LBL_map = findViewById(R.id.main_LBL_map);

//
//        fragmentList = new Fragment_List();
//        fragmentMap = new Fragment_Map();
//
//        fragmentList.setCallBackList(meshulash);
//
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.main_LAY_top, fragmentList)
//                .add(R.id.main_LAY_bottom, fragmentMap)
//                .commit();
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//
//    private CallBack_List meshulash = new CallBack_List() {
//        @Override
//        public void showLocationInMap(String user) {
//            fragmentMap.setLocation(32.4, 34.5);
//        }
//    };

    }
}
