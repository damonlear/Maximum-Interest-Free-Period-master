package com.damonleexh.url;

public class BaseUrl {

    public static String getBankIconUrl(String abbr) {
        String url = "https://apimg.alipay.com/combo.png?d=cashier&t=" + abbr;
        if (abbr.contentEquals("ALIPAY")) {
            url = "https://bkimg.cdn.bcebos.com/pic/d788d43f8794a4c22b73bf7d00f41bd5ad6e3917?x-bce-process=image/watermark,g_7,image_d2F0ZXIvYmFpa2U4MA==,xp_5,yp_5";
        }
        return url;
    }


}
