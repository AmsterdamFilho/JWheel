package br.com.jwheel.utils;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class UnitConverter
{
    private UnitConverter ()
    {
    }

    /**
     * Converts a measure in cm to pixels, according to the informed resolution
     *
     * @param cm the measure to be converted to pixels
     * @param dpi the resolution available
     * @return the measure in pixels
     */
    public static long convertToPixels (float cm, int dpi)
    {
        double dotsPerCm = dpi / 2.54;
        // casting is better than rounding
        return (long) (cm * dotsPerCm);
    }
}
