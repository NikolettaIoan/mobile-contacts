package gr.aueb.cf.contacts.dao;

import gr.aueb.cf.contacts.model.MobileContact;

public class MobileContactDAOImpl {

    private static final MobileContactDAOImpl DAO = new MobileContactDAOImpl();
    private static final int ARR_SIZE = 500;

    private final MobileContact[] contacts = new MobileContact[ARR_SIZE];
    private int pivot = -1;

    // This class should not be directly instantiated
    private MobileContactDAOImpl() {}
    public static MobileContactDAOImpl getInstance() {
        return DAO;
    }

    public MobileContact getOneContactPossibleNull(String phoneNumber) {
        int position = getPosition(phoneNumber);
        return (position != -1) ? contacts[position] : null;
    }

    public boolean insert(MobileContact contact) {
        if (contact == null) return false;
        int position = getPosition(contact.getPhoneNumber());

        // If contact already exists
        if (position != -1) return false;

        // If contact not exists then do insert
        contacts[++pivot] = contact;
        return true;
    }

    public boolean update(String phoneNumber, MobileContact newContact) {
        if ((phoneNumber == null) ||(newContact == null)) return false;

        int positionToUpdate = getPosition(phoneNumber);

        // Contact based on phone number not found
        if (positionToUpdate == -1) return false;

        contacts[positionToUpdate] = newContact;
        return true;
    }

    public boolean delete(String phoneNumber) {
        int positionToDelete;

        if (phoneNumber == null) return false;

        positionToDelete = getPosition(phoneNumber);

        //If contact not found
        if (positionToDelete == -1) return false;

        System.arraycopy(contacts, positionToDelete +1, contacts, positionToDelete, pivot - positionToDelete);
        pivot --;
        return true;
    }

    private int getPosition(String phoneNumber) {
        if (phoneNumber == null) return -1;

        for (int i = 0; i <= pivot; i++) {
            if (contacts[i].getPhoneNumber().equals(phoneNumber)) {
                return i;
            }
        }
        return -1;
    }


}
