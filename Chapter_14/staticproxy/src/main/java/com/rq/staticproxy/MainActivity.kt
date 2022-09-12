package com.rq.staticproxy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shop : IShop = Buyer()
        val buyerProxy = BuyerProxy(shop)
        buyerProxy.buy()
    }
}