package net.unverdruss.ShoutcastConnector.support;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import net.unverdruss.ShoutcastConnector.ShoutcastConnectorConfiguration;
import net.unverdruss.ShoutcastConnector.ShoutcastConnectorApplication;

public class ShoutcastConnectorAppRule extends DropwizardAppRule<ShoutcastConnectorConfiguration> {
    public ShoutcastConnectorAppRule() {
        super(ShoutcastConnectorApplication.class, ResourceHelpers.resourceFilePath("shoutcast-connector-config-test.yml"));
    }

    public String getBaseUrl() {
        return String.format("http://localhost:%d", this.getLocalPort());
    }
}
