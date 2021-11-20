/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lib;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class PSNR {

    // number of bits used to store the mantissa in the 64-bit IEEE-754 standard
    public static final int DOUBLE_MANTISSA_BITS = 52;
    // number of bits used to store the mantissa in the 32-bit IEEE-754 standard
    public static final int FLOAT_MANTISSA_BITS = 23;

    public static final byte POSITIVE = 0;
    public static final byte NEGATIVE = 1;

    public static final int BLACK = 0;
    public static final int WHITE = 1;

    public static final int INSIGNIFICANT = 0;
    public static final int SIGNIFICANT = 1;

    public static long unsigned(long l) {
        long x = l;
        x <<= 32;
        x >>>= 32;
        return x;
    }

    /**
     * Computes and prints out the PSNR of two images (which must have the same
     * dimensions and type).
     */
    public static double printPSNR(BufferedImage im1, BufferedImage im2) {
        assert (im1.getType() == im2.getType()
                && im1.getHeight() == im2.getHeight()
                && im1.getWidth() == im2.getWidth());

        double mse = 0;
        int width = im1.getWidth();
        int height = im1.getHeight();
        Raster r1 = im1.getRaster();
        Raster r2 = im2.getRaster();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                mse += Math.pow(r1.getSample(i, j, 0) - r2.getSample(i, j, 0), 2);
            }
        }
        mse /= (double) (width * height);
        int maxVal = 255;
        double x = Math.pow(maxVal, 2) / mse;
        double psnr = 10.0 * logbase10(x);
        
        return psnr;
    }

    /**
     * Returns the base-10 logarithm of a number
     */
    public static double logbase10(double x) {
        return Math.log(x) / Math.log(10);
    }
}
