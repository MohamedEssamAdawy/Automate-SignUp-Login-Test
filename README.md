# PixelLogicTask

This repository includes a full automate script to sign up on <http://phptravels.net/register>.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To run this project your machine must be had the following:

1. Java 13. [Download](https://www.oracle.com/java/technologies/javase-jdk13-downloads.html)
2. Allure Report. [Download](https://repo1.maven.org/maven2/io/qameta/allure/allure-commandline/2.13.3/allure-commandline-2.13.3.zip)
3. Any Java IDE "I prefer IntelliJ Community version." [Download](https://www.jetbrains.com/idea/download/)

***Don't forget to add them to the environment path.***

### Installing

- Download the project as zip file and import it in your IDE.
- Build the Project and make sure Maven load all dependencies. More information in the [project dependencies section](#project-dependencies).
- Open [**BaseTest**] class
- Run **BaseTest.main()** function and make sure all things work correctly.
- Now you are ready to start running the automated test for sign up.

## Testing

In this section you will be able to run tests and open the reports as well.

### Running test scenario

- Open [**SignUpTest**] class
- It contains two testcase

> To run each testcase separately, right-click on method name and run it.

- test with valid data "*positive testing*"

    ```java
    @Test(dataProvider = "SignUpValidData",description = "Test full sign up scenario with 2 valid cases")
    public void testSignUpWithValidData(JsonReader.User user){
        signUp(user);
    }
    ```

  - test with invalid data "*negative testing*"

    ```java
    @Test(dataProvider = "SignUpInvalidData",description = "Test full sign up scenario with all invalid cases")
    public void testSignUpWithInvalidData(JsonReader.User user){
       signUp(user);
    }
    ```

   > You can run both of them by right-click in the class and choose *Run 'SignUpTest'*

### Test Reporting

- In this project Allure Reporting with TestNG used, if you missed this, see it in [Prerequisites](#prerequisites)

- After each suite *Allure report* generated automatically using this function

    ```java
    @AfterSuite(description = "Open Allure reports")
    public void openReports() throws IOException, InterruptedException {
      // Open Allure Report
      String allureFolderPath = System.getProperty("user.dir");
      System.out.println(allureFolderPath);
      String generate = "cmd /c start \"\" allure serve " + allureFolderPath + "\\allure-results";
      Process runtimeProcess = Runtime.getRuntime().exec(generate);
      int processComplete = runtimeProcess.waitFor();
    }
    ```
  
- Report contains screenshots if testcase *failed* & a message with time.
- Report contains a success message with time if testcase *Success*"

- In case you want to check the report later, you can do this by **opening cmd/terminal in your project folder** and write the following

```shell script
allure serve allure-results
```

> If you interested to know more information, go to [Project Features](#allurelistenerreporter)

## Project Dependencies

Project include these dependencies using [**pom.xml**]

1. Selenium with Chrome driver
2. Selenium Support
3. TestNG
4. Allure Reporting with TestNG integration
5. Simple JSON
6. JSON

## Project Features

In this section, you will know more information about the features used in the project

### Data Input Method

>In this project, nothing is hard coded

- Testcase get the data through a data provider which get the data from a json file.
- There is 2 data providers *signUpValidData()* and *signUpInvalidData()*, you can find them in [**SignUpTest**] class
- These methods create an object from [**JsonReader**] class and return the input data parsed into arrays of User objects.

> Kindly follow the same json format if you want to edit/add more input data

- There are 2 JSON files as data providers methods
  - [validInputData.json]
  - [invalidInputData.json]

### HTTP Response for signUp API

- In case you want to check the response of the signUp Api request you will find it in the [**signUpResponse folder**] text file named with the timestamp for each testcase.
- To get this response, Selenium Support package used, so EventFiringWebDriver used instead of regular WebDriver to register an [**EventReporter**] class to the driver.
- [**EventReporter**] class implements *WebDriverEventListener* interface, so **afterClickOn** method overridden to get the response of the signUp API request after click on sign-up button.

### AllureListenerReporter

In this section you will get more information about how allure report works.

- Listeners method used to capture the testcase status and choose the way of reporting depends on its state.
- [**AllureListenerReporter**] implements *ITestListener* interface, so the only needed methods are **onTestSuccess** & **onTestFailure** implemented.
- One more thing you **MUST NOT FORGET**, add *@Listeners* in your testcase class in this way:

    ```java
    @Listeners({TestNGListenerReporter.class,AllureListenerReporter.class})

    public class SignUpTest extends BaseTest {
      // Code
    }
    ```

### TestNGListenerReporter

In case you did not want to use Allure Report, TestNG default report also available but not enabled by default.

- By the same way *AllureListenerReporter* work, also [**TestNGListenerReporter**] works, but it extends TestListenerAdapter.

> The difference between implements and extends keywords, implements used when inherit from interface while extends used when inherit from class

- **onTestFailure** take a screenshot and save it in [**screenshots folder**]
- you can find the report in test-output folder -*Automatically created*- in 2 ways *Detailed and Short* in these file *index.html* and *emailable-report.html* respectively

> For IntelliJ users: to generate TestNG reports, you must go to Run/Debug configurations, under Listener tab, check *use default reporters*

- if you want to open the TestNG report automatically afterSuite, modify **openReports()** function in [**BaseTest**] to be:

    ```java
    @AfterSuite(description = "Open TestNG report")
    public void openReports() throws IOException, InterruptedException {
      // Open TestNG Report
      String testNGReport = System.getProperty("user.dir") + "\\test-output\\index.html";
      Desktop.getDesktop().open(new File(testNGReport));
    }
    ```

## Author

- **Mohammed Essam** - [MElborolossy](https://github.com/MElborolossy)

[**BaseTest**]:../master/src/test/java/base/BaseTest.java
[**SignUpTest**]:../master/src/test/java/registerPageTests/SignUpTest.java
[**pom.xml**]:../master/pom.xml
[**JsonReader**]:../master/src/main/java/utils/JsonReader.java
[validInputData.json]:../master/resources/validInputData.json
[invalidInputData.json]:../master/resources/invalidInputData.json
[**signUpResponse folder**]:../../tree/master/resources/signUpResponse
[**EventReporter**]:../master/src/main/java/utils/EventReporter.java
[**AllureListenerReporter**]:../master/src/test/java/base/AllureListenerReporter.java
[**TestNGListenerReporter**]:../master/src/test/java/base/TestNGListenerReporter.java
[**screenshots folder**]:../../tree/master/resources/screenshots
