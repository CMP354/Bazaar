package com.example.CMP354.bazaar.Classes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.CMP354.bazaar.R;

import java.io.Serializable;
import java.util.Date;
public class ShopItem extends Activity {
        String ShopName;
        String ShopType;
        String ShopDes;
        int  ShopImg;

        public int getShopImg() {
                return 0;
        }

        public void setShopImg(int shopImg) {
                ShopImg = shopImg;
        }


        public ShopItem(String name,String type,String des) {
                ShopName=name;
                ShopType=type;
                ShopDes=des;
        }

        public String getShopName() {
                return ShopName;
        }

        public String getShopType() {

                return "@drawable/logo_haha";
                //return ShopType;
        }

        public String getShopDes() {
                return ShopDes;
        }

        public void setShopName(String shopName) {
                ShopName = shopName;
        }

        public void setShopType(String shopType) {
                ShopType = shopType;
        }

        public void setShopDes(String shopDes) {
                ShopDes = shopDes;
        }
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//                super.onCreate(savedInstanceState);
//                setContentView(R.layout.fragment_shopitem);
//
//                // get references to widgets
//                TextView shopName = (TextView) findViewById(R.id.shop_name);
//                TextView shopDes = (TextView) findViewById(R.id.shop_des);
//
//                // get the intent
//                Intent intent = getIntent();
//
//                // get data from the intent
//                String pubDate = intent.getStringExtra("pubdate");
//                String title = intent.getStringExtra("title");
//                String description = intent.getStringExtra("description").replace('\n', ' ');
//
//                // display data on the widgets
//                shopName.setText(ShopName);
//                shopDes.setText(ShopDes);
//
//
//        }


    }

