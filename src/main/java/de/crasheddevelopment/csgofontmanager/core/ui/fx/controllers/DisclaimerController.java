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

import com.jfoenix.controls.JFXButton;
import de.crasheddevelopment.csgofontmanager.core.ui.fx.applications.FXMenu;
import de.crasheddevelopment.csgofontmanager.utils.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class DisclaimerController
{
    // Initialize variables.
    @FXML private JFXButton continueButton;
    private boolean disable = true;

    // Method if the checkbox checked.
    @FXML private void runAtAccept ()
    {
        disable = !disable;
        continueButton.setDisable(disable);
    }

    // Opens the menu.
    @FXML private void continueApplication (ActionEvent actionEvent)
    {
        // Initialize the variables.
        final Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        try
        {
            // Opens the menu.
            new FXMenu().initGUI(stage);
        }
        catch (IOException ioException)
        {
            StringUtils.sendInformation("IOException!");
            ioException.printStackTrace();
        }
    }
}