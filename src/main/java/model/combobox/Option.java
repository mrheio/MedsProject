package model.combobox;

import model.roles.Person;

abstract public class Option {

    protected String option;

    public Option(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void action(Person person) {
    }

    @Override
    public String toString() {
        return option;
    }
}
