package br.com.luvva.jwheel.model;

import br.com.luvva.jwheel.core.model.UnitConverter;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class UnitConverterTest
{
    @Test
    public void convertToPixels ()
    {
        // expected values are from http://pixelyzer.com/inches_to_pixels.html
        Assert.assertEquals(23622, UnitConverter.convertToPixels(100, 600), 0.001);
        Assert.assertEquals(2952, UnitConverter.convertToPixels(100, 75), 0.001);
        Assert.assertEquals(295, UnitConverter.convertToPixels(5, 150), 0.001);
        Assert.assertEquals(3248, UnitConverter.convertToPixels(55, 150), 0.001);
        Assert.assertEquals(2494, UnitConverter.convertToPixels(10.56f, 600), 0.001);
        Assert.assertEquals(23520, UnitConverter.convertToPixels(99.57f, 600), 0.001);
    }
}