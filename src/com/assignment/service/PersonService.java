package com.assignment.service;

import com.assignment.model.Person;

public interface PersonService {

    void addChild(Person queen, String motherName, Person person);
    void setSpouse(Person root, String spouseName, Person person);
    void getRelationShip(Person root, String name, String rel);
}
