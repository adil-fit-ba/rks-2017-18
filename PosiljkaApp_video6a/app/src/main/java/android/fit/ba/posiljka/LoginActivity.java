package android.fit.ba.posiljka;

import android.content.Intent;
import android.fit.ba.posiljka.helper.MySession;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnLoginClick();
            }
        });
    }

    private void do_btnLoginClick() {

        String strUsername = txtUsername.getText().toString();
        String strPassword = txtPassword.getText().toString();

        KorisnikVM x = Storage.LoginCheck(strUsername, strPassword);

        if (x==null)
        {
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Pogrešan usernam/password", Snackbar.LENGTH_LONG).show();
        }
        else
        {
            MySession.setKorisnik( x);
            startActivity(new Intent(this, GlavniActivity.class));
        }
    }
}
