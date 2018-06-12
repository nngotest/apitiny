# Apitiny Automation test

## Description
Automation test for Apitiny using [Appium](http://appium.io/) and [ExtentReport](http://extentreports.com/)

## Setup
1. Download and install [Appium](https://github.com/appium/appium-desktop/releases)
2. Download and setup [JDK and Maven](https://www.tutorialspoint.com/maven/maven_environment_setup.htm) environments.
3. Clone this repo to your local using `git clone`
4. Open terminal, at project folder run command: `mvn clean install`

## Run test
1. Launch appium and start appium server
2. Open terminal, at project folder run command: `mvn test -DsuiteXmlFile={xmlFile}` . For example: `mvn test -DsuiteXmlFile=smoke.xml`

Once the test run finished, a html test report will be generated and stored in report folder.