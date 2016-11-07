package com.example.user.bustacallfordriver.model;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by user on 2016-11-08.
 */
public class BitmapUtil {
    ///비트맵 리사이징
    static public Bitmap resizeBitmap(Context context, Uri imgUri, int maxResolution)
    {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            // This is the part that changed
            InputStream inputStream = context.getApplicationContext().getContentResolver().openInputStream(imgUri);
            BitmapFactory.decodeStream(inputStream,new Rect(),options);

            //bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imgUri);

            ExifInterface exif = new ExifInterface(getRealPathFromURI(imgUri,context));
            int exifOrientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int exifDegree = exifOrientationToDegrees(exifOrientation);
            bitmap = rotate(bitmap, exifDegree);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int newWidth = width;
            int newHeight = height;
            float rate = 0.0f;
            if(width > height)
            {
                if(maxResolution < width)
                {
                    rate = maxResolution / (float) width;
                    newHeight = (int) (height * rate);
                    newWidth = maxResolution;
                }
            }
            else
            {
                if(maxResolution < height)
                {
                    rate = maxResolution / (float) height;
                    newWidth = (int) (width * rate);
                    newHeight = maxResolution;
                }
            }
            bitmap= Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public static int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }
    static public Bitmap rotate(Bitmap bitmap, int degrees)
    {
        if(degrees != 0 && bitmap != null)
        {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2,
                    (float) bitmap.getHeight() / 2);

            try
            {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(), m, true);
                if(bitmap != converted)
                {
                    bitmap.recycle();
                    bitmap = converted;
                }
            }
            catch(OutOfMemoryError ex)
            {
                // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
            }
        }
        return bitmap;
    }

    static public String getRealPathFromURI(Uri contentUri,Context context) {
        String[] proj = {MediaStore.Images.Media.DATA};

        CursorLoader cursorLoader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}