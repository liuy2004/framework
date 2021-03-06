/*
 * Copyright (c) 2017. CK. All rights reserved.
 */

package com.github.fartherp.framework.security.dissymmetry;

import base64.Base64Utils;

/**
 * <pre>
 *     公钥: MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjjH+39EX9iMFNWte6NZ35G5FccK0qnnLJaRpoHYuf9RLJBZwCqMlkJXly1qbZETJprmDEVCJVLE3+MHooSW6yfAGkGepmCjnC8u7yu/WeuPedYM3Zie7X3u9wt4JDS+fZdPD9Lyvls+nfIiVBd6aP3nl+0DQ6MtYLwXLIqcQlWwIDAQAB
 *     私钥: MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKOMf7f0Rf2IwU1a17o1nfkbkVxwrSqecslpGmgdi5/1EskFnAKoyWQleXLWptkRMmmuYMRUIlUsTf4weihJbrJ8AaQZ6mYKOcLy7vK79Z64951gzdmJ7tfe73C3gkNL59l08P0vK+Wz6d8iJUF3po/eeX7QNDoy1gvBcsipxCVbAgMBAAECgYAdLhs3igjW1IiQsa2d1qUp5gZIdP4ghYa7cto4S1U3I/XHEg4k11KfYKRLngrE3fGds3Qmc1Bw2+fKe4HAIGH0g2g2PkZGGi5X6Wsvj+s2/jhbGsKEUG1kfzrtEkQ8o/TFIxyHjeNpbdkrpGe/LjQzLF/31q+RXaT8r3vmNMqtGQJBANMjUy2Afus8olQpRaOpImeRkVxBA0hifobyILr2r9sHB2r49QAz4umR0V9HVEB8YPrysk8JjP+z5eaxdKTjuoUCQQDGTJncngA+qKkWVIJmgcxOnEqyN5qpz6xIyUPgDhtOsYzsh40Ad8Ypaf7iCN5lC4vFIqdQr4v1+i9jxf+PmJZfAkEAh9uBGBRIlqErarAr6UVZp/7Y493JePg3pimCugsl2O/K7CBJtdmtNtHnQUGqitv9ozwrVAQ3QAnH8wtYnQkT6QJBALs+O47ZVsvTwI3IULVwXLXh2Jp97Lnp0fmMql3XlCtzdekmeZr2fhQ+9SEEVNM+2sTT2v9SM6uZiMQCgzVZVKsCQQDMSFA9Bxj5DhvPfrlfIWaaORO5HMxMDcdOSckA/2lSNXmLMNfK8MJTIw03lUb10K35XN7i5pXN1JFAi+EjQ7Qc
 *     签名: T/MO63tceKxJQBhcIJuwHTBupKS0K0U21V+U1sS1LTU/IK+/VQ59cGnHMEAa89k55d5QMki+d/1661eJ1svfQK0Vk0FqMOaCtzl6htJrixjtN0xR/MH/phgAdg5OAmEz4MsovnG8aSRY0dK2wsQcuCfxJt7AykMXRC795YKiv+E=
 *     状态: true
 *     源数据: abc
 *     加密数据: Qc0Dvqxe1KsNWL7XA5aDXJwg0WmyTCPs1bFnv7+ijR7CZeVW1b9GhEYtFIkCEOwqLyfBEOxT6D+FO4iYVJvtuOJPwyOppGQxcp9cDWozNI8pdBUa4WmyJ3pYfFFSyzErP8srT47jJseBbwpEL4Az6p6+T4LQb7VLBiszYQGn8G8=
 *     解密数据: abc
 * </pre>
 * Author: CK
 * Date: 2015/4/13
 */
public class RSATest {
    public static void main(String[] args) throws Exception {
        String inputStr = "abc";

        // 构建密钥
        DisSymmetryKey disSymmetryKey = RSA.initKey();

        // 获得密钥
        byte[] publicKey = disSymmetryKey.getPublicKey();
        byte[] privateKey = disSymmetryKey.getPrivateKey();

        System.out.println("公钥: " + Base64Utils.encode(publicKey));
        System.out.println("私钥: " + Base64Utils.encode(privateKey));

        // 产生签名
        byte[] data = inputStr.getBytes();
        byte[] sign = RSA.sign(data, privateKey);
        System.out.println("签名: " + Base64Utils.encode(sign));

        // 验证签名
        boolean status = RSA.verify(data, publicKey, sign);
        System.out.println("状态: " + status);
        byte[] encryptData = RSA.encryptByPublicKey(data, publicKey);
        System.out.println("源数据: " + inputStr);
        System.out.println("加密数据: " + Base64Utils.encode(encryptData));
        System.out.println("解密数据: " + new String(RSA.decryptByPrivateKey(encryptData, privateKey)));


    }
}
