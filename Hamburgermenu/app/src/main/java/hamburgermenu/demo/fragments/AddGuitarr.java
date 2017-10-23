package hamburgermenu.demo.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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

                    final String stringTitle = title.getText().toString();
                    final String stringPrice = price.getText().toString();
                    final String stringInfo = info.getText().toString();

                    //InputStream inputPic = new ByteArrayInputStream(bytePic);

                    final Shop shopData = new Shop();


                    final RetrofitWrapper rw = new RetrofitWrapper();


                    System.out.println(image_file.getName());

                    rw.uploadImage(image_file, new RetroCallback<String>() {
                        @Override
                        public void onResponse(String entity) {
                            shopData.setTitle(stringTitle);
                            shopData.setPrice((Integer.parseInt(stringPrice)));
                            shopData.setInfo(stringInfo);
                            shopData.setImageURL(entity);
                            rw.postShopTest(shopData);
                        }
                    });


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
                image_file = new File(uri.getPath());

                image_file = new File(getRealPathFromURI_API19(uri));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            ImageView imageView = (ImageView) getView().findViewById(R.id.imageView);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            File picImage = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "CameraFile.jpg");
            FileOutputStream stream = null;
            try {
                stream = new FileOutputStream(picImage);
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            imageView.setImageBitmap(imageBitmap);

            if (!picImage.exists()) {
                picImage.mkdir();
            }

            //String fileName = getRealPathFromURI_API19());
            image_file = picImage;
            System.out.println(image_file.getPath());
        }
    }
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        return stream.toByteArray();
    }
    public String getRealPathFromURI_API19(Uri uri){
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{ id }, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }
}