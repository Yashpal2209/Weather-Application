/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String city=request.getParameter("city");
        
        String details=request.getParameter("GET Details");
        String apikey="9cdca7c037d9e5fcc97df7a784393022";
        String apiurl="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apikey;
        String apiurl2="https://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid="+apikey;
        
        try{
                int d;
                if (details.equalsIgnoreCase("Today")){
                    d=1;
                }
                else if (details.equalsIgnoreCase("1 Week")){
                    d=7;
                }else{
                    d=30;
                }
             
                URL url=new URL(apiurl2);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");

                InputStream inputStream=con.getInputStream();
                InputStreamReader reader=new InputStreamReader(inputStream);
                Scanner sc=new Scanner(reader);
                StringBuilder rescontent=new StringBuilder();

                while(sc.hasNext()){
                    rescontent.append(sc.nextLine());

                }
                sc.close();

                Gson gson=new Gson();
                JsonObject jsonObject=gson.fromJson(rescontent.toString(),JsonObject.class);
                JsonArray js1 =jsonObject.getAsJsonArray("list");
                String Date[]=new String[d];
                int Temp[]=new int[d];
                int Humidity[]=new int[d];
                double Wind[]=new double[d];
                String Weather[]=new String[d];
                for(int i=0;i<d;i++){
                    JsonObject js=js1.get(i).getAsJsonObject();
                    long dateTimestamp=js.get("dt").getAsLong()*1000;
                    String date=new Date(dateTimestamp).toString();
                    Date[i]=date;
                    double tempreatureKelvin=js.getAsJsonObject("main").get("temp").getAsDouble();
                    int temp=(int) (tempreatureKelvin-273.15);
                    Temp[i]=temp;
                    int humidity=js.getAsJsonObject("main").get("humidity").getAsInt();
                    Humidity[i]=humidity;
                    double wind=js.getAsJsonObject("wind").get("speed").getAsDouble();
                    Wind[i]=wind;

                    String weathercon = js.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
                    Weather[i]=weathercon;
                
            }
                request.setAttribute("date", Date);
                request.setAttribute("city", city);
                request.setAttribute("temperature", Temp);
                request.setAttribute("weatherCondition", Weather); 
                request.setAttribute("humidity", Humidity);    
                request.setAttribute("windSpeed", Wind);
            

                request.getRequestDispatcher("index.jsp").forward(request, response);
            
        }catch(IOException e){
        }
        
        
        
        
        
        
        
        
        
        
                
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
