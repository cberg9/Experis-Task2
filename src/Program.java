import logging.Logging;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

import java.util.Scanner;

public class Program {
  public static void main(String[] args) throws FileNotFoundException {
    int choice;
    Scanner scanChoice = new Scanner(System.in);

    do {
      System.out.println();
      System.out.println("=======================");
      System.out.println("1. List all files");
      System.out.println("2. List the .txt files");
      System.out.println("3. List the .jpg files");
      System.out.println("4. List the .png files");
      System.out.println("5. List the .jfif files");
      System.out.println("6. Get information of a specific file. Search for a specific word in .txt files");
      System.out.println("7. Rename a file");
      System.out.println("8. Quit");

      choice = scanChoice.nextInt();
      var log = new Logging();

      if (choice == 1) {
        File folder = new File("../NewFiles");
        File[] listOfFiles = folder.listFiles(); // Array with all files in folder.
        log.start();
        for (int i = 0; i < listOfFiles.length; i++) { // loops and if files are found, print names.
          if (listOfFiles[i].isFile()) {
            System.out.println("File: " + listOfFiles[i].getName());

          }
        }
        log.stop(listOfFiles.length + " files were found");

      } else if (choice == 2) {
        // See if the files ends with .txt
        File directoryPath = new File("../NewFiles");
        FilenameFilter textFilefilter = new FilenameFilter() {
          public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.endsWith(".txt")) {
              return true;
            } else {
              return false;
            }
          }
        };
        // List of the text files:
        String filesList[] = directoryPath.list(textFilefilter);
        System.out.println("List of the text files in the specified directory:");
        log.start();
        for (String fileName : filesList) {
          System.out.println(fileName);
        }
        log.stop(filesList.length + " files were found");

      } else if (choice == 3) {
        // See if the files ends with .jpg or .jpeg
        File directoryPath = new File("../NewFiles");
        FilenameFilter textFilefilter = new FilenameFilter() {

          public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();

            if (lowercaseName.endsWith(".jpg") || lowercaseName.endsWith(".jpeg")) {
              return true;

            } else {
              return false;
            }
          }
        };

        // List of the jpg files:
        String filesList[] = directoryPath.list(textFilefilter);
        System.out.println("List of the jpg files in the specified directory:");
        log.start();
        for (String fileName : filesList) {
          System.out.println(fileName);
        }
        log.stop(filesList.length + " files were found");

      } else if (choice == 4) {
        // See if the files ends with .png
        File directoryPath = new File("../NewFiles");
        FilenameFilter textFilefilter = new FilenameFilter() {
          public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.endsWith(".png")) {
              return true;

            } else {
              return false;
            }
          }
        };
        // List of the png files:
        String filesList[] = directoryPath.list(textFilefilter);
        System.out.println("List of the png files in the specified directory:");
        log.start();
        for (String fileName : filesList) {
          System.out.println(fileName);
        }
        log.stop(filesList.length + " files were found");

      } else if (choice == 5) {
        // See if the files ends with .jfif
        File directoryPath = new File("../NewFiles");
        FilenameFilter textFilefilter = new FilenameFilter() {
          public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.endsWith(".jfif")) {
              return true;
            } else {
              return false;
            }
          }
        };
        // List of the jfif files:
        String filesList[] = directoryPath.list(textFilefilter);
        System.out.println("List of the jfif files in the specified directory:");
        log.start();
        for (String fileName : filesList) {
          System.out.println(fileName);

        }
        log.stop(filesList.length + " files were found");

      } else if (choice == 6) {
        Scanner scan = new Scanner(System.in);
        // looks in the current directory
        String directory = System.getProperty("user.dir");

        System.out.println("Enter the whole filename (.txt ..etc): ");

        String filename = scan.nextLine();
        String pathToFile = directory + "\\" + "../NewFiles" + "\\" + filename;
        log.start();
        try (FileInputStream fileInputStream = new FileInputStream(pathToFile)) {
          // read and save, the more data in the file, the more data.
          int data = fileInputStream.read();
          int byteCount = 0;

          while (data != -1) {

            byteCount++;

            // next data
            data = fileInputStream.read();

          }

          System.out.println("File is " + byteCount + " bytes, or " + byteCount / 1024.0 + " kilobytes ("
              + (int) Math.ceil(byteCount / 1024.0) + "KB on disk).");
          log.stop(" The size of " + filename + " is " + (int) Math.ceil(byteCount / 1024.0) + "KB on disk");
        

        // if something is wrong
        }catch (IOException ex) {
          System.out.println("Something went wrong with reading file size..");
        }
        log.start();
        
        try {
          BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
          int lines = 0;
          while (reader.readLine() != null)
            lines++; // lines increases if there is more data to be read.
          reader.close();
          System.out.print("The file consists of " + lines + " lines.");
          log.stop(filename + " consist of " + lines + " lines");
        } catch (Exception e) {
          System.out.println("Something went wrong with reading lines. ");
        }

        if (pathToFile.endsWith(".txt")) {
          System.out.println(" Search for a specific word: ");
          String aWord = scan.nextLine();

          // try to scan the pathtofile and will be executed if it ends with .txt
          log.start();
          try (Scanner sc = new Scanner(new File(pathToFile))) {
            int scanner = 0;

            // skip the potential blankspaces
            sc.useDelimiter(" ");
            String word;

            // document will be reading the words in the file.
            while (sc.hasNext()) {
              word = sc.nextLine();

              if (word.toLowerCase().contains(aWord.toLowerCase())) {
                scanner++;// Add int value if it matches.

              }
              ;
            }
            System.out.println("was found " + scanner + " times.");
            log.stop("The term " + "'" + aWord + "'" + " was found " + scanner + " times");
          } catch (IOException ex) {
            System.out.println(ex.getMessage());
          }
        }
      
      } else if (choice == 7) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter file name: ");
        String aName = userInput.nextLine();

        File oldName = new File("../NewFiles/" + aName);
        log.start();
        if (oldName.exists()) {

          System.out.println("Enter new file name: ");
          String nextName = userInput.nextLine();
          File newName = new File("../NewFiles/" + nextName);
          
          if (oldName.renameTo(newName)) { // Rename old name to new name from user input.
            System.out.println("renamed");
          
          } else {
            System.out.println("Error with renaming file");
          }
          log.stop(oldName + " was change to " + newName);
        
        }
      
      }
    
    } while (choice < 8);
    System.out.print("Goodbye!");
  }
}

