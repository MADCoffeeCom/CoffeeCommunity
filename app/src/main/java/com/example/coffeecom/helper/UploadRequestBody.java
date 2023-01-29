package com.example.coffeecom.helper;

import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class UploadRequestBody extends RequestBody {

    private File file;
    private String contentType;
    private UploadCallback callback;

    public UploadRequestBody(File file, String contentType, UploadCallback callback) {
        this.file = file;
        this.contentType = contentType;
        this.callback = callback;
    }

    @Override
    public MediaType contentType() {
        return MediaType.parse(contentType + "/*");
    }

    @Override
    public long contentLength() {
        return file.length();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        long length = file.length();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        FileInputStream fileInputStream = new FileInputStream(file);
        long uploaded = 0L;
        try {
            int read;
            Handler handler = new Handler(Looper.getMainLooper());
            while ((read = fileInputStream.read(buffer)) != -1) {
                handler.post(new ProgressUpdater(uploaded, length));
                uploaded += read;
                sink.write(buffer, 0, read);
            }
        } finally {
            fileInputStream.close();
        }
    }

    public interface UploadCallback {
        void onProgressUpdate(int percentage);
    }

    class ProgressUpdater implements Runnable {

        private long uploaded;
        private long total;

        public ProgressUpdater(long uploaded, long total) {
            this.uploaded = uploaded;
            this.total = total;
        }

        @Override
        public void run() {
            callback.onProgressUpdate((int) (100 * uploaded / total));
        }
    }

    private static final int DEFAULT_BUFFER_SIZE = 2048;
}
