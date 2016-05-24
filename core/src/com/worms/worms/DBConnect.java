package com.worms.worms;

import java.sql.*;

/**
 * Created by Wojciech on 2016-05-23.
 */
public class DBConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
            st = con.createStatement();


            System.out.print("Chyba tak");
        }catch(Exception e){
            System.out.print("Error: " + e + "\n");
        }
    }

    public void getData(){
        try{
            String query = "select * from Java";
            rs = st.executeQuery(query);
            System.out.print("Dane w bazie: " + "\n");
            while(rs.next())
            {
                String login = rs.getString("Login");
                String haslo = rs.getString("Haslo");
                String nick = rs.getString("Nick");
                System.out.print(login + " " + haslo + " " + nick + "\n");
            }
        }catch(Exception e )
        {
            System.out.print("Nie udalo sie." + e + "\n");
        }

    }
    public void registration(String Login, String Haslo, String Nick)
    {
        try {
            String sql = "insert into Java "
                    + " (Login, Haslo, Nick)"
                    + "values ('"+Login+"', '"+Haslo+"', '"+Nick+"')";
            st.executeUpdate(sql);
        }catch(Exception e)
        {
            System.out.print("EX: "+ e+ "\n");
        }
    }
    public boolean logowanie(String Login, String Haslo)
    {
        try{
            String query = "select * from Java";
            rs = st.executeQuery(query);

            while(rs.next())
            {
                String login = rs.getString("Login");
                if(login == Login) {
                    System.out.print(login + " "  + "\n");
                    String haslo = rs.getString("Haslo");
                    if (haslo == Haslo) {
                        System.out.print(login + " " + haslo + " " + "\n");
                        return true;
                    }
                }

            }
        }catch(Exception e )
        {
            System.out.print("Cosi nie pyklo" + e + "\n");
        }
        return false;
    }
}
