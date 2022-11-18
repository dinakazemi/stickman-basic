import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import stickman.LevelProcessor;
import stickman.model.ErrorLogger;
import stickman.model.JsonFileReader;
import static org.junit.Assert.assertEquals;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.util.ArrayList;
import java.util.HashMap;

/** Tests different scenarios for reading the json file. Tests error logger as well */
public class FileReadingScenariosTest {
  private String validPath;
  private String invalidPath;
  private String invalidLevelNameType;
  private JsonFileReader jsonFileReader;
  private static final double TOLERANCE = 0.01;
  private String missingLevelName;
  private String invalidLifeCount;
  private String missingLifeCount;
  private String invalidStickmanSize;
  private String missingStickmanSize;
  private String invalidStickmanSizeType;
  private String invalidStickmanPos;
  private String missingStickmanPos;
  private String invalidStickmanPosType;
  private String invalidCloudVelocity;
  private String missingCloudVelocity;
  private String invalidCloudVelocityType;
  private String missingEnemyInfo;
  private String invalidEnemyInfo;
  private String missingPlatformInfo;
  private String invalidPlatformInfo;
  private String missingLevelLength;
  private String invalidLevelLength;
  private String invalidLevelLengthType;

  public FileReadingScenariosTest() {}
  /**
   * Sets up the required objects for testing, namely different file paths, json file reader, and
   * error logger
   */
  @Before
  public void setUp() {
    this.validPath = "src/main/resources/level-1.json";
    this.invalidPath = "invalid.path";
    this.invalidLevelNameType = "src/main/resources/invalid_level_name_type.json";
    this.jsonFileReader = new JsonFileReader();
    this.missingLevelName = "src/main/resources/missing_level_name.json";
    this.invalidLifeCount = "src/main/resources/invalid_life_count.json";
    this.missingLifeCount = "src/main/resources/missing_life_count.json";
    this.invalidStickmanSize = "src/main/resources/invalid_stickman_size.json";
    this.missingStickmanSize = "src/main/resources/missing_stickman_size.json";
    this.invalidStickmanSizeType = "src/main/resources/invalid_stickman_size_type.json";
    this.invalidStickmanPos = "src/main/resources/invalid_stickman_pos.json";
    this.missingStickmanPos = "src/main/resources/missing_stickman_pos.json";
    this.invalidStickmanPosType = "src/main/resources/invalid_stickman_pos_type.json";
    this.invalidCloudVelocity = "src/main/resources/invalid_cloud_velocity.json";
    this.missingCloudVelocity = "src/main/resources/missing_cloud_velocity.json";
    this.invalidCloudVelocityType = "src/main/resources/invalid_cloud_velocity_type.json";
    this.missingEnemyInfo = "src/main/resources/missing_enemy_info.json";
    this.invalidEnemyInfo = "src/main/resources/invalid_enemy_info.json";
    this.missingPlatformInfo = "src/main/resources/missing_platform_info.json";
    this.invalidPlatformInfo = "src/main/resources/invalid_platform_info.json";
    this.missingLevelLength = "src/main/resources/missing_level_length.json";
    this.invalidLevelLength = "src/main/resources/invalid_level_length.json";
    this.invalidLevelLengthType = "src/main/resources/invalid_level_length_type.json";
  }

  @Rule public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  /** Tests the scenario where an invalid file path is provided. json file reader is expected to */
  @Test
  public void invalidPathTest() {
    exit.expectSystemExitWithStatus(-1);
    jsonFileReader.read(invalidPath);
  }

