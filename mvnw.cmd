@REM ----------------------------------------------------------------------------
@REM Boutique Moderna - Maven Wrapper Launcher
@REM ----------------------------------------------------------------------------
@echo off
setlocal

set "MAVEN_CMD=C:\Program Files\NetBeans-25\netbeans\java\maven\bin\mvn.cmd"

if exist "%MAVEN_CMD%" (
    call "%MAVEN_CMD%" %*
    exit /b %ERRORLEVEL%
)

where mvn >nul 2>nul
if %ERRORLEVEL% equ 0 (
    call mvn %*
    exit /b %ERRORLEVEL%
)

echo [ERROR] No se encontro Maven en el sistema ni en NetBeans.
echo Configure MAVEN_HOME o agregue mvn al PATH.
exit /b 1
