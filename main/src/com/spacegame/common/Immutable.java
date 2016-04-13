package com.spacegame.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * This annotation is used to indicate that a type is immutable, meaning that it's internal is fixed after construction.
 */
@Documented
@Target({ElementType.TYPE})
public @interface Immutable {
}
