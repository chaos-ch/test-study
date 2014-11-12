package com.qunar.hotel.sa.product.price.bean;

import java.io.Serializable;

public class RoomGroup implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 804627086001083613L;
    /**
	 * 
	 */
    private String childAges;
    private int numberOfAdults;
    private int numberOfChildren;
    private String firstName;
    private String lastName;
    private String bedType;
    private String bedDesc;

    public RoomGroup() {
        super();
    }

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

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getBedDesc() {
        return bedDesc;
    }

    public void setBedDesc(String bedDesc) {
        this.bedDesc = bedDesc;
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
