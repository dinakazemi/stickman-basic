package stickman.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/** Creates platforms based on the information acquired from json file */
/*
using the factory method to make the platform.
At this stage, the factory method seems irrelevant as we have only one type of platform
But as new platform types will be added in the next stage, factory method is necessary.
 */
public class PlatformFactory {

  private static ErrorLogger eLog = new ErrorLogger();

  /**
   * Creates different types of platforms according to the information received from the json file
   * and returns an array of platforms of a certain type. If the information received is not in the
   * correct format or type, an error will be recorded to the ErrorLogger - at this stage, only one
   * type of platform has been implemented
   *
   * @param platformInfo The information about the enemy received from the json file
   * @param levelHeight The height of the level's floor
   * @return an array of enemies of a certain type used by the LevelImpl class
   */
  public static ArrayList<Platform> make(
      HashMap<String, ArrayList> platformInfo, double levelHeight) {
    if (platformInfo == null) {
      return null;
    }
    ArrayList<Platform> result = new ArrayList<Platform>();
    // Getting the iterator
    Iterator iterator = platformInfo.entrySet().iterator();

    while (iterator.hasNext()) {

      // if the format of the file is incorrect (ie it's not <String, ArrayList>, we will write an
      // error to the ErrorLogger object.
      try {
        Map.Entry<String, ArrayList> element = (Map.Entry<String, ArrayList>) iterator.next();
        String key = element.getKey();
        if (key.equalsIgnoreCase("NormalPlatform")) {

          // checks if the user has entered a non-double as for platform's properties.
          try {
            ArrayList<ArrayList<Double>> value = platformInfo.get(key);
            for (ArrayList<Double> properties : value) {
              if (properties.size() < 4) {
                eLog.setMessage(
                    "Insufficient elements for normalPlatform. Please provide four elements per normalPlatform");
                eLog.log();
              } else if (properties.get(0) < 0
                  || properties.get(1) < 0
                  || properties.get(2) <= 0
                  || properties.get(3) <= 0) {
                eLog.setMessage(
                    "x and y position of the normalPlatform must be non-negative while its height and width must be strictly positive.");
                eLog.log();
              } else {
                NormalPlatform normalPlatform =
                    new NormalPlatform(
                        properties.get(0),
                        properties.get(1),
                        properties.get(2),
                        properties.get(3),
                        levelHeight);
                result.add(normalPlatform);
              }
            }
          } catch (Exception ex) {
            eLog.setMessage(
                "please make sure all enemy properties are doubles for a normalPlatform");
            eLog.log();
          }
        }
      } catch (Exception ex) {
        eLog.setMessage(
            "invalid format for platforms Please provide a 2-d array for platform properties.");
        eLog.log();
      }
    }
    return result;
  }
}
