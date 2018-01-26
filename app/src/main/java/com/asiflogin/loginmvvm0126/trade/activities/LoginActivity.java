package com.asiflogin.loginmvvm0126.trade.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.asiflogin.loginmvvm0126.trade.R;
import com.asiflogin.loginmvvm0126.trade.databinding.ActivityLoginBinding;
import com.asiflogin.loginmvvm0126.trade.di.DaggerAppComponent;
import com.asiflogin.loginmvvm0126.trade.viewmodels.LoginViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginViewModel.ViewListener {
    @Inject
    LoginViewModel viewModel;
    private ConstraintLayout mLlParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_login
        );
        mLlParent = binding.llParent;
        DaggerAppComponent.builder().build().inject(this);
        viewModel.setViewListener(this);
        binding.setViewModel(viewModel);
        MaterialEditText emailEditText = binding.etEmailLogin;
        MaterialEditText passwordEditText = binding.etPasswordLogin;
        emailEditText.setAutoValidate(true);
        passwordEditText.setAutoValidate(true);
        emailEditText.addValidator(viewModel.getEmailValidator());
        passwordEditText.addValidator(viewModel.getPasswordValidator());
    }

    @Override
    public void onLoginSuccess() {
        showAlertDialog("SUCCESS", "Successfully logged in");
    }

    @Override
    public void onMessage(String message) {
        // Hide soft keyboard
        InputMethodManager imm = (InputMethodManager) this.getSystemService(
                Activity.INPUT_METHOD_SERVICE
        );
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        showAlertDialog("ERROR",message);

    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        builder.setTitle(title)
                .setMessage(message)
                .setNegativeButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }
                ).create()
                .show();
    }
}
