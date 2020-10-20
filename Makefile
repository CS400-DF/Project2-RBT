run: compile
        java inventoryDriver

compile: InventoryApp.class inventoryDriver.class

test:TestInventoryApp.class 
        java -jar junit5.jar --class-path . --scan-classpath --details tree
                
InventoryApp.class:
        javac InventoryApp.java

inventoryDriver.class:
        javac inventoryDriver.java
        
TestInventoryApp.class:InventoryApp.class 
        javac -cp .:junit5.jar TestInventoryApp.java

clean:
        $(RM) *.class
