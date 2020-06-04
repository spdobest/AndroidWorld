# This will explain the below concepts  

https://github.com/android10/Android-CleanArchitecture


**1. SOLID Principle**   
**2. Clean Architecture**  
**3. Functional Design Pattern**  
**4. Architeture Design Pattern**  
  
## SOLID Principle  
For More details please visit the site  
https://android.jlelse.eu/basic-concepts-of-software-architecture-patterns-in-android-c76e53f46cba  
https://android.jlelse.eu/solid-principles-the-definitive-guide-75e30a284dea  

  
***SOLID*** Represents five important principles which help to write better code, code modularity, better maintainablilty, easy to understand.Understanding and applying these principles will allow you to write better quality code and therefore be a better developer.  
  
**These principles are a set of practical recommendations that when applied to our code helps us to obtain the following benefits:**  
  
***1.Ease to maintain.***   
***2.Ease to extend.***  
***3.Robust code.***  
  
But before we see what each SOLID principle means, we need to remember two relevant concepts in the development of any software.
  
**COUPLING**  
We can define it as the degree to which a class, method or any other software entity, is directly linked to another. This degree of coupling can also be seen as a degree of dependence.  
**example:** when we want to use a class that is tightly bound (has a high coupling) to one or more classes, we will end up using or modifying parts of these classes for which we are dependent.  
**Cohesion:**  
***Cohesion*** is the measure in which two or more parts of a system work together to obtain better results than each part individually.
example: Han Solo and Chewbacca aboard the Millennium Falcon.  
  
#### NOTE To obtain a good software we must always try to have a low coupling and a high cohesion, and SOLID principles help us with this task. If we follow these guidelines our code will be more robust, maintainable, reusable and extensible and we will avoid the tendency of our code to break in many places every time something is changed.  
  
Now lets understand the 5 principles one by one  
  
### S - Single Responsibility Principle (SRP):  
A class should have only one reason to change.  
  
Now i will explain how this principle violets in below example  
Lets Consider a Student class , which have 2 different task to do.  
  
 
            ```  
            public class Student { 
                private String name;
                // getter and setter methods...
                // This is a Responsibility
                public void storeStudent(String studentName) {
                    // store Student into a database...
                } 
                // This is another Responsibility
                public void generateProgressReport(String studentName) {
                    // generate a report...
                }
            }```  
            
In the above example Student handling the two task which is against the rule.  We can separate the logic to other class.We can write the methods named **storeStudent() and generateProgress()** in other differrent classes.**storeStudent()** method has responsibility to store the student name in database, which should be in another class like StudentDatabaseHelper Class.**generateProgress()** methods responsibility to generate the report, which should not be inside student class as per the principle.  
  
**Better Solution**  
  
            ```  
             public class Student {
                private String name;
                // getter and setter methods...
            }

            public class StudentDB {

                public void storeCustomer(String studentrName) {
                    // store customer into a database...
                }
            }

            public class StudentProgresstGenerator {

                public void generateReport(String studentName) {
                    // generate a report...
                }
            }```  
                      
### O - Open Closed Principle (OCP):  
Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification.  
**open for extension:** new behaviour can be added to satisfy the new requirements.  
**closed for modification:** to extending the new behaviour are not required modify the existing code.  
  
  
You should be able to extend a class’s behavior, without modifying it.
This principle is the foundation for building code that is maintainable and reusable.  
  
***Benefits:***  
Code maintainable and reusable.  
Code more robust.    
  
