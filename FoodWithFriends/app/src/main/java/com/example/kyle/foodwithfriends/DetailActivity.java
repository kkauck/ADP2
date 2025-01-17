//Kyle Kauck

package com.example.kyle.foodwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;

public class DetailActivity extends Activity {

    public static final String RECIPE_NAME = "RECIPE_NAME";
    public static final String RECIPE_TYPE = "RECIPE_TYPE";
    public static final String RECIPE_TIME = "RECIPE_TIME";
    public static final String RECIPE_INGREDIENTS = "RECIPE_INGREDIENTS";
    public static final String RECIPE_INSTRUCTIONS = "RECIPE_INSTRUCTIONS";
    public static final String RECIPE_IMAGE = "RECIPE_IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main);

        //This will get all the passed in information from the list.
        if (savedInstanceState == null){

            DetailFragment frag = new DetailFragment();
            getFragmentManager().beginTransaction().replace(R.id.detail_container, frag, DetailFragment.TAG).commit();

            Intent intent = getIntent();

            DetailFragment.mRecipeName = intent.getStringExtra(RECIPE_NAME);
            DetailFragment.mRecipeType = intent.getStringExtra(RECIPE_TYPE);
            DetailFragment.mRecipeTime = intent.getStringExtra(RECIPE_TIME);
            DetailFragment.mRecipeIngredients = intent.getStringExtra(RECIPE_INGREDIENTS);
            DetailFragment.mRecipeInstructions = intent.getStringExtra(RECIPE_INSTRUCTIONS);
            DetailFragment.mRecipeImage = intent.getByteArrayExtra(RECIPE_IMAGE);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#571B7E")));

        return true;

    }

    @Override
    protected void onStop() {

        super.onStop();
        finish();

    }
}
