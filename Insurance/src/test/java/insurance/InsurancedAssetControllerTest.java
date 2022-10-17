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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsurancedAssetControllerTest extends InsuranceApplicationTest {

    private MockMvc mockMvc;

    @Autowired
    private InsurancedAssetController insurancedAssetController;

    @Before
    public void setUp() { this.mockMvc = MockMvcBuilders.standaloneSetup(insurancedAssetController).build();}

//    @Test
//    public void testGETInsuranceController() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/insuranced_asset"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void testPOSTInsuranceController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/insuranced_asset")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"monthExpiration\": 1, \"riskFactor\": 0}")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.personalId").value("12245030400"));
    }
}
