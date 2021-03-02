package com.senla.courses.ui.action;

public class SortBookBy extends AbstractAction implements IAction{
    private int index;

    public SortBookBy(int index) {
        this.index = index;
    }

    @Override
    public void execute() {
        facade.sortBooks(facade.createBookComparators().get(index));
    }
}
