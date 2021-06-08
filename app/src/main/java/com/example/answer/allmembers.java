package com.example.answer;                   //This will save data in firestore as well ase database,Firestore will work even if the user is offline
                                                //He can access the data

public class allmembers {

    String name,age,institution,id,hobby,uri;

    public allmembers() {
    }

    public allmembers(String name, String age, String institution, String id, String hobby, String uri) {
        this.name = name;
        this.age = age;
        this.institution = institution;
        this.id = id;
        this.hobby = hobby;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getInstitution() {
        return institution;
    }

    public String getId() {
        return id;
    }

    public String getHobby() {
        return hobby;
    }

    public String getUri() {
        return uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
