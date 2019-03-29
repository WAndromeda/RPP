package com.example.lab2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/*
 * - - - - - - - - - - - - - - - - БОЛЬШЕ НЕ ИСПОЛЬЗУЕТСЯ - - - - - - - - - - - - - - - - - -
 *
 * Класс DownloadImageTask  - для скачивания изображения по ссылке
 * Класс Async используется для асинхронизации потока, чтобы он выполнялся отдельно
 *
 * Первый параметр передаётся в метод doInBackGround
 * Второй параметр используется в методе onProgressUpdate (передаётся туда в качестве параметра)
 * Третий параметр является тип возвращаемого значения в методу doInBackground, а также передаётся в метод onPostExecute
 *
 * Для начала выполнения всех задачах в методах вызывается метод execute, наследуемый от AsyncTask
 *
 * - - - - - - - - - - - - - - - - БОЛЬШЕ НЕ ИСПОЛЬЗУЕТСЯ - - - - - - - - - - - - - - - - - -
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap icon = null;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            icon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Ошибка изображения", e.getMessage());
            e.printStackTrace();
        }
        return icon;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }

    public ImageView getBmImage() {
        return bmImage;
    }
}


