package com.randeep.builditbigger;

import android.test.AndroidTestCase;

import com.randeep.jokelibrary.JokeTeller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Randeep on 12/2/17.
 */

public class NonEmptyStringAsyncTest extends AndroidTestCase {

    String joke;

    public void test() {

        try {
            final CountDownLatch cdl = new CountDownLatch(1);
            new JokeReceiverAsyncTask(new JokeReceiverAsyncTask.JokeListener() {
                @Override
                public void onJokeReceived(String joke) {
                    NonEmptyStringAsyncTest.this.joke = joke;
                    cdl.countDown();
                }
            }, getContext()).execute();

            cdl.await(10, TimeUnit.SECONDS);
            assertNotNull("joke", joke);
            assertFalse("joke is empty", joke.isEmpty());

        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}
