package com.dubbo.consumer.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dubbo.provider.front.DemoService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2014年11月20日 <br>
 * Copyright (c) 2014 AILK <br>
 * @category dubbo客户端UI测试
 * @author xuanjian
 */
public class DubboClientUI extends JFrame {

    private static final long serialVersionUID = 8355327806498876311L;

    private JPanel contentPane;

    public static final String CRM_DUBBO_CONSUMER_XML = "applicationConsumer.xml";
    public static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { CRM_DUBBO_CONSUMER_XML });

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        context.registerShutdownHook();
        context.start();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DubboClientUI frame = new DubboClientUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public DubboClientUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JButton btnNewButton = new JButton("New button");
        contentPane.add(btnNewButton, BorderLayout.SOUTH);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DemoService iDemoService = (DemoService) context.getBean("demoService");
                iDemoService.sayHello();
                System.out.println(iDemoService.retHello());
            }
        });
    }

}
