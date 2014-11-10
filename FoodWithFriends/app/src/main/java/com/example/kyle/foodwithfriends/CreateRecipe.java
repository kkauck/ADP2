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

        Button saveRecipe = (Button) view.findViewById(R.id.saveNewRecipe);
        saveRecipe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                mImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                ParseFile imageFile = new ParseFile("RecipeImage.jpg", stream.toByteArray());

                imageFile.saveInBackground();

                ParseObject recipe = new ParseObject("Recipe");

                recipe.put("recipeImage", imageFile);

                recipe.saveInBackground();

            }

        });

    }

    private Uri getOutUri() {

        String imageName = new SimpleDateFormat("MMddyyyy HHmmss").format(new Date(System.currentTimeMillis()));
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

    private void addImage(Uri imageUri){

        Intent picIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        picIntent.setData(imageUri);
        getActivity().sendBroadcast(picIntent);

    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_recipe_main);

        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_home) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);

    }*/

}
