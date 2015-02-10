/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.great.comvicos;

import java.util.Set;
import javax.ws.rs.core.Application;

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
        resources.add(br.com.great.resource.GrupoResource.class);
        resources.add(br.com.great.resource.ImageResource.class);
        resources.add(br.com.great.resource.JogadorResource.class);
        resources.add(br.com.great.resource.JogoResource.class);
        resources.add(br.com.great.resource.MecanicaResource.class);
        resources.add(br.com.great.resource.ServidorResource.class);
        resources.add(br.com.great.resource.SomResource.class);
        resources.add(br.com.great.resource.TextoResource.class);
        resources.add(br.com.great.resource.VideoResource.class);
        
    }
    
}
