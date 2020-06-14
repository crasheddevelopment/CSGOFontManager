/*
 * Copyright © 2019 - 2020 CrashedDevelopment
 *
 * This is a non-commercial project.
 * All rights belong to their respective owners.
 * You are allowed to change the software as long as no illegal content is created and / or distributed.
 * All rights and information about the used libraries in the LICENSE_APACHE_2.0 / LIBRARIES and at the point "Libraries" and "Used Libraries" here.
 * The license from the project contains in the file LICENSE_PROJECT!
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
 * Development Team:
 *
 * CrashedLife | Sören (Developed everything.):
 * Discord: CrashedLife#9417
 * Twitter: https://twitter.com/sqliCrashedLife
 * Github: https://github.com/CrashedLife
 * YouTube: https://www.youtube.com/channel/UC3alimqGC2YcgEK7jc_N4mg
 *
 * Luca (Staff):
 * Discord: Luca.#1337
 * Twitter: https://twitter.com/luca1337_
 *
 * Libraries:
 * We used libraries to complete our project and respect and thanks every single owner that created the library.
 * If some owner has problems with it please contact us.
 *
 * Used Libraries:
 * JFoeniX © jfoenixadmin
 * Github: https://github.com/jfoenixadmin/
 * JFoeniX: https://github.com/jfoenixadmin/JFoenix
 * License: Apache 2.0 (Under LICENSE_APACHE_2.0)
 *
 * Apache Commons IO © Apache
 * Website: https://commons.apache.org/proper/commons-io/download_io.cgi
 * License: Apache 2.0 (Under LICENSE_APACHE_2.0)
 */

package de.crasheddevelopment.csgofontmanager.core.ui.fx.controllers;

import com.jfoenix.controls.JFXTextField;
import de.crasheddevelopment.csgofontmanager.core.managers.BackupManager;
import de.crasheddevelopment.csgofontmanager.core.managers.FontManager;
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
    @FXML private JFXTextField fontFileTextField;
    @FXML private JFXTextField fontNameTextField;
    @FXML private JFXTextField csgoPathTextField;

    private Alert alert = new Alert(Alert.AlertType.ERROR);
    private final BackupManager backupManager = new BackupManager();
    private final FontManager fontManager = new FontManager();
    private String backupFolderPath = null;

    // Changes the font.
    @FXML
    private void changeFont ()
    {
        // Check the requirements.
        if (!fontFileTextField.getText().isEmpty())
        {
            if (!fontNameTextField.getText().isEmpty())
            {
                if (!csgoPathTextField.getText().isEmpty())
                {
                    if (Files.exists(Paths.get(csgoPathTextField.getText().trim() + "\\panorama\\fonts\\")))
                    {
                        if (backupManager.createBackup(csgoPathTextField.getText().trim()))
                        {
                            if (fontManager.changeFont(Paths.get(fontFileTextField.getText().trim()), fontNameTextField.getText().trim(), Paths.get(csgoPathTextField.getText().trim() + "\\panorama\\fonts\\")))
                            {
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success!");
                                alert.setContentText("Font changed successfully!");
                            }
                            else
                            {
                                alert.setAlertType(Alert.AlertType.ERROR);
                                alert.setTitle("Error!");
                                alert.setContentText("Font cannot change!");
                            }
                        }
                        else
                        {
                            alert.setAlertType(Alert.AlertType.ERROR);
                            alert.setTitle("Error!");
                            alert.setContentText("Backup could not create!");
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
                    alert.setContentText("Please select the csgo folder!");
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
        if (!csgoPathTextField.getText().isEmpty())
        {
            if (backupFolderPath != null)
            {
                if (backupManager.restoreBackup(csgoPathTextField.getText().trim(), backupFolderPath))
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
        final Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Initialize file chooser.
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select font file");

        // Shows file chooser.
        final File file = fileChooser.showOpenDialog(stage);

        if (file != null)
        {
            fontFileTextField.setText(file.getPath().trim());
        }
    }

    // Select the csgo folder.
    @FXML
    private void browseCSGOFolder (ActionEvent actionEvent)
    {
        // Initialize the variables.
        final Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Initialize file chooser.
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select csgo folder");

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
        final Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Initialize file chooser.
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select backup folder");

        // Shows file chooser.
        final File file = directoryChooser.showDialog(stage);

        // Checks that the file is not null.
        if (file != null)
        {
            this.backupFolderPath = file.getPath().trim();
            restoreBackup();
        }
    }
}