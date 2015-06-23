package br.com.luvva.jwheel;

import javax.swing.*;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
public class ImageResources
{

    public static final String APPOINTMENT = "appointment.png";
    public static final String PATIENT = "patient.png";
    public static final String CAMERA = "camera.png";
    public static final String APPRAISAL = "appraisal.png";
    public static final String REPORTS = "reports.png";
    public static final String CID = "cid.png";

    private final String imagesDirectory = "/images/";

    public Icon getIcon (String fileName)
    {
        return new ImageIcon(getClass().getResource(imagesDirectory + fileName));
    }

}
