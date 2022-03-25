package com.appynitty.swachbharatabhiyanlibrary.dialogs;


import static android.graphics.Bitmap.Config.ARGB_8888;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.appynitty.swachbharatabhiyanlibrary.R;
import com.appynitty.swachbharatabhiyanlibrary.utils.AUtils;

public class ChooseActionPopUp extends Dialog {

    private static final String TAG = "ChooseActionPopUp";
    private ChooseActionPopUpDialogListener chooseActionPopUpDialogListener;

    private Button addDetailsBtn, skipBtn;
    ImageView ivQR_image;
    private Object data;
    private String mId, mPath;
    public static final int SKIP_BUTTON_CLICKED = 0;
    public static final int ADD_DETAILS_BUTTON_CLICKED = 1;

    Bitmap bmp = null;
    Bitmap result = null;
    Bitmap result1 = null;

    boolean isImageFitToScreen;

    public ChooseActionPopUp(Context context) {
        super(context);
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

        bmp = AUtils.writeOnImage(AUtils.getDateAndTime(), mId, mPath);
        Bitmap shadowImage32 = bmp.copy(ARGB_8888, true);
        ivQR_image.setImageBitmap(shadowImage32);


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


    /* private void setPic() {
         // Get the dimensions of the View
         int targetW = ivQR_image.getWidth();
         int targetH = ivQR_image.getHeight();

         // Get the dimensions of the bitmap
         BitmapFactory.Options bmOptions = new BitmapFactory.Options();
         bmOptions.inJustDecodeBounds = true;
         BitmapFactory.decodeFile(mPath, bmOptions);
         int photoW = bmOptions.outWidth;
         int photoH = bmOptions.outHeight;

         // Determine how much to scale down the image
         int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

         // Decode the image file into a Bitmap sized to fill the View
         bmOptions.inJustDecodeBounds = false;
         bmOptions.inSampleSize = scaleFactor;
         bmOptions.inPurgeable = true;

         //Bitmap bmp = AUtils.writeOnImage(AUtils.getDateAndTime(), mId, mPath);
         Bitmap bitmap = BitmapFactory.decodeFile(mPath, bmOptions);
         ivQR_image.setImageBitmap(bitmap);
     }
 */
    private void dismissPopup() {
        this.dismiss();
    }

    public interface ChooseActionPopUpDialogListener {
        void onChooseActionPopUpDismissed(String mId, int actionType);
    }

}
