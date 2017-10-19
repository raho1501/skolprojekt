package hamburgermenu.demo.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.markus.hamburgermenu.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;
import static hamburgermenu.demo.fragments.Kamera.CAM_REQUEST;

/**
 * Created by Markus on 2017-10-11.
 */

public class AddGuitarr extends Fragment {
    public Button btn;
    public Button btn2;
    public Button submitBtn;
    public Button byteBtn;
    public Button bytePicBtn;
    public TextView resTV;
    private int PICK_IMAGE_REQUEST = 1;
    File image_file;
    private String byteData;
    private byte[] Data;
    private Bitmap previewedPicture;
    private Uri uri;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.addguitarr, container, false);

        return rootView;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        btn = (Button)getView().findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////
        btn2 = (Button)getView().findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivityForResult(takePictureIntent,CAM_REQUEST);
                }
            }
        });

        submitBtn = (Button)getView().findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image_file != null)
                {
                    TextView title = (TextView) getView().findViewById(R.id.guitarTitle);
                    TextView price = (TextView) getView().findViewById(R.id.guitarPrice);
                    TextView info = (TextView) getView().findViewById(R.id.guitarDescription);

                    //byte[] bytePic = getBytesFromBitmap(previewedPicture);

                    String stringTitle = title.getText().toString();
                    String stringPrice = price.getText().toString();
                    String stringInfo = info.getText().toString();

                    //InputStream inputPic = new ByteArrayInputStream(bytePic);

                    Shop shopData = new Shop();


                    RetrofitWrapper rw = new RetrofitWrapper();


                    System.out.println(image_file.getName());

                    shopData.setTitle(stringTitle);
                    shopData.setPrice((Integer.parseInt(stringPrice)));
                    shopData.setInfo(stringInfo);
                    shopData.setImageURL(image_file.getName());

                    //rw.postShopTest(shopData);
                    rw.uploadImage(image_file);
                }
                else
                {
                    //TODO gör något här om användaren inte har lagt in en bild.
                }
            }
        });






        //bitmap to byteArray button not used for now
        /*
        byteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] bytePic = getBytesFromBitmap(previewedPicture);
                String res = new String(bytePic, StandardCharsets.UTF_8);
                System.out.println(res);
                StringBuilder s = new StringBuilder();
                Data = bytePic;
                for(int i = 0; i < bytePic.length; i++)
                {
                    s.append((char)bytePic[i]);
                }
                byteData = s.toString();

                resTV.setText(bytePic.toString() +"\n \n"+s.toString());
            }
        });

        resTV.setMovementMethod(new ScrollingMovementMethod());
        bytePicBtn = (Button)findViewById(R.id.btnByteToPic);
        bytePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ByteToImageView.class);


                i.putExtra("byteData",Data);
                startActivity(i);
            }
        });
        */

    }


    private Uri getBitMapUri(Bitmap bitmap){
        String path = MediaStore.Images.Media.insertImage(getActivity().getApplicationContext().getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }

    private File getFile(){

        File folder = new File("sdcard/camera_app");

        if(!folder.exists())
        {
            folder.mkdir();
        }

        File image_file = new File(folder,"cam_image.jpg");
        return image_file;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();
            System.out.println("\n \n \n"+uri);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                previewedPicture = bitmap;

                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) getView().findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
                loadFileFromUri(uri);
                image_file = new File(uri.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            ImageView imageView = (ImageView) getView().findViewById(R.id.imageView);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            File picImage = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "CameraFile");

            if(!picImage.exists())
            {
                picImage.mkdir();
            }

            image_file = new File(picImage,"cam_image.jpg");
            //File image = new File();
        }
    }
    public void loadFileFromUri(Uri uri)
    {
        System.out.println(uri.toString());
        String fileName = new String();
        if (uri.getScheme().equals("file")) {
            fileName = uri.getLastPathSegment();
        } else {
            Cursor cursor = null;
            try {
                cursor = getActivity().getContentResolver().query(uri, new String[]{
                        MediaStore.Images.ImageColumns.DISPLAY_NAME
                }, null, null, null);

                if (cursor != null && cursor.moveToFirst()) {
                    fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME));

                }
            } finally {

                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        return stream.toByteArray();
    }
}