package model.combobox;

import misc.utility.ViewMisc;
import model.roles.Doctor;
import model.roles.Patient;
import model.roles.Person;

public class AccSettings extends Option {

    public AccSettings() {
        super("ACCOUNT SETTINGS");
    }

    @Override
    public void action(Person person) {
        if (person instanceof Patient) {
            ViewMisc.showPatientSettings();
        }
        if (person instanceof Doctor) {
            ViewMisc.showDoctorSettings();
        }
    }

}
