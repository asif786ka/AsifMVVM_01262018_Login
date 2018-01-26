package com.asiflogin.loginmvvm0126.trade.viewmodels;

import android.databinding.BaseObservable;
import android.util.Log;

import com.asiflogin.loginmvvm0126.trade.entities.User;
import com.asiflogin.loginmvvm0126.trade.repositories.UserAlreadyExistsException;
import com.asiflogin.loginmvvm0126.trade.repositories.UserRepository;
import com.asiflogin.loginmvvm0126.trade.validators.EmailValidator;
import com.asiflogin.loginmvvm0126.trade.validators.PasswordValidator;
import com.rengwuxian.materialedittext.validation.METValidator;

import javax.inject.Inject;


public class LoginViewModel extends BaseObservable {

    private String mEmail, mPassword;
    private boolean mLoginEnabled;
    private boolean mRegisterEnabled;
    private ViewListener mListener;
    private EmailValidator mEmailValidator;
    private PasswordValidator mPasswordValidator;

    private UserRepository mUserRepository;

    @Inject
    public LoginViewModel(
            EmailValidator emailValidator,
            PasswordValidator passwordValidator,
            UserRepository userRepository
    ) {
        mEmail = "";
        mPassword = "";
        mEmailValidator = emailValidator;
        mPasswordValidator = passwordValidator;
        this.mUserRepository = userRepository;
    }

    public void setViewListener(ViewListener listener) {
        this.mListener = listener;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
        notifyChange();
        setLoginEnabled(isInputValid());
        setRegisterEnabled(isInputValid());
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
        notifyChange();
        setLoginEnabled(isInputValid());
        setRegisterEnabled(isInputValid());
    }

    public boolean isLoginEnabled() {
        return mLoginEnabled;
    }

    public void setLoginEnabled(boolean loginEnabled) {
        this.mLoginEnabled = loginEnabled;
        notifyChange();
    }

    public boolean isRegisterEnabled() {
        return mRegisterEnabled;
    }

    public void setRegisterEnabled(boolean registerEnabled) {
        this.mRegisterEnabled = registerEnabled;
        notifyChange();
    }

    private boolean isInputValid() {
        return mEmailValidator.isValid(mEmail, mEmail.length() == 0) &&
                mPasswordValidator.isValid(mPassword, mPassword.length() == 0);
    }

    public void onLoginClick() {
        if (isInputValid()) {
            setLoginEnabled(false);
            try {
                User user = mUserRepository.fetchByEmail(mEmail);
                if (user != null && user.getEmail().equals(mEmail)) {
                    // User exists in local DB, check for password
                    if (user.getPassword().equals(mPassword)) {
                        // Login successful
                        mListener.onLoginSuccess();
                    } else {
                        // Wrong mPassword
                        mListener.onMessage("Wrong password. Please retry.");
                    }
                } else {
                    // User not found
                    mListener.onMessage("Email not Registered. Please Register first.");
                }
            } catch (Exception e) {
                Log.d("LoginViewModel", "Error while saving: " + e.getMessage());
            } finally {
                setLoginEnabled(true);
            }
        }
    }

    public void onRegisterClick() {
        if (isInputValid()) {
            setRegisterEnabled(false);
            // Save the user in DB
            try {
                mUserRepository.save(new User(mEmail,mPassword));
                mListener.onLoginSuccess();
            } catch (UserAlreadyExistsException e) {
                Log.d("RegisterViewModel", "Error while saving: " + e.getMessage());
                mListener.onMessage("User Already Exists");
            } finally {
                setRegisterEnabled(true);
            }
        }
    }


    public METValidator getEmailValidator() {
        return mEmailValidator;
    }

    public PasswordValidator getPasswordValidator() {
        return mPasswordValidator;
    }

    public interface ViewListener {

        void onLoginSuccess();

        void onMessage(String message);
    }
}
