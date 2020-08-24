package com.housie.hosienumber;

import android.content.DialogInterface;

public class Utils {

    public interface ConfirmationDialogListner {

        void onclickYes();
        void onclickNo();
    }


    public static void takeConfirmation(androidx.appcompat.app.AlertDialog alertdialog, String content, final ConfirmationDialogListner listner) {

        alertdialog.setTitle(content);

        alertdialog.setButton(DialogInterface.BUTTON_POSITIVE, alertdialog.getContext().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listner.onclickYes();
            }
        });

        alertdialog.setButton(DialogInterface.BUTTON_NEGATIVE, alertdialog.getContext().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listner.onclickNo();
            }
        });

        alertdialog.show();
    }
}
