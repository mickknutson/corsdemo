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
@ActiveProfiles("corsFilterBean")
public class CORSFilterTests {

    @Autowired
    private MockMvc mvc;


    //-----------------------------------------------------------------------//

    @Test
    public void test_corsFilterBean() throws Exception {

        MvcResult result = mvc
                .perform(get("/api"))
                .andExpect(status().isOk())

                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, X-Auth-Token, X-Csrf-Token, WWW-Authenticate, Authorization"))
                .andExpect(header().string("Access-Control-Expose-Headers", "custom-token1, custom-token2"))
                .andExpect(header().string("Access-Control-Allow-Credentials", "false"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"))

                .andExpect(content().string(Matchers.containsString("{Hello API}")))

                .andDo(print())
                .andReturn()
        ;

        MockHttpServletResponse mockResponse = result.getResponse();

        assertThat(mockResponse.getContentType()).contains("charset=ISO-8859-1");

        Collection<String> responseHeaders = mockResponse.getHeaderNames();
        assertThat(responseHeaders).isNotNull();
        assertThat(1).isEqualTo(1);
        assertThat(responseHeaders.size()).isBetween(5, 15);

    }




    //-----------------------------------------------------------------------//


} // The End
