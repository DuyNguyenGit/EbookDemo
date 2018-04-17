package com.example.rct03.ebook_readerdm.util;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import okhttp3.Response;
import okhttp3.ResponseBody;

import static android.content.ContentValues.TAG;

public class FileUtils {
    public static void writeResponseBodyToDisk(ResponseBody body) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                boolean writtenToDisk = writeToDisk(body);

                Log.d(TAG, "file download was a success? " + writtenToDisk);
                return null;
            }
        }.execute();

    }

    private static boolean writeToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "content.opf");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }


    public static void getSingleFile(retrofit2.Response<ResponseBody> responseBody) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                boolean decryptSuccess = decryptSingleFile(responseBody);

                Log.e(TAG, "decrypt file was a success? " + decryptSuccess);
                return null;
            }
        }.execute();
    }

    private static boolean decryptSingleFile(retrofit2.Response<ResponseBody> responseBody) {
        Log.e(TAG, "decryptSingleFile: >>>" + new Gson().toJson(responseBody));

        String encryptedContentKey = responseBody.headers().get("X-CONTENT-KEY");
        Log.e(TAG, "decryptSingleFile: >>> encryptedContentKey = " + encryptedContentKey);
        String decryptedUserKey_ContentKey = null;
        try {
            decryptedUserKey_ContentKey = CryptoHandler.getInstance().decrypt(encryptedContentKey);
            Log.e(TAG, "decryptSingleFile: >>>decryptedUserKey_ContentKey = " + decryptedUserKey_ContentKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
