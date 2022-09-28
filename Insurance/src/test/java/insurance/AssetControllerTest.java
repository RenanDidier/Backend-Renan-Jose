package insurance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
    public void testGetAssetController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/assets")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
