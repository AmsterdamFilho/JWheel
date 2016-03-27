package br.com.luvva.jwheel;

import br.com.luvva.jwheel.model.beans.LogParameters;
import br.com.luvva.jwheel.view.swing.extension.JwDialog;
import br.com.luvva.jwheel.view.swing.extension.SpacedPanel;
import br.com.luvva.jwheel.view.swing.starter.SwingStarter;
import org.junit.runner.RunWith;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

/**
 * @author Amsterdam Filho - amsterdam@luvva.com.br
 */
@RunWith (WeldJUnit4Runner.class)
public abstract class AbstractTest
{

    private @Inject JwLoggerFactory jwLoggerFactory;
    private @Inject LogParameters   logParameters;

    public AbstractTest ()
    {
        WeldContext.getInstance().getBean(SwingStarter.class).configureView();
    }

    @PostConstruct
    private void init ()
    {
        jwLoggerFactory.configure(logParameters);
    }

    protected void showDialogTest (String title) throws Exception
    {
        EventQueue.invokeAndWait(() -> new TestDialog(title));
    }

    protected void showDialogTest (String title, int testContentPaneCode) throws Exception
    {
        EventQueue.invokeAndWait(() -> new TestDialog(title, testContentPaneCode));
    }

    private class TestDialog extends JwDialog
    {
        private TestDialog (String title)
        {
            this(title, new DefaultTestContainerCreator());
        }

        private TestDialog (String title, int testContentPaneCode)
        {
            this(title, new FlexibleTestContainerCreator(testContentPaneCode));
        }

        private TestDialog (String title, TestContainerCreator containerCreator)
        {
            setModal(true);
            setTitle(title);

            Container contentPane = new SpacedPanel(containerCreator.createTestContainer(), 5, 5);
            setContentPane(contentPane);
            Dimension size = contentPane.getPreferredSize();
            size.width += 4;
            size.height += 30;
            setSize(size);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        @Override
        public void dialogRequestedToClose ()
        {
            dispose();
        }
    }

    protected Container createTestContentPane ()
    {
        return null;
    }

    protected Container createTestContentPane (@SuppressWarnings ("UnusedParameters") int testContentPaneCode)
    {
        return null;
    }

    private class FlexibleTestContainerCreator implements TestContainerCreator
    {
        private int testContentPaneCode;

        private FlexibleTestContainerCreator (int testContentPaneCode)
        {
            this.testContentPaneCode = testContentPaneCode;
        }

        @Override
        public Container createTestContainer ()
        {
            return createTestContentPane(testContentPaneCode);
        }
    }

    private class DefaultTestContainerCreator implements TestContainerCreator
    {
        @Override
        public Container createTestContainer ()
        {
            return createTestContentPane();
        }
    }

    private interface TestContainerCreator
    {
        Container createTestContainer ();
    }
}
