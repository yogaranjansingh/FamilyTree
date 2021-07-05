# FamilyTree

# How to run ?
1. Download the project as a zip and extract it.

2. Build a jar using maven by running the following command in projects base directory from terminal : 
# mvn clean install
This generates a jar file named geektrust.jar

Alternatively the jar can directly be downloaded from here : https://tinyurl.com/22p2tdwj

3. Now to run the application , run the command as below (edit the path of the test input file)
This step requires a JRE installed system
# java -jar target/geektrust.jar /home/yogaranjans/Documents/test.txt

A sample test input file is added in the code name test.txt, that can be used for convenience

sample input  : 
ADD_CHILD Asva Vani Female
GET_RELATIONSHIP Vasa Siblings

sample output
CHILD_ADDITION_FAILED
NONE

