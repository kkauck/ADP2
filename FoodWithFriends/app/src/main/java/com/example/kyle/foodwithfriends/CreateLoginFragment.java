package com.example.kyle.foodwithfriends;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateLoginFragment extends Fragment {

    TextView mUserName;
    TextView mUserPass;
    TextView mUserEmail;
    Button mCreateUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.create_login_frag, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        final View view = getView();
        assert view != null;

        Parse.initialize(getActivity(), "Z2WrL4pGyKpldqzfqawk78CTKQ6sFZf1jhKh2jne", "YDCbfu7O5TPiBIbKrnjhXGbIlJ0iW2YFDEd83xal");

        mUserName = (TextView) view.findViewById(R.id.createUsername);
        mUserPass = (TextView) view.findViewById(R.id.createPassword);
        mUserEmail = (TextView) view.findViewById(R.id.createEmail);

        mCreateUser = (Button) view.findViewById(R.id.createButton);
        mCreateUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String username = mUserName.getText().toString().toLowerCase();
                String password = mUserPass.getText().toString();
                String email = mUserEmail.getText().toString();

                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);

                user.signUpInBackground(new SignUpCallback() {

                    @Override
                    public void done(ParseException e) {

                        if (e == null){

                            Toast.makeText(getActivity(), "New User Was Successfully Created", Toast.LENGTH_LONG).show();
                            Intent login = new Intent(getActivity(), LoginMain.class);
                            startActivity(login);

                        } else {

                            e.printStackTrace();
                            Toast.makeText(getActivity(), "New User Was Not Created", Toast.LENGTH_SHORT).show();

                        }

                    }

                });

            }

        });

    }
}
