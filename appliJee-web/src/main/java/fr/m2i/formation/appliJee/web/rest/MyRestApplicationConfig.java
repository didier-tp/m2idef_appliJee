package fr.m2i.formation.appliJee.web.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")//partie du milieu des URLs
//apres http://localhost:8080/appliJee-web
//avant les valeurs de @Path() des classes java
public class MyRestApplicationConfig extends Application {

}
