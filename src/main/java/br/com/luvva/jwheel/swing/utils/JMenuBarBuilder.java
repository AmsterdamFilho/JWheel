package br.com.luvva.jwheel.swing.utils;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class JMenuBarBuilder
{

    private final JMenuBar     menuBar      = new JMenuBar();
    private final Deque<JMenu> menus        = new ArrayDeque<>();
    private final char         nullMnemonic = '\u0000';

    public void addMenu (String menuText)
    {
        addMenu(menuText, nullMnemonic);
    }

    public void addMenu (String menuText, char mnemonic)
    {
        JMenu menu = new JMenu(menuText);
        menus.addFirst(menu);
        if (mnemonic != nullMnemonic)
        {
            menu.setMnemonic(mnemonic);
        }
        menuBar.add(menu);
    }

    public void addChildMenu (String childMenuText)
    {
        JMenu jMenu = new JMenu(childMenuText);
        menus.peek().add(jMenu);
        menus.addFirst(jMenu);
    }

    public void leaveChildMenu ()
    {
        menus.poll();
    }

    public void addMenuItem (String menuItemText, ActionListener actionListener)
    {
        addMenuItem(menuItemText, actionListener, "");
    }

    public void addMenuItem (String menuItemText, ActionListener actionListener, String keyCode)
    {
        addMenuItem(menuItemText, actionListener, keyCode, nullMnemonic);
    }

    public void addMenuItem (String menuItemText, ActionListener actionListener, String keyCode, char mnemonic)
    {
        JMenuItem menuItem = new JMenuItem(menuItemText);
        if (!keyCode.isEmpty())
        {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(keyCode));
        }
        if (mnemonic != nullMnemonic)
        {
            menuItem.setMnemonic(mnemonic);
        }
        menuItem.addActionListener(actionListener);
        menus.peek().add(menuItem);
    }

    public void addHorizontalSeparator ()
    {
        JSeparator jSeparator = new JSeparator();
        jSeparator.setOrientation(JSeparator.HORIZONTAL);
        menus.peek().add(jSeparator);
    }

    public void alignNextToTheRight ()
    {
        menuBar.add(Box.createHorizontalGlue());
    }

    public JMenuBar getMenuBar ()
    {
        return menuBar;
    }
}
