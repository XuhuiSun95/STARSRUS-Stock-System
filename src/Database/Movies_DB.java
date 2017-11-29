package Database;

import Utility.Utility;
import java.sql.*;

public class Movies_DB{
    public static String get_movie(String title){
        String QUERY = "SELECT * " +
                       "FROM moviesDB.Movies " +
                       "WHERE title = " + "'" + title + "'";
        ResultSet res = Utility.sql_query(QUERY);

        String info = "";
        try{
            while(res.next()){
                int id = res.getInt("id");
                String name = res.getString("title");
                double rating = res.getDouble("rating");
                int year = res.getInt("production_year");

                info += "\t" + "ID: " + String.valueOf(id);
                info += ", Title: " + name;
                info += ", Rating: " + String.valueOf(rating);
                info += ", Production Year: " + String.valueOf(year);
                info += "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return info;
    }

    public static String top_movies(String time_interval){
        int s_year = Integer.valueOf(time_interval.substring(0,4));
        int e_year = Integer.valueOf(time_interval.substring(5,9));

        String QUERY = "SELECT * " +
                       "FROM moviesDB.Movies " +
                       "WHERE rating = " + "5" + 
                       " AND production_year >= " + String.valueOf(s_year) +
                       " AND production_year <= " + String.valueOf(e_year);
        ResultSet res = Utility.sql_query(QUERY);

        String info = "";
        try{
            while(res.next()){
                int id = res.getInt("id");
                String name = res.getString("title");
                double rating = res.getDouble("rating");
                int year = res.getInt("production_year");

                info += "\t" + "ID: " + String.valueOf(id);
                info += ", Title: " + name;
                info += ", Rating: " + String.valueOf(rating);
                info += ", Production Year: " + String.valueOf(year);
                info += "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return info;
    }

    public static String display_review(String title){
        String QUERY = "SELECT * " +
                       "FROM moviesDB.Movies " +
                       "WHERE title = " + "'" + title + "'";
        ResultSet res = Utility.sql_query(QUERY);

        int id = -1;
        try{
            while(res.next()){
                id = res.getInt("id");
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        if(id==-1)
            return "";

        QUERY = "SELECT * " +
                "FROM moviesDB.Reviews " +
                "WHERE movie_id = " + String.valueOf(id);
        res = Utility.sql_query(QUERY);

        String reviews = "";
        try{
            while(res.next()){
                int review_id = res.getInt("id");
                int movie_id = res.getInt("movie_id");
                String author = res.getString("author");
                String review = res.getString("review");

                reviews += "\t" + "ID: " + String.valueOf(review_id);
                reviews += ", Movie ID: " + String.valueOf(movie_id);
                reviews += ", Author: " + String.valueOf(author);
                reviews += ", Review: " + String.valueOf(review);
                reviews += "\n";
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return reviews;
    }

}
