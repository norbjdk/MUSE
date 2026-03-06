package com.norbjdk.jmxml.util;

import com.norbjdk.jmxml.model.music.MusicModel;
import com.norbjdk.jmxml.model.music.ScorePartwise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MXMLUtil {
    private MXMLUtil() {}

    public static List<Field> getMusicFieldObjects(Class<? extends MusicModel> musicModel) {
        return List.of(musicModel.getDeclaredFields());
    }

    public static List<String> getMusicFieldNames(Class<? extends MusicModel> musicModel) {
        return Arrays.stream(musicModel.getDeclaredFields())
                .map(Field::getName)
                .toList();
    }

    public static void main(String [] args) {
        System.out.println(getMusicFieldObjects(ScorePartwise.class));
        System.out.println(getMusicFieldNames(ScorePartwise.class));
    }
}
