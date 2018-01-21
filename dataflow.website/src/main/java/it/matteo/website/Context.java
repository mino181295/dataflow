package it.matteo.website;

import it.matteo.datamodel.Person;

public class Context {
    
    Person user;
    
    private static Context INSTANCE = null;
    
    private Context(){
        init();
    }
    
    public static Context getInstance(){
        if (INSTANCE != null) {
            return INSTANCE;
        }
        return new Context();
    }
    
    private void init() {
        this.user = new Person("Martina", "Mambelli", 18);
    }
    
    public Person getLoggedUser() {
        return this.user;
    }
    
}
