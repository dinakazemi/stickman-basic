What is the purpose of the application?
This is a platformer video game, with a third person hero interacting with the world. At this stage, it only contains the hero, moving clouds, sky, and the ground.

How to run the application?
Please run the App.java file for the application to start. A json file provides information (characterImpl size, characterImpl starting position, and cloud velocity, enemy information, characterImpl's life count, level's length, and platforms' information) for initialising the characterImpl, clouds, enemies, characterImpl lives, platforms, and the level. If you would like to change the starting json file, please first provide your desired json file in the following path: src/main/resources. Write this path followed by the name of your new json file (src/main/resources/fileName in line 32 of LevelProcessor.java, instead of the old path (Please be aware that although you need to change the file path manually for this stage, the code as been implemented in a way that at the next stage, level files will be read automatically by the program). A default json file has already been provided.

What is the format of the json file?
An example of a valid json file has been provided in the resources folder, called level-1.json. If for any reason, LevelProcessor.java is provided with an invalid json file, the application will produce an error, print the relevant message, and exit the program. So please follow the structure of the provided json file. As the developer, I have designed the file in a way to give game designers total control over designing each level.

What is the chosen style guide for this project?
Google Java Style Guide (https://google.github.io/styleguide/javaguide.html)
All variable and class naming, coding layout, and java doc comments are aligned with this style. (Hence, java docs comments are provided at the start of each class, its implementations, and methods with relevant annotations eg @return, @parameter) 

Additional Resources:
https://itnext.io/solid-principles-explanation-and-examples-715b975dcad4
Used for creating ErrorLogger.java class

https://howtodoinjava.com/library/json-simple-read-write-json-examples/
Used for creating JsonFileReader.java

https://housemethod.com/hardscaping/brick-patterns/
Used for the brick patterned picture

http://www.transparentpng.com/thumb/instagram-heart/8TPkVW-red-instagram-heart-free-download.png
Used as the heart symbol

