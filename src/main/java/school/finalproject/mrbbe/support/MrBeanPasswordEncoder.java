package school.finalproject.mrbbe.support;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MrBeanPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence password) {
        return password.toString();
    }

    @Override
    public boolean matches(CharSequence password, String encodeString) {
        return password.toString().equals(encodeString);
    }
}
