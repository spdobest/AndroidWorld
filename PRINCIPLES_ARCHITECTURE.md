# This will explain the below concepts  
**1. SOLID Principle**   
**2. Clean Architecture**  
**3. Functional Design Pattern**  
**4. Architeture Design Pattern**  
  
## SOLID Principle  
For More details please visit the site  
https://android.jlelse.eu/basic-concepts-of-software-architecture-patterns-in-android-c76e53f46cba  
  
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
        
        

### Liskove Substitution Principle  
  
Derived classes must be substitutable for their base classes.
What is wanted here is something like the following substitution property: If
for each object o1 of type S there is an object o2 of type T such that for all
programs P defined in terms of T, the behavior of P is unchanged when o1 is
substituted for o2 then S is a subtype of T.  
  
### I — Interface Segregation Principle  
  
Make fine grained interfaces that are client specific.  
Clients should not be forced to implement interfaces they do not use.  
  
### D — Dependency Inversion Principle  
  
Depend on abstractions, not on concretions.  
A. High level modules should not depend upon low level modules. Both should depend upon abstractions.  
B. Abstractions should not depend upon details. Details should depend upon abstractions.  
Robert C. Martin


            
