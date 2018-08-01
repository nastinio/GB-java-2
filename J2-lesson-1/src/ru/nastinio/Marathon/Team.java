package ru.nastinio.Marathon;

public class Team {
    private String name;
    public Competitor[] listParticipant  = {new Human("Василий"), new Human("Гоша"), new Cat("Васька"), new Dog("Шарик")};


    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void infoSuccess() {
        System.out.println("------------------------------------");
        System.out.println("Список участников, прошедших марафон\n");
        for (Competitor comp : listParticipant) {
            if (comp.isOnDistance()){
                comp.info();
            }
        }
        System.out.println("------------------------------------");

    }

    public void infoAllCompetitor() {
        System.out.println("------------------------------------");
        System.out.println("Список всех участников команды\n");
        for (Competitor comp : listParticipant) {
            comp.info();

        }
        System.out.println("------------------------------------");
    }
}
