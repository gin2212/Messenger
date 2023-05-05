package com.example.messenger.utilities;

import java.util.HashMap;

public class Constants {

    public static final String KEY_COLLECTION_USERS = "Users";
    public static final String KEY_USER_ID = "id";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_USER_IMAGE = "imageUrl";
    public static final String REMOTE_MSG_AUTHORIZATION = "Authorization";
    public static final String REMOTE_MSG_CONTENT_TYPE = "Content-Type";
    public static final String REMOTE_MSG_TYPE = "type";
    public static final String REMOTE_MSG_INVITATION = "invitation";
    public static final String REMOTE_MSG_MEETING_TYPE = "meetingType";
    public static final String REMOTE_MSG_INVITER_TOKEN = "inviterToken";
    public static final String REMOTE_MSG_DATA = "data";
    public static final String REMOTE_MSG_REGISTRATION_IDS = "registration_ids";
    public static final String REMOTE_MSG_INVITATION_RESPONSE = "invitationResponse";
    public static final String REMOTE_MSG_INVITATION_ACCEPTED = "accepted";
    public static final String REMOTE_MSG_INVITATION_REJECTED = "rejected";
    public static final String REMOTE_MSG_INVITATION_CANCELED = "canceled";
    public static final String REMOTE_MSG_MEETING_ROOM = "meetingRoom";


    public static HashMap<String, String> getRemoteMessageHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(
                Constants.REMOTE_MSG_AUTHORIZATION,
                "key=AAAApHfnEWw:APA91bFk-HyJHBXBZcpk-U8Mbn8dDS9A-F4oHW-UkTSDx1bJD3vVF1lkLfCLvIDdMNVYomTMt4UwdcjHGMy_94gOGoRrWBnUYD_TwbrP-LK9FDCnvU-JfmkVcwLYvMZIS0WJVbQZDEBG"

        );
        headers.put(Constants.REMOTE_MSG_CONTENT_TYPE, "application/json");
        return headers;
    }

}
