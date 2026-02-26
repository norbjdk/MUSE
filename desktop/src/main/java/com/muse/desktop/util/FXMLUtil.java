package com.muse.desktop.util;

import com.muse.desktop.MUSE;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class FXMLUtil {
    public static Parent loadFXML(String fileName) throws IOException {
        FXMLLoader loader = new FXMLLoader(MUSE.class.getResource("fxml/" + fileName + ".fxml"));
        return loader.load();
    }
}
