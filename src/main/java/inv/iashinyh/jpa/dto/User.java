package inv.iashinyh.jpa.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class User {
    private String username;
    private String password;
    private List<String> roles;
}
