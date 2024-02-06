import arep.taller.HttpServer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HttpServerTest {

    /**
     * Test case for the function `extractMovieTitleFromUri` in the `HttpServer` class.
     *
     * This test case validates that the `extractMovieTitleFromUri` function correctly extracts
     * the movie title from the given URI.
     *
     * @return None
     */
    @Test
    public void testExtractMovieTitleFromUri() {
        assertNotEquals("Guardian", HttpServer.extractMovieTitleFromUri("/formulario.html"));
        assertEquals("Inception", HttpServer.extractMovieTitleFromUri("/formulario.html?title=Inception"));
        assertNotEquals("Interstellar", HttpServer.extractMovieTitleFromUri("/formulario.html?title=interstellar"));
        assertNotEquals("Interstellar", HttpServer.extractMovieTitleFromUri("/formulario.html?otherParam=123&title=Interstellar"));
    }
}