package stickman.model;
/** Creates enemies based on the information acquired from json file */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EnemyFactory {

  private static ErrorLogger eLog = new ErrorLogger();

  /**
   * Creates different types of enemies according to the information received from the json file and
   * returns an array of enemies of a certain type. If the information received is not in the
   * correct format or type, an error will be recorded to the ErrorLogger - an enemy is one of the
   * following 4 types: SteadyEnemy, MovingEnemy, AngryENnemy, DisappearingEnemy
   *
   * @param enemiesInfo The information about the enemy received from the json file
   * @param levelHeight The height of the level's floor
   * @return an array of enemies of a certain type used by the LevelImpl class
   */
  public static ArrayList<Enemy> make(HashMap<String, ArrayList> enemiesInfo, double levelHeight) {

    // if the given map is null (eg no enemies in the level)
    if (enemiesInfo == null) {
      return null;
    }
    ArrayList<Enemy> result = new ArrayList<Enemy>();
    // Getting the iterator
    Iterator iterator = enemiesInfo.entrySet().iterator();

    // going through the elements in the map
    while (iterator.hasNext()) {

      // if the format of the file is incorrect (ie it's not <String, ArrayList>, we will write an
      // error to the ErrorLogger object.
      try {
        Map.Entry<String, ArrayList> element = (Map.Entry<String, ArrayList>) iterator.next();
        String key = element.getKey();
        if (key.equalsIgnoreCase("steadyEnemy")) {

          // checks if the user has entered a non-double as for enemy's properties.
          try {
            ArrayList<ArrayList<Double>> value = enemiesInfo.get(key);
            for (ArrayList<Double> properties : value) {
              if (properties.size() < 4) {
                eLog.setMessage(
                    "Insufficient elements for steadyEnemy. Please provide four elements per steadyEnemy");
                eLog.log();
              } else if (properties.get(0) < 0
                  || properties.get(1) < 0
                  || properties.get(2) <= 0
                  || properties.get(3) <= 0) {
                eLog.setMessage(
                    "x and y position of the steadyEnemy must be non-negative while its height and width must be positive. ");
                eLog.log();
              } else {
                SteadyEnemy steadyEnemy =
                    new SteadyEnemy(
                        properties.get(0),
                        properties.get(1),
                        properties.get(2),
                        properties.get(3),
                        levelHeight);
                result.add(steadyEnemy);
              }
            }
          } catch (Exception ex) {
            eLog.setMessage("please make sure all enemy properties are doubles for a steadyEnemy");
            eLog.log();
          }

        } else if (key.equalsIgnoreCase("movingEnemy")) {

          // checks if the user has entered a non-double as for enemy's properties.
          try {
            ArrayList<ArrayList<Double>> value = enemiesInfo.get(key);
            for (ArrayList<Double> properties : value) {
              if (properties.size() < 6) {
                eLog.setMessage(
                    "Insufficient elements for movingEnemy. Please provide six elements per movingEnemy");
                eLog.log();
              } else if (properties.get(0) < 0
                  || properties.get(1) < 0
                  || properties.get(2) <= 0
                  || properties.get(3) <= 0) {
                eLog.setMessage(
                    "x and y position of the movingEnemy must be non-negative while its height and width must be positive. ");
                eLog.log();
              } else if (properties.get(4) != 1 && properties.get(4) != 0) {
                eLog.setMessage(
                    "Please provide a 0 or 1 to indicate whether the movingEnemy is moving in the x direction");
                eLog.log();
              } else if (properties.get(5) != 1 && properties.get(5) != 0) {
                eLog.setMessage(
                    "Please provide a 0 or 1 to indicate whether the movingEnemy is moving in the y direction");
                eLog.log();
              } else {
                MovingEnemy movingEnemy =
                    new MovingEnemy(
                        properties.get(0),
                        properties.get(1),
                        properties.get(2),
                        properties.get(3),
                        properties.get(4),
                        properties.get(5),
                        levelHeight);
                result.add(movingEnemy);
              }
            }
          } catch (Exception ex) {
            eLog.setMessage("please make sure all enemy properties are doubles for a movingEnemy");
            eLog.log();
          }

        } else if (key.equalsIgnoreCase("angryEnemy")) {
          try {
            ArrayList<ArrayList<Double>> value = enemiesInfo.get(key);
            for (ArrayList<Double> properties : value) {
              if (properties.size() < 6) {
                eLog.setMessage(
                    "Insufficient elements for angryEnemy. Please provide six elements per angryEnemy");
                eLog.log();
              } else if (properties.get(0) < 0
                  || properties.get(1) < 0
                  || properties.get(2) <= 0
                  || properties.get(3) <= 0) {
                eLog.setMessage(
                    "x and y position of the angryEnemy must be non-negative while its height and width must be positive. ");
                eLog.log();
              } else if (properties.get(4) != 1 && properties.get(4) != 0) {
                eLog.setMessage(
                    "Please provide a 0 or 1 to indicate whether the angryEnemy is moving in the x direction");
                eLog.log();
              } else if (properties.get(5) != 1 && properties.get(5) != 0) {
                eLog.setMessage(
                    "Please provide a 0 or 1 to indicate whether the angryEnemy is moving in the y direction");
                eLog.log();
              } else {
                AngryEnemy angryEnemy =
                    new AngryEnemy(
                        properties.get(0),
                        properties.get(1),
                        properties.get(2),
                        properties.get(3),
                        properties.get(4),
                        properties.get(5),
                        levelHeight);
                result.add(angryEnemy);
              }
            }
          } catch (Exception ex) {
            eLog.setMessage("please make sure all enemy properties are doubles for a angryEnemy");
            eLog.log();
          }

        } else if (key.equalsIgnoreCase("DisappearingEnemy")) {
          try {
            ArrayList<ArrayList<Double>> value = enemiesInfo.get(key);
            for (ArrayList<Double> properties : value) {
              if (properties.size() < 6) {
                eLog.setMessage(
                    "Insufficient elements for DisappearingEnemy. Please provide six elements per DisappearingEnemy");
                eLog.log();
              } else if (properties.get(0) < 0
                  || properties.get(1) < 0
                  || properties.get(2) <= 0
                  || properties.get(3) <= 0) {
                eLog.setMessage(
                    "x and y position of the DisappearingEnemy must be non-negative while its height and width must be positive. ");
                eLog.log();
              } else if (properties.get(4) != 1 && properties.get(4) != 0) {
                eLog.setMessage(
                    "Please provide a 0 or 1 to indicate whether the DisappearingEnemy is moving in the x direction");
                eLog.log();
              } else if (properties.get(5) != 1 && properties.get(5) != 0) {
                eLog.setMessage(
                    "Please provide a 0 or 1 to indicate whether the DisappearingEnemy is moving in the y direction");
                eLog.log();
              } else {
                DisappearingEnemy disappearingEnemy =
                    new DisappearingEnemy(
                        properties.get(0),
                        properties.get(1),
                        properties.get(2),
                        properties.get(3),
                        properties.get(4),
                        properties.get(5),
                        levelHeight);
                result.add(disappearingEnemy);
              }
            }
          } catch (Exception ex) {
            eLog.setMessage(
                "please make sure all enemy properties are doubles for a DisappearingEnemy");
            eLog.log();
          }
        }

      } catch (Exception ex) {
        eLog.setMessage(
            "Invalid format for enemies. Please provide a 2-d array list for enemy properties.");
        eLog.log();
      }

      // separating each key and making them by calling the relevant enemy class
    }
    return result;
  }
}
