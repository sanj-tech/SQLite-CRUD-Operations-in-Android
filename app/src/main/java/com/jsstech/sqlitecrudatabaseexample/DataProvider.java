package com.jsstech.sqlitecrudatabaseexample;

public class DataProvider {
    String name,contact,address;

    public DataProvider(String name,String contact,String address) {
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public DataProvider() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
