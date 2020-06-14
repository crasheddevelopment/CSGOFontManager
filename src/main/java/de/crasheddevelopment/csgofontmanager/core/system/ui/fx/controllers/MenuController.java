/*
 * Copyright © 2019 - 2020 CrashedDevelopment
 *
 * This is a non-commercial project.
 * All rights belong to their respective owners.
 * You are allowed to change the software as long as no illegal content is created and / or distributed.
 * All rights and information about the used libraries in the LICENSE_APACHE_2.0.txt / LIBRARIES.txt and at the point "Libraries" and "Used Libraries" here.
 * The license from the project contains in the file LICENSE_PROJECT.txt!
 *
 * You are allowed to use following changes at this software:
 * - Creates new features
 * - Modify source code. (Without the identification of "// Do not remove this.")
 * - Publish your changes. (non-commercial)
 * - Send changes to us to improve or add new features.
 *
 * You are not allowed to do:
 * - Publish the software for a commercial use without our permission.
 * - Infect users with viruses or to do any other illegal content.
 *
 * For news, updates and support:
 * https://discord.gg/CBkXXKa
 *
 * Development:
 *
 * CrashedLife | Sören:
 * Discord: CrashedLife#9417
 * Twitter: https://twitter.com/sqliCrashedLife
 * Github: https://github.com/CrashedLife
 * YouTube: https://www.youtube.com/channel/UC3alimqGC2YcgEK7jc_N4mg
 *
 * Libraries:
 * We used libraries to complete our project and respect and thanks every single owner that created the library.
 * If some owner has problems with it please contact us.
 */

package de.crasheddevelopment.csgofontmanager.core.system.ui.fx.controllers;

import com.jfoenix.controls.JFXTextField;
import de.crasheddevelopment.csgofontmanager.CSGOFontManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MenuController
{
    // Initialize variables.
    @FXML private JFXTextField fontFilePathTextField;
    @FXML private JFXTextField fontNameTextField;
    @FXML private JFXTextField csgoPathTextField;

    private final Alert alert = new Alert(Alert.AlertType.ERROR);
    private String backupFolderPath = null;

    // Changes the font.
    @FXML
    private void changeFont ()
    {
        // Check the requirements.
        if (!this.fontFilePathTextField.getText().isEmpty())
        {
            if (!fontNameTextField.getText().isEmpty())
            {
                if (!this.csgoPathTextField.getText().isEmpty())
                {
                    if (Files.exists(Paths.get(this.csgoPathTextField.getText().trim() + "\\panorama\\fonts\\")))
                    {
                        if (CSGOFontManager.BACKUP_MANAGER.createBackup(this.csgoPathTextField.getText().trim()))
                        {
                            if (CSGOFontManager.FONT_MANAGER.changeFont(Paths.get(this.fontFilePathTextField.getText().trim()), this.fontNameTextField.getText().trim(), Paths.get(this.csgoPathTextField.getText().trim() + "\\panorama\\fonts\\")))
                            {
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success!");
                                alert.setContentText("Font changed successfully!");
                            }
                            else
                            {
                                alert.setAlertType(Alert.AlertType.ERROR);
                                alert.setTitle("Error!");
                                alert.setContentText("Font cannot be changed!");
                            }
                        }
                        else
                        {
                            alert.setAlertType(Alert.AlertType.ERROR);
                            alert.setTitle("Error!");
                            alert.setContentText("Backup could not be created!");
                        }
                    }
                    else
                    {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setTitle("Error!");
                        alert.setContentText("Fonts folder not found!");
                    }
                }
                else
                {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setContentText("Please select the CS:GO folder!");
                }
            }
            else
            {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter the font name!");
            }
        }
        else
        {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please select a font file!");
        }
        alert.show();
    }

    // Restore the backup.
    private void restoreBackup ()
    {
        if (!this.csgoPathTextField.getText().isEmpty())
        {
            if (this.backupFolderPath != null)
            {
                if (CSGOFontManager.BACKUP_MANAGER.restoreBackup(this.csgoPathTextField.getText().trim(), this.backupFolderPath))
                {
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setContentText("Backup restored successfully!");
                }
                else
                {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setContentText("Restoring backup failed!");
                }
            }
            else
            {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("You need to select a backup folder!");
            }
        }
        else
        {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please select the csgo folder!");
        }
        alert.show();
    }

    // Select a font file.
    @FXML
    private void browseFontFile (ActionEvent actionEvent)
    {
        // Initialize the variables.
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Initialize file chooser.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the font file!");

        // Shows file chooser.
        File file = fileChooser.showOpenDialog(stage);

        if (file != null)
        {
            this.fontFilePathTextField.setText(file.getPath().trim());
        }
    }

    // Select the csgo folder.
    @FXML
    private void browseCSGOFolder (ActionEvent actionEvent)
    {
        // Initialize the variables.
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Initialize file chooser.
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the CS:GO folder!");

        // Shows file chooser.
        final File file = directoryChooser.showDialog(stage);

        if (file != null)
        {
            csgoPathTextField.setText(file.getPath().trim());
        }
    }

    // Select the backup folder.
    @FXML
    private void browseBackupFolder (ActionEvent actionEvent)
    {
        // Initialize the variables.
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Initialize file chooser.
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the backup folder!");

        // Shows file chooser.
        File file = directoryChooser.showDialog(stage);

        // Checks that the file is not null.
        if (file != null)
        {
            this.backupFolderPath = file.getPath().trim();
            restoreBackup();
        }
    }
}