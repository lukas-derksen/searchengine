/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoekmachine.pkg2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lukas
 */
public class Search {
    private ObservableList<File> files;
    private String word;
    private String searchPath;
    
    public Search(String word, String path) {
        this.files = FXCollections.observableArrayList();
        this.word = word;
        this.searchPath = path;
    }
    
    public ObservableList<File> getList() {
        return files;
    }
    
    public void clearList() {
        files.clear();
    }
    
    public void searchLoop(String path) {
        File file = new File(path);
        File[] dir = file.listFiles();
        if (dir != null) {
            for (File f : dir) {
                if (f != null) {
                    if (f.isDirectory()) {
                        if (f.getName().contains(word)) {
                            files.add(f);
                        }
                        searchLoop(f.getPath());
                    }
                    else {
                        if (f.getName().contains(word)) {
                            files.add(f);
                        }
                    }
                }
            }
        }
    }
}
