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

package de.crasheddevelopment.csgofontmanager.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants
{
    // Version string.
    public static final String VERSION = "1.1.0";

    // Build string.
    public static final String BUILD = "2";

    // Backup folder path.
    public static Path getBackupFolder ()
    {
        return Paths.get(System.getProperty("user.home") + "\\CrashedDevelopment\\CSGOFontManager\\backup\\");
    }
}