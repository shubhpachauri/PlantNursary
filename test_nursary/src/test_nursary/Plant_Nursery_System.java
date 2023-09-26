package test_nursary;
import com.amdocs.dao.*;
import com.amdocs.exp.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.amdocs.model.Plant;

public class Plant_Nursery_System {
	
public static void main(String args[]) {

        Scanner sc=new Scanner(System.in);

        PlantDAO p = new PlantDAO();
        int choice=0;
	
        do {
        	
	        System.out.println("Press 1 to add new plant");
	        System.out.println("Press 2 to update plant cost");
	        System.out.println("Press 3 to delete plant");
	        System.out.println("Press 4 to view all plants");
	        System.out.println("Press 5 to find plant by origin country name");
	        System.out.println("Press 6 to find outdoor plants which requires sunlight");
	        System.out.println("Press 7 to count plants by water supply frequency");
	        System.out.println("Press 8 to exit");
	        System.out.println("---------------------------------------");
	        System.out.println("Please enter your choice");
	        try{choice = sc.nextInt();
	       
	        System.out.println("---------------------------------------");
	        switch(choice) {
        	case 1:
		        System.out.println("Enter all details");
		        System.out.println("---------------------------------------");
		        System.out.print("Enter Plant ID: ");
		        int id = sc.nextInt();
		        sc.nextLine(); // Consume the newline character
		        
		        System.out.println("---------------------------------------");
		        System.out.print("Enter Plant Name: ");
		        String pname = sc.nextLine();
		
		        System.out.println("---------------------------------------");
		        System.out.print("Enter Origin Country Name: ");
		        String cname = sc.nextLine();
		        
		        System.out.println("---------------------------------------");
		        System.out.print("Enter Sunlight Required (1 for true, 0 for false): ");
		        int sun = sc.nextInt();
		        boolean flag=false;
		        if(sun==1) {flag=true;}
		        sc.nextLine(); // Consume the newline character
		        
		        System.out.println("---------------------------------------");
		        System.out.print("Enter Water Supply Frequency: (daily, weekly, monthly)");
		        String water = sc.nextLine();
		
		        System.out.println("---------------------------------------");
		        System.out.print("Enter Plant Type (indoor/outdoor): ");
		        String type = sc.nextLine();
		        
		        System.out.println("---------------------------------------");
		        System.out.print("Enter Cost: ");
		        double cost = sc.nextDouble();
		        sc.nextLine(); 
		
		        Plant pl = new Plant(id,pname,type,cname,water,cost,flag);
		       
		        try{
		        	int status = p.addPlant(pl);
		        	if(status == 0)
			        {
			        	System.out.println("---------------------------------------");
			        	System.out.println("Plant record inserted successfully.");
			        	System.out.println("---------------------------------------");
			        }
		        	else {
		        		
		        		throw new nursaryException("Plant record inserted un-successfully.");
		        		
		        	}
		        }
		        catch(nursaryException e)
		        {
		        	System.out.println(e);
		        }
		        
		        break;
		        
        	case 2:
	        	System.out.println("---------------------------------------");
		        System.out.println("Enter updated cost");
		        try{
		        	double ucost=sc.nextDouble();
		        
		        if(ucost <=0)
		        {
		        	throw new nursaryException("Cost has to be more than 0");
		        }
		        sc.nextLine();
		        System.out.println("---------------------------------------");
		        System.out.println("Enter plant id where updation needs to be done");
		        int plid=sc.nextInt();
		        boolean b=p.updatePlantCost(plid,ucost);
		        if(b)
		        {
		        	System.out.println("---------------------------------------");
		        	System.out.println("Plant Details updated sucessfully");
		        	System.out.println("---------------------------------------");
		        }
		        }
		        catch(nursaryException e)
		        {
		        	System.out.println("---------------------------------------");
		        	System.out.println(e);
		        	System.out.println("---------------------------------------");
		        }
		        break;
		        
	        case 3:
		        System.out.println("---------------------------------------");
		        System.out.println("Enter the id of plant which has to be deleted");
		        int plid2=sc.nextInt();
		        boolean status = p.deletePlant(plid2);
		        if(status) {
		        	System.out.println("---------------------------------------");
		        	System.out.println("Plant deleted sucessfully");
		        	System.out.println("---------------------------------------");
		        }
		        else
		        {
		        	System.out.println("---------------------------------------");
		        	System.out.println("Plant not found");
		        	System.out.println("---------------------------------------");
		        }
		    break;
	        
	        case 4:
		        System.out.println("---------------------------------------");
		        try{ArrayList<Plant> arr=p.showAllPlants();
		        for(int i=0;i<arr.size();i++) {
			        Plant t=arr.get(i);
			        System.out.println("Plant ID : "+ t.plantId+" ");
			        System.out.println("Plant Name : "+ t.plantName+" ");
			        System.out.println("Country of Origin : "+ t.originCountryName+" ");
			        System.out.println("Plant Type : "+ t.plantType+" ");
			        System.out.println("Sunlight required : "+ t.sunLightRequired+" ");
			        System.out.println("Cost : "+ t.cost);
			        System.out.println("---------------------------------------");   
		        }
		        }
		        catch(nursaryException e)
		        {
		        	System.out.println("---------------------------------------");
		        	System.out.println("Plant not found");
		        	System.out.println("---------------------------------------");
		        }
	        break;
	        
	        case 5:
		        System.out.println("---------------------------------------");
		        System.out.println("Enter the name of country of origin : ");
		        sc.nextLine();
		        System.out.println("---------------------------------------");
		        String cntry=sc.nextLine();
		        cntry = cntry.toLowerCase();
		        System.out.println("---------------------------------------");
		        
		        try {ArrayList<Plant> arr2=p.searchByOriginCountryName(cntry);
		        
		        for(int i=0;i<arr2.size();i++) {
			        Plant t=arr2.get(i);
			        System.out.println();
			        System.out.println("Plant ID : "+ t.plantId+" ");
			        System.out.println("Plant Name : "+ t.plantName+" ");
			        System.out.println("Country of Origin : "+ t.originCountryName+" ");
			        System.out.println("Sun Light Required : "+ t.sunLightRequired+" ");
			        System.out.println("Plant Type : "+ t.plantType+" ");
			        System.out.println("Cost : "+ t.cost);
			        System.out.println("---------------------------------------");
			       }
		        }
		        catch(nursaryException e)
		        {
		        	System.out.println("---------------------------------------");
		        	System.out.println(e);
		        	System.out.println("---------------------------------------");
		        }
	        break;
	        
	        
	        case 6:
		        System.out.println("---------------------------------------");
		        try {ArrayList<Plant> arr3 = p.searchOutdoorPlantsWithSunlight();	
		        for(int i=0;i<arr3.size();i++) {
			        Plant t=arr3.get(i);
			        System.out.println();
			        System.out.println("Plant ID : "+ t.plantId+" ");
			        System.out.println("Plant Name : "+ t.plantName+" ");
			        System.out.println("Country of Origin : "+ t.originCountryName+" ");
			        System.out.println("Sun Light Required : "+ t.sunLightRequired+" ");
			        System.out.println("Plant Type : "+ t.plantType+" ");
			        System.out.println("Cost : "+ t.cost);
			        System.out.println("---------------------------------------");
			       }
		        }
		        catch(nursaryException e)
		        {
		        	System.out.println("---------------------------------------");
		        	System.out.println(e);
		        	System.out.println("---------------------------------------");
		        }
	        break;
	        
	        case 7:
		        System.out.println("---------------------------------------");
		        System.out.println("Enter whether search is to be daily, weekly or monthly ");
		        sc.nextLine();
		        String water1=sc.nextLine();
		        System.out.println("---------------------------------------");
		        int ans=p.countPlantsByWaterSupplyFrequency(water1);
		        System.out.println("Total plants with your condition "+water1+" is "+ans);
	        break;
	        
	        case 8:
		        System.out.println("---------------------------------------");
		        System.out.println("Exiting from menu");
		        System.out.println("--------THANKS--FOR--USING--APP------");
	        break;
	        
	        default:
		        System.out.println("---------------------------------------");
		        System.out.println("Invalid choice");
	        break;
	        
	        } //end of switch
	        }catch(Exception e)
	        {
	        	System.out.println("Please enter a vaild input from the menu");
	        	sc.nextLine();
	        }
        
	         } while(choice!=8);
        }
	       	       
}

   
