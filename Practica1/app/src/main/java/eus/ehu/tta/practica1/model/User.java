package eus.ehu.tta.practica1.model;

/**
 * Created by edwin on 15/01/18.
 */

public class User {
    private int id;
    private String user;
    private int lessonNumber;
    private String lessonTittle;
    private int nextTest;
    private int nextExercise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getLessonTittle() {
        return lessonTittle;
    }

    public void setLessonTittle(String lessonTittle) {
        this.lessonTittle = lessonTittle;
    }

    public int getNextTest() {
        return nextTest;
    }

    public void setNextTest(int nextTest) {
        this.nextTest = nextTest;
    }

    public int getNextExercise() {
        return nextExercise;
    }

    public void setNextExercise(int nextExercise) {
        this.nextExercise = nextExercise;
    }

}
