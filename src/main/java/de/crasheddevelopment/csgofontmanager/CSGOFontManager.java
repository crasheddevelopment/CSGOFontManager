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

package de.crasheddevelopment.csgofontmanager;

import de.crasheddevelopment.csgofontmanager.core.network.updater.ApplicationUpdater;
import de.crasheddevelopment.csgofontmanager.core.ui.fx.applications.FXDisclaimer;
import de.crasheddevelopment.csgofontmanager.utils.StringUtils;
import javafx.application.Application;

public class CSGOFontManager
{
    public static void main (String[] args)
    {
        System.out.println("\n" +
                "   ___ ___  ___  ___  ___        _   __  __                             \n" +
                "  / __/ __|/ __|/ _ \\| __|__ _ _| |_|  \\/  |__ _ _ _  __ _ __ _ ___ _ _ \n" +
                " | (__\\__ \\ (_ | (_) | _/ _ \\ ' \\  _| |\\/| / _` | ' \\/ _` / _` / -_) '_|\n" +
                "  \\___|___/\\___|\\___/|_|\\___/_||_\\__|_|  |_\\__,_|_||_\\__,_\\__, \\___|_|  \n" +
                "                                                          |___/         \n");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        StringUtils.sendInformation("Copyright © 2019 - 2020 CrashedDevelopment");
        StringUtils.sendInformation("This is a non-commercial project.");
        StringUtils.sendInformation("All rights belong to their respective owners.");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        StringUtils.sendInformation("Version: " + StringUtils.VERSION);
        StringUtils.sendInformation("Build version: " + StringUtils.BUILD);
        System.out.println(" ");
        StringUtils.sendInformation("Searching application update...");
        searchUpdate();
        System.out.println(" ");
        StringUtils.sendInformation("FONT MANAGER : Starting Font Manager...");
        Application.launch(FXDisclaimer.class, args);
    }

    private static void searchUpdate ()
    {
        ApplicationUpdater.searchUpdate();
    }
}