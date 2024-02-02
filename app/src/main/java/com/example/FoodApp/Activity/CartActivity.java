package com.example.FoodApp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.FoodApp.Adapter.CartAdapter;
import com.example.FoodApp.Helper.ChangeNumberItemsListener;
import com.example.FoodApp.Helper.ManagementCart;
import com.example.FoodApp.R;
import com.example.FoodApp.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagementCart ManagementCart;
    private double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ManagementCart = new ManagementCart(this);

        setVariable();
        calculateCart();
        initList();
    }

    private void initList() {
        if (ManagementCart.getListCart().isEmpty()) {
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollviewCart.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollviewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.cardView.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(ManagementCart.getListCart(), this, () -> calculateCart());
        binding.cardView.setAdapter(adapter);
    }

    private void calculateCart() {
        double percentTax = 0.02; //percent 2% tax
        double delivery = 10; // 10 euro

        tax = Math.round(ManagementCart.getTotalFee() * percentTax * 100.0) / 100;

        double total = Math.round((ManagementCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(ManagementCart.getTotalFee() * 100) / 100;

        binding.totalFeeTxt.setText("€" + itemTotal);
        binding.taxTxt.setText("€" + tax);
        binding.deliveryTxt.setText("€" + delivery);
        binding.totalTxt.setText("€" + total);
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}