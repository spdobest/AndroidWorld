# Kotlin
## Coroutines
- Important Links : https://kotlinlang.org/docs/reference/coroutines/basics.html
- Package name : kotlinx.coroutines
- Coroutines are light weight threads.
- They are launched with launch coroutine builder in a context of some CoroutineScope.
- Here we are launching a new coroutine in the GlobalScope, meaning that the lifetime of the new coroutine is limited only by the lifetime of the whole application.
- ```
  fun main() {
      GlobalScope.launch { // launch a new coroutine in background and continue
          delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
          println("World!") // print after delay
      }
      println("Hello,") // main thread continues while coroutine is delayed
      Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
  }
  ```
## Key Points in Coroutines
- **suspend**
    - Suspend function's are like Backbone of Coroutines
    - coroutines are computations that can be suspended without blocking a thread
    - A process is suspended means that the OS has stopped executing it, but that could just be for time-slicing (multitasking). 
    - There is no implication that the process can not be resumed immediately.
    - 
- **GlobalScope.launch{ }**
    - starts a new coroutine in the 'global' scope
    - Global coroutines are like daemon threads
- launch{ } => starts a new Coroutine in a CoroutineScope
- **CoroutineScope:**
    - Helps to define the lifecycle of Kotlin Coroutines. 
    - It can be application-wide or bound to a component like the Android Activity. 
    - You have to use a scope to start a coroutine.
- Dispatchers
    - Dispatchers.IO
    - Dispatchers.main
    - Dispatchers.CPU
- runBlocking{ }


