package se.miun.raho1501.imagetest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    public Button btn;
    public Button byteBtn;
    public TextView resTV;
    private int PICK_IMAGE_REQUEST = 1;

    private Bitmap previewedPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resTV = (TextView)findViewById(R.id.resultTextView);
        btn = (Button)findViewById(R.id.btn);
        byteBtn = (Button)findViewById(R.id.btn_bytearray);

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

        byteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] bytePic = getBytesFromBitmap(previewedPicture);
                String res = new String(bytePic, StandardCharsets.UTF_8);
                System.out.println(res);
                StringBuilder s = new StringBuilder();
                int line = 0;
                for(int i = 0; i < bytePic.length; i++)
                {
                    s.append((char)bytePic[i]);
                    if(line%45 == 0)
                    {
                        s.append("\n");
                    }
                    line++;
                }


                resTV.setText(bytePic.toString() +"\n \n"+s.toString());
            }
        });

        resTV.setMovementMethod(new ScrollingMovementMethod());


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                previewedPicture = bitmap;
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }
}
