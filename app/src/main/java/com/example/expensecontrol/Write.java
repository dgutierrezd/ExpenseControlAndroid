package com.example.expensecontrol;

import android.content.Context;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Write {

    public static void writeFile(String data, Context context, String filePath) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filePath, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error.");
        }
    }
}
