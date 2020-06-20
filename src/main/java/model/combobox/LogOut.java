package model.combobox;

import misc.utility.ViewMisc;
import model.roles.Person;

public class LogOut extends Option {

    public LogOut() {
        super("LOG OUT");
    }

    @Override
    public void action(Person person) {
        ViewMisc.showLogin();
    }
}
