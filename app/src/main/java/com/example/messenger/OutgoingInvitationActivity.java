package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.messenger.Model.User;

public class OutgoingInvitationActivity extends AppCompatActivity {

    private SharedPreferences preferenceManager;
    private String inviterToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoing_invitation);

        preferenceManager = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                inviterToken = task.getResult().getToken();
            }
        });

        ImageView imageMeetingType = findViewById(R.id.imageMeetingType);
        String meetingType = getIntent().getStringExtra("type");

        if (meetingType != null && meetingType.equals("video")) {
            imageMeetingType.setImageResource(R.drawable.ic_video);
        }

        TextView textUsername = findViewById(R.id.textUsername);

        User user = (User) getIntent().getSerializableExtra("user");

        if (user != null) {
            textUsername.setText(user.getUsername());
        }

        ImageView imageStopInvitation = findViewById(R.id.imageStopInvitation);
        imageStopInvitation.setOnClickListener(v -> onBackPressed());
    }
}