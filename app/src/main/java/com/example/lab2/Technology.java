package com.example.lab2;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Technology implements Serializable, Parcelable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("helptext")
    @Expose
    private String helptext;
    @SerializedName("graphic")
    @Expose
    private String graphic;

    Technology(){
        name = "";
        helptext = "";
        graphic = "";
    }

    Technology(String name, String description, String picture){
        this.name = name;
        this.helptext = description;
        this.graphic = picture;
    }

    Technology(final Technology technology){
        name = technology.getName();
        helptext = technology.getHelptext();
        graphic = technology.getGraphic();
    }

    Technology(Parcel parcel){
        name = parcel.readString();
        helptext = parcel.readString();
        graphic = parcel.readString();
    }

    public String getName() {
        return name;
    }

    public String getHelptext() {
        return helptext;
    }

    public String getGraphic() {
        return graphic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHelptext(String helptext) {
        this.helptext = helptext;
    }

    public void setGraphic(String graphic) {
        this.graphic = graphic;
    }

    @Override
    public String toString() {
        return "Имя " + name + "\nКартинка " + graphic + "\nОписание " + helptext;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(helptext);
        dest.writeString(graphic);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Technology createFromParcel(Parcel in) {
            return new Technology(in);
        }

        public Technology[] newArray(int size) {
            return new Technology[size];
        }
    };
}
