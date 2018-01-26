package com.asiflogin.loginmvvm0126.trade.validators;

import android.support.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.METValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator extends METValidator {

    public PasswordValidator(String defaultErrString) {
        super(defaultErrString);
    }

    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        return ValidatorUtil.validatePassword(text.toString().trim());
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}
