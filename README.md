# Meds

##Project structure:

 ###java

####controllers
- **menuController**
    - DoctorViewC
    - LoginC
    - PatientViewC
- **otherController**
    - RegisterC
    - RequestHelpViewC
    
####misc
- **users**
    - DoctorMisc
    - PatientMisc
    - UserMisc
- **utility**
    - BCrypt
    - FileMisc
    - NodeMisc
    - TextMisc
    - ViewMisc
    
####model
- **date**
    - Date
    - month
- **other**
    - PatientProblem
    - ProblemTypes
- **roles**
    - Doctor
    - Patient
    - *Person*
    
####Main

###resources

####css
- theme.css

####data
- users.json

####view
- **menuView**
    - doctorView.fxml
    - loginView.fxml
    - patientView.fxml
- **otherView**
    - registerView.fxml
    - requestHelpView.fxml