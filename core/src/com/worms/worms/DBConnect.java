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


            System.out.print("Polaczono z baza danych!"+ "\n");
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
    public boolean registration(String Login, String Haslo, String Nick)
    {
        if(!Login.trim().equals("") && !Haslo.trim().equals("") && !Nick.trim().equals("")) {
            try {
                String query = "select * from Java";
                rs = st.executeQuery(query);
                boolean wlacz = true;
                while (rs.next()) {
                    String login = rs.getString("Login");
                    if (Login.trim().equals(login)) {

                        wlacz = false;
                    }

                }


                if (wlacz) {
                    String sql = "insert into Java "
                            + " (Login, Haslo, Nick)"
                            + "values ('" + Login + "', '" + Haslo + "', '" + Nick + "')";
                    st.executeUpdate(sql);
                    System.out.print("Nowy uzytkownik utwozony!" + "\n");
                    return true;
                } else {
                    System.out.print("Jest juz taki uzytkownik!" + "\n");
                }


            } catch (Exception e) {
                System.out.print("EX: " + e + "\n");
            }
        }else
        {
            System.out.print("Wypelnij pola" + "\n");
        }
        return false;
    }
    public boolean logowanie(String Login, String Haslo)
    {
        try{
            String query = "select * from Java";
            rs = st.executeQuery(query);

            while(rs.next())
            {
                String login = rs.getString("Login");
                if(Login.trim().equals(login)) {

                    String haslo = rs.getString("Haslo");
                    if (Haslo.trim().equals(haslo)) {
                        System.out.print("Zalogowano: "+ login +"\n");
                        return true;
                    }
                }

            }
            System.out.print("Bledne dane logowania!"+ "\n");
            return false;
        }catch(Exception e )
        {
            System.out.print("Cosi nie pyklo" + e + "\n");
        }
        return false;
    }
}
