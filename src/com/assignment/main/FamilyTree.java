package com.assignment.main;

import com.assignment.config.Constants;
import com.assignment.model.Command;
import com.assignment.model.Gender;
import com.assignment.model.Person;
import com.assignment.service.PersonService;
import com.assignment.service.PersonServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class FamilyTree {

	 static Person king;
	 static Person queen;
	 static HashSet<String> allPersonsInFamily = new HashSet<String>();

    public static void main(String[] args) {
    	FamilyTreeInitializer.generateExistingFamilyTree(Constants.FAMILY_INFORMATION);
    	if(args!=null && args.length>0)
		{
			String inputFilePath = args[0];
			readInputFileAndProcess(inputFilePath);
		}

    	String input = "/home/yogaranjans/Documents/test.txt";
		readInputFileAndProcess(input);
    }

	private static void readInputFileAndProcess(String inputFilePath) {
		System.out.println("Processing input file : "+inputFilePath);
		PersonService personService = new PersonServiceImpl();
		BufferedReader br = null;
		FileReader fr = null;
		try {

			File file = new File(inputFilePath);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				if (line == null || line.isEmpty())
					continue;
				String input[] = line.split(" ");
				Command command = Command.valueOf(input[0].toUpperCase());
				switch (command) {
					case ADD_CHILD:
						String name = input[2];
						FamilyTree.allPersonsInFamily.add(name);
						String relativeName = input[1];
						if(!allPersonsInFamily.contains(relativeName))
						{
							System.out.println("PERSON_NOT_FOUND");
							break;
						}
						Gender gender = Gender.valueOf(input[3].toUpperCase());
						Person person = new Person(name, gender);
						personService.addChild(queen, relativeName, person);
						break;

					case GET_RELATIONSHIP:
						name = input[1];
						if(!allPersonsInFamily.contains(name))
						{
							System.out.println("PERSON_NOT_FOUND");
							break;
						}
						String relation = input[2];
						//System.out.println("Get relation : "+name +" -> " + relation);
						personService.getRelationShip(queen, name, relation);
						break;
					default:
						System.out.println("Invalid Command !!");
						break;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
