run:InventoryDriver.class
	java CovidAppDriver

compile InventoryApp.class:

test:TestInventoryApp.class 
	java -jar junit5.jar --class-path . --scan-classpath --details tree
		
InventoryApp.class:
	javac InventoryApp.java

InventoryDriver.class:InventoryApp.class
	javac InventoryDriver.java

JUnit5:
	wget http://pages.cs.wisc.edu/~cs400/junit5.jar
	
TestInventoryApp.class:JUnit5 InventoryApp.class 
        javac -cp .:junit5.jar TestInventoryApp.java



clean:
	$(RM) junit5.jar
	$(RM) *.class
