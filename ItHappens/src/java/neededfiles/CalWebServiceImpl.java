/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neededfiles;

/**
 *
 * @author PR-PC
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
@WebService(endpointInterface="neededfiles.CalWebService",
        portName="calWebServicePort",
        serviceName="calWebService")
public class CalWebServiceImpl implements CalWebService{
    @Override
   public int add(int x , int y){
    return x+y;
    }
   
   
   @Override
 public List<Courses> getData(String cname) {
        //TODO write your implementation code here:
        List<Courses> haha = new ArrayList<Courses>();
         try{


         Class.forName("com.mysql.jdbc.Driver");

 	
         Connection con=DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/ws_pbl","root","");
         PreparedStatement ps =con.prepareStatement
                             ("SELECT * FROM ws_pbl.courses where name LIKE '%"+cname+"%'");
         
         ResultSet rs =ps.executeQuery();
         
         while(rs.next()){
         haha.add(new Courses(rs.getString("name"),rs.getString("unvi"),rs.getString("link"),rs.getString("spl"),rs.getString("website")));
         }
        
      }catch(ClassNotFoundException | SQLException e)
      {
         
      }
        return haha;
    }
}
