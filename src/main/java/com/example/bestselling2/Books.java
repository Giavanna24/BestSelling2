package com.example.bestselling2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Books extends BestSelling implements Serializable {
    private static ArrayList<Books> allBooks = new ArrayList<>();
    private String Author;
    private String Language;
    private String Genre;

    public Books(String name, int year, long sales, String author, String language, String genre) {
        super(name, year, sales);
        Author = author;
        Language = language;
        Genre = genre;
    }

    private static ArrayList<Books> getAllBooks() {
        return allBooks;
    }
    private static ArrayList<Books> setAllBooks() {
        return allBooks;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    @Override
    public String toString() {
        return
                " The best selling item is " + getName() + ", the authors name is " + Author +
                ", the book is in " + Language +
                " and the genre is " + Genre + ". It was made in "  + getYear() + ". It had " + getSales() + " sales. "
                ;
    }
    public static void Read (){
        Scanner sc = null;
        try {
            File text = new File("Books");
             sc = new Scanner(text);
            String line;

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                System.out.println(line);
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("\t");

                String name = lineScanner.next();
                String Author = lineScanner.next();
                String Language = lineScanner.next();
                int Year = lineScanner.nextInt();
                long Sales = lineScanner.nextLong();
                String Genre = lineScanner.next();
                new Books (name, Year, Sales, Author, Language,Genre);

                while(lineScanner.hasNext()) {
                   String chunkofData= lineScanner.next();
                   System.out.println(chunkofData);
                }
            }


        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } finally {
            if (sc!= null) sc.close();
        }
    }
}
