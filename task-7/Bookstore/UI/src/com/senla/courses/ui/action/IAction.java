package com.senla.courses.ui.action;


import java.util.logging.Logger;

public interface IAction {
    Logger log = Logger.getLogger(IAction.class.getName());
    void execute();
}
