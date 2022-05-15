package ru.netology.entities;

import lombok.Value;

@Value
public class RegistrationInfo {
    String city;
    String name;
    String phoneNumber;
}