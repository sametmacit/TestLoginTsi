package com.example.fsm.testlogintsi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://127.0.0.1";
    EditText et_name, et_surname, et_phone;
    Button btn_login;
    ApiInterface mInterfaceService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mInterfaceService = retrofit.create(ApiInterface.class);

    }

    private void initViews() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_surname = (EditText) findViewById(R.id.et_surname);
        et_phone = (EditText) findViewById(R.id.et_phone);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        setError();

        String name = et_name.getText().toString();
        String surname = et_surname.getText().toString();
        String phone = et_phone.getText().toString();

        int err = 0;
        if (!Validation.validateFields(name)) {

            err++;
            et_name.setError("Name should be valid !");
        }

        if (!Validation.validateFields(surname)) {

            err++;
            et_surname.setError("Password should not be empty !");
        }

        if (!Validation.validateFields(phone)) {

            err++;
            et_surname.setError("Password should not be empty !");
        }

        if (err == 0) {
            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setPhone(phone);

            mInterfaceService.createUser(user);


        } else {
            Toast.makeText(this, "Nereye Böyle", Toast.LENGTH_SHORT).show();
        }

    }


    private void setError() {

        et_name.setError(null);
        et_surname.setError(null);
        et_phone.setError(null);
    }
}
