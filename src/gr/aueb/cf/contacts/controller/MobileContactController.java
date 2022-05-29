package gr.aueb.cf.contacts.controller;

import gr.aueb.cf.contacts.dto.MobileContactDTO;
import gr.aueb.cf.contacts.model.MobileContact;
import gr.aueb.cf.contacts.service.MobileContactServiceImpl;

public class MobileContactController {

    // Service instance composition
    MobileContactServiceImpl service = new MobileContactServiceImpl();

    public boolean insertController(String... fields) {
        boolean response;

        if (fields == null) return false;
        // get user input and  map to dto
        MobileContactDTO dto = constructDTOFromInput(fields);

        // call the service layer and get the response
        response = service.insertContact(dto);

        return response;
    }

    public MobileContact getContactController(String phoneNumber) {
        MobileContact contact;

        if (phoneNumber == null) return null;
        MobileContactDTO dto = constructDTOFromInput("", "",phoneNumber);

        contact = service.getContactOrNull(dto);

        return contact;
    }

    public boolean updateController(String phoneNumber, String... fields) {
        boolean response;

        if ((phoneNumber == null) || (fields == null)) return false;
        // Get input from user and construct dto
        MobileContactDTO dto = constructDTOFromInput(fields);

        // call the service layer
        response = service.updateContact(phoneNumber, dto);

        return response;
    }

    public boolean deleteController(String phoneNumber) {
        boolean response;
        if (phoneNumber == null) return false;

        response = service.deleteContact(phoneNumber);

        return response;
    }

    private MobileContactDTO constructDTOFromInput(String... inputFields) {
        MobileContactDTO dto = new MobileContactDTO();

        dto.setFirstname(inputFields[0]);
        dto.setLastname(inputFields[1]);
        dto.setPhoneNumber(inputFields[2]);

        return dto;
    }
}
