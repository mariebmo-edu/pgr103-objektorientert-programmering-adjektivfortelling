package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Adjektivfortelling {

    //FILES
    File fortelling = new File("Java/src/Historie.txt");
    File adjektiv = new File("Java/src/Adjektiv.txt");

    //ARRAYS
    ArrayList<String> adjektivEntallUbestemt;
    ArrayList<String> adjektivEntallBestemt;
    ArrayList<String> adjektivFlertall;
    ArrayList<String> names;

    //USER ARRAYS
    ArrayList<String> addedAdjektiv = new ArrayList<>();
    ArrayList<String> addedNames = new ArrayList<>();

    //INIT
    public void init() throws FileNotFoundException {
        addContentFromFile(adjektiv);
        startMenu();

    }


    //METHODS
    public void startMenu() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int input = 0;

        while(input != 3){

            System.out.println(
                    "MENU \n" +
                    "WELCOME TO THE AJECTIVE STORY! \n" +
                            "1. AUTOMATIC STORY \n" +
                            "2. MAKE YOUR OWN STORY \n" +
                            "3. END SESSION");
            input = Integer.parseInt(scanner.nextLine());

            if (input == 1) {
                showRandomStory();
                break;
            } else if (input == 2) {
                showUserStory();
                break;
            } else if(input == 3){
                System.out.println("FINISHING SESSION");
            } else {
                System.out.println("ENTER VALID NUMBER");
            }
        }
    }

    public void showRandomStory() throws FileNotFoundException {
        String story = "";

        for(String s : makeRandomStory(fortelling)){
            story += (s + " ");
        }

        System.out.println(story);
    }

    public void showUserStory() throws FileNotFoundException {
        String story = "";

        for(String s : makeUserStory(fortelling)){
            story += (s + " ");
        }

        System.out.println(story);

    }

    public ArrayList<String> makeUserStory(File history) throws FileNotFoundException{
        Scanner scanner = new Scanner(history);
        Scanner userInput = new Scanner(System.in);
        ArrayList<String> story = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));

        int totalAdjFields = 0;
        int currentAdjFields = 0;
        int totalNameFields = 0;
        int currentNameFields = 0;

        for(String s : story){
            if (s.equals("_0_")  || s.equals("_1_") || s.equals("_2_")){
                totalAdjFields++;
            }
            else if (s.equals("_3_")){
                totalNameFields++;
            }
        }

        for(int i=0; i<story.size(); i++){
            if (story.get(i).equals("_0_") || story.get(i).equals("_1_") || story.get(i).equals("_2_")){
                currentAdjFields++;
                userStoryMenu("ADJECTIVE", currentAdjFields, totalAdjFields);
                story.set(i, userInput.nextLine());

            }
            else if (story.get(i).equals("_3_")){
                currentNameFields++;
                userStoryMenu("NAME", currentNameFields, totalNameFields);
                story.set(i, userInput.nextLine());

            }
        }

        return story;
    }

    public void userStoryMenu(String str, int current, int total){
        System.out.println("MAKE YOUR OWN ADVENTURE!");
        System.out.println("Currently: " + current + "/" + total + " of " + str + "S" +  "\n" +
                "Please add a " + str);
    }

    public ArrayList<String> makeRandomStory(File history) throws FileNotFoundException {
        Scanner scanner = new Scanner(history);
        ArrayList<String> story = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));

        for(int i=0; i<story.size(); i++){
            if ("_0_".equals(story.get(i))) {
                story.set(i, adjektivEntallUbestemt.get(randomNumber(0, adjektivEntallUbestemt.size())));
            } else if ("_1_".equals(story.get(i))) {
                story.set(i, adjektivEntallBestemt.get(randomNumber(0, adjektivEntallBestemt.size())));
            } else if ("_2_".equals(story.get(i))) {
                story.set(i, adjektivFlertall.get(randomNumber(0, adjektivFlertall.size())));
            } else if ("_3_".equals(story.get(i))) {
                story.set(i, names.get(randomNumber(0, names.size())));
            }
        }

        return story;
    }

    private int randomNumber(int min, int max){
        return (int)(Math.random()*max-min);
    }

    public void addContentFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        adjektivEntallUbestemt = new ArrayList<>(Arrays.asList(scanner.nextLine().split(", ")));
        scanner.nextLine();
        adjektivEntallBestemt = new ArrayList<>(Arrays.asList(scanner.nextLine().split(", ")));
        scanner.nextLine();
        adjektivFlertall = new ArrayList<>(Arrays.asList(scanner.nextLine().split(", ")));
        scanner.nextLine();
        names = new ArrayList<>(Arrays.asList(scanner.nextLine().split(", ")));
    }
}


