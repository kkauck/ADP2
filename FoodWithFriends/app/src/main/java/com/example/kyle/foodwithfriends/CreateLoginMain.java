//Kyle Kauck

package com.example.kyle.foodwithfriends;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;

public class CreateLoginMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_login_main);

        CreateLoginFragment frag = new CreateLoginFragment();
        getFragmentManager().beginTransaction().replace(R.id.create_container, frag).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#571B7E")));

        return true;

    }

}
