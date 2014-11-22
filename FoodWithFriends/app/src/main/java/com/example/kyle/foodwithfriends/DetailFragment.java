//Kyle Kauck

package com.example.kyle.foodwithfriends;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    public static String TAG = "DETAIL_FRAGMENT";

    public static String mRecipeName;
    public static String mRecipeType;
    public static String mRecipeTime;
    public static String mRecipeIngredients;
    public static String mRecipeInstructions;
    public static byte[] mRecipeImage;

    public DetailFragment(){



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.detail_fragment, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        View view = getView();
        assert view != null;

        TextView detailName = (TextView) view.findViewById(R.id.detailTitle);
        TextView detailType = (TextView) view.findViewById(R.id.detailType);
        TextView detailTime = (TextView) view.findViewById(R.id.detailTime);
        TextView detailIngredients = (TextView) view.findViewById(R.id.detailIngredients);
        TextView detailInstructions = (TextView) view.findViewById(R.id.detailInstructions);
        ImageView detailImage = (ImageView) view.findViewById(R.id.detailImage);

        //Decodes the image from Parse.
        Bitmap recipeImage = BitmapFactory.decodeByteArray(mRecipeImage, 0, mRecipeImage.length);

        //Sets all the views with the correct information
        detailName.setText(mRecipeName);
        detailType.setText("Recipe Type: " + mRecipeType);
        detailTime.setText("Cooking Time: " + mRecipeTime);
        detailIngredients.setText(mRecipeIngredients);
        detailInstructions.setText(mRecipeInstructions);
        detailImage.setImageBitmap(recipeImage);

        Button groceryList = (Button) view.findViewById(R.id.goToGroceries);
        groceryList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CreateGroceryList.class);
                startActivity(intent);

            }
        });

    }
}
