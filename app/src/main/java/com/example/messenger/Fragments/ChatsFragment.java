package com.example.messenger.Fragments;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.messenger.Adapter.UserAdapter;
import com.example.messenger.Model.Chat;
import com.example.messenger.Model.User;
import com.example.messenger.Notifications.Token;
import com.example.messenger.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatsFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> mUsers;
    private static final String TAG = "MyFirebaseMessagingService";

    FirebaseUser fuser;
    DatabaseReference reference;

    private List<String> usersList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        usersList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Chat chat= snapshot.getValue(Chat.class);

                    if (chat.getSender().equals(fuser.getUid())) {
                        usersList.add(chat.getReceiver());
                    }

                    if (chat.getReceiver().equals(fuser.getUid())) {
                        usersList.add(chat.getSender());
                    }
                }

                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                return;
            }

            String token = task.getResult();
            updateToken(token);
        });

        return view;
    }

    private void updateToken(String token) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());;
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        reference.updateChildren(map);
    }

    private void readChats() {
        mUsers = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);

                    for (String id: usersList) {
                        if (user.getId().equals(id)) {
                            if (mUsers.size() != 0) {
                                for (User user1: mUsers) {
                                    if (!user.getId().equals(user1.getId())) {
                                        mUsers.add(user);
                                    }
                                }
                            } else {
                                mUsers.add(user);
                            }
                        }
                    }
                }

                userAdapter = new UserAdapter(getContext(), mUsers, true);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}