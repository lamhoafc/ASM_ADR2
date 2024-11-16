package fpoly.account.asm_adr2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import fpoly.account.asm_adr2.R;
import fpoly.account.asm_adr2.dao.UserDAO;
import fpoly.account.asm_adr2.helper.DbHelper;
import fpoly.account.asm_adr2.model.UserModel;

public class SignUpActivity extends AppCompatActivity {
    private AppCompatButton btnSignUp;
    private AppCompatTextView tvSignIn;
    private AppCompatEditText edtUsername;
    private AppCompatEditText edtPassword;
    private AppCompatEditText edtCFPassword;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        userDAO = new UserDAO(this);

        initView();
        clickSignUp();
        clickSignIn();
    }

    private void clickSignIn() {
        tvSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void clickSignUp() {
        btnSignUp.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            String cfPassword = edtCFPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty() || cfPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 8 && cfPassword.length() < 8) {
                Toast.makeText(this, "Mật khẩu phải có ít nhất 8 ký tự", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(cfPassword)) {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            } else {
                if (userDAO.checkUserExists(username)) {
                    Toast.makeText(this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    UserModel user = new UserModel(username, password);
                    long result = userDAO.insertUser(user);

                    if (result != -1) {
                        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Lỗi đăng ký", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initView() {
        btnSignUp = findViewById(R.id.btnSignUp1);
        tvSignIn = findViewById(R.id.tvSignIn);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtCFPassword = findViewById(R.id.edtCFPassword);
    }
}