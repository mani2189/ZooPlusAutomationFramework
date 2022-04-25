Project Structure and its Explanation:

Framework Used : Cucumber BDD and PageObjectModel ( I have implemented Simple Cucumber BDD framework and PageObject Model which requires for the Current assigned Task)
PageFactory - To Initialize all the webelements
Design Pattern : Singleton Class for Driver object Initialization
Runwith : JUnit Test



Project and Code Structure:

1. BaseClass - Common Action Methods
2. Page Cass - Contains Action Methods on Webelements and all Code logics implemented using Corresponding pageWebelements
3. StepDefinition Class - All Test scernarios ( Note: This class Contains Only method calling from Page Class. Page Class holds All the code logic according the Test cases)
4. RunnerClass - Contains the Cucumber Options such as features,glue,dryRun,monochrome and plugin
5. FeatureFile - This holds the Test scenarios and TestSteps with the BDD style

    features - This holds the local path of Feature file present
    glue - This connects the Feature file and StepDefinition 
    dryrun - It Checks and Ensure whether all the Step Definitions are defined from Feature file (Always sets to false While Executing the script)
    monoChrome - To provide good look and feel on the outputconsole  ( Always Sets to True)
    plugin - All Reports generated  


How to Run the Program:
1. Go to gitHub repo and Download the Project as Zip into local system 
2. Go to Eclipse Click->File->Import as MavenProject  (Eclipse Latest version recommended)
3. Right Click Project GoTo->Maven->UpdateProject to make sure all the Depedencies are downloaded from pom.xml file
4. Go to Runner.java File (/ZooplusSeleniumTask/src/test/java/Runner)
5. RightClick-> run as JUnit-Test 
6. Once the program run completed successfully, Go to Console and See the Validation results printed in the console 

Please note : I have used Assersion from TestNG . Kindly make sure TestNG Jar is there and import org.testng.Assert;

Software and Plugins Versions used:

1. Eclipse Version: 2020-06 (4.16.0)
2. Selenium version :3.141.0
2. Junit4
3. CucumberVersion 6.6.0 (io.cucumber)
4. webdrivermanager 5.0.3
5. Java 1.8
6. CucumberEclipsePlugin : 1.0.0.2