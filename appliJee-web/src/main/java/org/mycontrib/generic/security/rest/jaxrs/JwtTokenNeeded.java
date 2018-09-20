package org.mycontrib.generic.security.rest.jaxrs;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@javax.ws.rs.NameBinding //Pour binding entre presence de @JwtTokenNeeded
						 //et declenchement filtre ContainerRequestFilter , @Provider
                         //depuis JAX-RS2 et JEE7
public @interface JwtTokenNeeded {

}
