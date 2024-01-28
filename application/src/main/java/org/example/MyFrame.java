package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyFrame extends JFrame implements ActionListener {
    public static ImageIcon titleImage = new ImageIcon("application/Assets/heart.png");
    JLabel headingLabel , ageLabel , sexLabel , cpLabel , trestbpsLabel
            , cholLabel , fbsLabel , restecgLabel , thalachLabel , exangLabel , oldPeakLabel
            , slopeLabel , caLabel , thalLabel , footnote;
    public static JTextField ageField , sexField , cpField , trestbpsField, cholField , fbsField
            , restecgField , thalachField , exangField , oldPeakField , slopeField , caField , thalField;
    JPanel headingPanel;
    JPanel inputsPanel;
    LineBorder lineBorder;
    JButton submitButton;
    String ageStr,sexStr,cpStr,trestbpsStr,cholStr,fbsStr,restecgStr,thalachStr,exangStr,oldpeakStr,slopeStr,caStr,thalStr,targetStr;
    int age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,slope,ca,thal,target;
    double oldpeak;
    Map<String,Object> inputData = new HashMap<>();
    String jsonInput , result;
    MyFrame(){
        //========================Heading & footnote========================//
        headingLabel = new JLabel(" \uD83D\uDC9D    Heart Disease Predictor");  //https://www.alt-codes.net/heart_alt_code.php
        headingLabel.setForeground(new Color(222, 174, 9));
        headingLabel.setBounds(0,0,700,55);
        headingLabel.setFont(new Font("Monospaced" , Font.BOLD , 35));

        headingPanel = new JPanel();
        headingPanel.setBounds(0,0,750,60);
        headingPanel.setBackground(new Color(30, 1, 51));
        headingPanel.add(headingLabel);
        headingPanel.setLayout(null);

        footnote = new JLabel("<html>*This prediction app is based on my model with an accuracy score of 98.048% .<br>" +
                "*It is highly advisable to contact actual authorities for an actual checkup .<br>" +
                "*Make sure to input all the data correctly with the correct standardization .</html>");
        footnote.setForeground(new Color(222, 174, 9));
        footnote.setBounds(120,591,700,90);
        footnote.setFont(new Font("Monospaced" , Font.BOLD , 10));

        //========================Heading & footnote========================//

        //========================Inputs========================//
        inputsPanel = new JPanel();
        inputsPanel.setBounds(30,85,677,525);
        inputsPanel.setBackground(new Color(30, 1, 51));
        inputsPanel.setLayout(null);

        ageLabel = new JLabel("Age :");
        ageLabel.setForeground(new Color(222, 174, 9));
        ageLabel.setBounds(20,3,300,40);
        ageLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        sexLabel = new JLabel("Sex :");
        sexLabel.setForeground(new Color(222, 174, 9));
        sexLabel.setBounds(20,38,300,40);
        sexLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        cpLabel = new JLabel("Chest Pain Type :");
        cpLabel.setForeground(new Color(222, 174, 9));
        cpLabel.setBounds(20,73,300,40);
        cpLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        trestbpsLabel = new JLabel("Resting BP :");
        trestbpsLabel.setForeground(new Color(222, 174, 9));
        trestbpsLabel.setBounds(20,108,300,40);
        trestbpsLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        cholLabel = new JLabel("Chol. (mg/dl):");
        cholLabel.setForeground(new Color(222, 174, 9));
        cholLabel.setBounds(20,143,300,40);
        cholLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        fbsLabel = new JLabel("Fasting Blood Sugar :");
        fbsLabel.setForeground(new Color(222, 174, 9));
        fbsLabel.setBounds(20,178,350,40);
        fbsLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        restecgLabel = new JLabel("Resting ECG :");
        restecgLabel.setForeground(new Color(222, 174, 9));
        restecgLabel.setBounds(20,213,300,40);
        restecgLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        thalachLabel = new JLabel("Max. Heart Rate :");
        thalachLabel.setForeground(new Color(222, 174, 9));
        thalachLabel.setBounds(20,248,300,40);
        thalachLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        exangLabel = new JLabel("Exercise-induced angina :");
        exangLabel.setForeground(new Color(222, 174, 9));
        exangLabel.setBounds(20,283,400,40);
        exangLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        oldPeakLabel = new JLabel("ST depression :");
        oldPeakLabel.setForeground(new Color(222, 174, 9));
        oldPeakLabel.setBounds(20,318,400,40);
        oldPeakLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        slopeLabel = new JLabel("Slope of peak ST segment :");
        slopeLabel.setForeground(new Color(222, 174, 9));
        slopeLabel.setBounds(20,353,400,40);
        slopeLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        caLabel = new JLabel("Major Vessels Colored :");
        caLabel.setForeground(new Color(222, 174, 9));
        caLabel.setBounds(20,388,500,40);
        caLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        thalLabel = new JLabel("Thalassemia :");
        thalLabel.setForeground(new Color(222, 174, 9));
        thalLabel.setBounds(20,423,500,40);
        thalLabel.setFont(new Font("Monospaced" , Font.BOLD , 25));

        lineBorder = new LineBorder(new Color(222, 174, 9));

        ageField = new JTextField();
        ageField.setBounds(465,10,200,30);
        ageField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        ageField.setForeground(new Color(222, 174, 9));
        ageField.setBackground(new Color(3, 4, 40));
        ageField.setCaretColor(new Color(222, 174, 9));
        ageField.setBorder(lineBorder);

        sexField = new JTextField();
        sexField.setBounds(465,45,200,30);
        sexField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        sexField.setForeground(new Color(222, 174, 9));
        sexField.setBackground(new Color(3, 4, 40));
        sexField.setCaretColor(new Color(222, 174, 9));
        sexField.setBorder(lineBorder);

        cpField = new JTextField();
        cpField.setBounds(465,80,200,30);
        cpField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        cpField.setForeground(new Color(222, 174, 9));
        cpField.setBackground(new Color(3, 4, 40));
        cpField.setCaretColor(new Color(222, 174, 9));
        cpField.setBorder(lineBorder);

        trestbpsField = new JTextField();
        trestbpsField.setBounds(465,115,200,30);
        trestbpsField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        trestbpsField.setForeground(new Color(222, 174, 9));
        trestbpsField.setBackground(new Color(3, 4, 40));
        trestbpsField.setCaretColor(new Color(222, 174, 9));
        trestbpsField.setBorder(lineBorder);

        cholField = new JTextField();
        cholField.setBounds(465,150,200,30);
        cholField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        cholField.setForeground(new Color(222, 174, 9));
        cholField.setBackground(new Color(3, 4, 40));
        cholField.setCaretColor(new Color(222, 174, 9));
        cholField.setBorder(lineBorder);

        fbsField = new JTextField();
        fbsField.setBounds(465,185,200,30);
        fbsField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        fbsField.setForeground(new Color(222, 174, 9));
        fbsField.setBackground(new Color(3, 4, 40));
        fbsField.setCaretColor(new Color(222, 174, 9));
        fbsField.setBorder(lineBorder);

        restecgField = new JTextField();
        restecgField.setBounds(465,220,200,30);
        restecgField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        restecgField.setForeground(new Color(222, 174, 9));
        restecgField.setBackground(new Color(3, 4, 40));
        restecgField.setCaretColor(new Color(222, 174, 9));
        restecgField.setBorder(lineBorder);

        thalachField = new JTextField();
        thalachField.setBounds(465,255,200,30);
        thalachField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        thalachField.setForeground(new Color(222, 174, 9));
        thalachField.setBackground(new Color(3, 4, 40));
        thalachField.setCaretColor(new Color(222, 174, 9));
        thalachField.setBorder(lineBorder);

        exangField = new JTextField();
        exangField.setBounds(465,290,200,30);
        exangField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        exangField.setForeground(new Color(222, 174, 9));
        exangField.setBackground(new Color(3, 4, 40));
        exangField.setCaretColor(new Color(222, 174, 9));
        exangField.setBorder(lineBorder);

        oldPeakField = new JTextField();
        oldPeakField.setBounds(465,325,200,30);
        oldPeakField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        oldPeakField.setForeground(new Color(222, 174, 9));
        oldPeakField.setBackground(new Color(3, 4, 40));
        oldPeakField.setCaretColor(new Color(222, 174, 9));
        oldPeakField.setBorder(lineBorder);

        slopeField = new JTextField();
        slopeField.setBounds(465,360,200,30);
        slopeField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        slopeField.setForeground(new Color(222, 174, 9));
        slopeField.setBackground(new Color(3, 4, 40));
        slopeField.setCaretColor(new Color(222, 174, 9));
        slopeField.setBorder(lineBorder);

        caField = new JTextField();
        caField.setBounds(465,395,200,30);
        caField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        caField.setForeground(new Color(222, 174, 9));
        caField.setBackground(new Color(3, 4, 40));
        caField.setCaretColor(new Color(222, 174, 9));
        caField.setBorder(lineBorder);

        thalField = new JTextField();
        thalField.setBounds(465,430,200,30);
        thalField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        thalField.setForeground(new Color(222, 174, 9));
        thalField.setBackground(new Color(3, 4, 40));
        thalField.setCaretColor(new Color(222, 174, 9));
        thalField.setBorder(lineBorder);
        //========================Inputs========================//

        submitButton = new JButton("Submit");
        submitButton.setBounds(265,475,150,30);
        submitButton.setFocusPainted(false);
        submitButton.setFont(new Font("Monospaced" , Font.BOLD , 15));
        submitButton.setForeground(new Color(3, 4, 40));
        submitButton.setBackground(new Color(222, 174, 9));
        submitButton.addActionListener(this);

        this.setTitle("Heart Disease Predictor");
        this.getContentPane().setBackground(new Color(3, 4, 40));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(750 , 700);
        this.setVisible(true);
        this.setLayout(null);
        this.add(headingPanel);
        this.add(inputsPanel);
        this.add(footnote);
        this.setIconImage(titleImage.getImage());
        inputsPanel.add(ageLabel);
        inputsPanel.add(sexLabel);
        inputsPanel.add(cpLabel);
        inputsPanel.add(trestbpsLabel);
        inputsPanel.add(cholLabel);
        inputsPanel.add(fbsLabel);
        inputsPanel.add(restecgLabel);
        inputsPanel.add(thalachLabel);
        inputsPanel.add(exangLabel);
        inputsPanel.add(oldPeakLabel);
        inputsPanel.add(slopeLabel);
        inputsPanel.add(caLabel);
        inputsPanel.add(thalLabel);
        inputsPanel.add(ageField);
        inputsPanel.add(sexField);
        inputsPanel.add(cpField);
        inputsPanel.add(trestbpsField);
        inputsPanel.add(cholField);
        inputsPanel.add(fbsField);
        inputsPanel.add(restecgField);
        inputsPanel.add(thalachField);
        inputsPanel.add(exangField);
        inputsPanel.add(oldPeakField);
        inputsPanel.add(slopeField);
        inputsPanel.add(caField);
        inputsPanel.add(thalField);
        inputsPanel.add(submitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton){

            //taking inputs from all text fields
            ageStr = ageField.getText();
            sexStr = sexField.getText();
            cpStr = cpField.getText();
            trestbpsStr = trestbpsField.getText();
            cholStr = cholField.getText();
            fbsStr = fbsField.getText();
            restecgStr = restecgField.getText();
            thalachStr = thalachField.getText();
            exangStr = exangField.getText();
            oldpeakStr = oldPeakField.getText();
            slopeStr = slopeField.getText();
            caStr = caField.getText();
            thalStr = thalField.getText();

            //parsing them to integer
            age = Integer.parseInt(ageStr);
            sex = Integer.parseInt(sexStr);
            cp = Integer.parseInt(cpStr);
            trestbps = Integer.parseInt(trestbpsStr);
            chol = Integer.parseInt(cholStr);
            fbs = Integer.parseInt(fbsStr);
            restecg = Integer.parseInt(restecgStr);
            thalach = Integer.parseInt(thalachStr);
            exang = Integer.parseInt(exangStr);
            oldpeak = Double.parseDouble(oldpeakStr);
            slope = Integer.parseInt(slopeStr);
            ca = Integer.parseInt(caStr);
            thal = Integer.parseInt(thalStr);

            //populating the input map
            inputData.put("age", age);
            inputData.put("sex", sex);
            inputData.put("cp", cp);
            inputData.put("trestbps", trestbps);
            inputData.put("chol", chol);
            inputData.put("fbs", fbs);
            inputData.put("restecg", restecg);
            inputData.put("thalach", thalach);
            inputData.put("exang", exang);
            inputData.put("oldpeak", oldpeak);
            inputData.put("slope", slope);
            inputData.put("ca", ca);
            inputData.put("thal", thal);

            //converting our map to a json string
            try {
                jsonInput = convertMapToJson(inputData);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            //sensing the JSON string to the api
            try {
                String apiUrl = "http://127.0.0.1:5000/predict";

                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();

                // Taking the response from the api
                System.out.println("Response Code: " + responseCode);
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder responseContent = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        responseContent.append(line);
                    }
                    result = String.valueOf(responseContent);

                    //telling the user the result of their inputs
                    if ("0".equals(result)){
                        JOptionPane.showMessageDialog(null,"You don't have heart disease :)","Result",JOptionPane.INFORMATION_MESSAGE);
                    } else if ("1".equals(result)) {
                        JOptionPane.showMessageDialog(null,"You have heart disease :(","Result",JOptionPane.WARNING_MESSAGE);
                    } else {
                        System.out.println("Unexpected API Response: " + result);
                        JOptionPane.showMessageDialog(null,"There was some error in inputs.","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                connection.disconnect();
            } catch (Exception exp) {
                exp.printStackTrace();
            }

        }
    }
    //function to convert a hash-map to a json string
    private static String convertMapToJson(Map<String, Object> map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }
}
