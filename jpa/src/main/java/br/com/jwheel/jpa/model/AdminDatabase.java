package br.com.jwheel.jpa.model;

import javax.inject.Qualifier;
import java.lang.annotation.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Qualifier
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminDatabase
{
}
