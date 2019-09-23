package com.moddb.dw_apistats.dw_apistats;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import com.moddb.dw_apistats.dw_apistats.conf.HelloWorldConfiguration;
import com.moddb.dw_apistats.dw_apistats.health.TemplateHealthCheck;
import com.moddb.dw_apistats.dw_apistats.model.Suscriber;
import com.moddb.dw_apistats.dw_apistats.resource.HelloWorldResource;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pnajda
 */
public class HelloWorldService extends Application<HelloWorldConfiguration> {

    public static void main(String args[]) throws Exception {
        new HelloWorldService().run(args);
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) throws IOException, TimeoutException {

        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.healthChecks().register("example health check", new TemplateHealthCheck(template));
        environment.jersey().register(new HelloWorldResource(template, defaultName));
        
        Suscriber suscriber = new Suscriber();
        suscriber.suscribeAddKudoQty();
    }

}
