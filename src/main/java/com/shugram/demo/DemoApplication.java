package com.shugram.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.shugram.demo.mapper")
//@ServletComponentScan(basePackages = "com.shugram.demo.jwt")
public class DemoApplication {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    @Value("${backup.email}")
    private String to;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    public void backupDatabase(){
        String fileName = "backup-" + LocalDate.now().toString() + ".sql";
        String url = "jdbc:h2:file:/path/to/database";
        String user = "sa";
        String password = "sa";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("BACKUP TO '"+fileName+"'");
            stmt.close();
            conn.close();
            sendEmail(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendEmail(String fileName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("Database Backup");
            helper.setText("Please find the attached SQL file.");
            FileSystemResource file = new FileSystemResource(fileName);
            helper.addAttachment(file.getFilename(), file);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
