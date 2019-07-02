package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class SecurityUtilTest {

    @Test
    public void encodeForBase64() {
        System.out.println(SecurityUtil.encodeForBase64("winn"));
    }

    @Test
    public void decodeForBase64() {
        System.out.println(SecurityUtil.decodeForBase64("d2lubg=="));
    }

    @Test
    public void MD5_16() {
        System.out.println(SecurityUtil.MD5_16("winn"));
    }

    @Test
    public void MD5_32() {
        System.out.println(SecurityUtil.MD5_32("winn"));
    }

    @Test
    public void generateKey() {
        System.out.println(SecurityUtil.generateKey());
    }

    @Test
    public void encrypt() {
        System.out.println(SecurityUtil.encrypt("This is security test.","0ebM100T0NgUsiiGH7rNmA=="));
    }

    @Test
    public void decrypt() {
        String decrypt = SecurityUtil.decrypt("/XiUHb4BnZIJ9H2p3hGMsfnmbkQJXMcW2sPPZeXZts0=", "0ebM100T0NgUsiiGH7rNmA==");
        System.out.println(decrypt);
    }
}