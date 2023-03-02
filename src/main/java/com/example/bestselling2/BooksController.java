package com.example.bestselling2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BooksController {
    public Label bestsellingbooks;
    public TableView allBooksView;
    public TableColumn<Books, String> name;
    public TableColumn<Books, Integer> year;
    public TableColumn<Books, Long> sales;
    public TableColumn<Books, String> author;
    public TableColumn<Books, String> language;
    public TableColumn<Books, String> Genre;

    public void initialize() {
        restoreOrReadData();

        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        year.setCellValueFactory(new PropertyValueFactory<>("Year"));
        sales.setCellValueFactory(new PropertyValueFactory<>("Sales"));
        author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        language.setCellValueFactory(new PropertyValueFactory<>("Language"));
        Genre.setCellValueFactory(new PropertyValueFactory<>("Genre"));

        name.setCellFactory(TextFieldTableCell.forTableColumn());
        year.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        sales.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        author.setCellFactory(TextFieldTableCell.forTableColumn());
        language.setCellFactory(TextFieldTableCell.forTableColumn());
        Genre.setCellFactory(TextFieldTableCell.forTableColumn());

        // add all the set on edits here
        name.setOnEditCommit(
                (TableColumn.CellEditEvent<Books, String> t) -> {
                    int tableRow = t.getTablePosition().getRow();
                    Books filmFromTableRow = t.getTableView().getItems().get(tableRow);
                    filmFromTableRow.setName(t.getNewValue());
                });
        year.setOnEditCommit(
                (TableColumn.CellEditEvent<Books, Integer> t) -> {
                    int tableRow = t.getTablePosition().getRow();
                    Books filmFromTableRow = t.getTableView().getItems().get(tableRow);
                    filmFromTableRow.setYear(t.getNewValue());
                });
        sales.setOnEditCommit(
                (TableColumn.CellEditEvent<Books, Long> t) -> {
                    int tableRow = t.getTablePosition().getRow();
                    Books filmFromTableRow = t.getTableView().getItems().get(tableRow);
                    filmFromTableRow.setSales(t.getNewValue());
                });
        author.setOnEditCommit(
                (TableColumn.CellEditEvent<Books, String> t) -> {
                    int tableRow = t.getTablePosition().getRow();
                    Books filmFromTableRow = t.getTableView().getItems().get(tableRow);
                    filmFromTableRow.setAuthor(t.getNewValue());
                });
        language.setOnEditCommit(
                (TableColumn.CellEditEvent<Books, String> t) -> {
                    int tableRow = t.getTablePosition().getRow();
                    Books filmFromTableRow = t.getTableView().getItems().get(tableRow);
                    filmFromTableRow.setLanguage(t.getNewValue());
                });
        Genre.setOnEditCommit(
                (TableColumn.CellEditEvent<Books, String> t) -> {
                    int tableRow = t.getTablePosition().getRow();
                    Books filmFromTableRow = t.getTableView().getItems().get(tableRow);
                    filmFromTableRow.setGenre(t.getNewValue());
                });
    }

    public void saveData() throws Exception {
        FileOutputStream fileOut = new FileOutputStream("SavedBooksObjects");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        ArrayList<String> temporaryList = new ArrayList<>(allBooksView.getItems());
        out.writeObject(temporaryList);

        out.close();
        fileOut.close();
        System.out.println("Saved data");
    }

    public void restoreOrReadData() {
        // First try to restore saved objects, but then read data file if that fails.
        try {
            FileInputStream fileIn = new FileInputStream("SavedBooksObjects");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            // Restored saved objects into Film's arrayList of all films
            Books.setAllBestSelling((ArrayList<BestSelling>) in.readObject());
            in.close();
            fileIn.close();
        } catch (Exception exception) {
            // Restoring saved objects failed, so read data from text file instead
            Books.Read();
        }
        ArrayList<BestSelling> temporaryList = (ArrayList<BestSelling>) Books.getAllBestSelling();
        // Turn the read data's ArrayList into an ObservableList
        ObservableList temporaryObservableList = FXCollections.observableArrayList(temporaryList);
        // Make that ObservableList the list for my TableView
        allBooksView.setItems(temporaryObservableList);
    }
}
