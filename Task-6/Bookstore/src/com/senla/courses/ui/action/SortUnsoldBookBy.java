package com.senla.courses.ui.action;

public class SortUnsoldBookBy extends AbstractAction implements IAction{
    private int index;

    public SortUnsoldBookBy(int index) {
        this.index = index;
    }

    @Override
    public void execute() {
        facade.sortUnsoldBooks(facade.createBookComparators().get(index));
    }
}
