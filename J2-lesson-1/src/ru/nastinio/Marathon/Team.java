package ru.nastinio.Marathon;

public class Team {
    private String name;
    public Competitor[] listParticipant;

    public Team(String name, Competitor[] listParticipant) {
        this.name = name;
        this.listParticipant = listParticipant;
    }

    public String getName() {
        return name;
    }

    public void infoSuccess() {
        System.out.println("------------------------------------");
        System.out.println("Список участников, прошедших марафон\n");
        for (Competitor comp : listParticipant) {
            if (comp.isOnDistance()) {
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
