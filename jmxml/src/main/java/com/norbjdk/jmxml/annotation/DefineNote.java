package com.norbjdk.jmxml.annotation;

import com.norbjdk.jmxml.model.music.Pitch;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DefineNote {
    char step();
    int octave();

    int duration();
    int voice();
    String half();
    String stem();
    int staff();
}
