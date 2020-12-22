package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageTextCell extends ListCell<Contact> {
    private HBox hBox = new HBox(8.0);
    private ImageView thumbImageView = new ImageView();
    private Label fullNameLabel = new Label();

    public ImageTextCell() {
        hBox.setAlignment(Pos.CENTER_LEFT);

        thumbImageView.setPreserveRatio(true);
        thumbImageView.setFitHeight(50.0);
        hBox.getChildren().add(thumbImageView);

        fullNameLabel.setWrapText(true);
        fullNameLabel.setTextAlignment(TextAlignment.CENTER);
        hBox.getChildren().add(fullNameLabel);

        setPrefWidth(USE_PREF_SIZE);
    }

    @Override
    protected void updateItem(Contact contact, boolean empty) {
        super.updateItem(contact, empty);

        if (empty || contact == null){
            setGraphic(null);
        }
        else {
            try {
                thumbImageView.setImage(new Image(new FileInputStream(contact.getThumbImage())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            fullNameLabel.setText(
                    contact.getLastName()
                    .concat(" ")
                    .concat(contact.getFirstName())
            );
            setGraphic(hBox);
        }
    }
}
