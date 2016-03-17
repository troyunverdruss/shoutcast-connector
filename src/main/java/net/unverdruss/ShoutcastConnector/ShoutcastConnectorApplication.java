package net.unverdruss.ShoutcastConnector;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.unverdruss.ShoutcastConnector.resources.RootResource;
import net.unverdruss.ShoutcastConnector.resources.StationsResource;
import net.unverdruss.ShoutcastConnector.resources.TuneResource;

public class ShoutcastConnectorApplication extends Application<ShoutcastConnectorConfiguration> {
    public static void main(String[] args) throws Exception {
        new ShoutcastConnectorApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<ShoutcastConnectorConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(ShoutcastConnectorConfiguration configuration, Environment environment) {
        final RootResource rootResource = new RootResource();
        environment.jersey().register(rootResource);

        final StationsResource stationsResource = new StationsResource(configuration);
        environment.jersey().register(stationsResource);

        final TuneResource tuneResource = new TuneResource(stationsResource);
        environment.jersey().register(tuneResource);

//        final ShoutcastConnectorHealthCheck healthCheck =
//                new ShoutcastConnectorHealthCheck(configuration.getTemplate());
//        environment.healthChecks().register("template", healthCheck);
//        environment.jersey().register(resource);
    }
}
