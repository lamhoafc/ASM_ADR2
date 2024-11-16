package fpoly.account.asm_adr2.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.account.asm_adr2.model.ProductModel;
import fpoly.account.asm_adr2.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductModel> productList;
    private Context context;

    public ProductAdapter(List<ProductModel> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice() + " - SL: " + product.getQuantity());
        //load ảnh ở đây

        holder.editButton.setOnClickListener(v -> {
            showEditProductDialog(position);
        });

        holder.deleteButton.setOnClickListener(v -> {
            showDeleteProductDialog(position);
        });
    }

    private void showEditProductDialog(int position) {
        ProductModel product = productList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_product,
        null);
        EditText edtProductName = dialogView.findViewById(R.id.edt_product_name);
        EditText edtProductPrice = dialogView.findViewById(R.id.edt_product_price);
        EditText edtProductQuantity = dialogView.findViewById(R.id.edt_product_quantity);

        edtProductName.setText(product.getName());
        edtProductPrice.setText(String.valueOf(product.getPrice()));
        edtProductQuantity.setText(String.valueOf(product.getQuantity()));

        builder.setView(dialogView);

        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                String name = edtProductName.getText().toString();
                double price = Double.parseDouble(edtProductPrice.getText().toString());
                int quantity = Integer.parseInt(edtProductQuantity.getText().toString());

                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);

                notifyItemChanged(position);
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface
                    dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void showDeleteProductDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa sản phẩm");
        builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này?");

                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface
                            dialog, int which) {
                        productList.remove(position);

                        notifyItemRemoved(position);
                    }
                });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface
                    dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImage;
        public TextView productName;
        public TextView productPrice;
        public TextView editButton;
        public TextView deleteButton;

        public ProductViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.img_product);
            productName = view.findViewById(R.id.tv_product_name);
            productPrice = view.findViewById(R.id.tv_product_price);
            editButton = view.findViewById(R.id.tv_edit);
            deleteButton = view.findViewById(R.id.tv_delete);
        }
    }
}
