**Introduction:**

Developed a Java GUI application and a Python ML API to predict heart disease risks collaboratively. Utilized a Support Vector Machine (SVM) model with an accuracy of 98.04% from the Heart Disease Prediction project. The Java Swing GUI allows users to input their data for estimating their heart disease risk.

**Components:**

- **Heart-Disease_Prediction Project:**
  - Heart disease prediction model using different classification models and their comparisons. This project was made for the research paper "SVM Based Risk Estimation for Heart Disease Prediction" published under IEEE Xplore     (https://ieeexplore.ieee.org/document/10463271) and presented in amity universities' confluence-2024.
  - Used data from the UCI ML repository, focusing on 14 key attributes related to heart disease prediction.
  - Models were evaluated and compared, with SVM demonstrating the highest accuracy.
  - Test scores for all models are available.
  
**Python API (Flask):**
- Implemented an API with Flask, utilizing the SVM model for heart disease prediction.
- Achieved an accuracy rate of 98.04%.
- Accepts JSON input via POST method and returns a prediction (0 or 1) indicating the presence or absence of heart disease.

**Java Swing Application:**
- Developed a user-friendly GUI application for data input and prediction.
- Communicates with the Python API to process user data and display prediction results.

**Workflow:**
- Users input data through the Java Swing GUI.
- Java application sends JSON input to Python API.
- Python API processes data using SVM model and returns prediction.
- Java application displays prediction result to the user.

**Visuals:**
- [Test score results](https://github.com/Yash-29-10-2003/Heart-Disease_Prediction/assets/89728102/828d52dd-c1aa-488c-aec7-13e241bb5b7f)
- [Java Swing GUI](https://github.com/Yash-29-10-2003/Heart-Disease-Predictor/assets/89728102/f97c62f4-c803-46d2-8564-fb55efe6fade)
