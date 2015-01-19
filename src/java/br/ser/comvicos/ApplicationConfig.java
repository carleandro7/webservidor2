/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ser.comvicos;

import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.*;
/**
 *
 * @author carleandro
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    /**
     *
     * @return Set Class
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.com.resource.GrupoResource.class);
        resources.add(br.com.resource.ImageResource.class);
        resources.add(br.com.resource.JogadorResource.class);
        resources.add(br.com.resource.JogoResource.class);
        resources.add(br.com.resource.MecanicaResource.class);
        resources.add(br.com.resource.SomResource.class);
        resources.add(br.com.resource.TextoResource.class);
        resources.add(br.com.resource.VideoResource.class);
        
    }
    
}
