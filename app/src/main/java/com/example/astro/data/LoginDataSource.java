package com.example.astro.data;

import androidx.annotation.NonNull;

import com.example.astro.data.model.LoggedInUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");

            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            Task<AuthResult> signinTask = firebaseAuth.signInWithEmailAndPassword(username, password);

            signinTask.getResult();
            UserInfo user = signinTask.getResult().getUser();

            if (signinTask.isSuccessful()) {
                return new Result.Success<UserInfo>(user);
            } else {
                throw new Exception("error");
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
