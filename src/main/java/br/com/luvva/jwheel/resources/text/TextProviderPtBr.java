package br.com.luvva.jwheel.resources.text;

public final class TextProviderPtBr implements TextProvider
{

    @Override
    public String JOptionPane_Yes_Option ()
    {
        return "Sim";
    }

    @Override
    public String JOptionPane_No_Option ()
    {
        return "Não";
    }

    @Override
    public String getExitSystemQuestion ()
    {
        return "Deseja sair do sistema?";
    }
}
