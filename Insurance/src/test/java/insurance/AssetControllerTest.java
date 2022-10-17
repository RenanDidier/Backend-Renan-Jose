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

//    @Test
//    public void testGETAssetController() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/assets"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void testGETByIdAssetController() throws Exception {
//        Integer id = 1;
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/assets/" + id))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void testPOSTAssetController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/assets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"itemName\": \"name test\", \"estimatedValue\": 500.0 , \"aliquot\": 0.1}")
        ).andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(jsonPath("$.id").value(1))
         .andExpect(jsonPath("$.itemName").value("name test"))
         .andExpect(jsonPath("$.estimatedValue").value(500.0))
         .andExpect(jsonPath("$.aliquot").value(0.1));
    }

    @Test(expected = NestedServletException.class)
    public void testPOSTAssetControllerFailItemName() throws Exception {
            this.mockMvc.perform(MockMvcRequestBuilders.post("/assets")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"id\": 1, \"itemName\": \"lfjanknsfjkansdkjnasjkndkjfsdfsdfsdfasndakjsndjanksdjnkasnd\", \"estimatedValue\": 500.0 , \"aliquot\": 0.1}")
            );
    }

    @Test(expected = NestedServletException.class)
    public void testPOSTAssetControllerFailAliquot() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/assets")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"itemName\": \"name test\", \"estimatedValue\": 500.0 , \"aliquot\": -1}")
        );
    }

    @Test
    public void testPUTAssetController() throws Exception {
        Integer id = 1;
        this.mockMvc.perform(MockMvcRequestBuilders.put("/assets/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"itemName\": \"name test\", \"estimatedValue\": 500.0 , \"aliquot\": 0.1}")
        ).andExpect(MockMvcResultMatchers.status().isOk())
         .andDo(print());
    }

    @Test(expected = NestedServletException.class)
    public void testPUTAssetControllerFailItemName() throws Exception {
        Integer id = 1;
        this.mockMvc.perform(MockMvcRequestBuilders.put("/assets/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"itemName\": \"lfjanknsfjkansdkjnasjkndkjfsdfsdfsdfasndakjsndjanksdjnkasnd\", \"estimatedValue\": 500.0 , \"aliquot\": 0.1}")
        );
    }

    @Test(expected = NestedServletException.class)
    public void testPUTAssetControllerFailAliquot() throws Exception {
        Integer id = 1;
        this.mockMvc.perform(MockMvcRequestBuilders.put("/assets/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"itemName\": \"name test\", \"estimatedValue\": 500.0 , \"aliquot\": -1}")
                );
    }

    @Test
    public void testDELETEAssetController() throws Exception {
        Integer id = 1;
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/assets/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
