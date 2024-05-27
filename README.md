_**Introduction :**_

A java GUI application and a ML python API that work together to predict heart disease risks.
Used the SVM model with a 98.04% accuracy from my Heart Disease Prediction model project to create an API that gets accessed by a java swing GUI application in which users can input their data to estimate their risk to the presence of a heart disease.

_**Components:**_

# Heart-Disease_Prediction
Heart disease prediction model using different classification models and their comparisons. This project was made for the research paper "SVM Based Risk Estimation for Heart Disease Prediction" published under IEEE Xplore (https://ieeexplore.ieee.org/document/10463271) and presented in amity universities' confluence-2024.

The data set used is from the UCI ML repository, the set dates from 1988 and consists of four databases (Cleveland, Hungary, Switzerland, Long Beach). It consists of 76 attributes but usually published experiments refer to a subset of 14 of them. 
1. Age 
2. Sex 
3. Chest pain type (cp) 
4. Blood pressure at rest (trestbps) 
5. Serum cholesterol (chol) in mg/dl 
6. Fasting blood sugar more than 120 mg/dl 
7. Resting electrocardiogram (restecg) findings 
8. The highest heart rate ever recorded (thalach) 
9. Exang (exercise-induced angina) 
10. Oldpeak ST depression caused by activity compared to rest 
11. The slope of the ST section of the peak workout 
12. Fluoroscopically colored main vessel count (0–3) 13. Thal: 0 denotes a normal condition, 1 a fixed abnormality, and 2 a reversible defect. 
14. Heart disease is present (target variable) - No presence (0), presence (1-4), or both.


Following are the test score results for all the models :
![image](https://github.com/Yash-29-10-2003/Heart-Disease_Prediction/assets/89728102/828d52dd-c1aa-488c-aec7-13e241bb5b7f)

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
