//Kyle Kauck

package com.example.kyle.foodwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    public MainFragment() {


    }

    private ArrayList<DataHelper> mRecipeDetails = new ArrayList<DataHelper>();
    DataHelper mDataHelper;
    private ListView mReciepList;
    TextView mSearchTerm;
    byte[] mRecipeData;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.main_activity_frag, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mRecipeDetails.clear();

        view = getView();
        assert view != null;

        mSearchTerm = (TextView) view.findViewById(R.id.mainSearch);
        mReciepList = (ListView) view.findViewById(R.id.recipeList);

        //Loads in the information from parse and uses a data helper class to add everything to an array that is then used to create a list view
        final ParseQuery<ParseObject> recipes = ParseQuery.getQuery("Recipe");
        recipes.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {

                if (e == null) {

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

                    Toast.makeText(getActivity(), "There were no saved recipes to display", Toast.LENGTH_SHORT).show();

                }

                mReciepList = (ListView) view.findViewById(R.id.recipeList);
                mReciepList.setAdapter(new RecipeAdapter(getActivity(), mRecipeDetails));

            }

        });

        //Uses the entered search term by the user to find the correct recipes and then display them in a list to the user
        final Button searchButton = (Button) view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mReciepList.setAdapter(null);

                String search = mSearchTerm.getText().toString();

                mRecipeDetails.clear();

                ParseQuery<ParseObject> searchRecipe = ParseQuery.getQuery("Recipe");
                searchRecipe.whereContains("recipeType", search);
                searchRecipe.findInBackground(new FindCallback<ParseObject>() {

                    @Override
                    public void done(List<ParseObject> parseObjects, ParseException e) {

                        if (e == null) {

                            for (int i = 0; i < parseObjects.size(); i++) {

                                String name = parseObjects.get(i).getString("recipeName");
                                String type = parseObjects.get(i).getString("recipeType");
                                String time = parseObjects.get(i).getString("recipeTime");
                                String ingredients = parseObjects.get(i).getString("recipeIngredients");
                                String instructions = parseObjects.get(i).getString("recipeInstructions");
                                ParseFile file = parseObjects.get(i).getParseFile("recipeImage");

                                mRecipeDetails.add(new DataHelper(name, time, type, ingredients, instructions, file));

                            }

                            Log.i("Hello", "Goodbye");

                            mReciepList = (ListView) view.findViewById(R.id.recipeList);
                            mReciepList.setAdapter(new RecipeAdapter(getActivity(), mRecipeDetails));

                        } else {

                            Toast.makeText(getActivity(), "There were no saved recipes to display", Toast.LENGTH_SHORT).show();
                            Log.i("Hello", "Goodbye");

                        }

                    }

                });

            }

        });

        //Upon click an item the user will be taken to a detail view that will display the correct information to the user.
        mReciepList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent detailView = new Intent(getActivity(), DetailActivity.class);

                mDataHelper = mRecipeDetails.get(position);

                ParseFile recipeImage = mDataHelper.getFile();

                try {

                    mRecipeData = recipeImage.getData();

                } catch (ParseException e) {

                    e.printStackTrace();
                }

                //detailView.putExtra(DetailActivity.EXTRA_ITEM, mDataHelper);
                detailView.putExtra(DetailActivity.RECIPE_NAME, mDataHelper.getName());
                detailView.putExtra(DetailActivity.RECIPE_TIME, mDataHelper.getTime());
                detailView.putExtra(DetailActivity.RECIPE_TYPE, mDataHelper.getType());
                detailView.putExtra(DetailActivity.RECIPE_INGREDIENTS, mDataHelper.getIngredients());
                detailView.putExtra(DetailActivity.RECIPE_INSTRUCTIONS, mDataHelper.getInstructions());
                detailView.putExtra(DetailActivity.RECIPE_IMAGE, mRecipeData);

                startActivity(detailView);

            }
        });

    }

}

