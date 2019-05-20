package com.example.project_practice.Message;

import android.os.Parcel;
import android.os.Parcelable;

public class Message_List_item implements Parcelable {
    String name;
    String last_Message;
    String lastTime;
    int Type;
    int img_id;

    public Message_List_item(String name, String last_Message, String lastTime, int type, int img_id) {
        this.name = name;
        this.last_Message = last_Message;
        this.lastTime = lastTime;
        Type = type;
        this.img_id = img_id;
    }

    protected Message_List_item(Parcel in) {
        name = in.readString();
        last_Message = in.readString();
        lastTime = in.readString();
        Type = in.readInt();
        img_id = in.readInt();
    }

    public static final Creator<Message_List_item> CREATOR = new Creator<Message_List_item>() {
        @Override
        public Message_List_item createFromParcel(Parcel in) {
            return new Message_List_item(in);
        }

        @Override
        public Message_List_item[] newArray(int size) {
            return new Message_List_item[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_Message() {
        return last_Message;
    }

    public void setLast_Message(String last_Message) {
        this.last_Message = last_Message;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(last_Message);
        dest.writeString(lastTime);
        dest.writeInt(Type);
        dest.writeInt(img_id);
    }
}

