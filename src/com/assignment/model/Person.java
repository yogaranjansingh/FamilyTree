package com.assignment.model;

import java.util.ArrayList;
import java.util.List;

public class Person {
	
	private int id;
	private String name;
	private Gender gender;
	private Person father;
	private Person mother;
	private List<Person> children;
	private Person spouse;
	boolean isMarried;

	public boolean isMarried() {
		return isMarried;
	}

	public void setMarried(boolean married) {
		isMarried = married;
	}

	public Person(String name, Gender gender) {
		super();
		this.name = name;
		this.gender = gender;
	}

	public Person getSpouse() {
		return spouse;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
		this.isMarried = true;
		spouse.spouse = this;
		this.spouse.isMarried = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	public List<Person> getChildren() {
		if(children==null)
		{
			children = new ArrayList<Person>();
		}
		return children;
	}

	public void setChildren(List<Person> children) {
		this.children = children;
	}

	public List<Person> getDaughters()
	{
		List<Person> daughters = new ArrayList<Person>();
		for(Person child : children)
		{
			if(child.getGender()==Gender.FEMALE)
			{
				daughters.add(child);
			}
		}
		return daughters;
	}
	
	public List<Person> getSons()
	{
		List<Person> sons = new ArrayList<Person>();
		for(Person child : children)
		{
			if(child.getGender()==Gender.MALE)
			{
				sons.add(child);
			}
		}
		return sons;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", gender=" + gender;
	}
	
}
