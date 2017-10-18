package hamburgermenu.demo.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Markus on 2017-10-11.
 */

public class AddGuitarr extends Fragment {
    public Button btn;
    public Button byteBtn;
    public Button bytePicBtn;
    public TextView resTV;
    private int PICK_IMAGE_REQUEST = 1;
    private String byteData;
    private byte[] Data;
    private Bitmap previewedPicture;
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

            Uri uri = data.getData();
            System.out.println("\n \n \n"+uri);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                previewedPicture = bitmap;
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) getView().findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        return stream.toByteArray();
    }
}