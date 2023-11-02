package com.msgfoundation.anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface BPMNTask {
   String type() default ""; // Atributo value como un arreglo de strings
   String name() default ""; // Atributo value como un arreglo de strings

}
