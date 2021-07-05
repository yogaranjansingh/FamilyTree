package com.assignment.service;

import com.assignment.model.Person;

import java.util.List;

public interface RelationshipService {

    List<Person> getSons(Person root);

    List<Person> getDaughters(Person root);

    List<Person> getPaternalUncles(Person root);

    List<Person> getMaternalUncles(Person root);

    List<Person> getPateralAunts(Person root);

    List<Person> getMaternalAunts(Person root);

    List<Person> getSiblings(Person root);

    List<Person> getSisterInLaws(Person root);

    List<Person> getBrotherInLaws(Person root);

    Person getFather(Person root);

    Person getMother(Person root);

}
