package com.senla.courses.action;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface IAction {
    Logger log = LogManager.getLogger(IAction.class.getName());
    void execute();
}
