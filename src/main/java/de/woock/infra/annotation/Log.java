package de.woock.infra.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Log {
	boolean printParamsValues()              default false;
	String  callMethodWithNoParamsToString() default "toString";
}
