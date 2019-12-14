package com.thongnguyen.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// This Dialog Fragment is very similar to DatePickerFragment, but it works with time instead of date
public class ImageZoomFragment extends DialogFragment {
    public static final String EXTRA_DATE = "com.thongnguyen.criminalIntent.date";
    private static final String ARG_FILE = "file";
    private ImageView mZoomedImage;

    public static ImageZoomFragment newInstance(File file) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_FILE, file);
        ImageZoomFragment fragment = new ImageZoomFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_image_zoom, null);

        File photoFile = (File) getArguments().getSerializable(ARG_FILE);
        Bitmap bm = PictureUtils.getScaledBitmap(photoFile.getPath(), getActivity());
        bm = PictureUtils.scaleUpBitmap(bm);
        mZoomedImage = (ImageView) v.findViewById(R.id.image_zoom);
        mZoomedImage.setImageBitmap(bm);
        return new AlertDialog.Builder(getActivity())
                        .setView(v)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Nothing here
                            }
                        })
                        .create();
    }

}
