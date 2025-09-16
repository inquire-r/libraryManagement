package org.repository;

import org.model.Patron;
import org.utils.LoggerUtil;

import java.util.HashMap;
import java.util.Map;

public class PatronRepository implements IPatronRepository {

    private final Map<String, Patron> patrons = new HashMap<>();

    @Override
    public void addPatron(Patron patron) {
        patrons.put(patron.getId(), patron);
        LoggerUtil.logInfo("Patron added: " + patron);
    }

    @Override
    public void updatePatron(Patron patron) {
        patrons.put(patron.getId(), patron);
        LoggerUtil.logInfo("Patron updated: " + patron);
    }

    @Override
    public Patron getPatronById(String id) {
        return patrons.get(id);
    }
}