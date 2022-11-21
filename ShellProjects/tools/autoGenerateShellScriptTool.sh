#!/usr/bin/env bash
########################################################
# ScriptFileName: autoGenerateShellScriptTool.sh
# Version: V1.0
# CreateDate: 2022-10-21
# Author: 陈朋
# Description: 快速生成shell脚本模板工具
########################################################

:<<!
    函数说明: 向脚文件中写入打印日志模块
    入参列表: $1=脚本文件(带绝对路径)
    返回值: 无
!
function writePrintLog() {
    local scriptFile=$1
    shift

    # INFO
    local infoLog=":<<!\n\t\
函数说明: 打印INFO级别日志\n\t\
入参列表: \$1=日志文件(带绝对路径) \$2=日志内容\n\t\
返回值: 无\n\
!\n\
function log_info(){\n\t\
local logFile=\$1\n\t\
local msg=\$2\n\
\n\t\
echo -e \"\$(date +%F\" \"%T)\\\033[1;32m [INFO]\\\033[0m \${msg}\" | tee -a \${logFile}\n\
}\n"
    echo -e ${infoLog} >> ${scriptFile}

    # WARN
    local warnLog=":<<!\n\t\
函数说明: 打印WARN级别日志\n\t\
入参列表: \$1=日志文件(带绝对路径) \$2=日志内容\n\t\
返回值: 无\n\
!\n\
function log_warn(){\n\t\
local logFile=\$1\n\t\
local msg=\$2\n\
\n\t\
echo -e \"\$(date +%F\" \"%T)\\\033[1;33m [WARN]\\\033[0m \${msg}\" | tee -a \${logFile}\n\
}\n"
    echo -e ${warnLog} >> ${scriptFile}

    # ERROR
    local errorLog=":<<!\n\t\
函数说明: 打印ERROR级别日志\n\t\
入参列表: \$1=日志文件(带绝对路径) \$2=日志内容\n\t\
返回值: 无\n\
!\n\
function log_error(){\n\t\
local logFile=\$1\n\t\
local msg=\$2\n\
\n\t\
echo -e \"\$(date +%F\" \"%T)\\\033[1;31m [ERROR]\\\033[0m \${msg}\" | tee -a \${logFile}\n\
}\n"
    echo -e ${errorLog} >> ${scriptFile}
}

:<<!
    函数说明: 向脚文件中写入加载配置文件函数
    入参列表: $1=脚本文件(带绝对路径)
    返回值: 无
!
function writeLoadProfile() {
  local scriptFile=$1
  shift

  local loadProfileInfo=":<<!\n\t\
函数说明: 加载配置文件函数\n\t\
入参列表: \$1=配置文件(带绝对路径)\n\t\
返回值: 无\n\
!\n\
function load_profile(){\n\t\
local profile=\$1\n\t\
shift\n\
\n\t\
while read line;\n\t\
do\n\t\t\
eval \"\$line\"\n\t\
done < \${profile}\n\
}\n"
  echo -e ${loadProfileInfo} >> ${scriptFile}
}

:<<!
    函数说明: 向脚文件中写入分割字符串为数组函数
    入参列表: $1=脚本文件(带绝对路径)
    返回值: 无
!
function writeSplitStr2Array(){
  local scriptFile=$1
  shift

  local splitStrInfo=":<<!\n\t\
函数说明: 分割字符串为数组函数\n\t\
入参列表: \$1=待分割字符串 \$2=指定分割符\n\t\
返回值: 数组\n\
!\n\
function split_str_to_array(){\n\t\
local str=\$1\n\t\
local split=\$2\n\
\n\t\
#保存当前shell默认的分割符，一会要恢复回去\n\t\
OLD_IFS=\"\$IFS\"\n\t\
#将shell的分割符号改为指定的分割符\n\t\
IFS=\${split}\n\t\
local array=(\${str})\n\t\
#恢复shell默认分割符配置\n\t\
IFS=\"\$OLD_IFS\"\n\t\
echo \${array[@]}\n\
}\n"
  echo -e ${splitStrInfo} >> ${scriptFile}
}

:<<!
    函数说明: 向脚文件中写入递归遍历目录函数(注明目录和文件)
    入参列表: $1=脚本文件(带绝对路径)
    返回值: 无
!
function writeRecursionDir(){
  local scriptFile=$1
  shift

  local recursionDirInfo=":<<!\n\t\
函数说明: 分割字符串为数组函数\n\t\
入参列表: \$1=待遍历目录\n\t\
返回值: 无\n\
!\n\
function recursion_dir(){\n\t\
local currDir=\$1\n\t\
shift\n\
\n\t\
if [[ -d \${currDir} ]]; then\n\t\t\
echo \"目录:\${currDir}\"\n\t\t\
for item in \`ls \${currDir}\`\n\t\t\
do\n\t\t\t\
recursion_dir \${currDir}/\${item}\n\t\t\
done\n\t\
else\n\t\t\
  echo \"文件:\${currDir}\"\n\t\
fi\n\
}\n"
  echo -e ${recursionDirInfo} >> ${scriptFile}
}

:<<!
    函数说明: 向脚文件中写入主函数
    入参列表: $1=脚本文件(带绝对路径)
    返回值: 无
