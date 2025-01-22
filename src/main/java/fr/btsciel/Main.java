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

            PreparedStatement preparedStatement = null;
            ResultSet rs = null;

            System.out.println("1) Lister les voitures par nombre de voitures vendues");
            System.out.println("2) Afficher les  9 premières voitures les plus vendues");
            System.out.println("3) Afficher la 9 ième voiture la plus vendue");
            System.out.println("4) Afficher les voitures de la marque Renault");
            System.out.println("5) Afficher la somme des ventes de voitures de la marque Renault");
            System.out.println("6) Afficher les voitures dont le nom du modèle commence par c");
            System.out.println("7) Afficher le nombre d’enregistrement de votre bdd");
            System.out.println("8) Afficher la somme des voitures vendues");
            System.out.println("9) Afficher les voitures dont le modèle contient un i");
            System.out.println("10) Afficher les voitures dont la deuxième lettre de la marque est i");
            System.out.println("11) Afficher les voitures dont le nom du modèle finit par o");
            System.out.println("12) Afficher les ventes par marque");
            System.out.println("13) Afficher les ventes de voitures en affichant leur rang en fonction du nombre de ventes");


            menu = In.readInteger();


            switch(menu) {
                case 1: //Lister les voitures par nombre de voitures vendues.
                    preparedStatement = connection.prepareStatement("SELECT * FROM `voitures` ORDER BY nombre DESC;\n");
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        System.out.println(rs.getString("modele"));
                        System.out.println("\t" + rs.getInt("nombre"));
                    }
                    break;
                case 2: //Afficher les  9 premières voitures les plus vendues
                    preparedStatement = connection.prepareStatement("SELECT * FROM `voitures` LIMIT 9;\n");
                    rs = preparedStatement.executeQuery();
                    while (rs.next()){
                        System.out.println(rs.getString("modele"));
                        System.out.println("\t" + rs.getInt("nombre"));
                    }
                    break;
                case 3: //Afficher la 9 ième voiture la plus vendue
                    preparedStatement = connection.prepareStatement("SELECT * FROM `voitures` LIMIT 9,1;");
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        System.out.println(rs.getString("modele"));
                        System.out.println("\t" + rs.getInt("nombre"));
                    }
                    break;
                case 4: //Afficher les voitures de la marque Renault
                    preparedStatement = connection.prepareStatement("SELECT * FROM `voitures` WHERE marque = \"Renault\";\n");
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        System.out.println(rs.getString("modele"));
                        System.out.println("\t" + rs.getInt("nombre"));
                    }
                    break;
                case 5: //Afficher la somme des ventes de voitures de la marque Renault
                    preparedStatement = connection.prepareStatement("SELECT SUM(nombre) FROM `voitures` WHERE marque = \"Renault\";");
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        System.out.println("\t" + rs.getInt("SUM(nombre)"));
                    }
                    break;
                case 6: //Afficher les voitures dont le nom du modèle commence par c
                    preparedStatement = connection.prepareStatement("SELECT * FROM `voitures` WHERE modele LIKE \"C%\";\n");
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        System.out.println(rs.getString("modele"));
                        System.out.println("\t" + rs.getInt("nombre"));
                    }
                    break;
                case 7: //Afficher le nombre d’enregistrement de votre bdd
                    preparedStatement = connection.prepareStatement("SELECT COUNT(*) voitures FROM `voitures`;\n");
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        System.out.println(rs.getString("voitures"));
                    }
                    break;
                case 8: //Afficher la somme des voitures vendues
                    preparedStatement = connection.prepareStatement("SELECT SUM(nombre) FROM `voitures` \n");
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        System.out.println("\t" + rs.getInt("SUM(nombre)"));
                    }
                    break;
                case 9: //Afficher les voitures dont le modèle contient un i
                    preparedStatement = connection.prepareStatement("SELECT * FROM `voitures` WHERE modele LIKE \"%i%\";\n");
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        System.out.println(rs.getString("modele"));
                        System.out.println("\t" + rs.getInt("nombre"));
                    }
                    break;
                case 10: //Afficher les voitures dont la deuxième lettre de la marque est i
                    preparedStatement = connection.prepareStatement("SELECT * FROM `voitures` WHERE marque LIKE \"_i%\";");
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        System.out.println(rs.getString("modele"));
                        System.out.println("\t" + rs.getInt("nombre"));
                    }
                    break;
                case 11: //Afficher les voitures dont le nom du modèle finit par o
                    preparedStatement = connection.prepareStatement("SELECT * FROM `voitures` WHERE modele LIKE \"%o\";\n");
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        System.out.println(rs.getString("modele"));
                        System.out.println("\t" + rs.getInt("nombre"));
                    }
                    break;
                case 12: //Afficher les ventes par marque
                    preparedStatement = connection.prepareStatement("SELECT SUM(nombre), marque FROM `voitures` GROUP BY marque;");
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        System.out.println(rs.getString("marque"));
                        System.out.println("\t" + rs.getInt("SUM(nombre)"));
                    }
                    break;
                case 13: //Afficher les ventes de voitures en affichant leur rang en fonction du nombre de ventes
                    preparedStatement = connection.prepareStatement("SELECT ROW_NUMBER() OVER (order by nombre desc) as rang, marque, modele,nombre from voitures");
                    rs = preparedStatement.executeQuery();

                    while (rs.next()) {
                        System.out.print("""
                        %-7s""".formatted(rs.getString("rang")));
                        System.out.print("""
                        %-15s""".formatted(rs.getString("marque")));
                        System.out.print("""
                        %-15s""".formatted(rs.getString("modele")));
                        System.out.println("""
                        %-7s""".formatted(rs.getInt("nombre")));
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