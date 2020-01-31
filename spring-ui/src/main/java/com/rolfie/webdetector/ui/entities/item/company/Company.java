package com.rolfie.webdetector.ui.entities.item.company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Company {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String url;

    public Company() {
    }

    public Company(String name,
                   String url) {

        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) &&
                Objects.equals(name, company.name) &&
                Objects.equals(url, company.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", firstName='" + name + '\'' +
                ", lastName='" + url + '\'' +
                '}';
    }
}
