package fpoly.account.asm_adr2.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fpoly.account.asm_adr2.model.ProductModel;
import fpoly.account.asm_adr2.R;
import fpoly.account.asm_adr2.adapter.ProductAdapter;

public class QuanLySanPhamFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<ProductModel> productList;
    private FloatingActionButton fabAddProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_san_pham, container, false);

        recyclerView = view.findViewById(R.id.rv_product_list);
        fabAddProduct = view.findViewById(R.id.fab_add_product);
        fabAddProduct.setOnClickListener(v -> {
            showAddProductDialog();
                });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productList = new ArrayList<>();
        adapter = new ProductAdapter(productList, requireContext());
        recyclerView.setAdapter(adapter);

        FloatingActionButton fabAddProduct = view.findViewById(R.id.fab_add_product);
        fabAddProduct.setOnClickListener(v -> {
            showAddProductDialog();
        });

        createSampleData();

        return view;
    }

    private void showAddProductDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_product,
        null);
        builder.setView(dialogView);

        EditText edtProductName = dialogView.findViewById(R.id.edt_product_name);
        EditText edtProductPrice = dialogView.findViewById(R.id.edt_product_price);
        EditText edtProductQuantity = dialogView.findViewById(R.id.edt_product_quantity);
        Button btnAdd = dialogView.findViewById(R.id.btn_add);
        ImageView imgClose = dialogView.findViewById(R.id.img_close);


        AlertDialog dialog = builder.create();
        dialog.show();

        imgClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

    }

    private void createSampleData() {
        productList.add(new ProductModel(1, "Bánh quy bơ LU Pháp", 45000, 10, "https://example.com/image1.jpg"));
        productList.add(new ProductModel(2, "Snack mực lăn muối ớt", 8000, 52, "https://example.com/image2.jpg"));
        productList.add(new ProductModel(3, "Snack khoai tây Lays", 12000, 38, "https://example.com/image3.jpg"));
        productList.add(new ProductModel(4, "Bánh gạo One One", 30000, 11, "https://example.com/image4.jpg"));
        productList.add(new ProductModel(5, "Kẹo sữa sô cô la", 25000, 30, "https://example.com/image5.jpg"));
        adapter.notifyDataSetChanged();
    }
}