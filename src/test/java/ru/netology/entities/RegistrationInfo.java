package ru.netology.entities;

public class RegistrationInfo {
    private final String city;
    private final String name;
    private final String phoneNumber;

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RegistrationInfo(String city, String name, String phoneNumber) {
        this.city = city;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}