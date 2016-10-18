package com.example.microsoft.service.Model;

/**
 * Created by Microsoft on 18.10.2016.
 */
public class Image {

    private int mImage; //поле класса типа инт

    public int getImage() { //метод возвращающий картинку
        return mImage;
    }

    public Image setImage(int image) { //метод задающий картинку
        mImage = image;
        return this;
    }
}
