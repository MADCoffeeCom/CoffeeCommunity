//package com.example.coffeecom.fragment;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.database.ContentObserver;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.fragment.app.Fragment;
//
//import android.os.ParcelFileDescriptor;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.example.coffeecom.R;
//import com.example.coffeecom.helper.MyAPI;
//import com.example.coffeecom.helper.UploadRequestBody;
//import com.example.coffeecom.model.UploadResponse;
//import android.app.Activity;
//import android.content.ContentResolver;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.OpenableColumns;
//import androidx.appcompat.app.AppCompatActivity;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.RequestBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import java.awt.image.BufferedImage;
//import java.awt.image.RenderedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.Socket;
//
//import javax.imageio.ImageIO;
//
//
//public class ImageUploaderFragment extends Fragment implements UploadRequestBody.UploadCallback{
//
//    private static final int REQUEST_CODE_PICK_IMAGE = 101;
//    private Uri selectedImageUri = null;
//    private ImageView image_view;
//    private ProgressBar progress_bar;
//    private Button button_upload;
//    private ConstraintLayout layout_root;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_image_uploader, container, false);
//
//        button_upload = view.findViewById(R.id.button_upload);
//        progress_bar = view.findViewById(R.id.progress_bar);
//        image_view = view.findViewById(R.id.image_view);
//        layout_root = view.findViewById(R.id.layout_root);
//        image_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openImageChooser();
//            }
//        });
//
//
//
//        onBind();
//        return view;
//    }
//
//    private void onBind(){
//        button_upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    uploadImage();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    private void openImageChooser() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        String[] mimeTypes = {"image/jpeg", "image/jpg"};
//        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
//        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            switch (requestCode) {
//                case REQUEST_CODE_PICK_IMAGE:
//                    selectedImageUri = data.getData();
//                    image_view.setImageURI(selectedImageUri);
//                    break;
//            }
//        }
//    }
//
//    private void uploadImage() throws IOException {
//        if (selectedImageUri == null) {
//            Toast.makeText(getContext(), "Select an Image First", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        ContentResolver contentResolver = getContext().getContentResolver();
//        ParcelFileDescriptor parcelFileDescriptor = contentResolver.openFileDescriptor(selectedImageUri, "r");
//        if (parcelFileDescriptor == null) {
//            return;
//        }
//
//        FileInputStream inputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
//        File file = new File(getContext().getCacheDir(), getFileName(contentResolver, selectedImageUri));
//        FileOutputStream outputStream = new FileOutputStream(file);
//        inputStream.equals(outputStream);
//
//        Socket soc;
//        BufferedImage img = null;
////        RenderedImage imgg =
////        soc=new Socket("localhost",3306);
//        System.out.println("Client is running. ");
//
//        try {
//            System.out.println("Reading image from disk. ");
//            img = ImageIO.read(file);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//            ImageIO.write(img, "jpg", baos);
//            baos.flush();
//
//            byte[] bytes = baos.toByteArray();
//            baos.close();
//
//            System.out.println("Sending image to server. ");
//
//            OutputStream out = soc.getOutputStream();
//            DataOutputStream dos = new DataOutputStream(out);
//
//            dos.writeInt(bytes.length);
//            dos.write(bytes, 0, bytes.length);
//
//            System.out.println("Image sent to server. ");
//
//            dos.close();
//            out.close();
//
//        } catch (Exception e) {
//            System.out.println("Exception: " + e.getMessage());
//            soc.close();
//        }
//        soc.close();
//    }
//
////        progress_bar.setProgress(0);
////        UploadRequestBody body = new UploadRequestBody(file, "image", this);
////        MyAPI.uploadImage(
////                MultipartBody.Part.createFormData(
////                        "image",
////                        file.getName(),
////                        body
////                ),
////                RequestBody.create(MediaType.parse("multipart/form-data"), "json")
////        )
////                .enqueue(new Callback<UploadResponse>() {
////            @Override
////            public void onFailure(Call<UploadResponse> call, Throwable t) {
////                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
////                progress_bar.setProgress(0);
////            }
////
////            @Override
////            public void onResponse(
////                    Call<UploadResponse> call,
////                    Response<UploadResponse> response
////            ) {
////                UploadResponse uploadResponse = response.body();
////                if (uploadResponse != null) {
////                    Toast.makeText(getContext(), uploadResponse.getMessage(), Toast.LENGTH_SHORT).show();
////                    progress_bar.setProgress(100);
////                }
////            }
////        });
//    }
//
//    public static String getFileName(ContentResolver contentResolver, Uri fileUri) {
//        String name = "";
//        Cursor returnCursor = contentResolver.query(fileUri, null, null, null, null);
//        if (returnCursor != null) {
//            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//            returnCursor.moveToFirst();
//            name = returnCursor.getString(nameIndex);
//            returnCursor.close();
//        }
//        return name;
//    }
//
//
//    @Override
//    public void onProgressUpdate(int percentage) {
//
//    }
//}