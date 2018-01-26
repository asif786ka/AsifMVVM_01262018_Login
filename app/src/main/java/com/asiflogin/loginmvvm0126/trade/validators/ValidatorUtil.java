package com.asiflogin.loginmvvm0126.trade.validators;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    /**
     * @param email the email to be validated
     * @return true if the email is valid
     */
    public static boolean validateEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     *
     * @param password the password to be validated
     * @return true if the password is valid
     */
    public static boolean validatePassword(String password) {
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
