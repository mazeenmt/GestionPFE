package org.example.gestionpfe;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.sql.*;
import java.time.LocalDate;

import static org.example.gestionpfe.Main.showAlert;

public class Jdbc {
    private Connection connection;
    public Jdbc() {
        // Initialize the database connection
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "SYS as SYSDBA";
            String password = "mazeenmt";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean idExists(String id, String nomTable) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM " + nomTable + " WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                exists = (count > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
    public String getCycle(String id) {
        String query = "SELECT Cycle_type FROM Etudiants WHERE id = ?";
        String cycleType = "null";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cycleType = resultSet.getString("Cycle_type");
            } else {
                // Handle the case where the ID doesn't exist in the database
                System.out.println("ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cycleType;
    }
    //ComboBoxes
    void handleComboBoxInput(String input, String table, ObservableList<String> comboList, ComboBox<String> comboBox) {
        comboList.clear(); // Clear previous items

        try(PreparedStatement statement = connection.prepareStatement("SELECT id FROM " + table + " WHERE id LIKE ?")) {
            statement.setString(1, input + "%"); // Match IDs starting with the input
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String item = (String) resultSet.getString("id");
                comboList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        comboBox.setItems(comboList);
        comboBox.show(); // Show dropdown with matching IDs
    }
    public boolean addEtudiantToDb(Etudiant etudiant) {
        // Prepare the SQL INSERT statement
        String sql = "INSERT INTO etudiants (id, nom, prenom, email, date_naissance, telephone, cycle_type, specialite, niveau) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set values for the parameters
            statement.setInt(1, etudiant.getId());
            statement.setString(2, etudiant.getNom());
            statement.setString(3, etudiant.getPrenom());
            statement.setString(4, etudiant.getEmail());
            statement.setDate(5, Date.valueOf(etudiant.getDateNaissance()));
            statement.setInt(6, etudiant.getTel());
            statement.setString(7, etudiant.getCycle());
            statement.setString(8, etudiant.getSpecialite());
            statement.setInt(9, etudiant.getNiveau());

            // Execute the insert statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Etudiant added successfully.");
                return true;
            } else {
                System.out.println("Failed to add Etudiant.");
                return false;
            }
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }
    public boolean addEnseignantToDb(Enseignant enseignant) {
        // Prepare the SQL INSERT statement
        String sql = "INSERT INTO enseignants (id, nom, prenom, date_naissance, email, tel, departement) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set values for the parameters
            statement.setInt(1, enseignant.getId());
            statement.setString(2, enseignant.getNom());
            statement.setString(3, enseignant.getPrenom());
            statement.setString(4, enseignant.getEmail());
            statement.setDate(5, Date.valueOf(enseignant.getDateNaissance()));
            statement.setInt(6, enseignant.getTel());
            statement.setString(7, enseignant.getDepartement());

            // Execute the insert statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Enseignant added successfully.");
                return true;
            } else {
                System.out.println("Failed to add Enseignant.");
                return false;
            }
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }
    public boolean supprimerEtudOp(String sql) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowsInserted = statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }
    public boolean supprimerPFEOp(String sql) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowsInserted = statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }
    public boolean addPFEToDb(PFE pfe) {
        // Prepare the SQL INSERT statement
        String sql = "INSERT INTO PFEs (id, sujet, cycle, etudiant1, etudiant2, encadreur, datePFE, heure, salle) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set values for the parameters
            statement.setInt(1, pfe.getId());
            statement.setString(2, pfe.getSujet());
            statement.setString(3, pfe.getType());
            statement.setInt(4, pfe.getEtudiant1());
            statement.setInt(5, pfe.getEtudiant2());
            statement.setInt(6, pfe.getEncadreurPedagogique());
            statement.setDate(7, pfe.getDate());
            statement.setString(8, pfe.getHeure());
            statement.setString(9, pfe.getSalle());

            // Execute the insert statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Enseignant added successfully.");
                return true;
            } else {
                System.out.println("Failed to add Enseignant.");
                return false;
            }
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }
    //TableViewPFE
    public ObservableList<PFE> updateTableDataPFE(String selectedItem, String text, TableView<PFE> TableViewPFE, ObservableList<PFE> tableDataPFE) {
        String sql = "";

        switch (selectedItem) {
            case "Tout":
                sql = "SELECT * FROM pfes";
                break;
            case "Id":
                sql = "SELECT * FROM pfes WHERE id like '%' || ? || '%' ";
                break;
            case "Validation":
                sql = "SELECT * FROM pfes WHERE  REGEXP_LIKE(valide, ? )";
                break;
            case "Id Etudiant":
                sql = "SELECT * FROM pfes WHERE  REGEXP_LIKE(Etudiant1, ? ) or REGEXP_LIKE(Etudiant2, ? )";
                break;
            case "Id Encadreur":
                sql = "SELECT * FROM pfes WHERE  REGEXP_LIKE(Encadreur, ? )";
                break;
            case "Id President":
                sql = "SELECT * FROM pfes WHERE  REGEXP_LIKE(President, ? )";
                break;
            case "Id Rapporteur":
                sql = "SELECT * FROM pfes WHERE  REGEXP_LIKE(Rapporteur, ? )";
                break;
            case "Id Examinateur":
                sql = "SELECT * FROM pfes REGEXP_LIKE(Examinateur, ? )";
                break;
            case "Salle":
                sql = "SELECT * FROM pfes WHERE REGEXP_LIKE(Salle, ? )";
                break;
            default:
                return null;
        }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                if (!"Tout".equals(selectedItem)) {
                    statement.setString(1, text);
                    if("Id Etudiant".equals(selectedItem)){
                        statement.setString(2, text);
                    }
                }
                try (ResultSet resultSet = statement.executeQuery()) {
                    tableDataPFE.clear();  // Clear previous data
                    System.out.println("SQL: "+sql);
                    System.out.println("requete: "+statement);
                    //System.out.println("ResultSet: "+resultSet.next());
                    int rowCount = 0; // Variable to count the number of rows fetched
                    while (resultSet.next()) {
                        rowCount++;
                        System.out.println("Row " + rowCount + ": " + resultSet.getString("Sujet"));
                        PFE rowData = new PFE(
                                resultSet.getInt("Id"),
                                resultSet.getString("Sujet"),
                                resultSet.getString("Cycle"),
                                resultSet.getInt("Etudiant1"),
                                resultSet.getInt("Etudiant2"),
                                resultSet.getInt("Encadreur"),
                                resultSet.getInt("President"),
                                resultSet.getInt("Rapporteur"),
                                resultSet.getInt("Examinateur"),
                                resultSet.getDate("DatePFE"),
                                resultSet.getString("Heure"),
                                resultSet.getString("Salle"),
                                resultSet.getString("Note"),
                                resultSet.getString("Valide")
                        );
                        System.out.println(rowData.getSujet());
                        tableDataPFE.add(rowData);  // Add new row data to the list
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (tableDataPFE);
    }
    //TableViewEtud
    public ObservableList<Etudiant> updateTableDataEtud(String selectedItem, String text, TableView<Etudiant> TableViewEtud, ObservableList<Etudiant> tableDataEtud) {
        String sql = "";

        switch (selectedItem) {
            case "Tout":
                sql = "SELECT * FROM etudiants";
                break;
            case "Id":
                sql = "SELECT * FROM etudiants WHERE id like '%' || ? || '%' ";
                break;
            case "Nom":
                sql = "SELECT * FROM etudiants WHERE  REGEXP_LIKE(nom, ? )";
                break;
            case "Prenom":
                sql = "SELECT * FROM etudiants WHERE  REGEXP_LIKE(prenom, ? )";
                break;
            case "DateNaissance":
                sql = "SELECT * FROM etudiants WHERE  REGEXP_LIKE(date_naissance, ? )";
                break;
            case "Email":
                sql = "SELECT * FROM etudiants WHERE  REGEXP_LIKE(email, ? )";
                break;
            case "Telephone":
                sql = "SELECT * FROM etudiants WHERE  REGEXP_LIKE(tel, ? )";
                break;
            case "Cycle":
                sql = "SELECT * FROM etudiants REGEXP_LIKE(cycle, ? )";
                break;
            case "Specialite":
                sql = "SELECT * FROM etudiants WHERE REGEXP_LIKE(specialite, ? )";
                break;
            case "Niveau":
                sql = "SELECT * FROM etudiants WHERE REGEXP_LIKE(niveau, ? )";
                break;
            default:
                return null;
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            if (!"Tout".equals(selectedItem)) {
                statement.setString(1, text); // Set the placeholder if needed
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                tableDataEtud.clear();  // Clear previous data
                System.out.println("SQL: "+sql);
                System.out.println("requete: "+statement);
                //System.out.println("ResultSet: "+resultSet.next());
                int rowCount = 0; // Variable to count the number of rows fetched
                while (resultSet.next()) {
                    rowCount++;
                    Etudiant rowData = new Etudiant(
                            resultSet.getInt("Id"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            LocalDate.parse(resultSet.getString("date_naissance").split(" ")[0]),
                            resultSet.getString("email"),
                            resultSet.getInt("telephone"),
                            resultSet.getString("cycle_type"),
                            resultSet.getString("specialite"),
                            resultSet.getInt("niveau")
                    );
                    tableDataEtud.add(rowData);  // Add new row data to the list
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (tableDataEtud);
    }
    public static void main (String[] args){
    }
}