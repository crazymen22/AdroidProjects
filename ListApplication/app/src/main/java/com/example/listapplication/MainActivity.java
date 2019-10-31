package com.example.listapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listapplication.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Product> products = new ArrayList<>();
    private LinearLayout linLayout;
    private LayoutInflater ltInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products.addAll(Arrays.asList(
                new Product("product1", 1, 10),
                new Product("product2", 2, 20),
                new Product("product3", 3, 30),
                new Product("product4", 4, 40),
                new Product("product5", 5, 50)));

        linLayout = findViewById(R.id.linLayout);

        ltInflater = getLayoutInflater();

        for(Product product : products) {
            View item = ltInflater.inflate(R.layout.item, linLayout, false);
            TextView tvName = item.findViewById(R.id.tvName);
            tvName.setText(product.getName());
            TextView tvPosition = item.findViewById(R.id.tvWeight);
            tvPosition.setText("Вага: " + product.getWeight());
            TextView tvSalary = item.findViewById(R.id.tvPrice);
            tvSalary.setText("Ціна: " + product.getPrice());
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            linLayout.addView(item);
        }
    }

    public void checkProduct(View view) {

    }

    public void addProduct(View view) {
        Product newProduct = new Product("newproduct", 2, 123);

        View item = ltInflater.inflate(R.layout.item, linLayout, false);
        TextView tvName = item.findViewById(R.id.tvName);
        tvName.setText(newProduct.getName());
        TextView tvPosition = item.findViewById(R.id.tvWeight);
        tvPosition.setText("Вага: " + newProduct.getWeight());
        TextView tvSalary = item.findViewById(R.id.tvPrice);
        tvSalary.setText("Ціна: " + newProduct.getPrice());
        item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        linLayout.addView(item);
    }

    public void deleteProduct(View view) {

    }

    public void updateProduct(View view) {
    }
}
