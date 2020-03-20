package com.damonleexh.util;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.damonleexh.bean.BankModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BankJsonPaser {

    public static List<BankModel> paser(Context context) throws IOException {
        InputStream inputStream = context.getAssets().open("banks.json");
        int lenght = inputStream.available();
        byte[] buffer = new byte[lenght];
        inputStream.read(buffer);
        String json = new String(buffer, "UTF-8");
        List<BankModel> bankModels = JSON.parseArray(json, BankModel.class);
        return bankModels;
    }
}
