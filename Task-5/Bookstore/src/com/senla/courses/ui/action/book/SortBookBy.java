package com.senla.courses.ui.action.book;

import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.AbstractAction;

public class SortBookBy extends AbstractAction implements IAction {
    private final int index;

    public SortBookBy(int index) {
        this.index = index;
    }

    @Override
    public void execute() {
        facade.sortBooks(facade.createBookComparators().get(index));
    }
}
