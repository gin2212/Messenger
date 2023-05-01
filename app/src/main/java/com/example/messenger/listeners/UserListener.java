package com.example.messenger.listeners;

import com.example.messenger.Model.User;

public interface UserListener {
    void initiateVideoMeeting(User user);
    void initiateAudioMeeting(User user);
}
