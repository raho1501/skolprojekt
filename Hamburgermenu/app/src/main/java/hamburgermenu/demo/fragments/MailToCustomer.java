package hamburgermenu.demo.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.markus.hamburgermenu.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static hamburgermenu.demo.fragments.Kamera.CAM_REQUEST;

/**
 * Created by Limeman on 10/20/2017.
 */

public class MailToCustomer extends Fragment {

    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap previewedPicture;
    private ArrayList<String> arguments;
    private Uri uri;
    private File image_file;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        arguments = getArguments().getStringArrayList("key");
        return inflater.inflate(R.layout.mail_to_customer, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Button takePhoto = getView().findViewById(R.id.takePhoto);
        Button selectPhoto = getView().findViewById(R.id.selectImage);
        final Button sendEmail = getView().findViewById(R.id.sendEmail);

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivityForResult(takePictureIntent,CAM_REQUEST);
                }
            }
        });
        selectPhoto.setOnClickListener(new View.OnClickListener() {
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

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendEmail(getRealPathFromURI_API19(getContext(), uri));

                Toast.makeText(getContext(), "Skickat!", Toast.LENGTH_SHORT).show();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame, new Veckoschema()).commit();
            }
        });
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
                //image_file = new File(uri.getPath());

                image_file = new File(getRealPathFromURI_API19(getContext(), uri));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            uri = data.getData();
            ImageView imageView = (ImageView) getView().findViewById(R.id.imageView);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            File picImage = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "CameraFile");

            if(!picImage.exists())
            {
                picImage.mkdir();
            }

            File image_file = new File(picImage,"cam_image.jpg");
            //File image = new File();
        }
    }

    private void sendEmail(String filePath){

        TextView subject = (TextView)getView().findViewById(R.id.subject);
        TextView body = (TextView)getView().findViewById(R.id.mainBody);

        String[] TO = {arguments.get(2)};
        String[] CC = {"majo1412@student.miun.se"};
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("application/image");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT,  "Hej " + arguments.get(0) + " " + arguments.get(1) + ",\n" + body.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(filePath));

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public static String getRealPathFromURI_API19(Context context, Uri uri){
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{ id }, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }


}
