//Kyle Kauck

package com.example.kyle.foodwithfriends;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreateGroceryListFrag extends Fragment {

    TextView mItemOne;
    TextView mItemTwo;
    TextView mItemThree;
    TextView mItemFour;
    TextView mItemFive;
    TextView mItemSix;
    TextView mItemSeven;
    TextView mItemEight;
    public static ArrayList<String> mListArray = new ArrayList<String>();
    private static final String FILENAME = "groceries.txt";

    public CreateGroceryListFrag(){



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.create_grocery_frag, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        View view = getView();
        assert view  != null;

        mItemOne =  (TextView) view.findViewById(R.id.groceryItemOne);
        mItemTwo =  (TextView) view.findViewById(R.id.groceryItemTwo);
        mItemThree =  (TextView) view.findViewById(R.id.groceryItemThree);
        mItemFour =  (TextView) view.findViewById(R.id.groceryItemFour);
        mItemFive =  (TextView) view.findViewById(R.id.groceryItemFive);
        mItemSix =  (TextView) view.findViewById(R.id.groceryItemSix);
        mItemSeven =  (TextView) view.findViewById(R.id.groceryItemSeven);
        mItemEight =  (TextView) view.findViewById(R.id.groceryItemEight);

        //Code that will load in already saved information from the file to populate the array and make sure it stays in sync and not overwrite other data.
        try{

            FileInputStream input = getActivity().openFileInput(FILENAME);
            ObjectInputStream stream = new ObjectInputStream(input);

            while (input.available() != 0){

                String item = (String) stream.readObject();
                mListArray.add(item);

            }

            stream.close();

        } catch (Exception e){

            e.printStackTrace();

        }


        //Upon clicking will gather all information entered by the user, and calling for the save data code.
        final Button saveListButton = (Button) view.findViewById(R.id.addToList);
        saveListButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String itemOne = mItemOne.getText().toString();
                String itemTwo = mItemTwo.getText().toString();
                String itemThree = mItemThree.getText().toString();
                String itemFour = mItemFour.getText().toString();
                String itemFive = mItemFive.getText().toString();
                String itemSix = mItemSix.getText().toString();
                String itemSeven = mItemSeven.getText().toString();
                String itemEight = mItemEight.getText().toString();

                mListArray.add(itemOne);
                mListArray.add(itemTwo);
                mListArray.add(itemThree);
                mListArray.add(itemFour);
                mListArray.add(itemFive);
                mListArray.add(itemSix);
                mListArray.add(itemSeven);
                mListArray.add(itemEight);

                saveListData();

                Toast.makeText(getActivity(), "You Have Successfully Saved Your Grocery List", Toast.LENGTH_SHORT).show();

                mItemOne.setText("");
                mItemTwo.setText("");
                mItemThree.setText("");
                mItemFour.setText("");
                mItemFive.setText("");
                mItemSix.setText("");
                mItemSeven.setText("");
                mItemEight.setText("");

            }

        });

    }

    //Saves the data to a file that will be saved on the device.
    public void saveListData(){

        try {

            FileOutputStream output = getActivity().openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream stream = new ObjectOutputStream(output);

            for (int i = 0; i < mListArray.size(); i++){

                String item = mListArray.get(i);
                stream.writeObject(item);

            }

            stream.close();

        } catch (Exception e){

            e.printStackTrace();

        }


    }

}
