//Kyle Kauck

package com.example.kyle.foodwithfriends;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;

public class ResetPasswordMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_main);

        ResetPasswordFragment frag = new ResetPasswordFragment();
        getFragmentManager().beginTransaction().replace(R.id.forgot_container, frag).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#571B7E")));

        return true;

    }

}
