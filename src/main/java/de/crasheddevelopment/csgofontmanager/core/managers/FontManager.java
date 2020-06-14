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

package de.crasheddevelopment.csgofontmanager.core.managers;

import de.crasheddevelopment.csgofontmanager.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FontManager
{
    // Changes the font.
    public boolean changeFont (Path fontFilePath, String fontName, Path fontsFolderPath)
    {
        try
        {
            // Initialize variables.
            final String fontFileName = fontFilePath.getFileName().toString().trim();
            final BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fontsFolderPath + "\\fonts.conf\\"));

            int lineNumber = 0;
            String line = bufferedReader.readLine();
            StringBuilder fileContent = null;

            // Checks if the line is not null
            while (line != null)
            {
                lineNumber ++;

                if (lineNumber == 39)
                {
                    line = "\t<fontpattern>." + FilenameUtils.getExtension(fontFilePath.toString()) + "</fontpattern>";
                }

                if (lineNumber == 40)
                {
                    line = "\t<fontpattern>" + fontFileName + "</fontpattern>";
                }

                if ((lineNumber != 41) && (lineNumber != 42) && (lineNumber != 253))
                {
                    if (fileContent == null)
                    {
                        fileContent = new StringBuilder(line + "\n");
                    }
                    else
                    {
                        fileContent.append(line).append("\n");
                    }
                }

                line = bufferedReader.readLine();
            }

            fileContent.append("\t<match>\n" +
                    "\t\t<test name=\"family\">\n" +
                    "\t\t\t<string>Stratum2</string>\n" +
                    "\t\t</test>\n" +
                    "\t\t<edit name=\"family\" mode=\"append\" binding=\"strong\">\n" +
                    "\t\t\t<string>" + fontName + "</string>\n" +
                    "\t\t</edit>\n" +
                    "\t</match>\n" +
                    "\t\n" +
                    "\t<match>\n" +
                    "\t\t<test name=\"family\">\n" +
                    "\t\t\t<string>Stratum2 Bold</string>\n" +
                    "\t\t</test>\n" +
                    "\t\t<edit name=\"family\" mode=\"append\" binding=\"strong\">\n" +
                    "\t\t\t<string>" + fontName + "</string>\n" +
                    "\t\t</edit>\n" +
                    "\t</match>\n" +
                    "\t\n" +
                    "\t<match>\n" +
                    "\t\t<test name=\"family\">\n" +
                    "\t\t\t<string>Arial</string>\n" +
                    "\t\t</test>\n" +
                    "\t\t<edit name=\"family\" mode=\"append\" binding=\"strong\">\n" +
                    "\t\t\t<string>" + fontName + "</string>\n" +
                    "\t\t</edit>\n" +
                    "\t</match>\n" +
                    "\t\n" +
                    "\t<match>\n" +
                    "\t\t<test name=\"family\">\n" +
                    "\t\t\t<string>Times New Roman</string>\n" +
                    "\t\t</test>\n" +
                    "\t\t<edit name=\"family\" mode=\"append\" binding=\"strong\">\n" +
                    "\t\t\t<string>" + fontName + "</string>\n" +
                    "\t\t</edit>\n" +
                    "\t</match>\n" +
                    "\t\n" +
                    "\t<match>\n" +
                    "\t\t<test name=\"family\">\n" +
                    "\t\t\t<string>Courier New</string>\n" +
                    "\t\t</test>\n" +
                    "\t\t<edit name=\"family\" mode=\"append\" binding=\"strong\">\n" +
                    "\t\t\t<string>" + fontName + "</string>\n" +
                    "\t\t</edit>\n" +
                    "\t</match>\n" +
                    "\t<match>\n" +
                    "\t\t<test name=\"family\">\n" +
                    "\t\t\t<string>notosans</string>\n" +
                    "\t\t</test>\n" +
                    "\t\t<edit name=\"family\" mode=\"append\" binding=\"strong\">\n" +
                    "\t\t\t<string>" + fontName + "</string>\n" +
                    "\t\t</edit>\n" +
                    "\t</match>\n" +
                    "</fontconfig>");

            bufferedReader.close();

            // Initialize variable.
            final BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(fontsFolderPath + "\\fonts.conf\\"));

            // Writes to the file.
            bufferedWriter.write(String.valueOf(fileContent));
            bufferedWriter.flush();
            bufferedWriter.close();

            // Copy the font file to the directory.
            FileUtils.copyFileToDirectory(fontFilePath.toFile(), fontsFolderPath.toFile());
            return true;
        }
        catch (IOException ioException)
        {
            StringUtils.sendInformation("EXCEPTION : IOException while changing font!");
            ioException.printStackTrace();
            return false;
        }
    }
}