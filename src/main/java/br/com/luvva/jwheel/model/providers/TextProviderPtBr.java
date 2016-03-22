package br.com.luvva.jwheel.model.providers;

import br.com.luvva.jwheel.model.beans.DecisionDialogModel;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@SuppressWarnings ("SpellCheckingInspection")
@Singleton
public class TextProviderPtBr implements TextProvider
{

    @Override
    public String yes ()
    {
        return "Sim";
    }

    @Override
    public String no ()
    {
        return "Não";
    }

    @Override
    public String cancel ()
    {
        return "Cancelar";
    }

    @Override
    public String save ()
    {
        return "Salvar";
    }

    @Override
    public String ok ()
    {
        return "OK";
    }

    @Override
    public String success ()
    {
        return "Sucesso";
    }

    @Override
    public String attention ()
    {
        return "Atenção";
    }

    @Override
    public String savedSuccessfully ()
    {
        return "Salvo com sucesso!";
    }

    @Override
    public String failedToSave ()
    {
        return "Erro ao salvar";
    }

    @Override
    public String exitSystemQuestion ()
    {
        return "Deseja sair do sistema?";
    }

    @Override
    public String databaseConnectionTestMessage ()
    {
        return "Tentando se conectar ao banco de dados...";
    }

    @Override
    public DecisionDialogModel connectionTestFailedDecision ()
    {
        return new DecisionDialogModel(
                "Não foi possível conectar-se com o banco de dados! " +
                        "Talvez o servidor esteja inacessível ou os dados para conexão estejam incorretos!",
                "Configurar conexão", "Tentar novamente", "Sair do sistema");
    }
}