!
function writeMain(){
  local scriptFile=$1
  shift

  local fileFinalInfo=":<<!\n\t\
函数说明: 主函数\n\t\
入参列表: 无\n\t\
返回值: 无\n\
!\n\
function main(){\n\
\n\t\
# 获取当前路径\n\t\
baseDir=\$(cd \$(dirname \$0); pwd)\n\
\n\t\
# 定义日志文件\n\t\
logFile=\${baseDir}/\${LOG_FILE_NAME}\n\t\
log_info \${logFile} \"当前脚本所在路径为:\${baseDir}\"\n\
\n\t\
# 获取系统日期 yyyy-MM-dd\n\t\
sysDate=\`date +%Y-%m-%d\`\n\t\
log_info \${logFile} \"系统日期:\${sysDate}\"\n\
\n\t\
echo TODO\n\
}\n\
\n\
main\n\
\n\
exit 0"
  echo -e ${fileFinalInfo} >> ${scriptFile}
}

:<<!
    函数说明: 主函数
    入参列表: 无
    返回值: 无
!
function main(){

  # 获取当前路径
  baseDir=$(cd $(dirname $0); pwd)
  echo "当前脚本所在路径为:${baseDir}"

  # 获取系统日期 yyyy-MM-dd
  sysDate=`date +%Y-%m-%d`
  echo "系统日期:${sysDate}"

  # 若未输入则提示用户输入脚本文件名称
  scriptName=""
  while [[ ${scriptName} = "" ]];
  do
      echo -e "\033[1m 请输入脚本名称: \033[0m"
      read scriptName
  done
  echo "您输入的脚本文件名称为:${scriptName}"

  # 检查用户输入的脚本是否重名
  scriptFile="${baseDir}/${scriptName}.sh"
  while [[ -f ${scriptFile} ]];
  do
     echo -e "\033[1m 脚本文件${scriptFile}已存在,是否覆盖(y/n)? \033[0m"
     read repeatFlag
     if [[ "Y" != "${repeatFlag}" ]] && [[ "y" != "${repeatFlag}" ]];then
       echo -e "\033[1m 请重新输入脚本名称: \033[0m"
       read scriptName
     else
       break
      fi
  done
  # 创建脚本文件
  `rm -rf ${scriptFile}`
  `touch ${scriptFile}`

  # 询问是否需要创建配置文件(默认配置文件后缀.properties)
  echo -e "\033[1m 是否创建配置文件(y/n)? \033[0m"
  read createProfileFlag
  profileName=""
  if [[ "Y" = "${createProfileFlag}" ]] || [[ "y" = "${createProfileFlag}" ]];then
      while [[ ${profileName} = "" ]];
      do
            echo -e "\033[1m 请输入配置文件名称: \033[0m"
            read profileName
      done

      # 检查用户输入的配置文件名称是否重名
      profile="${baseDir}/${profileName}.properties"
      while [[ -f ${profile} ]];
      do
           echo -e "\033[1m 配置文件${profile}已存在,是否覆盖(y/n)? \033[0m"
           read repeatFlag
           if [[ "Y" != "${repeatFlag}" ]] && [[ "y" != "${repeatFlag}" ]];then
               echo -e "\033[1m 请重新输入配置文件名称: \033[0m"
               read profileName
           else
               break
           fi
       done
       # 创建配置文件
       `rm -rf ${profile}`
       `touch ${profile}`
  fi

  # 1. 向脚本中写入头文件信息
  fileHeaderInfo="#!/usr/bin/env bash"
  echo ${fileHeaderInfo} > ${scriptFile}

  # 2. 向脚本中写入脚本说明信息
  fileExplainInfo="########################################################\n\
# ScriptFileName: ${scriptName}.sh\n\
# Version: V1.0\n\
# CreateDate: ${sysDate}\n\
# Author: 陈朋\n\
# Description: 快速生成shell脚本模板工具\n\
########################################################"
  echo -e ${fileExplainInfo} >> ${scriptFile}

  # 3. 向脚本中写入配置文件名称常量信息
  if [[ "Y" = "${createProfileFlag}" ]] || [[ "y" = "${createProfileFlag}" ]];then
    echo -e "\n#配置文件名称常量\nreadonly CONFIG_FILE_NAME=${profileName}.properties" >> ${scriptFile}
  fi

  # 4. 向脚本中写入日志文件名称常量信息
  echo -e "\n#日志文件名称常量\nreadonly LOG_FILE_NAME=console.log\n" >> ${scriptFile}

  # 5. 向脚本文件中写入打印日志函数
  writePrintLog ${scriptFile}

  # 6. 向脚本文件中写入加载配置文件函数
  writeLoadProfile ${scriptFile}

  # 7. 向脚本文件中写入分割字符串为数组函数
  writeSplitStr2Array ${scriptFile}

  # 8. 向脚本文件中写入递归目录函数
  writeRecursionDir ${scriptFile}

  # 9. 向脚本文件中写入GET方式上传文件


  # 10. 向脚本文件中写入GET方式请求接口

  # 11. 向脚本文件中写入POST方式上传文件

  # 12. 向脚本文件中写入POST方式请求接口

  # 13. (入参校验函数) (读取压缩包内文件指定内容 zcat)

  # 最后一步,向脚本文件中写入主函数
  writeMain ${scriptFile}

}

main

exit 0