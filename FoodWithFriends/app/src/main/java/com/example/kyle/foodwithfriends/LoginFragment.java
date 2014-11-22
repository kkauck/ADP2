//Kyle Kauck

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

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginFragment extends Fragment {

    TextView mUserName;
    TextView mUserPass;
    Button mCreateUser;
    Button mForgotPassword;
    Button mLoginUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.login_layout, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        final View view = getView();
        assert view != null;

        mUserName = (TextView) view.findViewById(R.id.loginUsername);
        mUserPass = (TextView) view.findViewById(R.id.loginPassword);

        //Will get the user information and check to make sure that the information is correct, if successful the user is logged into the app if not they are presented with a toast.
        Parse.initialize(getActivity(), "Z2WrL4pGyKpldqzfqawk78CTKQ6sFZf1jhKh2jne", "YDCbfu7O5TPiBIbKrnjhXGbIlJ0iW2YFDEd83xal");

        mLoginUser = (Button) view.findViewById(R.id.loginButton);
        mLoginUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String username = mUserName.getText().toString().toLowerCase();
                String password = mUserPass.getText().toString();

                ParseUser.logInInBackground(username, password, new LogInCallback() {

                    @Override
                    public void done(ParseUser parseUser, ParseException e) {

                        if (parseUser != null){

                            Toast.makeText(getActivity(), "You Have Successfully Logged In", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();

                        } else {

                            Toast.makeText(getActivity(), "You Were Unable To Login, Check Username/Password", Toast.LENGTH_SHORT).show();

                        }

                    }

                });


            }

        });

        mCreateUser = (Button) view.findViewById(R.id.createUserButton);
        mCreateUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent createUser = new Intent(getActivity(), CreateLoginMain.class);
                startActivity(createUser);

            }

        });

        mForgotPassword = (Button) view.findViewById(R.id.forgotPassword);
        mForgotPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent forgot = new Intent(getActivity(), ResetPasswordMain.class);
                startActivity(forgot);

            }

        });

    }
}
