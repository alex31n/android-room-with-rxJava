package com.example.androidroom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.androidroom.utils.Constant;
import com.example.androidroom.R;

public class AddEditUserActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtAddress;
    private EditText edtEmail;
    private EditText edtPhone;
    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        edtName = findViewById(R.id.edt_name);
        edtAddress = findViewById(R.id.edt_address);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);

        Intent intent = getIntent();

        if (intent.hasExtra(Constant.EXTRA_ID)) {
            setTitle("Edit Note");
            id = getIntent().getIntExtra(Constant.EXTRA_ID, -1);
            edtName.setText(intent.getStringExtra(Constant.EXTRA_NAME));
            edtAddress.setText(intent.getStringExtra(Constant.EXTRA_ADDRESS));
            edtEmail.setText(intent.getStringExtra(Constant.EXTRA_EMAIL));
            edtPhone.setText(intent.getStringExtra(Constant.EXTRA_PHONE));
        } else {
            setTitle("Add User");
        }
    }

    public void onClick(View view) {
        saveUser();
    }


    public void saveUser() {
        String name = edtName.getText().toString();
        String address = edtAddress.getText().toString();
        String email = edtEmail.getText().toString();
        String phone = edtPhone.getText().toString();

        if (name.trim().isEmpty()) {
            edtName.setError("Empty Name");
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(Constant.EXTRA_NAME, name);
        intent.putExtra(Constant.EXTRA_ADDRESS, address);
        intent.putExtra(Constant.EXTRA_EMAIL, email);
        intent.putExtra(Constant.EXTRA_PHONE, phone);


        if (id != -1) {
            intent.putExtra(Constant.EXTRA_ID, id);
        }

        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
