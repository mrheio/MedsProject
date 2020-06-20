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
- **combobox**
    - AccSettings
    - LogOut
    - Menu
    - *Option*
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
- RenogareSoft-Regular.ttf (font)
- theme.css

#### data
- users.json

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


## Create jar

- go to project folder

```
mvnw clean install
```

- go to "target" folder

```
java -jar Meds-jar-with-dependencies.jar
``` 
