/*
 Copyright (c) 2018-2020, Stephen Gold
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 * Neither the name of the copyright holder nor the names of its contributors
 may be used to endorse or promote products derived from this software without
 specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package jme3utilities.debug.textures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jme3utilities.Heart;
import jme3utilities.MyString;

/**
 * Console application to generate the texture "square.png".
 *
 * @author Stephen Gold sgold@sonic.net
 */
public class MakeSquare {
    // *************************************************************************
    // constants and loggers

    /**
     * size of the texture map (pixels per side)
     */
    final private static int textureSize = 64;
    /**
     * message logger for this class
     */
    final private static Logger logger
            = Logger.getLogger(MakeSquare.class.getName());
    /**
     * filesystem path to the asset directory/folder for output
     */
    final private static String assetDirPath
            = "../HeartLibrary/src/main/resources";
    // *************************************************************************
    // new methods exposed

    /**
     * Main entry point for the MakeSquare application.
     *
     * @param arguments array of command-line arguments (not null)
     */
    public static void main(String[] arguments) {
        /*
         * Mute the chatty loggers found in some imported packages.
         */
        Heart.setLoggingLevels(Level.WARNING);
        /*
         * Set the logging level for this class and also for writeImage().
         */
        //logger.setLevel(Level.INFO);
        //Logger.getLogger(Heart.class.getName()).setLevel(Level.INFO);
        /*
         * Instantiate the application.
         */
        MakeSquare application = new MakeSquare();
        /*
         * Log the working directory.
         */
        String userDir = System.getProperty("user.dir");
        logger.log(Level.INFO, "working directory is {0}",
                MyString.quote(userDir));
        /*
         * Generate color image map.
         */
        application.makeSquare();
    }
    // *************************************************************************
    // private methods

    /**
     * Generate an image map for a (voided) square.
     */
    private void makeSquare() {
        /*
         * Create a blank, color buffered image for the texture map.
         */
        BufferedImage image = new BufferedImage(textureSize, textureSize,
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = image.createGraphics();

        float brightness = 1f;
        float opacity = 1f;
        Color white = new Color(brightness, brightness, brightness, opacity);
        graphics.setColor(white);

        int[] xPoints = {0, 63, 63, 52, 52, 11};
        int[] yPoints = {0, 0, 63, 52, 11, 11};
        int numPoints = xPoints.length;
        graphics.fillPolygon(xPoints, yPoints, numPoints);

        int[] xPoints2 = {0, 0, 63, 52, 11, 11};
        int[] yPoints2 = {0, 63, 63, 52, 52, 11};
        int numPoints2 = xPoints2.length;
        graphics.fillPolygon(xPoints2, yPoints2, numPoints2);
        /*
         * Write the image to the asset file.
         */
        String assetPath = "Textures/shapes/square.png";
        String filePath = String.format("%s/%s", assetDirPath, assetPath);
        try {
            Heart.writeImage(filePath, image);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
