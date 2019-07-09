package com.example.androidroom.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.androidroom.db.UserDatabase;
import com.example.androidroom.db.dao.UserDao;
import com.example.androidroom.model.User;

import java.util.List;

import io.reactivex.Flowable;

public class UserRepo {

    private UserDao userDao;
    private Flowable<List<User>> allUsers;

    public UserRepo(Application application){
        userDao = UserDatabase.getInstance(application).userDao();
        allUsers = userDao.getAll();
    }

    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }
    public void update(User user){
        new UpdateUserAsyncTask(userDao).execute(user);
    }
    public void delete(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }
    public void deleteAllUsers(){
        new DeleteAllUserAsyncTask(userDao).execute();
    }

    public Flowable<List<User>> getAllUsers(){
        return allUsers;
    }



    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }
    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }
    private static class DeleteAllUserAsyncTask extends AsyncTask<Void, Void, Void>{

        private UserDao userDao;

        private DeleteAllUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAll();
            return null;
        }
    }
}
