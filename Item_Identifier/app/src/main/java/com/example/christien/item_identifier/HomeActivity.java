package com.example.christien.item_identifier;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class HomeActivity extends AppCompatActivity {

    private Button camButton;
    private Button logoutButton;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        camButton = (Button) findViewById(R.id.cameraButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);
        image = (ImageView) findViewById(R.id.picture);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);


        camButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Accessing Camera", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

            }
        });


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(HomeActivity.this);
                alertBuilder.setMessage("Are you sure you want to log out?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(HomeActivity.this, "Logging out", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                                startActivity(intent);
                                FirebaseAuth.getInstance().signOut();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = alertBuilder.create();
                //alert.setTitle("");
                alert.show();
            }
        });


    }

    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        bitmap = (Bitmap) data.getExtras().get("data");
        image.setImageBitmap(bitmap);
        try { startClient(bitmap); }
        catch (Exception e) {e.printStackTrace();}
    }

    public void startClient(Bitmap bmp) throws Exception{


        Toast.makeText(HomeActivity.this, "Inside now!!!!!", Toast.LENGTH_SHORT).show();


        Socket socket = new Socket("10.150.4.80", 8080);
        Log.d("CLIENT", "Socket Created");

        OutputStream outputStream = socket.getOutputStream();
        Log.d("CLIENT", "Output stream connected");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
//
//        outputStream.write(byteArray);
//        outputStream.write(stream.toByteArray());

        byte[] size = ByteBuffer.allocate(64).putInt(stream.size()).array();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        outputStream.write(size);
        outputStream.write(stream.toByteArray());
        outputStream.flush();

        socket.close();
    }
}
