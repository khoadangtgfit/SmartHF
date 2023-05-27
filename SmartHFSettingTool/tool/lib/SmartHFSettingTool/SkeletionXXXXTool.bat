call ..\..\..\bin\env.bat

set LIB_PATH=%FLC_ROOT%\tool\lib\common\ui_common.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\tool\lib\common\ui_feature.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\tool\lib\SmartHFSettingTool\LBRS.jar
rem set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\tool\lib\SmartHFSettingTool\icu4j-4_4_2.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\app_unit.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\job_body.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\base\leg_common.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\base\leg_flc.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\base\leg_jmf.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\base\leg_rlx.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\base\leg_server.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\base\leg_utility.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\base\leg_ruleEngine.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\base\net.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\base\plaf.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\base\plugin.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\base\rac.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\base\utility.jar

set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\ext\swingx-1.0.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\ext\commonutil.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\ext\commons-io-1.4.jar
set LIB_PATH=%LIB_PATH%;%FLC_ROOT%\lib\ext\log4j-1.2.15.jar

set JRE_ROOT=%FLC_ROOT%\lib\jre

set path=%path%;%FLC_ROOT%\tool\lib;%FLC_ROOT%\proc\bin\color;%FLC_ROOT%\proc\bin\ProcMng\bin;%FLC_ROOT%\proc\bin\Common;
set LBRS_HOME=%FLC_ROOT%\\tool\\lib\\LBRecorderSettingTool
set LBRS_RES_DIR=%FLC_ROOT%\\tool\\res\\LBRecorderSettingTool

cd /d %FLC_ROOT%

%JRE_ROOT%\bin\java.exe -classpath %LIB_PATH% -Degl.prop=%FLC_ROOT%\config\flc.properties -Dipct.home="%LBRS_HOME%" -Dipct.res.dir="%LBRS_RES_DIR%" -Xms256m -Xmx512m jp.co.screen.LBRS.LBRSMain

pause
