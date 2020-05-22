# Meds

## Project structure:

 ### java

#### controllers (java classes)
- **menuController**
    - settings
        - DocAccSettingsC
        - PatientAccSettingsC
    - DoctorMenuC
    - LoginC
    - PatientMenuC
- **otherController**
    - RegisterC
    - RequestHelpViewC
    
#### misc (java classes)
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
    
#### model (java classes)
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

#### images
- edit.png

#### view (fxml files)
- **menuView**
    - settingsView
        - docAccSettingsView
        - patientAccSettingsView
    - doctorMenuView
    - loginView
    - patientMenuView
- **otherView**
    - registerView
    - requestHelpView
