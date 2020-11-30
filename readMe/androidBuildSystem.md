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
- For more details follow the android website **https://developer.android.com/studio/build**
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
- The file looks something like this
```
/**
 * The buildscript block is where you configure the repositories and
 * dependencies for Gradle itself—meaning, you should not include dependencies
 * for your modules here. For example, this block includes the Android plugin for
 * Gradle as a dependency because it provides the additional instructions Gradle
 * needs to build Android app modules.
 */

buildscript {

    /**
     * The repositories block configures the repositories Gradle uses to
     * search or download the dependencies. Gradle pre-configures support for remote
     * repositories such as JCenter, Maven Central, and Ivy. You can also use local
     * repositories or define your own remote repositories. The code below defines
     * JCenter as the repository Gradle should use to look for its dependencies.
     *
     * New projects created using Android Studio 3.0 and higher also include
     * Google's Maven repository.
     */

    repositories {
        google()
        jcenter()
    }

    /**
     * The dependencies block configures the dependencies Gradle needs to use
     * to build your project. The following line adds Android plugin for Gradle
     * version 4.0.0 as a classpath dependency.
     */

    dependencies {
        classpath 'com.android.tools.build:gradle:4.0.0'
    }
}

/**
 * The allprojects block is where you configure the repositories and
 * dependencies used by all modules in your project, such as third-party plugins
 * or libraries. However, you should configure module-specific dependencies in
 * each module-level build.gradle file. For new projects, Android Studio
 * includes JCenter and Google's Maven repository by default, but it does not
 * configure any dependencies (unless you select a template that requires some).
 */

allprojects {
   repositories {
       google()
       jcenter()
   }
}
```
- You can add ext block in this file.
- For Android projects that include multiple modules, it may be useful to define certain properties at the project level and share them across all the modules. You can do this by adding extra properties to the ext block in the top-level build.gradle file
```
ext {
    // The following are only a few examples of the types of properties you can define.
    compileSdkVersion = 28
    // You can also create properties to specify versions for dependencies.
    // Having consistent versions between modules can avoid conflicts with behavior.
    supportLibVersion = "28.0.0"
    ...
}
```
## The module-level build file
- It allows you to configure build settings for the specific module it is located in
- Configuring these build settings allows you to provide custom packaging options, such as additional build types and product flavors, and override settings in the main/ app manifest or top-level build.gradle file.
- The sample build.gradle file looks like this
```
/**
 * The first line in the build configuration applies the Android plugin for
 * Gradle to this build and makes the android block available to specify
 * Android-specific build options.
 */

apply plugin: 'com.android.application'

/**
 * The android block is where you configure all your Android-specific
 * build options.
 */

android {

  /**
   * compileSdkVersion specifies the Android API level Gradle should use to
   * compile your app. This means your app can use the API features included in
   * this API level and lower.
   */

  compileSdkVersion 28

  /**
   * buildToolsVersion specifies the version of the SDK build tools, command-line
   * utilities, and compiler that Gradle should use to build your app. You need to
   * download the build tools using the SDK Manager.
   *
   * This property is optional because the plugin uses a recommended version of
   * the build tools by default.
   */

  buildToolsVersion "29.0.2"

  /**
   * The defaultConfig block encapsulates default settings and entries for all
   * build variants, and can override some attributes in main/AndroidManifest.xml
   * dynamically from the build system. You can configure product flavors to override
   * these values for different versions of your app.
   */

  defaultConfig {

    /**
     * applicationId uniquely identifies the package for publishing.
     * However, your source code should still reference the package name
     * defined by the package attribute in the main/AndroidManifest.xml file.
     */

    applicationId 'com.example.myapp'

    // Defines the minimum API level required to run the app.
    minSdkVersion 15

    // Specifies the API level used to test the app.
    targetSdkVersion 28

    // Defines the version number of your app.
    versionCode 1

    // Defines a user-friendly version name for your app.
    versionName "1.0"
  }

  /**
   * The buildTypes block is where you can configure multiple build types.
   * By default, the build system defines two build types: debug and release. The
   * debug build type is not explicitly shown in the default build configuration,
   * but it includes debugging tools and is signed with the debug key. The release
   * build type applies Proguard settings and is not signed by default.
   */

  buildTypes {

    /**
     * By default, Android Studio configures the release build type to enable code
     * shrinking, using minifyEnabled, and specifies the default Proguard rules file.
     */

    release {
        minifyEnabled true // Enables code shrinking for the release build type.
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
    }
  }

  /**
   * The productFlavors block is where you can configure multiple product flavors.
   * This allows you to create different versions of your app that can
   * override the defaultConfig block with their own settings. Product flavors
   * are optional, and the build system does not create them by default.
   *
   * This example creates a free and paid product flavor. Each product flavor
   * then specifies its own application ID, so that they can exist on the Google
   * Play Store, or an Android device, simultaneously.
   *
   * If you declare product flavors, you must also declare flavor dimensions
   * and assign each flavor to a flavor dimension.
   */

  flavorDimensions "tier"
  productFlavors {
    free {
      dimension "tier"
      applicationId 'com.example.myapp.free'
    }

    paid {
      dimension "tier"
      applicationId 'com.example.myapp.paid'
    }
  }

  /**
   * The splits block is where you can configure different APK builds that
   * each contain only code and resources for a supported screen density or
   * ABI. You'll also need to configure your build so that each APK has a
   * different versionCode.
   */

  splits {
    // Settings to build multiple APKs based on screen density.
    density {

      // Enable or disable building multiple APKs.
      enable false

      // Exclude these densities when building multiple APKs.
      exclude "ldpi", "tvdpi", "xxxhdpi", "400dpi", "560dpi"
    }
  }
}

/**
 * The dependencies block in the module-level build configuration file
 * specifies dependencies required to build only the module itself.
 * To learn more, go to Add build dependencies.
 */

dependencies {
    implementation project(":lib")
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation fileTree(dir: 'libs', include: ['*.jar'])
}
```
## gradle.properties
- This is where you can configure project-wide Gradle settings, such as the Gradle daemon's maximum heap size. For more information, see The Build Environment.


