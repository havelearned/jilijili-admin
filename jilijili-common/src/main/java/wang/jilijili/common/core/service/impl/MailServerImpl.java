package wang.jilijili.common.core.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import wang.jilijili.common.core.service.MailServer;

import java.io.File;

/**
 * 邮件服务
 *
 * @author Amani
 * @date 2023年06月01日 20:40
 */
@Slf4j
//@Component
@Service
public class MailServerImpl implements MailServer {

    @Value(value = "${spring.mail.username}")
    private String username;
    private final String nickname = "JiliMail@工作室";
    //邮件发送
    JavaMailSenderImpl mailSender;

    @Autowired
    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 发送文本
     *
     * @param subject     主题
     * @param content     内容
     * @param toWho       需要发送的人
     * @param ccPeoples   需要抄送的人
     * @param bccPeoples  需要密送的人
     * @param attachments 需要附带的附件流（key：附件名称，value：附件流）
     */
    @Override
    public void sendSimpleTextMailActual(String subject, String content, String[] toWho, String[] ccPeoples, String[] bccPeoples, String[] attachments) {
        //检验参数：邮件主题、收件人、邮件内容必须不为空才能够保证基本的逻辑执行
        if (subject == null || toWho == null || toWho.length == 0 || content == null) {
            log.error("邮件-> {} 无法继续执行，因为缺少基本的参数：邮件主题、收件人、邮件内容", subject);
            throw new RuntimeException("模板邮件无法继续发送，因为缺少必要的参数！");
        }
        log.info("开始发送简单文本邮件：主题->{}，收件人->{}，抄送人->{}，密送人->{}，附件->{}", subject, toWho, ccPeoples, bccPeoples, attachments);
        //附件处理，需要处理附件时，需要使用二进制信息，使用 MimeMessage 类来进行处理
        if (attachments != null && attachments.length > 0) {
            try {
                //附件处理需要进行二进制传输
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                //设置邮件的基本信息：这些函数都会在后面列出来
                boolean continueProcess = handleBasicInfo(helper, subject, content, toWho, ccPeoples, bccPeoples, false);
                //如果处理基本信息出现错误
                if (!continueProcess) {
                    log.error("邮件基本信息出错: 主题->{}", subject);
                    return;
                }
                //处理附件
                handleAttachment(helper, subject, attachments);
                //发送该邮件
                mailSender.send(mimeMessage);
                log.info("发送邮件成功: 主题->{}", subject);
            } catch (MessagingException e) {
                e.printStackTrace();
                log.error("发送邮件失败: 主题->{}", subject);
            }
        } else {
            //创建一个简单邮件信息对象
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //设置邮件的基本信息
            handleBasicInfo(simpleMailMessage, subject, content, toWho, ccPeoples, bccPeoples);
            //发送邮件
            mailSender.send(simpleMailMessage);
            log.info("发送邮件成功: 主题->{}", subject, toWho, ccPeoples, bccPeoples, attachments);
        }
    }

