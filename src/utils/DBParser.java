package utils;

import essences.*;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.sql.*;

/**
 * Created by Shoma on 15.04.2017.
 */
public class DBParser {

    public Connection initConnection()
    {
        Connection connection = null;
        try {Class.forName("org.postgresql.Driver");}
        catch (ClassNotFoundException e) {e.printStackTrace();}
        try {connection = DriverManager.getConnection(
                            "jdbc:postgresql://localhost/ashamaz",
                            "postgres", "mutanabbi1");}
        catch (SQLException e) {e.printStackTrace();}
        return connection;
    }

    public void selectQuestions() {
        Connection connection = initConnection();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet result =
                    statement.executeQuery("select * from public.questions");
            JAXBContext context;
            Marshaller marshaller = null;
            Question q1 = null;
            Questions q=new Questions();;
            while (result.next()) {

        q1 = new Question();
                q1.setQuest(result.getString(2));
                q1.setAnswer(result.getString(3));
                q1.setScore(result.getInt("score"));
q.getQuestions().add(q1);

             context = JAXBContext.newInstance(Questions.class);

               marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            }
         File file =    new File("question.xml");

            marshaller.marshal(q, file);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }


    public void selectGamers() {
        Connection connection = initConnection();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet result =
                    statement.executeQuery("select * from public.gamer");
            JAXBContext context;
            Marshaller marshaller = null;
            Gamer q1 = null;
            Gamers q=new Gamers();
            while (result.next()) {

                q1 = new Gamer();
                q1.setNiackname(result.getString("nickname"));
                q1.setScore(result.getInt("csore"));
                q1.setPassword(result.getString("password"));
                q.getGamers().add(q1);

                context = JAXBContext.newInstance(Gamers.class);

                marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            }
            File file =    new File("gamer.xml");

            marshaller.marshal(q, file);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }



    public void insertQuestion(String quest, String answer, int score) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO questions  (question, answer, score) VALUES (?,?, ?)");
            preparedStatement.setString(1, quest);
            preparedStatement.setString(2, answer);
            preparedStatement.setInt(3, score);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQuestion(String fieldForSet, String whatToSet,String where) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE questions SET "+ fieldForSet + "= '"+whatToSet+"'"+ " WHERE "+where);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteQuestion(String where) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM questions WHERE "+where);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void insertGamer(String nickname, String password, int score) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO gamer  (nickname, csore, password) VALUES (?,?, ?)");
            preparedStatement.setString(1, nickname);
            preparedStatement.setInt(2, score);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGamer(String fieldForSet, String whatToSet,String where) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE gamer SET "+ fieldForSet + "= '"+whatToSet+"'"+ " WHERE "+where);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGamer(String where) {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM gamer WHERE "+where);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public void cleanGamers()
{
    Connection connection = initConnection();
    try {
        PreparedStatement preparedStatement =
                connection.prepareStatement("truncate table gamer");
        preparedStatement.executeUpdate();
//        File file =    new File("gamer.xml");
//
//       Marshaller marshaller;
//        Gamers q=new Gamers();
//        marshaller = JAXBContext.newInstance(Gamers.class).createMarshaller();
//        marshaller.marshal(q, file);
//        try {
//            DBParser dbParser = new DBParser();
//            dbParser.selectGamers();
//        } catch (NullPointerException ex)
//        {
//            System.out.println("Таблица 'gamers' пуста");
//        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
//    catch (JAXBException e) {
//        e.printStackTrace();
//    }
}

    public void cleanQuestions()
    {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("truncate table questions");
            preparedStatement.executeUpdate();
//            File file =    new File("question.xml");
//
//            Marshaller marshaller;
//            Questions q=new Questions();
//            marshaller = JAXBContext.newInstance(Questions.class).createMarshaller();
//            marshaller.marshal(q, file);
//            try {
//                DBParser dbParser = new DBParser();
//                dbParser.selectQuestions();
//            } catch (NullPointerException ex)
//            {
//                System.out.println("Таблица 'questions' пуста");
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        catch (JAXBException e) {
//            e.printStackTrace();
//        }
    }

    public void fromXMLtoDBQuest()
    {

        try {
            File file =    new File("question.xml");
            Unmarshaller unmarshaller = JAXBContext.newInstance(Questions.class).createUnmarshaller();
            Questions questions;
          questions= (Questions) unmarshaller.unmarshal(file);

            for (int i = 0; i < questions.getQuestions().size(); i++) {
               Question q =  questions.getQuestions().get(i);
                insertQuestion(q.getQuest(), q.getAnswer(), q.getScore());
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void fromXMLtoDBGamer()
    {
        try {
            File file =    new File("gamer.xml");
            Unmarshaller unmarshaller = JAXBContext.newInstance(Gamers.class).createUnmarshaller();
            Gamers gamers;
            gamers= (Gamers) unmarshaller.unmarshal(file);

            for (int i = 0; i < gamers.getGamers().size(); i++) {
                Gamer q =  gamers.getGamers().get(i);
                insertGamer(q.getNiackname(), q.getPassword(), q.getScore());
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}





