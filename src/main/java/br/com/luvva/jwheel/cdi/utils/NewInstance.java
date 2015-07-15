package br.com.luvva.jwheel.cdi.utils;

import javax.inject.Qualifier;
import java.lang.annotation.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Qualifier
@Target ({ElementType.FIELD, ElementType.TYPE})
@Retention (RetentionPolicy.RUNTIME)
@Documented
public @interface NewInstance
{
}
