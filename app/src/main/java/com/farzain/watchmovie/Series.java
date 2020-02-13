package com.farzain.watchmovie;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Series implements Parcelable {
    private int id;
    private String name;
    private String synopsis;
    private String photo;
    private String release;

    protected Series(Parcel in) {
        id = in.readInt();
        name = in.readString();
        synopsis = in.readString();
        photo = in.readString();
        release = in.readString();
    }

    public static final Creator<Series> CREATOR = new Creator<Series>() {
        @Override
        public Series createFromParcel(Parcel in) {
            return new Series(in);
        }

        @Override
        public Series[] newArray(int size) {
            return new Series[size];
        }
    };

    public Series() {
        /*try {
            String name = jsonObject.getString("name");
            String overview = jsonObject.getString("overview");
            String poster_path = jsonObject.getString("poster_path");
            String first_air_date = jsonObject.getString("first_air_date");

            this.name = name;
            this.synopsis = overview;
            this.photo = poster_path;
            this.release = first_air_date;

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(synopsis);
        dest.writeString(photo);
        dest.writeString(release);
    }
}
