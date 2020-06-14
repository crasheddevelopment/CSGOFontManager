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

package de.crasheddevelopment.csgofontmanager.core.fontmanager;

import de.crasheddevelopment.csgofontmanager.utils.Constants;
import de.crasheddevelopment.csgofontmanager.utils.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BackupManager
{
    // Create a backup from the fonts folder.
    public boolean createBackup (String csgoPath)
    {
        // Check if the directory exists.
        if (!Files.exists(Constants.getBackupFolder()))
        {
            try
            {
                // Creates directory.
                Files.createDirectories(Constants.getBackupFolder());
            }
            catch (IOException ioException)
            {
                StringUtils.sendInformation("IOException while creating backup folder directories!");
                ioException.printStackTrace();
                return false;
            }
        }

        // Initialize variables.
        File fontsFolder = Paths.get(csgoPath + "\\panorama\\fonts\\").toFile();
        File backupFolder = Paths.get(Constants.getBackupFolder() + "\\" + StringUtils.getTime() + "\\").toFile();

        // Check if the fonts folder is a directory.
        if (fontsFolder.isDirectory())
        {
            try
            {
                // Copy content from the fonts folder to the backup folder.
                FileUtils.copyDirectory(fontsFolder, backupFolder);
                return true;
            }
            catch (IOException ioException)
            {
                StringUtils.sendInformation("IOException while copying " + fontsFolder + " to " + backupFolder);
                ioException.printStackTrace();
                return false;
            }
        }
        return false;
    }

    // Restore the backup.
    public boolean restoreBackup (String csgoPath, String backupFolderPath)
    {
        // Initialize variables.
        File fontsFolder = Paths.get(csgoPath + "\\panorama\\fonts\\").toFile();
        File backupFolder = Paths.get(backupFolderPath).toFile();

        // Check if fonts folder is a directory.
        if (fontsFolder.isDirectory())
        {
            // Check if the backup folder is a directory.
            if (backupFolder.isDirectory())
            {
               try
               {
                   // Deletes the content from the fonts folder.
                   FileUtils.deleteDirectory(fontsFolder);

                   // Copy the content from the backup folder to the fonts folder.
                   FileUtils.copyDirectory(backupFolder, fontsFolder);
                   return true;
               }
               catch (IOException ioException)
               {
                   StringUtils.sendInformation("IOException while copying " + backupFolder + " to " + fontsFolder);
                   ioException.printStackTrace();
                   return false;
               }
           }
        }
        return false;
    }
}