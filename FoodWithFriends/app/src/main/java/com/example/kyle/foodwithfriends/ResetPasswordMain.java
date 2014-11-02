package com.example.kyle.foodwithfriends;

import android.app.Activity;
import android.os.Bundle;

public class ResetPasswordMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_main);

        ResetPasswordFragment frag = new ResetPasswordFragment();
        getFragmentManager().beginTransaction().replace(R.id.forgot_container, frag).commit();

    }
}
