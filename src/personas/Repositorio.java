package personas;

import database.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Repositorio {
	
    private static DBManager database = new DBManager();

    public static void crear (Persona persona) {
        try {
            String query = "INSERT INTO personas (documento, nombre,  email) VALUES (?, ?, ?);";
            PreparedStatement sentenciaP = database.open().prepareStatement(query);
            sentenciaP.setInt(1, persona.getDocumento());
            sentenciaP.setString(2, persona.getNombre());
            //sentenciaP.setString(3, persona.getTelefono());
            sentenciaP.setString(4, persona.getEmail());
            //sentenciaP.setString(5, persona.getLugar_nacimiento());
            //sentenciaP.setString(6, persona.getFoto());
            sentenciaP.executeUpdate();
            sentenciaP.close();
            database.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void editar (Persona persona) {
        try {
            String query = "UPDATE personas SET documento = ?, nombre = ?, email = ? WHERE id = ?;";
            PreparedStatement sentenciaP = database.open().prepareStatement(query);
            sentenciaP.setInt(1, persona.getDocumento());
            sentenciaP.setString(2, persona.getNombre());
           // sentenciaP.setString(3, persona.getTelefono());
            sentenciaP.setString(4, persona.getEmail());
            //sentenciaP.setString(5, persona.getLugar_nacimiento());
            //sentenciaP.setString(6, persona.getFoto());
            sentenciaP.setString(7, Integer.toString(persona.getId()));

            sentenciaP.executeUpdate();
            sentenciaP.close();
            database.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void eliminar (Persona persona) {
        try {
            String query = "DELETE FROM personas WHERE id = ?;";
            PreparedStatement sentenciaP = database.open().prepareStatement(query);
            
            sentenciaP.setString(1, Integer.toString(persona.getId()));
            sentenciaP.execute();
            sentenciaP.close();
            database.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Persona obtener(int id) {
        try {
            String query = "SELECT * FROM personas WHERE id = ?;";
            PreparedStatement sentenciaP = database.open().prepareStatement(query);
            sentenciaP.setString(1, Integer.toString(id));

            ResultSet resultado = sentenciaP.executeQuery();

            sentenciaP.close();
            database.close();

            while (resultado.next()) {
                return Persona.crear(resultado.getInt("id"), resultado.getInt("documento"), resultado.getString("nombre"), resultado.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    
    
    public static Persona buscar(String documento) {
        try {
            String query = "SELECT * FROM personas WHERE documento = ?;";
            PreparedStatement sentenciaP = database.open().prepareStatement(query);
            sentenciaP.setString(1, documento);

            ResultSet resultado = sentenciaP.executeQuery();

            while (resultado.next()) {
                return Persona.crear(resultado.getInt("id"), resultado.getInt("documento"), resultado.getString("nombre"),  resultado.getString("email"));
            }
            
            sentenciaP.close();
            database.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static ArrayList<Persona> obtenerTodos() {
        ArrayList<Persona> personas = new ArrayList<Persona>();

        try {
            String query = "SELECT * FROM personas;";
            PreparedStatement sentenciaP = database.open().prepareStatement(query);
            ResultSet resultado = sentenciaP.executeQuery();

            while (resultado.next()) {
                personas.add(Persona.crear(resultado.getInt("id"), resultado.getInt("documento"), resultado.getString("nombre"), resultado.getString("email")));
            }

            sentenciaP.close();
            database.close();

            return personas;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return personas;
    }
}
