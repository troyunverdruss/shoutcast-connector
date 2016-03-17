package net.unverdruss.ShoutcastConnector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ShoutcastConnectorApplicationTest extends TestCase {
    public ShoutcastConnectorApplicationTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ShoutcastConnectorApplicationTest.class);
    }

    public void testApp() {
        assertTrue(true);
    }
}
