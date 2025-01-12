package com.danu.gehu_erp;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import okhttp3.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AppwriteUploader {

    private static final String ENDPOINT = "https://cloud.appwrite.io/v1";
    private static final String PROJECT_ID = "677e273b00160e765ea7";
    private static final String BUCKET_ID = "677e275f001bb892a579";
    private static final String API_KEY = "standard_fbb9ab333cbcb3fce8ca05716b06ed20129973c481c33409fdeed081a08c016f279948d20eea50c066925ed7ef009abedc75dbda769c32568744699d5c69781bcdd25dfe9577f16effcfd929afef2702950c3280628f80094d60228a60097c8e3f22347077177a8f3659b7a88ece0f4f5c6351eddc4290f1302cc27f0404a38f";
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/octet-stream");

    // OkHttpClient instance
    private OkHttpClient client;

    public AppwriteUploader() {
        client = new OkHttpClient();
    }

    // Method to upload the file
    public void uploadFileToAppwrite(Uri fileUri, Context context,String UID) {
        try {
            // Convert URI to File
            File file = getFileFromUri(fileUri, context);

            // Build Multipart RequestBody
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("fileId", UID)
                    .addFormDataPart("file", file.getName(), RequestBody.create(file, MEDIA_TYPE))
                    .build();

            // Create the HTTP request
            Request request = new Request.Builder()
                    .url(ENDPOINT + "/storage/buckets/" + BUCKET_ID + "/files")
                    .header("X-Appwrite-Project", PROJECT_ID)
                    .header("X-Appwrite-Key", API_KEY)
                    .post(requestBody)
                    .build();

            // Send the request asynchronously
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("Appwrite Upload", "File upload failed", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        Log.d("Appwrite Upload", "Response: " + responseBody);
                    } else {
                        Log.e("Appwrite Upload", "Upload failed: " + response.code());
                    }
                }
            });

        } catch (IOException e) {
            Log.e("Appwrite Upload", "Error while uploading file", e);
        }
    }

    // Convert URI to File using ContentResolver
    private File getFileFromUri(Uri uri, Context context) throws IOException {
        ContentResolver contentResolver = context.getContentResolver();
        InputStream inputStream = contentResolver.openInputStream(uri);
        if (inputStream == null) {
            throw new IOException("Failed to open InputStream from URI: " + uri);
        }

        // Create a temporary file to write the InputStream content to
        File tempFile = File.createTempFile("upload_", ".png", context.getCacheDir());
        try (OutputStream outputStream = context.getContentResolver().openOutputStream(Uri.fromFile(tempFile))) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } finally {
            inputStream.close();
        }

        return tempFile;
    }
}