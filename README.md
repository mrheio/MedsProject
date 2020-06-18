# Meds

## structure:

### java

#### controllers
- **account**
    - menus
         - DoctorMenuC
         - *MenuC*
         - PatientMenuC
    - settings
        - *AccSettingsC* 
        - DocAccSettingsC
        - PatientAccSettingsC
- **entry**
    - LoginC
    - RegisterC
- **other**
    - PasswordResetC
    - RequestHelpC
    
#### misc
- **user**
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
#### MainStarter

### resources

#### css
- theme.css

#### images
- FXML
    - LogoSVG.fxml
- PNG
    - edit
    - icon
- SVG
    - Logo    

#### view (fxml files)
- **account**
    - menus
        - doctorMenuView
        - patientMenuView  
    - settings
        - docAccSettingsView
        - patientAccSettingsView 
- **entry**
    - loginView
    - registerView
- **other**
    - passwordResetView
    - requestHelpView
