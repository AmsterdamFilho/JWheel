package br.com.luvva.jwheel;

import javax.swing.*;

public class ImageResources
{

    public static final String ATENDIMENTO = "atendimento.png";
    public static final String PACIENTE = "paciente.png";
    public static final String CAPTURA = "captura.png";
    public static final String LAUDO = "laudo.png";
    public static final String REPORTS = "relatórios.png";
    public static final String CID = "cid.png";

    private final String imagesDirectory = "/imagens/";

    public Icon getIcon (String fileName)
    {
        return new ImageIcon(getClass().getResource(imagesDirectory + fileName));
    }

}
