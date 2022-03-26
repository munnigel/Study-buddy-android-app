package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class EditProfilePage extends AppCompatActivity implements View.OnClickListener {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_page);

        image = findViewById(R.id.imageView);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseProfilePic();
            }
        });

        Button confirmEdit = (Button) findViewById(R.id.confirmButton);
        confirmEdit.setOnClickListener(this);

        ImageView back = findViewById(R.id.backButton);
        back.setOnClickListener(this);
    }

        private void chooseProfilePic() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View dialogview = inflater.inflate(R.layout.add_picture_alert, null);
            builder.setCancelable(false);
            builder.setView(dialogview);

            ImageView takePic = dialogview.findViewById(R.id.takePic);
            ImageView chooseGallery = dialogview.findViewById(R.id.chooseGallery);

            AlertDialog alertDialogProfilePicture = builder.create();
            alertDialogProfilePicture.show();

            takePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkAndRequestPermission()) {
                        takePicFromCamera();
                        alertDialogProfilePicture.cancel();
                    }
                }
            });

            chooseGallery.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    takePicFromGallery();
                    alertDialogProfilePicture.cancel();
                }
            }));



        }
    private void takePicFromGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }

    private void takePicFromCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImageUri = data.getData();
                    image.setImageURI(selectedImageUri);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmapImage = (Bitmap) bundle.get("data");
                    image.setImageBitmap(bitmapImage);
                }
        }
    }
    private boolean checkAndRequestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int cameraPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (cameraPermission == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 20);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 20 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }
        else {
            Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.confirmButton:
                startActivity(new Intent(EditProfilePage.this, ProfilePage.class));
                break;
<<<<<<< HEAD
=======
            case R.id.backButton:
                startActivity(new Intent(EditProfilePage.this, profilePage.class));
                break;

>>>>>>> b1a2cee (Added modules in profile page and camera and gallery for edit profile page)
        }
    }
}