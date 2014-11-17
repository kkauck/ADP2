//Kyle Kauck

package com.example.kyle.foodwithfriends.Helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kyle.foodwithfriends.R;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;

import java.util.ArrayList;

public class RecipeAdapter extends BaseAdapter {

    public static final long CONSTANT_ID = 0x010101010L;
    private Context mContext;
    private ArrayList<DataHelper> mRecipeDetails;

    public RecipeAdapter (Context _context, ArrayList<DataHelper> _recipeArray){

        mContext = _context;
        mRecipeDetails = _recipeArray;

    }

    @Override
    public int getCount() {

        return mRecipeDetails.size();

    }

    @Override
    public Object getItem(int position) {

        return mRecipeDetails.get(position);

    }

    @Override
    public long getItemId(int position) {

        return CONSTANT_ID + position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){

            convertView = LayoutInflater.from(mContext).inflate(R.layout.custom_list, parent, false);

        }

        DataHelper helper = (DataHelper) getItem(position);

        ParseImageView recipeImage = (ParseImageView) convertView.findViewById(R.id.listImage);
        ParseFile image = helper.getFile();

        if (image != null){

            recipeImage.setParseFile(image);
            recipeImage.loadInBackground(new GetDataCallback() {

                @Override
                public void done(byte[] bytes, ParseException e) {



                }

            });

        }

        TextView name = (TextView) convertView.findViewById(R.id.listName);
        TextView time = (TextView) convertView.findViewById(R.id.listTime);
        TextView type = (TextView) convertView.findViewById(R.id.listType);

        name.setText(helper.getName());
        time.setText("Cooking Time: " + helper.getTime());
        type.setText("Recipe Type: " + helper.getType());

        return convertView;

    }

}
