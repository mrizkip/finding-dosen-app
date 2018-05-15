package com.skripsi.mrizk.findingdosen.repository.datasource.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.repository.entity.local.User;

import javax.inject.Inject;

/**
 * Created by mrizk on 15/03/2018.
 */

public class SharedPrefsUserRepository {

    public static final String APP_SHARED_PREFS = "APP_SHARED_PREFS";

    private static final String USER_EMAIL = "USER_EMAIL";
    private static final String USER_TOKEN = "USER_TOKEN";
    private static final String USER_ID = "USER_ID";

    private static final String UNAVAILABLE_VALUE = "Unavailable";
    private static final int ZERO_VALUE = 0;

    private final Context context;
    private final SharedPreferences sharedPrefs;
    private final SharedPreferences.Editor editor;

    @Inject
    public SharedPrefsUserRepository() {
        context = FindingDosenApplication.getApplicationComponent().getApplicationContext();
        sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();
    }

    public void saveUserToPrefs(User user) {
        editor.putString(USER_EMAIL, user.getEmail());
        editor.putString(USER_TOKEN, user.getToken());
        editor.putInt(USER_ID, user.getId());
        editor.apply();
    }

    public User getUserFromPrefs() {
        String email = sharedPrefs.getString(USER_EMAIL, UNAVAILABLE_VALUE);
        String token = sharedPrefs.getString(USER_TOKEN, UNAVAILABLE_VALUE);
        int id = sharedPrefs.getInt(USER_ID, ZERO_VALUE);
        if (email.equalsIgnoreCase(UNAVAILABLE_VALUE) || token.equalsIgnoreCase(UNAVAILABLE_VALUE) || id == ZERO_VALUE) {
            return null;
        }
        User user = new User();
        user.setEmail(email);
        user.setToken(token);
        return user;
    }

    public void unsetUserFromPrefs() {
        editor.remove(USER_EMAIL);
        editor.remove(USER_TOKEN);
        editor.apply();
    }
}
