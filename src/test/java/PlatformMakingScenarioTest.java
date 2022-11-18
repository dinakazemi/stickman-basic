import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import stickman.model.JsonFileReader;
import stickman.model.PlatformFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Tests different scenarios for making platforms
 */
public class PlatformMakingScenarioTest {
    private JsonFileReader jsonFileReader;
    private String invalidKeyPath;
    private PlatformFactory platformFactory;

    @Before
    public void setUp (){
        this.jsonFileReader = new JsonFileReader();
        invalidKeyPath = "src/main/resources/invalid_key.json";
        this.platformFactory = new PlatformFactory();
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

//    @Test
//    public void invalidKey(){
//        exit.expectSystemExitWithStatus(-1);
//        JSONObject result = jsonFileReader.read(invalidKeyPath);
//        HashMap<String,ArrayList> platforms = jsonFileReader.findEnemies(result);
//        platformFactory.make(platforms,100.0);
//    }
}
