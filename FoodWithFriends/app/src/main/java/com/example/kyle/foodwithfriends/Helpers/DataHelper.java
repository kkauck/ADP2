//Kyle Kauck

package com.example.kyle.foodwithfriends.Helpers;

import com.parse.ParseFile;

import java.io.Serializable;

public class DataHelper implements Serializable {

    private String mName;
    private String mTime;
    private String mType;
    private String mIngredients;
    private String mInstructions;
    private ParseFile mFile;

    public DataHelper(){

        mIngredients = "";
        mInstructions = "";
        mName = "";
        mTime = "";
        mType = "";

    }

    public DataHelper (String _name, String _time, String _type, String _ingredients, String _instructions, ParseFile _file){

        mName = _name;
        mTime = _time;
        mType = _type;
        mIngredients = _ingredients;
        mInstructions = _instructions;
        mFile = _file;

    }

    public String getName(){

        return mName;

    }

    public String getTime(){

        return mTime;

    }

    public String getType(){

        return mType;

    }

    public String getIngredients(){

        return mIngredients;

    }

    public String getInstructions(){

        return mInstructions;

    }

    public ParseFile getFile(){

        return mFile;

    }

}
