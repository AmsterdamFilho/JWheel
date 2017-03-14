package br.com.jwheel.cdi;

import javax.inject.Qualifier;
import java.lang.annotation.*;

/**
 * The opposite of {@link javax.enterprise.inject.Default}
 *
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Qualifier
@Target ({ElementType.FIELD, ElementType.TYPE})
@Retention (RetentionPolicy.RUNTIME)
@Documented
public @interface Custom
{
}
