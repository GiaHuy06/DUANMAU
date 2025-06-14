package poly.cafe.util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class XMailer {

    private static final String USERNAME = "huy64443@gmail.com"; // Email người gửi
    private static final String PASSWORD = "gyajnwrfeukqkeks";   // App password từ Gmail

    public static void send(String toEmail, String username, String otpCode) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

            // HTML nội dung
            String htmlContent = """
<html>
  <body style="margin:0; padding:0; font-family: 'Segoe UI', sans-serif; background-color: #f4f4f4;">
    <table width="100%%" cellpadding="0" cellspacing="0">
      <tr>
        <td align="center">
          <table width="600" cellpadding="0" cellspacing="0" style="background-color: #ffffff; margin-top: 40px; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 12px rgba(0,0,0,0.1);">
            <tr>
              <td style="background-color: #2c3e50; padding: 20px; text-align: center;">
                <h2 style="color: #ffffff; margin: 0;">☕ Poly Café</h2>
              </td>
            </tr>
            <tr>
              <td style="padding: 30px; color: #333333;">
                <p style="font-size: 16px;">Xin chào <strong>%s</strong>,</p>
                <p style="font-size: 15px;">Chúng tôi đã nhận được yêu cầu khôi phục mật khẩu từ bạn.</p>
                <p style="font-size: 15px;">Dưới đây là mã OTP dùng để xác minh:</p>

                <div style="text-align: center; margin: 30px 0;">
                  <span style="display: inline-block; background-color: #27ae60; color: #ffffff; font-size: 24px; padding: 12px 30px; border-radius: 6px; letter-spacing: 4px; font-weight: bold;">
                    %s
                  </span>
                </div>

                <p style="font-size: 14px; color: #888;">Mã OTP có hiệu lực trong vòng <strong>5 phút</strong>. Nếu bạn không yêu cầu, hãy bỏ qua email này.</p>

                <hr style="margin: 30px 0; border: none; border-top: 1px solid #ddd;" />
                <p style="color: gray; font-size: 12px; margin-top: 30px; text-align: center;">Email được gửi tự động. Vui lòng không trả lời thư này.</p>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </body>
</html>
""".formatted(username, otpCode);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Khôi phục mật khẩu - Poly Café");

            // Cấu hình gửi HTML
            message.setContent(htmlContent, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("✅ Gửi email thành công đến " + toEmail);
        } catch (MessagingException e) {
            throw new RuntimeException("❌ Gửi email thất bại: " + e.getMessage());
        }
    }

}
