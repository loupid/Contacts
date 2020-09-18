package com.loupid.contacts;

public class Contact {
    public String firstName, lastName, cellPhone, workPhone;
    public boolean isCellPhoneDefault;

    public Contact(String firstName, String lastName, String cellPhone, String workPhone, boolean isCellPhoneDefault) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellPhone = cellPhone;
        this.workPhone = workPhone;
        this.isCellPhoneDefault = isCellPhoneDefault;
    }

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

    public boolean isCellPhoneDefault() {
        return isCellPhoneDefault;
    }

    public void setCellPhoneDefault(boolean cellPhoneDefault) {
        isCellPhoneDefault = cellPhoneDefault;
    }
}
