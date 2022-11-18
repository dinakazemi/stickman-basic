package stickman.model;
/**
 * Creates an object that reads the content of the json file and extract the necessary information
 * to initialise the game engine
 */
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Long;

public class JsonFileReader {

  // private String fileName;
  private ErrorLogger eLog;

  public JsonFileReader() {
    // this.fileName = fileName;
    this.eLog = new ErrorLogger();
  }

  /**
   * Read the content of the json file and parse them into JSONObject. Records an error to the
   * ErrorLogger if an error occurs
   *
   * @param fileName the name of the json file as a String - eg "example.json"
   * @return returns the content of the json file as JSONObject, or null if there is an error
   */
  public JSONObject read(String fileName) {
    JSONParser parser = new JSONParser();
    try (FileReader reader = new FileReader(fileName)) {
      Object obj = parser.parse(reader);
      JSONObject jo = (JSONObject) obj;
      return jo;
    } catch (FileNotFoundException ex) {
      eLog.setMessage("Please check that the starting json file exists.");
      eLog.log();
      return null;
    } catch (IOException ex) {
      eLog.setMessage(ex.toString());
      eLog.log();
      return null;
    } catch (ParseException pe) {
      eLog.setMessage(pe.toString());
      eLog.log();
      return null;
    }
  }

  /**
   * Returns the error logger created in the json file reader object for recording error.Designed
   * only for using to test the error logger object
   *
   * @return The ErrorLogger in this JsonFileReader object
   */
  public ErrorLogger getELog() {
    return this.eLog;
  }

  /**
   * Extracts and returns the name of the level specified in the json file as a String. Records an
   * error message to the ErrorLogger if an error occurs
   *
   * @param content The content of the file in JSONObject format
   * @return The name of the level or null if an error occurs
   */
  public String findLevelName(JSONObject content) {
    try {
      String levelName = (String) content.get("levelName");
      // System.out.println(levelName == null);
      if (levelName == null) {
        eLog.setMessage(
            "Please define the name of the level using the (format levelName: name) with name being a String ");
        eLog.log();
      }
      return levelName;
    } catch (Exception ex) {
      eLog.setMessage("Please use a String as your file name");
      eLog.log();
      return null;
    }
  }

  /**
   * Extracts and returns the size of the character specified in the json file as a String. Records
   * an error message to the ErrorLogger if an error occurs or if the size specified in the file is
   * not one of the followings: tiny, normal, large, giant
   *
   * @param content The content of the file in JSONObject format
   * @return The size of the character or null if an error occurs
   */
  public String findCharacterSize(JSONObject content) {
    try {
      String characterSize = (String) content.get("stickmanSize");
      if (characterSize == null) {
        eLog.setMessage(
            "Please define the size of the character using the format (stickmanSize: size)");
        eLog.log();
      }
      if (characterSize.equalsIgnoreCase("tiny")
          || characterSize.equalsIgnoreCase("normal")
          || characterSize.equalsIgnoreCase("large")
          || characterSize.equalsIgnoreCase("giant")) {
        return characterSize;
      } else {
        eLog.setMessage(
            "Please define the size of the character from one of the options: tiny, normal, large, and giant.");
        eLog.log();
      }
    } catch (Exception ex) {
      eLog.setMessage(
          "Please define the size of the character from one of the options: tiny, normal, large, and giant.");
      eLog.log();
    }
    return null;
  }

  /**
   * Extracts and returns the x position of the character specified in the json file as a double.
   * Records an error message to the ErrorLogger if an error occurs or if the position is negative
   *
   * @param content The content of the file in JSONObject format
   * @return The x position of the character or -1 if an error occurs
   */
  public double findCharacterXPos(JSONObject content) {
    try {
      HashMap<String, Double> stickmanX = (HashMap<String, Double>) content.get("stickmanPos");
      if (stickmanX.get("x") == null) {
        eLog.setMessage(
            "Please define the starting x position of the caracter in the form (x: value) using a non-negative double");
        eLog.log();
      }
      if (stickmanX.get("x") < 0) {
        eLog.setMessage(
            "Please define the starting x position of the caracter in the form (x: double) using a non-negative double");
        eLog.log();
      }

      return stickmanX.get("x");
    } catch (Exception ex) {
      eLog.setMessage(
          "Please define the starting x position of the caracter in the form (x: double) using a non-negative double");
      eLog.log();
    }
    return -1;
  }

