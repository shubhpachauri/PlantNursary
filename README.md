# PlantNursary

1. Introduction

1.1. Purpose of the Document
The purpose of this document is to provide an overview of the Plant Nursery System project
developed using Core Java. It outlines the system&#39;s requirements, architecture, features, user
interface.

1.2. Project Overview
The Plant Nursery System is a console-based application that allows users to perform various plant
search activities such as plant details management, view all plants of nursery, search plants by type,
cost and more. The system aims to provide user-friendly interface for nursery owner and plant
buyers to see plant details and search it as per requirement.
Note: for core java track participants will do this project as console-based menu driven code.

1.3. Scope
The scope of the Plant Nursery System project includes the following functionalities:
- Plant management (add, update, delete plants).
- View all plants available for sale .
- Search plants by origin country name
- Search outdoor plants which requires sunlight
- Count plants by water supply frequency

2. System Requirements

2.1. Functional Requirements
1. Plant Management: Users should be able to add, update, and delete plant details.
2. View All: Users should be able to view all plants available for sale.
3. Search by origin country name: Users should be able to search plants by their origin country
name.
4. Search outdoor plants which requires sunlight: Users should be able to search outdoor plant

details which requires sunlight

5. Count plants by water supply frequency: Users should be able to count plants as per given water

supply frequency

3. Architecture

3.1. High-Level Architecture
The Plant Nursery System will follow a menu driven application. The application will handle logic,
data processing and database interactions.

3.2. Class Diagram
Plant

-plantId:int
-plantName:String
-originCountyName:String
-sunlightRequired:boolean
-waterSupplyFrequency:String
-plantType:String
-cost:double

PlantDAO
+addPlant(Plant):int
+deletePlant(int):int
+updatePlantCost(int,double):boolean
+showAllPlants():&lt;List&gt;
+searchByOriginCountryName(String):&lt;List&gt;
+searchOutdoorPlantsWithSunlight():&lt;List&gt;
+countPlantsByWaterSupplyFrequency(String):in
t

Note: Here plant type is indoor/outdoor and water supply frequency is daily/alternate days/weekly
4. User Interface
The Plant Nursery System should have menu to select operation to perform. Wherever search
operation doesn’t return any result, show appropriate message. E.g. If for given origin country name,
plant doesn’t exist , show message “Plant not available”.
Menu should have below options
Enter your choice:
1. Add new plant
2. Update plant cost
3. Delete plant
4. View all plants
5. Find plant by origin country name

6. Find outdoor plants which requires sunlight
7. Count plants by water supply frequency
8. Exit
After user selects one option, take input from user wherever applicable. E.g. If option 7 is selected,
application should ask to enter water supply frequency (daily/weekly/alternate days) and then show
result.

5. Technologies Used
. Core Java, JDBC, Oracle
6. Conclusion
The Plant Nursery System will help nursery owner to advertise their plants for sale and buyers can
search it as per their requirement.
7. References
https://docs.oracle.com/javase/8/docs/
https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/
