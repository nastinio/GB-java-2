package ru.nastinio.Marathon;

public class Course {
    private Obstacle[] course = {new Cross(80), new Wall(5), new Water(3)};

    public void doiIt(Team team) {
        System.out.println("Команда "+team.getName()+" приступает к марафону\n");
        for (Obstacle obstacle : course) {
            for (Competitor comp : team.listParticipant) {
                obstacle.doIt(comp);
            }
            System.out.println("------------------------------------");
        }
    }
}
