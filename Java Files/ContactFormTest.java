/*
 * Copyright (c) 2024. This Project is free to use(You may sell,duplicate and edit).
 * Visit my Youtube channel @ZimCoding and enjoy more free projects like this cheers...
 */

package org.zimcoding.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.vaadin.example.Entities.Company;
import org.vaadin.example.Entities.Contact;
import org.vaadin.example.Entities.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ContactFormTest {
    private List<Company> companies;
    private List<Status> statuses;
    private Contact marcUsher;
    private Company company1;
    private Company company2;
    private Status status1;
    private Status status2;

    @BeforeEach
    public void setupData() {
        companies = new ArrayList<>();
        company1 = new Company();
        company1.setName("Vaadin Ltd");
        company2 = new Company();
        company2.setName("IT Mill");
        companies.add(company1);
        companies.add(company2);

        statuses = new ArrayList<>();
        status1 = new Status();
        status1.setName("Status 1");
        status2 = new Status();
        status2.setName("Status 2");
        statuses.add(status1);
        statuses.add(status2);

        marcUsher = new Contact();
        marcUsher.setFirstName("Marc");
        marcUsher.setLastName("Usher");
        marcUsher.setEmail("marc@usher.com");
        marcUsher.setStatus(status1);
        marcUsher.setCompany(company2);
    }
@Test
public void formFieldsPopulated(){
        ContactForm contactForm = new ContactForm(companies, statuses);
        contactForm.setContact(marcUsher);
        assertEquals("Marc", contactForm.firstName.getValue());
        assertEquals("Usher", contactForm.lastName.getValue());
        assertEquals("marc@usher.com", contactForm.email.getValue());
        assertEquals(company2, contactForm.company.getValue());
        assertEquals(status1, contactForm.status.getValue());
    }
    @Test
    public void saveEventHasCorrectValues(){

        ContactForm contactForm = new ContactForm(companies, statuses);
        Contact contact = new Contact();
        contactForm.setContact(contact);

        contactForm.firstName.setValue("Josy");
        contactForm.lastName.setValue("Julie");
        contactForm.email.setValue("josyj@mail.co.zw");
        contactForm.company.setValue(company1);
        contactForm.status.setValue(status2);

        AtomicReference<Contact> savedContact = new AtomicReference<>(null);
        contactForm.addSaveListener(e -> savedContact.set(e.getContact()));
        contactForm.save.click();

        Contact saved = savedContact.get();
        assertEquals("Josy",saved.getFirstName());
        assertEquals("Julie",saved.getLastName());
        assertEquals("josyj@mail.co.zw",saved.getEmail());
        assertEquals(company1,saved.getCompany());
        assertEquals(status2,saved.getStatus());


    }
}
