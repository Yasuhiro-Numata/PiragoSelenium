package model;

import com.google.gson.annotations.SerializedName;

public class Account {
    public String getUserName() {
        return userName;
    }


    @SerializedName("userName")
    private String userName;

    public String getPass() {
        return pass;
    }

    @SerializedName("pass")
    private String pass;


}
