package practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PhoneBook {
    private HashMap<String, String> hashMap;

    public PhoneBook() {
        hashMap = new HashMap<>();
    }

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // (рекомедуется написать отдельные методы для проверки является строка именем/телефоном)
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if (!isName(name) || !isPhone(phone)) {
            return;
        }
        if (hashMap.containsKey(phone)) {
            hashMap.remove(phone);
        }
        hashMap.put(phone, name);
    }

    public String getContactByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        if (hashMap.containsKey(phone)) {
            String name = hashMap.get(phone);
            return name + " - " + phone;
        }
        return "";
    }

    public Set<String> getContactByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        String contact = "";
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            if (entry.getValue().equals(name)) {
                contact = name + " - " + entry.getKey();
                break;
            }
        }
        TreeSet<String> set = new TreeSet<>();
        if (!contact.isEmpty()) {
            set.add(contact);
        }
        return set;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        HashMap<String, String> nameMap = new HashMap<>();  // <name, phone>
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String phone = entry.getKey();
            String name = entry.getValue();
            if (nameMap.containsKey(name)) {
                phone = nameMap.get(name) + ", " + phone;
                nameMap.remove(name);
                nameMap.put(name, phone);
            } else {
                nameMap.put(name, phone);
            }
        }
        TreeSet<String> set = new TreeSet<>();
        for (Map.Entry<String, String> entry : nameMap.entrySet()) {
            set.add(entry.getKey() + " - " + entry.getValue());
        }
        return set;
    }

    private boolean isName(String name) {
        return name.matches("^[А-ЯЁ][а-яё]+$");
    }

    private boolean isPhone(String phone) {
        return phone.matches("^79\\d{9}$");
    }
}
