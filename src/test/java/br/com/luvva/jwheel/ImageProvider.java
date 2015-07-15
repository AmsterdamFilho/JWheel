package br.com.luvva.jwheel;

import br.com.luvva.jwheel.cdi.utils.Initialized;
import br.com.luvva.jwheel.swing.utils.ResourceProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
@Singleton
public class ImageProvider
{

    @Inject
    @Initialized (stringValue = "/images/")
    private ResourceProvider resourceProvider;

    public Icon getCidIcon ()
    {
        return resourceProvider.getIcon("cid.png");
    }

    public Icon getCameraIcon ()
    {
        return resourceProvider.getIcon("camera.png");
    }

    public Icon getAppraisalIcon ()
    {
        return resourceProvider.getIcon("appraisal.png");
    }

    public Icon getPatientIcon ()
    {
        return resourceProvider.getIcon("patient.png");
    }

    public Icon getAppointmentIcon ()
    {
        return resourceProvider.getIcon("appointment.png");
    }

}
