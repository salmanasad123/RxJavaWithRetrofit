package com.example.teamas.loginfunctionality.Login.utils.constants;

public class LoginInApplication {

    public static boolean isValidEmail(CharSequence email) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();

    }
}
