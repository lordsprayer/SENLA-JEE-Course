package com.senla.courses.ui.action.book;

import com.senla.courses.ui.action.IAction;
import com.senla.courses.ui.action.AbstractAction;

public class SortUnsoldBookBy extends AbstractAction implements IAction {
    private final int index;

    public SortUnsoldBookBy(int index) {
        this.index = index;
    }

    @Override
    public void execute() {
        facade.sortUnsoldBooks(facade.createBookComparators().get(index));
    }
}
