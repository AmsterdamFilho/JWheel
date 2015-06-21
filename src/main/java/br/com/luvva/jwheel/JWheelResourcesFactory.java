package br.com.luvva.jwheel;

import br.com.luvva.jwheel.resources.images.DefaultImageResourceProvider;
import br.com.luvva.jwheel.resources.images.ImageResourceProvider;
import br.com.luvva.jwheel.resources.text.TextProvider;
import br.com.luvva.jwheel.resources.text.TextProviderPtBr;

public class JWheelResourcesFactory
{

    public static ImageResourceProvider imageResourceProvider = new DefaultImageResourceProvider();

    public static TextProvider textProvider = new TextProviderPtBr();

}
