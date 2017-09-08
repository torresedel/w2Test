package com.example.admin.w2test;

import android.os.Bundle;
import android.os.Message;

import java.util.Random;
import java.util.logging.Handler;

/**
 * Created by Admin on 9/7/2017.
 */

public class TestThreadHandlerMessage extends Thread {

    android.os.Handler handler;

    public TestThreadHandlerMessage(android.os.Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();

        String data = "";

        Random random = new Random();

        data = Integer.toString(random.nextInt(20));

        Bundle bundle = new Bundle();
        bundle.putString("keyData", data);

        Message message = new Message();
        message.setData(bundle);
        handler.sendMessage(message);


    }
}
