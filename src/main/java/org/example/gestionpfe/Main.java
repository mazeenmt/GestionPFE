package org.example.gestionpfe;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.application.Application.launch;

public class Main extends Application implements Initializable {
    Jdbc database = new Jdbc();
    //Panes
    @FXML
    private Pane Accueil;
    @FXML
    private Pane AjouterPFE;
    @FXML
    private Pane AjouterEtudiant;
    @FXML
    private Pane AjouterEnseignant;
    @FXML
    private Pane ConsulterPFE;
    @FXML
    private Pane EvaluerPFE;
    @FXML
    private Pane ConsulterEtudiants;
    @FXML
    private Pane ConsulterEnseignants;
    @FXML
    private Pane ModifierPFE;
    //Buttons
    @FXML
    private Button CloseButton;
    @FXML
    private Button ButAjouterPFE;
    @FXML
    private Button ButAjouterEtudiant;
    @FXML
    private Button ButConsulterPFE;
    @FXML
    private Button ButEvaluerPFE;
    @FXML
    private Button ButAccueil;
    @FXML
    private Button ButConsulterEns;
    @FXML
    private Button ButAjouterEnseignant;
    @FXML
    private Button ButConsulterEtud;
    //Inputs
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    // Ajouter Etudiant
    @FXML
    private TextField InputIdEtud;
    @FXML
    private TextField InputNomEtud;
    @FXML
    private TextField InputPrenomEtud;
    @FXML
    private TextField InputEmailEtud;
    @FXML
    private DatePicker InputDateNaissEtud;
    @FXML
    private TextField InputTelEtud;
    @FXML
    private ComboBox<String> ComboSpecialiteEtud;
    @FXML
    private ComboBox<Integer> ComboNiveauEtud;
    @FXML
    private Button ButAjouterEtudOp;
    @FXML
    public Label SuccessAjouterEtud;
    //Ajouter Enseignant
    @FXML
    private TextField InputIdEns;
    @FXML
    private TextField InputNomEns;
    @FXML
    private TextField InputPrenomEns;
    @FXML
    private TextField InputEmailEns;
    @FXML
    private DatePicker InputDateNaissEns;
    @FXML
    private TextField InputTelEns;
    @FXML
    private ComboBox<String> ComboDepartementEns;
    @FXML
    private Button ButAjouterEnsOp;
    @FXML
    public Label SuccessAjouterEns;
    //Ajouter PFE
    @FXML
    private TextField InputSujetPFE;
    @FXML
    private ComboBox<String> Combo1erEtudPFE;
    @FXML
    private ObservableList<String> ComboList1erEtudPFE = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> Combo2emeEtudPFE;
    @FXML
    private ObservableList<String> ComboList2emeEtudPFE = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> ComboEncadreurPFE;
    @FXML
    private ObservableList<String> ComboListEncadreurPFE = FXCollections.observableArrayList();
    @FXML
    private DatePicker InputDatePFE;
    @FXML
    private TextField InputHeurePFE;
    @FXML
    private ComboBox<String> ComboSallePFE;
    @FXML
    private Button ButAjouterPFEOp;
    @FXML
    private Label SuccessAjouterPFE;
    //Modifier PFE
    @FXML
    private TextField InputSujetPFEM;
    @FXML
    private ComboBox<String> Combo1erEtudPFEM;
    @FXML
    private ComboBox<String> Combo2emeEtudPFEM;
    @FXML
    private ComboBox<String> ComboEncadreurPFEM;
    @FXML
    private DatePicker InputDatePFEM;
    @FXML
    private TextField InputHeurePFEM;
    @FXML
    private ComboBox<String> ComboSallePFEM;
    @FXML
    private Button ButModifierPFEOp;
    @FXML
    private Label SuccessModifierPFE;
    //TableView PFE
    @FXML
    public TextField InputRechPFE;
    @FXML
    public ComboBox<String> ComboRechPFE;
    @FXML
    public TableView<PFE> tableViewPFE;
    @FXML
    private TableColumn<PFE, String> IDColumnPFE;
    @FXML
    private TableColumn<PFE, String> SujetColumnPFE;
    @FXML
    private TableColumn<PFE, String> CycleColumnPFE;
    @FXML
    private TableColumn<PFE, String> Etud1ColumnPFE;
    @FXML
    private TableColumn<PFE, String> Etud2ColumnPFE;
    @FXML
    private TableColumn<PFE, String> EncadreurColumnPFE;
    @FXML
    private TableColumn<PFE, String> PresidentColumnPFE;
    @FXML
    private TableColumn<PFE, String> RapporteurColumnPFE;
    @FXML
    private TableColumn<PFE, String> ExaminateurColumnPFE;
    @FXML
    private TableColumn<PFE, String> DateColumnPFE;
    @FXML
    private TableColumn<PFE, String> HeureColumnPFE;
    @FXML
    private TableColumn<PFE, String> SalleColumnPFE;
    @FXML
    private TableColumn<PFE, String> NoteColumnPFE;
    @FXML
    private TableColumn<PFE, String> ResultatColumnPFE;

