package br.com.luvva.jwheel.swing.providers;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
@ApplicationScoped
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
