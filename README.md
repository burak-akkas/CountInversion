# Folder Structure
+ Jar
  - count.jar
  + Test Dataset
	- test.txt
+ Source
  - Main.java

# Compiling
In /Source directory,
> javac Main.java

# Running
- From compiled source
> java Main -i test.txt

- From JAR
> cd Jar
> java -jar count.jar -i "Test Dataset/test.txt"
