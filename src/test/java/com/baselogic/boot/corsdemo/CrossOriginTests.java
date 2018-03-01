package com.baselogic.boot.corsdemo;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collection;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Mick Knutson
 *
 */
@DirtiesContext

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CrossOriginTests {


    @Autowired
    private MockMvc mvc;


    //-----------------------------------------------------------------------//

    @Test
    public void test_CrossOrigin() throws Exception {

        MvcResult result = mvc
                .perform(get("/crossOrigin"))
                .andExpect(status().isOk())

                .andExpect(header().string("X-Content-Type-Options", is("nosniff")))
                .andExpect(header().string("X-XSS-Protection", is("1; mode=block")))
                .andExpect(header().string("Cache-Control", is("no-cache, no-store, max-age=0, must-revalidate")))
                .andExpect(header().string("Pragma", is("no-cache")))
                .andExpect(header().string("Expires", is("0")))
                .andExpect(header().string("X-Frame-Options", is("DENY")))
                .andExpect(header().string("X-Application-Context", is("application:-1")))
                .andExpect(header().string("Content-Type", is("text/plain;charset=ISO-8859-1")))
                .andExpect(header().string("Content-Length", is("23")))

                .andExpect(content().string(Matchers.containsString("{Hello crossOrigin API}")))

                .andDo(print())
                .andReturn()
                ;


        MockHttpServletResponse mockResponse = result.getResponse();

        assertThat(mockResponse.getContentType()).contains("charset=ISO-8859-1");

        Collection<String> responseHeaders = mockResponse.getHeaderNames();
        assertThat(responseHeaders).isNotNull();
        assertThat(responseHeaders.size()).isBetween(1, 9);

        Iterator<String> keys = responseHeaders.iterator();


        String key = keys.next();
        assertThat(key).contains("X-Content-Type-Options");
        assertThat(mockResponse.getHeader(key)).contains("nosniff");
        key = keys.next();
        assertThat(key).contains("X-XSS-Protection");
        assertThat(mockResponse.getHeader(key)).contains("1; mode=block");
        key = keys.next();
        assertThat(key).contains("Cache-Control");
        assertThat(mockResponse.getHeader(key)).contains("no-cache, no-store, max-age=0, must-revalidate");
        key = keys.next();
        assertThat(key).contains("Pragma");
        assertThat(mockResponse.getHeader(key)).contains("no-cache");
        key = keys.next();
        assertThat(key).contains("Expires");
        assertThat(mockResponse.getHeader(key)).contains("0");
//5
        key = keys.next();
        assertThat(key).contains("X-Frame-Options");
        assertThat(mockResponse.getHeader(key)).contains("DENY");
        key = keys.next();
        assertThat(key).contains("X-Application-Context");
        assertThat(mockResponse.getHeader(key)).contains("application:-1");
        key = keys.next();
        assertThat(key).contains("Content-Type");
        assertThat(mockResponse.getHeader(key)).contains("text/plain;charset=ISO-8859-1");
        key = keys.next();
        assertThat(key).contains("Content-Length");
        assertThat(mockResponse.getHeader(key)).contains("23");
//        key = keys.next();
//        assertThat(key).contains("Access-Control-Expose-Headers");
//        assertThat(mockResponse.getHeader(key)).contains("custom-token1, custom-token2");
//10

    }




    //-----------------------------------------------------------------------//


} // The End
