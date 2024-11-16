package fpoly.account.asm_adr2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import fpoly.account.asm_adr2.R;
import fpoly.account.asm_adr2.fragment.GioiThieuFragment;
import fpoly.account.asm_adr2.fragment.QuanLySanPhamFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new QuanLySanPhamFragment()).commit();
            toolbar.setTitle("Quản lý sản phẩm");
            navigationView.setCheckedItem(R.id.nav_quan_ly_san_pham);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_quan_ly_san_pham) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new QuanLySanPhamFragment()).commit();
            toolbar.setTitle("Quản lý sản phẩm");
        } else if (itemId == R.id.nav_gioi_thieu) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                    new GioiThieuFragment()).commit();
            toolbar.setTitle("Giới thiệu");
        } else if (itemId == R.id.nav_dang_xuat) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }
}