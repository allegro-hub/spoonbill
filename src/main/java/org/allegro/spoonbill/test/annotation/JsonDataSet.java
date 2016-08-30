package org.allegro.spoonbill.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.allegro.spoonbill.test.extension.JsonDataSetExtenstion;
import org.spockframework.runtime.extension.ExtensionAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@ExtensionAnnotation(JsonDataSetExtenstion.class)
public @interface JsonDataSet {

    String[]value() default "";
}