  /**
   * Extracts and returns the velocity of the cloud specified in the json file as a double. Records
   * an error message to the ErrorLogger if an error occurs or if the velocity is less than 0
   *
   * @param content The content of the file in JSONObject format
   * @return The velocity of the cloud or -1 if an error occurs
   */
  public double findCloudVelocity(JSONObject content) {
    try {
      double cloudVelocity = (double) content.get("cloudVelocity");
      if (content.get("cloudVelocity") == null) {
        eLog.setMessage("please provide cloud velocity in the form: (cloudVelocity: velocity)");
        eLog.log();
      }
      if (cloudVelocity < 0) {
        eLog.setMessage("Please provide cloud velocity as a valid double starting from 0");
        eLog.log();
      }
      return cloudVelocity;
    } catch (Exception ex) {
      eLog.setMessage("Please provide cloud velocity as a valid double starting from 0");
      eLog.log();
    }
    return -1;
  }

  /**
   * Extracts and returns the information of all the enemies specified in the json file as a map.
   * Records an error message to the ErrorLogger if the provided information is not in the correct
   * format
   *
   * @param content The content of the file in JSONObject format
   * @return The map of enemies' information or null if an error occurs
   */
  public HashMap<String, ArrayList> findEnemies(JSONObject content) {
    try {
      HashMap<String, ArrayList> enemies = (HashMap<String, ArrayList>) content.get("enemies");
      if (enemies == null) {
        eLog.setMessage(
            "Please provide a map of all the enemies in the json file using the format enemies: { }. Please refer to the README file for further explanation on the format of the json files");
        eLog.log();
      }
      return enemies;
    } catch (Exception ex) {
      eLog.setMessage(
          "Please provide a map of all the enemies in the json file using the format enemies: { }. Please refer to the README file for further explanation on the format of the json files");
      eLog.log();
    }
    return null;
  }

  /**
   * Extracts and returns the number of lives the character has specified in the json file as a
   * double. Records an error message to the ErrorLogger if an error occurs or if the number is not
   * positive
   *
   * @param content The content of the file in JSONObject format
   * @return The number of lives a character has or -1 if an error occurs
   */
  public int findCharacterLifeCount(JSONObject content) {
    try {
      Long lifeCountLong = (Long) content.get("levelLifeCount");
      int lifeCount = lifeCountLong.intValue();
      if (lifeCount <= 0) {
        eLog.setMessage(
            "Please provide a positive int for the characterLifeCount element. This is the number of lives the character will have in a particular level");
        eLog.log();
      } else {
        return lifeCount;
      }
    } catch (Exception ex) {
      System.out.println(ex);
      eLog.setMessage(
          "Please provide a positive int for the characterLifeCount element. This is the number of lives the character will have in a particular level");
      eLog.log();
    }
    return -1;
  }

  /**
   * Extracts and returns the information of all the platforms specified in the json file as a map.
   * Records an error message to the ErrorLogger if an error occurs
   *
   * @param content The content of the file in JSONObject format
   * @return The map of platforms' information or null if an error occurs
   */
  public HashMap<String, ArrayList> findPlatforms(JSONObject content) {
    try {
      HashMap<String, ArrayList> platforms = (HashMap<String, ArrayList>) content.get("platforms");
      if (platforms == null) {
        eLog.setMessage(
            "Please provide a map of all the platforms in the json file using the format platforms: { }. Please refer to the README file for further explanation on the format of the json files");
        eLog.log();
      }
      //System.out.println(platforms.get("NormalPlatform"));
      return platforms;
    } catch (Exception ex) {
      eLog.setMessage(
          "Please provide a map of all the platforms in the json file using the format platforms: { }. Please refer to the README file for further explanation on the format of the json files");
      eLog.log();
    }
    return null;
  }

  /**
   * Extracts and returns the length of the level (x position of the finish line) specified in the
   * json file as a double. Records an error message to the ErrorLogger if an error occurs or if the
   * specified length is not positive
   *
   * @param content The content of the file in JSONObject format
   * @return The length of the level or -1 if an error occurs
   */
  public double findLevelLength(JSONObject content) {
    try {
      double length = (double) content.get("levelLength");
      if (length <= 0) {
        eLog.setMessage("Please provide a positive double as the level length.");
        eLog.log();
      }
      return length;
    } catch (Exception ex) {
      eLog.setMessage("Please provide a positive double as the level length.");
      eLog.log();
    }
    return -1;
  }
}
