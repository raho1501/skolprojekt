package hamburgermenu.demo.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.markus.hamburgermenu.R;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Martin/fredrik on 2017-10-12.
 */

public class Kamera extends Fragment{


    Button button;
    ImageView imageView;
    static final int CAM_REQUEST = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.kamera, container, false);

        button = (Button)rootView.findViewById(R.id.button);
        imageView = (ImageView)rootView.findViewById(R.id.image_view);

        button.setOnClickListener(new View.OnClickListener(){
            @Override


            public void onClick(View v){

                //////////////////////////////////////////////////////////

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivityForResult(takePictureIntent,CAM_REQUEST);
                }

            }
        });


        return rootView;
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
        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }



}
