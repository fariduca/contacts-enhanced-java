package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.awt.event.TextListener;
import java.util.Comparator;

public class ContactsController {

    @FXML private ListView<Contact> listView;

    private final ObservableList<Contact> contacts =
            FXCollections.observableArrayList();

    @FXML
    private TextField firstTextField;

    @FXML
    private TextField lastTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Button removeButton;

    @FXML
    private Button saveButton;

    public void initialize() {
        contacts.add(new Contact("Bob", "Marley", "bob@m.com", "12345"));
        listView.setItems(contacts);

        initializeTextFields();

        listView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Contact>() {
                    @Override
                    public void changed(ObservableValue<? extends Contact> observableValue,
                                        Contact contact, Contact t1) {
                        if (t1 == null) {
                            initializeTextFields();
                        }
                        else {
                            firstTextField.setDisable(false);
                            firstTextField.setEditable(true);
                            lastTextField.setDisable(false);
                            lastTextField.setEditable(true);
                            emailTextField.setDisable(false);
                            emailTextField.setEditable(true);
                            phoneTextField.setDisable(false);
                            phoneTextField.setEditable(true);
                            removeButton.setDisable(false);
                            saveButton.setDisable(false);

                            firstTextField.setText(t1.getFirstName());
                            lastTextField.setText(t1.getLastName());
                            emailTextField.setText(t1.getEmail());
                            phoneTextField.setText(t1.getPhoneNumber());
                        }
                    }
                });

        firstTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String s, String t1) {
                if (listView.getSelectionModel().getSelectedIndex() != -1) {
                    Contact c = contacts.get(listView.getSelectionModel().getSelectedIndex());
                    c.setFirstName(t1);
                }
            }
        });
        lastTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String s, String t1) {
                if (listView.getSelectionModel().getSelectedIndex() != -1) {
                    Contact c = contacts.get(listView.getSelectionModel().getSelectedIndex());
                    c.setLastName(t1);
                }
            }
        });
        emailTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String s, String t1) {
                if (listView.getSelectionModel().getSelectedIndex() != -1) {
                    Contact c = contacts.get(listView.getSelectionModel().getSelectedIndex());
                    c.setEmail(t1);
                }
            }
        });
        phoneTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String s, String t1) {
                if (listView.getSelectionModel().getSelectedIndex() != -1) {
                    Contact c = contacts.get(listView.getSelectionModel().getSelectedIndex());
                    c.setPhoneNumber(t1);
                }
            }
        });

        listView.setCellFactory(new Callback<ListView<Contact>, ListCell<Contact>>() {
            @Override
            public ListCell<Contact> call(ListView<Contact> contactListView) {
                return new ImageTextCell();
            }
        });
    }

    private void initializeTextFields() {
        firstTextField.clear();
        lastTextField.clear();
        emailTextField.clear();
        phoneTextField.clear();
        firstTextField.setEditable(false);
        firstTextField.setDisable(true);
        lastTextField.setEditable(false);
        lastTextField.setDisable(true);
        emailTextField.setEditable(false);
        emailTextField.setDisable(true);
        phoneTextField.setEditable(false);
        phoneTextField.setDisable(true);
        removeButton.setDisable(true);
        saveButton.setDisable(true);
    }

    @FXML
    void addContactPressed(ActionEvent event) {
        Contact c = new Contact("", "New", "", "");
        contacts.add(c);
        contacts.sort(new MyComparator());
        listView.setItems(contacts);
        listView.getSelectionModel().select(c);
    }

    @FXML
    void removeContactPressed(ActionEvent event) {
        if (listView.getSelectionModel().getSelectedIndex() != -1) {
            contacts.remove(listView.getSelectionModel().getSelectedIndex());
            contacts.sort(new MyComparator());
            listView.setItems(contacts);
        }
    }

    @FXML
    void saveButtonPressed(ActionEvent event) {
        contacts.sort(new MyComparator());
        listView.setItems(contacts);
    }

    private static class MyComparator implements Comparator<Contact> {

        @Override
        public int compare(Contact o1, Contact o2) {
            return o1.getLastName().compareToIgnoreCase(o2.getLastName());
        }
    }

}
