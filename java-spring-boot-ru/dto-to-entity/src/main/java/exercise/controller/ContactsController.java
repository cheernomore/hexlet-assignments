package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createContact(@RequestBody ContactCreateDTO contactCreateDTO) {
        var newContact = new Contact();

        newContact.setFirstName(contactCreateDTO.getFirstName());
        newContact.setLastName(contactCreateDTO.getLastName());
        newContact.setPhone(contactCreateDTO.getPhone());

        contactRepository.save(newContact);

        return toDto(newContact);
    }

    private ContactDTO toDto(Contact contact) {
        var contactDto = new ContactDTO();

        contactDto.setId(contact.getId());
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setPhone(contact.getPhone());
        contactDto.setCreatedAt(contact.getCreatedAt());
        contactDto.setUpdatedAt(contact.getUpdatedAt());

        return contactDto;
    }
    // END
}
