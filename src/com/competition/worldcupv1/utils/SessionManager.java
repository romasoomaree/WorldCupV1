package com.competition.worldcupv1.utils;

import java.util.HashMap;

import com.competition.worldcupv1.GameListActivity;
import com.competition.worldcupv1.MainActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

@SuppressLint("CommitPrefEdits")
public class SessionManager {
	// Shared Preferences
    SharedPreferences pref;     
    // Editor for Shared preferences
    Editor editor;
    Context _context;     
    // Shared pref mode
    int PRIVATE_MODE = 0;     
    // Sharedpref file name
    private static final String PREF_NAME = "loginPref";     
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_USERNAME = "userName";
    public static final String KEY_UID = "uid";    
    public static final String KEY_NICKNAME = "nickName";    
    public static final String KEY_FAVTEAM = "favTeam";    
    public static final String KEY_COUNTRY = "country";
     
    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }     
    /**
     * Create login session
     * */
    public void createLoginSession(String userName, String uid, String nickName, long favTeamId, String country){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);         
        editor.putString(KEY_USERNAME, userName);
        editor.putString(KEY_UID, uid);        
        editor.putString(KEY_NICKNAME, nickName);        
        editor.putLong(KEY_FAVTEAM, favTeamId);        
        editor.putString(KEY_COUNTRY, country);         
        // commit changes
        editor.commit();
    }  
     
    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);             
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);             
            // Staring Login Activity
            _context.startActivity(i);
        }
        
        else{
        	Intent i = new Intent(_context, GameListActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);             
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);             
            // Staring Login Activity
            _context.startActivity(i);
        }         
    }
     
    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));        
        user.put(KEY_UID, pref.getString(KEY_UID, null));        
        user.put(KEY_NICKNAME, pref.getString(KEY_NICKNAME, null));        
        user.put(KEY_FAVTEAM, String.valueOf(pref.getLong(KEY_FAVTEAM, 0)));        
        user.put(KEY_COUNTRY, pref.getString(KEY_COUNTRY, null));         
        // return user
        return user;
    }
     
    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();         
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);         
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);         
        // Staring Login Activity
        _context.startActivity(i);
    }
     
    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}