/**
 * Project Name:common-util File Name:Contended.java Package
 * Name:io.github.ljwlgl.testtools.internal.vm.annotation Date:2023年2月23日下午5:57:16 Copyright (c)
 * 2023, Luozi All Rights Reserved.
 * 
 */

package org.example.sun.vm.annotation;

/**
 * ClassName:Contended <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2023年2月23日 下午5:57:16 <br/>
 * 
 * @author xiaoy
 * @version
 * @since JDK 1.8
 * @see
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Contended {
  /**
   * Contended group
   */
  String value() default "";
}

