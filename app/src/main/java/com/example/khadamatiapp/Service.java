package com.example.khadamatiapp;

import java.io.Serializable;

// كلاس نموذج الخدمة (Service) – يمثل كل خدمة في التطبيق
// implements Serializable يسمح بتمرير الكائن بين Activities باستخدام Intent
public class Service implements Serializable {

    // اسم الخدمة
    private String name;

    // وصف الخدمة
    private String description;

    // معرف الصورة المخزنة في res/drawable
    private int imageResId;

    // Constructor لإنشاء كائن Service
    public Service(String name, String description, int imageResId) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
    }

    // Getter لاسترجاع اسم الخدمة
    public String getName() {
        return name;
    }

    // Getter لاسترجاع وصف الخدمة
    public String getDescription() {
        return description;
    }

    // Getter لاسترجاع معرف الصورة
    public int getImageResId() {
        return imageResId;
    }
}

