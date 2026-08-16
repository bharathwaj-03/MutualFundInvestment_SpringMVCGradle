package com.crimsonlogic.mutualfundinvestmentspringmvc.model.user;

import java.util.Objects;

public class Nominee {

    private String nomineeId;
    private String name;
    private int age;
    private String gender;
    private String relationship;

    public Nominee() {
    }

    public Nominee(String nomineeId,
                   String name,
                   int age,
                   String gender,
                   String relationship) {

        this.nomineeId = nomineeId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.relationship = relationship;
    }

    public String getNomineeId() {
        return nomineeId;
    }

    public void setNomineeId(String nomineeId) {
        this.nomineeId = nomineeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "Nominee{" +
                "nomineeId='" + nomineeId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", relationship='" + relationship + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Nominee nominee = (Nominee) o;

        return age == nominee.age &&
                Objects.equals(nomineeId, nominee.nomineeId) &&
                Objects.equals(name, nominee.name) &&
                Objects.equals(gender, nominee.gender) &&
                Objects.equals(relationship, nominee.relationship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                nomineeId,
                name,
                age,
                gender,
                relationship
        );
    }
}