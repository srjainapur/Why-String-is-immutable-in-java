Why String is immutable in Java?
Let’s look at some of the benefits of String immutability, that will help in understanding why String is immutable in Java.

1. String pool is possible only because String is immutable in Java. This way Java Runtime saves a lot of heap space because different String variables can refer to the same String variable in the pool. If String would not have  been immutable, then String interning would not have been possible because if any variable would have changed the value, it would have been reflected in the other variables too.
2. If String is not immutable then it would cause a severe security threat to the application. For example, database username, password are passed as String to get database connection and in socket programming host and port details passed as String. Since String is immutable, its value can’t be changed otherwise any hacker could change the referenced value to cause security issues in the application.
3. Since String is immutable, it is safe for multithreading. A single String instance can be shared across different threads. This avoids the use of synchronization for thread safety. Strings are implicitly thread-safe.
4. Strings are used in java classloader and immutability provides security that correct class is getting loaded by Classloader. For example, think of an instance where you are trying to load java.sql.Connection class but the referenced value is changed to myhacked.Connection class that can do unwanted things to your database.
5. Since String is immutable, its hashcode is cached at the time of creation and it doesn’t need to be calculated again. This makes it a great candidate for the key in a Map and its processing is faster than other HashMap key objects. This is why String is the most widely used as HashMap keys.


Immutable Class in Java

An immutable class is good for caching purpose because you don’t need to worry about the value changes. Other benefit of immutable class is that it is inherently thread-safe, so you don’t need to worry about thread safety in case of multi-threaded environment.

To create an immutable class in java, you have to do following steps.
1. Declare the class as final so it can’t be extended.
2. Make all fields private so that direct access is not allowed.
3. Don’t provide setter methods for variables
4. Make all mutable fields final so that it’s value can be assigned only once.
5. Initialize all the fields via a constructor performing deep copy.
6. Perform cloning of objects in the getter methods to return a copy rather than returning the actual object reference.

Output of the above immutable class in java example program is:
Performing Deep Copy for Object initialization
true
false
ce id:10
ce name:original
ce testMap:{2=second, 1=first}
ce id after local variable change:10
ce name after local variable change:original
ce testMap after local variable change:{2=second, 1=first}
ce testMap after changing variable from accessor methods:{2=second, 1=first}


Now let’s comment the constructor providing deep copy and uncomment the constructor providing a shallow copy. Also uncomment the return statement in getTestMap() method that returns the actual object reference and then execute the program once again.
Performing Shallow Copy for Object initialization
true
true
ce id:10
ce name:original
ce testMap:{2=second, 1=first}
ce id after local variable change:10
ce name after local variable change:original
ce testMap after local variable change:{3=third, 2=second, 1=first}
ce testMap after changing variable from accessor methods:{3=third, 2=second, 1=first, 4=new}

As you can see from the output, HashMap values got changed because of shallow copy in the constructor and providing a direct reference to the original object in the getter function.
