// PersonManager.aidl
package com.hugh.aidl.model;

// Declare any non-default types here with import statements
import com.hugh.aidl.model.Person;
interface PersonManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Person> getPersons();
    void addPerson(in Person person);

}
