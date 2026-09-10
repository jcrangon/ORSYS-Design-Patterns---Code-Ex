@echo off
setlocal EnableExtensions
set "BASEDIR=%~dp0"
set "MAVEN_VERSION=3.9.9"
set "MAVEN_HOME_LOCAL=%BASEDIR%.mvn\apache-maven-%MAVEN_VERSION%"
set "MAVEN_ZIP=%TEMP%\apache-maven-%MAVEN_VERSION%-bin.zip"
set "MAVEN_URL=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MAVEN_VERSION%/apache-maven-%MAVEN_VERSION%-bin.zip"
where java >nul 2>&1
if errorlevel 1 (
  echo [ERREUR] Java n'est pas disponible dans le PATH.
  echo Installez un JDK 17 ou plus puis relancez.
  exit /b 1
)
if not exist "%MAVEN_HOME_LOCAL%\bin\mvn.cmd" (
  echo [Wrapper] Premier lancement : telechargement de Maven %MAVEN_VERSION%...
  if not exist "%BASEDIR%.mvn" mkdir "%BASEDIR%.mvn"
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$ProgressPreference='SilentlyContinue'; Invoke-WebRequest -UseBasicParsing -Uri '%MAVEN_URL%' -OutFile '%MAVEN_ZIP%'"
  if errorlevel 1 (
    echo [ERREUR] Impossible de telecharger Maven. Verifiez Internet/proxy.
    exit /b 1
  )
  powershell -NoProfile -ExecutionPolicy Bypass -Command "Expand-Archive -Path '%MAVEN_ZIP%' -DestinationPath '%BASEDIR%.mvn' -Force"
  if errorlevel 1 exit /b 1
  del /q "%MAVEN_ZIP%" >nul 2>&1
)
call "%MAVEN_HOME_LOCAL%\bin\mvn.cmd" %*
exit /b %ERRORLEVEL%
