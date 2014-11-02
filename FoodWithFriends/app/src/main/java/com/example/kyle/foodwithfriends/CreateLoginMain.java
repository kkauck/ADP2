package com.example.kyle.foodwithfriends;

import android.app.Activity;
import android.os.Bundle;

public class CreateLoginMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_login_main);

        CreateLoginFragment frag = new CreateLoginFragment();
        getFragmentManager().beginTransaction().replace(R.id.create_container, frag).commit();

    }
}
