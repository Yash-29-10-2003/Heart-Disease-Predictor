_**Introduction :**_

A java GUI application and a ML python API that work together to predict heart disease risks.
Used the SVM model with a 98.04% accuracy from my Heart Disease Prediction model project to create an API that gets accessed by a java swing GUI application in which users can input their data to estimate their risk to the presence of a heart disease.

_**Components:**_

**Python API (Flask):**

- Model: Utilized a Support Vector Machine (SVM) model for heart disease prediction.
- Accuracy: Achieved an impressive accuracy of 98.04%.
- Flask API: Created an API using Flask, with a POST method to receive JSON input.
- Output: The API returns an integer value: 0 (heart disease not present) or 1 (heart disease present).

**Java Swing Application:**

- User Interface: Developed a Java Swing GUI application for user interaction.
- Input: Allows users to input their data for heart disease risk estimation.
- Communication with API: Sends user input to the Python API using appropriate requests.
- Output Display: Communicates the prediction result from the API to the user.

![image](https://github.com/Yash-29-10-2003/Heart-Disease-Predictor/assets/89728102/f97c62f4-c803-46d2-8564-fb55efe6fade)


**Workflow:**

- The user inputs their data through the Java Swing GUI application.
- The Java application sends this input as a JSON string to the Python API using the appropriate POST method.
- The Python API receives the JSON input, processes it using the SVM model, and predicts the presence or absence of heart disease.
- The API returns the prediction (0 or 1) to the Java application.
- The Java application displays the prediction result to the user.
