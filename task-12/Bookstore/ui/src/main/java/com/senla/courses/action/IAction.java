package com.senla.courses.action;


import java.util.logging.Logger;

public interface IAction {
    Logger log = Logger.getLogger(IAction.class.getName());
    void execute();
}
