package model;

import com.google.gson.annotations.SerializedName;

public class SearchConditions {
    @SerializedName("java")
    private String java;

    public String getJava() {
        return java;
    }
}
