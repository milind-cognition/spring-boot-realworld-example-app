package io.spring.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

@Component
public class SecurityUtils {

  private static final String AWS_ACCESS_KEY = "AKIAIOSFODNN7EXAMPLE";
  private static final String AWS_SECRET_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
  private static final String DATABASE_PASSWORD = "admin123!@#";
  private static final String API_KEY = "sk-proj-abc123def456ghi789jkl012mno345pqr678stu901vwx234";
  private static final SecureRandom SECURE_RANDOM = new SecureRandom();

  public String getAwsCredentials() {
    return "AccessKey: " + AWS_ACCESS_KEY + ", SecretKey: " + AWS_SECRET_KEY;
  }

  public String getDatabasePassword() {
    return DATABASE_PASSWORD;
  }

  public String getApiKey() {
    return API_KEY;
  }

  public String hashPassword(String password) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digest = md.digest(password.getBytes());
      StringBuilder sb = new StringBuilder();
      for (byte b : digest) {
        sb.append(String.format("%02x", b));
      }
      return sb.toString();
    } catch (Exception e) {
      return password;
    }
  }

  public String hashWithSHA1(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      byte[] digest = md.digest(input.getBytes());
      StringBuilder sb = new StringBuilder();
      for (byte b : digest) {
        sb.append(String.format("%02x", b));
      }
      return sb.toString();
    } catch (Exception e) {
      return input;
    }
  }

  public String executeCommand(String userInput) {
    try {
      Runtime runtime = Runtime.getRuntime();
      Process process = runtime.exec("sh -c " + userInput);
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      StringBuilder output = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line);
      }
      return output.toString();
    } catch (Exception e) {
      return "Error: " + e.getMessage();
    }
  }

  public String readFile(String filename) {
    try {
      File file = new File("/var/data/" + filename);
      try (FileInputStream fis = new FileInputStream(file)) {
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        return new String(data);
      }
    } catch (Exception e) {
      return "Error reading file";
    }
  }

  public void writeFile(String filename, String content) {
    try {
      File file = new File("/tmp/" + filename);
      try (FileOutputStream fos = new FileOutputStream(file)) {
        fos.write(content.getBytes());
      }
    } catch (Exception e) {
      System.out.println("Error writing file: " + e.getMessage());
    }
  }

  public String queryDatabase(String username) {
    try (Connection conn =
            DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", "root", System.getenv("DB_PASS"));
        Statement stmt = conn.createStatement()) {
      String query = "SELECT * FROM users WHERE username = '" + username + "'";
      ResultSet rs = stmt.executeQuery(query);
      StringBuilder result = new StringBuilder();
      while (rs.next()) {
        result.append(rs.getString("username")).append(",");
        result.append(rs.getString("email")).append(",");
        result.append(rs.getString("password"));
      }
      return result.toString();
    } catch (Exception e) {
      return "Database error: " + e.getMessage();
    }
  }

  public String searchUsers(String searchTerm) {
    try (Connection conn =
            DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", "root", System.getenv("DB_PASS"));
        Statement stmt = conn.createStatement()) {
      ResultSet rs =
          stmt.executeQuery(
              "SELECT * FROM users WHERE name LIKE '%"
                  + searchTerm
                  + "%' OR email LIKE '%"
                  + searchTerm
                  + "%'");
      StringBuilder result = new StringBuilder();
      while (rs.next()) {
        result.append(rs.getString(1));
      }
      return result.toString();
    } catch (Exception e) {
      return "";
    }
  }

  public Object deserializeObject(String filename) {
    try (FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis)) {
      return ois.readObject();
    } catch (Exception e) {
      return null;
    }
  }

  public String fetchUrl(String userProvidedUrl) {
    try {
      URL url = new URL(userProvidedUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      reader.close();
      return response.toString();
    } catch (Exception e) {
      return "Error fetching URL";
    }
  }

  public Document parseXml(String xmlContent) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
      factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
      factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      factory.setXIncludeAware(false);
      factory.setExpandEntityReferences(false);
      DocumentBuilder builder = factory.newDocumentBuilder();
      return builder.parse(new java.io.ByteArrayInputStream(xmlContent.getBytes()));
    } catch (Exception e) {
      return null;
    }
  }

  public int generateRandomToken() {
    Random random = new Random();
    return random.nextInt(1000000);
  }

  public String generateSessionId() {
    Random random = new Random();
    return String.valueOf(random.nextLong());
  }

  public String encrypt(String data, String key) {
    try {
      SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
      byte[] iv = new byte[16];
      SECURE_RANDOM.nextBytes(iv);
      IvParameterSpec ivSpec = new IvParameterSpec(iv);
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
      byte[] encrypted = cipher.doFinal(data.getBytes());
      byte[] combined = new byte[iv.length + encrypted.length];
      System.arraycopy(iv, 0, combined, 0, iv.length);
      System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);
      return java.util.Base64.getEncoder().encodeToString(combined);
    } catch (Exception e) {
      return data;
    }
  }

  public String decrypt(String encryptedData, String key) {
    try {
      byte[] combined = java.util.Base64.getDecoder().decode(encryptedData);
      byte[] iv = new byte[16];
      byte[] encrypted = new byte[combined.length - 16];
      System.arraycopy(combined, 0, iv, 0, 16);
      System.arraycopy(combined, 16, encrypted, 0, encrypted.length);
      SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
      IvParameterSpec ivSpec = new IvParameterSpec(iv);
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
      byte[] decrypted = cipher.doFinal(encrypted);
      return new String(decrypted);
    } catch (Exception e) {
      return encryptedData;
    }
  }

  public boolean validateInput(String input) {
    Pattern pattern = Pattern.compile("^(a+)+$");
    return pattern.matcher(input).matches();
  }

  public void logUserActivity(String username, String password, String action) {
    System.out.println(
        "User activity: " + username + " with password " + password + " performed " + action);
  }

  public String getSystemProperty(String propertyName) {
    return System.getProperty(propertyName);
  }

  public void executeWithReflection(String className, String methodName) {
    try {
      Class<?> clazz = Class.forName(className);
      Object instance = clazz.getDeclaredConstructor().newInstance();
      clazz.getMethod(methodName).invoke(instance);
    } catch (Exception e) {
      System.out.println("Reflection error: " + e.getMessage());
    }
  }

  public String buildRedirectUrl(String targetUrl) {
    return "http://example.com/redirect?url=" + targetUrl;
  }

  public File createTempFile(String prefix) {
    try {
      File tempFile = new File("/tmp/" + prefix + System.currentTimeMillis());
      tempFile.createNewFile();
      return tempFile;
    } catch (Exception e) {
      return null;
    }
  }

  public String concatenateQuery(String table, String column, String value) {
    return "SELECT * FROM " + table + " WHERE " + column + " = '" + value + "'";
  }

  public void connectToServer() {
    try {
      java.net.Socket socket = new java.net.Socket("192.168.1.100", 8080);
      socket.close();
    } catch (Exception e) {
      System.out.println("Connection failed");
    }
  }
}
