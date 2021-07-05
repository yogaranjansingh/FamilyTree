package com.assignment.main;

import com.assignment.model.Command;
import com.assignment.model.Gender;
import com.assignment.model.Person;
import com.assignment.service.PersonService;
import com.assignment.service.PersonServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FamilyTreeInitializer {

     static void generateExistingFamilyTree(String inputFilePath) {
         System.out.println("Initializing the existing family tree");
        Person king = new Person("Shan", Gender.MALE);
        Person queen = new Person("Anga", Gender.FEMALE);
        PersonService personService = new PersonServiceImpl();
        king.setSpouse(queen);
        FamilyTree.king = king;
        FamilyTree.queen = queen;
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
                        //	System.out.println("Add child : " + Arrays.toString(input));
                        String name = input[1];
                        String relativeName = input[2];
                        Gender gender = Gender.valueOf(input[3].toUpperCase());
                        Person person = new Person(name, gender);
                        FamilyTree.allPersonsInFamily.add(name);
                        personService.addChild(queen, relativeName, person);
                        break;
                    case SET_SPOUSE:
                        name = input[1];
                        FamilyTree.allPersonsInFamily.add(name);
                        relativeName = input[2];
                        gender = Gender.valueOf(input[3].toUpperCase());
                        person = new Person(name, gender);
                        personService.setSpouse(queen, relativeName, person);
                        break;
                    case GET_RELATIONSHIP:
                        name = input[1];
                        String relation = input[2];
                        System.out.println("Get relation : "+name +" -> " + relation);
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
