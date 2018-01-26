package com.asiflogin.loginmvvm0126.trade.validators;

import android.support.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.METValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends METValidator {

    public EmailValidator(String defaultErrString) {
        super(defaultErrString);
    }

    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        return ValidatorUtil.validateEmail(text.toString().trim());
    }

    public boolean isValidEmail(final String email) {

        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
