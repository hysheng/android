package com.hys.parcel;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
	//序列化
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	private Person(Parcel parcel) {
		name = parcel.readString();
		age = parcel.readInt();
	}

	public Person() {

	}

	public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {

		@Override
		public Person createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Person(source);
		}

		@Override
		public Person[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Person[size];
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		//向Parcel写入要序列化的数据
		dest.writeString(name);
		dest.writeInt(age);

	}

}
