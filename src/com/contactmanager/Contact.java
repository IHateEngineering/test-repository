package com.contactmanager;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
	private String _firstName;
	private String _middleName;
	private String _preferedName;
	private String _lastName;
	private int _mobileNumber;
	private int _homeNumber;
	private String _email;
	private String _adress;
	private String _DOB;
	private int _age;
	private String _gender;
	private String _notes;

	public boolean ignored = false;
	
	private long ID;

	private Contact(Builder builder) {
		_firstName = builder._firstName;
		_middleName = builder._middleName;
		_preferedName = builder._preferedName;
		_lastName = builder._lastName;
		_mobileNumber = builder._mobileNumber;
		_homeNumber = builder._homeNumber;
		_email = builder._email;
		_adress = builder._adress;
		_DOB = builder._DOB;
		_age = builder._age;
		_gender = builder._gender;
		_notes = builder._notes;
	}

	public static class Builder {
		private String _firstName;
		private String _middleName;
		private String _preferedName;
		private String _lastName;
		private int _mobileNumber;
		private int _homeNumber;
		private String _email;
		private String _adress;
		private String _DOB;
		private int _age;
		private String _gender;
		private String _notes;

		public Builder firstName(String firstName) {
			_firstName = firstName;
			return this;
		}

		public Builder middleName(String middleName) {
			_middleName = middleName;
			return this;
		}

		public Builder preferedName(String preferedName) {
			_preferedName = preferedName;
			return this;
		}

		public Builder lastName(String lastName) {
			_lastName = lastName;
			return this;
		}

		public Builder mobileNumber(int number) {
			_mobileNumber = number;
			return this;
		}

		public Builder homeNumber(int number) {
			_homeNumber = number;
			return this;
		}

		public Builder email(String email) {
			_email = email;
			return this;
		}

		public Builder adress(String adress) {
			_adress = adress;
			return this;
		}

		public Builder dob(String string) {
			_DOB = string;
			return this;
		}

		public Builder age(int age) {
			_age = age;
			return this;
		}

		public Builder gender(String gender) {
			_gender = gender;
			return this;
		}

		public Builder notes(String notes) {
			_notes = notes;
			return this;
		}

		public Contact build() {
			return new Contact(this);
		}

	}

	public String getFirstName() {
		return _firstName;
	}

	public String getMiddleName() {
		return _middleName;
	}

	public String getPreferredName() {
		return _preferedName;
	}

	public String getLastName() {
		return _lastName;
	}

	public int getMobileNumber() {
		return _mobileNumber;
	}

	public int getHomeNumber() {
		return _homeNumber;
	}

	public String getEmail() {
		return _email;
	}

	public String getAdress() {
		return _adress;
	}

	public String getDOB() {
		return _DOB;
	}

	public int getAge() {
		return _age;
	}

	public String getGender() {
		return _gender;
	}

	public String getNotes() {
		return _notes;
	}

	public long getId() {
		return ID;
	}

	public void setId(long id) {
		ID = id;
	}

	public void setFirstName(String s) {
		_firstName = s;
	}

	public void setPreferedName(String s) {
		_preferedName = s;
	}

	public void setLastName(String s) {
		_lastName = s;
	}

	public void setMiddleName(String s) {
		_middleName = s;
	}

	public void setMobileNumber(int i) {
		_mobileNumber = i;
	}

	public void setHomeNumber(int i) {
		_homeNumber = i;
	}

	public void setEmail(String s) {
		_email = s;
	}

	public void setAdress(String s) {
		_adress = s;
	}

	public void setDOB(String s) {
		_DOB = s;
	}

	public void setAge(int i) {
		_age = i;
	}

	public void setGender(String s) {
		_gender = s;
	}

	public void setNotes(String s) {
		_notes = s;
	}

	public boolean isNull() {
		return false;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		out.writeString(_firstName);
		out.writeString(_middleName);
		out.writeString(_preferedName);
		out.writeString(_lastName);
		out.writeInt(_mobileNumber);
		out.writeInt(_homeNumber);
		out.writeString(_email);
		out.writeString(_adress);
		out.writeString(_DOB);
		out.writeInt(_age);
		out.writeString(_gender);
		out.writeString(_notes);
		out.writeLong(ID);
	}

	public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
		public Contact createFromParcel(Parcel in) {
			return new Contact(in);
		}

		public Contact[] newArray(int size) {
			return new Contact[size];
		}
	};

	/**
	 * constructor used by the framework when parcing.
	 * 
	 * @param in
	 */
	private Contact(Parcel in) {
		_firstName = in.readString();
		_middleName = in.readString();
		_preferedName = in.readString();
		_lastName = in.readString();
		_mobileNumber = in.readInt();
		_homeNumber = in.readInt();
		_email = in.readString();
		_adress = in.readString();
		_DOB = in.readString();
		_age = in.readInt();
		_gender = in.readString();
		_notes = in.readString();
		ID = in.readLong();
		//
	}

}
