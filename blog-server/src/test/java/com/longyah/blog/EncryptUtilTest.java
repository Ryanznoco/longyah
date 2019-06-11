package com.longyah.blog;

import com.longyah.blog.utils.EncryptUtil;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
public class EncryptUtilTest {

    @Test
    public void testEncryptPassword() {
        String password = "810625938";
        String encrypted = EncryptUtil.encryptPassword(password, 8);
        System.out.println(encrypted);
        assertTrue(EncryptUtil.validatePassword(password, encrypted));
    }
}
