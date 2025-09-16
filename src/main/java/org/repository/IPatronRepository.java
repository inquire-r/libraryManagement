package org.repository;

import org.model.Patron;

public interface IPatronRepository {
    void addPatron(Patron patron);
    void updatePatron(Patron patron);
    Patron getPatronById(String id);
}
