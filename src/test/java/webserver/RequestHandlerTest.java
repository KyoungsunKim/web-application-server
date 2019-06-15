package webserver;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;

public class RequestHandlerTest {

    private HttpClient httpClient;

    @Before
    public void init() {
        httpClient = HttpClients.createDefault();
    }

    @Test
    public void index_html_요청_테스트() throws IOException {
        // Given
        HttpGet httpGet = new HttpGet("http://localhost:8080/index.html");

        // When
        HttpResponse httpResponse = httpClient.execute(httpGet);
        String responseText = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(responseText);

        // Then
        assertEquals(new String(Files.readAllBytes(new File("./webapp/index.html").toPath())),
                responseText);
    }
}
