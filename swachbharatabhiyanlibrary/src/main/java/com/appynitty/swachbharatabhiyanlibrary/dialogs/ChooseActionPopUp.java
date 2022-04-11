package com.appynitty.swachbharatabhiyanlibrary.dialogs;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.appynitty.swachbharatabhiyanlibrary.R;
import com.appynitty.swachbharatabhiyanlibrary.utils.AUtils;

import java.io.File;
import java.io.IOException;

public class ChooseActionPopUp extends Dialog {

    private static final String TAG = "ChooseActionPopUp";
    private ChooseActionPopUpDialogListener chooseActionPopUpDialogListener;

    private Button addDetailsBtn, skipBtn;
    ImageView ivQR_image;
    private Object data;
    private String mId, mPath;
    public static final int SKIP_BUTTON_CLICKED = 0;
    public static final int ADD_DETAILS_BUTTON_CLICKED = 1;
    Context mContext;
    Uri uri;
    Bitmap bmp = null;
    Bitmap result = null;
    Bitmap result1 = null;

    boolean isImageFitToScreen;

    public ChooseActionPopUp(Context context) {
        super(context);
        mContext = context;
    }

    public void setChooseActionPopUpDialogListener(ChooseActionPopUpDialogListener chooseActionPopUpDialogListener) {
        this.chooseActionPopUpDialogListener = chooseActionPopUpDialogListener;
    }

    public void setData(String mId, String mPath) {
        this.mId = mId;
        this.mPath = mPath;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_choose_option);

        initComponents();
    }

    private void initComponents() {

        generateID();
        registerEvents();
        initData();
    }

    private void generateID() {
        skipBtn = findViewById(R.id.btn_skip);
        addDetailsBtn = findViewById(R.id.btn_add_details);
        ivQR_image = findViewById(R.id.imgQRimg);
    }

    private void initData() {

        if (mPath != null && !mPath.trim().isEmpty()) {
            uri = Uri.fromFile(new File(mPath));
            Bitmap btmap = AUtils.writeOnImage(mContext, AUtils.getDateAndTime(), mId, mPath);
            ivQR_image.setImageBitmap(btmap);
        }

    }

    private void registerEvents() {

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dismissPopup();
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseActionPopUpDialogListener.onChooseActionPopUpDismissed(mId, SKIP_BUTTON_CLICKED);
                dismissPopup();
            }
        });

        addDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseActionPopUpDialogListener.onChooseActionPopUpDismissed(mId, ADD_DETAILS_BUTTON_CLICKED);
                dismissPopup();
            }
        });

        ivQR_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    ivQR_image.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    ivQR_image.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    ivQR_image.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    ivQR_image.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

    }

    public Bitmap loadFromUri(Uri photoUri) {
        Bitmap image = null;
        try {
            // check version of Android on device
            if (Build.VERSION.SDK_INT > 27) {
                // on newer versions of Android, use the new decodeBitmap method
                ImageDecoder.Source source = ImageDecoder.createSource(mContext.getContentResolver(), photoUri);
                image = ImageDecoder.decodeBitmap(source);
            } else {
                // support older versions of Android by using getBitmap
                image = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), photoUri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private void dismissPopup() {
        this.dismiss();
    }

    public interface ChooseActionPopUpDialogListener {
        void onChooseActionPopUpDismissed(String mId, int actionType);
    }

}
