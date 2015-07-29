package com.termux.api;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.termux.api.util.ResultReturner;
import com.termux.api.util.TermuxApiLogger;

import java.io.PrintWriter;

public class ToastAPI {

    public static void onReceive(TermuxApiReceiver receiver, final Context context, Intent intent) {
        final int durationExtra = intent.getBooleanExtra("short", false) ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;
        final String gravityExtra = intent.getStringExtra("gravity");

        final Handler handler = new Handler();

        TermuxApiLogger.info("duration=" + durationExtra);
        ResultReturner.returnData(context, intent, new ResultReturner.WithStringInput() {
            @Override
            public void writeResult(PrintWriter out) throws Exception {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(context, inputString, durationExtra);
                        toast.show();
                    }
                });
            }
        });
    }

}
