package com.example.expensecontrol;

import android.content.Context;

public interface Escritor {

    public void writeFile(String data, Context context, String filePath);
}