***Violation of this Rule***  
We have a rectangle class  

          ```
          public class Rectangle {
 
          private int width;
          private int height;
          // getter and setter methods...
        }```  
 
        public class Square {
         private int side;
        // getter and setter methods...
        }
        
        public class ShapeDrawer{
        public void drawShare(Object shape){
        
        if(shape instanceof Rectangle){
        //Draw rectAngle
        }
        else if(shape instanceof Square){
        //Draw Square
        }
        }
        }```  
        
To implement the **Open Closed Principle (OCP)** principle, you have to write like below.   
Just create an abstract class with abstract method draw(). which you need to extend to your specified class (Rectangle and square). Inside the overrided **draw()** method you can write your specific code related to square and rectAngle.  
  
Otherwise you can create interface containing draw method which implement to both of your classes  
    
### Liskove Substitution Principle  
  
Derived classes must be substitutable for their base classes.
What is wanted here is something like the following substitution property: If
for each object o1 of type S there is an object o2 of type T such that for all
programs P defined in terms of T, the behavior of P is unchanged when o1 is
substituted for o2 then S is a subtype of T.  
  
***Object in a program should be replaceable with instances of their subtype without altering the correctness of that program.***  
  
          ```  
         public class Rectangle {

                private int width;
                private int height;
                // getter and setter methods...
                
                public int getArea(){
                  return width*height;
                }
              }  

              public class Square {
               private int side;
              // getter and setter methods...
              public int getArea(){
                  return side*side;
                }
              }```  
               
                
To implement **Liskove Substitution Principle** you need to create an interface which sould contain **getArea()** method when you can write your own implementations for different shape like **rectAngle and Square**  
  
  
  
  
  
### I — Interface Segregation Principle  
  
**Many client-specific interfaces are better than one general purpose interface. You can create individual interfaces spceific to task and keep it simple**    
    
Make fine grained interfaces that are client specific.  
Clients should not be forced to implement interfaces they do not use.  
  
### D — Dependency Inversion Principle  
  
**High-level modules should not depend on low-level modules.Both should depend on abstractions.Abstractions should not depend on details.Details should depend on abdtractions.  
  
Depend on abstractions, not on concretions.  
A. High level modules should not depend upon low level modules. Both should depend upon abstractions.  
B. Abstractions should not depend upon details. Details should depend upon abstractions.  
  
# CLEAN ARCHITECTURE  
  
https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029  
  
Clean Architecture, as mentioned in the provided articles, makes your code:  
     
**Independent of Frameworks  
Testable.  
Independent of UI.  
Independent of Database.  
Independent of any external agency.**  
  
***What this means for Android***    
Generally, your app can have an arbitrary amount of layers but unless you have Enterprise wide business logic that you have to apply in every Android app, you will most often have 3 layers:  
  
**Outer:** Implementation layer - Database,UI, external Interfaces,Device  
**Middle:** Interface adapter layer - Presenter, Controller, Gateways  
**Inner:** Business logic layer - Entity  
  
#### Structure  
  
The general structure for an Android app looks like this:  

***Outer layer packages:*** UI, Storage, Network, etc.  
***Middle layer packages:*** Presenters, Converters  
***Inner layer packages:*** Interactors, Models, Repositories, Executor  
  
**Outer layer**  
  
As already mentioned, this is where the framework details go.  
  
**UI —** This is where you put all your Activities, Fragments, Adapters and other Android code related to the user interface.
  
**Storage —** Database specific code that implements the interface our Interactors use for accessing data and storing data.  
This includes, for example, ContentProviders or ORM-s such as DBFlow.  
  
**Network —** Things like Retrofit go here.  
  
**Middle layer**  
  
Glue code layer which connects the implementation details with your business logic.
**Presenters —** Presenters handle events from the UI (e.g. user click) and usually serve as callbacks from inner layers (Interactors).  
**Converters —** Converter objects are responsible for converting inner models to outer models and vice versa.  

**Inner layer**  
  
The core layer contains the most high-level code. All classes here are POJOs. Classes and objects in this layer have no knowledge that they are run in an Android app and can easily be ported to any machine running JVM.  

**Interactors —** These are the classes which actually contain your business logic code. These are run in the background and communicate events to the upper layer using callbacks. They are also called UseCases in some projects (probably a better name). It is normal to have a lot of small Interactor classes in your projects that solve specific problems. This conforms to the Single Responsibility Principle and in my opinion is easier on the brain.  

**Models —** These are your business models that you manipulate in your business logic.  

**Repositories —** This package only contains interfaces that the database or some other outer layer implements. These interfaces are used by Interactors to access and store data. This is also called a repository pattern.  
  
**Executor —** This package contains code for making Interactors run in the background by using a worker thread executor. This package is generally not something you need to change.  
  
## Java Design Patterns
  
In core java, there are mainly three types of design patterns, which are further divided into their sub-parts:  
  
#### 1.Creational Design Pattern

**Factory Pattern  
Abstract Factory Pattern  
Singleton Pattern  
Prototype Pattern  
Builder Pattern.**  
  
#### 2. Structural Design Pattern  
  
**Adapter Pattern  
Bridge Pattern  
Composite Pattern  
Decorator Pattern  
Facade Pattern  
Flyweight Pattern  
Proxy Pattern**  
  
#### 3. Behavioral Design Pattern  
  
**Chain Of Responsibility Pattern  
Command Pattern  
Interpreter Pattern  
Iterator Pattern  
Mediator Pattern  
Memento Pattern  
Observer Pattern  
State Pattern  
Strategy Pattern  
Template Pattern  
Visitor Pattern** 

            
