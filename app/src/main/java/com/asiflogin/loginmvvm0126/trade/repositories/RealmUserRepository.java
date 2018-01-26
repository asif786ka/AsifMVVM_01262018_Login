package com.asiflogin.loginmvvm0126.trade.repositories;

import com.asiflogin.loginmvvm0126.trade.entities.User;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;

@Singleton
public class RealmUserRepository implements UserRepository {

    @Inject
    public RealmUserRepository() {

    }

    @Override
    public void save(final User user) throws UserAlreadyExistsException {
        Realm realm = Realm.getDefaultInstance();
        // Check if User with given email already exist
        User existingUser = fetchByEmail(user.getEmail());
        if (existingUser == null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(user);
                }
            });
            if (!realm.isClosed()) {
                realm.close();
            }
        } else {
            // User already exists!
            throw new UserAlreadyExistsException("User already exists");
        }
    }

    @Override
    public User fetchByEmail(String email) {
        Realm realm = Realm.getDefaultInstance();
        try {
            User user = realm.where(User.class).equalTo("email", email).findFirst();
            if (user != null) {
                return realm.copyFromRealm(user);
            } else {
                return null;
            }
        } finally {
            if (!realm.isClosed()) {
                realm.close();
            }
        }
    }

}
