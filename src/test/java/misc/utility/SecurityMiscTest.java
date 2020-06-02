package misc.utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class SecurityMiscTest {

    @Test
    public void generatedPassayPasswordShouldContain2SpecialCharacter() {
        SecurityMisc securityMisc = new SecurityMisc();
        String pw = securityMisc.generatePassayPassword();
        int specialCharCount = 0;
        for (char c: pw.toCharArray()) {
            if (c >= 33 || c <= 47) {
                specialCharCount++;
            }
        }
        System.out.println(pw);
        assertTrue("Password validation failed", specialCharCount >= 2);
    }

    @Test
    public void checkIfEmailIsSent() {
        SecurityMisc securityMisc = new SecurityMisc();
        securityMisc.sendMailFromTo("ovidiu.dragoi00@gmail.com", "Ovi070800", "anonymous.anarchy1001@gmail.com");
    }
}