package com.example.kyle.foodwithfriends;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kyle.foodwithfriends.Helpers.DataHelper;
import com.example.kyle.foodwithfriends.Helpers.RecipeAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends android.support.v4.app.Fragment {

    public MainFragment(){


    }

    private ArrayList<DataHelper> mRecipeDetails = new ArrayList<DataHelper>();
    DataHelper mDataHelper;
    private ListView mReciepList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.main_activity_frag, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        View view = getView();
        assert view != null;

        final ParseQuery<ParseObject> recipes = ParseQuery.getQuery("Recipe");
        recipes.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {

                if (e == null){

                    for (int i = 0; i < parseObjects.size(); i++) {

                        String name = parseObjects.get(i).getString("recipeName");
                        String type = parseObjects.get(i).getString("recipeType");
                        String time = parseObjects.get(i).getString("recipeTime");
                        String ingredients = parseObjects.get(i).getString("recipeIngredients");
                        String instructions = parseObjects.get(i).getString("recipeInstructions");
                        ParseFile file = parseObjects.get(i).getParseFile("recipeImage");

                        mRecipeDetails.add(new DataHelper(name, time, type, ingredients, instructions, file));

                        Log.i("Hey", "Bye");

                    }

                } else {

                    //something went very wrong

                }

            }

        });

        mReciepList = (ListView) view.findViewById(R.id.recipeList);
        mReciepList.setAdapter(new RecipeAdapter(getActivity(), mRecipeDetails));

    }
}
