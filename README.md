# Meds

## Project structure:

 ### java

#### controllers
- **menuController**
    - settings
        - DocAccSettingsC
    - DoctorMenuC
    - LoginC
    - PatientMenuC
- **otherController**
    - RegisterC
    - RequestHelpViewC
    
#### misc
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
    
#### model
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
    
#### Main

### resources

#### css
- theme.css

#### data
- users.json

#### view
- **menuView**
    - doctorMenuView.fxml
    - loginView.fxml
    - patientMenuView.fxml
- **otherView**
    - registerView.fxml
    - requestHelpView.fxml
