package com.example.useri.myapplication4;

/**
 * Created by USERi on 03-01-2018.
 */

public class blog {

    private String item;
    private String price;
    private String image;
    public blog(){

    }
    public blog(String item,String price,String image)
    {
        this.item=item;
        this.price=price;
        this.image=image;
    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}