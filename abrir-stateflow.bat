@echo off
setlocal

set ECLIPSE_DIR=C:\Users\edney.siqueira\Documents\Ferramentas\eclipse-luna-P2K
set VM_PATH=%ECLIPSE_DIR%\jre\bin\javaw.exe
set DEDICATED_WORKSPACE=%USERPROFILE%\.csi-stateflow-workspace

echo =======================================================
echo Iniciando CSI StateFlow / ExecutionFlow Visual Tool...
echo JRE Isolado: %VM_PATH%
echo Workspace Isolado: %DEDICATED_WORKSPACE%
echo =======================================================

if "%~1"=="" (
    start "" "%ECLIPSE_DIR%\eclipse.exe" -vm "%VM_PATH%" -data "%DEDICATED_WORKSPACE%"
) else (
    start "" "%ECLIPSE_DIR%\eclipse.exe" -vm "%VM_PATH%" -data "%DEDICATED_WORKSPACE%" "%~1"
)
exit /b 0
