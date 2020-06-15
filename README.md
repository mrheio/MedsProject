# Meds

## Project structure:

### java

#### controllers (java classes)
- **menuController**
    - menus
         - DoctorMenuC
         - *MenuC*
         - PatientMenuC
    - settings
        - *AccSettingsC* 
        - DocAccSettingsC
        - PatientAccSettingsC
    - LoginC
- **otherController**
    - RegisterC
    - RequestHelpViewC
    
#### misc (java classes)
- **users**
    - DoctorMisc
    - PatientMisc
    - UserMisc
- **utility**
    - security
        - BCrypt
        - SecurityMisc
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
#### MainStarter

### resources

#### css
- theme.css

#### images
- FXML
    - LogoSVG.fxml
- PNG
    - appIcon-01
    - appIcon-02
    - edit
- SVG
    - Logo    

#### view (fxml files)
- **menuView**
    - menusView
        - doctorMenuView
        - patientMenuView  
    - settingsView
        - docAccSettingsView
        - patientAccSettingsView 
    - loginView
    
- **otherView**
    - registerView
    - requestHelpView
