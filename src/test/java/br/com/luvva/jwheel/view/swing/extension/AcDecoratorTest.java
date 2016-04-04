package br.com.luvva.jwheel.view.swing.extension;

import br.com.luvva.jwheel.AbstractTest;
import br.com.luvva.jwheel.control.autocomplete.AbstractAcController;
import br.com.luvva.jwheel.control.autocomplete.AcController;
import br.com.luvva.jwheel.control.autocomplete.AcControllerFromStringsList;
import br.com.luvva.jwheel.control.autocomplete.AcListener;
import br.com.luvva.jwheel.model.utils.ListItemsCollector;
import br.com.luvva.jwheel.view.swing.docfilters.LimitedLengthDocumentFilter;
import br.com.luvva.jwheel.view.swing.utils.SwingUtils;
import org.junit.Test;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.util.*;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class AcDecoratorTest extends AbstractTest
{

    private List<String>   statesAbbreviations = new ArrayList<>();
    private List<String>   statesNames         = new ArrayList<>();
    private List<String>   personsNames        = new ArrayList<>();
    private List<Object[]> personDatabase      = new ArrayList<>();

    private LimitedLengthDocumentFilter documentFilter = new LimitedLengthDocumentFilter();

    private @Inject AcDecorator mainTestDecorator;
    private @Inject AcDecorator passwordDecorator;
    private JwTable.Configurator namesTableConfigurator = new JwTable.Configurator()
    {
        @Override
        public String[] columnsTitles ()
        {
            return new String[]{"Code", "Name"};
        }

        @Override
        public Class<?>[] columnsClasses ()
        {
            return new Class<?>[]{Integer.class, String.class};
        }

        @Override
        public Map<Integer, TableCellRenderer> overriddenRenderersMap ()
        {
            return null;
        }

        @Override
        public int[] columnsWidths ()
        {
            return new int[]{60, 0};
        }

        @Override
        public boolean[] shouldResizeColumns ()
        {
            return new boolean[]{false, true};
        }

        @Override
        public int[] sortedColumns ()
        {
            return new int[]{1};
        }
    };

    private AcController statesController;
    private NamesAcController        namesController        = new NamesAcController();
    private PrimeNumbersAcController primeNumbersController = new PrimeNumbersAcController();

    private JwTextField mainTestTextField = new JwTextField();
    private JLabel mainTestLabel;

    private LabelUpdater noAcLabelUpdater;
    private LabelUpdater statesLabelUpdater;
    private LabelUpdater namesLabelUpdater;
    private LabelUpdater primeNumbersLabelUpdater;

    @PostConstruct
    private void init ()
    {
        populateLists();
        statesController = new BrazilianStatesAcController();
        statesLabelUpdater = new LabelUpdater(() -> {
            String selected = statesController.getSelectedAsString();
            if (selected.isEmpty())
            {
                return " Select a brazilian state to see its abbreviation.";
            }
            else
            {
                String abbreviation = statesAbbreviations.get(statesNames.indexOf(selected));
                return " Selected state abbreviation is: " + abbreviation;
            }
        });
        statesController.addListener(() -> statesLabelUpdater.update());

        namesLabelUpdater = new LabelUpdater(() -> {
            String selected = namesController.getSelectedAsString();
            if (selected.isEmpty())
            {
                return " Find and select a person in the database to see her age.";
            }
            else
            {
                return " " + selected + ", code " + namesController.getSelectedPersonsCode() +
                        ", is " + namesController.getSelectedPersonsAge() + " years old.";
            }
        });
        namesController.addListener(() -> namesLabelUpdater.update());

        primeNumbersLabelUpdater = new LabelUpdater(() -> {
            String text = mainTestTextField.getText();
            if (text.isEmpty() || !text.matches("[0-9]+"))
            {
                primeNumbersController.clearHistory();
                return " Type a number to see a list of the 50 next prime numbers after it";
            }
            else
            {
                StringBuilder response = new StringBuilder();
                response.append(" Calculated for: ");
                for (String number : primeNumbersController.getHistory())
                {
                    response.append(number).append(", ");
                }
                String responseString = response.toString();
                return responseString.substring(0, responseString.length() - 2);
            }
        });
        primeNumbersController.addListener(() -> primeNumbersLabelUpdater.update());

        noAcLabelUpdater =
                new LabelUpdater(() -> " The text field does not have an auto complete yet. Choose an option below!");
    }

    @Test
    public void test () throws Exception
    {
        showDialogTest("Auto complete test");
    }

    @SuppressWarnings ("SpellCheckingInspection")
    private void populateLists ()
    {
        statesAbbreviations.add("AC");
        statesAbbreviations.add("AL");
        statesAbbreviations.add("AP");
        statesAbbreviations.add("AM");
        statesAbbreviations.add("BA");
        statesAbbreviations.add("CE");
        statesAbbreviations.add("DF");
        statesAbbreviations.add("ES");
        statesAbbreviations.add("GO");
        statesAbbreviations.add("MA");
        statesAbbreviations.add("MT");
        statesAbbreviations.add("MS");
        statesAbbreviations.add("MG");
        statesAbbreviations.add("PA");
        statesAbbreviations.add("PB");
        statesAbbreviations.add("PR");
        statesAbbreviations.add("PE");
        statesAbbreviations.add("PI");
        statesAbbreviations.add("RJ");
        statesAbbreviations.add("RN");
        statesAbbreviations.add("RS");
        statesAbbreviations.add("RO");
        statesAbbreviations.add("RR");
        statesAbbreviations.add("SC");
        statesAbbreviations.add("SP");
        statesAbbreviations.add("SE");
        statesAbbreviations.add("TO");

        statesNames.add("Acre");
        statesNames.add("Alagoas");
        statesNames.add("Amapá");
        statesNames.add("Amazonas");
        statesNames.add("Bahia");
        statesNames.add("Ceará");
        statesNames.add("Distrito Federal");
        statesNames.add("Espírito Santo");
        statesNames.add("Goiás");
        statesNames.add("Maranhão");
        statesNames.add("Mato Grosso");
        statesNames.add("Mato Grosso do Sul");
        statesNames.add("Minas Gerais");
        statesNames.add("Pará");
        statesNames.add("Paraíba");
        statesNames.add("Paraná");
        statesNames.add("Pernambuco");
        statesNames.add("Piauí");
        statesNames.add("Rio de Janeiro");
        statesNames.add("Rio Grande do Norte");
        statesNames.add("Rio Grande do Sul");
        statesNames.add("Rondônia");
        statesNames.add("Roraima");
        statesNames.add("Santa Catarina");
        statesNames.add("São Paulo");
        statesNames.add("Sergipe");
        statesNames.add("Tocantins");

        personsNames.add("MARY");
        personsNames.add("PATRICIA");
        personsNames.add("LINDA");
        personsNames.add("BARBARA");
        personsNames.add("ELIZABETH");
        personsNames.add("JENNIFER");
        personsNames.add("MARIA");
        personsNames.add("SUSAN");
        personsNames.add("MARGARET");
        personsNames.add("DOROTHY");
        personsNames.add("LISA");
        personsNames.add("NANCY");
        personsNames.add("KAREN");
        personsNames.add("BETTY");
        personsNames.add("HELEN");
        personsNames.add("SANDRA");
        personsNames.add("DONNA");
        personsNames.add("CAROL");
        personsNames.add("RUTH");
        personsNames.add("SHARON");
        personsNames.add("MICHELLE");
        personsNames.add("LAURA");
        personsNames.add("SARAH");
        personsNames.add("KIMBERLY");
        personsNames.add("DEBORAH");
        personsNames.add("JESSICA");
        personsNames.add("SHIRLEY");
        personsNames.add("CYNTHIA");
        personsNames.add("ANGELA");
        personsNames.add("MELISSA");
        personsNames.add("BRENDA");
        personsNames.add("AMY");
        personsNames.add("ANNA");
        personsNames.add("REBECCA");
        personsNames.add("VIRGINIA");
        personsNames.add("KATHLEEN");
        personsNames.add("PAMELA");
        personsNames.add("MARTHA");
        personsNames.add("DEBRA");
        personsNames.add("AMANDA");
        personsNames.add("STEPHANIE");
        personsNames.add("CAROLYN");
        personsNames.add("CHRISTINE");
        personsNames.add("MARIE");
        personsNames.add("JANET");
        personsNames.add("CATHERINE");
        personsNames.add("FRANCES");
        personsNames.add("ANN");
        personsNames.add("JOYCE");
        personsNames.add("DIANE");
        personsNames.add("ALICE");
        personsNames.add("JULIE");
        personsNames.add("HEATHER");
        personsNames.add("TERESA");
        personsNames.add("DORIS");
        personsNames.add("GLORIA");
        personsNames.add("EVELYN");
        personsNames.add("JEAN");
        personsNames.add("CHERYL");
        personsNames.add("MILDRED");
        personsNames.add("KATHERINE");
        personsNames.add("JOAN");
        personsNames.add("ASHLEY");
        personsNames.add("JUDITH");
        personsNames.add("ROSE");
        personsNames.add("JANICE");
        personsNames.add("KELLY");
        personsNames.add("NICOLE");
        personsNames.add("JUDY");
        personsNames.add("CHRISTINA");
        personsNames.add("KATHY");
        personsNames.add("THERESA");
        personsNames.add("BEVERLY");
        personsNames.add("DENISE");
        personsNames.add("TAMMY");
        personsNames.add("IRENE");
        personsNames.add("JANE");
        personsNames.add("LORI");
        personsNames.add("RACHEL");
        personsNames.add("MARILYN");
        personsNames.add("ANDREA");
        personsNames.add("KATHRYN");
        personsNames.add("LOUISE");
        personsNames.add("SARA");
        personsNames.add("ANNE");
        personsNames.add("JACQUELINE");
        personsNames.add("WANDA");
        personsNames.add("BONNIE");
        personsNames.add("JULIA");
        personsNames.add("RUBY");
        personsNames.add("LOIS");
        personsNames.add("TINA");
        personsNames.add("PHYLLIS");
        personsNames.add("NORMA");
        personsNames.add("PAULA");
        personsNames.add("DIANA");
        personsNames.add("ANNIE");
        personsNames.add("LILLIAN");
        personsNames.add("EMILY");
        personsNames.add("ROBIN");
        personsNames.add("PEGGY");
        personsNames.add("CRYSTAL");
        personsNames.add("GLADYS");
        personsNames.add("RITA");
        personsNames.add("DAWN");
        personsNames.add("CONNIE");
        personsNames.add("FLORENCE");
        personsNames.add("TRACY");
        personsNames.add("EDNA");
        personsNames.add("TIFFANY");
        personsNames.add("CARMEN");
        personsNames.add("ROSA");
        personsNames.add("CINDY");
        personsNames.add("GRACE");
        personsNames.add("WENDY");
        personsNames.add("VICTORIA");
        personsNames.add("EDITH");
        personsNames.add("KIM");
        personsNames.add("SHERRY");
        personsNames.add("SYLVIA");
        personsNames.add("JOSEPHINE");
        personsNames.add("THELMA");
        personsNames.add("SHANNON");
        personsNames.add("SHEILA");
        personsNames.add("ETHEL");
        personsNames.add("ELLEN");
        personsNames.add("ELAINE");
        personsNames.add("MARJORIE");
        personsNames.add("CARRIE");
        personsNames.add("CHARLOTTE");
        personsNames.add("MONICA");
        personsNames.add("ESTHER");
        personsNames.add("PAULINE");
        personsNames.add("EMMA");
        personsNames.add("JUANITA");
        personsNames.add("ANITA");
        personsNames.add("RHONDA");
        personsNames.add("HAZEL");
        personsNames.add("AMBER");
        personsNames.add("EVA");
        personsNames.add("DEBBIE");
        personsNames.add("APRIL");
        personsNames.add("LESLIE");
        personsNames.add("CLARA");
        personsNames.add("LUCILLE");
        personsNames.add("JAMIE");
        personsNames.add("JOANNE");
        personsNames.add("ELEANOR");
        personsNames.add("VALERIE");
        personsNames.add("DANIELLE");
        personsNames.add("MARY");
        personsNames.add("PATRICIA");
        personsNames.add("LINDA");
        personsNames.add("BARBARA");
        personsNames.add("ELIZABETH");
        personsNames.add("JENNIFER");
        personsNames.add("MARIA");
        personsNames.add("SUSAN");
        personsNames.add("MARGARET");
        personsNames.add("DOROTHY");
        personsNames.add("LISA");
        personsNames.add("NANCY");
        personsNames.add("KAREN");
        personsNames.add("BETTY");
        personsNames.add("HELEN");
        personsNames.add("SANDRA");
        personsNames.add("DONNA");
        personsNames.add("CAROL");
        personsNames.add("RUTH");
        personsNames.add("SHARON");
        personsNames.add("MICHELLE");
        personsNames.add("LAURA");
        personsNames.add("SARAH");
        personsNames.add("KIMBERLY");
        personsNames.add("DEBORAH");
        personsNames.add("JESSICA");
        personsNames.add("SHIRLEY");
        personsNames.add("CYNTHIA");
        personsNames.add("ANGELA");
        personsNames.add("MELISSA");
        personsNames.add("BRENDA");
        personsNames.add("AMY");
        personsNames.add("ANNA");
        personsNames.add("REBECCA");
        personsNames.add("VIRGINIA");
        personsNames.add("KATHLEEN");
        personsNames.add("PAMELA");
        personsNames.add("MARTHA");
        personsNames.add("DEBRA");
        personsNames.add("AMANDA");
        personsNames.add("STEPHANIE");
        personsNames.add("CAROLYN");
        personsNames.add("CHRISTINE");
        personsNames.add("MARIE");
        personsNames.add("JANET");
        personsNames.add("CATHERINE");
        personsNames.add("FRANCES");
        personsNames.add("ANN");
        personsNames.add("JOYCE");
        personsNames.add("DIANE");
        personsNames.add("ALICE");
        personsNames.add("JULIE");
        personsNames.add("HEATHER");
        personsNames.add("TERESA");
        personsNames.add("DORIS");
        personsNames.add("GLORIA");
        personsNames.add("EVELYN");
        personsNames.add("JEAN");
        personsNames.add("CHERYL");
        personsNames.add("MILDRED");
        personsNames.add("KATHERINE");
        personsNames.add("JOAN");
        personsNames.add("ASHLEY");
        personsNames.add("JUDITH");
        personsNames.add("ROSE");
        personsNames.add("JANICE");
        personsNames.add("KELLY");
        personsNames.add("NICOLE");
        personsNames.add("JUDY");
        personsNames.add("CHRISTINA");
        personsNames.add("KATHY");
        personsNames.add("THERESA");
        personsNames.add("BEVERLY");
        personsNames.add("DENISE");
        personsNames.add("TAMMY");
        personsNames.add("IRENE");
        personsNames.add("JANE");
        personsNames.add("LORI");
        personsNames.add("RACHEL");
        personsNames.add("MARILYN");
        personsNames.add("ANDREA");
        personsNames.add("KATHRYN");
        personsNames.add("LOUISE");
        personsNames.add("SARA");
        personsNames.add("ANNE");
        personsNames.add("JACQUELINE");
        personsNames.add("WANDA");
        personsNames.add("BONNIE");
        personsNames.add("JULIA");
        personsNames.add("RUBY");
        personsNames.add("LOIS");
        personsNames.add("TINA");
        personsNames.add("PHYLLIS");
        personsNames.add("NORMA");
        personsNames.add("PAULA");
        personsNames.add("DIANA");
        personsNames.add("ANNIE");
        personsNames.add("LILLIAN");
        personsNames.add("EMILY");
        personsNames.add("ROBIN");
        personsNames.add("PEGGY");
        personsNames.add("CRYSTAL");
        personsNames.add("GLADYS");
        personsNames.add("RITA");
        personsNames.add("DAWN");
        personsNames.add("CONNIE");
        personsNames.add("FLORENCE");
        personsNames.add("TRACY");
        personsNames.add("EDNA");
        personsNames.add("TIFFANY");
        personsNames.add("CARMEN");
        personsNames.add("ROSA");
        personsNames.add("CINDY");
        personsNames.add("GRACE");
        personsNames.add("WENDY");
        personsNames.add("VICTORIA");
        personsNames.add("EDITH");
        personsNames.add("KIM");
        personsNames.add("SHERRY");
        personsNames.add("SYLVIA");
        personsNames.add("JOSEPHINE");
        personsNames.add("THELMA");
        personsNames.add("SHANNON");
        personsNames.add("SHEILA");
        personsNames.add("ETHEL");
        personsNames.add("ELLEN");
        personsNames.add("ELAINE");
        personsNames.add("MARJORIE");
        personsNames.add("CARRIE");
        personsNames.add("CHARLOTTE");
        personsNames.add("MONICA");
        personsNames.add("ESTHER");
        personsNames.add("PAULINE");
        personsNames.add("EMMA");
        personsNames.add("JUANITA");
        personsNames.add("ANITA");
        personsNames.add("RHONDA");
        personsNames.add("HAZEL");
        personsNames.add("AMBER");
        personsNames.add("EVA");
        personsNames.add("DEBBIE");
        personsNames.add("APRIL");
        personsNames.add("LESLIE");
        personsNames.add("CLARA");
        personsNames.add("LUCILLE");
        personsNames.add("JAMIE");
        personsNames.add("JOANNE");
        personsNames.add("ELEANOR");
        personsNames.add("VALERIE");
        personsNames.add("DANIELLE");

        Random random = new Random();
        int personCode = 1;
        for (String personName : personsNames)
        {
            personDatabase.add(new Object[]{personCode, personName, random.nextInt(80) + 11});
            personCode++;
        }
    }

    @Override
    protected Container createTestContentPane ()
    {
        SwingUtils.removeEnterAsForwardTraversalKey(mainTestTextField);
        mainTestTextField.setDocumentFilter(documentFilter);

        LabeledPanel labeledPanel = new LabeledPanel();
        labeledPanel.setComponent(mainTestTextField, "");
        mainTestLabel = labeledPanel.getLabel();

        JRadioButton rbTestList = new AcTestRadioButton("Test autocomplete by list", new DecorationSettings()
        {
            @Override
            public AcController acController ()
            {
                return statesController;
            }

            @Override
            public JwTable.Configurator tableConfigurator ()
            {
                return null;
            }

            @Override
            public boolean selectFirstAfterAc ()
            {
                return true;
            }
        }, statesLabelUpdater, 50);
        JRadioButton rbNoAc =
                new AcTestRadioButton("Test removing autocomplete decoration", null, noAcLabelUpdater, 100);
        JRadioButton rbTestTable =
                new AcTestRadioButton("Test autocomplete with multiple columns", new DecorationSettings()
                {
                    @Override
                    public AcController acController ()
                    {
                        return namesController;
                    }

                    @Override
                    public JwTable.Configurator tableConfigurator ()
                    {
                        return namesTableConfigurator;
                    }

                    @Override
                    public boolean selectFirstAfterAc ()
                    {
                        return false;
                    }
                }, namesLabelUpdater, 50);
        JRadioButton rbTestAcDelay = new AcTestRadioButton("Test autocomplete configured with a delay " +
                "to start auto completing.", new DecorationSettings()
        {
            @Override
            public AcController acController ()
            {
                return primeNumbersController;
            }

            @Override
            public JwTable.Configurator tableConfigurator ()
            {
                return null;
            }

            @Override
            public boolean selectFirstAfterAc ()
            {
                return false;
            }
        }, primeNumbersLabelUpdater, 8);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbTestAcDelay);
        buttonGroup.add(rbTestTable);
        buttonGroup.add(rbTestList);
        buttonGroup.add(rbNoAc);
        rbTestTable.setSelected(true);

        GridBagLayoutPanel contentPane = new GridBagLayoutPanel();
        contentPane.addWithConstraints(createJwPasswordPanel());
        contentPane.getConstraints().gridy = 1;
        contentPane.addWithConstraints(labeledPanel);
        contentPane.getConstraints().gridy = 2;
        contentPane.addWithConstraints(rbNoAc);
        contentPane.getConstraints().gridy = 3;
        contentPane.addWithConstraints(rbTestList);
        contentPane.getConstraints().gridy = 4;
        contentPane.addWithConstraints(rbTestTable);
        contentPane.getConstraints().gridy = 5;
        contentPane.addWithConstraints(rbTestAcDelay);
        contentPane.addWithConstraints(Box.createHorizontalStrut(500));

        return contentPane;
    }

    private Component createJwPasswordPanel ()
    {
        JwPasswordField jwPasswordField = new JwPasswordField();
        SwingUtils.removeEnterAsForwardTraversalKey(jwPasswordField);
        passwordDecorator.setController(new PasswordFieldAcController());
        passwordDecorator.decorate(jwPasswordField);
        LabeledPanel labeledPanelPassword = new LabeledPanel();
        labeledPanelPassword.setComponent(jwPasswordField, "Type a password");
        return labeledPanelPassword;
    }

    private class AcTestRadioButton extends JRadioButton
    {
        private AcTestRadioButton (String text, DecorationSettings settings, LabelUpdater labelUpdater,
                                   int textFieldMaxLength)
        {
            super(text);
            setFocusable(false);
            addItemListener(e -> {
                if (isSelected())
                {
                    mainTestDecorator.undo();
                    if (settings != null)
                    {
                        mainTestDecorator.setSelectFirstWhenPopupShows(settings.selectFirstAfterAc());
                        mainTestDecorator.setController(settings.acController(), settings.tableConfigurator());
                        mainTestDecorator.decorate(mainTestTextField);
                    }
                    labelUpdater.update();
                }
                documentFilter.setMaximumLength(textFieldMaxLength);
            });
        }
    }

    private class PasswordFieldAcController extends AbstractAcController
    {
        @Override
        public List<Object[]> getAutoCompleteOptions (String incomplete)
        {
            Object[] suggestion = new Object[]{incomplete};
            List<Object[]> response = new ArrayList<>();
            if (!(incomplete == null || incomplete.isEmpty()))
            {
                response.add(suggestion);
            }
            return response;
        }

        @Override
        public boolean autocompleteIsResourceConsuming (String incomplete)
        {
            return false;
        }

        @Override
        public String getSelectedAsString ()
        {
            Object[] selected = getSelected();
            if (selected == null)
            {
                return "";
            }
            return getSelected()[0].toString();
        }
    }

    private class BrazilianStatesAcController extends AcControllerFromStringsList
    {

        private BrazilianStatesAcController ()
        {
            super(statesNames);
        }

    }

    private class NamesAcController extends AbstractAcController
    {
        private ListItemsCollector collector = new ListItemsCollector();

        @Override
        public List<Object[]> getAutoCompleteOptions (String hint)
        {
            List<String> matchingNames = collector.collect(personsNames, hint);
            Set<String> uniqueMatchingNames = new HashSet<>(matchingNames);
            List<Object[]> response = new ArrayList<>();
            for (String matchingName : uniqueMatchingNames)
            {
                Object[] personData = personDatabase.get(personsNames.indexOf(matchingName));
                Object[] nameSake = personDatabase.get(personsNames.lastIndexOf(matchingName));
                response.add(personData);
                response.add(nameSake);
            }
            return response;
        }

        @Override
        public boolean autocompleteIsResourceConsuming (String hint)
        {
            return false;
        }

        @Override
        public String getSelectedAsString ()
        {
            Object[] selected = getSelected();
            if (selected == null)
            {
                return "";
            }
            return selected[1].toString();
        }

        private String getSelectedPersonsAge ()
        {
            return getSelected()[2].toString();
        }

        private String getSelectedPersonsCode ()
        {
            return getSelected()[0].toString();
        }
    }

    private class PrimeNumbersAcController extends AbstractAcController
    {

        // stores the numbers to which the calculation finished successfully
        private List<String> history = new ArrayList<>();

        @Override
        public synchronized List<Object[]> getAutoCompleteOptions (String incomplete)
        {
            List<Object[]> primeNumbers = new ArrayList<>();
            if (incomplete.matches("[0-9]+"))
            {
                long loopNumber = Long.valueOf(incomplete);
                for (int i = 0; i < 50; i++)
                {
                    loopNumber++;
                    while (!isPrime(loopNumber))
                    {
                        loopNumber++;
                    }
                    primeNumbers.add(new Object[]{String.valueOf(loopNumber)});
                }
            }
            history.add(incomplete);
            EventQueue.invokeLater(() -> primeNumbersLabelUpdater.update());
            return primeNumbers;
        }

        @Override
        public String getSelectedAsString ()
        {
            Object[] selected = getSelected();
            return selected == null ? "" : selected[0].toString();
        }

        @Override
        public boolean autocompleteIsResourceConsuming (String incomplete)
        {
            return true;
        }

        private boolean isPrime (long number)
        {
            for (long i = 2; i < number; i++)
            {
                if (number % i == 0)
                {
                    return false;
                }
            }
            return true;
        }

        private void clearHistory ()
        {
            history.clear();
        }

        private List<String> getHistory ()
        {
            return history;
        }
    }

    private class LabelUpdater implements AcListener
    {
        private LabelTextProvider labelTextProvider;

        private LabelUpdater (LabelTextProvider labelTextProvider)
        {
            this.labelTextProvider = labelTextProvider;
        }

        @Override
        public void selectedOptionChanged ()
        {
            mainTestLabel.setText(labelTextProvider.getLabelText());
        }

        private void update ()
        {
            selectedOptionChanged();
        }
    }

    private interface LabelTextProvider
    {
        String getLabelText ();
    }

    private interface DecorationSettings
    {
        AcController acController ();

        JwTable.Configurator tableConfigurator ();

        boolean selectFirstAfterAc ();
    }

}