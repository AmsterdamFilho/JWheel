package br.com.luvva.jwheel.model.providers;

import br.com.luvva.jwheel.cdi.utils.Custom;
import br.com.luvva.jwheel.model.beans.DecisionDialogModel;

import javax.inject.Singleton;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
@SuppressWarnings ("SpellCheckingInspection")
@Singleton
@Custom
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
    public String confirm ()
    {
        return "Confirmar";
    }

    @Override
    public String success ()
    {
        return "Sucesso";
    }

    @Override
    public String error ()
    {
        return "Erro";
    }

    @Override
    public String info ()
    {
        return "Informação";
    }

    @Override
    public String warning ()
    {
        return "Aviso";
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
                "Tentar novamente", "Configurar conexão", "Sair do sistema");
    }

    //<editor-fold desc="ConnectionParametersRecord">
    @Override
    public String connectionParametersRecordTitle ()
    {
        return "Dados da conexão com o banco de dados";
    }

    @Override
    public String connectionParametersRecordURL ()
    {
        return "URL";
    }

    @Override
    public String connectionParametersRecordUser ()
    {
        return "Usuário";
    }

    @Override
    public String connectionParametersRecordDriver ()
    {
        return "Driver";
    }

    @Override
    public String connectionParametersRecordTest ()
    {
        return "Testar conexão";
    }

    @Override
    public String connectionParametersRecordPassword ()
    {
        return "Senha";
    }

    @Override
    public String connectionParametersRecordTestSuccessful ()
    {
        return "Conexão bem sucedida!";
    }

    @Override
    public String connectionParametersRecordTestFailed ()
    {
        return "Não foi possível conectar-se ao banco de dados!";
    }
    //</editor-fold>
}
