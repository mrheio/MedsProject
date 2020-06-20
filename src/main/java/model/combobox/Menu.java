package model.combobox;

import misc.utility.ViewMisc;
import model.roles.Doctor;
import model.roles.Patient;
import model.roles.Person;

public class Menu extends Option {

    public Menu() {
        super("MENU");
    }

    @Override
    public void action(Person person) {
        if (person instanceof Patient) {
            ViewMisc.showPatientMenu();
        }
        if (person instanceof Doctor) {
            ViewMisc.showDoctorMenu();
        }
    }
}
