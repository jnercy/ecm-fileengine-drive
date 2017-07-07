#!/usr/bin/env bash

#/var/githome/ecm-drive/drive-rest/build/libs

cd /var/githome/ecm-drive

git pull  https://git.nextcont.com/Dev/ecm-drive.git

echo -e "\033[41;37m git pull  complete...  \033[0m"


gradle clean
echo -e "\033[41;37m gradle clean  complete...  \033[0m"


gradle build -x test
echo -e "\033[41;37m gradle build  complete...  \033[0m"


cd /usr/local/software/ecm-drive

./deleteBoot.sh

echo -e "\033[41;37m delete old jar complete...  \033[0m"


mv /var/githome/ecm-drive/drive-rest/build/libs/ecm-drive-0.0.1.jar /usr/local/software/ecm-drive

echo -e "\033[41;37m move new jar complete...  \033[0m"

./start.sh && tail -f nohup.out -n 200

