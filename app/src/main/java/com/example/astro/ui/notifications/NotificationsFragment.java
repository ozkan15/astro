package com.example.astro.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.astro.R;
import com.example.astro.ui.login.LoginActivity;
import com.example.astro.ui.login.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button logoutBtn = root.findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                UserInfo user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    firebaseAuth.signOut();
                    NotificationsFragment.this.getActivity().finish();
                    NavController appNavigation = Navigation.findNavController(NotificationsFragment.this.getActivity(), R.id.nav_host_fragment);
                    appNavigation.navigate(R.id.action_notificationsFragment_to_loginActivity);
                }
            }
        });

        return root;
    }
}