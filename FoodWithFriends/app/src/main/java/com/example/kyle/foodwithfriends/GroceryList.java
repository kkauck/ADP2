//Kyle Kauck

package com.example.kyle.foodwithfriends;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GroceryList extends Fragment {

    private ActionMode mAction;
    private ArrayList<String> mGroceryListArray = new ArrayList<String>();
    private static final String FILENAME = "groceries.txt";
    private int mGroceryPosition = -1;
    private View mView;

    public GroceryList(){



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.grocery_list, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mView = getView();
        assert mView != null;

        //Calls the load function to load in saved data then sets the array adapter and a long click to let the user delete items
        loadSavedData();

        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mGroceryListArray);

        ListView groceryList = (ListView) mView.findViewById(R.id.groceryList);
        groceryList.setAdapter(myArrayAdapter);
        groceryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (mAction != null){

                    return false;

                }

                mGroceryPosition = position;
                mAction = getActivity().startActionMode(actionCallback);

                return true;

            }

        });

        //Will take the user to the create new grocery list view
        Button addGroceries = (Button) mView.findViewById(R.id.createGrocery);
        addGroceries.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CreateGroceryList.class);
                intent.putExtra("groceries", mGroceryListArray);
                startActivity(intent);

            }

        });

    }

    //Loads in saved data from the device
    public void loadSavedData(){

        mGroceryListArray.clear();

        try{

            FileInputStream input = getActivity().openFileInput(FILENAME);
            ObjectInputStream stream = new ObjectInputStream(input);

            while (input.available() != 0){

                String item = (String) stream.readObject();
                mGroceryListArray.add(item);

            }

            stream.close();

        } catch (Exception e){

            e.printStackTrace();

        }

    }

    private ActionMode.Callback actionCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            MenuInflater inflate = mode.getMenuInflater();
            inflate.inflate(R.menu.grocery_menu, menu);

            return true;

        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

            return false;

        }

        //Lets the user delete an item that then updates the list to make sure the deleted item is removed from the file as well
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()){

                case R.id.groceryDelete:

                    deleteThisContact();
                    mode.finish();

                    return true;

                default:

                    return false;

            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            mAction = null;

        }

    };

    //Deletes the contact
    public void deleteThisContact(){

        mGroceryListArray.remove(mGroceryPosition);
        updateList();

    }

    //Updates the file after something is deleted and will update the list as well to reflect the item being deleted.
    public void updateList(){

        try {

            FileOutputStream output = getActivity().openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream stream = new ObjectOutputStream(output);

            for (int i = 0; i < mGroceryListArray.size(); i++){

                String item = mGroceryListArray.get(i);
                stream.writeObject(item);

            }

            stream.close();

        } catch (Exception e){

            e.printStackTrace();

        }

        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mGroceryListArray);

        ListView groceryList = (ListView) mView.findViewById(R.id.groceryList);
        groceryList.setAdapter(null);
        groceryList.setAdapter(myArrayAdapter);

    }

    @Override
    public void onResume() {

        super.onResume();

        loadSavedData();

        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mGroceryListArray);

        ListView groceryList = (ListView) mView.findViewById(R.id.groceryList);
        groceryList.setAdapter(null);
        groceryList.setAdapter(myArrayAdapter);

    }
}
