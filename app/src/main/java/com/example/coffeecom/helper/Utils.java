package com.example.coffeecom.helper;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Utils {

    public static void snackbar(View view, String message) {
        Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_LONG
        ).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dismiss the Snackbar
            }
        }).show();
    }

    public static String getFileName(ContentResolver contentResolver, Uri fileUri) {
        String name = "";
        Cursor returnCursor = contentResolver.query(fileUri, null, null, null, null);
        if (returnCursor != null) {
            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            returnCursor.moveToFirst();
            name = returnCursor.getString(nameIndex);
            returnCursor.close();
        }
        return name;
    }
}
