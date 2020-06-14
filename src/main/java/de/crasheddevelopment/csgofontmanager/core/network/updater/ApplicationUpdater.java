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

package de.crasheddevelopment.csgofontmanager.core.network.updater;

import de.crasheddevelopment.csgofontmanager.utils.StringUtils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class ApplicationUpdater
{
    public static void searchUpdate ()
    {
        try
        {
            // Initialize variables.
            // Connect to the URL.
            final URL url = new URL("https://api.github.com/repos/CrashedDevelopment/CSGOFontManager/releases/latest");
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            final StringBuilder stringBuilder = new StringBuilder();
            String source;

            // Read website content.
            while ((source = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(source);
            }

            // Splits website content.
            final String[] source1 = stringBuilder.toString().split("\"browser_download_url\":");
            final String[] source2 = source1[1].split("}],\"tarball_url\":");
            final String source3 = source2[0].replace("\"", "");

            // Checks if the latest version not contains with the CSGOFontManager version.
            if (!source3.contains(StringUtils.VERSION))
            {
                // Sends output.
                StringUtils.sendInformation("Update found!");
                StringUtils.sendInformation("Download update...");
                downloadZIP();
            }

            // Closes buffer reader.
            bufferedReader.close();
        }
        catch (IOException ioException)
        {
            // Prints the exception.
            StringUtils.sendInformation("EXCEPTION : IOException while searching new updates for this application!");
            ioException.printStackTrace();
        }
    }

    // Download ZIP method.
    private static void downloadZIP ()
    {
        try
        {
            // Initialize variables.
            // Connect to the URL.
            final URL url = new URL("https://api.github.com/repos/CrashedDevelopment/CSGOFontManager/releases/latest");
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            final StringBuilder stringBuilder = new StringBuilder();
            String source;

            // Read website content.
            while ((source = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(source);
            }

            // Splits website content.
            final String[] source1 = stringBuilder.toString().split("\"browser_download_url\":");
            final String[] source2 = source1[1].split("}],\"tarball_url\":");

            // Initialize variables.
            // Opens a new URL to download the file.
            final URL url2 = new URL(source2[0].replace("\"", ""));
            final ReadableByteChannel readableByteChannel = Channels.newChannel(url2.openStream());
            final FileOutputStream fileOutputStream = new FileOutputStream("JPGFileInjector.zip");

            // Downloads the file.
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

            // Sends output.
            StringUtils.sendInformation("File successfully downloaded!");

            // Close method.
            readableByteChannel.close();
            fileOutputStream.close();
            bufferedReader.close();
        }
        catch (IOException ioException)
        {
            // Print exception.
            StringUtils.sendInformation("EXCEPTION : IOException while downloading zip!");
            ioException.printStackTrace();
        }
    }
}