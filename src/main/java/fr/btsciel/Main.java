package fr.btsciel;

import fr.btsciel.clavier.In;

import java.lang.runtime.SwitchBootstraps;
import java.sql.*;

public class Main {
    public static void main(String[] args) {


        int menu = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Pilote fonctionnel");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventes2024", "root", "");

            PreparedStatement ps = null;
            ResultSet rs = null;




            System.out.println("""
                    1. Afficher une voiture selon l'id
                    2. Trier selon le nombre de ventes
                    3.
                    4.
                    """);

            menu = In.readInteger();
            int nbreLigne = 0;

            switch(menu) {
                case 1:
                    ps = connection.prepareStatement("SELECT * FROM `voitures` WHERE `id` = ?");

                    System.out.println("Entrez l'id de votre choix");
                    ps.setInt(1, In.readInteger());
                    rs = ps.executeQuery();


                    while (rs.next()) {
                        nbreLigne = rs.getRow();
                        System.out.println(rs.getString("id") + " " + rs.getString("marque") + " " + rs.getString("modele") + " " + rs.getString("nombre") + " " + rs.getString("energie"));
                    }

                    if (nbreLigne == 0){
                        System.out.println("Il n'y a pas d'enregistrement ayant cet id");
                    }
                    break;

                case 2:
                    ps = connection.prepareStatement("SELECT * FROM `voitures` WHERE nombre >? ORDER BY nombre DESC");

                    System.out.println("Entrez le nombre de votre choix");
                    ps.setInt(1, In.readInteger());
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        nbreLigne = rs.getRow();
                        System.out.println(rs.getString("id") + " " + rs.getString("marque") + " " + rs.getString("modele") + " " + rs.getString("nombre") + " " + rs.getString("energie"));
                    }

                    break;

                case 3:
                    ps = connection.prepareStatement("INSERT INTO voitures (id,modele,marque,nombre,energie) VALUES (null,?,?,?,?)");

                    System.out.println("Entrez le modèle");
                    ps.setInt(1, In.readInteger());
                    
                    ps.setString(2, In.readString());
                    ps.setString(3, In.readString());
                    ps.setString(4, In.readString());

                    while (rs.next()) {
                        nbreLigne = rs.getRow();
                        System.out.println(rs.getString("id") + " " + rs.getString("marque") + " " + rs.getString("modele") + " " + rs.getString("nombre") + " " + rs.getString("energie"));
                    }

                    break;

            }
            rs.close();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}