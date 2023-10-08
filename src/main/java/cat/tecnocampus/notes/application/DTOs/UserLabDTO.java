package cat.tecnocampus.notes.application.DTOs;

import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class UserLabDTO {

    private String username;

    @Size(min = 3, message = "size must be greater than 3")
    @Pattern(regexp = "^[A-Z][a-z]* ?[A-Za-z]*$", message = "must begin with a capital letter and contain only letters")
    private String name;

    @Size(min = 3, message = "size must be greater than 3")
    @Pattern(regexp = "^[A-Z][a-z]* ?[A-Za-z]*$", message = "must begin with a capital letter and contain only letters")
    private String secondName;

    @Email
    @NotEmpty
    private String email;

    private final Map<String, NoteLabDTO> ownedNotes;

    public UserLabDTO() {
        ownedNotes = new HashMap<String, NoteLabDTO>();
    }

    public UserLabDTO(String username, String name, String secondName, String email) {
        ownedNotes = new HashMap<>();

        this.username = username;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public List<NoteLabDTO> getOwnedNotes() {
        return new ArrayList<>(ownedNotes.values());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOwnedNotes(List<NoteLabDTO> ownedNotes) {
        this.ownedNotes.clear();
        ownedNotes.forEach(note -> this.ownedNotes.put(note.getTitle(), note));
    }
}