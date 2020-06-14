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

package de.crasheddevelopment.csgofontmanager;

import de.crasheddevelopment.csgofontmanager.core.fontmanager.BackupManager;
import de.crasheddevelopment.csgofontmanager.core.fontmanager.FontManager;
import de.crasheddevelopment.csgofontmanager.core.system.network.updater.ApplicationUpdater;
import de.crasheddevelopment.csgofontmanager.core.system.ui.fx.applications.FXMenu;
import de.crasheddevelopment.csgofontmanager.utils.Constants;
import de.crasheddevelopment.csgofontmanager.utils.StringUtils;
import javafx.application.Application;

public class CSGOFontManager
{
    public static final BackupManager BACKUP_MANAGER = new BackupManager();
    public static final FontManager FONT_MANAGER = new FontManager();

    public static void main (String[] args)
    {
        new CSGOFontManager().initializeFontManager(args);
    }

    private void initializeFontManager (String[] args)
    {
        System.out.println("\n" +
                "   ___ ___  ___  ___  ___        _   __  __                             \n" +
                "  / __/ __|/ __|/ _ \\| __|__ _ _| |_|  \\/  |__ _ _ _  __ _ __ _ ___ _ _ \n" +
                " | (__\\__ \\ (_ | (_) | _/ _ \\ ' \\  _| |\\/| / _` | ' \\/ _` / _` / -_) '_|\n" +
                "  \\___|___/\\___|\\___/|_|\\___/_||_\\__|_|  |_\\__,_|_||_\\__,_\\__, \\___|_|  \n" +
                "                                                          |___/         \n");
        StringUtils.sendInformation("Copyright © 2020 CrashedDevelopment");
        StringUtils.sendInformation("This is a non-commercial project.");
        StringUtils.sendInformation("All rights belong to their respective owners.");
        StringUtils.sendEmptyLine();
        StringUtils.sendInformation("Version: " + Constants.VERSION);
        StringUtils.sendInformation("Build version: " + Constants.BUILD);
        StringUtils.sendEmptyLine();
        StringUtils.sendInformation("Searching application update...");

        if (this.searchUpdate())
        {
            StringUtils.sendInformation("Application update found!");
            StringUtils.sendInformation("Please download the update under:");
            StringUtils.sendInformation("https://www.github.com/crasheddevelopment/CSGOFontManager/releases/latest/");
        }
        else
        {
            StringUtils.sendInformation("No application update found!");
        }

        StringUtils.sendInformation("Starting Font Manager...");
        Application.launch(FXMenu.class, args);
    }

    private boolean searchUpdate ()
    {
        return ApplicationUpdater.searchUpdate();
    }
}