    /**
     * 发送Html
     *
     * @param subject 主题
     * @param content 内容
     * @param toWho   需要发送的人
     */
    @Override
    public void sendHtmlMail(String subject, String content, String[] toWho) {
        //检验参数：邮件主题、收件人、邮件内容必须不为空才能够保证基本的逻辑执行
        if (subject == null || toWho == null || toWho.length == 0 || content == null) {
            log.error("邮件-> {} 无法继续执行，因为缺少基本的参数：邮件主题、收件人、邮件内容", subject);
            throw new RuntimeException("模板邮件无法继续发送，因为缺少必要的参数！");
        }
        log.info("开始发送Html邮件：主题->{}，收件人->{}", subject, toWho);
        //html
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            //设置邮件的基本信息
            handleBasicInfo(helper, subject, content, toWho);
            //发送邮件
            mailSender.send(mimeMessage);
            log.info("html邮件发送成功");
        } catch (MessagingException e) {
            log.error("发送邮件出错->{}", subject);
        }
        log.info("发送邮件成功: 主题->{}", subject, toWho);
    }

    /**
     * 处理二进制邮件的基本信息，比如需要带附件的文本邮件、HTML文件、图片邮件、模板邮件等等
     *
     * @param mimeMessageHelper：二进制文件的包装类
     * @param subject：邮件主题
     * @param content：邮件内容
     * @param toWho：收件人
     * @param ccPeoples：抄送人
     * @param bccPeoples：暗送人
     * @param isHtml：是否是HTML文件，用于区分带附件的简单文本邮件和真正的HTML文件
     * @return ：返回这个过程中是否出现异常，当出现异常时会取消邮件的发送
     */
    @Override
    public boolean handleBasicInfo(MimeMessageHelper mimeMessageHelper, String subject, String content, String[] toWho, String[] ccPeoples, String[] bccPeoples, boolean isHtml) {
        try {
            //设置必要的邮件元素
            //设置发件人
            mimeMessageHelper.setFrom(nickname + '<' + username + '>');
            //设置邮件的主题
            mimeMessageHelper.setSubject(subject);
            //设置邮件的内容，区别是否是HTML邮件
            mimeMessageHelper.setText(content, isHtml);
            //设置邮件的收件人
            mimeMessageHelper.setTo(toWho);
            //设置非必要的邮件元素，在使用helper进行封装时，这些数据都不能够为空
            if (ccPeoples != null)
                //设置邮件的抄送人：MimeMessageHelper # Assert.notNull(cc, "Cc address array must not be null");
                mimeMessageHelper.setCc(ccPeoples);
            if (bccPeoples != null)
                //设置邮件的密送人：MimeMessageHelper # Assert.notNull(bcc, "Bcc address array must not be null");
                mimeMessageHelper.setBcc(bccPeoples);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("邮件基本信息出错->{}", subject);
        }
        return false;
    }

    /**
     * 用于填充简单文本邮件的基本信息
     *
     * @param simpleMailMessage：文本邮件信息对象
     * @param subject：邮件主题
     * @param content：邮件内容
     * @param toWho：收件人
     * @param ccPeoples：抄送人
     * @param bccPeoples：暗送人
     */
    @Override
    public void handleBasicInfo(SimpleMailMessage simpleMailMessage, String subject, String content, String[] toWho, String[] ccPeoples, String[] bccPeoples) {
        //设置发件人
        simpleMailMessage.setFrom(nickname + '<' + username + '>');
        //设置邮件的主题
        simpleMailMessage.setSubject(subject);
        //设置邮件的内容
        simpleMailMessage.setText(content);
        //设置邮件的收件人
        simpleMailMessage.setTo(toWho);
        //设置邮件的抄送人
        simpleMailMessage.setCc(ccPeoples);
        //设置邮件的密送人
        simpleMailMessage.setBcc(bccPeoples);
    }

    /**
     * 发送html
     *
     * @param subject：邮件主题
     * @param content：邮件内容
     * @param toWho：收件人
     * @param mimeMessageHelper：二进制文件的包装类
     */
    @Override
    public void handleBasicInfo(MimeMessageHelper mimeMessageHelper, String subject, String content, String[] toWho) {
        try {
            //设置发件人
            mimeMessageHelper.setFrom(nickname + '<' + username + '>');
            //设置邮件的主题
            mimeMessageHelper.setSubject(subject);
            //设置邮件的内容
            mimeMessageHelper.setText(content, true);
            //设置邮件的收件人
            mimeMessageHelper.setTo(toWho);
        } catch (MessagingException e) {
            log.error("html邮件基本信息出错->{}", subject);
        }
    }

    /**
     * 用于处理附件信息，附件需要 MimeMessage 对象
     *
     * @param mimeMessageHelper：处理附件的信息对象
     * @param subject：邮件的主题，用于日志记录
     * @param attachmentFilePaths：附件文件的字节流（key：附件名称，value：附件流）
     */
    @Override
    public void handleAttachment(MimeMessageHelper mimeMessageHelper, String subject, String[] attachmentFilePaths) {
        //判断是否需要处理邮件的附件
        if (attachmentFilePaths != null && attachmentFilePaths.length > 0) {
            FileSystemResource resource;
            String fileName;
            //循环处理邮件的附件
            for (String attachmentFilePath : attachmentFilePaths) {
                //获取该路径所对应的文件资源对象
                resource = new FileSystemResource(new File(attachmentFilePath));
                //判断该资源是否存在，当不存在时仅仅会打印一条警告日志，不会中断处理程序。
                // 也就是说在附件出现异常的情况下，邮件是可以正常发送的，所以请确定你发送的邮件附件在本机存在
                if (!resource.exists()) {
                    log.warn("邮件->{} 的附件->{} 不存在！", subject, attachmentFilePath);
                    //开启下一个资源的处理
                    continue;
                }
                //获取资源的名称
                fileName = resource.getFilename();
                try {
                    //添加附件
                    mimeMessageHelper.addAttachment(fileName, resource);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    log.error("邮件->{} 添加附件->{} 出现异常->{}", subject, attachmentFilePath, e.getMessage());
                }
            }
        }
    }
}
