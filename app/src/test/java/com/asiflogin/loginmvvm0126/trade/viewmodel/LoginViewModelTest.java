package com.asiflogin.loginmvvm0126.trade.viewmodel;

import com.asiflogin.loginmvvm0126.trade.entities.User;
import com.asiflogin.loginmvvm0126.trade.repositories.UserRepository;
import com.asiflogin.loginmvvm0126.trade.validators.EmailValidator;
import com.asiflogin.loginmvvm0126.trade.validators.PasswordValidator;
import com.asiflogin.loginmvvm0126.trade.validators.ValidatorUtil;
import com.asiflogin.loginmvvm0126.trade.viewmodels.LoginViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LoginViewModelTest {

    private LoginViewModel viewModel;
    @Mock
    private UserRepository repository;
    @Mock
    private EmailValidator emailValidator;
    @Mock
    private PasswordValidator passwordValidator;

    @Before
    public void setUp() throws Exception {
        passwordValidator = mock(PasswordValidator.class);
        viewModel = new LoginViewModel(emailValidator, passwordValidator, repository);
        User user = new User();
        user.setEmail("hub123@gmail.com");
        user.setPassword("Ca123456");
        repository.save(user);
        when(repository.fetchByEmail("hub123@gmail.com")).thenReturn(user);

    }

    @Test
    public void testEmailSaved() throws Exception {
        assertEquals(repository.fetchByEmail("hub123@gmail.com").getEmail(), "hub123@gmail.com");

    }

    @Test
    public void testPasswordLength() throws Exception {
        assertTrue(repository.fetchByEmail("hub123@gmail.com").getPassword().toString().length() >= 8);
    }

    @Test
    public void testPasswordValidatePass() throws Exception {
        assertTrue(ValidatorUtil.validatePassword(repository.fetchByEmail("hub123@gmail.com").getPassword()));
    }

    @Test
    public void testPasswordValidateFail() throws Exception {
        User user = new User();
        user.setEmail("hub123@gmail.com");
        user.setPassword("1234");
        repository.save(user);
        when(repository.fetchByEmail("hub123@gmail.com")).thenReturn(user);
        assertFalse(ValidatorUtil.validatePassword(repository.fetchByEmail("hub123@gmail.com").getPassword()));
    }

    @Test
    public void testEmailValidatePass() throws Exception {
        assertTrue(ValidatorUtil.validateEmail(repository.fetchByEmail("hub123@gmail.com").getEmail()));
    }

    @Test
    public void testEmailValidateFail() throws Exception {
        User user = new User();
        user.setEmail("hub123gmail.com");
        user.setPassword("1234");
        repository.save(user);
        when(repository.fetchByEmail("hub123@gmail.com")).thenReturn(user);
        assertFalse(ValidatorUtil.validateEmail(repository.fetchByEmail("hub123@gmail.com").getEmail()));
    }

}