    private ObservableList<PFE> tableDataPFE = FXCollections.observableArrayList();


    //TableView Etudiants
    @FXML
    public TextField InputRechEtud;
    @FXML
    public ComboBox<String> ComboRechEtud;
    @FXML
    public TableView<Etudiant> tableViewEtud;
    @FXML
    private TableColumn<PFE, String> IDColumnEtud;
    @FXML
    private TableColumn<PFE, String> NomColumnEtud;
    @FXML
    private TableColumn<PFE, String> PrenomColumnEtud;
    @FXML
    private TableColumn<PFE, String> DateNaissColumnEtud;
    @FXML
    private TableColumn<PFE, String> EmailColumnEtud;
    @FXML
    private TableColumn<PFE, String> TelColumnEtud;
    @FXML
    private TableColumn<PFE, String> CycleColumnEtud;
    @FXML
    private TableColumn<PFE, String> SpecialiteColumnEtud;
    @FXML
    private TableColumn<PFE, String> NiveauColumnEtud;

    private ObservableList<PFE> tableDataEtud = FXCollections.observableArrayList();




    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("resources/org/example/gestionpfe/Accueil.fxml"));
            primaryStage.setTitle("Your Application Title");
            primaryStage.setScene(new Scene(root, 1300, 700));
            primaryStage.setFullScreen(true);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources){
        System.out.println("++++++++++++++++++++++++++++++");
        //set buttons invisible
        ButAccueil.setVisible(false);
        ButAjouterPFE.setVisible(false);
        ButAjouterEtudiant.setVisible(false);
        ButAjouterEnseignant.setVisible(false);
        ButConsulterEtud.setVisible(false);
        ButConsulterEns.setVisible(false);
        ButConsulterPFE.setVisible(false);
        ButEvaluerPFE.setVisible(false);
        //Ajouter Etudiant
        ComboSpecialiteEtud.getItems().addAll("LIC INFO", "LIC EEA ", "LIC TIC", "LIC MATH", "MST Génie Logiciel", "MST Data Science", "ING INFO", "ING Eléctronique", "ING Eléctrique");
        ComboDepartementEns.getItems().addAll("Informatique", "Electronique", "Mathématique");
        ComboNiveauEtud.getItems().addAll(1, 2, 3);
        ComboNiveauEtud.setValue(null);
        //Ajouter Enseignant
        ComboDepartementEns.setValue("--Choisir Département--");
        ComboSpecialiteEtud.setValue("--Choisir Spécialité--");
        //Ajouter PFE
        ComboSallePFE.getItems().addAll("Salle des thèses", "C01", "C11", "C12", "C13", "C14", "C15", "C16", "C21", "C22", "C23", "C24", "C25");
        Combo1erEtudPFE.setOnKeyReleased(event -> {
            String input = Combo1erEtudPFE.getEditor().getText();
            if (event.getCode().isDigitKey()) {
                database.handleComboBoxInput(input, "Etudiants", ComboList1erEtudPFE, Combo1erEtudPFE);
            }});
        Combo2emeEtudPFE.setOnKeyReleased(event -> {String input = Combo2emeEtudPFE.getEditor().getText();
            if (event.getCode().isDigitKey()) {
                database.handleComboBoxInput(input, "Etudiants", ComboList2emeEtudPFE, Combo2emeEtudPFE);
            }});
        ComboEncadreurPFE.setOnKeyReleased(event -> {String input = ComboEncadreurPFE.getEditor().getText();
                if (event.getCode().isDigitKey()) {
                    database.handleComboBoxInput(input, "Enseignants", ComboListEncadreurPFE, ComboEncadreurPFE);
                }});
        //TableView PFE
        ComboRechPFE.getItems().addAll("Tout", "Id", "Validation", "Id Etudiant", "Id Encadreur", "Id President", "Id Rapporteur", "Id Examinateur", "Salle");
        ComboRechPFE.setValue("Tout");
        // Define the cell value factories for each column
        IDColumnPFE.setCellValueFactory(new PropertyValueFactory<>("id"));
        SujetColumnPFE.setCellValueFactory(new PropertyValueFactory<>("Sujet"));
        CycleColumnPFE.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Etud1ColumnPFE.setCellValueFactory(new PropertyValueFactory<>("Etudiant1"));
        Etud2ColumnPFE.setCellValueFactory(new PropertyValueFactory<>("Etudiant2"));
        EncadreurColumnPFE.setCellValueFactory(new PropertyValueFactory<>("EncadreurPedagogique"));
        PresidentColumnPFE.setCellValueFactory(new PropertyValueFactory<>("President"));
        RapporteurColumnPFE.setCellValueFactory(new PropertyValueFactory<>("Rapporteur"));
        ExaminateurColumnPFE.setCellValueFactory(new PropertyValueFactory<>("Examinateur"));
        DateColumnPFE.setCellValueFactory(new PropertyValueFactory<>("Date"));
        HeureColumnPFE.setCellValueFactory(new PropertyValueFactory<>("Heure"));
        SalleColumnPFE.setCellValueFactory(new PropertyValueFactory<>("Salle"));
        NoteColumnPFE.setCellValueFactory(new PropertyValueFactory<>("Note"));
        ResultatColumnPFE.setCellValueFactory(new PropertyValueFactory<>("valide"));

        // Update table data initially
        System.out.println("*********************************");
        refreshPfeTab();
        InputRechPFE.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshPfeTab();
        });

        tableViewPFE.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                PFE editedPFE = tableViewPFE.getSelectionModel().getSelectedItem();
                showModifierPFE(editedPFE);
            }
        });


        //tableau
        ComboRechEtud.getItems().addAll("Tout", "Id", "Nom", "Prenom", "DateNaissance", "Email", "Telephone", "Cycle", "Specialite", "Niveau");
        ComboRechEtud.setValue("Tout");
        IDColumnEtud.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomColumnEtud.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrenomColumnEtud.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        DateNaissColumnEtud.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        EmailColumnEtud.setCellValueFactory(new PropertyValueFactory<>("email"));
        TelColumnEtud.setCellValueFactory(new PropertyValueFactory<>("tel"));
        CycleColumnEtud.setCellValueFactory(new PropertyValueFactory<>("cycle"));
        SpecialiteColumnEtud.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        NiveauColumnEtud.setCellValueFactory(new PropertyValueFactory<>("niveau"));

        refreshEtudTab();
        InputRechEtud.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshEtudTab();
        });

        //Close button
        CloseButton.setOnAction(this::closeWindow);
    }
    @FXML
    public void connect(){
        if(username.getText().equals("admin") && password.getText().equals("admin")){
            ButAccueil.setVisible(true);
            ButAjouterPFE.setVisible(true);
            ButAjouterEtudiant.setVisible(true);
            ButAjouterEnseignant.setVisible(true);
            ButConsulterEtud.setVisible(true);
            ButConsulterEns.setVisible(true);
            ButConsulterPFE.setVisible(true);
            ButEvaluerPFE.setVisible(true);
            showAjouterEtudiant();
        }
    }
    @FXML
    public void refreshPfeTab() {
        System.out.println("--------------------------------------------" + ComboRechPFE.getValue() + "::" + InputRechPFE.getText());
        ObservableList<PFE> tableDataPFE = FXCollections.observableArrayList();
        tableDataPFE = database.updateTableDataPFE(ComboRechPFE.getValue(), InputRechPFE.getText(), tableViewPFE, tableDataPFE);
        tableViewPFE.setItems(tableDataPFE);
        tableViewPFE.refresh();
    }

    @FXML
    public void refreshEtudTab() {
        System.out.println("--------------------------------------------" + ComboRechPFE.getValue() + "::" + InputRechPFE.getText());
        ObservableList<Etudiant> tableDataEtud = FXCollections.observableArrayList();
        tableDataEtud = database.updateTableDataEtud(ComboRechEtud.getValue(), InputRechEtud.getText(), tableViewEtud, tableDataEtud);
        tableViewEtud.setItems(tableDataEtud);
        tableViewEtud.refresh();
    }

    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }
    public static void showAlert(Stage stage, String title,String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.showAndWait();
    }
    public static void main(String[] args){
        launch(args);
    }

    //Controle de saisie
    public boolean cntrlId(String id){
        if (id.length()==8){
            try {
                Integer.parseInt(id);
                return true;
            } catch(NumberFormatException e){
                return false;
            }
        }
        return false;
    }
    public boolean cntrlNom(String nom){
        char[] chars = nom.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c))
                return false;
        }
        return true;
    }
    public boolean cntrlEmail(String email){
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //Navigation within panes
    @FXML
    private void showAccueil() {
        Accueil.setVisible(true);
        ConsulterPFE.setVisible(false);
        EvaluerPFE.setVisible(false);
        AjouterPFE.setVisible(false);
        AjouterEtudiant.setVisible(false);
        AjouterEnseignant.setVisible(false);
        ConsulterEnseignants.setVisible(false);
        ConsulterEtudiants.setVisible(false);
        ModifierPFE.setVisible(false);
    }
    @FXML
    private void showAjouterPFE() {
        Accueil.setVisible(false);
        ConsulterPFE.setVisible(false);
        EvaluerPFE.setVisible(false);
        AjouterPFE.setVisible(true);
        AjouterEtudiant.setVisible(false);
        AjouterEnseignant.setVisible(false);
        ConsulterEnseignants.setVisible(false);
        ConsulterEtudiants.setVisible(false);
        ModifierPFE.setVisible(false);
    }
    @FXML
    private void showAjouterEtudiant() {
        Accueil.setVisible(false);
        ConsulterPFE.setVisible(false);
        EvaluerPFE.setVisible(false);
        AjouterPFE.setVisible(false);
        AjouterEtudiant.setVisible(true);
        AjouterEnseignant.setVisible(false);
        ConsulterEnseignants.setVisible(false);
        ConsulterEtudiants.setVisible(false);
        ModifierPFE.setVisible(false);
    }
    @FXML
    private void showAjouterEnseignant() {
        Accueil.setVisible(false);
        ConsulterPFE.setVisible(false);
        EvaluerPFE.setVisible(false);
        AjouterPFE.setVisible(false);
        AjouterEtudiant.setVisible(false);
        AjouterEnseignant.setVisible(true);
        ConsulterEnseignants.setVisible(false);
        ConsulterEtudiants.setVisible(false);
        ModifierPFE.setVisible(false);
    }
    @FXML
    private void showConsulterEtud() {
        Accueil.setVisible(false);
        ConsulterPFE.setVisible(false);
        EvaluerPFE.setVisible(false);
        AjouterPFE.setVisible(false);
        AjouterEtudiant.setVisible(false);
        AjouterEnseignant.setVisible(false);
        ConsulterEnseignants.setVisible(false);
        ConsulterEtudiants.setVisible(true);
        ModifierPFE.setVisible(false);
    }
    @FXML
    private void showConsulterPFE() {
        Accueil.setVisible(false);
        ConsulterPFE.setVisible(true);
        EvaluerPFE.setVisible(false);
        AjouterPFE.setVisible(false);
        AjouterEtudiant.setVisible(false);
        AjouterEnseignant.setVisible(false);
        ConsulterEnseignants.setVisible(false);
        ConsulterEtudiants.setVisible(false);
        ModifierPFE.setVisible(false);
    }
    @FXML
    public void supprimerEtud() {
        String sql = "DELETE FROM etudiants WHERE id = " + tableViewEtud.getSelectionModel().getSelectedItem().getId();
        database.supprimerEtudOp(sql);
        refreshEtudTab();
    }
    @FXML
    public void supprimerPFE() {
        String sql = "DELETE FROM pfes WHERE id = " + tableViewPFE.getSelectionModel().getSelectedItem().getId();
        database.supprimerPFEOp(sql);
        refreshPfeTab();
    }
    @FXML
    private void showConsulterEnseignant() {
        Accueil.setVisible(false);
        ConsulterPFE.setVisible(false);
        EvaluerPFE.setVisible(false);
        AjouterPFE.setVisible(false);
        AjouterEtudiant.setVisible(false);
        AjouterEnseignant.setVisible(false);
        ConsulterEnseignants.setVisible(true);
        ConsulterEtudiants.setVisible(false);
        ModifierPFE.setVisible(false);
    }
    @FXML
    private void showEvaluerPFE() {
        Accueil.setVisible(false);
        ConsulterPFE.setVisible(false);
        EvaluerPFE.setVisible(true);
        AjouterPFE.setVisible(false);
        AjouterEtudiant.setVisible(false);
        AjouterEnseignant.setVisible(false);
        ConsulterEnseignants.setVisible(false);
        ConsulterEtudiants.setVisible(false);
        ModifierPFE.setVisible(false);
    }

    public void showModifierPFE(PFE editedPFE){
        Accueil.setVisible(false);
        ConsulterPFE.setVisible(false);
        EvaluerPFE.setVisible(false);
        AjouterPFE.setVisible(false);
        AjouterEtudiant.setVisible(false);
        AjouterEnseignant.setVisible(false);
        ConsulterEnseignants.setVisible(false);
        ConsulterEtudiants.setVisible(false);
        ModifierPFE.setVisible(true);

        InputSujetPFEM.setText(editedPFE.getSujet());
        Combo1erEtudPFEM.setValue(editedPFE.getEtudiant1() + "");
        Combo2emeEtudPFEM.setValue(editedPFE.getEtudiant2() + "");
        ComboEncadreurPFEM.setValue(editedPFE.getEncadreurPedagogique() + "");
        InputDatePFEM.setValue(editedPFE.getDate().toLocalDate());
        InputHeurePFEM.setText(editedPFE.getHeure());
        ComboSallePFEM.setValue(editedPFE.getSalle());
    }

    //Get data from inputs
        //Ajouter Etudiant
    @FXML
    void AjouterEtudiantOp(ActionEvent event){
        boolean err = false;
        Etudiant etud = new Etudiant();
        InputIdEtud.setStyle(InputIdEtud.getStyle() + "-fx-text-fill: #000000;");
        InputNomEtud.setStyle(InputIdEtud.getStyle() + "-fx-text-fill: #000000;");
        InputPrenomEtud.setStyle(InputIdEtud.getStyle() + "-fx-text-fill: #000000;");
        InputEmailEtud.setStyle(InputIdEtud.getStyle() + "-fx-text-fill: #000000;");
        InputTelEtud.setStyle(InputIdEtud.getStyle() + "-fx-text-fill: #000000;");
        if(!cntrlId(InputIdEtud.getText())) {
            err = true;
            InputIdEtud.setStyle(InputIdEtud.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if(!cntrlNom(InputNomEtud.getText())){
            err = true;
            InputNomEtud.setStyle(InputNomEtud.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if(!cntrlNom(InputPrenomEtud.getText())){
            err = true;
            InputPrenomEtud.setStyle(InputPrenomEtud.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if(!cntrlEmail(InputEmailEtud.getText())){
            err = true;
            InputEmailEtud.setStyle(InputEmailEtud.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if(InputDateNaissEtud.getValue() == null){
            err = true;
            InputDateNaissEtud.setStyle(InputDateNaissEtud.getStyle() + "-fx-text-fill: #ff0000;");
        }
        if(!cntrlId(InputTelEtud.getText())){
            err = true;
            InputTelEtud.setStyle(InputTelEtud.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if(ComboSpecialiteEtud.getValue() == "" || ComboNiveauEtud.getValue() == null){
            err = true;
        }
        if(!err){
            if(database.idExists(InputIdEtud.getText(), "etudiants")){
                InputIdEtud.setStyle(InputIdEtud.getStyle() +"-fx-text-fill: #ff0000;");
                showAlert((Stage) ButAjouterEtudOp.getScene().getWindow() ,"Erreur d'ajout","Cet étudiant existe déja!");
            }
            else{
                etud.setId(Integer.parseInt(InputIdEtud.getText()));
                etud.setNom(InputNomEtud.getText());
                etud.setPrenom((InputPrenomEtud.getText()));
                etud.setEmail(InputEmailEtud.getText());
                etud.setDateNaissance(InputDateNaissEtud.getValue());
                etud.setTel(Integer.parseInt(InputTelEtud.getText()));
                String spec = String.valueOf(ComboSpecialiteEtud.getValue());
                etud.setSpecialite(spec);
                etud.setNiveau(Integer.parseInt(String.valueOf((ComboNiveauEtud.getValue()))));
                String cycle;
                if (spec.substring(0, 3) == "LIC")
                    cycle = "Licence";
                else if (spec.substring(0, 3) == "MST")
                    cycle = "Mastère";
                else cycle = "Ingénieurie";
                etud.setCycle(cycle);
                InputIdEtud.setText("");
                InputNomEtud.setText("");
                InputPrenomEtud.setText("");
                InputEmailEtud.setText("");
                InputDateNaissEtud.setValue(null);
                InputTelEtud.setText("");
                ComboSpecialiteEtud.setValue("--Choisir Spécialité--");
                ComboNiveauEtud.setValue(null);
                if (database.addEtudiantToDb(etud)) {
                    SuccessAjouterEtud.setStyle("-fx-text-fill: #2b9e1c");
                    SuccessAjouterEtud.setText("Etudiant ajouté avec succés.");
                } else {
                    showAlert((Stage) ButAjouterEtudOp.getScene().getWindow(), "Erreur", "Erreur dans l'ajout à la base de données!");
                }
            }
        }
        else{
            SuccessAjouterEtud.setStyle("-fx-text-fill: #ff0000;");
            SuccessAjouterEtud.setText("Vérifiez vos données.");
        }
    }
    @FXML
    void EffacerEtudBut(){
        InputIdEtud.setStyle(InputIdEtud.getStyle() +"-fx-text-fill: #000000;");
        InputNomEtud.setStyle(InputIdEtud.getStyle() +"-fx-text-fill: #000000;");
        InputPrenomEtud.setStyle(InputIdEtud.getStyle() +"-fx-text-fill: #000000;");
        InputEmailEtud.setStyle(InputIdEtud.getStyle() +"-fx-text-fill: #000000;");
        InputTelEtud.setStyle(InputIdEtud.getStyle() +"-fx-text-fill: #000000;");
        InputIdEtud.setText("");
        InputNomEtud.setText("");
        InputPrenomEtud.setText("");
        InputEmailEtud.setText("");
        InputTelEtud.setText("");
        InputDateNaissEtud.setValue(null);
        ComboSpecialiteEtud.setValue("--Choisir Spécialité--");
        ComboNiveauEtud.setValue(null);
        SuccessAjouterEtud.setText("");
    }

    //Ajouter Enseignant
    @FXML
    void AjouterEnseignantOp(ActionEvent event){
        boolean err = false;
        Enseignant ens = new Enseignant();
        InputIdEns.setStyle(InputIdEns.getStyle() + "-fx-text-fill: #000000;");
        InputNomEns.setStyle(InputIdEns.getStyle() + "-fx-text-fill: #000000;");
        InputPrenomEns.setStyle(InputIdEns.getStyle() + "-fx-text-fill: #000000;");
        InputEmailEns.setStyle(InputIdEns.getStyle() + "-fx-text-fill: #000000;");
        InputTelEns.setStyle(InputIdEns.getStyle() + "-fx-text-fill: #000000;");
        if(!cntrlId(InputIdEns.getText())) {
            err = true;
            InputIdEns.setStyle(InputIdEns.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if(!cntrlNom(InputNomEns.getText())){
            err = true;
            InputNomEns.setStyle(InputNomEns.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if(!cntrlNom(InputPrenomEns.getText())){
            err = true;
            InputPrenomEns.setStyle(InputPrenomEns.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if(!cntrlEmail(InputEmailEns.getText())){
            err = true;
            InputEmailEns.setStyle(InputEmailEns.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if(InputDateNaissEns.getValue() == null){
            err = true;
            InputDateNaissEns.setStyle(InputDateNaissEns.getStyle() + "-fx-text-fill: #ff0000;");
        }
        if(!cntrlId(InputTelEns.getText())){
            err = true;
            InputTelEns.setStyle(InputTelEns.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if(ComboDepartementEns.getValue() == "--Choisir Département--"){
            err = true;
        }
        if(!err){
            if(database.idExists(InputIdEns.getText(), "enseignants")){
                InputIdEns.setStyle(InputIdEns.getStyle() +"-fx-text-fill: #ff0000;");
                showAlert((Stage) ButAjouterEnsOp.getScene().getWindow() ,"Erreur d'ajout","Cet enseignant existe déja!");
            }
            else{
                ens.setId(Integer.parseInt(InputIdEns.getText()));
                ens.setNom(InputNomEns.getText());
                ens.setPrenom((InputPrenomEns.getText()));
                ens.setEmail(InputEmailEns.getText());
                ens.setDateNaissance(InputDateNaissEns.getValue());
                ens.setTel(Integer.parseInt(InputTelEns.getText()));
                String dep = String.valueOf(ComboDepartementEns.getValue());
                ens.setDepartement(dep);
                InputIdEns.setText("");
                InputNomEns.setText("");
                InputPrenomEns.setText("");
                InputEmailEns.setText("");
                InputDateNaissEns.setValue(null);
                InputTelEns.setText("");
                System.out.println("wa");
                ComboDepartementEns.setValue("--Choisir Département--");
                if (database.addEnseignantToDb(ens)) {
                    SuccessAjouterEns.setStyle("-fx-text-fill: #2b9e1c");
                    SuccessAjouterEns.setText("Enseignant ajouté avec succés.");
                } else {
                    showAlert((Stage) ButAjouterEnsOp.getScene().getWindow(), "Erreur", "Erreur dans l'ajout à la base de données!");
                }
            }
        }
        else{
            SuccessAjouterEns.setStyle("-fx-text-fill: #ff0000;");
            SuccessAjouterEns.setText("Vérifiez vos données.");
        }
    }
    @FXML
    void EffacerEnsBut() {
        InputIdEns.setStyle(InputIdEns.getStyle() + "-fx-text-fill: #000000;");
        InputNomEns.setStyle(InputNomEns.getStyle() + "-fx-text-fill: #000000;");
        InputPrenomEns.setStyle(InputPrenomEns.getStyle() + "-fx-text-fill: #000000;");
        InputEmailEns.setStyle(InputEmailEns.getStyle() + "-fx-text-fill: #000000;");
        InputTelEns.setStyle(InputTelEns.getStyle() + "-fx-text-fill: #000000;");
        InputIdEns.setText("");
        InputNomEns.setText("");
        InputPrenomEns.setText("");
        InputEmailEns.setText("");
        InputTelEns.setText("");
        InputDateNaissEns.setValue(null);
        ComboDepartementEns.setValue("--Choisir Département--");
        SuccessAjouterEns.setText("");
    }
    //Ajouter PFE
    @FXML
    void AjouterPFEOp(ActionEvent event){
        boolean err = false;
        PFE pfe = new PFE();
        InputHeurePFE.setStyle(InputHeurePFE.getStyle() + "-fx-text-fill: #000000;");
        Combo1erEtudPFE.setStyle(Combo1erEtudPFE.getStyle() + "-fx-text-fill: #000000;");
        Combo2emeEtudPFE.setStyle(Combo2emeEtudPFE.getStyle() + "-fx-text-fill: #000000;");
        ComboEncadreurPFE.setStyle(ComboEncadreurPFE.getStyle() + "-fx-text-fill: #000000;");
        InputDatePFE.setStyle(InputDatePFE.getStyle() + "-fx-text-fill: #000000;");
        if((Combo1erEtudPFE.getValue() == null) || !cntrlId(Combo1erEtudPFE.getValue())) {
            err = true;
            Combo1erEtudPFE.setStyle(Combo1erEtudPFE.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if((Combo2emeEtudPFE.getValue() == null) || !cntrlId(Combo2emeEtudPFE.getValue())){
            err = true;
            Combo2emeEtudPFE.setStyle(Combo2emeEtudPFE.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if((ComboEncadreurPFE.getValue() == null) ||!cntrlId(ComboEncadreurPFE.getValue())){
            err = true;
            ComboEncadreurPFE.setStyle(ComboEncadreurPFE.getStyle() +"-fx-text-fill: #ff0000;");
        }
        if(InputDatePFE.getValue() == null){
            err = true;
            InputDatePFE.setStyle(InputDatePFE.getStyle() + "-fx-text-fill: #ff0000;");
        }
        if(Combo1erEtudPFE.getValue() == null || Combo2emeEtudPFE.getValue() == null|| ComboEncadreurPFE.getValue() == null|| ComboSallePFE.getValue() == null){
            err = true;
        }
        String idpfe = InputDatePFE.getValue() + InputHeurePFE.getText() + ComboSallePFE.getValue();
        String cycleEtud1 = database.getCycle(Combo2emeEtudPFE.getValue());
        String cycleEtud2 = database.getCycle(Combo1erEtudPFE.getValue());
        if(!err){
            //Controle de l'existance des étudiants dans la base de données
            if(!database.idExists(Combo1erEtudPFE.getValue(), "etudiants")){
                showAlert((Stage) ButAjouterPFEOp.getScene().getWindow() ,"Erreur","L'étudiant 1 n'existe pas!");
            }
            else if(!database.idExists(Combo2emeEtudPFE.getValue(), "etudiants")){
                showAlert((Stage) ButAjouterPFEOp.getScene().getWindow() ,"Erreur","L'étudiant 2 n'existe pas!");
            }
            //Controle de l'existance de l'enseignant dans la base de données
            else if(!database.idExists(ComboEncadreurPFE.getValue(), "enseignants")){
                showAlert((Stage) ButAjouterPFEOp.getScene().getWindow() ,"Erreur","Cet enseignant n'existe pas!");
            }
            //Controle des cycles identiques pour les deux étudiants
            else if(!Objects.equals(cycleEtud1, cycleEtud2)){
                showAlert((Stage) ButAjouterPFEOp.getScene().getWindow() ,"Erreur","Les deux étudiants n'appartiennent pas au même cycle!");
            }
            //Controle s'il existe un pfe ayant la même planification
            else if(!database.idExists(idpfe, "pfe")){
                showAlert((Stage) ButAjouterPFEOp.getScene().getWindow() ,"Erreur","Il existe déja un pfe dans cette salle à cette heure!");
            }
            else{
                pfe.setId(Integer.parseInt(idpfe));
                pfe.setType(cycleEtud1);
                pfe.setSujet(InputSujetPFE.getText());
                pfe.setEtudiant1(Integer.parseInt(Combo1erEtudPFE.getValue()));
                pfe.setEtudiant2(Integer.parseInt(Combo2emeEtudPFE.getValue()));
                pfe.setEncadreurPedagogique(Integer.parseInt(ComboEncadreurPFE.getValue()));
                pfe.setSujet(String.valueOf(InputDatePFE.getValue()));
                pfe.setHeure(InputHeurePFE.getText());
                pfe.setSalle(ComboSallePFE.getValue());
                InputSujetPFE.setText("");
                Combo1erEtudPFE.setValue(null);
                Combo2emeEtudPFE.setValue(null);
                ComboEncadreurPFE.setValue(null);
                InputDatePFE.setValue(null);
                InputHeurePFE.setText("");
                ComboSallePFE.setValue("--Choisir Salle--");
                ComboNiveauEtud.setValue(null);
                if (database.addPFEToDb(pfe)) {
                    SuccessAjouterPFE.setStyle("-fx-text-fill: #2b9e1c");
                    SuccessAjouterPFE.setText("Projet ajouté avec succés.");
                } else {
                    showAlert((Stage) ButAjouterPFEOp.getScene().getWindow(), "Erreur", "Erreur dans l'ajout à la base de données!");
                }
            }
        }
        else{
            SuccessAjouterPFE.setStyle("-fx-text-fill: #ff0000;");
            SuccessAjouterPFE.setText("Vérifiez vos données.");
        }
    }
    @FXML
    void EffacerPFEBut(){
        InputSujetPFE.setStyle(InputSujetPFE.getStyle() +"-fx-text-fill: #000000;");
        Combo1erEtudPFE.setStyle(Combo1erEtudPFE.getStyle() +"-fx-text-fill: #000000;");
        Combo2emeEtudPFE.setStyle(Combo2emeEtudPFE.getStyle() +"-fx-text-fill: #000000;");
        ComboEncadreurPFE.setStyle(ComboEncadreurPFE.getStyle() +"-fx-text-fill: #000000;");
        InputDatePFE.setStyle(InputDatePFE.getStyle() +"-fx-text-fill: #000000;");
        InputHeurePFE.setStyle(InputHeurePFE.getStyle() +"-fx-text-fill: #000000;");
        ComboSallePFE.setStyle(InputHeurePFE.getStyle() +"-fx-text-fill: #000000;");
        InputSujetPFE.setText("");
        Combo1erEtudPFE.setValue(null);
        Combo2emeEtudPFE.setValue(null);
        ComboEncadreurPFE.setValue(null);
        InputDatePFE.setValue(null);
        InputHeurePFE.setText("");
        ComboSallePFE.setValue("--Choisir Salle--");
        SuccessAjouterPFE.setText("");
    }

}
