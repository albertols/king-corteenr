package com.uem.theory.exercises;

public class U4Java {

    private Integer myInt = 4;

    private Integer myMethod (Integer x) {
        return x +1;
    }


    private String nameAlbum;
    private Integer releaseYear;

    public U4Java (String nameAlbum, Integer releaseYear) {
        this.nameAlbum = nameAlbum;
        this.releaseYear = releaseYear;
    }


    @Override
    public String toString() {
        return "U4Java{" +
                "myInt=" + myInt +
                ", nameAlbum='" + nameAlbum + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }

    public static void main(String[] args) {
        U4Java myNewAlbum = new U4Java("U2: JoshuaTree", 1987);
        System.out.println(myNewAlbum);

    }

}
