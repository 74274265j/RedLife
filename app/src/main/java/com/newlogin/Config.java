package com.newlogin;

import android.app.Application;

public class Config extends Application {
    public static final String LOGIN_URL = "http://52.37.139.194/android/login.php";
    public static final String REGISTER_URL = "http://52.37.139.194/android/reg.php";
    public static final String HISTORY_URL = "http://52.37.139.194/android/history.php";
    public static final String DONOR_URL = "http://52.37.139.194/android/donor.php";
    public static final String MAKEREQ_URL = "http://52.37.139.194/android/reciever.php";
    public static final String YOUTUBE_API_KEY = "AIzaSyDnqnQBUJSxDv0otX7Qh9OFSwbZWzCvJbg";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
