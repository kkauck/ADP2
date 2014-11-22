//Kyle Kauck

package com.example.kyle.foodwithfriends;

import android.app.Fragment;
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
import com.parse.RequestPasswordResetCallback;

public class ResetPasswordFragment extends Fragment {

    TextView mUserEmail;
    Button mResetPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.reset_frag, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        final View view = getView();
        assert view != null;

        Parse.initialize(getActivity(), "Z2WrL4pGyKpldqzfqawk78CTKQ6sFZf1jhKh2jne", "YDCbfu7O5TPiBIbKrnjhXGbIlJ0iW2YFDEd83xal");

        mUserEmail = (TextView) view.findViewById(R.id.resetEmail);

        //Upon clicking the button the user will have an email sent to them if the email is registered with Parse so that they can reset their forgotten password
        mResetPassword = (Button) view.findViewById(R.id.resetPassword);
        mResetPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = mUserEmail.getText().toString();

                ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {

                    @Override
                    public void done(ParseException e) {

                        if (e == null){

                            Toast.makeText(getActivity(), "An Email Was Successfully Sent To Reset Your Password", Toast.LENGTH_LONG).show();

                        } else {

                            Toast.makeText(getActivity(), "There Was An Error In Sending A Reset Password", Toast.LENGTH_LONG).show();

                        }

                    }

                });

            }

        });

    }
}
