package com.loupid.contacts;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Contact implements Parcelable {
    public String firstName, lastName, cellPhone, workPhone, email;
    public boolean isCellPhoneDefault, isWorkPhoneDefault;

    public Contact(String firstName, String lastName, String cellPhone, String workPhone, String email, boolean isCellPhoneDefault, boolean isWorkPhoneDefault) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellPhone = cellPhone;
        this.workPhone = workPhone;
        this.email = email;
        this.isCellPhoneDefault = isCellPhoneDefault;
        this.isWorkPhoneDefault = isWorkPhoneDefault;
    }

    protected Contact(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        cellPhone = in.readString();
        workPhone = in.readString();
        email = in.readString();
        isCellPhoneDefault = in.readByte() != 0;
        isWorkPhoneDefault = in.readByte() != 0;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCellPhoneDefault() {
        return isCellPhoneDefault;
    }

    public void setCellPhoneDefault(boolean cellPhoneDefault) {
        isCellPhoneDefault = cellPhoneDefault;
    }

    public boolean isWorkPhoneDefault() {
        return isWorkPhoneDefault;
    }

    public void setWorkPhoneDefault(boolean workPhoneDefault) {
        isWorkPhoneDefault = workPhoneDefault;
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(cellPhone);
        dest.writeString(workPhone);
        dest.writeString(email);
        dest.writeByte((byte) (isCellPhoneDefault ? 1 : 0));
        dest.writeByte((byte) (isWorkPhoneDefault ? 1 : 0));
    }


}
