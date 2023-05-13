package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.room.dao.UserDao;
import com.example.room.database.AppDatabase;
import com.example.room.entity.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        userDao = db.userDao();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                User usuario = new User();
                usuario.uid = 2;
                usuario.firstName = "Diego";
                usuario.lastName = "Peña";

                userDao.insertAll(usuario);
                List<User> users = userDao.getAll();
            }
        });
    }
}