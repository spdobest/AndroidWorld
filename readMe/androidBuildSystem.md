# Android Build System
- Gradle Build System
- What is Gradle
- How gradle works

# What is build tools ?
- Lets consider a Carpenter who uses different tools to make his work **faster, easier, effecient, organised and maintainable**. These set of tools is known as **Build Tools** for carpenter.
- In the same way, in software
- **Build Tools** are software applications, that helps in build automation.
## What is Build Automation
- The process of automating a wide veriety of tasks that are required by software developers to build the application like 
  - Compiling Source Code
  - Packaging into binary code
  - managing required libraries (Dependencies)
  - Running automated tests
  - Deployment
  - Sending emails and notification
  
## Common Build Tools used in software
- Apache Ant
- Apache Maven
- Gradle
- Grunt
- Gulp
- 
# WHat is gradle ?
- It is an open source build automation tool
- Gradle builds the scripts are written in groovy or kotlin DSL.
- It is a successor of **Ant 2000, Maven 2004, Gradle 2007**
- It is build over ant and maven to make the build more faster, powerful, customizable than other tools.
- For more details follow the 2 links below
- **https://gradle.org/**
- **https://docs.gradle.org/current/userguide/userguide.html**

## Gradle Installation
- Install Java
- Check the gradle version if installed in command promt, terminal - **gradle -v or gradle --version**
- Follow the steps in this site to install gradle **https://gradle.org/install/**
- Set environment variables

# Android Studio gradle system in Deep 
- https://www.youtube.com/watch?v=Xw6CKEsWvxo

<table>
<tr>
<td>
  <img src="https://github.com/spdobest/AndroidWorld/blob/master/readMe/Images/androidBuildSystem3.png" width="500" height="400" /> 
 </td>
<td>
<img src="https://github.com/spdobest/AndroidWorld/blob/master/readMe/Images/androidBuildSystem4.png" width="600" height="400" />
</td>
</tr>
  
  <tr>
<td>
  <img src="https://github.com/spdobest/AndroidWorld/blob/master/readMe/Images/androidBuildSystem2.png" width="800" height="500" /> 
 </td>
</tr>
</table> 

- You can have different built types like thie
```
android {
 compileSdkVersion 20
 buildToolsVersion "20.0.2"
 flavourDimensions "version" , "abi"
 productFlavours {
      free{
          flavourDimension "version"
      }
      paid{
          flavourDimension "version"
      }
      arm{
          flavourDimension "abi"
      }
      x86{
          flavourDimension "abi"
      }
 }

```
- To achieve same thing in ant build system, it requires 300 lines of xml code
- To do the same task in Maven its require to create 4 sub module for this, which is very complex
## Different gradle files in android project
- **settings.gradle** - The settings.gradle file, located in the root project directory, tells Gradle which modules it should include when building your app. For most projects, the file is simple and only includes the following:
- However, multi-module projects need to specify each module that should go into the final build.
- **Top level build.gradle**  
- The top-level build.gradle file, located in the root project directory, defines build configurations that apply to all modules in your project. 
- By default, the top-level build file uses the buildscript block to define the Gradle repositories and dependencies that are common to all modules in the project. The following code sample describes the default settings and DSL elements you can find in the top-level build.gradle after creating a new project.
- 



