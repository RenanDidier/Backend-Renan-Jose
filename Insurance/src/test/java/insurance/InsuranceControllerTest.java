package insurance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsuranceControllerTest extends InsuranceApplicationTest {

    private MockMvc mockMvc;

    @Autowired
    private InsuranceController insuranceController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(insuranceController).build();
    }

    @Test
    public void testGETInsuranceController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/insurance"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testPOSTInsuranceController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/insurance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"date\": \"2023-04-13\", \"personalId\": \"12245030400\"}")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.personalId").value("12245030400"));
    }

    @Test
    public void testGETByIdInsuranceController() throws Exception {
        Integer id = 1;
        this.mockMvc.perform(MockMvcRequestBuilders.get("/insurance/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test(expected = NestedServletException.class)
    public void testPOSTInsuranceControllerFailPersonalIdExplode() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/insurance")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"date\": \"2023-04-13\", \"personalId\": \"1222245030400\"}")
        );
    }

    @Test(expected = NestedServletException.class)
    public void testPOSTInsuranceControllerFailPersonalIdRepeated() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/insurance")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"date\": \"2023-04-13\", \"personalId\": \"1111111111\"}")
        );
    }

    @Test(expected = NestedServletException.class)
    public void testPOSTInsuranceControllerFailDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/assets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"date\": \"1900-04-13\", \"personalId\": \"12245030400\"}")
        );
    }

    @Test
    public void testDELETEInsuranceController() throws Exception {
        Integer id = 1;
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/assets/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
