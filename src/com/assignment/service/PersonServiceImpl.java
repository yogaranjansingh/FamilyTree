package com.assignment.service;

import com.assignment.model.Gender;
import com.assignment.model.Person;
import com.assignment.model.Relation;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PersonServiceImpl implements PersonService {

     public void setSpouse(Person root, String spouseName, Person person) {
        if (spouseName.equalsIgnoreCase(root.getName())) {
            root.setSpouse(person);
            return;
        } else {
            List<Person> children = root.getChildren();
            for (Person child : children) {
                setSpouse(child, spouseName, person);
            }
        }
    }

     public void addChild(Person queen, String motherName, Person person) {
        if(queen==null)
            return;
        if (motherName.equalsIgnoreCase(queen.getName())) {
            person.setFather(queen.getSpouse());
            person.setMother(queen);
            queen.getChildren().add(person);
            queen.getSpouse().getChildren().add(person);
            System.out.println("CHILD_ADDITION_SUCCEEDED");
            return;
        } else {
            List<Person> children = queen.getChildren();
            for (Person child : children) {
                if (child.getGender() == Gender.FEMALE) {
                    addChild(child, motherName, person);
                } else {
                    if (child.getName().equalsIgnoreCase(motherName)) {
                        System.out.println("CHILD_ADDITION_FAILED");
                        return;
                    }
                    addChild(child.getSpouse(), motherName, person);
                }
            }
        }
    }

    public void getRelationShip(Person root, String name, String rel) {
         if(root.getSpouse()!=null)
         {
             if(root.getSpouse().getName().equalsIgnoreCase(name))
             {
                 root = root.getSpouse();
             }
         }
        if(root.getName().equalsIgnoreCase(name))
        {
            Relation relation = Relation.valueOf(rel);
            RelationshipService relationshipService = new RelationshipServiceImpl();
            switch (relation) {
                case Father:
                    Person father = relationshipService.getFather(root);
                    System.out.println(father.getName());
                    return;
                case Mother:
                    Person mother = relationshipService.getMother(root);
                    System.out.println(mother.getName());
                    return;

                case Son:
                    List<Person> relations = relationshipService.getSons(root);
                    if(relations==null || relations.size()==0)
                    {
                        System.out.println("NONE");
                        return;
                    }
                    for(Person person : relations)
                    {
                        System.out.print(person.getName()+" ");
                    }
                    System.out.println();
                    return;
                case Daughter:
                    relations = relationshipService.getDaughters(root);
                    if(relations==null || relations.size()==0)
                    {
                        System.out.println("NONE");
                        return;
                    }
                    for(Person person : relations)
                    {
                        System.out.print(person.getName()+" ");
                    }
                    System.out.println();
                    return;
                case Siblings:
                    relations = relationshipService.getSiblings(root);
                    if(relations==null || relations.size()==0)
                    {
                        System.out.println("NONE");
                        return;
                    }
                    for(Person person : relations)
                    {
                        System.out.print(person.getName()+" ");
                    }
                    System.out.println();
                    return;
                case Paternal_Uncle:
                    relations = relationshipService.getPaternalUncles(root);
                    if(relations==null || relations.size()==0)
                    {
                        System.out.println("NONE");
                        return;
                    }
                    for(Person person : relations)
                    {
                        if(person!=null)
                        System.out.print(person.getName()+" ");
                    }
                    System.out.println();
                    return;
                case Maternal_Uncle:
                    relations = relationshipService.getMaternalUncles(root);
                    if(relations==null || relations.size()==0)
                    {
                        System.out.println("NONE");
                        return;
                    }
                    for(Person person : relations)
                    {
                        System.out.print(person.getName()+" ");
                    }
                    System.out.println();
                    return;
                case Paternal_Aunt:
                    relations = relationshipService.getPateralAunts(root);
                    if(relations==null || relations.size()==0)
                    {
                        System.out.println("NONE");
                        return;
                    }
                    for(Person person : relations)
                    {
                        System.out.print(person.getName()+" ");
                    }
                    System.out.println();
                    return;
                case Maternal_Aunt:
                    relations = relationshipService.getMaternalAunts(root);
                    if(relations==null || relations.size()==0)
                    {
                        System.out.println("NONE");
                        return;
                    }
                    for(Person person : relations)
                    {
                        System.out.print(person.getName()+" ");
                    }
                    System.out.println();
                    return;
                case Brother_In_Law:
                    relations = relationshipService.getBrotherInLaws(root);
                    if(relations==null || relations.size()==0)
                    {
                        System.out.println("NONE");
                        return;
                    }
                    for(Person person : relations)
                    {
                        System.out.print(person.getName()+" ");
                    }
                    System.out.println();
                    return;
                case Sister_In_Law:
                    relations = relationshipService.getSisterInLaws(root);
                    if(relations==null || relations.size()==0)
                    {
                        System.out.println("NONE");
                        return;
                    }
                    for(Person person : relations)
                    {
                        System.out.print(person.getName()+" ");
                    }
                    System.out.println();
                    return;
                default:
                    return;
            }
        }
        else
        {
            CopyOnWriteArrayList<Person> children = new CopyOnWriteArrayList<>(root.getChildren());
            Iterator<Person> itr = children.listIterator();
            while(itr.hasNext()) {
                   getRelationShip(itr.next(), name,rel);
            }
        }
    }
}
