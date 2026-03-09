package com.norbjdk.util;

import com.norbjdk.annotation.UseXML;
import com.norbjdk.core.Version;

import java.lang.annotation.AnnotationTypeMismatchException;

public class VersionFactory {
    public static Version createFromAnnotation(Class<?> annotation) {
        if (!annotation.isAnnotationPresent(UseXML.class)) {
            throw new RuntimeException("Class" + UseXML.class.getName() +  " is missing annotation");
        }

        UseXML config = annotation.getAnnotation(UseXML.class);

        final Version version = new Version();

        version.setValue(config.version());

        return version;
    }
}