  /** Tests the scenario where there is a file path with an invalid level name type. */
  @Test
  public void invalidLevelNameType() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(invalidLevelNameType);
    jsonFileReader.findLevelName(result);
  }

  /** Tests a file with missing file name */
  @Test
  public void missingLevelName() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(missingLevelName);
    jsonFileReader.findLevelName(result);
  }

  /** Tests a file with invalid life count value (a string) */
  @Test
  public void invalidLifeCount() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(invalidLifeCount);
    jsonFileReader.findCharacterLifeCount(result);
  }

  /** Tests a file with missing life count value */
  @Test
  public void missingLifeCount() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(missingLifeCount);
    jsonFileReader.findCharacterLifeCount(result);
  }

  /** Tests a file with invalid stickman size srting */
  @Test
  public void invalidStickmanSize() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(invalidStickmanSize);
    jsonFileReader.findCharacterSize(result);
  }

  /** Tests a file with no stickman size provided */
  @Test
  public void missingStickmanSize() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(missingStickmanSize);
    jsonFileReader.findCharacterSize(result);
  }

  /** Tests a file with an invalid size type for the stickman (not a string) */
  @Test
  public void invalidStickmanSizeType() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(invalidStickmanSizeType);
    jsonFileReader.findCharacterSize(result);
  }

  /** Tests a file with an invalid starting x pos (negative x) */
  @Test
  public void invalidStickmanPosX() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(invalidStickmanPos);
    jsonFileReader.findCharacterXPos(result);
  }

  /** Tests a file with missing stickman position */
  @Test
  public void missingStickmanPos() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(missingStickmanPos);
    jsonFileReader.findCharacterXPos(result);
  }

  /** Tests a file with an invalid starting x position (not a map) */
  @Test
  public void invalidStickmanPosType() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(invalidStickmanPosType);
    jsonFileReader.findCharacterXPos(result);
  }

  /** Tests a file with an invalid cloud velocity (negative velocity) */
  @Test
  public void invalidCloudVelocity() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(invalidCloudVelocity);
    jsonFileReader.findCloudVelocity(result);
  }

  /** Tests a file with a missing cloud velocity */
  @Test
  public void missingCloudVelocity() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(missingCloudVelocity);
    jsonFileReader.findCloudVelocity(result);
  }

  /** Tests a file with an invalid cloud velocity type (a string) */
  @Test
  public void invalidCloudVelocityType() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(invalidCloudVelocityType);
    jsonFileReader.findCloudVelocity(result);
  }

  /** Tests a file with missing enemy info */
  @Test
  public void missingEnemyInfo() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(missingEnemyInfo);
    jsonFileReader.findEnemies(result);
  }

  /** Tests a file with invalid enemy information type (not a HashMap<String,ArrayList>) */
  @Test
  public void invalidEnemyInfo() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(invalidEnemyInfo);
    jsonFileReader.findEnemies(result);
  }

  /** Tests a file with missing platform info */
  @Test
  public void missingPlatformInfo() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(missingPlatformInfo);
    jsonFileReader.findPlatforms(result);
  }

  /** Tests a file with a invalid platform info type (not a HashMap<String,ArrayList>) */
  @Test
  public void invalidPlatformInfo() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(invalidPlatformInfo);
    jsonFileReader.findPlatforms(result);
  }

  /** Tests a file with missing level length (final x position of the character) */
  @Test
  public void missingLevelLength() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(missingLevelLength);
    jsonFileReader.findLevelLength(result);
  }

  /** Tests a file with an invalid level length value (negative finish line) */
  @Test
  public void invalidLevelLength() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(invalidLevelLength);
    jsonFileReader.findLevelLength(result);
  }

  /** Tests a file with an invalid length value type */
  @Test
  public void invalidLevelLengthType() {
    exit.expectSystemExitWithStatus(-1);
    JSONObject result = jsonFileReader.read(invalidLevelLengthType);
    jsonFileReader.findLevelLength(result);
  }

  /** Tests a valid file path */
  @Test
  public void validFilePath() {
    JSONObject result = jsonFileReader.read(validPath);
    assertEquals(jsonFileReader.findLevelName(result), "1");
    assertEquals(jsonFileReader.findLevelLength(result), 1700.0, TOLERANCE);
    assertEquals(jsonFileReader.findCloudVelocity(result), 3.2, TOLERANCE);
    assertEquals(jsonFileReader.findCharacterXPos(result), 20.0, TOLERANCE);
    assertEquals(jsonFileReader.findCharacterSize(result), "tiny");
    assertEquals(jsonFileReader.findCharacterLifeCount(result), 3);
    HashMap<String, ArrayList> expected = new HashMap<String, ArrayList>();

    ArrayList<ArrayList> steadyEnemy = new ArrayList<>();
    ArrayList<Double> steadyEnemyFirstArray = new ArrayList<Double>();
    ArrayList<Double> steadyEnemySecondArray = new ArrayList<>();

    ArrayList<ArrayList> movingEnemy = new ArrayList<>();
    ArrayList<Double> movingEnemyFirstArray = new ArrayList<Double>();

    ArrayList<ArrayList> disappearingEnemy = new ArrayList<>();
    ArrayList<Double> disappearingEnemyFirstArray = new ArrayList<Double>();

    ArrayList<ArrayList> angryEnemy = new ArrayList<>();
    ArrayList<Double> angryEnemyFirstArray = new ArrayList<>();

    steadyEnemyFirstArray.add(100.0);
    steadyEnemyFirstArray.add(250.0);
    steadyEnemyFirstArray.add(50.0);
    steadyEnemyFirstArray.add(50.0);
    steadyEnemySecondArray.add(900.0);
    steadyEnemySecondArray.add(200.0);
    steadyEnemySecondArray.add(20.0);
    steadyEnemySecondArray.add(20.0);
    steadyEnemy.add(steadyEnemyFirstArray);
    steadyEnemy.add(steadyEnemySecondArray);

    movingEnemyFirstArray.add(700.0);
    movingEnemyFirstArray.add(250.0);
    movingEnemyFirstArray.add(50.0);
    movingEnemyFirstArray.add(50.0);
    movingEnemyFirstArray.add(1.0);
    movingEnemyFirstArray.add(0.0);
    movingEnemy.add(movingEnemyFirstArray);

    disappearingEnemyFirstArray.add(1500.0);
    disappearingEnemyFirstArray.add(30.0);
    disappearingEnemyFirstArray.add(50.0);
    disappearingEnemyFirstArray.add(50.0);
    disappearingEnemyFirstArray.add(0.0);
    disappearingEnemyFirstArray.add(1.0);
    disappearingEnemy.add(disappearingEnemyFirstArray);

    angryEnemyFirstArray.add(0.0);
    angryEnemyFirstArray.add(50.0);
    angryEnemyFirstArray.add(50.0);
    angryEnemyFirstArray.add(50.0);
    angryEnemyFirstArray.add(0.0);
    angryEnemyFirstArray.add(1.0);
    angryEnemy.add(angryEnemyFirstArray);

    expected.put("SteadyEnemy", steadyEnemy);
    expected.put("MovingEnemy", movingEnemy);
    expected.put("DisappearingEnemy", disappearingEnemy);
    expected.put("AngryEnemy", angryEnemy);

    assertEquals(jsonFileReader.findEnemies(result), expected);

    HashMap<String, ArrayList> expected_platform = new HashMap<>();

    ArrayList<ArrayList> normalPlatform = new ArrayList<>();
    ArrayList<Double> normalPlatformFirstArray = new ArrayList<>();
    ArrayList<Double> normalPlatformSecondArray = new ArrayList<>();
    ArrayList<Double> normalPlatformThirdArray = new ArrayList<>();

    normalPlatformFirstArray.add(300.0);
    normalPlatformFirstArray.add(240.0);
    normalPlatformFirstArray.add(70.0);
    normalPlatformFirstArray.add(70.0);

    normalPlatformSecondArray.add(1000.0);
    normalPlatformSecondArray.add(250.0);
    normalPlatformSecondArray.add(50.0);
    normalPlatformSecondArray.add(50.0);

    normalPlatformThirdArray.add(500.0);
    normalPlatformThirdArray.add(250.0);
    normalPlatformThirdArray.add(100.0);
    normalPlatformThirdArray.add(50.0);

    normalPlatform.add(normalPlatformFirstArray);
    normalPlatform.add(normalPlatformSecondArray);
    normalPlatform.add(normalPlatformThirdArray);
    expected_platform.put("NormalPlatform", normalPlatform);
    assertEquals(jsonFileReader.findPlatforms(result),expected_platform);
  }
  //  /** Tests the scenario where a valid file path has been provided. */
  //  @Test
  //  public void validLevelName() {
  //    JSONObject result = jsonFileReader.read(validPath);
  //    assertEquals(errorLogger.getMessage(), null);
  //    assertEquals(jsonFileReader.findLevelName(result), "1");
  //  }
  //
  //  @Test
  //  public void validLevelLifeCount() {
  //    JSONObject result = jsonFileReader.read(validPath);
  //    assertEquals(errorLogger.getMessage(), null);
  //    assertEquals(jsonFileReader.findCharacterLifeCount(result), 3);
  //  }
  //
  //  @Test
  //  public void validStickmanSize() {
  //    JSONObject result = jsonFileReader.read(validPath);
  //    assertEquals(errorLogger.getMessage(), null);
  //    assertEquals(jsonFileReader.findCharacterSize(result), "tiny");
  //  }
  //
  //  @Test
  //  public void validStickmanPos() {
  //    JSONObject result = jsonFileReader.read(validPath);
  //    assertEquals(errorLogger.getMessage(), null);
  //    assertEquals(jsonFileReader.findCharacterXPos(result), 20.0, TOLERANCE);
  //  }
  //
  //  @Test
  //  public void validCloudVelocity() {
  //    JSONObject result = jsonFileReader.read(validPath);
  //    assertEquals(errorLogger.getMessage(), null);
  //    assertEquals(jsonFileReader.findCloudVelocity(result), 3.2, TOLERANCE);
  //  }
  //
  //  @Test
  //  public void validLevelLength() {
  //    JSONObject result = jsonFileReader.read(validPath);
  //    assertEquals(errorLogger.getMessage(), null);
  //    assertEquals(jsonFileReader.findLevelLength(result), 1000.0, TOLERANCE);
  //  }
  //
  //  @Test
  //  public void validEnemyList() {
  //    JSONObject result = jsonFileReader.read(validPath);
  //    assertEquals(errorLogger.getMessage(), null);

  //  }
  //
  //  @Test
  //  public void validPlatformList() {
  //    JSONObject result = jsonFileReader.read(validPath);
  //    assertEquals(errorLogger.getMessage(), null);

  //  }
  //
  //  @Test
  //  public void invalidLevelNameType (){
  //    JSONObject result = jsonFileReader.read(invalidLevelNameType);
  //    assertEquals(errorLogger.getMessage(), "Please use a String as your file name");
  //    assertEquals(jsonFileReader.findPlatforms(result),null);
  //  }
}
