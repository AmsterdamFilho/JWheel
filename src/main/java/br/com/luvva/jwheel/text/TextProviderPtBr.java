package br.com.luvva.jwheel.text;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TextProviderPtBr implements TextProvider
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
