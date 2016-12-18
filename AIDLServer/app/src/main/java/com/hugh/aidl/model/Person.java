package com.hugh.aidl.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hugh on 16/10/1.
 */

public class Person implements Parcelable {
    private String name;
    private int age;

    public Person() {
    }
    public Person(Parcel in){
        name=in.readString();
        age=in.readInt();
    }
    public static final Creator<Person> CREATOR=new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
    public void readFromParcel(Parcel dest){
        name=dest.readString();
        age=dest.readInt();
    }
    public String toString(){
        return "name :"+name+" , age :"+age;
    }
}
