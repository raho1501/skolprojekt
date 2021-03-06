package com.example.posti.kameratest;


 // https://pastebin.com/grx6DMfy

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;


public class MainActivity extends Activity {



    Button button;
    ImageView imageView;
    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        setContentView(R.layout.test);

        button = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.image_view);

        button.setOnClickListener(new View.OnClickListener(){
            @Override


            public void onClick(View v){








                //////////////////////////////////////////////////////////

               Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(takePictureIntent,CAM_REQUEST);
                }

            }
        });
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }


    // DFHGSFDHSFTGHSFTGHSTFRGJHSRFTJARTJARTJARTJARTJARZTHAZRTHGSRTGHSRTHZARTHAZRTGHSZRTH




}