//Kyle Kauck

package com.example.kyle.foodwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateRecipe extends Fragment {

    private static final int REQUEST_TAKE_PICTURE = 101;

    Uri mImageUri;
    Bitmap mImage;
    ImageView mRecipeImage;
    TextView mRecipeName;
    TextView mRecipeTime;
    TextView mRecipeType;
    TextView mRecipeIngredients;
    TextView mRecipeInstructions;
    String imageName;

    public CreateRecipe(){



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.create_recipe_frag, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        Parse.initialize(getActivity(), "Z2WrL4pGyKpldqzfqawk78CTKQ6sFZf1jhKh2jne", "YDCbfu7O5TPiBIbKrnjhXGbIlJ0iW2YFDEd83xal");

        final View view = getView();
        assert view != null;

        mRecipeImage = (ImageView) view.findViewById(R.id.createRecipeImage);
        mRecipeName = (TextView) view.findViewById(R.id.createRecipeName);
        mRecipeTime = (TextView) view.findViewById(R.id.createRecipeTime);
        mRecipeType = (TextView) view.findViewById(R.id.createRecipeType);
        mRecipeIngredients = (TextView) view.findViewById(R.id.createRecipeIngredients);
        mRecipeInstructions = (TextView) view.findViewById(R.id.createRecipeInstructions);

        //Starts up intent to call the built in Camera Intent
        Button takePicture = (Button) view.findViewById(R.id.takePicture);
        takePicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mImageUri = getOutUri();

                if (mImageUri != null){

                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);

                }

                startActivityForResult(cameraIntent, REQUEST_TAKE_PICTURE);

            }

        });

        //Gets all information from the user entered results, as well as converts the image file into a savable data type that is then saved into Parse.
        Button saveRecipe = (Button) view.findViewById(R.id.saveNewRecipe);
        saveRecipe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name, time, type, ingredients, instructions;
                name  = mRecipeName.getText().toString();
                time = mRecipeTime.getText().toString();
                type = mRecipeType.getText().toString();
                ingredients = mRecipeIngredients.getText().toString();
                instructions = mRecipeInstructions.getText().toString();

                Bitmap scaleImage = Bitmap.createScaledBitmap(mImage, 150, 150 * mImage.getHeight() / mImage.getWidth(), false);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                scaleImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                ParseFile imageFile = new ParseFile(imageName+".jpg", stream.toByteArray());

                imageFile.saveInBackground();

                ParseObject recipe = new ParseObject("Recipe");

                recipe.put("recipeName", name);
                recipe.put("recipeTime", time);
                recipe.put("recipeType", type);
                recipe.put("recipeIngredients", ingredients);
                recipe.put("recipeInstructions", instructions);
                recipe.put("recipeImage", imageFile);

                recipe.saveInBackground();

                Toast.makeText(getActivity(), "You Have Successfully Saved Your Recipe", Toast.LENGTH_SHORT).show();

                mRecipeIngredients.setText("");
                mRecipeType.setText("");
                mRecipeTime.setText("");
                mRecipeInstructions.setText("");
                mRecipeName.setText("");
                mRecipeImage.setImageResource(android.R.color.transparent);

            }

        });

    }

    //Gets the URI of the image so that is can be saved to the device
    private Uri getOutUri() {

        imageName = new SimpleDateFormat("MMddyyyy HHmmss").format(new Date(System.currentTimeMillis()));
        File imageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File recipeDirectory = new File(imageDirectory, "Recipe Photos");
        recipeDirectory.mkdirs();

        File image = new File(recipeDirectory, imageName + ".jpg");

        try{

            image.createNewFile();

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }

        return Uri.fromFile(image);

    }

    //Returns the image from the camera intent
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAKE_PICTURE && resultCode != Activity.RESULT_CANCELED){

            mRecipeImage.setImageBitmap(BitmapFactory.decodeFile(mImageUri.getPath()));

            try {

                mImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mImageUri);

            } catch (IOException e) {

                e.printStackTrace();

            }

            addImage(mImageUri);

        } else {

            mRecipeImage.setImageBitmap((Bitmap)data.getParcelableExtra("data"));

        }

    }

    //Adds image to device
    private void addImage(Uri imageUri){

        Intent picIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        picIntent.setData(imageUri);
        getActivity().sendBroadcast(picIntent);

    }

}
