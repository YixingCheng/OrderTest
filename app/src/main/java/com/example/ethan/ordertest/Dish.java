package com.example.ethan.ordertest;

/**
 * Created by ethan on 12/22/14.
 */
public class Dish {

    private int _id;
    private String _dishCategory;
    private String _dishName;
    private float _dishPrice;
    private String _imgPath;

    public Dish(){
    }

    public Dish(String category, String name, float price, String path){
        this._dishCategory = category;
        this._dishName = name;
        this._dishPrice = price;
        this._imgPath = path;
    }

    public int getID(){ return this._id; }
    public void setID(int id){ this._id = id; }

    public String getCategory(){ return this._dishCategory; }
    public void setCategory(String category) { this._dishCategory = category; }

    public String getName(){ return this._dishName; }
    public void setName(String name){ this._dishName = name; }

    public float getPrice(){ return this._dishPrice; }
    public void setPrice(float price){ this._dishPrice = price; }

    public String getPath(){ return this._imgPath; }
    public void setPath(String path) {this._imgPath = path;}

}
