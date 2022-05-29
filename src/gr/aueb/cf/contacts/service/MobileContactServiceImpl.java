package gr.aueb.cf.contacts.service;

import gr.aueb.cf.contacts.dao.MobileContactDAOImpl;
import gr.aueb.cf.contacts.dto.MobileContactDTO;
import gr.aueb.cf.contacts.model.MobileContact;

public class MobileContactServiceImpl {

    // DAO Composition
    MobileContactDAOImpl dao = MobileContactDAOImpl.getInstance();

    public MobileContact getContactOrNull(MobileContactDTO dto) {
        if ((dto == null) || (dto.getPhoneNumber() == null)) return null;

        //return dao.getOneContactPossibleNull(dto.getPhoneNumber());
        //Extract phone number from dto
        String phoneNumber = dto.getPhoneNumber();

        //forward to dao and return
        return dao.getOneContactPossibleNull(phoneNumber);
    }

    public boolean insertContact(MobileContactDTO dto) {
        if (dto == null) return false;

        MobileContact contact = new MobileContact();
        extractFields(dto, contact);
        return dao.insert(contact);
    }

    public boolean updateContact(String phoneNumber, MobileContactDTO dto) {
        if ((dto == null) || (phoneNumber ==null)) return false;

        MobileContact contact = new MobileContact();
        extractFields(dto,contact);
        return dao.update(phoneNumber, contact);
    }

    public boolean deleteContact(String phoneNumber) {
        if (phoneNumber == null) return false;
        return dao.delete(phoneNumber);
    }

    private void extractFields(MobileContactDTO dto, MobileContact contact) {
        contact.setFirstname(dto.getFirstname());
        contact.setLastname(dto.getLastname());
        contact.setPhoneNumber(dto.getPhoneNumber());
    }
}
