package com.example.onlineshop.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.ProductActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.text.DecimalFormat;


public class ProductViewModel extends BaseObservable {

    private Product mProduct;
    private Context mContext;

    public ProductViewModel(Product product) {
        mProduct = product;
    }

    public ProductViewModel(Context context) {
        mContext = context;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
        notifyChange();
    }

    @Bindable
    public String getName() {
        return mProduct.getName();
    }

    @Bindable
    public String getPrice() {
        String price = mProduct.getPrice();
        if (price == null || price.equals(""))
            price = "0";
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(Double.valueOf(price));
    }

    @Bindable
    public String getRegularPrice() {
        String price = mProduct.getRegularPrice();
        if (price == null || price.equals(""))
            price = "0";
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(Double.valueOf(price));
    }

    @Bindable
    public String getAvRate() {
        return mProduct.getAvRate();
    }

    @Bindable
    public String getDescription() {
        String html = mProduct.getDescription();
        if (html == null)
            return html;
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
        document.select("br").append("\\n");
        document.select("p").prepend("\\n\\n");
        String s = document.html().replaceAll("\\\\n", "\n");
        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }

    public void productDetail() {
        mContext.startActivity(ProductActivity.newIntent(mContext, mProduct.getmId()));
    }
}
