package com.example.project_practice.Explore;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Parameter;

public class Explore_list_item implements Parcelable {
    String title;
    String brief;
    int img_id;

    public Explore_list_item(String title, String brief, int img_id) {
        this.title = title;
        this.brief = brief;
        this.img_id = img_id;
    }

    protected Explore_list_item(Parcel in) {
        title = in.readString();
        brief = in.readString();
        img_id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(brief);
        dest.writeInt(img_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Explore_list_item> CREATOR = new Creator<Explore_list_item>() {
        @Override
        public Explore_list_item createFromParcel(Parcel in) {
            return new Explore_list_item(in);
        }

        @Override
        public Explore_list_item[] newArray(int size) {
            return new Explore_list_item[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}
