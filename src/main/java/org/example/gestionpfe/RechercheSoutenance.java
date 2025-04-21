package org.example.gestionpfe;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class RechercheSoutenance extends JFrame {
    private JPanel jpRechercheEnseignant;
    private JTextField tftext;
    private JComboBox tableComboBox;
    private JTable table1;

    private JLabel recherchepar;
    private JLabel rechercheEnseignant;
    private DefaultTableModel tableModel;

    public RechercheSoutenance() {
        //setTitle("Recherche Soutenance");

        // Initialisation du modèle de tableau
        tableModel = new DefaultTableModel(new String[]{"Id", "Sujet", "Cycle", "Etudiant1", "Etudiant2", "President","Examinateur","Rapporteur","Encadreur","Invités","Note","Validation","NumGroupe"}, 0);
        Color customColor=new Color(225,239,230);
        Color customColor1=new Color(234,232,221);
        Color cellsColor = new Color(0,4,17);

        // Initialisation du tableau
        table1 = new JTable(tableModel);
        table1.setBackground(customColor1);
        table1.setBounds(154, 201, 277, 182);

        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setBounds(154, 201, 277, 182);
        scrollPane.setBackground(customColor);
        table1.setFont(new Font("NSimSun", Font.BOLD, 16));
        table1.setForeground(Color.WHITE);
        table1.getTableHeader().setBackground(new Color(0, 4, 17));
        table1.getTableHeader().setForeground(Color.WHITE);
        table1.getTableHeader().setFont(new Font("NSimSun", Font.BOLD, 16));

        // Initialisation du JComboBox, JTextField, et JButton
        tableComboBox = new JComboBox<>(new String[]{"Tout","ID", "Validation"});
        tableComboBox.setFont(new Font("NSimSun", Font.BOLD, 16));
        tableComboBox.setBackground(new Color(234, 232, 221));
        tableComboBox.setForeground(new Color(0, 4, 17)); // Dark blue text color

        tftext = new JTextField(20);
        tftext.setFont(new Font("NSimSun", Font.BOLD, 16));
        tftext.setBackground(new Color(234, 232, 221));
        tftext.setForeground(new Color(0, 4, 17));

        // Création du panneau de recherche
        JPanel titlePanel = new JPanel(new FlowLayout());
        JPanel searchPanel = new JPanel(new FlowLayout());

        rechercheEnseignant=new JLabel("Recherche Soutenance");
        recherchepar=new JLabel("Recherche par:");

        titlePanel.add(rechercheEnseignant);
        searchPanel.add(recherchepar);
        recherchepar.setFont(new Font("NSimSun", Font.BOLD, 16));
        recherchepar.setForeground(customColor1);
        searchPanel.add(tableComboBox);
        searchPanel.add(tftext);

        // Add components to the main panel
        add(new JLabel("Recherche Soutenance"), BorderLayout.NORTH);
        add(searchPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the JFrame on screen
        setVisible(true);
        // Création du panneau principal avec un layout BorderLayout
        jpRechercheEnseignant = new JPanel(new BorderLayout());

        jpRechercheEnseignant.setBackground(customColor);

        jpRechercheEnseignant.add(titlePanel, BorderLayout.PAGE_START);
        jpRechercheEnseignant.add(searchPanel, BorderLayout.BEFORE_FIRST_LINE);
        searchPanel.setBackground(customColor);
        jpRechercheEnseignant.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBounds(154, 201, 277, 182);
        table1.setBounds(154, 201, 277, 182);

        scrollPane.setBackground(customColor);

        // Configuration de la fenêtre
        setContentPane(jpRechercheEnseignant);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer l'application à la fermeture
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiser la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        setVisible(true); // Rendre la fenêtre visible

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYS as SYSDBA", "mazeenmt")) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM pfe ")) {
                // Assignation du paramètre de recherche


                // Exécution de la requête et mise à jour du tableau
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Effacement du contenu du tableau
                    //tableModel.setRowCount(0);

                    // Ajout des lignes au modèle de tableau à partir des résultats
                    while (resultSet.next()) {
                        tableModel.addRow(new Object[]{
                                resultSet.getString("Id"),
                                resultSet.getString("Sujet"),
                                resultSet.getString("Cycle"),
                                resultSet.getInt("Etudiant1"),
                                resultSet.getInt("Etudiant2"),
                                resultSet.getInt("Encadrant"),
                                resultSet.getInt("Jury"),
                                resultSet.getDate("Date"),
                                resultSet.getString("Heure"),
                                resultSet.getString("Salle"),
                                resultSet.getString("Note")
                        });
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tftext.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTableData((String) tableComboBox.getSelectedItem(), tftext.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTableData((String) tableComboBox.getSelectedItem(), tftext.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTableData((String) tableComboBox.getSelectedItem(), tftext.getText());
            }
        });
    }
    private void updateTableData(String selectedItem, String text) {
        String sql = "";
        System.out.println("wa");
        // Construction de la requête SQL en fonction du critère de recherche
        switch (selectedItem) {
           case"Tout":
                sql="SELECT * FROM etudiant ";
                System.out.println("Tout");
                break;
            case "ID":
                sql = "SELECT * FROM pfe WHERE id LIKE ?";
                System.out.println("ID");
                break;
            case "Validation":
                sql = "SELECT * FROM pfe WHERE valide LIKE ?";
                break;

            default:
                return;

        }

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYS as SYSDBA", "mazeenmt")) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Assignation du paramètre de recherche
                statement.setString(1, "%" + text + "%");

                // Exécution de la requête et mise à jour du tableau
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Effacement du contenu du tableau
                    tableModel.setRowCount(0);
                    System.out.println(resultSet.getString("ID"));

                    // Ajout des lignes au modèle de tableau à partir des résultats
                    while (resultSet.next()) {
                        System.out.println("ajout ligne");
                        tableModel.addRow(new Object[]{
                                resultSet.getString("Id"),
                                resultSet.getString("Sujet"),
                                resultSet.getString("Cycle"),
                                resultSet.getInt("Etudiant1"),
                                resultSet.getInt("Etudiant2"),
                                resultSet.getInt("Encadrant"),
                                resultSet.getInt("Jury"),
                                resultSet.getDate("Date"),
                                resultSet.getString("Heure"),
                                resultSet.getString("Salle"),
                                resultSet.getString("Note"),
                        });
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RechercheSoutenance().setVisible(true); // Démarrer le programme
    }
}