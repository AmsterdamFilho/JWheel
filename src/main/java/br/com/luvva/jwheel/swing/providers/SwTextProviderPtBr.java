package br.com.luvva.jwheel.swing.providers;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@Singleton
public class SwTextProviderPtBr implements SwTextProvider
{

    @Override
    public String getJOptionPane_Yes_Option ()
    {
        return "Sim";
    }

    @Override
    public String getJOptionPane_No_Option ()
    {
        return "Não";
    }

    @Override
    public String getExitSystemQuestion ()
    {
        return "Deseja sair do sistema?";
    }
}
