package br.com.luvva.jwheel;

import br.com.luvva.jwheel.images.DefaultImageProvider;
import br.com.luvva.jwheel.images.ImageProvider;
import br.com.luvva.jwheel.text.TextProvider;
import br.com.luvva.jwheel.text.TextProviderPtBr;

public class JWheelResourcesFactory
{

    public static ImageProvider imageProvider = new DefaultImageProvider();

    public static TextProvider textProvider = new TextProviderPtBr();

}
