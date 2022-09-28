package insurance;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssetControllerTest extends InsuranceApplicationTest{

    private MockMvc mockMvc;

    @Autowired
    private AssetController assetController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(assetController).build();
    }

    @Test
    public void testGETAssetController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/assets")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPOSTAssetController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/assets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 5, \"itemName\": \"name test\", \"estimatedValue\": 500.0 , \"aliquot\": 0.1}")
        ).andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(jsonPath("$.id").value(5))
         .andExpect(jsonPath("$.itemName").value("name test"))
         .andExpect(jsonPath("$.estimatedValue").value(500.0))
         .andExpect(jsonPath("$.aliquot").value(0.1));
    }

    @Test(expected = NestedServletException.class)
    public void testPOSTAssetControllerFailItemName() throws Exception {
            this.mockMvc.perform(MockMvcRequestBuilders.post("/assets")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"id\": 5, \"itemName\": \"lfjanknsfjkansdkjnasjkndkjfsdfsdfsdfasndakjsndjanksdjnkasnd\", \"estimatedValue\": 500.0 , \"aliquot\": 0.1}")
            ).andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test(expected = NestedServletException.class)
    public void testPOSTAssetControllerFailAliquot() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/assets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 5, \"itemName\": \"name test\", \"estimatedValue\": 500.0 , \"aliquot\": -1}")
        ).andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }


}
