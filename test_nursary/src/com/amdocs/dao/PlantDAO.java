package com.amdocs.dao;
import com.amdocs.model.Plant;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.amdocs.exp.*;

public class PlantDAO {
	
	getConnection gt = new getConnection(); 
	Connection conn = gt.getCon();
	public int addPlant(Plant p) {
		String query2;
		String insertQuery = "INSERT INTO plant (plantId, plantName, originCountryName,"
				+ " sunLightRequired, waterSupplyFrequency, plantType, cost) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
		   preparedStatement.setInt(1, p.plantId);
		   preparedStatement.setString(2, p.plantName);
		   preparedStatement.setString(3, p.originCountryName);
		   if(p.sunLightRequired)
		   {
			   preparedStatement.setInt(4,1);
		   }
		   else
		   {
			   preparedStatement.setInt(4,0);
		   }
		   
		   preparedStatement.setString(5, p.waterSupplyFrequency);
		   preparedStatement.setString(6, p.plantType);
		   preparedStatement.setDouble(7, p.cost);

		   // Execute the query
		   int rowsInserted = preparedStatement.executeUpdate();
		   
		   if (rowsInserted > 0) {
			   return 0;
		      
		   } else {
			   return -1;
		   }
		} catch (SQLException e) {
		   e.printStackTrace();
		   // Handle the SQL exception appropriately
		}

		return 0;
		}

	
		public boolean deletePlant(int pid) {
			try{
				Statement stmt=conn.createStatement();
				String query2;
				String query1="select * from plant where plantId='"+pid+"'";
				ResultSet rs=stmt.executeQuery(query1);
				
				if(rs.next()) {
					query2="delete from plant where plantId='"+pid+"'";
					stmt.executeUpdate(query2);
					return true;	
				}
				
				else {
					return false;
					
				}

				} catch(SQLException e) {
					e.printStackTrace();
				}
			return true;
			}
		
		public boolean updatePlantCost(int plid,double newCost) {
			try {
				Statement stmt=conn.createStatement();
				String query2 = "UPDATE plant SET cost=" + newCost + " WHERE plantId='" + plid + "'";
				int rows=stmt.executeUpdate(query2);
				if(rows>0)
					return true;
				
				return false;
				} catch(SQLException e) {
		
				}
			return true;
			}

		public ArrayList<Plant> showAllPlants() throws nursaryException{
		ArrayList<Plant> arr=new ArrayList<Plant>();
		try {
		String query1="select * from plant";
		Statement stmt=conn.createStatement();
		ResultSet rs = stmt.executeQuery(query1);
		       while (rs.next()) {
		           // Retrieve by column name
		    	boolean sun = false;
		        int id=rs.getInt("plantId");
		        String pname=rs.getString("plantName");
		        String cname=rs.getString("originCountryName");
		        if(rs.getInt("sunLightRequired")==1)
		        {
		        	sun=true;
		        }
		        String water=rs.getString("waterSupplyFrequency");
		        String ptype=rs.getString("plantType");
		        double cost=rs.getDouble("cost");
		        Plant p=new Plant(id,pname,ptype,cname,water,cost,sun);
		           arr.add(p);
		        }
		       
		} catch(SQLException e) {

		}
		if(arr.size()==0)
		{
			throw new nursaryException("No Plant Found");
		}

		        return arr;
		}
		
		public ArrayList<Plant> searchByOriginCountryName(String country) throws nursaryException{
			ArrayList<Plant> arr=new ArrayList<Plant>();
			try {
			Statement stmt=conn.createStatement();
			String query1="select * from plant where originCountryName='"+country+"'";
			  ResultSet rs = stmt.executeQuery(query1);
			while (rs.next()) {
					boolean sun = false;
			        int id=rs.getInt("plantId");
			        String pname=rs.getString("plantName");
			        String cname=rs.getString("originCountryName");
			        if(rs.getInt("sunLightRequired")==1)
			        {
			        	sun = true;
			        }
			        String water=rs.getString("waterSupplyFrequency");
			        String ptype=rs.getString("plantType");
			        
			        double cost=rs.getDouble("cost");
			        Plant p=new Plant(id,pname,ptype,cname,water,cost,sun);
			           arr.add(p);
			        }
			      
			} catch(SQLException e) {
	
			}
			if(arr.size()==0)
			{
				throw new nursaryException("No plant found from "+country);
			}
		     return arr;
		}
		
		public ArrayList<Plant> searchOutdoorPlantsWithSunlight() throws nursaryException{
			ArrayList<Plant> arr=new ArrayList<Plant>();
			try {
			Statement stmt=conn.createStatement();
			String query1="select * from plant where plantType='outdoor' AND sunLightRequired=1";
			  ResultSet rs = stmt.executeQuery(query1);
			while (rs.next()) {
			           // Retrieve by column name
					boolean sun=false;
			        int id=rs.getInt("plantId");
			        String pname=rs.getString("plantName");
			        String cname=rs.getString("originCountryName");
			        if(rs.getInt("sunLightRequired")==1)
			        {
			        	sun=true;
			        }
			        String water=rs.getString("waterSupplyFrequency");
			        String ptype=rs.getString("plantType");
			        double cost=rs.getDouble("cost");
			        Plant p=new Plant(id,pname,ptype,cname,water,cost,sun);
			           arr.add(p);
			        }
		} catch(SQLException e) {

			}		        
			if(arr.size()==0)
			{
				throw new nursaryException("No plant found with the entred amount of sunlight ");
			}
			return arr;
		}
	
		public int countPlantsByWaterSupplyFrequency(String waterSupplyFrequency) {
			String query = "SELECT COUNT(*) FROM plant WHERE waterSupplyFrequency = '" +
			waterSupplyFrequency + "'";
			        try (Statement statement = conn.createStatement()) {
			            ResultSet resultSet = statement.executeQuery(query);
			            if (resultSet.next()) {
			                return resultSet.getInt(1);
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			           
			        }
			        return 0;
			}
}

