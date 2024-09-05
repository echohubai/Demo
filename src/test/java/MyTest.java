
import hubai.LibrarianApplication;
import hubai.service.impl.UserServiceImpl;
import hubai.utils.CaptchaUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest(classes = LibrarianApplication.class)
@RunWith(SpringRunner.class)
public class MyTest {

    @MockBean
    private UserServiceImpl userService;


    @Test
    public void passwordTest(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("1234");
        System.out.println(password);
        boolean matches = passwordEncoder.matches("123", "$2a$10$tuF5tf9QfiIl66bs.0rauuf9rDFqRnRVlH2iowuOZRuISGpV1xUvi");
        System.out.println(matches);
        System.out.println(new Date());
    }
    @Test
    public void getCode(){
        String code = CaptchaUtil.generatedCode(4);
        System.out.println(code);
    }

    @Test
    public void testEmail(){
        Pattern email_pattern=Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+");
        String email="1171043694@qq.com";
        Matcher matcher = email_pattern.matcher(email);
        System.out.println(matcher.matches());
    }
}
