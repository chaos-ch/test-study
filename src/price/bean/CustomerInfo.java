package com.qunar.hotel.sa.product.price.bean;

public class CustomerInfo {

    private String childAges;
    private int numberOfAdults;
    private int numberOfChildren;
    private String firstName;
    private String lastName;

    public String getChildAges() {
        return childAges;
    }

    public void setChildAges(String childAges) {
        this.childAges = childAges;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
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

}
